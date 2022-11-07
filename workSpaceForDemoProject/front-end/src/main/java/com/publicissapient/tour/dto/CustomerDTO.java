package com.publicissapient.tour.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class CustomerDTO {

	private Integer userId;
	private Integer age;
	private String firstName;
	private String lastName;
	private String phoneNo;
	private String email;
	private String address;
	private String gender;
	private String password;
	public boolean enabled;
	
	public CustomerDTO() {
	
	}


	public CustomerDTO(Integer age, String firstName, String lastName, String phoneNo, String email,
			String address, String gender, String password, boolean enabled) {
		super();
		this.age = age;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNo = phoneNo;
		this.email = email;
		this.address = address;
		this.gender = gender;
		this.password = password;
		this.enabled = enabled;
	}


}