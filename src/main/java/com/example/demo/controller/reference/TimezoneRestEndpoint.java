package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.Timezone;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/timezones", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class TimezoneRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public Timezone create(@RequestBody Timezone timezone) throws Exception {
    return referenceDataService.create(timezone);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public Timezone update(@PathVariable("code") String code, @RequestBody Timezone timezone) throws Exception {
    if (timezone == null) {
      throw new ServiceException("Timezone Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("Timezone Code not valid.");
    }
    if (!code.equals(timezone.getCode())) {
      throw new ServiceException("Timezone Object not valid for Provided Code.");
    }

    return referenceDataService.update(timezone);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    Timezone timezone = new Timezone();
    timezone.setCode(code);
    referenceDataService.remove(timezone);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public Timezone findById(@PathVariable("code") String code) throws Exception {
    Timezone timezoneQuery = new Timezone();
    timezoneQuery.setCode(code);
    List<Timezone> countries = referenceDataService.findByExample(timezoneQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<Timezone> findAll() throws Exception {
    return referenceDataService.findAllTimezones();
  }

}
