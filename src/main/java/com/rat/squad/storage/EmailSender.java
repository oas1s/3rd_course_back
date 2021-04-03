package com.rat.squad.storage;

import javax.mail.MessagingException;

//Decorator class
public class EmailSender {
    void sendMail(String mail, String letter) throws MessagingException{
        EmailSenderImpl emailSender = new EmailSenderImpl();
        emailSender.sendMail(mail,letter);
    }
}
