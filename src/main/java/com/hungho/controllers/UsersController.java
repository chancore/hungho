package com.hungho.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="/user")
public class UsersController {
	
	
	@RequestMapping(value="/sys_login")
	public String index(){
		
		return "index";
	}
}