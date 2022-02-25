package com.employee.testcases;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.employee.baseapi.TestBase;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Tc002_GET_single_Employee extends TestBase {
	
	RequestSpecification httpRequest;
	Response response;
	
	@BeforeClass
	void getEmployeeData() throws InterruptedException{
		
		logger.info("**********Started Tc002_GET_Single_Employee *******");
		
		RestAssured.baseURI="https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/employees/"+id);
		
		Thread.sleep(5000);
		}
	
	@Test
	void CheckResponseBody() {
		
		logger.info("******  Checking Response Body  ******");
		
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is" +responseBody);
		Assert.assertEquals(responseBody.contains(id),true);
		
		
	}
	
	@Test
	void checkStatusCode() {
		logger.info("****  Checking Status Code ****");
		
		int statusCode = response.getStatusCode();
		logger.info("statusCode is" +statusCode);
		Assert.assertEquals(statusCode, 200);
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
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
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
		logger.info("***** Finished Tc002_GET_Single_Employee***");
	}
	
	



}
