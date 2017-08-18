package priv.cai.jobapply.constant;

public enum SalaryEstimate {
	
	ONEHUNDREDFIFTEEN_K(115000), ONEHUNDREDFIVE_K(105000), NINETYFIVE_K(95000), SEVENTYFIVE_K(75000), FIFTYFIVE_K(55000);

    private final int salaryEstimateCode;

    SalaryEstimate (int salaryEstimateCode) {
        this.salaryEstimateCode = salaryEstimateCode;
    }
    
    public int getSalaryEstimateCode() {
        return this.salaryEstimateCode;
    }
    
    public static void main(String[] args) {
    	SalaryEstimate type = SalaryEstimate.ONEHUNDREDFIFTEEN_K;
    	System.out.println(type.getSalaryEstimateCode());
    	
    	for (SalaryEstimate type2 : SalaryEstimate.values()) {
    	    System.out.println(type2);
    	}
    }

}
