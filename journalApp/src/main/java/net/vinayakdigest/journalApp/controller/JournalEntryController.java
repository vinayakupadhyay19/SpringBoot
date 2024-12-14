package net.vinayakdigest.journalApp.controller;

import java.time.LocalDateTime;
import java.util.*;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import net.vinayakdigest.journalApp.service.journalAppServices;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
	
	@Autowired
	private journalAppServices jas;
	
	
	@GetMapping("/findallentry")
    public List<JournalEntry> getAllEntry() {
		
		return jas.getAllEntry();
	}
	@GetMapping("/id/{id}")
	public ResponseEntity<JournalEntry> findById(@PathVariable ObjectId id) {
		   Optional<JournalEntry> obj = jas.findById(id);
		   if(obj.isPresent()) {
			   return new ResponseEntity<JournalEntry>(obj.get() , HttpStatus.OK);
		   }
		   return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
	}
	@PostMapping("/createentry")
	public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry je) {
		
		try {
			je.setDate(LocalDateTime.now());
			jas.saveEntry(je);
			return new ResponseEntity<JournalEntry>(je , HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<JournalEntry>(HttpStatus.BAD_REQUEST);
		}
		
		
	}
	
	@PutMapping("/id/{id}")
	public ResponseEntity<JournalEntry> updateEntry(@PathVariable ObjectId id , @RequestBody JournalEntry je) {
		JournalEntry old = jas.findById(id).orElse(null);	
		if(old != null) {
			old.setTitle(je.getTitle());
			old.setContent(je.getContent());
			jas.saveEntry(old);
			return new ResponseEntity<JournalEntry>(HttpStatus.OK);
		}
		else {
			return new ResponseEntity<JournalEntry>(HttpStatus.NOT_FOUND);
		}
		
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
	public ResponseEntity<?> deleteById(@PathVariable ObjectId id) {
		try {
			jas.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
}
