package com.example.cnitz.eatthis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.cnitz.eatthis.SchedData.TableInfo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Cnitz on 10/28/15.
 */
public class SchedHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 3;

    public String CREATE_QUERY = "CREATE TABLE " + TableInfo.TABLE_NAME + "( "
            + TableInfo.COLUMN_NAME + " TEXT, "
            + TableInfo.COLUMN_LOCATION + " TEXT, "
            + TableInfo.COLUMN_STARTTIME + " TEXT, "
            + TableInfo.COLUMN_ENDTIME + " TEXT, "
            + TableInfo.COLUMN_MONDAY + " TEXT, "
            + TableInfo.COLUMN_TUESDAY + " TEXT, "
            + TableInfo.COLUMN_WEDNESDAY + " TEXT, "
            + TableInfo.COLUMN_THURSDAY + " TEXT, "
            + TableInfo.COLUMN_FRIDAY + " TEXT "
            + ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TableInfo.TABLE_NAME;

    public SchedHelper(Context ctx) {
        super(ctx, TableInfo.DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Sched Handler", "Database Created");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Sched Handler", "Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public long addClass(SchedHelper sh, SchedClass sc) {
        SQLiteDatabase db = sh.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TableInfo.COLUMN_NAME, sc.getName());
        cv.put(TableInfo.COLUMN_LOCATION, sc.getLocation());
        cv.put(TableInfo.COLUMN_STARTTIME, sc.getStartTime());
        cv.put(TableInfo.COLUMN_ENDTIME, sc.getEndTime());
        cv.put(TableInfo.COLUMN_MONDAY, sc.getMonday());
        cv.put(TableInfo.COLUMN_TUESDAY, sc.getTuesday());
        cv.put(TableInfo.COLUMN_WEDNESDAY, sc.getWednesday());
        cv.put(TableInfo.COLUMN_THURSDAY, sc.getThursdays());
        cv.put(TableInfo.COLUMN_FRIDAY, sc.getFridays());


        long k = db.insert(TableInfo.TABLE_NAME, null, cv);

        return k;
    }

    public List<String> getAllClasses(SchedHelper sh) {
        List<String> list = new ArrayList<String>();
        SQLiteDatabase db = sh.getWritableDatabase();
        String selection = "";
        Cursor cursor = db.rawQuery("SELECT * FROM " + TableInfo.TABLE_NAME, null);
        cursor.moveToFirst();
        if (!cursor.isAfterLast()){
            do {
                String s;
                s = cursor.getString(cursor.getColumnIndexOrThrow(TableInfo.COLUMN_NAME));
                list.add(s);
                Log.w("myApp", "read: " + s);
            }
            while (cursor.moveToNext());
    }
        return list;
    }

    public void removeAt(int i, SchedHelper sh){
        SQLiteDatabase db = sh.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM "+TableInfo.TABLE_NAME,null);
        cursor.moveToPosition(i);
        //make the class object to be deleted
        SchedClass sclass = new SchedClass();
        sclass.setName(cursor.getString(cursor.getColumnIndexOrThrow(TableInfo.COLUMN_NAME)));
        sclass.setStartTime(cursor.getString(cursor.getColumnIndexOrThrow(TableInfo.COLUMN_STARTTIME)));
        sclass.setEndTime(cursor.getString(cursor.getColumnIndexOrThrow(TableInfo.COLUMN_ENDTIME)));
        sclass.setLocation(cursor.getString(cursor.getColumnIndexOrThrow(TableInfo.COLUMN_LOCATION)));
       // db.rawQuery("DELETE FROM "+TableInfo.TABLE_NAME+" WHERE "+TableInfo.COLUMN_NAME+" = '"+sclass.getName()+"'"//+"' AND "+TableInfo.COLUMN_STARTTIME+" = '"+sclass.getStartTime()
               // +"' AND "+TableInfo.COLUMN_ENDTIME+" = '"+sclass.getEndTime()+"' AND "+TableInfo.COLUMN_LOCATION+" = '"+sclass.getLocation()+"'" ,null);
        db.delete(TableInfo.TABLE_NAME,TableInfo.COLUMN_NAME+" = '"+sclass.getName()+"'",null);

    }

    public SchedClass getNextClass(SchedHelper sh){
        SchedClass sclass = new SchedClass();
        SQLiteDatabase db = sh.getWritableDatabase();
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        List<SchedClass> list = new ArrayList<SchedClass>();
        Cursor cursor;
        String select = "SELECT * FROM "+TableInfo.TABLE_NAME+" WHERE ";

        if(day == Calendar.TUESDAY){

            select = select+TableInfo.COLUMN_TUESDAY+" = '1'";
        }
        else if(day == Calendar.WEDNESDAY){
            select = select+TableInfo.COLUMN_WEDNESDAY+" = '1'";
        }
        else if(day == Calendar.THURSDAY){
            select = select+TableInfo.COLUMN_THURSDAY+" = '1'";
        }
        else if(day == Calendar.FRIDAY){
            select = select+TableInfo.COLUMN_FRIDAY+" = '1'";
        }
        else {
            select = select+TableInfo.COLUMN_MONDAY+" = '1'";
        }

        cursor = db.rawQuery(select,null);
        if(cursor.isAfterLast()) return null;



        return sclass;
    }

}
