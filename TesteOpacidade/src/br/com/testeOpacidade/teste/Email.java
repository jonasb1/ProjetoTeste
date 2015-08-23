package br.com.testeOpacidade.teste;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.junit.Ignore;
import org.junit.Test;

public class Email {
	@Ignore
	public void sendEmail() throws EmailException {

		SimpleEmail email = new SimpleEmail();
		// Utilize o hostname do seu provedor de email
		System.out.println("alterando hostname...");
		email.setHostName("smtp.gmail.com");
		// Quando a porta utilizada não é a padrão (gmail = 465)
		email.setSmtpPort(465);
		// Adicione os destinatários

		email.addTo("jonas_alexandre92@hotmail.com", "Jonas");
		// Configure o seu email do qual enviará
		email.setFrom("jonas_benevenuto@outlook.com", "Seu nome");
		// Adicione um assunto
		email.setSubject("Testando envio de Mensagem");
		// Adicione a mensagem do email
		email.setMsg("Este é o teste dos testes");
		// Para autenticar no servidor é necessário chamar os dois métodos
		// abaixo
		System.out.println("autenticando...");
		email.setSSL(true);
		email.setAuthentication("username", "senha");
		System.out.println("enviando...");
		email.send();
		System.out.println("Email enviado!");
	}

	@Ignore
	public void enviar() {
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
						return new PasswordAuthentication(
								"jonas_alexandre92@hotmail.com",
								"esmeraldarubi");
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("jonas_benevenuto@outlook.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse("jonas_benevenuto@outlook.com"));
			message.setSubject("Testing Subject");
			message.setText("Dear Mail Crawler,"
					+ "\n\n No spam to my email, please!");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

	public static void main(String[] args) throws AddressException,
			MessagingException {
		// objeto para definicao das propriedades de configuracao do provider
		Properties props = new Properties();
		// o valor "smtp.host.com.br" deve ser alterado para o seu servidor SMTP

		// props.put("mail.host", "smtp.outlook.com");

		props.setProperty("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true");
		String mailhost = "smtp.outlook.com";
		props.setProperty("mail.host", mailhost);
		props.put("mail.smtp.port", "587");
		props.getProperty("mail.smtp.starttls.enable", "true");

		props.setProperty("mail.user", "jonas_alexandre92@hotmail.com");
		props.setProperty("mail.password", "esmeraldarubi");

		// obtendo um Session com a configuração
		// do servidor SMTP definida em props
		Session session = Session.getDefaultInstance(props);

		// criando a mensagem
		MimeMessage message = new MimeMessage(session);
		// substituir pelos e-mails desejados

		Address from = new InternetAddress("jonas_alexandre92@hotmail.com");
		Address to = new InternetAddress("jonas_benevenuto@outlook.com");
		// configurando o remetente e o destinatario

		message.setFrom(from);
		message.addRecipient(RecipientType.TO, to);
		// configurando a data de envio, o assunto e o texto da mensagem
		message.setSentDate(new Date());
		message.setSubject("Assunto da mensagem");
		message.setText("Texto da mensagem!");
		// enviando

		Transport.send(message);
		System.out.println("Mensgaem enviada");
	}

	@Ignore
	public void enviarT() throws UnsupportedEncodingException,
			MessagingException {

		Properties props = new Properties();

		String host = "localhost";
		int port = 587;
		String user = "jonas_alexandre92@hotmail.com";
		String senha = "esmeraldarubi";
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress("jonas_benevenuto@outlook.com"));
		msg.setSubject("*****o selina");
		msg.setText("do you want to have diner ?");
		Transport transport = session.getTransport("smtp");
		transport.connect(host, port, user, senha);
		transport.sendMessage(msg, msg.getAllRecipients());

	}

}
