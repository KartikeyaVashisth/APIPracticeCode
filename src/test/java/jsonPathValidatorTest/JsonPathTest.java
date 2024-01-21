package jsonPathValidatorTest;

import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.List;
import java.util.Map;

public class JsonPathTest {

	@Test
	public void getCircuitDataAPIWith_YearTest() {
		
		RestAssured.baseURI = "http://ergast.com";
		Response response = given().log().all().when().log().all().get("/api/f1/2017/circuits.json");

		//Extracting the response body as a String.
		String jsonResponse = response.asString(); //Need to convert the response to the String with the help of asString() method.
		System.out.println(jsonResponse);

		//This jsonPath library is designed to fetch complex to complex information with help of the JSONPath expressions known as JQL(Json Query Language)
		int totalCircuits = JsonPath.read(jsonResponse, "$.MRData.CircuitTable.Circuits.length()"); //This JsonPath is coming from Jayway(JsonPath library) in pom.xml.
		//The above read()method cannot understand, what we are going to return. By default, it gives Object. We need to give appropriate datatype because we will be aware of the return datatype.
		System.out.println(totalCircuits);

		List<String> countryList = JsonPath.read(jsonResponse, "$..Circuits..country");
		System.out.println(countryList.size());
		System.out.println(countryList);

	}
	
	@Test
	public void getProductTest() {
	RestAssured.baseURI = "https://fakestoreapi.com";

	Response response = given().when().get("/products");

	String jsonResponse = response.asString();
	System.out.println(jsonResponse);
	
	List<Float> rateLessThanThree = JsonPath.read(jsonResponse, "$[?(@.rating.rate<3)].rating.rate"); //In generics of List<generics>, it's always ClassName. We cannot write primitive data types.
	System.out.println(rateLessThanThree);
	
	// with two attributes
	List<Map<String, Object>> jewellryList = JsonPath.read(jsonResponse, "$[?(@.category == 'jewelery')].[\"title\",\"price\"]"); //Anytime when we are fetching two or three values, it will be stored in a List of Map<>
	//The above one is Map<String, Object> because title and price are Keys which will be in String always but their values are not sure whether if it will be int or float or String so we used as Object.
	System.out.println(jewellryList);
	
	for(Map<String, Object> product: jewellryList) {
		String title = (String) product.get("title");
		Object price = product.get("price");

		System.out.println("title : " + title);
		System.out.println("price : " + price);
		System.out.println("--------");

	// with three attributes
		//$[?(@.category == 'jewelery')].[\"id\",\"image\", \"title\"], Here [?(@.category == 'jewelery')] part is query and whatever you want to fetch, we just need to pass keys afterwards like [\"id\",\"image\", \"title\"]
		List<Map<String, Object>> jewellryList2 = JsonPath.read(jsonResponse,"$[?(@.category == 'jewelery')].[\"id\",\"image\", \"title\"]");
		System.out.println(jewellryList2);

		for(Map<String, Object> product2: jewellryList2) {
			Integer id = (Integer) product2.get("id");
			String image = (String) product2.get("image");
			String title2 = (String) product.get("title");

			System.out.println("image : " + image);
			System.out.println("id : " + id);	
			System.out.println("title : " + title2);

		}

	}




	}	
}
 