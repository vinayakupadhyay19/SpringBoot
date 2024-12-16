package net.vinayakdigest.journalApp.service;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.vinayakdigest.journalApp.model.User;
import net.vinayakdigest.journalApp.repository.journalAppRepository;
import net.vinayakdigest.journalApp.repository.userAppRepository;

@Component
public class userAppServices {
	
	@Autowired
	private userAppRepository uar;
	
	public void saveEntry(User user) {
		uar.save(user);
	}
	
	
	public List<User> getAllEntry(){
		return uar.findAll();
	}
	

	public void  deleteAll() {
		uar.deleteAll();
	}
	
	public void deleteById(ObjectId id) {
		uar.deleteById(id);
	}
	
	public Optional<User> findById(ObjectId id) {
		return uar.findById(id);
	}
	
	public User findByUserName(String username) {
		return uar.findByUsername(username);
	}
}
