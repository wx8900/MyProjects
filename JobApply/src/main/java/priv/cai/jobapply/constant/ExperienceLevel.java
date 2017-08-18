package priv.cai.jobapply.constant;

public enum ExperienceLevel {

	SENIORLEVEL(3), MIDLEVEL(2), ENTRYLEVEL(1);


    private final int levelCode;

    ExperienceLevel (int levelCode) {
        this.levelCode = levelCode;
    }
    
    public int getLevelCode() {
        return this.levelCode;
    }
    
    public static void main(String[] args) {
    	ExperienceLevel level = ExperienceLevel.SENIORLEVEL;
    	System.out.println(level.getLevelCode());
    	
    	for (ExperienceLevel level2 : ExperienceLevel.values()) {
    	    System.out.println(level2);
    	}
    	
    	ExperienceLevel level3 = ExperienceLevel.MIDLEVEL;
		switch (level3) {
		    case SENIORLEVEL   : System.out.println("  SENIOR  "); break;
		    case MIDLEVEL : System.out.println("  MEDIUM  "); break;
		    case ENTRYLEVEL    : System.out.println("  ENTRY  "); break;
		}
    }

}
