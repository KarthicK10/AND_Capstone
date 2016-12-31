package com.example.karthick.goplaces.data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by KarthicK on 8/7/2016.
 *
 * The result from Retrofit call to themoviedb api
 */
public class GooglePlacesResult {

    @SerializedName("results")
    private ArrayList<GooglePlace> placesList = new ArrayList<>();

    public ArrayList<GooglePlace> getPlacesList() {
        return placesList;
    }
}
