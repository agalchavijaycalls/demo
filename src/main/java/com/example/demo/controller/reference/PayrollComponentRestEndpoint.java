package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.PayrollComponent;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/payroll-components", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class PayrollComponentRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public PayrollComponent create(@RequestBody PayrollComponent payrollComponent) throws Exception {
    return referenceDataService.create(payrollComponent);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public PayrollComponent update(@PathVariable("code") String code, @RequestBody PayrollComponent payrollComponent)
      throws Exception {
    if (payrollComponent == null) {
      throw new ServiceException("PayrollComponent Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("PayrollComponent Code not valid.");
    }
    if (!code.equals(payrollComponent.getCode())) {
      throw new ServiceException("PayrollComponent Object not valid for Provided Code.");
    }

    return referenceDataService.update(payrollComponent);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    PayrollComponent payrollComponent = new PayrollComponent();
    payrollComponent.setCode(code);
    referenceDataService.remove(payrollComponent);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public PayrollComponent findById(@PathVariable("code") String code) throws Exception {
    PayrollComponent payrollComponentQuery = new PayrollComponent();
    payrollComponentQuery.setCode(code);
    List<PayrollComponent> countries = referenceDataService.findByExample(payrollComponentQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<PayrollComponent> findAll() throws Exception {
    return referenceDataService.findAllPayrollComponents();
  }

}