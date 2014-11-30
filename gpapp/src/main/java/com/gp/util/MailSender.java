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

	public static void send() throws UnsupportedEncodingException, MessagingException{
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);

        String msgBody = "hello this is the message body";

      //  try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("noreply@gp.com", "gp.com Admin"));
            msg.addRecipient(Message.RecipientType.TO,
                             new InternetAddress("jtraviesor@gmail.com", "Mr. User"));
            msg.setSubject("Your Example.com account has been activated");
            msg.setText(msgBody);
            Transport.send(msg);

   //     } catch (AddressException e) {
    //        e.printStackTrace();
    //    } catch (MessagingException e) {
    //        e.printStackTrace();
    //    }
	}
	
	
	public static void main(String[] args) throws UnsupportedEncodingException, MessagingException{
		MailSender.send();
	}
	
	
}
