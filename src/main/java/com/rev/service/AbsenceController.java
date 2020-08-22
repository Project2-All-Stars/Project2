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

import org.springframework.http.MediaType;


import com.google.gson.Gson;

@Controller
@RequestMapping(path="/absences")
public class AbsenceController {

	private Gson gson = new Gson().newBuilder().enableComplexMapKeySerialization().create();

	/**
	 * Get
	 * 
	 * @return All absences.
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllAbsences(){
		
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
		
	}
}
