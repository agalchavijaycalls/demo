package com.example.demo.repository.universal;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

public interface UniversalRepository {
  EntityManager getEntityManager();

  Session getSession();

  <E> void create(E entity);

  <E> E save(E entity);

  <E> List<E> findAll(Class<E> entityClass);

  <E> E findOne(Class<E> entityClass, Serializable primaryKey);

  <E> List<E> findRange(Class<E> entityClass, int[] range);

  <E> List<E> findByExample(E exampleInstance);

  <E> E findReferenceByKey(Class<E> entityClass, Serializable primaryKey);

  <E> long count(Class<E> entityClass);

  <E> void removeByKeys(Class<E> entityClass, Serializable... primaryKeys);

  <E> TypedQuery<E> createNamedQuery(String name, Class<E> entityClass);
}
