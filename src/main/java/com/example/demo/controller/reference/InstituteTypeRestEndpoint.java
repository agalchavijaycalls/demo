package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.InstituteType;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/institute-types", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class InstituteTypeRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public InstituteType create(@RequestBody InstituteType instituteType) throws Exception {
    return referenceDataService.create(instituteType);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public InstituteType update(@PathVariable("code") String code, @RequestBody InstituteType instituteType)
      throws Exception {
    if (instituteType == null) {
      throw new ServiceException("InstituteType Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("InstituteType Code not valid.");
    }
    if (!code.equals(instituteType.getCode())) {
      throw new ServiceException("InstituteType Object not valid for Provided Code.");
    }

    return referenceDataService.update(instituteType);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    InstituteType instituteType = new InstituteType();
    instituteType.setCode(code);
    referenceDataService.remove(instituteType);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public InstituteType findById(@PathVariable("code") String code) throws Exception {
    InstituteType instituteTypeQuery = new InstituteType();
    instituteTypeQuery.setCode(code);
    List<InstituteType> countries = referenceDataService.findByExample(instituteTypeQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<InstituteType> findAll() throws Exception {
    return referenceDataService.findAllInstituteTypes();
  }

}