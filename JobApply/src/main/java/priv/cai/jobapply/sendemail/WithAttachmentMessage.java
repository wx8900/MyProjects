package priv.cai.jobapply.sendemail;

import java.io.FileOutputStream;  
import java.util.Properties;  
 
import javax.activation.DataHandler;  
import javax.activation.FileDataSource;  
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;  
import javax.mail.internet.InternetAddress;  
import javax.mail.internet.MimeBodyPart;  
import javax.mail.internet.MimeMessage;  
import javax.mail.internet.MimeMultipart;

import priv.cai.jobapply.constant.Constants;  
 
/**  
 * 创建内含附件、图文并茂的邮件  
 * @author haolloyin  
 */ 
public class WithAttachmentMessage {  
 
    /**  
     * 根据传入的文件路径创建附件并返回  
     */ 
    public MimeBodyPart createAttachment(String fileName) throws Exception {  
        MimeBodyPart attachmentPart = new MimeBodyPart();  
        FileDataSource fds = new FileDataSource(fileName);  
        attachmentPart.setDataHandler(new DataHandler(fds));  
        attachmentPart.setFileName(fds.getName());  
        return attachmentPart;  
    }  
 
    /**  
     * 根据传入的邮件正文body和文件路径创建图文并茂的正文部分  
     */ 
    public MimeBodyPart createContent(String body, String fileName)  
            throws Exception {  
        // 用于保存最终正文部分  
        MimeBodyPart contentBody = new MimeBodyPart();  
        // 用于组合文本和图片，"related"型的MimeMultipart对象  
        MimeMultipart contentMulti = new MimeMultipart("related");  
 
        // 正文的文本部分  
        MimeBodyPart textBody = new MimeBodyPart();  
        textBody.setContent(body, "text/html;charset=gbk");  
        contentMulti.addBodyPart(textBody);  
 
        // 正文的图片部分  
        MimeBodyPart jpgBody = new MimeBodyPart();  
        FileDataSource fds = new FileDataSource(fileName);  
        jpgBody.setDataHandler(new DataHandler(fds));  
        jpgBody.setContentID("logo_jpg");  
        contentMulti.addBodyPart(jpgBody);  
 
        // 将上面"related"型的 MimeMultipart 对象作为邮件的正文  
        contentBody.setContent(contentMulti);  
        return contentBody;  
    }  
 
    /**  
     * 根据传入的 Seesion 对象创建混合型的 MIME消息  
     */ 
    public MimeMessage createMessage(Session session) throws Exception {  
        String from = "cwx8900@gmail.com";  
        String to = "cwx8900@gmail.com";  
        String subject = "Testing";  
        String body = "Dear Hiring Manager,</br>" 
                + "I have uploaded my resume and cover letter.</br>" 
                + "Thank you for reviewing them!";  
 
        MimeMessage msg = new MimeMessage(session);  
        msg.setFrom(new InternetAddress(from));  
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(to));  
        msg.setSubject(subject);  
 
        // 创建邮件的各个 MimeBodyPart 部分  
        MimeBodyPart attachment01 = createAttachment(Constants.PATH_RESUME);  
        MimeBodyPart attachment02 = createAttachment(Constants.PATH_COVER_LETTER);  
        MimeBodyPart content = createContent(body, "");  
 
        // 将邮件中各个部分组合到一个"mixed"型的 MimeMultipart 对象  
        MimeMultipart allPart = new MimeMultipart("mixed");  
        allPart.addBodyPart(attachment01);  
        allPart.addBodyPart(attachment02);  
        allPart.addBodyPart(content);  
 
        // 将上面混合型的 MimeMultipart 对象作为邮件内容并保存  
        msg.setContent(allPart);  
        msg.saveChanges();  
        return msg;  
    }  
 
    // 测试生成邮件  
    public static void main(String[] args) throws Exception {  
        WithAttachmentMessage mail = new WithAttachmentMessage();  
        //Session session = Session.getDefaultInstance(new Properties());
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
        MimeMessage message = mail.createMessage(session);  
        message.writeTo(new FileOutputStream("withAttachmentMail.eml"));  
    }  
} 