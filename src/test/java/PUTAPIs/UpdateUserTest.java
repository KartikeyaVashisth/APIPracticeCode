package PUTAPIs;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class UpdateUserTest {

	//create user - POST --> userId
	//update user - PUT --> /userId
	
	public static String getRandomEmailId() {
		return "apiautomation"+System.currentTimeMillis()+"@mail.com";
	}
	
	@Test
	public void updateUserTest() {
		RestAssured.baseURI = "https://gorest.co.in";

		User user = new User("Sunny", getRandomEmailId(), "male", "inactive");

		//1. POST - Create User
		Response response = RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.body(user)
				.when()
				.post("/public/v2/users");

		Integer userId = response.jsonPath().get("id"); //Another Way --> Integer userId2 = response.then().extract().path("id");
		System.out.println("user id : " + userId);
//		System.out.println("user id : " + userId2);

		System.out.println("-------------------------");
		
		//update the existing user:
		user.setName("Kartikeya"); //Using the setter method of the User class to update the existing user.
		user.setStatus("active");
		//Note: Never use Builder pattern in the PUT call as it will create a new user instead of updating. Always user setters() method to update the values.
		
		//2. PUT - Update User
				RestAssured.given().log().all()
				.contentType(ContentType.JSON)
				.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.body(user)
				.when()
					.put("/public/v2/users/"+userId)//patch will also work. PUT means entire object that we are passing whereas PATCH means only partial information that we really want to update. PATCH is idempotent because it will NOT create a new entry in the database whereas PUT is not idempotent bcoz it will create a new entry in DB.  
					.then().log().all()
					.assertThat()
						.statusCode(200)
							.and()
								.assertThat()
									.body("id", equalTo(userId))
										.and()
											.body("name", equalTo(user.getName()))
												.and()
													.body("status", equalTo(user.getStatus()));
	}
}
