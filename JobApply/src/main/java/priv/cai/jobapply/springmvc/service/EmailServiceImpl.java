package priv.cai.jobapply.springmvc.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import priv.cai.jobapply.constant.Constants;
import priv.cai.jobapply.springmvc.controller.LoginController;

@Service("emailService")
@Transactional
public class EmailServiceImpl implements EmailService {
	
	final static Logger logger = Logger.getLogger(EmailServiceImpl.class);
	
	private MimeMessage message;
	private Session session;
	private Transport transport;
	
    private String sender_username = "cwx8900@gmail.com";
    private String sender_password = "wx98989988";
    private String receiveUser = "cwx8900@gmail.com";
    
	//private Properties properties = new Properties();
	
	public static void main(String[] args) throws Exception {
		/*String filepath="src/main/resources/uploadresume/Resume.docx";
		File file=new File(filepath);
		System.out.println(file.exists());*/
		
		//new EmailServiceImpl(true).sendEmail();
	}
	
	//public EmailServiceImpl (boolean debug) { // debug switch
	public EmailServiceImpl () {
		InputStream input = null;
		Properties props = new Properties();
		try {
			input = new FileInputStream(Constants.PATH_PROPERTIES_EMAIL);
		} catch (FileNotFoundException e) {
			System.out.print("property file in the path '" + Constants.PATH_PROPERTIES_EMAIL + "' not found!");
		}
		
        try {
        	props.load(input);
        	/*
        	props.setProperty("mail.transport.protocol", "smtp");
            props.setProperty("mail.host", mailhost);
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.quitwait", "false");*/
        } catch (IOException e) {
        	logger.error("This is error : " + e);
        }
 
		session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(sender_username, sender_password);
			}
		});
        
		//session.setDebug(debug);  // debug switch
        message = new MimeMessage(session);
    }
	
	@Override
	public void sendEmail(String subject, String body, String sender, String recipients) throws Exception {
		try {
			//Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress((sender != null) ? sender : sender_username));
			String receiver = (recipients != null) ? recipients : receiveUser;
			if (recipients != null) {
				if (recipients.indexOf(',') > 0) 
	                message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(receiver));
				else
	                message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
			} else {
				message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiveUser));
			}
			
			message.setSubject((subject != null) ? subject : Constants.EMAIL_SUBJECT);
			message.setSentDate(new Date());

			// creates message part
			Multipart multipart = new MimeMultipart();
			
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent((body != null) ? body : Constants.EMAIL_BODY, Constants.EMAIL_FORMAT);
			multipart.addBodyPart(contentPart);
			
	        // adds attachments
			MimeBodyPart attachPart1 = new MimeBodyPart();
			MimeBodyPart attachPart2 = new MimeBodyPart();
			try {
				attachPart1.attachFile(Constants.PATH_RESUME);
				attachPart2.attachFile(Constants.PATH_COVER_LETTER);
            } catch (IOException ex) {
            	logger.error("This is error : " + ex);
            } catch (MessagingException e) {
            	logger.error("This is error : " + e);
            }
            multipart.addBodyPart(attachPart1);
            multipart.addBodyPart(attachPart2);
            
	        message.setContent(multipart);
	        message.saveChanges();
	        
	        Transport.send(message);
	        
			System.out.println("===== Send email successful !!!===== ");
		} catch (MessagingException e) {
			logger.error("This is error : " + e);
			throw new RuntimeException(e);
		} finally {
            if (transport != null) {
                try {
                    transport.close();
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                transport = null;
            }
		}
	}

}
