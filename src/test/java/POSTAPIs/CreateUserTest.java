package POSTAPIs;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class CreateUserTest {
	
	//POST -- add a user -- user id = 123 -- assert(201, body)
	//GET --> get a user --> /users/123 -- 200 -- userid=123
	
	@Test
	public void addUserTest() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		int userId = given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("./src/test/resources/data/adduser.json"))
		.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
		.when()
		.post("/public/v2/users/")
		.then().log().all()
		.assertThat()
			.statusCode(201)
			 .and()
			  .body("name", equalTo("Kartikeya"))
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
								.body("id", equalTo(userId));
	
	}

}
