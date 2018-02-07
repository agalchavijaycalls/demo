package com.example.demo.security.config;

import com.asoft.ainstitute.api.security.spring.AbacPermissionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@EnableGlobalMethodSecurity(prePostEnabled = true, jsr250Enabled = true, proxyTargetClass = true)
public class MethodSecurityConfig extends GlobalMethodSecurityConfiguration {
  @Autowired
  private AbacPermissionEvaluator permissionEvaluator;

  @Override
  protected MethodSecurityExpressionHandler createExpressionHandler() {
    DefaultMethodSecurityExpressionHandler result = new DefaultMethodSecurityExpressionHandler();
    result.setPermissionEvaluator(permissionEvaluator);
    return result;
  }

}