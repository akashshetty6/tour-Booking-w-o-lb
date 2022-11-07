package com.publicissapient.tour;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.publicissapient.tour.entity.Email;

class EntityTest {

	@Test
	void EmailTest() {
		Email mail=new Email();
		mail.setToMail("akash");
		
		Assertions.assertEquals("akash",mail.getToMail());
	}
	
	@Test
	void EmailTest1() {
		Email mail=new Email("akash");
		mail.setToMail("akash");
		
		Assertions.assertEquals("akash",mail.getToMail());
	}
}
