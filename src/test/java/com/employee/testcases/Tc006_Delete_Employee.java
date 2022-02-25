package com.employee.testcases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.baseapi.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;

public class Tc006_Delete_Employee extends TestBase {

	@BeforeClass
	void deleteEmployee() throws InterruptedException{
		
		logger.info("**********Started Tc005_Delete_Employee *******");
		
		RestAssured.baseURI="https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/users");
		
		// First get the JsonPath object instance from the response interface
		JsonPath JsonPathEvaluator = response.jsonPath();
		
		//Capture id
		String empid=JsonPathEvaluator.get("[0].id");
		response =httpRequest.request(Method.DELETE,"/delete/"+empid);
		
		Thread.sleep(3000);
	}
	
	@Test
	void CheckResponseBody() {
		
		logger.info("******  Checking Response Body  ******");
		
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is" +responseBody);

		
		
	}
	
	@Test
	void checkStatusCode() {
		logger.info("****  Checking Status Code ****");
		
		int statusCode = response.getStatusCode();
		logger.info("statusCode is" +statusCode);
		Assert.assertEquals(statusCode, 204);
	}
	
	@Test
	void checkResponseTime() {
		
		logger.info("*** checking Response Time ***");
		long responseTime= response.getTime();
		logger.info("Response time is " +responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<10000);
		
		}
	@Test
	void checkStatusLine() {
		logger.info("****  Checking Status Line ****");
		
		String statusLine = response.getStatusLine();
		logger.info("statusCode is" +statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 204 No Content");
	}
	@Test
	void contentType() {
		logger.info("****  Checking Content Type ****");
		
		String ContentType = response.header("Content-Type");
		logger.info("Content Type is" +ContentType);
		Assert.assertEquals(ContentType, null);
	}
	
	@Test
	void checkServeType() {
		logger.info("****  Checking Serve Type ****");
		
		String serveType = response.header("server");
		logger.info("serve Type is" +serveType);
		Assert.assertEquals(serveType, "cloudflare");
	}
	

	@AfterClass
	void tearDown() {
		logger.info("***** Finished Tc006_Delete_Employee  ***");
	}
	

}


