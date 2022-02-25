package com.employee.testcases;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.baseapi.TestBase;
import com.employee.utilities.RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;

public class Tc003_POST_New_Record_RestUtils extends TestBase {
	
	String empName=RestUtils.empName();
	String empJob=RestUtils.empJob();
	
	
	
	@BeforeClass
	void createEmployee() throws InterruptedException
	{
		
		logger.info("***  Tc003_POST_New_Record   *****");
		
		RestAssured.baseURI="https://reqres.in/api";
		httpRequest = RestAssured.given();
		
		
		 JSONObject requestParams=new JSONObject();
		  
		  requestParams.put("name","empName");
		  requestParams.put("Job","empJob");
		
		  
		  httpRequest.header("Content-Type","application/json");
		  
		  httpRequest.body(requestParams.toJSONString());
		  
		  response=httpRequest.request(Method.POST,"/create");
		  
		  Thread.sleep(5000);
		    
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
		Assert.assertEquals(statusCode, 201);
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
		Assert.assertEquals(statusLine, "HTTP/1.1 201 Created");
	}
	@Test
	void contentType() {
		logger.info("****  Checking Content Type ****");
		
		String ContentType = response.header("Content-Type");
		logger.info("Content Type is" +ContentType);
		Assert.assertEquals(ContentType, "application/json; charset=utf-8");
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
		logger.info("***** Finished Tc003_POST_New_Record ***");
	}
	

}
