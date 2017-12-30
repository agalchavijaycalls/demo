package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.authentication.AbstractAuthenticationTargetUrlRequestHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLogoutSuccessHandler extends AbstractAuthenticationTargetUrlRequestHandler
        implements LogoutSuccessHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomLogoutSuccessHandler.class);

    private static final String BEARER_AUTHENTICATION = "Bearer ";
    private static final String HEADER_AUTHORIZATION = "authorization";

    @Autowired
    private TokenStore tokenStore;

    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication)
            throws IOException, ServletException {

        String token = request.getHeader(HEADER_AUTHORIZATION);

        if (token != null && token.startsWith(BEARER_AUTHENTICATION)) {
            LOGGER.info("Loging out for token : "+token);
            OAuth2AccessToken oAuth2AccessToken = tokenStore.readAccessToken(token.split(" ")[1]);

            if (oAuth2AccessToken != null) {
                tokenStore.removeAccessToken(oAuth2AccessToken);

//                boolean authRemoved = ((DefaultTokenServices) authorizationServerTokenServices).revokeToken(tokenValue); // <- true
//                boolean resourceRemoved = ((DefaultTokenServices) resourceServerTokenServices).revokeToken(tokenValue); // <- true
//                SecurityContextHolder.getContext().setAuthentication(null);
            }

        }

        response.setStatus(HttpServletResponse.SC_OK);

    }

}