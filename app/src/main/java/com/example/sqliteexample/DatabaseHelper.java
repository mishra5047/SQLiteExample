package com.example.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {
    Context context;

    public static final String DATABASE_NAME = "UserList.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "user_data";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME= "item_name";
    public static final String COLUMN_DATE = "item_date";

    DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String query = "CREATE TABLE " + TABLE_NAME +
                " ( " + COLUMN_ID + " INTEGER PRIMARY KEY , " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DATE + " TEXT " + ")";

        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addData(int id, String name, String date){
       SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ID, id);
        cv.put(COLUMN_NAME, name);
        cv.put(COLUMN_DATE, date);

        long res = db.insert(TABLE_NAME, null, cv);

        if(res == -1){
            Toast.makeText(context, "Failure", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show();
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "item_name=?", new String[]{row_id});

        if (result == -1) {

        }
        else{

        }
    }

    public long noItems(){
        SQLiteDatabase db = this.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        db.close();
        return count;
    }

}
