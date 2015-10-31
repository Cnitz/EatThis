package com.example.cnitz.eatthis;

import android.provider.BaseColumns;

/**
 * Created by Cnitz on 10/28/15.
 */
public class SchedData {

    public SchedData(){

    }

    public abstract class TableInfo implements BaseColumns{

        public static final String TABLE_NAME = "schedule";
        public static final String DATABASE_NAME = "schedule";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_STARTTIME = "starttime"; //int
        public static final String COLUMN_ENDTIME = "endtime";     //int
        public static final String COLUMN_LOCATION = "location";
        //Days of the week all seperate to help with sorting
        public static final String COLUMN_MONDAY = "Monday";
        public static final String COLUMN_TUESDAY = "Tuesday";
        public static final String COLUMN_WEDNESDAY = "Wednesday";
        public static final String COLUMN_THURSDAY = "Thursday";
        public static final String COLUMN_FRIDAY = "Friday";


    }

}
