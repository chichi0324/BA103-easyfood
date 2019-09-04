package com.stream.tool;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.store.model.StrVO;

public class MailService {
	
	
	public void sendMail(String to, String subject, String messageText) {
			
	   try {
		 
		   Properties props = new Properties();
		   props.put("mail.smtp.host", "smtp.gmail.com");
		   props.put("mail.smtp.socketFactory.port", "465");
		   props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		   props.put("mail.smtp.auth", "true");
		   props.put("mail.smtp.port", "465");

     
	     final String myGmail = "ixlogic.wu@gmail.com";
	     final String myGmail_password = "AAA45678";
		   Session session = Session.getInstance(props, new Authenticator() {
			   protected PasswordAuthentication getPasswordAuthentication() {
				   return new PasswordAuthentication(myGmail, myGmail_password);
			   }
		   });

		   Message message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(myGmail));
		   message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));
		  
		  
		   message.setSubject(subject);
		
		   message.setText(messageText);

		   Transport.send(message);
		   System.out.println("success!");
     }catch (MessagingException e){
	     System.out.println("fail!");
	     e.printStackTrace();
     }
   }
	
	

	
//	 public static void main (String args[]){
//
//      String to = "kurtie.one215@gmail.com";
//      
//      String subject = "easyfood 食在方便店家註冊成功";
//      
//      String ch_name = "Yung";
//      String passRandom = "kkk555";
//      String messageText = "Hello! " + ch_name + " 請謹記此密碼: " + passRandom + "\n" +" (已經啟用)"; 
//       
//      MailService mailService = new MailService();
//      mailService.sendMail(to, subject, messageText);
//
//   }


}
