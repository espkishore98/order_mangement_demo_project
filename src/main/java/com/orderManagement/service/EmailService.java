package com.orderManagement.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.orderManagement.entity.EmailTemplates;
import com.orderManagement.entity.Users;
import com.orderManagement.repository.EmailTemplateRepository;
import com.orderManagement.repository.UserRepository;

@Service
public class EmailService {
	@Value("${spring.mail.username}")
	private String sender;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.host}")
	private String host;
	@Autowired
	EmailTemplateRepository emailTemplateRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	private JavaMailSender javaMailSender;

	private Map<String, String> replacables = new HashMap<String, String>();

	public String sendEmail(String templateType, String bearerToken) {

		String authToken = bearerToken.substring(7);
		String externalId = UserService.getUserExternalId(authToken);
		Users user = userRepository.findByExternalId(externalId);
		EmailTemplates template = emailTemplateRepository.findByTemplateType(templateType);

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 25);
		props.put("mail.transport.protocol", "smtp");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(sender, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(sender));

			// Set To: header field of the header.

			// Set Subject: header field
			message.setSubject(template.getSubject());
			// Now set the actual message
			String msg = template.getEmailBody();
			if (user != null) {
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(user.getEmailId() != null ? user.getEmailId() : "no email found"));
				replacables.put("<name>", user.getUserName());
				msg = msg.replace("<name>", user.getUserName());
				message.setText(msg);

			} else {
				msg.replace("<name>", "");
				message.setText(template.getEmailBody());

			}

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");
			return "email sent successfully";

		} catch (MessagingException e) {
//				throw new RuntimeException(e);
			return e.getMessage();
		}

	}

	public String testMail(String templateType, String bearerToken) {

		String authToken = bearerToken.substring(7);
		String externalId = UserService.getUserExternalId(authToken);
		Users user = userRepository.findByExternalId(externalId);
		EmailTemplates template = emailTemplateRepository.findByTemplateType(templateType);
		// Recipient's email ID needs to be mentioned.
		String to = "espkishore@gmail.com";

//		String host = "smtp.gmail.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 25);
		props.put("mail.transport.protocol", "smtp");

		// Get the Session object.
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new javax.mail.PasswordAuthentication(sender, password);
			}
		});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(sender));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject(template.getSubject());
			// Now set the actual message
			String msg = template.getEmailBody();
			if (user != null) {
				replacables.put("<name>", user.getUserName());
				msg = msg.replace("<name>", user.getUserName());
				message.setText(msg);

			} else {
				msg.replace("<name>", "");
				message.setText(template.getEmailBody());

			}

			// Send message
			Transport.send(message);

			System.out.println("Sent message successfully....");
			return "email sent successfully";

		} catch (MessagingException e) {
//			throw new RuntimeException(e);
			return e.getMessage();
		}
	}

}
