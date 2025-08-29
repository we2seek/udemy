package com.we2seek.demo.currencyexchangeservice.controller;

import com.we2seek.demo.currencyexchangeservice.dto.CurrencyExchange;
import com.we2seek.demo.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private final Logger log = LoggerFactory.getLogger(CurrencyExchangeController.class);

    private final Environment environment;
    private final CurrencyExchangeRepository repository;

    public CurrencyExchangeController(Environment environment, CurrencyExchangeRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        log.info("Request to retrieve exchange value from {} to {}", from, to);
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to);
        if (currencyExchange == null) {
            throw new RuntimeException(String.format("Unable to find data for currencies '%s' and '%s'", from, to));
        }
        currencyExchange.setEnvironment(environment.getProperty("local.server.port"));

        return currencyExchange;
    }
}