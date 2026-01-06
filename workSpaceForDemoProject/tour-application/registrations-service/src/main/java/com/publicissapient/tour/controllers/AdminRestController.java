package com.publicissapient.tour.controllers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.publicissapient.tour.entity.Customer;
import com.publicissapient.tour.service.AdminServiceImpl;

import io.micrometer.core.annotation.Timed;

@RestController
@RequestMapping("/adminRegistration")
public class AdminRestController {
	
	@Autowired
	AdminServiceImpl adminServiceImpl;
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@GetMapping(path = "/find/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value="requests.count.findById")
	public ResponseEntity<Customer> findById(@PathVariable("customerId") int id) {
		logger.info("call for findUserById");
		return ResponseEntity.ok(adminServiceImpl.findUserById(id));
	}

	@GetMapping(path = "/find/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value="requests.count.findById")
	public ResponseEntity<Customer> findById(@PathVariable("customerId") int id) {
		logger.info("call for find User ById");
		return ResponseEntity.ok(adminServiceImpl.findUserById(id));
	}

	@GetMapping(path = "/find/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value="requests.count.findById")
	public ResponseEntity<Customer> findById(@PathVariable("customerId") int id) {
		logger.info("call for findUserById");
		logger.info("call for findUserById");
		return ResponseEntity.ok(adminServiceImpl.findUserById(id));
	}

	@GetMapping(path = "/find/{customerId}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed(value="requests.count.findById")
	public ResponseEntity<Customer> findById(@PathVariable("customerId") int id) {
		logger.info("call for findUserById");
		return ResponseEntity.ok(adminServiceImpl.findUserById(id));
	}
 
}
  