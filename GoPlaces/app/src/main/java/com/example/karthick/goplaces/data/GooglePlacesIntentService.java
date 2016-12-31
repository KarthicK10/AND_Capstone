package com.example.karthick.goplaces.data;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by KarthicK on 12/31/2016.
 */

public class GooglePlacesIntentService extends IntentService {

    public static final String PLACE_ID_KEY = "PLACE_ID";
    public static final String ADDRESS_KEY = "ADDRESS";

    private static final String SERVICE_NAME = "GOOGLE_PLACES_INTENT_SERVICE";
    private static final String LOG_TAG = GooglePlacesIntentService.class.getSimpleName();
    private static final String API_KEY = com.example.karthick.goplaces.BuildConfig.API_KEY;

    public GooglePlacesIntentService() {
        super(SERVICE_NAME);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        long placeId = intent.getLongExtra(PLACE_ID_KEY, 0);
        String address = intent.getStringExtra(ADDRESS_KEY);
        //update place details from Google
        fetchGooglePlaceDetails(placeId, address);
    }

    private void fetchGooglePlaceDetails(final long _id, final String address){
        //Get the google place from Google Places API
        getPlaceFromApi( _id, address);
    }

    private void getPlaceFromApi(final long _id, final String address){
        Call<GooglePlacesResult> placesCall = RetrofitGoogleAPIProvider
                .getGoogleAPI()
                .getPlaces(
                        address,
                        API_KEY
                );
        placesCall.enqueue(new Callback<GooglePlacesResult>() {
            @Override
            public void onResponse(Call<GooglePlacesResult> call, Response<GooglePlacesResult> response) {
                int statusCode = response.code();
                Log.i(LOG_TAG, "Retrofit status code for places: " + statusCode);
                if(statusCode == 200){
                    GooglePlacesResult googlePlacesResult = response.body();
                    ArrayList<GooglePlace> googlePlaceArrayList = googlePlacesResult.getPlacesList();
                    if(googlePlaceArrayList != null && !googlePlaceArrayList.isEmpty()){
                        GooglePlace googlePlace = googlePlaceArrayList.get(0);
                        getPlaceDetailsFromApi(_id, googlePlace.getPlace_id());
                    }

                }
            }

            @Override
            public void onFailure(Call<GooglePlacesResult> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void getPlaceDetailsFromApi(final long _id, final String googlePlaceId){
        Call<GooglePlaceDetailResult> placeDetailsCall = RetrofitGoogleAPIProvider
                .getGoogleAPI()
                .getPlaceDetail(
                        googlePlaceId,
                        API_KEY
                );
        placeDetailsCall.enqueue(new Callback<GooglePlaceDetailResult>() {
            @Override
            public void onResponse(Call<GooglePlaceDetailResult> call, Response<GooglePlaceDetailResult> response) {
                int statusCode = response.code();
                Log.i(LOG_TAG, "Retrofit status code for place details: " + statusCode);
                if(statusCode == 200){
                    GooglePlaceDetailResult googlePlaceDetailResult = response.body();
                    GooglePlaceDetail googlePlaceDetail = googlePlaceDetailResult.getPlaceDetail();
                    if(googlePlaceDetail != null){
                        updateGooglePlaceDetails(_id, googlePlaceDetail);
                    }
                }
            }

            @Override
            public void onFailure(Call<GooglePlaceDetailResult> call, Throwable t) {
                Log.e(LOG_TAG, t.getMessage());
            }
        });
    }

    private void updateGooglePlaceDetails(final long _id, final GooglePlaceDetail googlePlaceDetail){
        ContentValues googlePlaceContentValues = new ContentValues();
        googlePlaceContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_GOOGLE_ID, googlePlaceDetail.getPlace_id());
        googlePlaceContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_GOOGLE_NAME, googlePlaceDetail.getName());
        googlePlaceContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_PHONE, googlePlaceDetail.getFormatted_phone_number());
        googlePlaceContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_WEBSITE, googlePlaceDetail.getWebsite());
        googlePlaceContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_LATITUDE,
                googlePlaceDetail.getGeometry().getLocation().getLat());
        googlePlaceContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_LONGITUDE,
                googlePlaceDetail.getGeometry().getLocation().getLng());
        String[] whereClauseArgs = {new Long(_id).toString()};
        Context context = getApplicationContext();
        Log.i(LOG_TAG, context.toString());
        context.getContentResolver()
                .update(
                        PlacesContract.PlaceEntry.buildPlaceUri(_id),
                        googlePlaceContentValues,
                        PlacesContract.PlaceEntry._ID + " = ?",
                        whereClauseArgs
                );
    }
}
