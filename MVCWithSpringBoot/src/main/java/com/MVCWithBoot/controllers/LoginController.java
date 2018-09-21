package com.MVCWithBoot.controllers;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.MVCWithBoot.exceptions.UserNotFoundException;
import com.MVCWithBoot.model.Login;
import com.MVCWithBoot.model.User;
import com.MVCWithBoot.services.UserService;

@Controller
public class LoginController {
	
	
	@Autowired
	UserService userService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView showLogin() {
		ModelAndView mav = new ModelAndView("Login");
		mav.addObject("login", new Login());
		return mav;
	}
	@RequestMapping(value = "/loginProcess", method = RequestMethod.POST)
	  public ModelAndView loginProcess(HttpServletRequest request, HttpServletResponse response,
	  @ModelAttribute("login") Login login) {
	    ModelAndView mav = null;
	    User user = userService.validateUser(login.getUserName(),login.getPassword());
	    if (null != user) {
	    mav = new ModelAndView("welcome");
	    mav.addObject("firstName", user.getFirst_name());
	    } else {
	    mav = new ModelAndView("login");
	    mav.addObject("message", "Username or Password is wrong!!");
	    }
	    return mav;
	  }
	
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.GET)
	public String getUser(@PathVariable("id")int id,Model model) throws Exception {
		
		if(id==1){
			throw new UserNotFoundException(id);
		}
		else if(id==2){
			throw new SQLException("SQLException,id="+id);
		}
		else if(id==10){
			User user = new User();
			user.setUserName("Pankaj");
			user.setUserId(id);
			model.addAttribute("user",user);
			return "welcome";
		}
		else {
			throw new Exception("Generic Exception,id="+id);
		}
		
		
	}
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ModelAndView handleUserNotFoundException(HttpServletRequest request,Exception ex){
		System.out.println("Reuested URL:"+request.getRequestURL());
		System.out.println("Exception Raised:"+ex);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("exception",ex);
		modelAndView.addObject("url",request.getRequestURL());
		
		modelAndView.setViewName("error");
		return modelAndView;
	}

	
	
	

}
