package com.taste.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("")
public class MediatorController {
	
	private static Logger logger = Logger.getLogger(MediatorController.class.getName());
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getHomePage() {
		logger.info("Inside Home Page..");
		return "home";
	}

}
