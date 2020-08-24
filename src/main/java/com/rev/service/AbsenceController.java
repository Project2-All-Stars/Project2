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
import org.springframework.web.bind.annotation.*;

import com.rev.model.Absence;

import java.lang.reflect.Type;
import java.util.List;

/**
 *
 */
@Controller
@RequestMapping("/absences")
public class AbsenceController {
    private Gson gson = new Gson().newBuilder().enableComplexMapKeySerialization().create();
//	Type tAbsenceList = new TypeToken<List<Absence>>() {}.getType();
    GenericRepository<Absence> absenceRepository;

	@Autowired
	public void setAbsenceRepository(GenericRepository<Absence> absenceRepository) {
		this.absenceRepository = absenceRepository;
	}

    /**
     *
     * @return
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAllAbsences() {
        List<Absence> list = absenceRepository.findAll();
        if (list == null) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            return new ResponseEntity<>(gson.toJson(list, List.class), HttpStatus.OK);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAbsenceById(@PathVariable int id){
        Absence a = absenceRepository.findById(id);
        return a == null ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
                new ResponseEntity<>(gson.toJson(a, Absence.class), HttpStatus.OK);
    }
//
//    /**
//     *
//     * @param id
//     * @param absence
//     * @return
//     */
//    @PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
//    @ResponseBody
//    public ResponseEntity<String> updateAbsence(@PathVariable int id, @RequestBody String absence){
//        Absence a = absenceRepository.findById(id);
//        if (a == null)
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//
//        a = gson.fromJson(absence, Absence.class);
//        a.setAid(id);
//        return absenceRepository.update(a) ? new ResponseEntity<>(null, HttpStatus.OK) :
//            new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

}
