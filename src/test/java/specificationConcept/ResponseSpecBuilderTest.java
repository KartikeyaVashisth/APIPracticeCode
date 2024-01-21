package specificationConcept;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ResponseSpecBuilderTest {

	//Response Spec for Status Code 200
	public static ResponseSpecification get_res_spec_200_OK() {
		
		ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "cloudflare")
			.build();
		
		return res_spec_200_ok;
	}
	
	//Response Spec for Status Code 200 with Body
	public static ResponseSpecification get_res_spec_200_OK_With_Body() {
		
		ResponseSpecification res_spec_200_ok = new ResponseSpecBuilder()
			.expectContentType(ContentType.JSON)
			.expectStatusCode(200)
			.expectHeader("Server", "cloudflare")
			.expectBody("$.size()", equalTo(20))
			.expectBody("id", hasSize(10))
			.expectBody("status", hasItem("active"))	
			.build();
		
		return res_spec_200_ok;
	}

	//Response Spec for Status Code 401
	public static ResponseSpecification get_res_spec_401_Auth_Fail() {
		
		ResponseSpecification res_spec_401_AUTH_FAIL = new ResponseSpecBuilder()
			.expectStatusCode(401)
			.expectHeader("Server", "cloudflare")
			.build();
		
		return res_spec_401_AUTH_FAIL;
	}
	
	@Test
	public void get_user_res_200_spec_Test() {
		RestAssured.baseURI = "https://gorest.co.in";
		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_res_spec_200_OK_With_Body()); //Assert the entire response spec that we created.
	}
	
	@Test
	public void get_user_res_401_Auth_Fail_spec_Test() {
		RestAssured.baseURI = "https://gorest.co.in";
		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac699999999")
				.when()
					.get("/public/v2/users")
						.then()
							.assertThat()
								.spec(get_res_spec_401_Auth_Fail());
	}	
	
}
