package com.example.demo.repository.institute;

import com.asoft.ainstitute.api.model.user.InstituteMemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstituteMemberRepository extends JpaRepository<InstituteMemberEntity, Long> {
  List<InstituteMemberEntity> findByInstituteEntity_Code(String code);
}
