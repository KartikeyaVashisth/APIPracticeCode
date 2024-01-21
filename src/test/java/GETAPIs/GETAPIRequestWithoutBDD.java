package GETAPIs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GETAPIRequestWithoutBDD {
	
	RequestSpecification request;
	
	//RestAssured supports two type of approach BDD and Non BDD. This is Non BDD approach.
	
	@BeforeTest
	public void setup() {
		//Instead of writing same code again and again in @Test method, we can execute it once under @BeforeTest
		//REQUEST:
		RestAssured.baseURI = "https://gorest.co.in";
		request = RestAssured.given(); // RestAssured.given() will help in creating one request specification. Return type is RequestSpecification
		request.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6"); //header is always added before GET call
	}
	
	@Test
	public void getUsersAPITest() {

		//REQUEST:
//		RestAssured.baseURI = "https://gorest.co.in";
//		RequestSpecification request = RestAssured.given(); // RestAssured.given() will help in creating one request specification. Return type is RequestSpecification
//		request.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6"); //header is always added before GET call
		Response response = request.get("/public/v2/users"); //.get() method is for GET call. Similarly we have .post, .put, .patch, .delete. The Return type is Response.

		//=================
		//Status Code
		int statusCode = response.statusCode(); //Fetching the statuscode from the response
		System.out.println("Status code is:"+statusCode);

		//Verification point
		Assert.assertEquals(statusCode, 200);

		//Status Message
		String statusLine = response.getStatusLine();
		System.out.println("Status message is: " +statusLine);

		//fetch the whole response body using PrettyPrint method
		String body = response.prettyPrint(); 
		System.out.println("Response Body is: "+body);

		//To Fetch a specific Header, we need to pass the header key to get the value of the header name, we need to pass the key.
		String contentType = response.header("Content-Type");
		System.out.println(contentType);

		//To fetch all the headers
		List<Header> headersList = response.headers().asList();
		System.out.println(headersList.size());

		//Iterating Headers to get the key and value of all headers.
		for(Header h : headersList ) {
			System.out.println(h.getName() + ":" + h.getValue());
		}
	}
	
	@Test
	public void getAllUsersWithQueryParameterAPITest() {

		//REQUEST:
//		RestAssured.baseURI = "https://gorest.co.in";
//		RequestSpecification request = RestAssured.given(); // RestAssured.given() will help in creating one request specification. Return type is RequestSpecification
		request.queryParam("name", "Kartikeya");
		request.queryParam("status", "active");
//		request.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6"); //header is always added before GET call
		Response response = request.get("/public/v2/users"); //.get() method is for GET call. Similarly we have .post, .put, .patch, .delete. The Return type is Response.

		//=================
		//Status Code
		int statusCode = response.statusCode(); //Fetching the statuscode from the response
		System.out.println("Status code is:"+statusCode);

		//Verification point
		Assert.assertEquals(statusCode, 200);

		//Status Message
		String statusLine = response.getStatusLine();
		System.out.println("Status message is: " +statusLine);

		//fetch the whole response body using PrettyPrint method
		String body = response.prettyPrint(); 
		System.out.println("Response Body is: "+body);

		}
	
	@Test
	public void getAllUsersWithQueryParameter_WithHashMap_APITest() {

		//REQUEST:
//		RestAssured.baseURI = "https://gorest.co.in";
//		RequestSpecification request = RestAssured.given(); // RestAssured.given() will help in creating one request specification. Return type is RequestSpecification
		
		Map<String,String> queryParamsMap = new HashMap<String,String>();
		queryParamsMap.put("name", "Kartik");
		queryParamsMap.put("gender", "male");
		
		request.queryParams(queryParamsMap); //Passing the queryparamsMap Hashmap that we created to the request.queryParams() method.
		
//		request.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6"); //header is always added before GET call
		Response response = request.get("/public/v2/users"); //.get() method is for GET call. Similarly we have .post, .put, .patch, .delete. The Return type is Response.

		//=================
		//Status Code
		int statusCode = response.statusCode(); //Fetching the statuscode from the response
		System.out.println("Status code is:"+statusCode);

		//Verification point
		Assert.assertEquals(statusCode, 200);

		//Status Message
		String statusLine = response.getStatusLine();
		System.out.println("Status message is: " +statusLine);

		//fetch the whole response body using PrettyPrint method
		String body = response.prettyPrint(); 
		System.out.println("Response Body is: "+body);

		
		}


}
