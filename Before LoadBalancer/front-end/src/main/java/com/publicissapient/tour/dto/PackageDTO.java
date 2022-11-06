package com.publicissapient.tour.dto;

public class PackageDTO {
	private Integer code=0;
	private String packageDetails;
	private Integer fees;
	private String facilities;

	public PackageDTO() {

	}


	public PackageDTO(String packageDetails, Integer fees, String facilities) {
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
