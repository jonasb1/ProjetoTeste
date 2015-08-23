package br.com.testeOpacidade.teste;

import java.util.Properties;











import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.junit.Test;

public class aaaa {
	  public static void main(String [] args)
	   {    
	      // Recipient's email ID needs to be mentioned.
	      String to = "jonas.benevenuto.192@gmail.com";
	      
	      // Sender's email ID needs to be mentioned
	      String from = "aparecidobenevenuto@gmail.com";

	      // Assuming you are sending email from localhost
	      String host = "localhost";

	      // Get system properties
	      Properties properties = System.getProperties();

	      // Setup mail server
	      properties.setProperty("mail.smtp.host", host);
	      
	      
	      // Get the default Session object.
	      Session session = Session.getDefaultInstance(properties);

	      try{
	         // Create a default MimeMessage object.
	         MimeMessage message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	         message.addRecipient(Message.RecipientType.TO,
	                                  new InternetAddress(to));

	         // Set Subject: header field
	         message.setSubject("This is the Subject Line!");

	         // Now set the actual message
	         message.setText("This is actual message");

	         // Send message
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }
	   }
	  
	  @Test
	  public void enviarEmail(){
	  Properties props = new Properties();  
      /** Parâmetros de conexão com servidor Hotmail */  
      props.put("mail.transport.protocol", "smtp");  
      props.put("mail.smtp.host", "smtp.live.com");  
      props.put("mail.smtp.socketFactory.port", "587");  
      props.put("mail.smtp.socketFactory.fallback", "false");  
      props.put("mail.smtp.starttls.enable", "true");  
      props.put("mail.smtp.auth", "true");  
      props.put("mail.smtp.port", "587");  

      Session session = Session.getDefaultInstance(props,  
                  new javax.mail.Authenticator() {  
                       protected PasswordAuthentication getPasswordAuthentication()   
                       {  
                             return new PasswordAuthentication("jonas_alexandre92@hotmail.com", "esmeraldarubi");  
                       }  
                  });  

      /** Ativa Debug para sessão */  
      session.setDebug(true);  

      try {  

            Message message = new MimeMessage(session);  
            message.setFrom(new InternetAddress("jonas_alexandre92@hotmail.com")); //Remetente  

            message.setRecipients(Message.RecipientType.TO,   
                              InternetAddress.parse("jonas_benevenuto@outlook.com")); //Destinatário(s)  
            message.setSubject("Enviando email com JavaMail");//Assunto  
            message.setText("Enviei este email utilizando JavaMail com minha conta Hotmail!");  
            /**Método para enviar a mensagem criada*/  
            Transport.send(message);  

            System.out.println("Feito!!!");  

       } catch (MessagingException e) {  
            throw new RuntimeException(e);  
      }
	  
	}
}