package com.publicissapient.tour;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.publicissapient.tour.entity.Customer;
import com.publicissapient.tour.repository.CustomerRepository;

@SpringBootTest
@AutoConfigureMockMvc
class AdminRestControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	CustomerRepository customerRepo;

	@Test
	void findByIdTest() throws Exception {

		Customer customer = new Customer(1, 20, "akash", "shetty", "812334003", "a@gmail.com", "bhatkal", "male",
				"akash", true);

		when(customerRepo.findById(1)).thenReturn(Optional.of(customer));

		mvc.perform(get("/adminRegistration/find/1").with(user("akae@gmail.com").password("akash"))).andDo(print()).
				andExpect(jsonPath("$.firstName").value("akash"));

		mvc.perform(get("/adminRegistration/find/200").with(user("dhoni@gmail.com").password("dhoni"))).andDo(print()).andExpect(status().is4xxClientError())
		.andExpect(content().string("customer not found by this id"));


	}

}

