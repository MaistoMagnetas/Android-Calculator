package com.example.pc.calculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC on 4/27/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "calculator.db";
    private static final String TABLE_NAME = "history_table";
    private static final String COL_1 = "ID";
    private static final String COL_2 = "INPUT";
    private static final String COL_3 = "ANSWER";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "INPUT TEXT, ANSWER TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    //Simple method to add data to SQLlite DB
    public boolean addEntryToHistory(String input, String answer){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,input);
        contentValues.put(COL_3,answer);
        long isInserted = db.insert(TABLE_NAME,null,contentValues);
        if(isInserted == -1){
            return false;
        }else{
            return true;
        }
    }

    //Method to get all the data from DB in DESC order
    public Cursor getAllHistoryData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT * FROM "+TABLE_NAME +" ORDER BY ID DESC";
        Cursor res = db.rawQuery(sql,null);
        return res;
    }

}
