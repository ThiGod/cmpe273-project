package edu.sjsu.cmpe.kidsontrack.controller.usermgnt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.sjsu.cmpe.kidsontrack.service.usermgnt.UserMgntService;

@Controller
@RequestMapping("/mainpage")
public class MainpageController {
	 
	@Autowired
	private UserMgntService userMgntService;

	@RequestMapping(method = RequestMethod.GET)
	public String getUserProfile(ModelMap model) {
	    return "mainpage";
	}
}
