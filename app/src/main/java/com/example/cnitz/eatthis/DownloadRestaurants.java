package com.example.cnitz.eatthis;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

/**
 * Created by sharrell on 9/21/15.

Place object documentation: http://windy1.github.io/google-places-api-java/docs/

 */

public class DownloadRestaurants extends AsyncTask<Void, Void, Integer> {

	private static String GPAPIKEY = "";
	// Approx middle of campus: 40.425408, -86.919722
	private static double lat = 40.425408;
	private static double lng = -86.919722;
	// most "campus" restaurants are within 2km  
	private static int radius = 2000;
    private List<Place> restaurantList;

    private Context mContext;
    public DownloadRestaurants (Context context){
        mContext = context;
    }

    protected Integer doInBackground(Void... params) {

        return new Integer(1);
    }

    protected void onProgressUpdate(Void... params) {

    }

    protected void onPostExecute(Integer returnCode) {

    }


	public void GrabRestaurantList() {

		GooglePlaces client = new GooglePlaces(GPAPIKEY);

        RestaurantDb rDB = new RestaurantDb(mContext);

		restaurantList = client.getNearbyPlaces(lat, lng, radius, GooglePlaces.MAXIMUM_RESULTS, Param.name("type").value("restaurant"));
		for (Place resturaunt : restaurantList) {
          rDB.InsertRestaurant((ETPlace)resturaunt);
		}
	}

	
}
