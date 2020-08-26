package com.rev.service.spring;

import com.rev.dao.GenericRepository;
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
 * @param <T> The model to access
 */
@RestController
public abstract class GenericController<T> {

    @Autowired
    private GenericRepository<T> dao;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Serializable> post(@RequestBody T t){
        Serializable id = dao.save(t);
        return new ResponseEntity<Serializable>(id, id!=null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> get(@PathVariable Serializable id){
        T t = dao.findById(id);
        return new ResponseEntity<T>(t, t!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND );
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getAll(){
        List<T> t = dao.findAll();
        return new ResponseEntity<List<T>>(t, t!=null ? HttpStatus.OK : HttpStatus.NOT_FOUND );
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> put(@RequestBody T t){
        Boolean success = dao.update(t);
        return new ResponseEntity<Boolean>(success, success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Serializable id){
        Boolean success = dao.deleteById(id);
        return new ResponseEntity<Boolean>(success, success ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
