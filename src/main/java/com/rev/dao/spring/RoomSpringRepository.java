package com.rev.dao.spring;

import com.rev.dao.RoomRepository;
import com.rev.model.Room;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoomSpringRepository extends SpringRepository<Room> implements RoomRepository {
    @Autowired
    public RoomSpringRepository(SessionFactory sf){
        super(sf);
    }
}
