package com.publicissapient.tour.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.publicissapient.tour.entity.Booking;
@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

	@Query("Update  Booking b set b.staffId=:staffId where b.bookingId=:bookingId")
	@Transactional
	@Modifying
	void assignStaff(int bookingId, int staffId);

	List<Booking> findAllByUserName(String userName);


}
