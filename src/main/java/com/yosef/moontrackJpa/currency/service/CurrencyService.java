package com.yosef.moontrackJpa.currency.service;

import com.yosef.moontrackJpa.currency.entity.Currency;

import java.util.List;

public interface CurrencyService {
  List<Currency> getAllCurrencies();

  Currency createCurrency(Currency currency);

  String deleteCurrency(Long id);
}
