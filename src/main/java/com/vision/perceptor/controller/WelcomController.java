package com.vision.perceptor.controller;

import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomController {

	@RequestMapping("/")
	public String root() {
		StringBuffer message = new StringBuffer();
		message.append("Server is running ").append(new Date().toString());
		
		return message.toString();
	}
}
