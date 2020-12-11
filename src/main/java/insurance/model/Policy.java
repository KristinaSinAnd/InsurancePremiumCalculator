package insurance.model;

import java.util.List;

public class Policy {

    private String policyNumber;

    private PolicyStatus policyStatus;

    private List<PolicyObject> policyObjects;

    public Policy() {

    }

    public Policy(String policyNumber, PolicyStatus policyStatus, List<PolicyObject> policyObjects) {
        this.policyNumber = policyNumber;
        this.policyStatus = policyStatus;
        this.policyObjects = policyObjects;
    }


    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public PolicyStatus getPolicyStatus() {
        return policyStatus;
    }

    public void setPolicyStatus(PolicyStatus policyStatus) {
        this.policyStatus = policyStatus;
    }

    public List<PolicyObject> getPolicyObjects() {
        return policyObjects;
    }

    public void setPolicyObjects(List<PolicyObject> policyObjects) {
        this.policyObjects = policyObjects;
    }


    @Override
    public String toString() {
        return "Policy{" +
                "policyNumber='" + policyNumber + '\'' +
                ", policyStatus=" + policyStatus +
                ", policyObjects=" + policyObjects +
                '}';
    }
}


