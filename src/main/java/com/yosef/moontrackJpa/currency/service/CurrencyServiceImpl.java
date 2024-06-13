package com.yosef.moontrackJpa.currency.service;

import com.yosef.moontrackJpa.currency.entity.Currency;
import com.yosef.moontrackJpa.currency.repository.CurrencyRepository;
import com.yosef.moontrackJpa.exceptions.applicationException.ApplicationException;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Service
@Log
public class CurrencyServiceImpl implements CurrencyService{
  private final CurrencyRepository currencyRepository;

  public CurrencyServiceImpl(CurrencyRepository currencyRepository) {
    this.currencyRepository = currencyRepository;
  }
  @Override
  public List<Currency> getAllCurrencies() {
    return currencyRepository.findAll();
  }

  @Override
  public Currency createCurrency(Currency currency) {
    var result = currencyRepository.findByCurrencyName(currency.getCurrencyName());
    if (!result.isEmpty()) {
      throw new ApplicationException("Currency name already exists");
    }
    currency.setCreatedAt(LocalDateTime.now());
    currency.setUpdatedAt(LocalDateTime.now());
    return currencyRepository.save(currency);
  }

  @Override
  public String deleteCurrency(Long id) {
    currencyRepository.deleteById(id);
    return "Delete Currency success";
  }
}
