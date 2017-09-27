package priv.cai.jobapply.constant;

public final class Constants {
	
	public static final String REGEX_NUMERIC = "\\d";
	public static final String REGEX_NEWLINE = "\n";
	public static final String SPACE = " ";
	public static final String COMMA = ",";
	public static final String SPACE_TO_PLUS = "+";
	public static final String COMMA_TO_HEX = "%2C";
	public static final String DATAFORMAT_YMD = "yyyy-MM-dd";
	public static final String DATAFORMAT_MDY_HMS = "MM/dd/yyyy HH:mm:ss";
	public static final String DATAFORMAT_YMD_HMS = "yyyy-MM-dd HH:mm:ss";
	public static final String DATAFORMAT_YMDHMS = "yyyyMMdd_HHmmss";
	public static final String TIMEZONE_US_LA = "America/Los_Angeles";
	
	//validate
	String REGEX_ZIPCODE_US = "^[0-9]{5}(?:-[0-9]{4})?$";
	
	public static final String LIKE = "like";
	public static final String EQUALS = "equals";
	
	public static final String PATH_PROPERTIES = "src/main/resources/data/config.properties";
	public static final String PATH_PROPERTIES_EMAIL = "src/main/resources/emailconfig.properties";

	public static final String PATH_RESUME = "src/main/resources/uploadresume/Jack Cai_Resume.docx";
	public static final String PATH_COVER_LETTER = "src/main/resources/uploadresume/Cover Letter.docx";
	
	public static final String FILE_NAME = "src/main/resources/dataset/";
	
	// email
	public static final String EMAIL_FORMAT = "text/html;charset=UTF-8";
	public static final String EMAIL_SUBJECT = "Apply for job";
    public static final String EMAIL_BODY = "<p>Dear Hiring Manager,</p>"
			+ "<p>I have uploaded my resume and cover letter in the attachment.</p>"
			+ "<p>Thank you for reviewing them!</p><p></p><p>Sincerely,</p><p>Jack</p>";
    
    //big data
    public static final String BIG_DATA_PATTERN = "[\\p{Punct}\\d]";
    public static final String BIG_DATA_INPUT_PATH = "src/main/resources/dataset/";
    public static final String BIG_DATA_WORDCOUNT_PATH = "src/main/resources/wordcount/";
    public static final String BIG_DATA_CACHE_PATH = "src/main/resources/cache/";
    public static final String BIG_DATA_OUTPUT_PATH = "src/main/resources/output/";
	
}
