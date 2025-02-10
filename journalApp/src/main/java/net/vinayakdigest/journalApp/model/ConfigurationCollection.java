package net.vinayakdigest.journalApp.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Document("configutation_collection")
public class ConfigurationCollection {
		
	private String key;
	private String value;
}
