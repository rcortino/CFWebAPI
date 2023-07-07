package com.skapps.cfweb.dao;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TransactionRequiredException;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class Query<T> {

    final Class<T> tClass;

    private SessionFactory sessionFactory;
    private Session session;
    private CriteriaBuilder criteriaBuilder;
    private CriteriaQuery<T> criteriaQuery;
    private Root<T> root;

    public Query(Class<T> tClass, SessionFactory sessionFactory) {
        this.tClass = tClass;
        this.sessionFactory = sessionFactory;
        session = getSession();
        criteriaBuilder = session.getCriteriaBuilder();
        criteriaQuery = criteriaBuilder.createQuery(tClass);
        root = criteriaQuery.from(tClass);
    }

    public CriteriaBuilder getCriteriaBuilder() {
        return criteriaBuilder;
    }

    public Root<T> getRoot() {
        return root;
    }

    @Transactional
    public List<T> performListQuery(Params params) {
        try {
            org.hibernate.query.Query<T> query = buildQuery(params);
            return query.getResultList();
        } catch (NoResultException e){
            return null;
        }
    }

    @Transactional
    public List<T> performOrderedQuery(Params params, String orderBy){

        if (params != null) {
            List<String> keys = params.getParamKeys();
            Predicate[] predicates = new Predicate[keys.size()];
            for (int i = 0; i < keys.size(); i++) {
                predicates[i] = criteriaBuilder.equal(root.get(keys.get(i)), params.getValue(keys.get(i)));
            }
            criteriaQuery.select(root).where(predicates).orderBy();
        }
        criteriaQuery.orderBy(criteriaBuilder.desc(root.get(orderBy)));
        try {
            org.hibernate.query.Query<T> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public List<T> performPredicateQuery(Predicate[] predicates) {
        try {
            criteriaQuery.select(root).where(predicates);
            org.hibernate.query.Query<T> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public T getSingleResult(Params params) {
        try {
            org.hibernate.query.Query<T> query = buildQuery(params);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void saveEntity(T enitity) {
        try {
            Transaction tx = getTransaction(session);
            session.persist(enitity);
            tx.commit();
        } catch (TransactionRequiredException e) {
            
        }
    }

    @Transactional
    public void delete(T entity) {
        Transaction tx = getTransaction(session);
        session.remove(entity);
        tx.commit();
    }

    private org.hibernate.query.Query<T> buildQuery(Params params) {

        if (params != null) {
            List<String> keys = params.getParamKeys();
            Predicate[] predicates = new Predicate[keys.size()];
            for (int i = 0; i < keys.size(); i++) {
                predicates[i] = criteriaBuilder.equal(root.get(keys.get(i)), params.getValue(keys.get(i)));
            }
            criteriaQuery.select(root).where(predicates);
        }
        return session.createQuery(criteriaQuery);
    }


    private Session getSession() {
        if (sessionFactory.getCurrentSession().isOpen()) {
            Session session = sessionFactory.getCurrentSession();
            if (session.getTransaction().isActive()){
                session.getTransaction();
            } else {
                session.beginTransaction();
            }
            return sessionFactory.getCurrentSession();
        } else {
            sessionFactory.openSession();
            Session session = sessionFactory.getCurrentSession();
            if (session.getTransaction().isActive()){
                session.getTransaction();
            } else {
                session.beginTransaction();
            }
            return sessionFactory.getCurrentSession();
        }
    }

    private Transaction getTransaction(Session session) {
        try {
            Transaction transaction = session.getTransaction();
            if (!transaction.isActive()) {
                transaction.begin();
            }
            return transaction;
        } catch (Exception e) {
            return session.beginTransaction();
        }
    }
}
