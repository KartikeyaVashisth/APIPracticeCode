package com;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class GetProduct {
	
	//Basically, the previous method of using the JQL(In JsonPathTest java class file) is more appropriate instead of doing unnecessary deserialization. We can ignore it for the GET call where there is no request body. Both approaches are fine though but previous one(JQL) is easier.
	//What if someone is using this approach in their framework, so we should be aware of this approach as well. Might be important for interview as well.
	@Test
	public void getProductTest_With_POJO() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given() //this 'response' is reference variable of Response class but not a Json. It will contain multiple things like headers, body, cookies, status
			.when()
				.get("/products");
		
		//json to pojo mapping:de-serialization
		
		ObjectMapper mapper = new ObjectMapper(); //ObjectMapper class will help to deserialize
		try { //Here, we are extracting the body from response and converting to String using asString() method.
			Product product[] = mapper.readValue(response.getBody().asString(), Product[].class); //Passing the "Product[].class" means give the POJO class where we can map the Json string or where we can deserialize. Basically, JSON to Java Object(POJO). Since, with this API we are getting response in Json array, that's why we have used Product[].class
			
			for(Product p : product) {
				System.out.println("ID: " + p.getId());
				System.out.println("Title " + p.getTitle());
				System.out.println("Price :" + p.getPrice());
				System.out.println("Description: " + p.getDescription());
				System.out.println("Category: " + p.getCategory());
				System.out.println("Image: " + p.getImage());
				System.out.println("Rate: " + p.getRating().getRate());
				System.out.println("Count: " + p.getRating().getCount());

				System.out.println("--------------");
			}
			
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void getProductTest_With_POJO_Lombok() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given()
			.when()
				.get("/products");	
		
		//json to pojo mapping:de-serialization
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			ProductLombok product[] = mapper.readValue(response.getBody().asString(), ProductLombok[].class);//JSON to Java Object(POJO)
			
			for(ProductLombok p : product) {
				System.out.println("ID: " + p.getId());
				System.out.println("Title " + p.getTitle());
				System.out.println("Price :" + p.getPrice());
				System.out.println("Description: " + p.getDescription());
				System.out.println("Category: " + p.getCategory());
				System.out.println("Image: " + p.getImage());
				System.out.println("Rate: " + p.getRating().getRate());
				System.out.println("Count: " + p.getRating().getCount());

				System.out.println("--------------");
			}	
			
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
	}
}
