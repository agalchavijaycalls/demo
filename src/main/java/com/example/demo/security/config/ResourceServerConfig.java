package com.example.demo.security.config;

import com.asoft.ainstitute.api.security.CustomAuthenticationEntryPoint;
import com.asoft.ainstitute.api.security.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  @Value("${security.oauth2.resource.id}")
  private String resourceId;

  @Autowired
  private DefaultTokenServices tokenServices;

  @Autowired
  private TokenStore tokenStore;

  @Autowired
  private CustomAuthenticationEntryPoint customAuthenticationEntryPoint;

  @Autowired
  private CustomLogoutSuccessHandler customLogoutSuccessHandler;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) {
    resources.resourceId(resourceId).tokenServices(tokenServices).tokenStore(tokenStore);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    //@formatter:off
    http
        .exceptionHandling().authenticationEntryPoint(customAuthenticationEntryPoint)
        .and()
        .logout().logoutUrl("/oauth/logout").logoutSuccessHandler(customLogoutSuccessHandler)
        .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .requestMatcher(new OAuthRequestedMatcher()).anonymous().disable()
        .authorizeRequests()
        .antMatchers(HttpMethod.OPTIONS).permitAll()
        .anyRequest().authenticated();
    //@formatter:on
  }

  private static class OAuthRequestedMatcher implements RequestMatcher {
    public boolean matches(HttpServletRequest request) {
      String auth = request.getHeader("Authorization");
      // Determine if the client request contained an OAuth Authorization
      boolean haveOauth2Token = (auth != null) && auth.startsWith("Bearer");
      boolean haveAccessToken = request.getParameter("access_token") != null;
      return haveOauth2Token || haveAccessToken;
    }
  }

}
