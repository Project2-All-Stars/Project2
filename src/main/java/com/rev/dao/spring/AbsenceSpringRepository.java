package com.rev.dao.spring;

import com.rev.dao.AbsenceRepository;
import com.rev.model.Absence;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AbsenceSpringRepository extends SpringRepository<Absence> implements AbsenceRepository {

    @Autowired
    public AbsenceSpringRepository(SessionFactory sf){
        super(sf);
    }
}
