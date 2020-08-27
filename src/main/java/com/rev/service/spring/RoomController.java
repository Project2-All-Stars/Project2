package com.rev.service.spring;

import com.rev.dao.GenericRepository;
import com.rev.dao.RoomRepository;
import com.rev.model.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @Author Trevor
 */
@RestController
@RequestMapping(path = "/room", method = {RequestMethod.GET})
public class RoomController {

    @Autowired
    private RoomRepository dao;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Serializable> post(@RequestBody Room t){
        Serializable id = dao.save(t);
        return new ResponseEntity<Serializable>(id, id!=null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Room> get(@PathVariable Serializable id){
        Room t = dao.findById(id);
        return new ResponseEntity<Room>(t, t!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Room>> getAll(){
        List<Room> t = dao.findAll();
        return new ResponseEntity<List<Room>>(t, t!=null ? HttpStatus.OK : HttpStatus.I_AM_A_TEAPOT );
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> put(@RequestBody Room t){
        Boolean success = dao.update(t);
        return new ResponseEntity<Boolean>(success, success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Serializable id){
        Boolean success = dao.deleteById(id);
        return new ResponseEntity<Boolean>(success, success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
