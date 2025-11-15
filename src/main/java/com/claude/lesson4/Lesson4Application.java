package com.claude.lesson4;

import com.claude.lesson4.controllers.DemoController;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class Lesson4Application {

	public static void main(String[] args) {
		SpringApplication.run(Lesson4Application.class, args);
	}
	@Bean
	public CommandLineRunner run(DemoController demoController) {
		return args -> demoController.demonstrateAOP();
	}
}


