package priv.cai.jobapply.constant;

public enum Distance {
	
	FIVE_MILES(5), TEN_MILES(10), FIFTEEN_MILES(15), TWENTYFIVE_MILES(25), FIFTY_MILES(50), ONEHUNDRED_MILES(100);

    private final int distanceCode;

    Distance (int distanceCode) {
        this.distanceCode = distanceCode;
    }
    
    public int getDistanceCode() {
        return this.distanceCode;
    }
    
    public static void main(String[] args) {
    	Distance type = Distance.FIVE_MILES;
    	System.out.println(type.getDistanceCode());
    	
    	for (Distance type2 : Distance.values()) {
    	    System.out.println(type2);
    	}
    }

}
