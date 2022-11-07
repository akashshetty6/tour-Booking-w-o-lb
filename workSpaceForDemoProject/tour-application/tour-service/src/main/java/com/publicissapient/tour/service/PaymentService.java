package com.publicissapient.tour.service;

import static com.publicissapient.tour.Constants.ACCEPT;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.publicissapient.tour.entity.Booking;
import com.publicissapient.tour.exception.BookingNotFoundException;
import com.publicissapient.tour.repository.BookingRepository;
@Service
public class PaymentService {
	
	@Autowired
	BookingRepository bookingRepo;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Value("${notification:http://notification-service/notify/sendMail/}")
	String notification;
	
	public void updateStatus(int id) {
		Optional<Booking> bookingOp =bookingRepo.findById(id);
		if(bookingOp.isPresent()) {
			bookingOp.get().setPaymentStatus("payment done");
			bookingRepo.save(bookingOp.get());
						
			HttpHeaders header1 = new HttpHeaders();
			header1.set(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
			HttpEntity<Void> req1 = new HttpEntity<>(header1); 
			 
			String email1= bookingOp.get().getUserName();
			restTemplate.exchange(notification+email1,
					HttpMethod.GET, req1,String.class);
		}else {
			throw new BookingNotFoundException("Booking not found with this id");
		} 
	} 
}
