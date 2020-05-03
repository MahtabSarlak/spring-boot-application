package com.sample.demo;

import com.sample.demo.controller.HomeController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
@ComponentScan
@EnableAutoConfiguration
public class DemoApplication extends SpringBootServletInitializer {
	private static final Logger logger = LogManager.getLogger(HomeController.class);
	public static void main(String[] args) {
		logger.info("Starting spring boot application");
		SpringApplication.run(DemoApplication.class, args);
	}
}
