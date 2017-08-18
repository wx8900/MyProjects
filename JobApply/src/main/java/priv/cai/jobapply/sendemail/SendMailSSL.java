package priv.cai.jobapply.sendemail;


import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMultipart;

import priv.cai.jobapply.constant.Constants;

import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

public class SendMailSSL {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("cwx8900@gmail.com","wx98989988");
				}
			});

		try {
			//
			// creates a new e-mail message
			Message message = new MimeMessage(session);
	 
			message.setFrom(new InternetAddress("cwx8900@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("cwx8900@gmail.com"));
			message.setSubject("Testing Subject 1111111");
			message.setText("Dear Mail ," +
					"\n\n No spam to my email, please!");
			message.setSentDate(new Date());
	 
	        // creates message part
	        //MimeBodyPart messageBodyPart = new MimeBodyPart();
	 
	        // creates multi-part
			Multipart multipart = new MimeMultipart();
	        //multipart.addBodyPart(messageBodyPart);
	        //messageBodyPart.setContent(multipart);
	 
	        // adds attachments
	        String[] attachFiles = new String[2];
	        attachFiles[0] = Constants.PATH_RESUME;
	        attachFiles[1] = Constants.PATH_COVER_LETTER;
	        if (attachFiles != null && attachFiles.length > 0) {
	        	MimeBodyPart attachPart = null;
	            for (String filePath : attachFiles) {
	            	attachPart = new MimeBodyPart();
	                try {
	                    attachPart.attachFile(filePath);
	                } catch (IOException ex) {
	                    ex.printStackTrace();
	                }
	 
	                multipart.addBodyPart(attachPart);
	            }
	        }
	 
	        // sets the multi-part as e-mail's content
	        message.setContent(multipart);
	 
	        // sends the e-mail
			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}
