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

    @PostMapping(path = "post", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Serializable> post(@RequestBody T t){
        Serializable id = dao.save(t);
        return new ResponseEntity<Serializable>(id, id!=null ? HttpStatus.CREATED : HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @GetMapping(path = "get/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<T> get(@PathVariable String id){
        T t;
        try{ //try to parse the id as an int
            t = dao.findById(Integer.parseInt(id));
        }
        catch (final NumberFormatException e){
            t = dao.findById(id);
        }
        return new ResponseEntity<T>(t, t!=null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @GetMapping(path = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<T>> getAll(){
        List<T> t = dao.findAll();
        return new ResponseEntity<List<T>>(t, t!=null ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping(path = "put", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> put(@RequestBody T t){
        Boolean success = dao.update(t);
        return new ResponseEntity<>(null, success ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR );
    }

    @DeleteMapping(path = "delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id){
        Boolean success;
        try{ //try to parse the id as an int
            success = dao.deleteById(Integer.parseInt(id));
        }
        catch (final NumberFormatException e){
            success = dao.deleteById(id);
        }
        return new ResponseEntity<>(null, success ? HttpStatus.NO_CONTENT : HttpStatus.INTERNAL_SERVER_ERROR );
    }
}
