package POSTAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;

public class BookingAuthTest {

	@Test
	public void getBookingAuthTokenTest_With_Json_String() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON) //Passing String like below in body is not a good practice, bcoz what if we have a huge body. Code looks ugly as well.
			.body("{\n" 
					+ "    \"username\" : \"admin\",\n"
					+ "    \"password\" : \"password123\"\n"
					+ "}")
			.when()
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
					
			
		System.out.println(tokenId);	
		Assert.assertNotNull(tokenId);
		
	}	
	
	@Test
	public void getBookingAuthTokenTest_With_JSON_File() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		String tokenId = given()
			.contentType(ContentType.JSON)
			.body(new File("./src/test/resources/data/basicauth.json")) //Supplying .json file in body() method. Now, whenever we need to create files other than .java, keep them under resources folder. Go to Project > Right click > Source folder > src/test/resources
			.when() //continuing above comment, Also, whenever we need to create a new file, first create a new folder under that create a new file like how we create .java class under a package.
				.post("/auth")
					.then()
						.assertThat()
							.statusCode(200)
								.extract()
									.path("token");
					
			
		System.out.println(tokenId);	
		Assert.assertNotNull(tokenId);
		
		
	}
}
