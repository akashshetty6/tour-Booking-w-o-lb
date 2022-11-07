package com.publicissapient.tour.service;

import org.springframework.stereotype.Service;

import com.publicissapient.tour.dto.CustomerDTO;
import com.publicissapient.tour.entity.Customer;
@Service
public interface CustomerService {
	
	public Customer save(CustomerDTO customerDTO);
	public boolean existsByEmail(String email);
}