package com.in28minutes.service;

import org.springframework.stereotype.Component;

@Component
public class LoginService {

	public boolean validate(String name,String pwd) {
		return name.equalsIgnoreCase("Gosu") && pwd.equalsIgnoreCase("dummy");
	}
}
