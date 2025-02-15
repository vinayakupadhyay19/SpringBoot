package net.vinayakdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.vinayakdigest.journalApp.model.Email;

@Service
@Slf4j
public class EmailService {
	
	@Autowired
	private JavaMailSender jms;
	
	public String sendEmail(Email email) {
		
		try {
			SimpleMailMessage mail = new SimpleMailMessage();
			mail.setTo(email.getTo());
			mail.setSubject(email.getSubject());
			mail.setText(email.getBody());
			jms.send(mail);
			return "Success !";
			
		}catch(Exception e) {
			log.error("Error occured while sending mail : ",e);
			return "Error occured while sending email please check request body !!!";
		}
		
	}
	
}
