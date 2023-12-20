package net.dancier.chatdancer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ChatDancerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChatDancerApplication.class, args);
	}

}
