package com.devcomanda.easymeetup;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;

@SpringBootApplication(
		// this resources and mvc settings needed for exception handling
		exclude = ErrorMvcAutoConfiguration.class
)
public class EasyMeetupApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyMeetupApplication.class, args);
	}

}

