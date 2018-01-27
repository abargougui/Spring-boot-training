package com.abargougui.springboottraining;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringBootTrainingApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringBootTrainingApplication.class, args);
		for (String name : context.getBeanDefinitionNames()) {
			System.out.println("Bean: " + name);
		}
	}
}