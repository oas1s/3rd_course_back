package com.rat.squad.storage;

import javax.mail.MessagingException;

public class MainRunner {
    public static void main(String[] args) throws MessagingException {
        Calculator calculator = new Calculator("1 + 1");
        System.out.println(calculator.calculateSentence());
    }
}
