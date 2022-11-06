package com.publicissapient.tour.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.tour.service.EmailSenderServiceImpl;

@RestController
@RequestMapping("/notify")
public class EmailSenderController {
	@Autowired
	private EmailSenderServiceImpl service;
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());

	@GetMapping(path="/sendMail/{email}")
	public ResponseEntity<String> sendMail(@PathVariable ("email") String email) {
		service.sendEmail(email);
		logger.info("call for sending email");
		return ResponseEntity.ok("notification sent");
	}
}
