package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.qa.base.TestBase;
import com.qa.util.CitiesResponse;
import com.qa.util.CitiesResponse.Data;
import com.qa.util.LocationsResponse;
import com.qa.utils.DataRead;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetLocationsApi extends DataRead {

	TestBase testbase = new TestBase();
	RequestSpecification httpRequest;
	Response response;
	String pathParameter = testbase.prop.getProperty("pathParameter2");
	String baseUri = testbase.prop.getProperty("URI");
	Map<String, String> queryParamMap = new HashMap<String, String>();

	com.qa.util.LocationsResponse locObj = new com.qa.util.LocationsResponse();
	DataRead dObj = new DataRead();

	@BeforeMethod
	public void setUp() {

		RestAssured.baseURI = baseUri;
		httpRequest = RestAssured.given();
		Map<String, String> headersHap = new HashMap<String, String>();
		headersHap.put("user-key", testbase.prop.getProperty("userKey"));
		httpRequest.headers(headersHap);

	}

	@Test(priority = 1)
	public void verfiyResposeBodyWithoutQueryParams() {
		response = httpRequest.request(Method.GET, pathParameter);
		String responseBody = response.getBody().asString();
		Assert.assertEquals(responseBody.contains("Beerescourt, Hamilton"), true);
	}

	@Test(priority = 2)
	public void verfiyResposeBodyWithQueryParams() throws JsonParseException, JsonMappingException, IOException {
		queryParamMap.put("query", testbase.prop.getProperty("cityName"));
		httpRequest.queryParams(queryParamMap);
		response = httpRequest.request(Method.GET, pathParameter);
		String responseBody = response.getBody().asString();

		locObj = dObj.dataRead(responseBody, LocationsResponse.class);
		List<com.qa.util.LocationsResponse.Data> s = locObj.getData();
		Assert.assertTrue(s.size() == 1);

	}
	@Test(priority = 3)
	public void verfiyResposeBodyWithAllQueryParams() throws JsonParseException, JsonMappingException, IOException {
		queryParamMap.put("query", testbase.prop.getProperty("cityName"));
		queryParamMap.put("count", testbase.prop.getProperty("count"));
		
		httpRequest.queryParams(queryParamMap);
		response = httpRequest.request(Method.GET, pathParameter);
		String responseBody = response.getBody().asString();

		locObj = dObj.dataRead(responseBody, LocationsResponse.class);
		List<com.qa.util.LocationsResponse.Data> s = locObj.getData();
		Assert.assertTrue(s.size() > 1);
	}
	
	
}
