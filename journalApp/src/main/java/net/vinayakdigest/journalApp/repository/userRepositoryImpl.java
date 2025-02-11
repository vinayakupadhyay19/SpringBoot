package net.vinayakdigest.journalApp.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import net.vinayakdigest.journalApp.model.User;

@Component
public class userRepositoryImpl {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	public List<User> getUserSentimentAnalysis(){
		Query query = new Query();
		
		query.addCriteria(Criteria.where("email").exists(true));
		query.addCriteria(Criteria.where("sentimentAnalysis").is(true));
		List<User> users = mongoTemplate.find(query, User.class);
		return users;
	}
}
