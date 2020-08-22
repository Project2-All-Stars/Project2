package com.rev.service;

import org.springframework.http.MediaType;
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

import com.google.gson.Gson;

@Controller
@RequestMapping(path = "/excuses")
public class ExcuseController {
	private Gson gson = new Gson().newBuilder().enableComplexMapKeySerialization().create();
	
	/**
	 * Get all
	 * 
	 * @return
	 */
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> getAllExcuses(){
		
	}
	
	/**
	 * Get by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getExcuseById(@PathVariable int id){
		
	}
	
	/**
	 * Update
	 * 
	 * @param id
	 * @param teacher
	 * @return
	 */
	@PutMapping(path = "{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateExcuse(@PathVariable int id, @RequestBody String excuse){
		
	}
	
	/**
	 * Add
	 * 
	 * @param teacher
	 * @return
	 */
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<String> addExcuse(@RequestBody String excuse){
		
	}
	
	/**
	 * Delete
	 * 
	 * @param id
	 * @return
	 */
	@DeleteMapping(path = "{id}", consumes = MediaType.TEXT_PLAIN_VALUE)
	@ResponseBody
	public ResponseEntity<String> deleteExcuse(@PathVariable int id){

	
	}
}

































































































































































































































































































































































