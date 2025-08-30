package com.we2seek.demo.apigateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

@SpringBootApplication
public class ApiGatewayApplication implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApiGatewayApplication.class);

    @Autowired
    Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // get app version from environment
        log.info("API Gateway Application Version: {}", environment.getProperty("app.version", "1.0.0"));
        // log the port on which the application is running
        log.info("API Gateway is running on port {}", environment.getProperty("server.port"));
    }

}
