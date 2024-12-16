package net.vinayakdigest.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import net.vinayakdigest.journalApp.model.JournalEntry;
import net.vinayakdigest.journalApp.model.User;
import net.vinayakdigest.journalApp.service.journalAppServices;
import net.vinayakdigest.journalApp.service.userAppServices;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private userAppServices uas;
	
	@GetMapping("/getAllUser")
	public List<User> getAllUsers(){
		return uas.getAllEntry();
	}
	
	@PostMapping("/createUser")
	public void createUser(@RequestBody User user) {
		uas.saveEntry(user);
	}
	
	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User user){
		User userInDb = uas.findByUserName(user.getUsername());
		if(userInDb != null) {
			userInDb.setUsername(user.getUsername());
			userInDb.setPassword(user.getPassword());
			uas.saveEntry(userInDb);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
