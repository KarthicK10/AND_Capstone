package com.example.karthick.goplaces.data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by KarthicK on 8/6/2016.
 *
 * Interface to be used by Retrofit
 *
 */
public interface GoogleAPI {
    @GET("maps/api/place/textsearch/json")
    Call<GooglePlacesResult> getPlaces(@Query("query") String searchQuery, @Query("key") String apiKey);

    @GET("/maps/api/place/details/json")
    Call<GooglePlaceDetailResult> getPlaceDetail(@Query("placeid") String placeId, @Query("key") String apiKey);
}
