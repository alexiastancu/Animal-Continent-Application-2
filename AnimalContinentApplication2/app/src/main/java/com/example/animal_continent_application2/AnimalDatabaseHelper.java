package com.example.animal_continent_application2;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
//        Set<Animal> animalList = fetchAnimals();
//        FirstFragment.adapter.setItems(animalList);
//        FirstFragment.adapter.notifyDataSetChanged();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS animal";
        db.execSQL(sql);
        onCreate(db);
    }

    public ArrayList<Animal> fetchAnimals() {
        ArrayList<Animal> animalList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("animal", null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex("id"));
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
            @SuppressLint("Range") String continent = cursor.getString(cursor.getColumnIndex("continent"));
            Animal animal = new Animal(id, name, continent);
            animalList.add(animal);
        }

        cursor.close();
        return animalList;
    }

}

