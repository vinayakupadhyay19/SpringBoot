package net.vinayakdigest.journalApp.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import net.vinayakdigest.journalApp.model.User;
import net.vinayakdigest.journalApp.repository.journalAppRepository;
import net.vinayakdigest.journalApp.repository.userAppRepository;

@Component
@Slf4j
public class userAppServices {
	
	@Autowired
	private userAppRepository uar;
	
	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public boolean saveEntry(User user) {
		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			uar.save(user);
			return true;
		}catch(Exception e) {
			log.error("HAHAHAHAHAHAHA");
			log.info("HAHAHAHAHAHAHA");
			log.warn("HAHAHAHAHAHAHA");
			log.debug("HAHAHAHAHAHAHA");
			log.trace("HAHAHAHAHAHAHA");
			return false;
		}
		
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


	public void deleteByUsername(String username) {
		// TODO Auto-generated method stub
		uar.deleteByUsername(username);
		
	}
}
