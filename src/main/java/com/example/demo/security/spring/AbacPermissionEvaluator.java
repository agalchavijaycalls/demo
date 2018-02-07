package com.example.demo.security.spring;

import com.asoft.ainstitute.api.dto.session.UserSessionDetails;
import com.asoft.ainstitute.api.security.OAuth2AuthenticationUser;
import com.asoft.ainstitute.api.security.policy.PolicyEnforcement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class AbacPermissionEvaluator implements PermissionEvaluator {
  private static Logger logger = LoggerFactory.getLogger(AbacPermissionEvaluator.class);

  @Autowired
  PolicyEnforcement policy;

  @Override
  public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
    Map<String, Object> environment = new HashMap<>();
		
		/*
		Object authDetails = authentication.getDetails();
		if(authDetails != null) {
			if(authDetails instanceof WebAuthenticationDetails) {
				environment.put("remoteAddress", ((WebAuthenticationDetails) authDetails).getRemoteAddress());
			}
		}
		*/
    environment.put("time", new Date());
    OAuth2AuthenticationUser oAuth2AuthenticationUser = (OAuth2AuthenticationUser) authentication;
    UserSessionDetails userSessionDetails = (
        oAuth2AuthenticationUser != null && oAuth2AuthenticationUser.getUserSessionDetails() != null
            ? oAuth2AuthenticationUser.getUserSessionDetails() : null);

    logger.debug("hasPersmission({}, {}, {})", userSessionDetails, targetDomainObject, permission);
    return policy.check(userSessionDetails, targetDomainObject, permission, environment);
  }

  @Override
  public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
      Object permission) {
    return false;
  }

}
