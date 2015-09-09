package br.com.testeOpacidade.teste;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Ignore;
import org.junit.Test;

public class Teste {

	@Ignore
	public static void main(String[] args) {
		// Recipient's email ID needs to be mentioned.
		String to = "jonas_alexandre92@hotmail.com";

		// Sender's email ID needs to be mentioned
		String from = "jonas_benevenuto@outlook.com";

		// Assuming you are sending email from localhost
		//String host = "localhost";

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server

		properties.setProperty("mail.transport.protocol", "smtp");
		String mailhost = "smtp.live.com";
		properties.setProperty("mail.host", mailhost);

		properties.put("mail.smtp.port", "587");
	    properties.setProperty("mail.smtp.starttls.enable", "true");
		
		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);

		try {
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
	
	@Test
	public void sendEmail() throws EmailException {
	    
		   SimpleEmail email = new SimpleEmail();
		   //Utilize o hostname do seu provedor de email
		   System.out.println("alterando hostname...");
		   email.setHostName("smtp.outlook.com");
		   //Quando a porta utilizada não é a padrão (gmail = 465)
		   email.setSmtpPort(587);
		   //Adicione os destinatários
		   email.addTo("jonas_benevenuto@outlook.com", "Seu nome");
		   //Configure o seu email do qual enviará
		   email.setFrom("jonas_alexandre92@hotmail.com", "Jonas");
		   //Adicione um assunto
		   email.setSubject("Test message");
		   //Adicione a mensagem do email
		   email.setMsg("This is a simple test of commons-email");
		   //Para autenticar no servidor é necessário chamar os dois métodos abaixo
		   System.out.println("autenticando...");
		   email.setSSL(true);
		   email.setAuthentication("jonas_alexandre92@hotmail.com", "esmeraldarubi");
		   System.out.println("enviando...");
		   email.send();
		   System.out.println("Email enviado!");
		}
	
	
	@Test
	 public void enviar()   {    
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
	
	
}
