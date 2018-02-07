package com.example.demo.security.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
  private static final Logger logger = LoggerFactory.getLogger(AuthorizationServerConfig.class);

  @Value("${security.oauth2.resource.id}")
  private String resourceId;

  @Value("${security.oauth2.access_token.validity_period}")
  private int accessTokenValiditySeconds;

  @Value("${security.oauth2.refresh_token.validity_period}")
  private int refreshTokenValiditySeconds;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private DefaultTokenServices tokenServices;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private JwtAccessTokenConverter accessTokenConverter;

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // @formatter:off
    endpoints
        .authenticationManager(this.authenticationManager)
        .tokenServices(tokenServices)
        .tokenStore(tokenStore)
        .accessTokenConverter(accessTokenConverter);
    // @formatter:on
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    // @formatter:off
    oauthServer
        .tokenKeyAccess("isAnonymous() || hasAuthority('ROLE_TRUSTED_CLIENT')")
        .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
    // @formatter:on
  }

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    // @formatter:off
    clients.inMemory()
        .withClient("normal-app")
        .authorizedGrantTypes("authorization_code", "implicit")
        .authorities("ROLE_CLIENT")
        .scopes("read", "write")
        .resourceIds(resourceId)
        .accessTokenValiditySeconds(accessTokenValiditySeconds)
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
        .and()
        .withClient("trusted-app")
        .authorizedGrantTypes("client_credentials", "password", "refresh_token")
        .authorities("ROLE_TRUSTED_CLIENT")
        .scopes("read", "write")
        .resourceIds(resourceId)
        .accessTokenValiditySeconds(accessTokenValiditySeconds)
        .refreshTokenValiditySeconds(refreshTokenValiditySeconds)
        .secret("secret")
        .and()
        .withClient("register-app")
        .authorizedGrantTypes("client_credentials")
        .authorities("ROLE_REGISTER")
        .scopes("read")
        .resourceIds(resourceId)
        .secret("secret")
        .and()
        .withClient("my-client-with-registered-redirect")
        .authorizedGrantTypes("authorization_code")
        .authorities("ROLE_CLIENT")
        .scopes("read", "trust")
        .resourceIds("oauth2-resource")
        .redirectUris("http://anywhere?key=value");
    // @formatter:on
  }

}
