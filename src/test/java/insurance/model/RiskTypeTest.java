package insurance.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.Function;

import static org.junit.Assert.*;

public class RiskTypeTest {

    @Test
    public void getFireRiskPremiumLessThan100() {
        Function<BigDecimal, BigDecimal> riskPremiumFunction = RiskType.FIRE.getRiskPremium();
        BigDecimal actual = riskPremiumFunction.apply(BigDecimal.valueOf(10));
        assertEquals(BigDecimal.valueOf(0.14), actual);
    }

    @Test
    public void getFireRiskPremiumGreaterThan100() {
        Function<BigDecimal, BigDecimal> riskPremiumFunction = RiskType.FIRE.getRiskPremium();
        BigDecimal actual = riskPremiumFunction.apply(BigDecimal.valueOf(101));
        assertEquals(BigDecimal.valueOf(2.42), actual);
    }

    @Test
    public void getTheftRiskPremiumLessThan15() {
        Function<BigDecimal, BigDecimal> riskPremiumFunction = RiskType.THEFT.getRiskPremium();
        BigDecimal actual = riskPremiumFunction.apply(BigDecimal.valueOf(10));
        assertEquals(BigDecimal.valueOf(1.10).setScale(2, RoundingMode.HALF_EVEN), actual);
    }

    @Test
    public void getTheftRiskPremiumEqualOrGreaterThan15() {
        Function<BigDecimal, BigDecimal> riskPremiumFunction = RiskType.THEFT.getRiskPremium();
        BigDecimal actual = riskPremiumFunction.apply(BigDecimal.valueOf(15));
        assertEquals(BigDecimal.valueOf(0.75), actual);
    }
}