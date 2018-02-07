package com.example.demo.repository.universal;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class AbstractUniversalJpaRepository implements UniversalRepository {

  @Override
  public <E> void create(E entity) {
    getEntityManager().persist(entity);
  }

  @Override
  public <E> E save(E entity) {
    return getEntityManager().merge(entity);
  }

  @Override
  public <E> void removeByKeys(Class<E> entityClass, Serializable... primaryKeys) {
    for (Serializable primaryKey : primaryKeys) {
      getEntityManager().remove(getEntityManager().getReference(entityClass, primaryKey));
    }
  }

  @Override
  public <E> E findOne(Class<E> entityClass, Serializable primaryKey) {
    return getEntityManager().find(entityClass, primaryKey);
  }

  @Override
  public <E> List<E> findByExample(E exampleInstance) {
    Criteria hibernateCriteria = getSession().createCriteria(exampleInstance.getClass());
    Example example = Example.create(exampleInstance);
    hibernateCriteria.add(example);
    return hibernateCriteria.list();
  }

  @Override
  public <E> List<E> findRange(Class<E> entityClass, int[] range) {
    CriteriaQuery<E> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    Query query = getEntityManager().createQuery(cq);
    query.setMaxResults(range[1] - range[0]);
    query.setFirstResult(range[0]);
    List<E> resultEntities = query.getResultList();
    return resultEntities;
  }

  @Override
  public <E> List<E> findAll(Class<E> entityClass) {
    CriteriaQuery<E> cq = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
    cq.select(cq.from(entityClass));
    List<E> resultEntities = getEntityManager().createQuery(cq).getResultList();
    return resultEntities;
  }

  @Override
  public <E> E findReferenceByKey(Class<E> entityClass, Serializable primaryKey) {
    return getEntityManager().getReference(entityClass, primaryKey);
  }

  @Override
  public <E> long count(Class<E> entityClass) {
    CriteriaQuery<Long> cq = getEntityManager().getCriteriaBuilder().createQuery(Long.class);
    Root<E> rt = cq.from(entityClass);
    cq.select(getEntityManager().getCriteriaBuilder().count(rt));
    Query query = getEntityManager().createQuery(cq);
    long resultCount = (long) query.getSingleResult();
    return resultCount;
  }

  @Override
  public <E> TypedQuery<E> createNamedQuery(String name, Class<E> entityClass) {
    return getEntityManager().createNamedQuery(name, entityClass);
  }
}