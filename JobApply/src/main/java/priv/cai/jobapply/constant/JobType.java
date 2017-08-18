package priv.cai.jobapply.constant;

public enum JobType {
	
	FULLTIME(5), PARTTIME(4), CONTRACT(3), TEMPORARY(2), INTERNSHIP(1);

    private final int jobTypeCode;

    JobType (int jobTypeCode) {
        this.jobTypeCode = jobTypeCode;
    }
    
    public int getJobTypeCode() {
        return this.jobTypeCode;
    }
    
    public static void main(String[] args) {
    	JobType type = JobType.FULLTIME;
    	System.out.println(type.getJobTypeCode());
    	
    	for (JobType type2 : JobType.values()) {
    	    System.out.println(type2);
    	}
    }

}
