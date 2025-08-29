package com.we2seek.demo.currencyconversionservice.controller;

import com.we2seek.demo.currencyconversionservice.dto.CurrencyConversion;
import com.we2seek.demo.currencyconversionservice.dto.CurrencyConversionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
public class CurrencyConversionController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyConversion.class);

    private final RestTemplate restTemplate;
    private final CurrencyConversionProxy currencyConversionProxy;

    public CurrencyConversionController(
            RestTemplate restTemplate,
            CurrencyConversionProxy currencyConversionProxy
    ) {
        this.restTemplate = restTemplate;
        this.currencyConversionProxy = currencyConversionProxy;
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        CurrencyConversion c = restTemplate.getForObject(
                "http://localhost:8765/currency-exchange/from/{from}/to/{to}",
                CurrencyConversion.class,
                from,
                to
        );

        if (c == null) {
            return null;
        }

        return new CurrencyConversion(
                c.getId(),
                c.getFrom(),
                c.getTo(),
                c.getConversionMultiple(),
                quantity,
                quantity.multiply(c.getConversionMultiple()),
                c.getEnvironment()
        );
    }

    @GetMapping("/currency-conversion-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversionFeign(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        CurrencyConversion c = currencyConversionProxy.retrieveExchangeValue(from, to);
        return new CurrencyConversion(
                c.getId(),
                c.getFrom(),
                c.getTo(),
                c.getConversionMultiple(),
                quantity,
                quantity.multiply(c.getConversionMultiple()),
                c.getEnvironment() + " feign"
        );
    }
}
