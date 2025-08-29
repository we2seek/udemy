package com.we2seek.demo.helloworldjava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@SpringBootApplication
public class HelloWorldJavaApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloWorldJavaApplication.class, args);
    }

    @RestController
    public static class MainController {
        @GetMapping
        public Response home() {
            return new Response(LocalDateTime.now(), "Hello from Java");
        }

        public record Response(LocalDateTime timestamp, String data) {
        }
    }
}
