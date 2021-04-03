package com.rat.squad.storage;

import javax.mail.MessagingException;
import java.util.Arrays;
import java.util.List;
public class Calculator {
    private String operation;
    private String arg1;
    private String arg2;

    public Calculator(String sentence) {
        defineOperation(sentence);
    }

    // Factory
    public Double calculateSentence() throws MessagingException {
        Double arg1 = Double.parseDouble(this.arg1);
        Double arg2 = Double.parseDouble(this.arg2);
        Double res = 0.0;
        String mail = "alena_alena2000@mail.ru";
        switch (this.operation) {
            case "ADD":
                res = arg1 + arg2;
                getEmailSender().sendMail(mail,res.toString());
                return res;
            case "SUB":
                res = arg1 - arg2;
                getEmailSender().sendMail(mail,res.toString());
                return res;
            case "MULTI":
                res = arg1*arg2;
                getEmailSender().sendMail(mail,res.toString());
                return res;
            case "DIV":
                res = arg1/arg2;
                getEmailSender().sendMail(mail,res.toString());
                return res;
            default:
                throw new IllegalArgumentException("Unknown operation");
        }
    }

    // Strategy
    private void defineOperation(String sentence){
        List<String> sentences = Arrays.asList(sentence.split(" "));
        String operation = sentences.get(1);
        this.arg1 = sentences.get(0);
        this.arg2 = sentences.get(2);
        switch (operation){
            case "+":
                this.operation = "ADD";
                break;
            case "-":
                this.operation = "SUB";
                break;
            case "*":
                this.operation = "MULTI";
                break;
            case "/":
                this.operation = "DIV";
                break;
            default:
                throw new IllegalArgumentException("This operation not implemented yet");
        }
    }

    //Singleton
    private static class EmailSenderCreator {
        private EmailSender emailSender;

        private EmailSender getEmailSenderInstance(){
            if (this.emailSender == null) {
                this.emailSender = new EmailSender();
                return this.emailSender;
            } else {
                return this.emailSender;
            }
        }
    }

    private EmailSender getEmailSender() {
        EmailSenderCreator emailSenderCreator = new EmailSenderCreator();
        return emailSenderCreator.getEmailSenderInstance();
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }
}