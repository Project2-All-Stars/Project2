package com.rev.dao.spring;

import com.rev.dao.ExcuseRepository;
import com.rev.model.Excuse;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ExcuseSpringRepository extends SpringRepository<Excuse> implements ExcuseRepository {
    @Autowired
    public ExcuseSpringRepository(SessionFactory sf){
        super(sf);
    }
}
