package model; 

 
public class Restaurant 
{
	private int restaurantid;
 	private String name;
	private String cuisinetype;
	private String address;
	private int rating;
	private String deliverytime;
	private String isactive;
	private String image;
	
	 
	public Restaurant() {
		super();
	}
  
	public Restaurant(int restaurantid, String name, String cuisinetype, String address, int rating,
			String deliverytime, String isactive, String image) {
		super();
		this.restaurantid = restaurantid;
		this.name = name;
		this.cuisinetype = cuisinetype;
		this.address = address;
		this.rating = rating;
		this.deliverytime = deliverytime;
		this.isactive = isactive;
		this.image = image;
	}


	public Restaurant(String name, String cuisinetype, String address, int rating, String deliverytime,
			String isactive, String image) {
		super();
		this.name = name;
		this.cuisinetype = cuisinetype;
		this.address = address;
		this.rating = rating;
		this.deliverytime = deliverytime;
		this.isactive = isactive;
		this.image = image;
	}


	

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCuisinetype() {
		return cuisinetype;
	}

	public void setCuisinetype(String cuisinetype) {
		this.cuisinetype = cuisinetype;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDeliverytime() {
		return deliverytime;
	}

	public void setDeliverytime(String deliverytime) {
		this.deliverytime = deliverytime;
	}

	public String getIsactive() {
		return isactive;
	}

	public void setIsactive(String isactive) {
		this.isactive = isactive;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return  restaurantid + " " +
				name + "  " +
				cuisinetype + " " +
				address + " " + 
				rating + " " + 
				deliverytime + " "  + 
				isactive +  " "  + 
				image;
	}
 
	 
	 
}
