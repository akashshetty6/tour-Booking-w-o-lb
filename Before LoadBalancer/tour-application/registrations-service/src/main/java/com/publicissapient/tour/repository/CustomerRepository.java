package com.publicissapient.tour.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.publicissapient.tour.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

	boolean existsByEmail(String email);
	
	@Query("select c from Customer c where c.email=:email")
	public Customer getCustomerbyUserName(@Param("email") String email);

	Boolean existsByFirstName(String firstName);	
}
