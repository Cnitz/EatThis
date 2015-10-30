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

	public void ETplace() { placeId = "2"; }
	public void Place() { }

	private int foodType;

	public int getFoodType() {
		return foodType;
	}

	public Place setFoodType(Integer foodType) {
		this.foodType = foodType;
		return this;
	}

	// Override because of protected keyword
	private double lat;
	private double lng;
	private String name;
	private String addr;
	private String placeId;
	private double rating;
	private Price price;


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

	public Place setPlaceId(String placeIds) {
	    this.placeId = placeIds;
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

    public String getPlaceId() {
        return placeId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return addr;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lng;
    }

    public double getRating() {
        return rating;
    }

    public Price getPrice() {
        return price;
    }

	@Override
	public String toString() {
		return name;
	}
}
