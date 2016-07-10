package io.weinfurtner.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created by Jared on 7/10/2016.
 */
@Service
public class MailSenderService {

    private final MailSender mailSender;

    @Autowired
    public MailSenderService(MailSender mailSender)
    {
        this.mailSender = mailSender;
    }

    public void sendMessage(String subject, String text) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("j.weinfurtner+aws@gmail.com");
        simpleMailMessage.setTo("j.weinfurtner+aws@gmail.com");
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(text);

        this.mailSender.send(simpleMailMessage);

    }
}
