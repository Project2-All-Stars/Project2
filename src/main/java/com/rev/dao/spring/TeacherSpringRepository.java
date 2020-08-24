package com.rev.dao.spring;

import com.rev.dao.TeacherRepository;
import com.rev.model.Teacher;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TeacherSpringRepository extends SpringRepository<Teacher> implements TeacherRepository {
    @Autowired
    public TeacherSpringRepository(SessionFactory sf){
        super(sf);
    }
}
