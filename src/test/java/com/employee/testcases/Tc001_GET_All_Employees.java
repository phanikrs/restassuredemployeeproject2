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

public class Tc001_GET_All_Employees extends TestBase {
	

	
	@BeforeClass
	void getAllEmployees() throws InterruptedException{
		
		logger.info("**********Started Tc001_GET_All_Employees *******");
		
		RestAssured.baseURI="https://reqres.in/api";
		httpRequest = RestAssured.given();
		response = httpRequest.request(Method.GET,"/users");
		
		Thread.sleep(5);
		}
	
	@Test
	void CheckResponseBody() {
		
		logger.info("******  Checking Response Body  ******");
		
		String responseBody= response.getBody().asString();
		System.out.println("Response Body is" +responseBody);
		Assert.assertTrue(responseBody!=null);
		
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
	
	@Test
	void contentEncoding() {
		logger.info("****  Checking Content Encoding ****");
		
		String ContentEncoding = response.header("Content-Encoding");
		logger.info("Content Encoding is" +ContentEncoding);
		Assert.assertEquals(ContentEncoding, "gzip");
	}
	
	
	@AfterClass
	void tearDown() {
		logger.info("***** Finished Tc001_GET_All_Employees***");
	}
	
	



}
