package io.weinfurtner.email;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
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
