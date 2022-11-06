package com.publicissapient.tour;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class FrontEndApplication implements WebMvcConfigurer{

	public static void main(String[] args) {
		SpringApplication.run(FrontEndApplication.class, args);
	}
	
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("home");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/signup").setViewName("signup");
		registry.addViewController("/signin").setViewName("signin");
		registry.addViewController("/admin").setViewName("admin");
		registry.addViewController("/customer").setViewName("customer");
		registry.addViewController("/staff").setViewName("staff");
		
		
		registry.addViewController("/createPackage").setViewName("createPackage");

		registry.addViewController("/updatePackage").setViewName("updatePackage");
		registry.addViewController("/deletePackage").setViewName("deletePackage");
		registry.addViewController("/deleteBooking").setViewName("deleteBooking");
		registry.addViewController("/assignStaff").setViewName("assignStaff");
		
		
		registry.addViewController("/myBookings").setViewName("MyBookings");
		registry.addViewController("/myBooking").setViewName("myBookingList");
		registry.addViewController("/makeBooking").setViewName("makeBooking");
		registry.addViewController("/deleteBookingForCustomer").setViewName("deleteBookingForCustomer");
		registry.addViewController("/makePayment").setViewName("makePayment");
		
		
	}
	
	@Bean
	RestTemplate resttemplate() {
		return new RestTemplate();
	}

}
