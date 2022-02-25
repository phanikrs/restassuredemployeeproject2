import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Tc003_GET_validateheaders {
	
	@Test
	void getvalidateheaders() {
		
		//Specify URI
		RestAssured.baseURI="https://maps.googleapis.com";
		
		//Request Object
		RequestSpecification httpRequest=RestAssured.given();
		
		//Response object
		Response response=httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
		
		//Print console
		String ResponseBody=response.getBody().asString();
		System.out.println("Response Body is "+ResponseBody);
		
		//Validating Headers
		String ContentType=response.header("Content-Type");
		System.out.println("Content_Type is "+ContentType);
		Assert.assertEquals(ContentType,"application/xml; charset=UTF-8");
		
		String ContentEncoding=response.header("Content-Encoding");
		System.out.println("Content_Encoding is "+ContentEncoding);
		Assert.assertEquals(ContentEncoding,"gzip");
		
	}

}
