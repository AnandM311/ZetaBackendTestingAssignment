package com.qa.utils;

import java.io.IOException;


import com.fasterxml.jackson.core.JsonParseException;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.util.CitiesResponse;
import com.qa.util.LocationsResponse;

public class DataRead {
	static ObjectMapper mapper = new ObjectMapper();

	@SuppressWarnings("unchecked")
	public  <T> T  dataRead(String responseBody,Class<T> T) throws JsonParseException, JsonMappingException, IOException {

		
		if(responseBody.contains("entity_type"))
		{
			
			LocationsResponse thisClass = new com.qa.util.LocationsResponse();
			thisClass = mapper.readValue(responseBody, LocationsResponse.class);
			return (T) thisClass;
			
		}
		CitiesResponse thisClass = new com.qa.util.CitiesResponse();
		thisClass = mapper.readValue(responseBody, CitiesResponse.class);
		return (T) thisClass;
	}
	

 
}
