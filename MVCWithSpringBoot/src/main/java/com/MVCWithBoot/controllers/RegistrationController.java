package com.MVCWithBoot.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.MVCWithBoot.model.User;
import com.MVCWithBoot.services.UserService;

@Controller
public class RegistrationController {
	
	
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
			  @ModelAttribute("user") User user) {


		userService.register(user);
		return new ModelAndView("Registration");

	}
	
	@RequestMapping(value="/registrationForm" ,method=RequestMethod.GET)
	public ModelAndView showRegistration(HttpServletRequest request, HttpServletResponse response){
		
		 ModelAndView mav = new ModelAndView("Registration");
		    mav.addObject("user", new User());
		    return mav;
	}

}
