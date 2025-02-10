package net.vinayakdigest.journalApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.vinayakdigest.journalApp.cache.AppCache;
import net.vinayakdigest.journalApp.model.User;
import net.vinayakdigest.journalApp.service.userAppServices;
import net.vinayakdigest.journalApp.service.wheatherServiceImpl;

@RestController
@RequestMapping("/public")
public class PublicController {
	
	@Autowired
	private userAppServices uas;
	
	@Autowired
	private AppCache ac;

	@GetMapping("health")
	public String healthCheck() {
		return "OK";
	}
	
	@PostMapping("create-user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		boolean val = uas.saveEntry(user);
		
		if(val) {
			return new ResponseEntity<> (HttpStatus.OK);
		}else {
			return new ResponseEntity<> (HttpStatus.BAD_REQUEST);
		}
		
	}
	@GetMapping("cache-clear-api")
	public ResponseEntity<?> cacheClear(){
		ac.init();
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
