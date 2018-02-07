package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.CurrencyType;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/currencies", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class CurrencyTypeRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public CurrencyType create(@RequestBody CurrencyType currencyType) throws Exception {
    return referenceDataService.create(currencyType);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public CurrencyType update(@PathVariable("code") String code, @RequestBody CurrencyType currencyType)
      throws Exception {
    if (currencyType == null) {
      throw new ServiceException("CurrencyType Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("CurrencyType Code not valid.");
    }
    if (!code.equals(currencyType.getCode())) {
      throw new ServiceException("CurrencyType Object not valid for Provided Code.");
    }

    return referenceDataService.update(currencyType);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    CurrencyType currencyType = new CurrencyType();
    currencyType.setCode(code);
    referenceDataService.remove(currencyType);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public CurrencyType findById(@PathVariable("code") String code) throws Exception {
    CurrencyType currencyTypeQuery = new CurrencyType();
    currencyTypeQuery.setCode(code);
    List<CurrencyType> countries = referenceDataService.findByExample(currencyTypeQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<CurrencyType> findAll() throws Exception {
    return referenceDataService.findAllCurrencyTypes();
  }
}
