package com.rev.dao;

import com.rev.model.AbsenceModel;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbsenceSpringRepository extends SpringRepository<AbsenceModel> implements AbsenceRepository{

    @Autowired
    public AbsenceSpringRepository(SessionFactory sf){
        super(sf);
    }
}
