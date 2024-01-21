package com.user.api;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateUserTestWithLombok {

	public static String getRandomEmailID() {
		
		return "apiautomation"+System.currentTimeMillis()+"@yahoo.com";
		//return "apiautomation"+ UUID.randomUUID()+"@mail.com"; Another way to create random numbers but above one is better actually as this will be generated with -(hyphens)
	}
	
//	@Test
//	public void createUserTest() {
//		
//		RestAssured.baseURI = "https://gorest.co.in";
//		
//		User user = new User("Vashisth", getRandomEmailID(), "male", "active");
//		
//		Response response = RestAssured.given().log().all()
//					.contentType(ContentType.JSON)
//					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
//					.body(user) //Serialization is happening here as here we are converting the java object to Json.
//					.when().log().all()
//					.post("/public/v2/users");
//		
//		Integer userID = response.jsonPath().get("id"); //jsonPath() method is used to get a JsonPath view of the response body. This will let you use the JsonPath syntax to get values from the response.
//		System.out.println("user id : " + userID);
//		
//		//GET API to get the same user:
//		
//		//2. get the same user and verify it: GET
//		Response getResponse =	given()
//				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
//					.when().log().all()
//						.get("/public/v2/users/"+ userID);
//		
//		ObjectMapper mapper = new ObjectMapper();
//		try {
//			User userResponse = mapper.readValue(getResponse.body().asString(), User.class); //Deserialization happening here
//			
//			System.out.println(userResponse.getId() + ":" + userResponse.getGender() + ":" + userResponse.getEmail() + ":" + userResponse.getStatus());
//			
//			Assert.assertEquals(userID, userResponse.getId());
//			Assert.assertEquals(user.getName(), userResponse.getName());
//			Assert.assertEquals(user.getEmail(), userResponse.getEmail());
//			
//		} catch (JsonProcessingException e) {
//			e.printStackTrace();
//		}
//	}
	
	@Test
	public void createUserTest_WithBuilderPattern() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		//Another way to create the constructor using the Builder pattern.
		User user = new User.UserBuilder()
				.name("Kartikeya")
				.email(getRandomEmailID())
				.gender("male")
				.status("active")
				.build(); //this is coming from the @Builder annotation in the User class.
		
		Response response = RestAssured.given().log().all()
					.contentType(ContentType.JSON)
					.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.body(user) //Serialization is happening here as here we are converting the java object to Json.
					.when().log().all()
					.post("/public/v2/users");
		
		Integer userID = response.jsonPath().get("id"); //jsonPath() method is used to get a JsonPath view of the response body. This will let you use the JsonPath syntax to get values from the response.
		System.out.println("user id : " + userID);
		
		//GET API to get the same user:
		
		//2. get the same user and verify it: GET
		Response getResponse =	given()
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
					.when().log().all()
						.get("/public/v2/users/"+ userID);
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			User userResponse = mapper.readValue(getResponse.body().asString(), User.class); //Deserialization happening here
			
			System.out.println(userResponse.getId() + ":" + userResponse.getGender() + ":" + userResponse.getEmail() + ":" + userResponse.getStatus());
			
			Assert.assertEquals(userID, userResponse.getId());
			Assert.assertEquals(user.getName(), userResponse.getName());
			Assert.assertEquals(user.getEmail(), userResponse.getEmail());
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}
	
}
