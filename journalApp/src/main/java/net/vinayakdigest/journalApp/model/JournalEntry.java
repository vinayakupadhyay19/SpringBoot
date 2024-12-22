package net.vinayakdigest.journalApp.model;
import java.time.LocalDateTime;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import com.mongodb.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;



@Data
@NoArgsConstructor
@Component
@Document(collection = "journal_entries")

public class JournalEntry {
	@Id
	private ObjectId id;
	@NonNull
	private String title;
	
	private String content;

	private LocalDateTime date;

}
