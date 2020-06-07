package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.qa.base.TestBase;
import com.qa.util.CitiesResponse;
import com.qa.util.CitiesResponse.Data;
import com.qa.utils.DataRead;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCitiesApi2 {

	TestBase testbase = new TestBase();
	RequestSpecification httpRequest;
	Response response;
	String pathParameter = testbase.prop.getProperty("pathParameter");
	String baseUri = testbase.prop.getProperty("URI");
	com.qa.util.CitiesResponse cityObj = new com.qa.util.CitiesResponse();
	DataRead dObj = new DataRead();

	Map<String, String> queryParamMap = new HashMap<String, String>();

	@Test(priority = 1)
	public void verifyStatusCodeWithoutApiKey() {
		// Without api key
		RestAssured.baseURI = baseUri;
		httpRequest = RestAssured.given();

		response = httpRequest.request(Method.GET, pathParameter);
		int statusCode = response.statusCode();
		Assert.assertEquals(statusCode, testbase.RESPONSE_STATUS_CODE_403);
	}

	@Test(priority = 2)
	public void verifyResponseBodyWithoutCityID() throws JsonParseException, JsonMappingException, IOException {

		RestAssured.baseURI = baseUri;
		httpRequest = RestAssured.given();

		Map<String, String> headersHap = new HashMap<String, String>();
		String userKey = testbase.prop.getProperty("userKey");

		headersHap.put("user-key", userKey);
		httpRequest.headers(headersHap);

		response = httpRequest.request(Method.GET, pathParameter);
		String responseBody = response.getBody().asString();

		cityObj = dObj.dataRead(responseBody, CitiesResponse.class);
		List<Data> s = cityObj.getData();
		Assert.assertTrue(s.size() == 0);

	}

	@Test(priority = 3)
	public void getCityUsingLatLong() throws JsonParseException, JsonMappingException, IOException {

		RestAssured.baseURI = baseUri;
		httpRequest = RestAssured.given();
		Map<String, String> headersHap = new HashMap<String, String>();

		headersHap.put("user-key", testbase.prop.getProperty("userKey"));
		httpRequest.headers(headersHap);

		queryParamMap.put("lat", testbase.prop.getProperty("lat"));
		queryParamMap.put("lon", testbase.prop.getProperty("lon"));
		httpRequest.queryParams(queryParamMap);

		response = httpRequest.request(Method.GET, pathParameter);
		String responseBody = response.getBody().asString();

		cityObj = dObj.dataRead(responseBody, CitiesResponse.class);
		List<Data> s = cityObj.getData();
		Assert.assertTrue(s.size() == 1);

	}

	@Test(priority = 4)
	public void getCityUsingQ() throws JsonParseException, JsonMappingException, IOException {

		// Get cities useing q=pu

		RestAssured.baseURI = baseUri;
		httpRequest = RestAssured.given();
		Map<String, String> headersHap = new HashMap<String, String>();

		headersHap.put("user-key", testbase.prop.getProperty("userKey"));
		httpRequest.headers(headersHap);

		queryParamMap.put("q", testbase.prop.getProperty("q"));
		httpRequest.queryParams(queryParamMap);

		response = httpRequest.request(Method.GET, pathParameter);
		String responseBody = response.getBody().asString();

		cityObj = dObj.dataRead(responseBody, CitiesResponse.class);
		List<Data> s = cityObj.getData();

		Assert.assertTrue(s.size() > 1);

	}

	@Test(priority = 5)
	public void getCityUsingSpecificCityName() throws JsonParseException, JsonMappingException, IOException {

		

		RestAssured.baseURI = baseUri;
		httpRequest = RestAssured.given();
		Map<String, String> headersHap = new HashMap<String, String>();

		headersHap.put("user-key", testbase.prop.getProperty("userKey"));
		httpRequest.headers(headersHap);

		queryParamMap.put("q", testbase.prop.getProperty("CityName"));
		httpRequest.queryParams(queryParamMap);

		response = httpRequest.request(Method.GET, pathParameter);
		String responseBody = response.getBody().asString();

		cityObj = dObj.dataRead(responseBody, CitiesResponse.class);
		List<Data> s = cityObj.getData();
		Assert.assertTrue(s.size() == 1);

	}

}
