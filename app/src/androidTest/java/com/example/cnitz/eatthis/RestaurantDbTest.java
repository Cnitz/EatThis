package com.example.cnitz.eatthis;


import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;
import android.test.suitebuilder.annotation.MediumTest;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

import se.walkercrou.places.Price;


/**
 * Created by sharrell on 9/21/15.
 */


@RunWith(AndroidJUnit4.class)
@MediumTest
public final class RestaurantDbTest extends AndroidTestCase  {

	RestaurantDb rDB;
	AndroidTestCase aTC = new AndroidTestCase();


    @Before
	public void setUp() {

        Context instrumentationCtx = InstrumentationRegistry.getContext();
        rDB = new RestaurantDb(instrumentationCtx, true);
        rDB.ResetDatabase();
    }

    @Test
    public void testETPlace() {

		ETPlace place = new ETPlace();
		place.setPlaceId("12345");
		place.setName("restaurant");
        place.setAddress("23 Main St, Lafayette, IN 47904");
		// 40.425408, -86.919722
		place.setLatitude(40.425404);
		place.setLongitude(-86.919722);
		place.setRating(3);
		place.setFoodType(1);
		place.setPrice(Price.valueOf("FREE"));

        assertEquals(1, place.getFoodType());
        assertEquals("12345", place.getPlaceId());
		assertEquals("restaurant", place.getName());
		assertEquals("23 Main St, Lafayette, IN 47904", place.getAddress());
		assertEquals(40.425404, place.getLatitude());
		assertEquals(-86.919722, place.getLongitude());
		assertEquals(3.0, place.getRating());
		assertEquals(Price.valueOf("FREE"), place.getPrice());
	}

    @Test
    public void testInsertRestaurant() {
        ETPlace place = new ETPlace();
        place.setPlaceId("12345");
        place.setName("restaurant");
        place.setAddress("23 Main St, Lafayette, IN 47904");
        // 40.425408, -86.919722
        place.setLatitude(40.425404);
        place.setLongitude(-86.919722);
        place.setRating(3.0);
        place.setFoodType(1);
        place.setPrice(Price.valueOf("FREE"));

        assertTrue(rDB.InsertRestaurant(place) != -1);


        List<ETPlace> places = rDB.GetAllRestaurants();

        assertEquals("12345", places.get(0).getPlaceId());
        assertEquals("restaurant", places.get(0).getName());

	}

    @Test
    public void testCreateExampleData() {
        rDB.CreateExampleData();

        List<ETPlace> places = rDB.GetAllRestaurants();

        assertEquals(10, places.size());
        assertEquals("123450", places.get(0).getPlaceId());
        assertEquals("123459", places.get(9).getPlaceId());

    }

    @Test
    public void testGetResturaunt() {
        rDB.CreateExampleData();

        List<ETPlace> places = rDB.GetRestaurants(1, null, null);
        assertEquals(2, places.size());
        assertEquals(places.get(0).getFoodType(), 1);

        places = rDB.GetRestaurants(null, 3.0, null);
        assertEquals(1, places.size());
        assertEquals(places.get(0).getRating(), 3.0);

        places = rDB.GetRestaurants(null, null, "FREE");
        assertEquals(5, places.size());
        assertEquals(places.get(0).getPrice(),Price.valueOf("FREE"));

    }

}
