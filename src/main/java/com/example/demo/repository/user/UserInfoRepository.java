package com.example.demo.repository.user;

import com.asoft.ainstitute.api.model.user.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfoEntity, Long> {
  UserInfoEntity findByUserName(String userName);

  UserInfoEntity findByEmailId(String emailId);

  UserInfoEntity findByMobileNo(String mobileNo);
}
