package io.weinfurtner.email.service;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * Created by Jared on 7/10/2016.
 */
@Service
public class MailSenderService {

    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;
    private final MessageSource messageSource;
    private final String from;


    @Autowired
    public MailSenderService(
            JavaMailSender javaMailSender,
            TemplateEngine templateEngine,
            MessageSource messageSource,
            @Value("${application.email.from}") String from)
    {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.messageSource = messageSource;
        this.from = from;
    }

    public void sendMessage(String to, String template, Context context) throws MessagingException {

        String html = templateEngine.process(template, context);

        MimeMessage email = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(email, true);
        mimeMessageHelper.setFrom(from);
        mimeMessageHelper.setTo(to);
        mimeMessageHelper.setSubject(Jsoup.parse(html).title());
        mimeMessageHelper.setText(html, true);

        javaMailSender.send(email);
    }
}
