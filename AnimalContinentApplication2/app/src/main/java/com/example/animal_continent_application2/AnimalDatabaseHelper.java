package com.example.animal_continent_application2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.AsyncTask;

import java.util.ArrayList;

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

    public void fetchAnimalsAsync(OnAnimalsFetchedListener listener) {
        FetchAnimalsTask task = new FetchAnimalsTask(listener);
        task.execute();
    }

    private class FetchAnimalsTask extends AsyncTask<Void, Void, ArrayList<Animal>> {

        private final OnAnimalsFetchedListener listener;

        public FetchAnimalsTask(OnAnimalsFetchedListener listener) {
            this.listener = listener;
        }

        @Override
        protected ArrayList<Animal> doInBackground(Void... voids) {
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

        @Override
        protected void onPostExecute(ArrayList<Animal> animalList) {
            if (listener != null) {
                listener.onAnimalsFetched(animalList);
            }
        }
    }

    public interface OnAnimalsFetchedListener {
        void onAnimalsFetched(ArrayList<Animal> animalList);
    }

}

