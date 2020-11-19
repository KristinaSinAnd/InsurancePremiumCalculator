# **InsurancePremiumCalculator**

Created functionality calculates policy premium.

## **Short description**

Functionality at the moment consists of two premium types: **firePremium** and **theftPremium**.
It is also possible to extend implementation for new risk types in the future by adding an additional field in Enum class RiskType.

No GUI is used, policy data is sent through the API directly to the methods. <br>
No database is used, functionality doesn’t store any data. It receives policy object, calculates
premium and returns result. <br>
Calculation methods and functions are tested.

## **Premium calculation formula:**

PREMIUM = PREMIUM_FIRE + PREMIUM_THEFT

PREMIUM_FIRE = SUM_INSURED_FIRE * COEFFICIENT_FIRE <br>
SUM_INSURED_FIRE - total sum insured of all policy's sub-objects with type "Fire" <br>
COEFFICIENT_FIRE - by default 0.014 but if SUM_INSURED_FIRE is over 100 then 0.024

PREMIUM_THEFT = SUM_INSURED_THEFT * COEFFICIENT_THEFT <br>
SUM_INSURED_THEFT - total sum insured of all policy's sub-objects with type "Theft" <br>
COEFFICIENT_THEFT - by default 0.11 but if SUM_INSURED_THEFT equal or greater than 15 then 0.05

*These conditions are described in RiskType class and tested in the corresponding RiskTypeTest together with function* "private final Function<BigDecimal, BigDecimal> riskPremium".

## **Acceptance criteria**

1) If there is 1 policy, 1 object and 2 sub-objects as described below, then calculator should return premium = **2.28 EUR** <br>
Risk type = FIRE, Sum insured = 100.00 <br>
Risk type = THEFT, Sum insured = 8.00

2) If policy's total sum insured for risk type FIRE and total sum insured for risk type THEFT are as described below, then calculator should return premium = **17.13 EUR** <br>
Risk type = FIRE, Sum insured = 500.00 <br>
Risk type = THEFT, Sum insured = 102.51

*Necessary functionality is described in the class PremiumCalculatorService, where such instruments as streams, maps and for loop, as well as Currency class are used. Corresponding tests are provided in PremiumCalculatorServiceTest.*

## **Object entities**
### **Policy:**
Policy can have multiple policy objects and each policy object can have multiple sub-objects.

| ATTRIBUTES | NOTES| 
|----------------|---------|
| Policy number | e.g. LV20-02-100000-5 | 
| Policy status | e.g. REGISTERED, APPROVED| 
| Policy objects | Collection of one or multiple objects| 

### **Policy object:**
Policy objects can have multiple sub-objects and can be related only to one policy
Policy objects have 2 attributes:

| ATTRIBUTES | NOTES| 
|----------------|---------|
| Object name  | e.g. A House| 
| Sub-objects | Collection of none or multiple sub-objects | 
 
### **Policy sub-object:**
Policy has 3 attributes:
Policy sub-objects can be related only to one policy object and have 3 attributes:

| ATTRIBUTES | NOTES| 
|----------------|---------|
| Sub-object name |  e.g. TV | 
| Sum insured | Cost that will be covered by insurance | 
| Risk type |  e.g. FIRE, THEFT| 


## **Technologies used:**
Java 8 <br>
JUnit 4 <br>
Spring Framework <br>
Maven
