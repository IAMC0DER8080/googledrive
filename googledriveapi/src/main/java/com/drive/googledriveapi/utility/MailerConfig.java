package com.drive.googledriveapi.utility;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.stereotype.Component;


@Component
public class MailerConfig {
	
	//static Properties properties =PropertyFactory.getAppProperties();	
	public static int sendMail(String fileID,String fileName) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	
		Properties props = new Properties();
		   props.put("mail.smtp.auth","true");
		   props.put("mail.smtp.starttls.enable", "true");
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.port", "587");//
		   
		   try {
			   
			   String message="";
			  Session session = Session.getInstance(props, new javax.mail.Authenticator() {
		      protected PasswordAuthentication getPasswordAuthentication() {
		         return new PasswordAuthentication("officeaccessacc10@gmail.com",  "Tushar@123");
		      }
		   });
		   Message msg = new MimeMessage(session);
		   msg.setFrom(new InternetAddress("officeaccessacc10@gmail.com", false));

		   msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("tusharabhi619@gmail.com"));
		   msg.setSubject("MailerDemo");
		   msg.setContent("MailerDemo pratice round", "text/html");
		   msg.setSentDate(new Date());

		   MimeBodyPart messageBodyPart = new MimeBodyPart();
		   
		   
		   messageBodyPart.setContent("File Has been uploded at:"+timestamp+" with ID "+fileID+" and file name "+fileName+" in Google Drive.", "text/html");
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   MimeBodyPart attachPart = new MimeBodyPart();

		  
		   msg.setContent(multipart);
		   Transport.send(msg);
		   return 00;
		   }catch(Exception e) {
			   System.out.println("An Error Occured While Send The mail  "+e);
			   e.printStackTrace();
		   }
		return 01;
}
}