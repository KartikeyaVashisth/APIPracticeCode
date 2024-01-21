package GETAPIs;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*; //With this no need to write RestAssured.given() or .when() or .then() again and again. We are importing all STATIC methods of RestAssured. So, with this No need to write ClassName.method(); We can use given(), when(), then(), and() directly since we have imported all static methods.
import static org.hamcrest.Matchers.*; // For the assertion methods in response like hasSize(10), equalTo(20), is(notNullValue()), hasItem("Mens Cotton Jacket")

import java.util.List;

public class GETAPIWITHBDD {

	@Test
	public void getProductsTest() {
		
		given().log().all() //log() and all() will give proper logs on console about what is happening. Without them we will not get console logs.
			.when().log().all() //As soon as we write when(), we need to hit the API, be it GET, POST, PUT, DELETE
				.get("https://fakestoreapi.com/products") //As soon as we hit the GET, we will get Response.
					.then().log().all()
						.assertThat() //assertThat() will always be after then() method because once we get response, it's then we do assertion.
							.statusCode(200) //These are Hard Assertions which means if the assertion fails here, it will not proceed to subsequent assertions. RestAssured doesn't support Soft Assertions.
								.and()
									.contentType(ContentType.JSON)
										.and()
											.header("Connection", "keep-alive")
												.and()
													.body("$.size()", equalTo(20)) //In RestAssured, entire body is represented by $
														.and()
															.body("id", is(notNullValue())) //This means collect all IDs from response. No need to write for loop like in Non BDD approach. Major Advantage in RestAssured library.
																.and()
																	.body("title", hasItem("Mens Cotton Jacket")); //This will collect all titles from the response body and check if it has item or not.
					
		//This is actually a builder pattern which RestAssured Library has given it to us.
	}
	
	
	@Test
	public void getUserAPITest() {
		
		RestAssured.baseURI = "https://gorest.co.in";

		given()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().log().all()
					.get("/public/v2/users/")
						.then().log().all()
							.assertThat()
								.statusCode(200)
									.and()
										.contentType(ContentType.JSON)
											.and()
												.body("$.size()", equalTo(10));
	}
	
	@Test
	public void getProductDataAPIWithQueryParamTest() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		given().log().all()
			.queryParam("limit", 5)
				.when().log().all()
					.get("/products")
					.then().log().all()
					.assertThat()
					.statusCode(200)
					.and()
					.contentType(ContentType.JSON);
	}
	
	@Test
	public void getProductDataAPI_With_Extract_Body() {
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response =  given().log().all()
									.queryParam("limit", 5)
											.when().log().all()
													.get("/products");
		
		JsonPath js = response.jsonPath(); //jsonPath() method need to write to get JSON path view of the response body. 
		
		//get the id of the first product:
		int firstProductId = js.getInt("[0].id"); //Since, 0 will be the first index in the JSON array.
		System.out.println("firstProductId = " + firstProductId);
		
		String firstProductTitle = js.getString("[0].title");
		System.out.println("firstProductTitle = " + firstProductTitle );
		
		float price = js.getFloat("[0].price");
		System.out.println("price = " + price);
		
		int count = js.getInt("[0].rating.count");
		System.out.println("count = " + count);
		
	}
	
	@Test
	public void getProductDataAPI_With_Extract_Body_withJSONArray() {
		
		RestAssured.baseURI = "https://fakestoreapi.com";
		
		Response response = given().log().all()
								.queryParam("limit", 5)
									.when().log().all()
										.get("/products");
		
		JsonPath js = response.jsonPath();
		
		List<Integer> idList = js.getList("id");
		List<String> titleList = js.getList("title");
		List<Object> rateList = js.getList("rating.rate");
		List<Integer> countList = js.getList("rating.count");
		
		for(int i=0; i<idList.size(); i++) {
			int id = idList.get(i);
			String title = titleList.get(i);
			Object rate = rateList.get(i);
			int count = countList.get(i);
			
			System.out.println("ID: " + id + " " + "Title: " + title + " " + "Rate: " + rate + " " + "Count: "+ count);
		}
		
		
		Assert.assertTrue(rateList.contains(4.9));
		
	}
	
	@Test
	public void getUserAPI_With_Extract_Body_withJson() {
		
		RestAssured.baseURI = "https://gorest.co.in";

		Response response = given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when()
					.get("/public/v2/users/3571519");
		
		JsonPath js = response.jsonPath();
		
		System.out.println(js.getInt("id"));
		System.out.println(js.getString("email"));

		
	}
	
	@Test
	public void getUserAPI_With_Extract_Body_withJson_Extract() {
		
		RestAssured.baseURI = "https://gorest.co.in";
		
		Response response = given().log().all()
			.header("Authorization", "Bearer e4b8e1f593dc4a731a153c5ec8cc9b8bbb583ae964ce650a741113091b4e2ac6")
				.when().log().all()
					.get("/public/v2/users/3571519")
						.then()
							.extract() //extract() method will be used to extract the values from the response and use the same values in the subsequent requests.
								.response();
		
		int userID = response.path("id");
		String email = response.path("email");				

		
		System.out.println(userID);
		System.out.println(email);
		
	}
		
}
