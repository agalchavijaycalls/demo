package com.example.demo.repository.universal.impl;

import com.asoft.ainstitute.api.repository.universal.AbstractUniversalJpaRepository;
import com.asoft.ainstitute.api.repository.universal.UniversalRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;

@Component
public class PrimaryUniversalJpaRepository extends AbstractUniversalJpaRepository implements UniversalRepository {
  @Autowired
  private EntityManager entityManager;

  public EntityManager getEntityManager() {
    return entityManager;
  }

  public Session getSession() {
    return entityManager.unwrap(Session.class);
  }

}