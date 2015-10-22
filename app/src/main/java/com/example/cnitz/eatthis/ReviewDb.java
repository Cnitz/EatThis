package com.example.cnitz.eatthis;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import java.util.List;
import java.util.ArrayList;
import android.app.Activity;
import android.database.Cursor;
import android.content.ContentValues;

import java.util.Date;

/**
 * Created by Cnitz on 10/06/15.
 */
public final class ReviewDb extends Activity{

    private SQLiteDatabase writableDatabase = null;
    private SQLiteDatabase readableDatabase = null;


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE" + ReviewEntry.TABLE_NAME + " (" +
                    ReviewEntry._ID + " INTEGER PRIMARY KEY," +
                    ReviewEntry.COLUMN_RESTAURANT_ID + " TEXT," +
                    ReviewEntry.COLUMN_NAME + " TEXT," +
                    ReviewEntry.COLUMN_PRICE + " REAL," +
                    ReviewEntry.COLUMN_MENU_ITEMS + " TEXT," +
                    ReviewEntry.COLUMN_SUMMARY + " TEXT" +
                    ReviewEntry.COLUMN_RATING + " INTEGER," +
                    ReviewEntry.COLUMN_DATE + "TEXT" +
                    " )";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + ReviewEntry.TABLE_NAME;

    public ReviewDb() {}

    public abstract class ReviewEntry implements BaseColumns {
        public static final String TABLE_NAME = "review";
        public static final String COLUMN_RESTAURANT_ID = "restaurantid";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price";
        public static final String COLUMN_MENU_ITEMS = "menuitems";
        public static final String COLUMN_SUMMARY = "summary";
        public static final String COLUMN_RATING = "rating"; //int
        public static final String COLUMN_DATE = "date";
    }

    public class ReviewDbHelper extends SQLiteOpenHelper {
        // If you change the database schema, you must increment the database version.
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "Review.db";

        public ReviewDbHelper(Context context) {
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


    public void InsertRestaurant(Review review) {
        ReviewDbHelper dbHelper;

        if(writableDatabase == null) {
            dbHelper = new ReviewDbHelper(getApplicationContext());
            writableDatabase = dbHelper.getWritableDatabase();
        }
//TODO: possibly add to string methods for data entry
        ContentValues values = new ContentValues();
        values.put(ReviewEntry.COLUMN_RESTAURANT_ID, review.getReviewId());
        values.put(ReviewEntry.COLUMN_NAME, review.getName());
        values.put(ReviewEntry.COLUMN_PRICE, review.getPrice());
        values.put(ReviewEntry.COLUMN_MENU_ITEMS, review.getMenuItems());
        values.put(ReviewEntry.COLUMN_SUMMARY, review.getSummary());
        values.put(ReviewEntry.COLUMN_RATING, review.getRating());
        values.put(ReviewEntry.COLUMN_DATE, review.getDate());

        long newRowId = writableDatabase.insert(ReviewEntry.TABLE_NAME,
                null, values);
    }

    public List<Review> GetReviews() {
        ReviewDbHelper dbHelper;
        List<Review> reviews = new ArrayList<Review>();;
        Review review;
        String[] restaurantColumns = {
                ReviewEntry.COLUMN_RESTAURANT_ID,
                ReviewEntry.COLUMN_NAME,
                ReviewEntry.COLUMN_PRICE,
                ReviewEntry.COLUMN_MENU_ITEMS,
                ReviewEntry.COLUMN_SUMMARY,
                ReviewEntry.COLUMN_RATING,
                ReviewEntry.COLUMN_DATE
        };
        String selection = "";
        List<String> selectionColumns;
        int selections;
/*
		if(foodType != null) {
			selections;
			selection = selection + RestaurantEntry.COLUMN_FOOD_TYPE + "=?";
			selectionColumns.add(foodType.toString);
		}
		if(foodType != null) {
			selections;
			selection = selection + RestaurantEntry.COLUMN_FOOD_TYPE + "=?";
			selectionColumns.add(foodType.toString);
		}
		if(foodType != null) {
			selections;
			selection = selection + RestaurantEntry.COLUMN_FOOD_TYPE + "=?";
			selectionColumns.add(foodType.toString);
		}


		rating != null
		price != null) {


		}
*/

        if(readableDatabase == null) {
            dbHelper = new ReviewDbHelper(getApplicationContext());
            readableDatabase = dbHelper.getReadableDatabase();
        }

        Cursor cursor = readableDatabase.query(
                ReviewEntry.TABLE_NAME,  // The table to query
                restaurantColumns,           // The columns to return
                null,                        // The columns for the WHERE clause
                null,                        // The values for the WHERE clause
                null,                        // don't group the rows
                null,                        // don't filter by row groups
                null                         // The sort order
        );
        cursor.moveToFirst();

        do {
            review = new Review();

            review.setReviewId(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    ReviewEntry.COLUMN_RESTAURANT_ID)));
            review.setName(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    ReviewEntry.COLUMN_NAME)));
            review.setPrice(
                    cursor.getDouble(
                            cursor.getColumnIndexOrThrow(
                                    ReviewEntry.COLUMN_PRICE)));
            review.setMenuItems(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    ReviewEntry.COLUMN_MENU_ITEMS)));
            review.setSummary(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    ReviewEntry.COLUMN_SUMMARY)));
            review.setRating(
                    cursor.getInt(
                            cursor.getColumnIndexOrThrow(
                                    ReviewEntry.COLUMN_RATING)));
            review.setDate(
                    cursor.getString(
                            cursor.getColumnIndexOrThrow(
                                    ReviewEntry.COLUMN_DATE)));

            reviews.add(review);

        } while(cursor.moveToNext());

        return reviews;

    }

    public List<Review> GetAllRestaurants() {
        return GetReviews();

    }



}
