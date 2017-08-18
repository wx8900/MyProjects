package priv.cai.jobapply.constant;

public enum Stars {

	FIVE(5), FOUR(4), THREE(3), TWO(2), ONE(1);


    private final int starCode;

    Stars (int starCode) {
        this.starCode = starCode;
    }
    
    public int getStarCode() {
        return this.starCode;
    }
    
    public static void main(String[] args) {
    	Stars stars = Stars.FIVE;
    	System.out.println(stars.getStarCode());
    	
    	for (Stars level2 : Stars.values()) {
    	    System.out.println(level2);
    	}
    }

}
