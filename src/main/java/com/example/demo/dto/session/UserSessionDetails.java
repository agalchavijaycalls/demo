package com.example.demo.dto.session;

import com.asoft.ainstitute.api.security.config.SecurityConfig;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.*;

public class UserSessionDetails extends User {
  private static final long serialVersionUID = 8498233196842987555L;
  private Map<String, List<String>> instToPermListMap;

  public Map<String, List<String>> getInstToPermListMap() {
    return instToPermListMap;
  }

  public UserSessionDetails(String username, String password, Map<String, List<String>> instToPermListMap) {
    super(username, password, new ArrayList<GrantedAuthority>(0));
    this.instToPermListMap = instToPermListMap;
  }

  public UserSessionDetails(String username, String password, boolean enabled, boolean accountNonExpired,
      boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
      Map<String, List<String>> instToPermListMap) {
    super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
    this.instToPermListMap = instToPermListMap;
  }

  public boolean hasPermissionOnInstitute(String permission, String institute) {
    boolean result = false;
    if (MapUtils.isNotEmpty(instToPermListMap)) {
      List<String> permissionsForInstitute = instToPermListMap.get(institute);
      if (CollectionUtils.isNotEmpty(permissionsForInstitute)) {
        if (permissionsForInstitute.contains(permission)) {
          result = true;
        }
      }
    }
    return result;
  }

  public static UserSessionDetails buildFromJsonMap(Object instPermDelimListObj) {
    UserSessionDetails userSessionDetails = null;
    try {
      Map<String, List<String>> instToPermListMap = new HashMap<>();
      List<String> instPermDelimList = (List<String>) instPermDelimListObj;
      if (CollectionUtils.isNotEmpty(instPermDelimList)) {
        instPermDelimList.forEach(instPermDelimStr -> {
          String[] split = instPermDelimStr.split(SecurityConfig.INST_TO_PERM_DELIMITER);
          if (!instToPermListMap.containsKey(split[0])) {
            instToPermListMap.put(split[0], new ArrayList<>());
          }
          instToPermListMap.get(split[0]).add(split[1]);
        });
      }
      userSessionDetails = new UserSessionDetails("permission_only", "", instToPermListMap);
    } catch (Exception exc) {
      exc.printStackTrace();
    }
    return userSessionDetails;
  }
}