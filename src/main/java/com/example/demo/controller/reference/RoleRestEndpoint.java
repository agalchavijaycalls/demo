package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.Role;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/roles", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class RoleRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public Role create(@RequestBody Role role) throws Exception {
    return referenceDataService.create(role);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public Role update(@PathVariable("code") String code, @RequestBody Role country) throws Exception {
    if (country == null) {
      throw new ServiceException("Role Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("Role Code not valid.");
    }
    if (!code.equals(country.getCode())) {
      throw new ServiceException("Role Object not valid for Provided Code.");
    }

    return referenceDataService.update(country);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    Role country = new Role();
    country.setCode(code);
    referenceDataService.remove(country);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public Role findById(@PathVariable("code") String code) throws Exception {
    Role countryQuery = new Role();
    countryQuery.setCode(code);
    List<Role> countries = referenceDataService.findByExample(countryQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<Role> findAll() throws Exception {
    return referenceDataService.findAllRoles();
  }

}
