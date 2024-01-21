package Pojo;

import java.io.File;

public class Credentials {

	//This is a POJO class. It is created as equivalent to .body(new File("./src/test/resources/data/basicauth.json")). We'll pass object of this class to the .body() method. Hence, Pojo class is used only for the POST and PUT methods where body is required and not for GET call request where request body is not required. 
	//For POJO class, check the original JSON(like we have in basicauth.json) and then make the same keys as the private variables in the POJO class.
	private String username;
	private String password;

	public Credentials(String username, String password) { //Constructor
		this.username = username;
		this.password = password;
	}

	//Getters and setters will be used when we have other parameters in response JSON and we can assert them using getter method.  
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// getter/setter:
}
