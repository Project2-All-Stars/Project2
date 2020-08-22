package com.rev.dao;

import java.io.Serializable;
import java.util.List;

public interface GenericRepository<T> {
    Serializable save(T t);
    List<T> findAll();
    T findById(Serializable id);
    boolean update(T t);
    boolean deleteById(Serializable id);
}
