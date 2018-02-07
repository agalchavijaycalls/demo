package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.Province;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/provinces", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProvinceRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public Province create(@RequestBody Province country) throws Exception {
    return referenceDataService.create(country);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public Province update(@PathVariable("code") String code, @RequestBody Province province) throws Exception {
    if (province == null) {
      throw new ServiceException("Province Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("Province Code not valid.");
    }
    if (!code.equals(province.getCode())) {
      throw new ServiceException("Province Object not valid for Provided Code.");
    }

    return referenceDataService.update(province);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    Province province = new Province();
    province.setCode(code);
    referenceDataService.remove(province);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public Province findById(@PathVariable("code") String code) throws Exception {
    Province countryQuery = new Province();
    countryQuery.setCode(code);
    List<Province> countries = referenceDataService.findByExample(countryQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<Province> findAll() throws Exception {
    return referenceDataService.findAllProvinces();
  }

}
