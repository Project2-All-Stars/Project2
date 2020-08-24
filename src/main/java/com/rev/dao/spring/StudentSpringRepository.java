package com.rev.dao.spring;

import com.rev.dao.StudentRepository;
import com.rev.model.Student;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class StudentSpringRepository extends SpringRepository<Student> implements StudentRepository {
    @Autowired
    public StudentSpringRepository(SessionFactory sf){
        super(sf);
    }
}
