package com.gp.util;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	public static void send(MessageDTO message)
			throws UnsupportedEncodingException, MessagingException {
		Properties props = new Properties();
		Session session = Session.getDefaultInstance(props, null);

		Message msg = new MimeMessage(session);
		msg.setFrom(new InternetAddress(message.getFromAddress(), message
				.getFromName()));
		msg.addRecipient(
				Message.RecipientType.TO,
				new InternetAddress(message.getToAddress(), message.getToName()));
		msg.setSubject(message.getTitle());
		msg.setText(message.getText());
		Transport.send(msg);

	}

}
