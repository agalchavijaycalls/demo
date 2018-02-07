package com.example.demo.security.config;

import com.asoft.ainstitute.api.dto.session.UserSessionDetails;
import com.asoft.ainstitute.api.security.OAuth2AuthenticationUser;
import com.asoft.ainstitute.api.security.SecretKeyProvider;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Configuration
@EnableWebSecurity(debug = true)
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  public static final String INST_PERM_DELIM_LIST = "UserDetails";
  public static final String INST_TO_PERM_DELIMITER = ":";

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(getUserDetailsService()).passwordEncoder(passwordEncoder());
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
        .addFilterBefore(corsFilter(), UsernamePasswordAuthenticationFilter.class)
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf().disable()
        .headers().frameOptions().disable()
        .and()
        .authorizeRequests()
        .antMatchers("/reference/**").permitAll()
        .anyRequest().authenticated();
    // @formatter:on
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
    // @formatter:off
    web
        .ignoring()
        .antMatchers(HttpMethod.OPTIONS)
        .antMatchers("/webjars/**", "/h2-console/**")
        .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.ico", "/**/*.html", "/**/*.css", "/**/*.js");
    // @formatter:on
  }

  @Override
  @Bean
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public CorsFilter corsFilter() {
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    CorsConfiguration config = new CorsConfiguration();
    if (config.getAllowedOrigins() != null && !config.getAllowedOrigins().isEmpty()) {
      source.registerCorsConfiguration("/api/**", config);
    }
    return new CorsFilter(source);
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setUserDetailsService(getUserDetailsService());
    return provider;
  }

  @Bean
  public TokenStore tokenStore() {
    return new JwtTokenStore(accessTokenConverter());
  }

  @Bean
  public SecretKeyProvider keyProvider() {
    return new SecretKeyProvider();
  }

  @Bean
  public JwtAccessTokenConverter accessTokenConverter() {
    JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
    try {
      converter.setSigningKey(keyProvider().getKey());
      converter.setAccessTokenConverter(getAuthenticationAccessTokenConverter());
    } catch (URISyntaxException | KeyStoreException | NoSuchAlgorithmException | IOException |
        UnrecoverableKeyException | CertificateException e) {
      e.printStackTrace();
    }

    return converter;
  }

  private DefaultAccessTokenConverter getAuthenticationAccessTokenConverter() {
    return new DefaultAccessTokenConverter() {
      @Override
      public Map<String, ?> convertAccessToken(OAuth2AccessToken token, OAuth2Authentication authentication) {
        Map<String, Object> response = (Map<String, Object>) super.convertAccessToken(token, authentication);

        if (authentication.getUserAuthentication().getPrincipal() instanceof UserSessionDetails) {
          UserSessionDetails userSessionDetails = (UserSessionDetails) authentication.getUserAuthentication()
              .getPrincipal();
          List<String> instPermStringDelimited = new ArrayList<>();
          if (userSessionDetails.getInstToPermListMap() != null) {
            userSessionDetails.getInstToPermListMap().entrySet().forEach(instPermListMapEntry -> {
              if (instPermListMapEntry.getValue() != null) {
                instPermListMapEntry.getValue().forEach(perm -> {
                  instPermStringDelimited.add(instPermListMapEntry.getKey() + INST_TO_PERM_DELIMITER + perm);
                });
              }
            });
          }
          response.put(INST_PERM_DELIM_LIST, instPermStringDelimited);
        }
        return response;
      }

      @Override
      public OAuth2Authentication extractAuthentication(Map<String, ?> map) {
        OAuth2Authentication oAuth2Authentication = super.extractAuthentication(map);
        OAuth2AuthenticationUser oAuth2AuthenticationUser = new OAuth2AuthenticationUser(
            oAuth2Authentication.getOAuth2Request(), oAuth2Authentication.getUserAuthentication());

        if (map.containsKey(INST_PERM_DELIM_LIST)) {
          UserSessionDetails userSessionDetails = UserSessionDetails.buildFromJsonMap(map.get(INST_PERM_DELIM_LIST));
          oAuth2AuthenticationUser.setUserSessionDetails(userSessionDetails);
        }
        return oAuth2AuthenticationUser;
      }
    };
  }

  @Bean
  @Primary
  public DefaultTokenServices defaultTokenServices() {
    DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
    defaultTokenServices.setTokenStore(tokenStore());
    defaultTokenServices.setSupportRefreshToken(true);
    defaultTokenServices.setTokenEnhancer(accessTokenConverter());
    return defaultTokenServices;
  }

  @Bean
  public org.springframework.security.core.userdetails.UserDetailsService getUserDetailsService() {
    return new com.asoft.ainstitute.api.security.UserDetailsService();
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

}
