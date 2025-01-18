package net.vinayakdigest.journalApp.controller;


import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
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
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User userInDb = uas.findByUserName(username);
		List<String> roles = userInDb.getRoles();
		int f=0;
		for(int i=0;i<roles.size();i++) {
			if(roles.get(i).equals("ADMIN")) {
				f=1;
			}
		}
		if(f == 1)
		return uas.getAllEntry();
		
		return  null;
	}

	@PutMapping("/updateUser")
	public ResponseEntity<?> updateUser(@RequestBody User user){
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userInDb = uas.findByUserName(auth.getName());
		if(userInDb != null) {
			userInDb.setUsername(user.getUsername());
			userInDb.setPassword(user.getPassword());
			userInDb.setRoles(user.getRoles());
			uas.saveEntry(userInDb);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@DeleteMapping("delete-user")
	public void deleteUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		uas.deleteByUsername(auth.getName());
	}
}
