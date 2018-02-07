package com.example.demo.repository.user;

import com.asoft.ainstitute.api.model.user.LoginInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoginInfoRepository extends JpaRepository<LoginInfoEntity, Long> {
  Optional<LoginInfoEntity> findByUserInfoEntity_UserName(String userName);
}
