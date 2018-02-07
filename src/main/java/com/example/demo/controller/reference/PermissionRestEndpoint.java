package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.Permission;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/permissions", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class PermissionRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public Permission create(@RequestBody Permission permission) throws Exception {
    return referenceDataService.create(permission);
  }

  @PutMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public Permission update(@PathVariable("code") String code, @RequestBody Permission permission) throws Exception {
    if (permission == null) {
      throw new ServiceException("Permission Object not valid.");
    }
    if (code == null) {
      throw new ServiceException("Permission Code not valid.");
    }
    if (!code.equals(permission.getCode())) {
      throw new ServiceException("Permission Object not valid for Provided Code.");
    }

    return referenceDataService.update(permission);
  }

  @DeleteMapping(value = "/{code:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("code") String code) throws Exception {
    Permission permission = new Permission();
    permission.setCode(code);
    referenceDataService.remove(permission);
  }

  @GetMapping("/{code:[a-zA-Z0-9]{2,5}}")
  public Permission findById(@PathVariable("code") String code) throws Exception {
    Permission permissionQuery = new Permission();
    permissionQuery.setCode(code);
    List<Permission> countries = referenceDataService.findByExample(permissionQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<Permission> findAll() throws Exception {
    return referenceDataService.findAllPermissions();
  }

}
