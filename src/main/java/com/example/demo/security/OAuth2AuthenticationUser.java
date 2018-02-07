package com.example.demo.security;

import com.asoft.ainstitute.api.dto.session.UserSessionDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;

public class OAuth2AuthenticationUser extends OAuth2Authentication {

  private UserSessionDetails userSessionDetails;

  public OAuth2AuthenticationUser(OAuth2Request storedRequest, Authentication userAuthentication) {
    super(storedRequest, userAuthentication);
  }

  public UserSessionDetails getUserSessionDetails() {
    return userSessionDetails;
  }

  public void setUserSessionDetails(UserSessionDetails userSessionDetails) {
    this.userSessionDetails = userSessionDetails;
  }
}