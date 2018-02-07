package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.PayFrequency;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/pay-frequencies", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class PayFrequencyRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public PayFrequency create(@RequestBody PayFrequency payFrequency) throws Exception {
    return referenceDataService.create(payFrequency);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public PayFrequency update(@PathVariable("code") String code, @RequestBody PayFrequency payFrequency)
      throws Exception {
    if (payFrequency == null) {
      throw new ServiceException("PayFrequency Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("PayFrequency Code not valid.");
    }
    if (!code.equals(payFrequency.getCode())) {
      throw new ServiceException("PayFrequency Object not valid for Provided Code.");
    }

    return referenceDataService.update(payFrequency);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    PayFrequency payFrequency = new PayFrequency();
    payFrequency.setCode(code);
    referenceDataService.remove(payFrequency);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public PayFrequency findById(@PathVariable("code") String code) throws Exception {
    PayFrequency payFrequencyQuery = new PayFrequency();
    payFrequencyQuery.setCode(code);
    List<PayFrequency> countries = referenceDataService.findByExample(payFrequencyQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<PayFrequency> findAll() throws Exception {
    return referenceDataService.findAllPayFrequencies();
  }

}
