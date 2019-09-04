package com.stream.tool;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
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
import javax.servlet.http.Part;

import com.store.model.StrVO;
import com.storecategory.model.TestStocaJDBCGetOne;

public class ShareTool {
	
	

	public static byte[] sendPicture(String path) throws IOException {
		
		FileInputStream in = new FileInputStream(path);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buff = new byte[in.available()];
		int len;
		while((len=in.read(buff)) != -1) {
			out.write(buff, 0 , len);
		}
		out.close();
		in.close();
		return out.toByteArray();
	}
	
	public static byte[] sendPicture(Part part) throws IOException {
		
		 InputStream in = part.getInputStream();
		 ByteArrayOutputStream out = new ByteArrayOutputStream();
		 byte[] buff = new byte[in.available()];
		 int len;
		 while((len=in.read(buff)) != -1) {
			 out.write(buff, 0 , len);
		 }
		
		out.close();
		in.close();
		return out.toByteArray();
		
	}
	
	public static String sendInfo(String text) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(text));
		StringBuilder sb = new StringBuilder();
		String info;
		while((info = in.readLine()) != null) {
			sb.append(info);
			sb.append("\n\r");
		}
		in.close();
		return sb.toString();
	}
	
	public static void readPicture(byte[] bytes) throws IOException {
		
		File place = new File("images");
		if(!place.exists()) {
			place.mkdirs();
		}
		
		FileOutputStream out = null;
		if(bytes.length != 0) {
			out = new FileOutputStream("images/"+ TestStocaJDBCGetOne.path + ".png");
			out.write(bytes);
			out.flush();
		} else {
			out = new FileOutputStream("images/nopic.png");
		}
		
		out.close();
		
	}
	
	public static void readFile(String str) throws IOException {
		
		File place = new File("images");
		if(!place.exists()) {
			place.mkdirs();
		}
		
		PrintWriter out = null;
		if(str.length() != 0) {
			out = new PrintWriter("images/" + TestStocaJDBCGetOne.name + ".txt");
			out.write(str);
			out.flush();
		}
		
		out.close();
		
	}
	
	public static void sendMail(String to, String subject, String messageText) {
		
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
			   System.out.println("傳送成功!");
			   
		     }	catch (MessagingException e){
			     System.out.println("傳送失敗!");
			     e.printStackTrace();
		     }
	  }
	
	public static String messageText() throws UnsupportedEncodingException {
		
		StringBuffer text = new StringBuffer();
		text.append("恭喜成為easyfood 食在方便店家身份\n");
		text.append("請妥善保存貴司的使用帳號及密碼 : ");
		
		return text.toString();
	}
	
	
}


