package com.example.cnitz.eatthis;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cnitz.eatthis.TableData.TableInfo;

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
}

