package com.example.karthick.goplaces.data;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by KarthicK on 12/25/2016.
 *
 * Defines the Tables and Columns for the Go Places Database
 */

public final class PlacesContract {
    //Define the Content Authority to be used in Content URI
    public static final String CONTENT_AUTHORIY = "com.example.karthick.goplaces";

    //Define the base URI path
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORIY);

    //Define path for places data
    public static final String PATH_PLACE = "place";

    private PlacesContract(){
        //Suppress the constructor as this is not to be instantiated
    }

    /*Inner class to define the column names of the place table*/
    public static final class PlaceEntry implements BaseColumns{
        //Table name
        public static final String TABLE_NAME = "place";

        //Start - column names

        //place id as returned by google api, stored as String
        public static final String COLUMN_PLACE_GOOGLE_ID = "google_id";

        //place name, stored as String
        public static final String COLUMN_PLACE_NAME = "name";

        //place google name, stored as String
        public static final String COLUMN_PLACE_GOOGLE_NAME = "google_name";

        //address, stored as String
        public static final String COLUMN_PLACE_ADDRESS = "address";

        //phone, stored as String
        public static final String COLUMN_PLACE_PHONE = "phone";

        //website, stored as String
        public static final String COLUMN_PLACE_WEBSITE = "website";

        //latitude, stored as String
        public static final String COLUMN_PLACE_LATITUDE = "lat";

        //longitude, stored as String
        public static final String COLUMN_PLACE_LONGITUDE = "lng";

        //End - Column names

        //Content Uri
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon()
                .appendPath(PATH_PLACE).build();

        public static Uri buildPlaceUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        //Cursor types
        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORIY + "/" + PATH_PLACE;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORIY + "/" + PATH_PLACE;


    }
}
