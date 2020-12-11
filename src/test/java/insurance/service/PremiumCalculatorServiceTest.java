package insurance.service;

import insurance.model.*;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class PremiumCalculatorServiceTest {

    private PremiumCalculatorService premiumCalculatorService = new PremiumCalculatorService();

    @Test
    public void calculatePremium100Fire8Theft() {

        PolicySubObject policySubObject1 = new PolicySubObject();
        policySubObject1.setSubObjectName("TV 1");
        policySubObject1.setSumInsured(BigDecimal.valueOf(100));
        policySubObject1.setRiskType(RiskType.FIRE);


        PolicySubObject policySubObject2 = new PolicySubObject();
        policySubObject2.setSubObjectName("Umbrella");
        policySubObject2.setSumInsured(BigDecimal.valueOf(8));
        policySubObject2.setRiskType(RiskType.THEFT);

        PolicyObject policyObject = new PolicyObject();
        policyObject.setObjectName("House 1");
        policyObject.setPolicySubObjects(Arrays.asList(policySubObject1, policySubObject2));

        Policy policy = new Policy();
        policy.setPolicyObjects(Arrays.asList(policyObject));
        policy.setPolicyNumber("0001");
        policy.setPolicyStatus(PolicyStatus.REGISTERED);

        String premium = premiumCalculatorService.calculate(policy);
        assertEquals("2.28 EUR", premium);
    }

    @Test
    public void calculatePremium500Fire102_51Theft() {

        PolicySubObject policySubObject1 = new PolicySubObject();
        policySubObject1.setSubObjectName("TV 1");
        policySubObject1.setSumInsured(BigDecimal.valueOf(250));
        policySubObject1.setRiskType(RiskType.FIRE);

        PolicySubObject policySubObject2 = new PolicySubObject();
        policySubObject2.setSubObjectName("TV 2");
        policySubObject2.setSumInsured(BigDecimal.valueOf(250));
        policySubObject2.setRiskType(RiskType.FIRE);

        PolicySubObject policySubObject3 = new PolicySubObject();
        policySubObject3.setSubObjectName("Umbrella 1");
        policySubObject3.setSumInsured(BigDecimal.valueOf(50));
        policySubObject3.setRiskType(RiskType.THEFT);

        PolicySubObject policySubObject4 = new PolicySubObject();
        policySubObject4.setSubObjectName("Umbrella 2");
        policySubObject4.setSumInsured(BigDecimal.valueOf(52.51));
        policySubObject4.setRiskType(RiskType.THEFT);

        PolicyObject policyObject1 = new PolicyObject();
        policyObject1.setObjectName("House 1");
        policyObject1.setPolicySubObjects(Arrays.asList(policySubObject1, policySubObject3));

        PolicyObject policyObject2 = new PolicyObject();
        policyObject2.setObjectName("House 2");
        policyObject2.setPolicySubObjects(Arrays.asList(policySubObject2, policySubObject4));

        Policy policy = new Policy();
        policy.setPolicyObjects(Arrays.asList(policyObject1, policyObject2));
        policy.setPolicyNumber("0001");
        policy.setPolicyStatus(PolicyStatus.REGISTERED);

        String premium = premiumCalculatorService.calculate(policy);
        assertEquals("17.13 EUR", premium);
    }


}