package com.example.cnitz.eatthis;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import se.walkercrou.places.Price;


/**
 * Created by sharrell on 9/21/15.
 */

public final class RestaurantDb  {

	private SQLiteDatabase writableDatabase = null;
	private Context mContext;
    private String databaseName = "Restaurant.db";

	private static final String SQL_CREATE_ENTRIES = 
		"CREATE TABLE " + RestaurantEntry.TABLE_NAME + " (" +
		RestaurantEntry._ID + " INTEGER PRIMARY KEY," +
		RestaurantEntry.COLUMN_RESTAURANT_UNIQUE + " TEXT," +
        RestaurantEntry.COLUMN_NAME + " TEXT," +
		RestaurantEntry.COLUMN_ADDRESS + " TEXT," +
		RestaurantEntry.COLUMN_LAT + " REAL," +
		RestaurantEntry.COLUMN_LONG + " REAL," +
		RestaurantEntry.COLUMN_FOOD_TYPE + " INTEGER," +
		RestaurantEntry.COLUMN_RATING + " REAL," +
		RestaurantEntry.COLUMN_PRICE_LEVEL + " INTEGER" +
			" )";

	private static final String SQL_DELETE_ENTRIES =
		"DROP TABLE IF EXISTS " + RestaurantEntry.TABLE_NAME;

	public RestaurantDb(Context lContext) {
		mContext = lContext;
	}

    public RestaurantDb(Context lContext, boolean test) {
        if(test) { databaseName = null; }
        mContext = lContext;
    }


	public abstract class RestaurantEntry implements BaseColumns {
		public static final String TABLE_NAME = "restaurant";
		public static final String COLUMN_RESTAURANT_UNIQUE = "restaurantunique";
		public static final String COLUMN_NAME = "restname";
		public static final String COLUMN_ADDRESS = "address";
		public static final String COLUMN_LAT = "lat"; // double
		public static final String COLUMN_LONG = "lng"; //double
		public static final String COLUMN_FOOD_TYPE = "foodtype"; // int
		public static final String COLUMN_RATING = "rating"; //double
		public static final String COLUMN_PRICE_LEVEL = "pricelevel"; //int
	}


	public class RestaurantDbHelper extends SQLiteOpenHelper {
		// If you change the database schema, you must increment the database version.
		public static final int DATABASE_VERSION = 1;



		public RestaurantDbHelper(Context context) {
            super(context, databaseName, null, DATABASE_VERSION);
		}


		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_ENTRIES);
		}
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(SQL_DELETE_ENTRIES);
			onCreate(db);
		}
	}

	// Place object doc: http://windy1.github.io/google-places-api-java/docs/se/walkercrou/places/Place.html

	public long InsertRestaurant(ETPlace place) {
		RestaurantDbHelper dbHelper;

		if(writableDatabase == null) {
			dbHelper = new RestaurantDbHelper(mContext);

			writableDatabase = dbHelper.getWritableDatabase();
		}

		ContentValues values = new ContentValues();
		values.put(RestaurantEntry.COLUMN_RESTAURANT_UNIQUE, place.getPlaceId());
		values.put(RestaurantEntry.COLUMN_NAME, place.getName());
		values.put(RestaurantEntry.COLUMN_ADDRESS, place.getAddress());
		values.put(RestaurantEntry.COLUMN_LAT, place.getLatitude());
		values.put(RestaurantEntry.COLUMN_LONG, place.getLongitude());
		values.put(RestaurantEntry.COLUMN_FOOD_TYPE, place.getFoodType());
		values.put(RestaurantEntry.COLUMN_RATING, place.getRating());
		values.put(RestaurantEntry.COLUMN_PRICE_LEVEL, place.getPrice().ordinal());
	
		return writableDatabase.insertOrThrow(RestaurantEntry.TABLE_NAME, null, values);

	}

	
	// grab based on food type, rating, price
	// unittest
	public List<ETPlace> GetRestaurants(Integer foodType, Double rating, String price) {

		RestaurantDbHelper dbHelper;
		List<ETPlace> restaurants = new ArrayList<ETPlace>();
		ETPlace place;
		String[] restaurantColumns = {
			RestaurantEntry.COLUMN_RESTAURANT_UNIQUE,
			RestaurantEntry.COLUMN_ADDRESS,
	        RestaurantEntry.COLUMN_NAME,
			RestaurantEntry.COLUMN_LAT,
			RestaurantEntry.COLUMN_LONG,
			RestaurantEntry.COLUMN_FOOD_TYPE,
			RestaurantEntry.COLUMN_RATING,
			RestaurantEntry.COLUMN_PRICE_LEVEL
		};
		String selection = "";
		List<String> selectionColumns = new ArrayList<String>();
		int selections = 0;


		if(foodType != null) {
			selections++;
			selection = selection + RestaurantEntry.COLUMN_FOOD_TYPE + "=" + foodType.toString();
		}
		if(rating != null) {
			if(selections > 0) selection = selection + " AND ";
			selections++;
			selection = selection + RestaurantEntry.COLUMN_RATING + "=" + rating.toString();
		}
		if(price != null) {
			if(selections > 0) selection = selection + " AND ";
			selection = selection + RestaurantEntry.COLUMN_PRICE_LEVEL + "=" + Price.valueOf(price).ordinal();

		}


		if(writableDatabase == null) {
			dbHelper = new RestaurantDbHelper(mContext);
			writableDatabase = dbHelper.getWritableDatabase();
		}

        //Cursor cursor = writableDatabase.rawQuery("SELECT * FROM " + RestaurantEntry.TABLE_NAME, null);
		Cursor cursor = writableDatabase.query(
    			RestaurantEntry.TABLE_NAME,  // The table to query
    			restaurantColumns,           // The columns to return
    			selection,                        // The columns for the WHERE clause
    			null,                        // The values for the WHERE clause
			null,                        // don't group the rows
			null,                        // don't filter by row groups
			null                         // The sort order
        );
        Log.d("Testing Info", "row count: " + cursor.getCount());

		if (cursor.getCount() == 0) {
            return null;
		}


		cursor.moveToFirst();


		do {
			place = new ETPlace();

			place.setPlaceId(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    RestaurantEntry.COLUMN_RESTAURANT_UNIQUE)));
			place.setName(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    RestaurantEntry.COLUMN_NAME)));
			place.setAddress(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    RestaurantEntry.COLUMN_ADDRESS)));
			place.setLatitude(
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    RestaurantEntry.COLUMN_LAT)));
			place.setLongitude(
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    RestaurantEntry.COLUMN_LONG)));
			place.setRating(
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    RestaurantEntry.COLUMN_RATING)));
			place.setFoodType(
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    RestaurantEntry.COLUMN_FOOD_TYPE)));
			place.setPrice(
				Price.values()[
					cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    RestaurantEntry.COLUMN_PRICE_LEVEL))]);
			restaurants.add(place);

		} while(cursor.moveToNext()); 


		return restaurants;

	}
	
	public List<ETPlace> GetAllRestaurants() {
		return GetRestaurants(null, null, null);
		
	}

	public void ResetDatabase() {
		RestaurantDbHelper dbHelper;
		if(writableDatabase == null) {
			dbHelper = new RestaurantDbHelper(mContext);

			writableDatabase = dbHelper.getWritableDatabase();
		}
		writableDatabase.execSQL(SQL_DELETE_ENTRIES);
		writableDatabase.execSQL(SQL_CREATE_ENTRIES);
	}

	public void CreateExampleData() {

		ResetDatabase();

		ETPlace place;
		int i;
        String[] restNames = new String[10];
        Double[] lats = new Double[10];
        Double[] longs = new Double[10];
        int[] foodtypes = new int[10];




        restNames[0] = "Qdoba Mexican Grill";
        lats[0] = 40.425529;
        longs[0] = -86.908164;
        foodtypes[0] = 2;

        restNames[1] = "Fiesta Mexican Grill";
        lats[1] = 40.425089;
        longs[1] = -86.906689;
        foodtypes[1] = 2;

        restNames[2] = "Chipotle Mexican Grill";
        lats[2] = 40.424332;
        longs[2] = -86.907106;
        foodtypes[2] = 2;

        restNames[3] = "Taco Bell";
        lats[3] = 40.423624;
        longs[3] = -86.907523;
        foodtypes[3] = 2;

        restNames[4] = "Rice Cafe";
        lats[4] = 40.423312;
        longs[4] = -86.908981;
        foodtypes[4] = 0;

        restNames[5] = "Ahz";
        lats[5] = 40.423225;
        longs[5] = -86.908181;
        foodtypes[5] = 0;

        restNames[6] = "Maru Sushi";
        lats[6] = 40.424506;
        longs[6] = -86.907115;
        foodtypes[6] = 0;

        restNames[7] = "Khana Khazana Indian Grill";
        lats[7] = 40.424506;
        longs[7] = -86.907834;
        foodtypes[7] = 0;

        restNames[8] = "Mad Mushroom Pizza";
        lats[8] = 40.424751;
        longs[8] = -86.908975;
        foodtypes[8] = 1;

        restNames[9] = "HotBox Pizza";
        lats[9] = 40.423979;
        longs[9] = -86.908083;
        foodtypes[9] = 1;




		for(i = 0; i < 10; i++) {
			place = new ETPlace();
			place.setPlaceId("12345" + i);
			place.setName(restNames[i]);
			place.setAddress(i + "23 Main St, Lafayette, IN 47904");
			// 40.425408, -86.919722
			place.setLatitude(lats[i]);
			place.setLongitude(longs[i]);
			place.setRating((i * .1) + 3.0);
			place.setFoodType(foodtypes[i]);
            if(i % 2 == 0) place.setPrice(Price.valueOf("FREE"));
            else place.setPrice(Price.valueOf("INEXPENSIVE"));

			InsertRestaurant(place);
		}
	}

}

