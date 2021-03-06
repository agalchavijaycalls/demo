package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

  private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationEntryPoint.class);

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException ae)
      throws IOException {

    logger.info("Pre-authenticated entry point called. Rejecting access");
    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied");

  }
}