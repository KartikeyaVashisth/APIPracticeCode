package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import Pojo.Credentials;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;

public class BookingAuthWithPOJOTest {
	
	//POJO - Plain Old Java Object --
		//can not extend any other class
		//oop - encapsulation
		//private class vars -- json body
		//public getter/setter
		
		//serialization/Marshelling --> java object to other format: file, media, json/xml/text/pdf. Basically Serialization is converting Java object to Non java object.
		//Serialization is the concept of only and only for Non static
		//POJO to json -- Serialization/Marshelling
		//json to pojo - De-serialization/UnMarshelling
		//add jackson dependency for the serialization
		
		@Test
		public void getBookingAuthTokenTest_With_Json_String() {
			
			RestAssured.baseURI = "https://restful-booker.herokuapp.com";
			Credentials creds = new Credentials("admin", "password123"); //Object of Credentials class got created and constructor will be called.
			
			String tokenId = given().log().all()
					.contentType(ContentType.JSON)
					.body(creds) //Passing the java object to the .body() method, Here an Object request content that will automatically be serialized to JSON and sent with the request. This works for the POST and PUT methods only.
					.when().log().all()
						.post("/auth")
							.then()
								.assertThat()
									.statusCode(200)
										.extract()
											.path("token");
							
					
				System.out.println(tokenId);	
				Assert.assertNotNull(tokenId);
				
				//response json -- username --- compare with getter(getusername())
		}

}
