package net.vinayakdigest.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import net.vinayakdigest.journalApp.model.JournalEntry;
import net.vinayakdigest.journalApp.model.User;


public interface userAppRepository extends MongoRepository<User, ObjectId> {
	User findByUsername(String username);
	
    void deleteByUsername(String username);
	
}
