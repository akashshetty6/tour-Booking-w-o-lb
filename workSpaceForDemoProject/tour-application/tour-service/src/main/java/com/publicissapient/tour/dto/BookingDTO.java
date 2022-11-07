package com.publicissapient.tour.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingDTO {



	private String userName;

	private Integer code;

	private String fromDate;

	private Integer noOfPeople;

	private double totalPrice;

	public BookingDTO() {
		super();
	}


	public String getUserName() {
		return userName;
	}

	public BookingDTO(String userName, Integer code, String fromDate, Integer noOfPeople, double totalPrice) {
		this.userName = userName;
		this.code = code;
		this.fromDate = fromDate;
		this.noOfPeople = noOfPeople;
		this.totalPrice = totalPrice;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Integer getNoOfPeople() {
		return noOfPeople;
	}

	public void setNoOfPeople(Integer noOfPeople) {
		this.noOfPeople = noOfPeople;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

}
