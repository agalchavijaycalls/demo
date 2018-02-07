package com.example.demo.service.reference.impl;

import com.asoft.ainstitute.api.repository.universal.UniversalRepository;
import com.asoft.ainstitute.api.service.reference.ReferenceDataLocalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

@Service
public class ReferenceDataLocalServiceImpl implements ReferenceDataLocalService {

  @Autowired
  private UniversalRepository universalRepository;

  @Override
  public <E> void create(E entity) throws Exception {
    universalRepository.create(entity);
  }

  @Override
  public <E> E update(E entity) throws Exception {
    return universalRepository.save(entity);
  }

  @Override
  public <E> void remove(Class<E> entityClass, Serializable... keys) throws Exception {
    universalRepository.removeByKeys(entityClass, keys);
  }

  @Override
  public <E> E findByKey(Class<E> entityClass, Serializable key) throws Exception {
    return universalRepository.findOne(entityClass, key);
  }

  @Override
  public <E> List<E> findByExample(E entity) throws Exception {
    List<E> result = universalRepository.findByExample(entity);
    return result;
  }

  @Override
  public <E> List<E> findAll(Class<E> e) throws Exception {
    List<E> result = universalRepository.findAll(e);
    return result;
  }

}
