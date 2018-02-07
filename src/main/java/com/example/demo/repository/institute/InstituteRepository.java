package com.example.demo.repository.institute;

import com.asoft.ainstitute.api.model.institute.InstituteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituteRepository extends JpaRepository<InstituteEntity, Long> {
  InstituteEntity findByCode(String code);
}
