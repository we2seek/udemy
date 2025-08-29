package com.we2seek.demo.currencyexchangeservice.repository;

import com.we2seek.demo.currencyexchangeservice.dto.CurrencyExchange;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Long> {
    CurrencyExchange findByFromAndTo(String from, String to);
}
