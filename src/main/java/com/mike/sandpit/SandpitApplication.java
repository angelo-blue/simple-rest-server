package com.mike.sandpit;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SandpitApplication {
	private static final Logger logger = LogManager.getLogger(SandpitApplication.class);

	public static void main(String[] args) {
		logger.debug("Starting app");
		SpringApplication.run(SandpitApplication.class, args);
	}

	/**
	 * Register the camel servlet for all urls starting rest/*
	 * @return
	 */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new CamelHttpTransportServlet(), "/rest/*");
        registration.setName("CamelServlet");
        return registration;
    }
}
