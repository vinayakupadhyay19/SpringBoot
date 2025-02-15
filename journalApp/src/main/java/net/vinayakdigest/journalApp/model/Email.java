package net.vinayakdigest.journalApp.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Email {
	
	private String to;
	private String subject;
	private List<String> cc;
	private List<String>bcc;
	private String body;
}
