package com.rev.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.rev.dao.AbsenceRepository;
import com.rev.dao.AbsenceSpringRepository;
import com.rev.dao.GenericRepository;
import com.rev.dao.SpringRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rev.model.Absence;

import java.lang.reflect.Type;
import java.util.List;


@Controller
@RequestMapping("/absences")
public class AbsenceController {
    private Gson gson = new Gson().newBuilder().enableComplexMapKeySerialization().create();
	Type tAbsenceList = new TypeToken<List<Absence>>() {}.getType();
    GenericRepository<Absence> absenceRepository;

	@Autowired
	public void setAbsenceRepository(GenericRepository<Absence> absenceRepository) {
		this.absenceRepository = absenceRepository;
	}
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAllAbsences() {
//        return new ResponseEntity<String>( "This is the response", HttpStatus.OK);
        List<Absence> list = absenceRepository.findAll();
        if (list == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(gson.toJson(list, List.class), HttpStatus.OK);
        }
    }
    
}
