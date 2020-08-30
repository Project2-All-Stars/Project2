package com.rev.service.spring;

import com.rev.model.Excuse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodType;

@CrossOrigin("*")
@RestController
@RequestMapping(path = "/excuse", method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST, RequestMethod.PUT})
public class ExcuseController extends GenericController<Excuse> {

}
