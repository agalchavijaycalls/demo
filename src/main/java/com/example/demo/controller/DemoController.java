package com.example.demo.controller;

import com.asoft.ainstitute.api.security.spring.ContextAwarePolicyEnforcement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DemoController {

  @Autowired
  private ContextAwarePolicyEnforcement policy;

  @RequestMapping("/info")
  public String getInfo() {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    return "Hello " + userName;
  }

  @RequestMapping("/pre/{inst_code}")
  @PreAuthorize("hasPermission(#instCode,'VIEW_REF_DATA')")
  public String helloUser(@PathVariable("inst_code") String instCode) {
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    return "Hello " + userName + " " + instCode;
  }

  @RequestMapping("/cus/{inst_code}")
  public String helloAdmin(@PathVariable("inst_code") String instCode) {
    policy.checkPermission(instCode, "VIEW_REF_DATA");
    String userName = SecurityContextHolder.getContext().getAuthentication().getName();
    return "Hello " + userName + " " + instCode;
  }

}
