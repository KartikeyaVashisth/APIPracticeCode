package com;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //It will create getters and setters for all the fields.
@NoArgsConstructor
@AllArgsConstructor
public class ProductLombok {

	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;
	
	// Rating class: Inner class
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Rating{
		private double rate;
		private int count;
	}
	
}
