package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.PayrollComponentType;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/payroll-component-types", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class PayrollComponentTypeRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public PayrollComponentType create(@RequestBody PayrollComponentType payrollComponentType) throws Exception {
    return referenceDataService.create(payrollComponentType);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public PayrollComponentType update(@PathVariable("code") String code,
      @RequestBody PayrollComponentType payrollComponentType) throws Exception {
    if (payrollComponentType == null) {
      throw new ServiceException("PayrollComponentType Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("PayrollComponentType Code not valid.");
    }
    if (!code.equals(payrollComponentType.getCode())) {
      throw new ServiceException("PayrollComponentType Object not valid for Provided Code.");
    }

    return referenceDataService.update(payrollComponentType);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    PayrollComponentType payrollComponentType = new PayrollComponentType();
    payrollComponentType.setCode(code);
    referenceDataService.remove(payrollComponentType);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public PayrollComponentType findById(@PathVariable("code") String code) throws Exception {
    PayrollComponentType payrollComponentTypeQuery = new PayrollComponentType();
    payrollComponentTypeQuery.setCode(code);
    List<PayrollComponentType> countries = referenceDataService.findByExample(payrollComponentTypeQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<PayrollComponentType> findAll() throws Exception {
    return referenceDataService.findAllPayrollComponentTypes();
  }

}
