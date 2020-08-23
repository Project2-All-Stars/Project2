package com.rev.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/absences")
public class AbsenceController {
    
    @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
    @ResponseBody
    public ResponseEntity<String> getAllAbsences() {
        return new ResponseEntity<String>( "This is the response", HttpStatus.OK);
    }
    
}
