package net.vinayakdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import net.vinayakdigest.journalApp.model.User;
import net.vinayakdigest.journalApp.repository.userAppRepository;

@Component
public class userDetailServiceImpl implements UserDetailsService{
	
	@Autowired
	private userAppRepository uar;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    // Fetch user from the database
	    User user = uar.findByUsername(username);
	    
	    if (user == null) {
	        // Throw exception if user not found
	        throw new UsernameNotFoundException("User not found with username: " + username);
	    }

	    // Convert the database user to a Spring Security UserDetails
	    return org.springframework.security.core.userdetails.User.builder()
	            .username(user.getUsername())
	            .password(user.getPassword())
	            .roles(user.getRoles().toArray(new String[0])) // Convert roles to array
	            .build();
	}

	
}
