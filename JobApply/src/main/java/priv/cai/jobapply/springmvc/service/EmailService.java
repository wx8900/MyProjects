package priv.cai.jobapply.springmvc.service;


public interface EmailService {
	
	public void sendEmail(String subject, String body, String sender, String recipients) throws Exception;

	
}
