package insurance.service;

import insurance.model.Policy;
import insurance.model.PolicyObject;
import insurance.model.PolicySubObject;
import insurance.model.RiskType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;

@Service
public class PremiumCalculatorService {

    public String calculate(Policy policy) {

        Map<RiskType, List<PolicySubObject>> groupedByRiskType = groupByRiskType(policy);

        BigDecimal total = getTotal(groupedByRiskType);

        return String.format("%s %s", total, Currency.getInstance("EUR").getCurrencyCode());
    }

    private BigDecimal getTotal(Map<RiskType, List<PolicySubObject>> groupedByRiskType) {
        BigDecimal total = BigDecimal.ZERO;
        for (Map.Entry<RiskType, List<PolicySubObject>> groupedRiskType : groupedByRiskType.entrySet()) {
            RiskType riskType = groupedRiskType.getKey();

            BigDecimal riskTypeInsuranceSum = getInsuranceSum(groupedRiskType);

            BigDecimal riskTypePremium = riskType.getRiskPremium().apply(riskTypeInsuranceSum);
            total = total.add(riskTypePremium);
        }
        return total;
    }

    private BigDecimal getInsuranceSum(Map.Entry<RiskType, List<PolicySubObject>> groupedRiskType) {
        return groupedRiskType.getValue().stream()
                .map(PolicySubObject::getSumInsured)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private Map<RiskType, List<PolicySubObject>> groupByRiskType(Policy policy) {
        return policy.getPolicyObjects().stream()
                .map(PolicyObject::getPolicySubObjects)
                .flatMap(List::stream)
                .collect(groupingBy(PolicySubObject::getRiskType));
    }
}
