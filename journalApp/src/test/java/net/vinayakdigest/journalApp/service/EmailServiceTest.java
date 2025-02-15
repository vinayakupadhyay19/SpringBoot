package net.vinayakdigest.journalApp.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@Disabled
@SpringBootTest
public class EmailServiceTest {
	
	@Autowired 
	private EmailService es;
	
	@Test
	public void testSendMail() {

	}
}
