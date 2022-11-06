package com.publicissapient.tour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.publicissapient.tour.dto.BookingDTO;
import com.publicissapient.tour.entity.Booking;
import com.publicissapient.tour.entity.Packages;
@Service
public interface CustomerService {

	public List<Packages> listAllPackages();
	public Booking booking(BookingDTO bookingDTO);
	public void delete(int id);
	public List<Booking> listBookings(String userName);

}