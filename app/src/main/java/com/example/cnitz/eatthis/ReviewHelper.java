package com.example.cnitz.eatthis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cnitz.eatthis.TableData.TableInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Cnitz on 10/28/15.
 */
public class ReviewHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    public String CREATE_QUERY = "CREATE TABLE " + TableInfo.TABLE_NAME + "( "
            + TableInfo.COLUMN_NAME + " TEXT, "
            + TableInfo.COLUMN_PRICE + " REAL, "
            + TableInfo.COLUMN_MENU_ITEMS + " TEXT, "
            + TableInfo.COLUMN_SUMMARY + " TEXT, "
            + TableInfo.COLUMN_RATING + " TEXT, "
            + TableInfo.COLUMN_DATE + " TEXT "
            + ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME;

    public ReviewHelper(Context ctx) {
        super(ctx, TableInfo.DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Review Handler", "Database Created");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Review Handler", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public long addReview(ReviewHelper rh, Review review) {
        SQLiteDatabase db = rh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableInfo.COLUMN_NAME, review.getName());
        cv.put(TableInfo.COLUMN_PRICE, review.getPrice());
        cv.put(TableInfo.COLUMN_MENU_ITEMS, review.getMenuItems());
        cv.put(TableInfo.COLUMN_SUMMARY, review.getSummary());
        cv.put(TableInfo.COLUMN_RATING, review.getRating());
        cv.put(TableInfo.COLUMN_DATE, review.getDate());


        long k = db.insert(TableInfo.TABLE_NAME, null, cv);

        return k;
    }

    public String GET_ALL_QUERY = "SELECT * FROM " + TableInfo.TABLE_NAME;

    public List<Review> getAllReviews(ReviewHelper rh){
        SQLiteDatabase db = rh.getReadableDatabase();
        List<Review> rl = new ArrayList<Review>();
        Cursor c = db.rawQuery(GET_ALL_QUERY, null);

        if(c.moveToFirst()){

            while(c.isAfterLast() == false){
                Review review = getReviewFromCursor(c);

                rl.add(review);
                c.moveToNext();
            }
        }
        return rl;

    }

    @NonNull
    private Review getReviewFromCursor(Cursor c) {
        Review review = new Review();
        review.setId(c.getInt(c.getColumnIndex("id")));
        review.setName(c.getString(c.getColumnIndex(TableInfo.COLUMN_NAME)));
        review.setPrice(c.getDouble(c.getColumnIndex(TableInfo.COLUMN_PRICE)));
        review.setMenuItems(c.getString(c.getColumnIndex(TableInfo.COLUMN_MENU_ITEMS)));
        review.setSummary(c.getString(c.getColumnIndex(TableInfo.COLUMN_SUMMARY)));
        review.setRating(c.getInt(c.getColumnIndex(TableInfo.COLUMN_RATING)));
        review.setDate(c.getString(c.getColumnIndex(TableInfo.COLUMN_DATE)));
        return review;
    }
}

