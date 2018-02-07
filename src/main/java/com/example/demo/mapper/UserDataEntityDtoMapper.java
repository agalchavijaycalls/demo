package com.example.demo.mapper;

import com.asoft.ainstitute.api.dto.user.InstituteMember;
import com.asoft.ainstitute.api.dto.user.InstituteMemberRole;
import com.asoft.ainstitute.api.dto.user.LoginInfo;
import com.asoft.ainstitute.api.dto.user.UserInfo;
import com.asoft.ainstitute.api.model.user.InstituteMemberEntity;
import com.asoft.ainstitute.api.model.user.InstituteMemberRoleEntity;
import com.asoft.ainstitute.api.model.user.LoginInfoEntity;
import com.asoft.ainstitute.api.model.user.UserInfoEntity;
import org.mapstruct.*;

@Mapper(uses = {ReferenceDataEntityDtoMapper.class, InstituteEntityDtoMapper.class}, componentModel = "spring")
public interface UserDataEntityDtoMapper {
  void mapInstituteMemberToEntity(InstituteMember instituteMember,
      @MappingTarget InstituteMemberEntity instituteMemberEntity);

  void mapInstituteMemberRoleToEntity(InstituteMemberRole instituteMemberRole,
      @MappingTarget InstituteMemberRoleEntity instituteMemberRoleEntity);

  void mapLoginInfoToEntity(LoginInfo loginInfo, @MappingTarget LoginInfoEntity loginInfoEntity);

  void mapUserInfoToEntity(UserInfo UserInfo, @MappingTarget UserInfoEntity userInfoEntity);

  @Mappings({@Mapping(source = "instituteEntity", target = "institute"),
      @Mapping(source = "instituteMemberRoleEntities", target = "instituteMemberRoles")})
  InstituteMember mapInstituteMemberToDto(InstituteMemberEntity instituteMemberEntity);

  @Named("WithUserInfoOnly")
  @Mappings({@Mapping(source = "userInfoEntity", target = "userInfo")})
  InstituteMember mapInstituteMemberToDtoWithUserInfoOnly(InstituteMemberEntity instituteMemberEntity);

  @Mappings({@Mapping(source = "roleEntity", target = "role")})
  InstituteMemberRole mapInstituteMemberRoleToDto(InstituteMemberRoleEntity instituteMemberRoleEntity);

  LoginInfo mapLoginInfoToDto(LoginInfoEntity loginInfoEntity);

  @Mappings({@Mapping(source = "userInfoEntity", target = "userInfo")})
  LoginInfo mapLoginInfoToDtoWithUserInfo(LoginInfoEntity loginInfoEntity);

  UserInfo mapUserInfoToDto(UserInfoEntity UserInfoEntity);

  @Named("WithInstituteMember")
  @Mappings({@Mapping(source = "instituteMemberEntities", target = "instituteMembers")})
  UserInfo mapUserInfoToDtoWithInstituteMember(UserInfoEntity UserInfoEntity);

}
