package com.publicissapient.tour.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.publicissapient.tour.dto.CustomerDTO;
import com.publicissapient.tour.entity.Customer;
import com.publicissapient.tour.exception.CustomerExistsException;
import com.publicissapient.tour.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	CustomerRepository customerRepo;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Override
	public Customer save(CustomerDTO customerDTO) {
		if (customerRepo.existsByEmail(customerDTO.getEmail())) {  
			logger.error("customer exists by this email {}",customerDTO.getEmail());
			throw new CustomerExistsException("Customer exists by this email " + customerDTO.getEmail());
		} else {
			customerDTO.setEnabled(true);
			customerDTO.setPassword(passwordEncoder.encode(customerDTO.getPassword()));

			Customer customer = new Customer(customerDTO.getAge(), customerDTO.getFirstName(),
					customerDTO.getLastName(), customerDTO.getPhoneNo(), customerDTO.getEmail(),
					customerDTO.getAddress(), customerDTO.getGender(), customerDTO.getPassword(),
					customerDTO.isEnabled());
			logger.info("Registration successful");
			return customerRepo.save(customer);
		}
	}
	@Override
	public boolean existsByEmail(String email) {
		logger.info("Exists by mail : {}",customerRepo.existsByEmail(email));
		return customerRepo.existsByEmail(email);
	}


}
