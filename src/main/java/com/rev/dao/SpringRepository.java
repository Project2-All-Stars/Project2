package com.rev.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Repository
@Transactional
abstract class SpringRepository<T> implements GenericRepository<T> {

    protected final static Logger log = Logger.getLogger(SpringRepository.class.getName());
    protected SessionFactory sessionFactory;

    //horrible, no good, bad code using reflections, this wont work if T doesn't have a super class but were working on a DAO so.....
    private Class<T> getType(){
        return ((Class) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]);
    }

    @Autowired
    public SpringRepository(SessionFactory sf){
        sessionFactory = sf;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public Serializable save(T t) {
        Session session = sessionFactory.getCurrentSession();
        return session.save(t);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly=true)
    @Override
    public List<T> findAll() {
        Session session = sessionFactory.getCurrentSession();
        return session.createCriteria(getType()).list();
    }

    @Transactional(isolation = Isolation.READ_COMMITTED, readOnly=true)
    @Override
    public T findById(Serializable id) {
        Session session = sessionFactory.getCurrentSession();
        return (T)session.get(getType(), id);
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean update(T t) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.update(t);
            return true;
        }
        catch (Exception e){
            log.error("Update call failed", e);
        }
        return false;
    }

    @Transactional(isolation = Isolation.READ_COMMITTED)
    @Override
    public boolean deleteById(Serializable id) {
        try {
            Session session = sessionFactory.getCurrentSession();
            session.delete(session.get(getType(), id)); //cant call findById() bc you cant have 2 uncommited sessions i think
            return true;
        }
        catch (Exception e){
            log.error("Delete call failed", e);
        }
        return false;
    }
}
