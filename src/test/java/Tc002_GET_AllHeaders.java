import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Tc002_GET_AllHeaders {
	
	@Test
	void getallheaders() {
		
		//Specifying URI
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Request object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response object
		Response response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
		 
		// print the console
		String ResponseBody=response.getBody().asString();
		System.out.println("Response body is "+ResponseBody);
		
		//capturing all headers
		Headers allheaders=response.headers();
		for(Header header:allheaders){
			System.out.println(header.getName()+"   "+header.getValue());
		}
		
	}
	

}
