package com.example.cnitz.eatthis;

import java.util.List;
import java.util.ListIterator;
import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Place;
import se.walkercrou.places.Param;
import com.example.cnitz.eatthis.RestaurantDb;

/**
 * Created by sharrell on 9/21/15.

Place object documentation: http://windy1.github.io/google-places-api-java/docs/

 */

public class DownloadRestaurants {

	private static String GPAPIKEY = "";
	// Approx middle of campus: 40.425408, -86.919722
	private static double lat = 40.425408;
	private static double lng = -86.919722;
	// most "campus" restaurants are within 2km  
	private static int radius = 2000;
	

	private List<Place> restaurantList;

	public void GrabRestaurantList() {

		GooglePlaces client = new GooglePlaces(GPAPIKEY);

		restaurantList = client.getNearbyPlaces(lat, lng, radius, GooglePlaces.MAXIMUM_RESULTS, Param.name("type").value("restaurant"));
	}

/*
	public void AddResturauntListToDatabase() {
		RestaurantDb db = new RestaurantDb();
		ListIterator<Place> iter = restaurantList.listIterator();
		while(iter.hasNext()) {
			Place currentPlace = iter.next();
			db.InsertRestaurant((ETPlace) currentPlace);

		}
			
	}	
*/
	
}
