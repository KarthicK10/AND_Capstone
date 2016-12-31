package com.example.karthick.goplaces.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by KarthicK on 12/25/2016.
 *
 * Manages the local database for the places data
 */

public class PlacesDBHelper extends SQLiteOpenHelper{

    // If you change the database schema, you must increment the database version.
    private static final int DATABASE_VERSION = 5;

    static final String DATABASE_NAME = "places.db";

    public PlacesDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        /*
         * Create a table to store the places.
         * A place consist of its name and address.
         */
        final String SQL_CREATE_PLACE_TABLE = "CREATE TABLE " + PlacesContract.PlaceEntry.TABLE_NAME + " (" +
                PlacesContract.PlaceEntry._ID + " INTEGER PRIMARY KEY," +
                PlacesContract.PlaceEntry.COLUMN_PLACE_GOOGLE_ID + " TEXT, " +
                PlacesContract.PlaceEntry.COLUMN_PLACE_NAME + " TEXT UNIQUE NOT NULL, " +
                PlacesContract.PlaceEntry.COLUMN_PLACE_GOOGLE_NAME + " TEXT, " +
                PlacesContract.PlaceEntry.COLUMN_PLACE_ADDRESS + " TEXT NOT NULL,  " +
                PlacesContract.PlaceEntry.COLUMN_PLACE_PHONE + " TEXT, " +
                PlacesContract.PlaceEntry.COLUMN_PLACE_WEBSITE + " TEXT, " +
                PlacesContract.PlaceEntry.COLUMN_PLACE_LATITUDE + " TEXT, " +
                PlacesContract.PlaceEntry.COLUMN_PLACE_LONGITUDE + " TEXT " +
                " );";

        sqLiteDatabase.execSQL(SQL_CREATE_PLACE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //As of now, following a discard and start over approach.
        //TODO - Keep existing data on upgrade
        //Note: This will trigger only when db version number is changed.
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + PlacesContract.PlaceEntry.TABLE_NAME);

    }
}
