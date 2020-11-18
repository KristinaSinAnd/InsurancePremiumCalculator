package insurance.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

public enum RiskType {

    FIRE(sumInsured -> {
        if (sumInsured.compareTo(BigDecimal.valueOf(100)) > 0) {
            return sumInsured.multiply(BigDecimal.valueOf(0.024)).setScale(2, RoundingMode.HALF_EVEN);
        }
        return sumInsured.multiply(BigDecimal.valueOf(0.014)).setScale(2, RoundingMode.HALF_EVEN);
    }),

    THEFT(sumInsured -> {
        if (sumInsured.compareTo(BigDecimal.valueOf(15)) > -1) {
            return sumInsured.multiply(BigDecimal.valueOf(0.05)).setScale(2, RoundingMode.HALF_EVEN);
        }
        return sumInsured.multiply(BigDecimal.valueOf(0.11)).setScale(2, RoundingMode.HALF_EVEN);
    });

    private final Function<BigDecimal, BigDecimal> riskPremium;

    RiskType(Function<BigDecimal, BigDecimal> riskPremium) {
        this.riskPremium = riskPremium;
    }

    public Function<BigDecimal, BigDecimal> getRiskPremium() {
        return riskPremium;
    }
}
