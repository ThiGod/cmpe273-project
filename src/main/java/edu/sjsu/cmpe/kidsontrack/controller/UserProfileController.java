package edu.sjsu.cmpe.kidsontrack.controller;



/**
 * Author: Lei Zhang
 */
import edu.sjsu.cmpe.kidsontrack.domain.User;
import edu.sjsu.cmpe.kidsontrack.service.UserMgntService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.RedirectView;


@Controller
@RequestMapping("/signup")
public class UserProfileController {

    @Autowired
    private UserMgntService securityService;

    @RequestMapping(method = RequestMethod.GET)
    public String getUserProfile(ModelMap model) {
        return "userProfile";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = {
            "application/x-www-form-urlencoded", "application/json", "application/xml" })
    public View saveUser(
            @ModelAttribute("user") User user , ModelMap model)  { try{
         securityService.addUser(user);

        System.out.println("User is created: " + user);

        return new RedirectView("/kidsontrack/login");
    }
    catch(Exception e){
        return null;
    }
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public View deleteUser(@PathVariable String id, ModelMap model) {
        try {
        securityService.deleteUser(id);
        return new RedirectView("/kidsontrack/userProfile");
    }

    catch(Exception e){
        return null;
    }
    }

}