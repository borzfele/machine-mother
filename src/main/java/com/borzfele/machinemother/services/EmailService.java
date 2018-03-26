package com.borzfele.machinemother.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final static Logger logger = LoggerFactory.getLogger(EmailService.class);
    private final JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String MAIL_FROM;

    @Autowired
    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMessage(String email, String name) {
        SimpleMailMessage mail = null;

        try {
            mail = new SimpleMailMessage();
            mail.setFrom(MAIL_FROM);
            mail.setTo(email);
            mail.setSubject("Successful registration");
            mail.setText("Dear " + name + ", \n\nI can't thank you enough for your trust.\n" +
                    "I know, it probably was a hard decision for you, " +
                    "but I still promise my help in regain your social capabilities, " +
                    "confidence and contentment in this world.\n\n" +
                    "Be as bad as you want to be," +
                    "Mom");
            mailSender.send(mail);

        } catch (Exception e) {
            logger.error("wrong email address: " + email);
        }
    }
}
