package com.pet.api;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetLombok {

	private Integer id;
	private Category category; //Whenever we have a json attribute named category under which there are key and value pair, we need to create a inner class of it.
	private String name;
	private List<String> photoUrls; //Wherever there is Json array, use List.
	private List<Tag> tags; //Here, we have a json attribute named tags which has a array under which there are key and value pairs.
	private String status;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Category {
		private Integer id;
		private String name;
	}
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class Tag {
		private Integer id;
		private String name;
	}
}
