package com.example.cnitz.eatthis;

import se.walkercrou.places.Place;
import se.walkercrou.places.Price;

/**
 * Created by sharrell on 9/21/15.

Google Place Java API documentation: http://windy1.github.io/google-places-api-java/docs/
Place object doc: http://windy1.github.io/google-places-api-java/docs/se/walkercrou/places/Place.html
Price enum object doc: http://windy1.github.io/google-places-api-java/docs/se/walkercrou/places/Price.html


 */

public class ETPlace extends Place {

	// Added functionality

	public Integer foodType;

	public Integer getFoodType() {
		return foodType;
	}

	public Place setFoodType(Integer foodType) {
		this.foodType = foodType;
		return this;
	}

	// Override because of protected keyword
	public double lat;
	public double lng;
	public String name;
	public String addr;
	public String placeId;
	public double rating;
	public Price price;


	public Place setLatitude(double lat) {
	    this.lat = lat;
	    return this;
	}

	public Place setLongitude(double lon) {
	    this.lng = lon;
	    return this;
	}

	public Place setName(String name) {
	    this.name = name;
	    return this;
	}

	public Place setAddress(String addr) {
	    this.addr = addr;
	    return this;
	}

	public Place setPlaceId(String placeId) {
	    this.placeId = placeId;
	    return this;
	}

	public Place setRating(double rating) {
	    this.rating = rating;
	    return this;
	}

	public Place setPrice(Price price) {
	    this.price = price;
	    return this;
	}

	
}
