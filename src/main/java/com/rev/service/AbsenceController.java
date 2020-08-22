package com.rev.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;


import com.google.gson.Gson;
import com.rev.dao.AbsenceRepository;
import com.rev.dao.AbsenceSpringRepository;
import com.rev.model.AbsenceModel;

@Controller
@RequestMapping(path="/absences")
public class AbsenceController {

	private Gson gson = new Gson().newBuilder().enableComplexMapKeySerialization().create();
	private AbsenceSpringRepository absenceRepository;
	
	@Autowired
	public void setAbsenceRepository(AbsenceSpringRepository absenceRepository) {
		this.absenceRepository = absenceRepository;
	}

	/**
	 * Get
	 * 
	 * @return All absences.
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllAbsences(){
		List<AbsenceModel> list = absenceRepository.findAll();
		if (list == null) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<>(gson.toJson(list, List.class), HttpStatus.OK);
		}
	}
	
	/**
	 * Get by ID
	 * 
	 * @param id
	 * @return Absence matching the given ID.
	 */
	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAbsenceById(@PathVariable int id){
		AbsenceModel abs = absenceRepository.findById(id);
		return abs == null ? new ResponseEntity<>(null, HttpStatus.NOT_FOUND) :
			new ResponseEntity<>(gson.toJson(abs, AbsenceModel.class), HttpStatus.OK);
	}
	
	/**
	 * Update the given absence.
	 * 
	 * @param id
	 * @param absence
	 * @return 
	 */
	@PutMapping(path = "{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> updateAbsence(@PathVariable int id, @RequestBody String absence){
		AbsenceModel a = absenceRepository.findById(id);
		if (a == null)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		AbsenceModel abs = gson.fromJson(absence, AbsenceModel.class);
		abs.setAid(id);
		return absenceRepository.update(abs) ? new ResponseEntity<>(null, HttpStatus.OK) :
			new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Post
	 * 
	 * @param absenceObject  JSON string representing an absence object.
	 * @return
	 * @throws URISyntaxException
	 */
	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> addAbsence(@RequestBody String absenceObject) throws URISyntaxException {
		if (absenceObject == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
		AbsenceModel abs = gson.fromJson(absenceObject, AbsenceModel.class);
		AbsenceModel temp = (AbsenceModel)absenceRepository.save(abs);
				
		return temp != null	? new ResponseEntity<>(null, HttpStatus.OK) :
		new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * Delete
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> deleteAbsence(@PathVariable int id){
		AbsenceModel temp = absenceRepository.findById(id);
		if (temp == null)
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		
		return absenceRepository.deleteById(id) ? new ResponseEntity<>(null, HttpStatus.OK) :
			new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
