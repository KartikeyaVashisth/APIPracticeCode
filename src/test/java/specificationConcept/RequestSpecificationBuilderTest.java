package specificationConcept;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RequestSpecificationBuilderTest {
	
	public static RequestSpecification user_req_spec() {

		RequestSpecification requestSpec = new RequestSpecBuilder()
				.setBaseUri("https://gorest.co.in")
				.setContentType(ContentType.JSON)
				.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.build();

		return requestSpec;
	}

	@Test
	public void getUser_With_Request_Spec() {
		
//		RequestSpecification requestSpec = new RequestSpecBuilder()
//			.setBaseUri("https://gorest.co.in")
//			.setContentType(ContentType.JSON)
//			.addHeader("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
//			.build(); //Here .build() method will return RequestSpecification reference. Hence, we have used RequestSpecification and it is not because of TopCasting.
					
		RestAssured.given().log().all()
				.spec(user_req_spec())
					.get("/public/v2/users")
						.then()
							.statusCode(200);			
	}
	
	@Test
	public void getUser_With_Param_Request_Spec() {
		RestAssured.given().log().all()
		.queryParam("name", "Kartik").queryParam("status", "active")
		.spec(user_req_spec())
		.get("/public/v2/users")
		.then()
		.statusCode(200);
	}	

}
