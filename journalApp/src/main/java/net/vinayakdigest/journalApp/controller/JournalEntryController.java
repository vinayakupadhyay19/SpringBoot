package net.vinayakdigest.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.tomcat.Jar;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
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
@RequestMapping("/journal")
public class JournalEntryController {

	@Autowired
	private journalAppServices jas;

	@Autowired
	private userAppServices uas;


	@GetMapping("/findallentry")
	public  ResponseEntity <?> getAllEntryOfUser() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		User user = uas.findByUserName(username);
		List<JournalEntry> res = user.getJournalEntries();
		if(res != null && !res.isEmpty()) {
			return new ResponseEntity<>(res , HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<JournalEntry> findById(@PathVariable ObjectId id) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		User user = jas.findByUsername(username);

		List<JournalEntry> collect =  user.getJournalEntries()
				.stream().filter(x-> x.getId().equals(id)).collect(Collectors.toList());
		if(!collect.isEmpty()) {
			Optional<JournalEntry> obj = jas.findById(id);
			if(obj.isPresent()) {
				return new ResponseEntity<JournalEntry>(obj.get() , HttpStatus.OK);
			}
		}
		return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
	}
	@PostMapping("/createentry")
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry je ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();
		try {
			jas.saveEntry(je,username);
			return new ResponseEntity<JournalEntry>(je , HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
		}


	}

	@PutMapping("/id/{id}")
	public ResponseEntity<JournalEntry> updateEntry(
			@PathVariable ObjectId id ,
			@RequestBody JournalEntry je
			){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		User user = jas.findByUsername(username);

		List<JournalEntry> collect = user.getJournalEntries()
				.stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

		if(!collect.isEmpty()) {
			JournalEntry old = jas.findById(id).orElse(null);	
			if(old != null) {
				if(je.getTitle() != null) 
				old.setTitle(je.getTitle());
				old.setContent(je.getContent());
				jas.saveEntry(old);
				return new ResponseEntity<JournalEntry>(HttpStatus.OK);
				
			}
		}

		return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/deleteall")
	public ResponseEntity<?> deleteAll() {
		try {
			jas.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteById(@PathVariable ObjectId id ) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String username = auth.getName();

		User user = jas.findByUsername(username);

		List<JournalEntry>collect = user.getJournalEntries().stream().filter(x->x.getId().equals(id)).collect(Collectors.toList());

		if(!collect.isEmpty()) {
			jas.deleteById(id,username);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
}
