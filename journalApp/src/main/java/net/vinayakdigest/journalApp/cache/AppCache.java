package net.vinayakdigest.journalApp.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import net.vinayakdigest.journalApp.model.ConfigurationCollection;
import net.vinayakdigest.journalApp.repository.configCollectionRepository;

@Component
public class AppCache {

	@Autowired
	
	public configCollectionRepository ccr;
	
	public Map<String , String > appCache;
	
	@PostConstruct
	public void init() {
		appCache = new HashMap<>();
		
		List<ConfigurationCollection> all = ccr.findAll();
		
		for(ConfigurationCollection cc : all) {
			appCache.put(cc.getKey(), cc.getValue());
		}
		
	}
}
