package com.example.cnitz.eatthis;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.content.Context;
import android.content.ContentValues;

/**
 * Created by sharrell on 9/21/15.
 */

public final class RestaurantDb {

	private SQLiteDatabase writableDatabase = null; 
	private SQLiteDatabase readableDatabase = null; 

	private static final String SQL_CREATE_ENTRIES = 
		"CREATE TABLE" + RestaurantEntry.TABLE_NAME + " (" + 
		RestaurantEntry._ID + " INTEGER PRIMARY KEY," + 
		RestaurantEntry.COLUMN_NAME_RESTAURANT_ID + " TEXT," +
		RestaurantEntry.COLUMN_NAME_NAME + " TEXT" +
		" )";

	private static final String SQL_DELETE_ENTRIES =
		"DROP TABLE IF EXISTS " + RestaurantEntry.TABLE_NAME;

	public RestaurantDb() {}

	public abstract class RestaurantEntry implements BaseColumns {
		public static final String TABLE_NAME = "restaurant";
		public static final String COLUMN_NAME_RESTAURANT_ID = "restaurantid";
		public static final String COLUMN_NAME_NAME = "name";
	}

	public class RestaurantDbHelper extends SQLiteOpenHelper {
		// If you change the database schema, you must increment the database version.
		public static final int DATABASE_VERSION = 1;
		public static final String DATABASE_NAME = "Restaurant.db";

		public RestaurantDbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(SQL_CREATE_ENTRIES);
		}
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL(SQL_DELETE_ENTRIES);
			onCreate(db);
		}
	}

	public void InsertRestaurant(String id, String name) {
		RestaurantDbHelper dbHelper;

		if(writableDatabase == null) {
			dbHelper = new RestaurantDbHelper(getContext());
			writableDatabase = dbHelper.getWritableDatabase();
		}

		ContentValues values = new ContentValues();
		values.put(RestaurantEntry.COLUMN_NAME_RESTAURANT_ID, id);
		values.put(RestaurantEntry.COLUMN_NAME_NAME, name);

		long newRowId = writableDatabase.insert(RestaurantEntry.TABLE_NAME,
			 null, values);
		
	}
		
}

