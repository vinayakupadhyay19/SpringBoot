package net.vinayakdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.vinayakdigest.journalApp.model.JournalEntry;
import net.vinayakdigest.journalApp.model.User;
import net.vinayakdigest.journalApp.repository.journalAppRepository;
import net.vinayakdigest.journalApp.repository.userAppRepository;

@Component
public class journalAppServices {
	
	@Autowired
	private journalAppRepository jar;
	
	@Autowired
	private userAppRepository uar;
	
	public void saveEntry(JournalEntry je ,String username) {
		User user = uar.findByUsername(username);
		je.setDate(LocalDateTime.now());
		JournalEntry saved = jar.save(je);
		user.getJournalEntries().add(saved);
		uar.save(user);
	}
	public void saveEntry(JournalEntry je) {
		je.setDate(LocalDateTime.now());
		jar.save(je);
	}
	
	public List<JournalEntry> getAllEntry(){
		return jar.findAll();
	}
	

	public void  deleteAll() {
		jar.deleteAll();
	}
	
	public void deleteById(ObjectId id , String username) {
		User user = uar.findByUsername(username);
		user.getJournalEntries().removeIf(x->x.getId().equals(id));
		uar.save(user);
		jar.deleteById(id);
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return jar.findById(id);
	}
}
