package net.vinayakdigest.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import net.vinayakdigest.journalApp.model.JournalEntry;


public interface journalAppRepository extends MongoRepository<JournalEntry, ObjectId> {
	
}
