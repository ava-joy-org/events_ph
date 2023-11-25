package com.avaroti.events;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
public class EventsApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventsApplication.class, args);
	}

//	@Scheduled(fixedRate = 60 * 1000)
////	@Scheduled(cron = "0 30 18 * * ?") // Run every day at 6:30 PM
//	public void scheduler(){
//		System.out.println("SCHEDULED");
//		RestTemplate template = new RestTemplate();
////		Events ev = template.getForObject("http://localhost:8082/home", Events.class);
//		template.exchange("http://localhost:8082/home", HttpMethod.GET, null, Events.class);
//	}
}
