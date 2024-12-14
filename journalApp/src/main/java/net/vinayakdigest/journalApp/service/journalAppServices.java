package net.vinayakdigest.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.vinayakdigest.journalApp.model.JournalEntry;
import net.vinayakdigest.journalApp.repository.journalAppRepository;

@Component
public class journalAppServices {
	
	@Autowired
	private journalAppRepository jar;
	
	public void saveEntry(JournalEntry je) {
		jar.save(je);
	}
	
	
	public List<JournalEntry> getAllEntry(){
		return jar.findAll();
	}
	

	public void  deleteAll() {
		jar.deleteAll();
	}
	
	public void deleteById(ObjectId id) {
		jar.deleteById(id);
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return jar.findById(id);
	}
}
