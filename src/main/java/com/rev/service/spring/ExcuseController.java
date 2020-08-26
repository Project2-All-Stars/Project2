package com.rev.service.spring;

import com.rev.model.Excuse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/excuse")
public class ExcuseController extends GenericController<Excuse> {

}
