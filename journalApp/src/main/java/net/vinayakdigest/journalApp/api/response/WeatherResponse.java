package net.vinayakdigest.journalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {
	
	private Current current;
	
	@Getter
	@Setter
	public class Current{
		@JsonProperty("temp_c")
	    public double tempC;
		@JsonProperty("temp_f")
	    public double tempF;
		@JsonProperty("wind_kph")
	    public double windKph;
	}
}
