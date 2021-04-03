package com.rat.squad.storage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

//Implementation of decorator
public class EmailSenderImpl  {

    public void sendMail(String mail, String letter) throws MessagingException {
        String username = "azatazat835@mail.ru";
        String password = "azat1504";
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.mail.ru");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.port", "587");
        String from = "azatazat835@mail.ru";
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress(mail));
        message.setSubject("Calculation result!");
        message.setText(letter);
        Transport.send(message);
    }
}
