package net.vinayakdigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.vinayakdigest.journalApp.model.User;
import net.vinayakdigest.journalApp.service.userAppServices;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private userAppServices uas;

	@GetMapping("health")
	public String healthCheck() {
		return "OK";
	}
	
	@PostMapping("create-user")
	public void createUser(@RequestBody User user) {
		uas.saveEntry(user);
	}
}
