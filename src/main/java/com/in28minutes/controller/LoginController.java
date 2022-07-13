package com.in28minutes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.in28minutes.service.LoginService;

//
@Controller
@SessionAttributes("name2")
public class LoginController {

	@Autowired
	LoginService service ;
	//this is to map the /login (url) to this handler method
	@RequestMapping(value="/login",method = RequestMethod.GET)										
	public String loginMessage() {	
		return "login";
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST)										
	public String welcomeMessage(ModelMap model,@RequestParam String name,@RequestParam String pwd) {
		
		boolean isValid =service.validate(name, pwd);
		if(!isValid) {
			model.put("errorMessage", "INVALID CREDENTIALS");
			return "login";
		}
		
		model.put("name2", name);
		model.put("pwd2", pwd);
		
		return "welcome";
	}
}
