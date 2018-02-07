package com.example.demo.service.reference;

import java.io.Serializable;
import java.util.List;

public interface ReferenceDataLocalService {

  <E> void create(E entity) throws Exception;

  <E> E update(E entity) throws Exception;

  <E> void remove(Class<E> entityClass, Serializable... keys) throws Exception;

  <E> E findByKey(Class<E> entityClass, Serializable key) throws Exception;

  <E> List<E> findByExample(E entity) throws Exception;

  <E> List<E> findAll(Class<E> e) throws Exception;
}
