package io.weinfurtner.email;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import io.weinfurtner.email.message.ContactMessage;
import io.weinfurtner.email.service.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.Message;
import org.springframework.messaging.SubscribableChannel;

@EnableDiscoveryClient
@SpringBootApplication
@EnableBinding(EmailServiceChannels.class)
public class EmailServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmailServerApplication.class, args);
	}

	@Autowired
	AmazonSimpleEmailService amazonSimpleEmailService(AmazonSimpleEmailService amazonSimpleEmailService)
	{
		amazonSimpleEmailService.setRegion(Region.getRegion(Regions.EU_WEST_1));
		return amazonSimpleEmailService;
	}
}

interface EmailServiceChannels {

	@Input
	SubscribableChannel contact();
}

@MessageEndpoint
class EmailProcessor {

	private final MailSenderService mailSenderService;

	@Autowired
	public EmailProcessor(MailSenderService mailSenderService)
	{
		this.mailSenderService = mailSenderService;
	}

	@ServiceActivator(inputChannel = "contact")
	public void contact(Message<ContactMessage> msg)
	{
		ContactMessage contact = msg.getPayload();
		mailSenderService.sendMessage(
				String.format("New contact from %s",
						contact.getName()),
				String.format("Name: %s \r\nEmail: %s\r\nMessage: %s",
						contact.getName(), contact.getEmail(), contact.getMessage()));
	}



}
