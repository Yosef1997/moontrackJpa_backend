package com.yosef.moontrackJpa.currency;

import com.yosef.moontrackJpa.currency.entity.Currency;
import com.yosef.moontrackJpa.currency.service.CurrencyService;
import com.yosef.moontrackJpa.response.Response;
import lombok.extern.java.Log;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/currency")
@Log
public class CurrencyController {
  private final CurrencyService currencyService;

  public CurrencyController(CurrencyService currencyService) {
    this.currencyService = currencyService;
  }

  @GetMapping("/")
  public ResponseEntity<Response<List<Currency>>> getAllCurrencies() {
    return Response.successResponse("All Currency Fetched", currencyService.getAllCurrencies());
  }

  @PostMapping("/")
  public ResponseEntity<Response<Currency>> createCurrency(@Validated @RequestBody Currency currency) {
    return Response.successResponse("Create Currency Success", currencyService.createCurrency(currency));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Response<Object>> deleteCurrency(@PathVariable("id") Long id) {
   return Response.successResponse("Delete Currency with "+ id + " success", currencyService.deleteCurrency(id));
  }
}
