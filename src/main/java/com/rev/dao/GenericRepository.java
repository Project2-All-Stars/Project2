package com.rev.dao;

import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface GenericRepository<T> {
    Serializable save(T t);
    List<T> findAll();
    T findById(Serializable id);
    boolean update(T t);
    boolean deleteById(Serializable id);
}
