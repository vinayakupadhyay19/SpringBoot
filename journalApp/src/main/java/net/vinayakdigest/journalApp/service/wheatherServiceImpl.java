package net.vinayakdigest.journalApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import net.vinayakdigest.journalApp.api.response.WeatherResponse;

@Component
public class wheatherServiceImpl {
	
	@Value("${whether.api.apiKey}")
	private String apiKey;
	
	private static final String API = "http://api.weatherapi.com/v1/current.json?key=API_KEY&q=CITY";
	
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	public WeatherResponse getWether(String city) {
		String finalAPI = API.replace("API_KEY", apiKey).replace("CITY", city);
		
		ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET ,null , WeatherResponse.class);
		
		return response.getBody();
		
	}
}
