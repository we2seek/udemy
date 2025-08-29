package com.we2seek.demo.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class LimitsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(LimitsServiceApplication.class, args);
    }

    @RestController
    @RequestMapping("/limits")
    public static class LimitsController {
        @Autowired
        private Configuration configuration;

        @GetMapping
        public Limits home() {
            return new Limits(configuration.getMinimum(), configuration.getMaximum());
        }
    }

    public record Limits(int minimum, int maximum) {
    }

    @Component
    @ConfigurationProperties("limits-service")
    public static class Configuration {
        private int minimum;
        private int maximum;

        public int getMinimum() {
            return minimum;
        }

        public void setMinimum(int minimum) {
            this.minimum = minimum;
        }

        public int getMaximum() {
            return maximum;
        }

        public void setMaximum(int maximum) {
            this.maximum = maximum;
        }
    }
}
