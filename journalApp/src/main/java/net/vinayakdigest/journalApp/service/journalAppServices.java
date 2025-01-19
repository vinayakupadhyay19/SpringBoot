package net.vinayakdigest.journalApp.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
	
	@Transactional
	public void saveEntry(JournalEntry je ,String username) {
		try {
			User user = uar.findByUsername(username);
			je.setDate(LocalDateTime.now());
			JournalEntry saved = jar.save(je);
			user.getJournalEntries().add(saved);
			uar.save(user);
		}catch(Exception e) {
			System.out.println(e);
		}
		
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
	
	@Transactional
	public void deleteById(ObjectId id , String username) {
		try {
			User user = uar.findByUsername(username);
			boolean removed = user.getJournalEntries().removeIf(x->x.getId().equals(id));
			if(removed) {
				uar.save(user);
				jar.deleteById(id);
			}
		}catch(Exception e) {
			System.out.println(e);
			throw new RuntimeException("An error occured while deleting the entry : ",e);
		}
		
	}
	
	public Optional<JournalEntry> findById(ObjectId id) {
		return jar.findById(id);
	}
	public User findByUsername(String username) {
		return uar.findByUsername(username);
	}
}
