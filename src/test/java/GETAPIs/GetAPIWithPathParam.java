package GETAPIs;

import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

public class GetAPIWithPathParam {
	
	//query vs path
	//<k,v>    <anykey, value>
	//?         /
	
	@Test
	public void getCircuitDataAPIWith_YearTest() {
		
		RestAssured.baseURI = "http://ergast.com";
		
		given().log().all()
			.pathParam("year", "2016") //Note: In POSTMAN request, we can give the path parameter by using ':' i.e. like http://ergast.com/api/f1/:year/circuits.json
				.when().log().all()
					.get("/api/f1/{year}/circuits.json") //In RestAssured we will use like {year} and it will take the value 2016 automatically as mentioned in .pathParam
						.then()
							.assertThat()
								.statusCode(200)
									.and()
										.body("MRData.CircuitTable.season", equalTo("2017"))
											.and()
												.body("MRData.CircuitTable.Circuits.circuitId", hasSize(20));
		
	}
	
	//Same as above but with DataProvider this time
		
	@DataProvider
	public Object[][] getCircuitYearDataProvider() {
		
		return new Object[][] {
			
			{"2016", 21},
			{"2017", 20},
			{"1966", 9},
			{"2023", 22}
		};
	}
	
	@Test(dataProvider = "getCircuitYearDataProvider")
	public void getCircuitDataAPIWith_Year_DataProvider(String seasonYear, int totalCircuits) {
		
		RestAssured.baseURI = "http://ergast.com";
		
		given().log().all()
			.pathParam("year", seasonYear)
				.when().log().all()
					.get("/api/f1/{year}/circuits.json")
						.then()
							.assertThat()
								.statusCode(200)
									.and()
										.body("MRData.CircuitTable.season", equalTo(seasonYear))
											.and()
												.body("MRData.CircuitTable.Circuits.circuitId", hasSize(totalCircuits));
		
	}
}
