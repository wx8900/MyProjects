package priv.cai.jobapply.sendemail;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.testng.annotations.Test;


public class EmailService {
	
	public static void main(String[] args) {
		new EmailService().test();
	}

@Test
public void test(){
    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", true); // added this line
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.user", "cwx8900@gmail.com");
    props.put("mail.smtp.password", "wx98989988");
    props.put("mail.smtp.port", "465");//587
    props.put("mail.smtp.auth", true);



    Session session = Session.getInstance(props,null);
    MimeMessage message = new MimeMessage(session);

    System.out.println("Port: "+session.getProperty("mail.smtp.port"));

    // Create the email addresses involved
    try {
        InternetAddress from = new InternetAddress("cwx8900@gmail.com");
        message.setSubject("Yes we can");
        message.setFrom(from);
        message.addRecipients(Message.RecipientType.TO, InternetAddress.parse("receivermail"));

        // Create a multi-part to combine the parts
        Multipart multipart = new MimeMultipart("alternative");

        // Create your text message part
        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("some text to send");

        // Add the text part to the multipart
        multipart.addBodyPart(messageBodyPart);

        // Create the html part
        messageBodyPart = new MimeBodyPart();
        String htmlMessage = "Our html text";
        messageBodyPart.setContent(htmlMessage, "text/html");


        // Add html part to multi part
        multipart.addBodyPart(messageBodyPart);

        // Associate multi-part with message
        message.setContent(multipart);

        // Send message
        Transport transport = session.getTransport("smtp");
        transport.connect("smtp.gmail.com", "cwx8900@gmail.com", "wx98989988");
        System.out.println("Transport: "+transport.toString());
        transport.sendMessage(message, message.getAllRecipients());


    } catch (AddressException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    } catch (MessagingException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
}
}