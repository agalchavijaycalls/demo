package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.Country;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/countries", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class CountryRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public Country create(@RequestBody Country country) throws Exception {
    return referenceDataService.create(country);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public Country update(@PathVariable("code") String code, @RequestBody Country country) throws Exception {
    if (country == null) {
      throw new ServiceException("Country Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("Country Code not valid.");
    }
    if (!code.equals(country.getCode())) {
      throw new ServiceException("Country Object not valid for Provided Code.");
    }

    return referenceDataService.update(country);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    Country country = new Country();
    country.setCode(code);
    referenceDataService.remove(country);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public Country findById(@PathVariable("code") String code) throws Exception {
    Country countryQuery = new Country();
    countryQuery.setCode(code);
    List<Country> countries = referenceDataService.findByExample(countryQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<Country> findAll() throws Exception {
    return referenceDataService.findAllCountries();
  }

}
