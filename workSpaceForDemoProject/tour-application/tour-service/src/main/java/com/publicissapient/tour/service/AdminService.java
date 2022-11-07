package com.publicissapient.tour.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.publicissapient.tour.dto.PackageDTO;
import com.publicissapient.tour.entity.Booking;
import com.publicissapient.tour.entity.Packages;
import com.publicissapient.tour.exception.PackageExistsException;

@Service
public interface AdminService {
	
	public void create(PackageDTO packageDTO) throws PackageExistsException;
	public List<Packages> listAllPackages();
	public List<Booking> listAllBookings();
	public void updateWholePackage(PackageDTO packageDTO);
	void deletePackage(int code);
	public boolean assignStaff(int bookingId, int staffId) ;
	
	public void delete(int id);
}
