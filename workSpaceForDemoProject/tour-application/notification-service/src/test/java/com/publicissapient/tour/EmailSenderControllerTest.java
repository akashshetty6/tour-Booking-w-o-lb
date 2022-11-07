package com.publicissapient.tour;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class EmailSenderControllerTest {
	
	@Autowired
	MockMvc mvc;

	@Test
	void sendMailTest() throws Exception {

		mvc.perform(get("/notify/sendMail/"+"akash@gmail.com"))
		.andDo(print()).andExpect(status().isOk()).andExpect(content().string("notification sent"));
		
	}
}
