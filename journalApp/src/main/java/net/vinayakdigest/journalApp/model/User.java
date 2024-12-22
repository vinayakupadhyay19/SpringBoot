package net.vinayakdigest.journalApp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.mongodb.lang.NonNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Data
@NoArgsConstructor
@Document(collection = "users")
public class User {
	@Id
	private ObjectId id;
	@Indexed(unique = true)
	@NonNull
	private String username = "Deafault";
	@NonNull	
	private String password = "Deafault";
	@DBRef
	private List<JournalEntry> journalEntries = new ArrayList<JournalEntry>();
}
