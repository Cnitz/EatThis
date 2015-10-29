package com.example.cnitz.eatthis;

import android.provider.BaseColumns;

/**
 * Created by Cnitz on 10/28/15.
 */
public class TableData {

    public TableData(){

    }

    public abstract class TableInfo implements BaseColumns{

        public static final String TABLE_NAME = "review";
        public static final String DATABASE_NAME = "reviews";

        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PRICE = "price"; //double
        public static final String COLUMN_MENU_ITEMS = "menuitems";
        public static final String COLUMN_SUMMARY = "summary";
        public static final String COLUMN_RATING = "rating"; //int
        public static final String COLUMN_DATE = "date";


    }

}
