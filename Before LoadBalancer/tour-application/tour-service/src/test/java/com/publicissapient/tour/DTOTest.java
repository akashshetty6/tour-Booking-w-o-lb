package com.publicissapient.tour;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.publicissapient.tour.dto.BookingDTO;
import com.publicissapient.tour.dto.CustomerDTO;
import com.publicissapient.tour.dto.PackageDTO;
import com.publicissapient.tour.dto.RoleDTO;
import com.publicissapient.tour.entity.Booking;
import com.publicissapient.tour.entity.Packages;
import com.publicissapient.tour.util.Message;

class DTOTest {

	@Test
	void BookingDTOTest() {
	
		BookingDTO book=new BookingDTO();
		book.setCode(1);
		book.setNoOfPeople(1);
		book.setTotalPrice(300.0);
		book.setUserName("akash");	
		book.setFromDate("2022-10-20");
		
		assertEquals(1, book.getCode());
	    assertEquals(1,book.getNoOfPeople());
	    assertEquals(300.0,book.getTotalPrice());
	    assertEquals("akash",book.getUserName());
	    assertEquals("2022-10-20", book.getFromDate());
	}
	
	@Test
	void BookingDTO1Test() {
	
		BookingDTO book=new BookingDTO("akash",3,"2022",3,3.3);
		book.setCode(1);
		book.setNoOfPeople(1);
		book.setTotalPrice(300.0);
		book.setUserName("akash");	
		book.setFromDate("2022-10-20");
		
		assertEquals(1, book.getCode());
	    assertEquals(1,book.getNoOfPeople());
	    assertEquals(300.0,book.getTotalPrice());
	    assertEquals("akash",book.getUserName());
	    assertEquals("2022-10-20", book.getFromDate());
	}
	
	@Test
	void TestDTO(){
	CustomerDTO customer= new CustomerDTO(3, "Akash", "shetty", "9900101010", "akash@gmail.com",
			"bhatkal", "male", "akash",true);
	Assertions.assertEquals(3, customer.getAge());
	Assertions.assertEquals("Akash", customer.getFirstName());
	Assertions.assertEquals("shetty", customer.getLastName());
	Assertions.assertEquals("9900101010", customer.getPhoneNo());
	Assertions.assertEquals("akash@gmail.com", customer.getEmail());
	Assertions.assertEquals("bhatkal", customer.getAddress());
	Assertions.assertEquals("male", customer.getGender());
	Assertions.assertEquals( "akash", customer.getPassword());
	Assertions.assertEquals(true, customer.isEnabled());
	
	}
	
	@Test 
	void PackageDTOTest() {
		PackageDTO pack=new PackageDTO();
		pack.setCode(1);
		pack.setFacilities("AC ROOM");
		pack.setFees(1000);
		pack.setPackageDetails("bang-mys");
		
	    assertEquals(1,pack.getCode());
	    assertEquals("AC ROOM",pack.getFacilities());
	    assertEquals(1000,pack.getFees());
	    assertEquals("bang-mys",pack.getPackageDetails());
	}
	
	@Test 
	void PackageDTO1Test() {
		PackageDTO pack=new  PackageDTO(1, "abc", 10, "xyz");
		pack.setCode(1);
		pack.setFacilities("AC ROOM");
		pack.setFees(1000);
		pack.setPackageDetails("bang-mys");
		
	    assertEquals(1,pack.getCode());
	    assertEquals("AC ROOM",pack.getFacilities());
	    assertEquals(1000,pack.getFees());
	    assertEquals("bang-mys",pack.getPackageDetails());
	} 
	
	@Test 
	void PackageDTO2Test() {
		PackageDTO pack=new  PackageDTO("abc", 10, "xyz");
		pack.setFacilities("AC ROOM");
		pack.setFees(1000);
		pack.setPackageDetails("bang-mys");
		
	    assertEquals("AC ROOM",pack.getFacilities());
	    assertEquals(1000,pack.getFees());
	    assertEquals("bang-mys",pack.getPackageDetails());
	} 
	
	@Test
	void RoleTest() {
		RoleDTO role=new  RoleDTO();
		role.setId(2);
		role.setName("customer");
		
		Assertions.assertEquals(2,role.getId());
		Assertions.assertEquals("customer",role.getName());
		
	}
	
	@Test
	void BookingTest() {
	
		Booking book=new Booking(1,"akash",3,"2022",3,3.3,"xyz",2);
		book.setBookingId(1);
		book.setCode(1);
		book.setNoOfPeople(1);
		book.setTotalPrice(300.0);
		book.setUserName("akash");	
		book.setFromDate("2022-10-20");
		book.setPaymentStatus("xyz");
		book.setStaffId(2);
		
		assertEquals(1, book.getCode());
	    assertEquals(1,book.getNoOfPeople());
	    assertEquals(300.0,book.getTotalPrice()); 
	    assertEquals("akash",book.getUserName());
	    assertEquals("2022-10-20", book.getFromDate());
	}
	
	@Test
	void packageTest() {
		Packages pack=new Packages();
		pack.setCode(1);
		pack.setFacilities("abc");
		pack.setFees(1000);
		pack.setPackageDetails("xyz");
		
		assertEquals(1, pack.getCode());
	}
	
	@Test
	void MessageTest() {
		Message m=new Message();
		m.setStatus("hi");
		
		assertEquals("hi", m.getStatus());
	}
	
}
   
