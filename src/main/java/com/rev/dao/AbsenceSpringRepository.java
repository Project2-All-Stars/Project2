package com.rev.dao;

import com.rev.model.Absence;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class AbsenceSpringRepository extends SpringRepository<Absence> implements AbsenceRepository{

    @Autowired
    public AbsenceSpringRepository(SessionFactory sf){
        super(sf);
    }
}
