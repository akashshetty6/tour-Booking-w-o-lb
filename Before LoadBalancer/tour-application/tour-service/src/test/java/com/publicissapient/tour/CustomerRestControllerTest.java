package com.publicissapient.tour;

import static com.publicissapient.tour.Constants.ACCEPT;
import static com.publicissapient.tour.Constants.CONTENT_TYPE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.tour.entity.Booking;
import com.publicissapient.tour.entity.Packages;
import com.publicissapient.tour.repository.BookingRepository;
import com.publicissapient.tour.repository.PackageRepository;;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class CustomerRestControllerTest {
	@MockBean
	BookingRepository bookingRepo;

	@MockBean
	PackageRepository packageRepo;

	@Mock
	RestTemplate restTemplate;

	@Autowired
	RestTemplate rest;

	@Autowired
	MockMvc mvc;
	
	@Value("${findByEmail: http://localhost:8087/registration/byEmail/}")
	String findByEmail;

	@Test
	void bookingTest() throws Exception {
		Packages packages = new Packages(44, "AC BUS", 2000, "bang-hyd:3 days");

		Booking booking = new Booking("akae@gmail.com", 44, "2022-10-10", 3, 300.0);

		ObjectMapper mapper = new ObjectMapper();
		String bookingJson = mapper.writeValueAsString(booking);

		HttpHeaders headers = new HttpHeaders();
		headers.set(ACCEPT, "application/json");

		HttpEntity<Void> req = new HttpEntity<>(headers);

		String email = booking.getUserName();

		when(restTemplate.exchange(findByEmail + email, HttpMethod.GET, req,
				Boolean.class)).thenReturn(new ResponseEntity<Boolean>(true, HttpStatus.OK));

		when(packageRepo.existsById(44)).thenReturn(true);

		when(packageRepo.findById(44)).thenReturn(Optional.of(packages));

		when(bookingRepo.save(booking)).thenReturn(new Booking("akae@gmail.com", 44, 3, 300.0));

		mvc.perform(post("/customer/book").content(bookingJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk());

	}

	@Test
	void packageNotFoundExceptionTest() throws Exception {
	

		Booking booking = new Booking("akae@gmail.com", 44, 3, 300.0);

		ObjectMapper mapper = new ObjectMapper();
		String bookingJson = mapper.writeValueAsString(booking);

		HttpHeaders headers = new HttpHeaders();
		headers.set(ACCEPT, "application/json");
		headers.set(CONTENT_TYPE, "application/json");

		HttpEntity<Void> req = new HttpEntity<>(headers);

		String email = booking.getUserName();

		when(restTemplate.exchange(findByEmail + email, HttpMethod.GET, req,
				Boolean.class)).thenReturn(new ResponseEntity<Boolean>(true, HttpStatus.OK));

		when(packageRepo.existsById(44)).thenReturn(false);

		mvc.perform(post("/customer/book").content(bookingJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().is4xxClientError())
				.andExpect(content().string("package not found for the id 44"));
	}

	@Test
	void userNameDoesNotExistsExceptionTest() throws Exception {
		

		Booking booking = new Booking("akaess@gmail.com", 44, 3, 300.0);

		ObjectMapper mapper = new ObjectMapper();
		String bookingJson = mapper.writeValueAsString(booking);

		HttpHeaders headers = new HttpHeaders();
		headers.set(ACCEPT, "application/json");
		headers.set(CONTENT_TYPE, "application/json");
		HttpEntity<Void> req = new HttpEntity<>(headers);

		String email = booking.getUserName();

		when(restTemplate.exchange(findByEmail + email, HttpMethod.GET, req,
				Boolean.class)).thenReturn(new ResponseEntity<Boolean>(false, HttpStatus.OK));


		mvc.perform(post("/customer/book").content(bookingJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().is4xxClientError())
				.andExpect(content().string("user not found :akaess@gmail.com"));
	}

	@Test
	void listAllPackagesTest() throws Exception {
		List<Packages> list = new ArrayList<>();
		list.add(new Packages(1, "AC BUS", 2000, "bang-hyd:3 days"));
		when(packageRepo.findAll()).thenReturn(list);

		mvc.perform(get("/customer/listPackage")).andDo(print()).andExpect(status().isOk()).andExpect(
				content().json("[{'code':1,'facilities':'bang-hyd:3 days','fees':2000,'packageDetails':'AC BUS'}]"));

	}

	@Test
	void deleteTest() throws Exception {

		when(bookingRepo.existsById(1)).thenReturn(true);
		mvc.perform(delete("/customer/delete/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("Booking is deleted"));

		mvc.perform(delete("/customer/delete/2")).andDo(print()).andExpect(status().is4xxClientError());
	}

	@Test
	void listBookingsTest() throws Exception {
		List<Booking> list = new ArrayList<>();
		list.add(new Booking("akash", 1, "2022-10-10", 3, 330.0));
		when(bookingRepo.findAllByUserName("akash")).thenReturn(list);

		mvc.perform(get("/customer/listBookings/akash")).andDo(print()).andExpect(status().isOk()).andExpect(
				content().json("[{'userName':'akash','code':1,'fromDate':'2022-10-10','totalPrice':330.0}]"));

	}

}
