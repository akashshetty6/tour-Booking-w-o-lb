package com.publicissapient.tour.service;

import org.springframework.stereotype.Service;

import com.publicissapient.tour.entity.Customer;

@Service
public interface AdminService {
	public Customer findUserById(int id);
}
