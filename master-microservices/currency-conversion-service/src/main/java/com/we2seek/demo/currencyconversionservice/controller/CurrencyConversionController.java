package com.we2seek.demo.currencyconversionservice.controller;

import com.we2seek.demo.currencyconversionservice.dto.CurrencyConversion;
import com.we2seek.demo.currencyconversionservice.dto.CurrencyConversionProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

@RestController
public class CurrencyConversionController {

    private static final Logger log = LoggerFactory.getLogger(CurrencyConversion.class);

    private final RestTemplate restTemplate;
    private final CurrencyConversionProxy currencyConversionProxy;
    private final String url;

    public CurrencyConversionController(
            RestTemplate restTemplate,
            CurrencyConversionProxy currencyConversionProxy,
            @Value("${currency.exchange.service.url}") String baseUrlStr
    ) throws MalformedURLException {
        this.restTemplate = restTemplate;
        this.currencyConversionProxy = currencyConversionProxy;
        this.url = new URL(
                new URL(baseUrlStr),
                "/currency-exchange/from/{from}/to/{to}")
                .toString();
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(
            @PathVariable String from,
            @PathVariable String to,
            @PathVariable BigDecimal quantity
    ) {
        CurrencyConversion c = restTemplate.getForObject(url, CurrencyConversion.class, from, to);

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
