package com.example.animal_continent_application2;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class AnimalDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "animal.db";
    private static final int DATABASE_VERSION = 1;

    public AnimalDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE animal (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "continent TEXT" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS animal";
        db.execSQL(sql);
        onCreate(db);
    }



}

