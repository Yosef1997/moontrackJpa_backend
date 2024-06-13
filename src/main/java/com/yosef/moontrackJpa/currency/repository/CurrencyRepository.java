package com.yosef.moontrackJpa.currency.repository;

import com.yosef.moontrackJpa.currency.entity.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency, Long> {
  List<Currency> findByCurrencyName(String currencyName);
}
