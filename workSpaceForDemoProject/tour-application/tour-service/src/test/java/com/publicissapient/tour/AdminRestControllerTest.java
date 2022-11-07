package com.publicissapient.tour;

import static com.publicissapient.tour.Constants.ACCEPT;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicissapient.tour.dto.CustomerDTO;
import com.publicissapient.tour.entity.Booking;
import com.publicissapient.tour.entity.Packages;
import com.publicissapient.tour.repository.BookingRepository;
import com.publicissapient.tour.repository.PackageRepository;
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class AdminRestControllerTest {

	@LocalServerPort
	Integer port;

	// for accessing web services
	@Autowired
	TestRestTemplate template;

	@MockBean
	PackageRepository packageRepo;

	@MockBean
	BookingRepository bookingRepo;
	
	@Mock
	RestTemplate rest;
	
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	MockMvc mvc;
	
	@Value("${registration:http://localhost:8087/adminRegistration/find/}")
	String registration;

	@Test
	void createTest() throws Exception {  
		Packages pack = new Packages(1,"bang-hyd:3 days", 2000,"AC BUS" );
		ObjectMapper mapper = new ObjectMapper();
		String empJson = mapper.writeValueAsString(pack);

		when(packageRepo.existsByPackageDetails("bang-hyd:3 days")).thenReturn(false); 

		mvc.perform(post("/admin/create").content(empJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("packages created"));

		
		when(packageRepo.existsByPackageDetails("bang-hyd:3 days")).thenReturn(true);
		mvc.perform(post("/admin/create").content(empJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isNotFound());
				
	}


	@Test
	void listAllPackages1Test() throws Exception {
		List<Packages> list = new ArrayList<>();
		list.add(new Packages(1, "AC BUS", 2000, "bang-hyd:3 days"));
		when(packageRepo.findAll()).thenReturn(list);

		mvc.perform(get("/admin/listPackage")).andDo(print()).andExpect(status().isOk()).andExpect(
				content().json("[{'code':1,'facilities':'bang-hyd:3 days','fees':2000,'packageDetails':'AC BUS'}]"));
 
	}
	
	@Test
	void listAllBookingsTest() throws Exception {
		List<Booking> list = new ArrayList<>();
		list.add(new Booking(1,"akash", 10, "2022-10-20",3,60000.0,"pending",2));
		when(bookingRepo.findAll()).thenReturn(list);

		mvc.perform(get("/admin/listBooking")).andDo(print()).andExpect(status().isOk()).andExpect(
				content().json("[{'bookingId':1,'userName':'akash','code':10,'fromDate':'2022-10-20','noOfPeople':3,'totalPrice':60000.0,'paymentStatus':'pending','staffId':2}]"));
 
	}

	@Test
	 void updatePackageTest() throws Exception {
		Packages pack = new Packages(1,"bang-hyd:3 days", 2000,"AC BUS" );
		ObjectMapper mapper = new ObjectMapper();
		String empJson = mapper.writeValueAsString(pack);

		when(packageRepo.existsById(1)).thenReturn(true);

		mvc.perform(put("/admin/updatePackage").content(empJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("Package updated successfully"));

		when(packageRepo.existsById(1)).thenReturn(false);
		mvc.perform(put("/admin/updatePackage").content(empJson).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andDo(print()).andExpect(status().is4xxClientError())
				.andExpect(content().string("Package not found"));

	}

	@Test
	 void deletePackageTest() throws Exception {
		when(packageRepo.existsById(1)).thenReturn(true);

		mvc.perform(delete("/admin/deletePackage/1")).andDo(print()).andExpect(status().isOk());
				
		when(packageRepo.existsById(1)).thenReturn(false);

		mvc.perform(delete("/admin/deletePackage/1")).andDo(print()).andExpect(status().isNotFound());
				
	}
	@Test
	void deleteTest() throws Exception {
		when(bookingRepo.existsById(1)).thenReturn(true);
//		when(bookingRepo.deleteById(1)).thenReturn(true);

		mvc.perform(delete("/admin/delete/1")).andDo(print()).andExpect(status().isOk())
		.andExpect(jsonPath("$.status").value("Booking is deleted"));
				
		when(bookingRepo.existsById(1)).thenReturn(false);

		mvc.perform(delete("/admin/delete/1")).andDo(print()).andExpect(status().is4xxClientError());
//		.andExpect(jsonPath("$.status").value("booking not found by this id :1"));
				
	}
	
	@Test
	void assignStaffTest() throws Exception {
		when(bookingRepo.existsById(1)).thenReturn(true);
		HttpHeaders header = new HttpHeaders();
		header.set(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(header);
		
		ResponseEntity<CustomerDTO> response = restTemplate.exchange(registration + 2,
				HttpMethod.GET, req, CustomerDTO.class);
		CustomerDTO customerDTO=response.getBody();
				
		when(rest.exchange(registration + 2,
					HttpMethod.GET, req, CustomerDTO.class)).thenReturn(new ResponseEntity<CustomerDTO>(customerDTO,HttpStatus.OK));
	
		mvc.perform(put("/admin/addStaff/1/2")).andDo(print()).andExpect(status().isOk());			
	}
	
	@Test
	void NotAStaffExceptionTest() throws Exception {
		when(bookingRepo.existsById(1)).thenReturn(true);
		HttpHeaders header = new HttpHeaders();
		header.set(ACCEPT, MediaType.APPLICATION_JSON_VALUE);
		HttpEntity<Void> req = new HttpEntity<>(header);
		
		ResponseEntity<CustomerDTO> response = restTemplate.exchange(registration + 1,
				HttpMethod.GET, req, CustomerDTO.class);
		CustomerDTO customerDTO=response.getBody();
				
		when(rest.exchange(registration + 1,
					HttpMethod.GET, req, CustomerDTO.class)).thenReturn(new ResponseEntity<CustomerDTO>(customerDTO,HttpStatus.OK));
	
		mvc.perform(put("/admin/addStaff/1/1")).andDo(print()).andExpect(content().string("not a staff"));			
	}
	
	
	
	@Test
	void assignStaffTestFail() throws Exception {
		when(bookingRepo.existsById(10)).thenReturn(false);

		mvc.perform(put("/admin/addStaff/10/3")).andDo(print()).andExpect(status().is4xxClientError()) .andExpect(content().string("Booking not found"));
			
	}
}
