package priv.cai.jobapply.sendemail;


import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTLS {

	public static void main(String[] args) {
	    Properties props = new Properties();
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.socketFactory.port", "465");
	    props.put("mail.smtp.socketFactory.class",
	            "javax.net.ssl.SSLSocketFactory");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.port", "587"); 
	    Session session = Session.getDefaultInstance(props,
	        new javax.mail.Authenticator() {
	                            @Override
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("cwx8900@gmail.com","wx98989988");
	            }
	        });

	    try {

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress("cwx8900@gmail.com"));
	        message.setRecipients(Message.RecipientType.TO,
	                InternetAddress.parse("cwx8900@gmail.com"));
	        message.setSubject("Testing Subject2222");
	        message.setText("Test Mail");

	        Transport.send(message);

	        System.out.println("Done");

	    } catch (MessagingException e) {
	        throw new RuntimeException(e);
	    }
	}
}