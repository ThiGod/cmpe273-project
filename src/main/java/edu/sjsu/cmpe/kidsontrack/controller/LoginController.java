package edu.sjsu.cmpe.kidsontrack.controller;

/**
 * Author: Lei Zhang
 */
import edu.sjsu.cmpe.kidsontrack.domain.UserCredential;
import edu.sjsu.cmpe.kidsontrack.dto.UserDto;
import edu.sjsu.cmpe.kidsontrack.exception.UserManagmentException;
import edu.sjsu.cmpe.kidsontrack.service.UserMgntService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserMgntService userMgntService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserProfile(ModelMap model) {
        return "login";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {
            "application/x-www-form-urlencoded", "application/json", "application/xml" })
    public View authenticateUser(
            @ModelAttribute("userCredential") UserCredential userCredential , ModelMap model) throws UserManagmentException {

        UserDto httpUser =  userMgntService.authenticate(userCredential.getEmail(), userCredential.getPassword());

        if (httpUser.getAuthResult() == 1) {
            //verification pass, go to create adPrefProfile page
            return new RedirectView("/kidsontrack/mainpage");
        } else{
            //verification fail, go to login page again
            return new RedirectView("/kidsontrack/signup");
        }

    }



}