package com.example.content_calendar;

import com.example.content_calendar.model.Content;
import com.example.content_calendar.model.Status;
import com.example.content_calendar.model.Type;
import com.example.content_calendar.repository.ContentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(ContentRepository repository) {
		return args -> {
			Content content = new Content(null,
					"Test title",
					"Testing",
					Status.IDEA,
					Type.ARTICLE,
					LocalDateTime.now(),
					null,
					"",
					null);
			repository.save(content);
		};
	}

}
