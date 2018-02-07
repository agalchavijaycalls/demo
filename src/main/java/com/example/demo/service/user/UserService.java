package com.example.demo.service.user;

import com.asoft.ainstitute.api.dto.user.InstituteMember;
import com.asoft.ainstitute.api.dto.user.InstituteMemberRole;
import com.asoft.ainstitute.api.dto.user.LoginInfo;
import com.asoft.ainstitute.api.dto.user.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {

  UserInfo register(LoginInfo loginInfo) throws Exception;

  Optional<UserInfo> findByUserName(String userName);

  LoginInfo update(LoginInfo loginInfo) throws Exception;

  UserInfo update(UserInfo userInfo) throws Exception;

  InstituteMember register(InstituteMember instituteMember) throws Exception;

  InstituteMember update(InstituteMember instituteMember) throws Exception;

  List<InstituteMember> findByInstituteCode(String instituteCode) throws Exception;

  InstituteMemberRole create(InstituteMemberRole instituteMemberRole) throws Exception;

  InstituteMemberRole update(InstituteMemberRole instituteMemberRole) throws Exception;

  void remove(InstituteMemberRole instituteMemberRole) throws Exception;

}
