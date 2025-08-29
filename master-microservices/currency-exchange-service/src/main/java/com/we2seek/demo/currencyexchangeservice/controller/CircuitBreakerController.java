package com.we2seek.demo.currencyexchangeservice.controller;

//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
//import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

//@RestController
public class CircuitBreakerController {

    private final Logger log = LoggerFactory.getLogger(CircuitBreakerController.class);

    private static final String URL = "http://localhost:8080/some-dummy-api";

    @GetMapping("/sample-api")
//    @Retry(name = "sample-api", fallbackMethod = "hardcodedResponse")
    /*
    @CircuitBreaker(name = "default", fallbackMethod = "hardcodedResponse")
    @RateLimiter(name = "default")
    */
    public String sampleApi() {
        log.info("[sample-api]");
        /*ResponseEntity<String> response = new RestTemplate().getForEntity(URL, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.warn("Bad request to {}: {}", URL, response.getStatusCode());
        }

        return response.getBody();*/
        return "sample-api";
    }

    public String hardcodedResponse(Exception e) {
        return String.format("Remote api is unavailable. Address: %s Exception: %s", URL, e.getMessage());
    }
}
