package com.publicissapient.tour.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PACKAGES")
public class Packages {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int code;
	private String packageDetails;
	private int fees;
	private String facilities;
	
	public Packages() {
		
	}

	public Packages(int code, String packageDetails, int fees, String facilities) {
		super();
		this.code = code;
		this.packageDetails = packageDetails;
		this.fees = fees;
		this.facilities = facilities;
	} 
	
	public Packages(String packageDetails, int fees, String facilities) {
		super();
		this.packageDetails = packageDetails;
		this.fees = fees;
		this.facilities = facilities;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code; 
	}

	public String getPackageDetails() {
		return packageDetails;
	}

	public void setPackageDetails(String packageDetails) {
		this.packageDetails = packageDetails;
	}

	public int getFees() {
		return fees;
	}

	public void setFees(int fees) {
		this.fees = fees;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

}
