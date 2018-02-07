package com.example.demo.security;

import com.asoft.ainstitute.api.constant.Status;
import com.asoft.ainstitute.api.dto.session.UserSessionDetails;
import com.asoft.ainstitute.api.model.user.LoginInfoEntity;
import com.asoft.ainstitute.api.repository.user.LoginInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("userDetailsService")
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

  private static final Logger logger = LoggerFactory.getLogger(UserDetailsService.class);

  @Autowired
  private LoginInfoRepository loginInfoRepository;

  @Override
  @Transactional
  public UserDetails loadUserByUsername(final String username) {
    logger.debug("Authenticating {}", username);
    Optional<LoginInfoEntity> loginInfoEntityOptional = loginInfoRepository.findByUserInfoEntity_UserName(username);
    return loginInfoEntityOptional.map(loginInfoEntity -> {
      UserSessionDetails userSessionDetails = buildUserDetails(loginInfoEntity);
      return userSessionDetails;
    }).orElseThrow(() -> {
      UsernameNotFoundException usernameNotFoundException = new UsernameNotFoundException(
          "User " + username + " was not found in the database");
      return usernameNotFoundException;
    });
  }

  private UserSessionDetails buildUserDetails(LoginInfoEntity loginInfoEntity) {
    UserSessionDetails userSessionDetails = new UserSessionDetails(loginInfoEntity.getUserInfoEntity().getUserName(),
        loginInfoEntity.getPassword(), loginInfoEntity.getStatus() == Status.LoginStatus.ACTIVE, true, true, true,
        new ArrayList<>(), createInstitutePermisionListMap(loginInfoEntity));
    return userSessionDetails;
  }

  private Map<String, List<String>> createInstitutePermisionListMap(LoginInfoEntity loginInfoEntity) {
    Map<String, List<String>> instToPermListMap = new HashMap<>();
    if (loginInfoEntity.getUserInfoEntity().getInstituteMemberEntities() != null) {
      loginInfoEntity.getUserInfoEntity().getInstituteMemberEntities().forEach(instituteMemberEntity -> {
        String instituteCode = instituteMemberEntity.getInstituteEntity().getCode();
        if (!instToPermListMap.containsKey(instituteCode)) {
          instToPermListMap.put(instituteCode, new ArrayList<>());
        }
        instituteMemberEntity.getInstituteMemberRoleEntities().forEach(instituteMemberRoleEntity -> {
          instituteMemberRoleEntity.getRoleEntity().getRolePermissionEntities().forEach(rolePermissionEntity -> {
            instToPermListMap.get(instituteCode).add(rolePermissionEntity.getPermissionEntity().getCode());
          });
        });
      });
    }
    return instToPermListMap;
  }
}
