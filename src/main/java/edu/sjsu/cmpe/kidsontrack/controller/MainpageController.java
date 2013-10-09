package edu.sjsu.cmpe.kidsontrack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import edu.sjsu.cmpe.kidsontrack.dao.UserMgntDao;


@Controller
@RequestMapping("/mainpage")
public class MainpageController {
	 
	@Autowired
	private UserMgntDao userMgntService;

	@RequestMapping(method = RequestMethod.GET)
	public String getUserProfile(ModelMap model) {
	    return "mainpage";
	}
}
