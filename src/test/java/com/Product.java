package com;

//POJO class where the extracted Json string will be mapped.
//But due to several Json attributes, this class has become super lengthy becoz what if we have more attributes, then accordingly more number of getters and setters, more lengthy.
//Solution is Lombok API, with help of annotations, we can manage our POJO. No need to worry about lengthy variables, getters and setters.
public class Product {

	private int id;
	private String title;
	private double price;
	private String description;
	private String category;
	private String image;
	private Rating rating;

	public Product() { //Default constructor is needed because deserialization happens with help of jackson api and it always check in POJO that due have default constructor or not. If not, it will throw an error.  	 	 	 	
	}

	public Product(int id, String title, double price, String description, String category, String image, Rating rating) {
		this.id = id;
		this.title = title;
		this.price = price;
		this.description = description;
		this.category = category;
		this.image = image;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}
	
	// Rating class: Inner class
	public static class Rating {
		
		private double rate;
		private int count;
		
		public Rating() {
		}

		public Rating(double rate, int count) {
			this.rate = rate;
			this.count = count;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}
		
}
	
	
}
