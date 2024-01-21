package schemaValidation;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class SchemaValidationTest {

	@Test
	public void addUserSchemaTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1. add user - POST 
		//To Convert the Json to Json Schema there are online tools like https://transform.tools/json-to-json-schema where we can convert Json body to Json schema. Once converted we can store in .json file under src/test/resources folder
		given().log().all()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/data/adduser.json"))
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.when()
			.post("/public/v2/users/")
			.then().log().all()
			.assertThat()
				.statusCode(201)
				.body(matchesJsonSchemaInClasspath("createuserschema.json")); //classpath basically means src/test/resources and not src/test/java or src/main/java
	}
	
	@Test
	public void getUserSchemaTest() {
		RestAssured.baseURI = "https://gorest.co.in";
		
		//1. add user - POST
		given().log().all()
			.contentType(ContentType.JSON)
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
			.when()
			.get("/public/v2/users/")
			.then().log().all()
			.assertThat()
				.statusCode(200)
				.body(matchesJsonSchemaInClasspath("getuserschema.json"));
	}
}	
