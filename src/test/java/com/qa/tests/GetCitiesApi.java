package com.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.http.Header;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.TestBase;
import com.qa.util.CitiesResponse;
import com.qa.utils.DataRead;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetCitiesApi extends TestBase {

	TestBase testbase = new TestBase();
	RequestSpecification httpRequest; 
	Response response; 
	String pathParameter = testbase.prop.getProperty("pathParameter");
	String baseUri = testbase.prop.getProperty("URI");
	com.qa.util.CitiesResponse cityObj = new com.qa.util.CitiesResponse();

	DataRead dObj = new DataRead();

	Map<String, String> queryParamMap = new HashMap<String, String>();
	String cityID;

	@BeforeMethod
	public void setUp() {

		RestAssured.baseURI = baseUri;
		httpRequest = RestAssured.given();
		Map<String, String> headersHap = new HashMap<String, String>();
		String userKey = testbase.prop.getProperty("userKey");

		headersHap.put("user-key", userKey);
		httpRequest.headers(headersHap);

		cityID = testbase.prop.getProperty("cityID");
		queryParamMap.put("city_ids", cityID);

		httpRequest.queryParams(queryParamMap);
		response = httpRequest.request(Method.GET, pathParameter);

	}

	@Test(priority = 1)
	public void verifyStatusCodeAndStatusLine() {

		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201);

		String statuLine = response.getStatusLine();
		Assert.assertEquals(statuLine, "HTTP/1.1 200 OK");

	}

	@Test(priority = 2)
	public void verifyCityID() throws JsonParseException, JsonMappingException, IOException {
		String responseBody = response.getBody().asString();

		cityObj = dObj.dataRead(responseBody, CitiesResponse.class);

		String responseCityid = cityObj.getData().get(0).getId();

		String cityID = null;

		for (Map.Entry<String, String> entry : queryParamMap.entrySet()) {

			if (entry.getKey().contains("city_ids")) {
				cityID = entry.getValue();

			}
		}

		Assert.assertEquals(responseCityid, cityID);

	}

	@Test(priority = 3)
	public void verifyCountryFlagUrl() throws JsonParseException, JsonMappingException, IOException {
		String responseBody = response.getBody().asString();

		cityObj = dObj.dataRead(responseBody, CitiesResponse.class);;

		String countryID = cityObj.getData().get(0).getCountryId();
		String countryFlagUrl = cityObj.getData().get(0).getCountryFlagUrl();

		Assert.assertTrue(countryFlagUrl.contains(countryID));

	}

}
