package com.romanyuta.todoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ToDoProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ToDoProjectApplication.class, args);
	}

}
