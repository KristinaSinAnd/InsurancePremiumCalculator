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
import javax.validation.Valid;

import static java.util.stream.Collectors.groupingBy;

@Service
public class PremiumCalculatorService {

    public String calculate(@Valid Policy policy) {
        BigDecimal total = BigDecimal.ZERO;

        Map<RiskType, List<PolicySubObject>> groupedByRiskType = policy.getPolicyObjects().stream()
                .map(PolicyObject::getPolicySubObjects)
                .flatMap(List::stream)
                .collect(groupingBy(PolicySubObject::getRiskType));

        for (Map.Entry<RiskType, List<PolicySubObject>> groupedRiskType : groupedByRiskType.entrySet()) {
            RiskType riskType = groupedRiskType.getKey();

            BigDecimal riskTypeInsuranceSum = groupedRiskType.getValue().stream()
                    .map(PolicySubObject::getSumInsured)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);

            BigDecimal riskTypePremium = riskType.getRiskPremium().apply(riskTypeInsuranceSum);
            total = total.add(riskTypePremium);
        }

        return String.format("%s %s", total, Currency.getInstance("EUR").getCurrencyCode());
    }
}
