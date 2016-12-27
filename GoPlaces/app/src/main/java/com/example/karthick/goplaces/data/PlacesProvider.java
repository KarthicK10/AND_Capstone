package com.example.karthick.goplaces.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

/**
 * Created by KarthicK on 12/25/2016.
 */

public class PlacesProvider extends ContentProvider {

    // The URI Matcher used by this content provider.
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private PlacesDBHelper mPlacesDBHelper;

    static final int PLACES = 100;

    @Override
    public boolean onCreate() {
        mPlacesDBHelper = new PlacesDBHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projections, String selection, String[] selectionArgs, String sortOrder) {
        Cursor retCursor;
        switch (sUriMatcher.match(uri)){
            case PLACES:
                retCursor = mPlacesDBHelper.getReadableDatabase().query(
                        PlacesContract.PlaceEntry.TABLE_NAME,
                        null,
                        null,
                        null,
                        null,
                        null,
                        sortOrder
                );
                break;
            default:
                throw new UnsupportedOperationException("Unknown URI: " + uri);
        }
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)){
            case PLACES:
                return PlacesContract.PlaceEntry.CONTENT_TYPE;
            default:
                throw new UnsupportedOperationException("Unknown Uri: " + uri);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Uri returnUri;
        final SQLiteDatabase db = mPlacesDBHelper.getWritableDatabase();
        final int match = sUriMatcher.match(uri);

        switch (match){
            case PLACES:
                long _id = db.insert(PlacesContract.PlaceEntry.TABLE_NAME, null, contentValues);
                break;
            default:
                throw new UnsupportedOperationException("Unknown uri: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int rowsDeleted = 0;
        final SQLiteDatabase db = new PlacesDBHelper(getContext()).getWritableDatabase();
        switch (sUriMatcher.match(uri)){
            case PLACES:
                rowsDeleted = db.delete(
                        PlacesContract.PlaceEntry.TABLE_NAME,
                        selection,
                        selectionArgs
                );
                break;
            default:
                throw new UnsupportedOperationException("Delete URI not found");
        }
        if(rowsDeleted > 0){
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }


    static UriMatcher buildUriMatcher() {

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = PlacesContract.CONTENT_AUTHORIY;

        // For each type of URI you want to add, create a corresponding code.
        matcher.addURI(authority, PlacesContract.PATH_PLACE, PLACES);

        return matcher;
    }
}
