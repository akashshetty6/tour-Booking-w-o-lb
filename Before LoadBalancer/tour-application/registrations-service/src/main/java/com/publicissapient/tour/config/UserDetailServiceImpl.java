package com.publicissapient.tour.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.publicissapient.tour.entity.Customer;
import com.publicissapient.tour.repository.CustomerRepository;

public class UserDetailServiceImpl implements UserDetailsService{

	@Autowired
	CustomerRepository customerRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer=customerRepo.getCustomerbyUserName(username);
		if(customer==null) {
			throw new UsernameNotFoundException("could not found username");
		}

		return new CustomUserDetails(customer);
	} 
}
