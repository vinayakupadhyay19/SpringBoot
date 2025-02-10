package net.vinayakdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.vinayakdigest.journalApp.api.response.WeatherResponse;
import net.vinayakdigest.journalApp.cache.AppCache;

@Component
public class wheatherServiceImpl {
	
	@Value("${whether.api.apiKey}")
	private String apiKey;
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private AppCache ac;
	
	
	public WeatherResponse getWether(String city) {
		String finalAPI = ac.appCache.get("weatherApi").replace("<API_KEY>", apiKey).replace("<CITY>", city);
		
		ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET ,null , WeatherResponse.class);
		
		return response.getBody();
		
	}
}
