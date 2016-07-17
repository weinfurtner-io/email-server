package io.weinfurtner.email.subscriber;

import io.weinfurtner.email.event.ContactEvent;
import io.weinfurtner.email.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;

import javax.mail.MessagingException;

/**
 * Created by Jared on 7/17/2016.
 */
@MessageEndpoint
public class ContactSubscriber {

    private final MailSenderService mailSenderService;

    @Autowired
    public ContactSubscriber(
            MailSenderService mailSenderService)
    {
        this.mailSenderService = mailSenderService;
    }

    @ServiceActivator(inputChannel = "contact")
    public void contact(Message<ContactEvent> msg) throws MessagingException {

        mailSenderService.sendMessage(
                "j.weinfurtner+aws@gmail.com",
                "contact",
                msg.getPayload());
    }
}
