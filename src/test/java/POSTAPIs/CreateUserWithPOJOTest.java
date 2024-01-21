package POSTAPIs;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;

import org.testng.annotations.Test;

import Pojo.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserWithPOJOTest {
	
	// So, basically there are 3 ways we can pass to the .body() method of the POST call as below:
	//1. direct supply the json string like in BookingAuthTest 1st TC.
	//2. pass the json file like in BookingAuthTest 2nd TC.
	//3. pojo - java objects -- to json with the help of jackson-databind library dependency/rest assured (Most preferred)

	public static String getRandomEmailID() {
	
		return "apiautomation"+System.currentTimeMillis()+"@gmail.com";
		//return "apiautomation"+ UUID.randomUUID()+"@mail.com"; Another way to create random numbers but above one is better actually as this will be generated with -(hyphens)
	}
	
	@Test
	public void addUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		User user = new User("Kartikeya", getRandomEmailID() , "male", "active");
		
		int userId = given().log().all()
		.contentType(ContentType.JSON)
		.body(user) //Passing the java object to the .body() method, Here an Object request content that will automatically be serialized to JSON and sent with the request. This works for the POST and PUT methods only.
		//We need to add Jackson-databind dependency to convert java object to the JSON object. Otherwise on passing object to .body() method, IllegalStateException will be thrown.
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
		.post("/public/v2/users/")
		.then().log().all()
		.assertThat()
			.statusCode(201)
			 .and()
			  .body("name", equalTo(user.getName())) //Usage of the getter method present in the User(POJO) class. //Setter method will be used for the PUT call.
			   .extract()
				.path("id");
			
	System.out.println("user id -->" + userId);
	
	//2. get the same user and verify it: GET
	given()
	.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when().log().all()
			.get("/public/v2/users/"+ userId)
				.then()
					.assertThat()
						.statusCode(200)
							.and()
								.body("id", equalTo(userId))
									.and()
										.body("name", equalTo(user.getName())) //Usage of the getter method in the User(POJO) class.
											.and()
												.body("status", equalTo(user.getStatus())) //Usage of the getter method in the User(POJO) class.
													.and()
														.body("email", equalTo(user.getEmail())); //Usage of the getter method in the User(POJO) class.
	}
	
}
