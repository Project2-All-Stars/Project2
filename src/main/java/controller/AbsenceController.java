package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AbsenceService;

@Controller("AbsenceControllerBean")
@RequestMapping(path="/absence")
@ResponseBody
public class AbsenceController {


}
