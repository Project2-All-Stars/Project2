package com.rev.dao;

import com.rev.model.Absence;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository("absenceRepository")
public class AbsenceSpringRepository extends SpringRepository<Absence> implements AbsenceRepository{

    @Autowired
    public AbsenceSpringRepository(SessionFactory sf){
        super(sf);
    }
}
