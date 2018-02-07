package com.example.demo.service.user.impl;

import com.asoft.ainstitute.api.constant.Constants;
import com.asoft.ainstitute.api.dto.user.InstituteMember;
import com.asoft.ainstitute.api.dto.user.InstituteMemberRole;
import com.asoft.ainstitute.api.dto.user.LoginInfo;
import com.asoft.ainstitute.api.dto.user.UserInfo;
import com.asoft.ainstitute.api.exception.InvalidAttributeException;
import com.asoft.ainstitute.api.exception.ServiceException;
import com.asoft.ainstitute.api.mapper.InstituteEntityDtoMapper;
import com.asoft.ainstitute.api.mapper.UserDataEntityDtoMapper;
import com.asoft.ainstitute.api.model.institute.InstituteEntity;
import com.asoft.ainstitute.api.model.referencedata.RoleEntity;
import com.asoft.ainstitute.api.model.user.InstituteMemberEntity;
import com.asoft.ainstitute.api.model.user.InstituteMemberRoleEntity;
import com.asoft.ainstitute.api.model.user.LoginInfoEntity;
import com.asoft.ainstitute.api.model.user.UserInfoEntity;
import com.asoft.ainstitute.api.repository.institute.InstituteMemberRepository;
import com.asoft.ainstitute.api.repository.institute.InstituteRepository;
import com.asoft.ainstitute.api.repository.universal.UniversalRepository;
import com.asoft.ainstitute.api.repository.user.LoginInfoRepository;
import com.asoft.ainstitute.api.repository.user.UserInfoRepository;
import com.asoft.ainstitute.api.service.user.UserService;
import com.google.common.collect.Lists;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserInfoRepository userInfoRepository;
  @Autowired
  private UserDataEntityDtoMapper userDataEntityDtoMapper;
  @Autowired
  private InstituteRepository instituteRepository;
  @Autowired
  private InstituteMemberRepository instituteMemberRepository;
  @Autowired
  private UniversalRepository universalRepository;
  @Autowired
  private InstituteEntityDtoMapper instituteEntityDtoMapper;
  @Autowired
  private LoginInfoRepository loginInfoRepository;

  @Override
  @Transactional
  public UserInfo register(LoginInfo loginInfo) throws Exception {
    LoginInfoEntity loginInfoEntity = new LoginInfoEntity();
    userDataEntityDtoMapper.mapLoginInfoToEntity(loginInfo, loginInfoEntity);

    UserInfoEntity userInfoEntity = new UserInfoEntity();
    userDataEntityDtoMapper.mapUserInfoToEntity(loginInfo.getUserInfo(), userInfoEntity);

    loginInfoEntity.setUserInfoEntity(userInfoEntity);

    UserInfoEntity existingUserInfoEntity = userInfoRepository
        .findByUserName(loginInfoEntity.getUserInfoEntity().getUserName());
    if (existingUserInfoEntity != null) {
      throw new InvalidAttributeException(Constants.Key.USER_NAME, Constants.ErrorCode.ALREADY_EXIST);
    }

    UserInfoEntity existingUserInfoEntities = userInfoRepository
        .findByEmailId(loginInfoEntity.getUserInfoEntity().getEmailId());
    if (existingUserInfoEntities != null) {
      throw new InvalidAttributeException(Constants.Key.EMAIL_ID, Constants.ErrorCode.ALREADY_EXIST);
    }

    existingUserInfoEntities = userInfoRepository.findByMobileNo(loginInfoEntity.getUserInfoEntity().getMobileNo());
    if (existingUserInfoEntities != null) {
      throw new InvalidAttributeException(Constants.Key.MOBILE_NO, Constants.ErrorCode.ALREADY_EXIST);
    }

    userInfoRepository.save(loginInfoEntity.getUserInfoEntity());
    loginInfoRepository.save(loginInfoEntity);
    return userDataEntityDtoMapper.mapUserInfoToDto(loginInfoEntity.getUserInfoEntity());
  }

  @Override
  @Transactional
  public Optional<UserInfo> findByUserName(String userName) {
    UserInfoEntity userInfoEntity1 = userInfoRepository.findByUserName(userName);
    if (userInfoEntity1 != null) {
      userInfoEntity1.getUserName();
      return Optional.of(userDataEntityDtoMapper.mapUserInfoToDtoWithInstituteMember(userInfoEntity1));
    } else {
      return Optional.of(null);
    }
  }

  @Override
  @Transactional
  public LoginInfo update(LoginInfo loginInfo) throws Exception {
    LoginInfo updateLoginInfo = null;
    Optional<LoginInfoEntity> loginInfoEntityFromDB = loginInfoRepository
        .findByUserInfoEntity_UserName(loginInfo.getUserInfo().getUserName());

    if (loginInfoEntityFromDB.isPresent()) {
      userDataEntityDtoMapper.mapLoginInfoToEntity(loginInfo, loginInfoEntityFromDB.get());
      LoginInfoEntity updateLoginInfoEntity = loginInfoRepository.save(loginInfoEntityFromDB.get());
      updateLoginInfo = userDataEntityDtoMapper.mapLoginInfoToDto(updateLoginInfoEntity);
    } else {
      throw new InvalidAttributeException(Constants.Key.USER_NAME, Constants.ErrorCode.INVALID);
    }
    return updateLoginInfo;
  }

  @Override
  @Transactional
  public UserInfo update(UserInfo userInfo) throws Exception {
    UserInfo updateUserInfo = null;
    UserInfoEntity userInfoEntityFromDB = userInfoRepository.findByUserName(userInfo.getUserName());

    if (userInfoEntityFromDB != null) {
      userDataEntityDtoMapper.mapUserInfoToEntity(userInfo, userInfoEntityFromDB);
      userInfoRepository.save(userInfoEntityFromDB);
    } else {
      throw new InvalidAttributeException(Constants.Key.USER_NAME, Constants.ErrorCode.INVALID);
    }
    return updateUserInfo;
  }

  @Override
  @Transactional
  public InstituteMember register(InstituteMember instituteMember) throws ServiceException {
    InstituteMemberEntity instituteMemberEntity = new InstituteMemberEntity();
    userDataEntityDtoMapper.mapInstituteMemberToEntity(instituteMember, instituteMemberEntity);

    UserInfoEntity userInfoEntityFromDB = userInfoRepository
        .findByUserName(instituteMember.getUserInfo().getUserName());
    if (userInfoEntityFromDB == null) {
      throw new InvalidAttributeException(Constants.Key.USER_NAME, Constants.ErrorCode.INVALID);
    }
    instituteMemberEntity.setUserInfoEntity(userInfoEntityFromDB);

    InstituteEntity instituteEntityFromDb = instituteRepository.findByCode(instituteMember.getInstitute().getCode());
    if (instituteEntityFromDb == null) {
      throw new InvalidAttributeException(Constants.Key.INSTITUTE_CODE, Constants.ErrorCode.INVALID);
    }
    instituteMemberEntity.setInstituteEntity(instituteEntityFromDb);

    if (CollectionUtils.isNotEmpty(instituteMember.getInstituteMemberRoles())) {
      instituteMemberEntity.setInstituteMemberRoleEntities(new ArrayList<>());

      instituteMember.getInstituteMemberRoles().forEach(instituteMemberRole -> {
        InstituteMemberRoleEntity instituteMemberRoleEntity = new InstituteMemberRoleEntity();
        userDataEntityDtoMapper.mapInstituteMemberRoleToEntity(instituteMemberRole, instituteMemberRoleEntity);

        instituteMemberRoleEntity.setRoleEntity(
            universalRepository.findReferenceByKey(RoleEntity.class, instituteMemberRole.getRole().getCode()));
        instituteMemberRoleEntity.setInstituteMemberEntity(instituteMemberEntity);
        instituteMemberEntity.getInstituteMemberRoleEntities().add(instituteMemberRoleEntity);
      });
    }

    if (userInfoEntityFromDB.getInstituteMemberEntities() != null) {
      if (userInfoEntityFromDB.getInstituteMemberEntities().contains(instituteMemberEntity)) {

        int i = userInfoEntityFromDB.getInstituteMemberEntities().indexOf(instituteMemberEntity);
        InstituteMemberEntity instituteMemberEntityFromDB = userInfoEntityFromDB.getInstituteMemberEntities().get(i);

        instituteMemberEntity.getInstituteMemberRoleEntities().forEach(instituteMemberRoleEntity -> {
          instituteMemberRoleEntity.setInstituteMemberEntity(instituteMemberEntityFromDB);
          int i1 = instituteMemberEntityFromDB.getInstituteMemberRoleEntities().indexOf(instituteMemberRoleEntity);
          if (i1 > 0) {
            InstituteMemberRoleEntity instituteMemberRoleEntity1 = instituteMemberEntityFromDB
                .getInstituteMemberRoleEntities().get(i1);
            instituteMemberRoleEntity1.setStatusUpdatedDate(new Date());
            instituteMemberRoleEntity1.setStatus(instituteMemberRoleEntity.getStatus());
          } else {
            instituteMemberEntityFromDB.getInstituteMemberRoleEntities().add(instituteMemberRoleEntity);
          }
        });
        return userDataEntityDtoMapper.mapInstituteMemberToDto(instituteMemberEntityFromDB);
      }
    }

    instituteMemberRepository.save(instituteMemberEntity);

    return userDataEntityDtoMapper.mapInstituteMemberToDto(instituteMemberEntity);

  }

  @Override
  @Transactional
  public InstituteMember update(InstituteMember instituteMember) throws Exception {
    InstituteMember updateInstituteMember = null;
    InstituteMemberEntity instituteMemberEntityToBeUpdated = universalRepository
        .findOne(InstituteMemberEntity.class, instituteMember.getId());
    if (instituteMemberEntityToBeUpdated != null) {
      userDataEntityDtoMapper.mapInstituteMemberToEntity(instituteMember, instituteMemberEntityToBeUpdated);
      updateInstituteMember = userDataEntityDtoMapper
          .mapInstituteMemberToDto(universalRepository.save(instituteMemberEntityToBeUpdated));
    } else {
      throw new InvalidAttributeException(Constants.Key.USER_NAME, Constants.ErrorCode.INVALID);
    }

    return updateInstituteMember;
  }

  @Override
  @Transactional
  public List<InstituteMember> findByInstituteCode(String instituteCode) throws Exception {
    List<InstituteMember> instituteMembers = Lists.newArrayList();
    List<InstituteMemberEntity> instituteMemberEntitiesByCode = instituteMemberRepository
        .findByInstituteEntity_Code(instituteCode);
    if (CollectionUtils.isNotEmpty(instituteMemberEntitiesByCode)) {
      instituteMemberEntitiesByCode.forEach(instituteMemberEntity -> {
        instituteMembers.add(userDataEntityDtoMapper.mapInstituteMemberToDto(instituteMemberEntity));
      });
    }
    return instituteMembers;
  }

  @Override
  public InstituteMemberRole create(InstituteMemberRole instituteMemberRole) throws ServiceException {
    InstituteMemberRoleEntity instituteMemberRoleEntity = new InstituteMemberRoleEntity();
    userDataEntityDtoMapper.mapInstituteMemberRoleToEntity(instituteMemberRole, instituteMemberRoleEntity);
    InstituteMemberEntity instituteMemberEntityFromDB = instituteMemberRepository
        .findOne(instituteMemberRole.getInstituteMember().getId());
    instituteMemberRoleEntity.setInstituteMemberEntity(instituteMemberEntityFromDB);
    RoleEntity roleEntity = universalRepository
        .findReferenceByKey(RoleEntity.class, instituteMemberRole.getRole().getCode());
    instituteMemberRoleEntity.setRoleEntity(roleEntity);
    universalRepository.create(instituteMemberRoleEntity);
    return userDataEntityDtoMapper.mapInstituteMemberRoleToDto(instituteMemberRoleEntity);
  }

  @Override
  public InstituteMemberRole update(InstituteMemberRole instituteMemberRole) throws ServiceException {
    InstituteMemberRoleEntity instituteMemberRoleEntityFromDB = universalRepository
        .findOne(InstituteMemberRoleEntity.class, instituteMemberRole.getId());
    userDataEntityDtoMapper.mapInstituteMemberRoleToEntity(instituteMemberRole, instituteMemberRoleEntityFromDB);
    InstituteMemberRoleEntity updatedInstituteMemberRoleEntity = universalRepository
        .save(instituteMemberRoleEntityFromDB);
    return userDataEntityDtoMapper.mapInstituteMemberRoleToDto(updatedInstituteMemberRoleEntity);
  }

  @Override
  public void remove(InstituteMemberRole instituteMemberRole) throws ServiceException {
    instituteMemberRepository.delete(instituteMemberRole.getId());
  }
}
