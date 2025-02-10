package net.vinayakdigest.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import net.vinayakdigest.journalApp.model.ConfigurationCollection;

public interface configCollectionRepository extends MongoRepository<ConfigurationCollection, ObjectId> {

}
