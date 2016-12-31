package com.example.karthick.goplaces.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KarthicK on 8/7/2016.
 *
 * The result from Retrofit call to themoviedb api
 */
public class GooglePlaceDetailResult {

    @SerializedName("result")
    GooglePlaceDetail placeDetail;

    public GooglePlaceDetail getPlaceDetail() {
        return placeDetail;
    }
}
