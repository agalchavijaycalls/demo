package com.example.demo.controller.reference;

import com.asoft.ainstitute.api.dto.referencedata.RolePermission;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.service.reference.ReferenceDataService;
import com.google.common.collect.Iterables;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/reference/role-permissions", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
public class RolePermissionRestEndpoint {

  @Autowired
  private ReferenceDataService referenceDataService;

  @PostMapping
  public RolePermission create(@RequestBody RolePermission rolePermission) throws Exception {
    return referenceDataService.create(rolePermission);
  }

  @PutMapping(value = "/{id:[a-zA-Z0-9]{2,5}}")
  public RolePermission update(@PathVariable("id") Long id, @RequestBody RolePermission rolePermission)
      throws Exception {
    if (rolePermission == null) {
      throw new ServiceException("RolePermission Object not valid.");
    }
    if (id == null) {
      throw new ServiceException("RolePermission Code not valid.");
    }
    if (!id.equals(rolePermission.getId())) {
      throw new ServiceException("RolePermission Object not valid for Provided Code.");
    }

    return referenceDataService.update(rolePermission);
  }

  @DeleteMapping(value = "/{id:[a-zA-Z0-9]{2,5}}")
  public void removeById(@PathVariable("id") Long id) throws Exception {
    RolePermission rolePermission = new RolePermission();
    rolePermission.setId(id);
    referenceDataService.remove(rolePermission);
  }

  @GetMapping("/{id:[a-zA-Z0-9]{2,5}}")
  public RolePermission findById(@PathVariable("id") Long id) throws Exception {
    RolePermission rolePermissionQuery = new RolePermission();
    rolePermissionQuery.setId(id);
    List<RolePermission> countries = referenceDataService.findByExample(rolePermissionQuery);
    return Iterables.getFirst(countries, null);
  }

  @GetMapping
  public List<RolePermission> findAll() throws Exception {
    return referenceDataService.findAllRolePermissions();
  }

}