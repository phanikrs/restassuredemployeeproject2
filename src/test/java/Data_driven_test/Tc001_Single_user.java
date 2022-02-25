package Data_driven_test;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Tc001_Single_user {
	@Test 
	void PostNewEmployees()
	 {
		RestAssured.baseURI="http://dummy.restapiexample.com/api/v1";
		
	  //Request object
	  RequestSpecification httpRequest=RestAssured.given();
	  
	   
	  //Request paylaod sending along with post request
	  JSONObject requestParams=new JSONObject();
	  
	  requestParams.put("Name","JohnXYZ");
	  requestParams.put("salary","70000");
	  requestParams.put("age","40");
	
	  
	  httpRequest.header("Content-Type","application/json");
	  
	  httpRequest.body(requestParams.toJSONString()); // attach above data to the request
	  
	  //Response object
	  Response response=httpRequest.request(Method.POST,"/create");
	    
	  
	  //print response in console window
	  
	  String responseBody=response.getBody().asString();
	  System.out.println("Response Body is:" +responseBody);
	  Assert.assertEquals(responseBody.contains("JohnXYZ"), true);
	  Assert.assertEquals(responseBody.contains("70000"), true);
	  Assert.assertEquals(responseBody.contains("40"), true);
	  
	  int statuscode=response.getStatusCode();
	  Assert.assertEquals(statuscode, 200);
}
}
