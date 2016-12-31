package com.example.karthick.goplaces.google;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KarthicK on 12/31/2016.
 */

public class GoogleLatLong {

    @SerializedName("lat")
    private String lat;
    @SerializedName("lng")
    private String lng;

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }
}
