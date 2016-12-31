package com.example.karthick.goplaces.google;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KarthicK on 12/30/2016.
 */

public class GooglePlace {

    @SerializedName("place_id")
    private String place_id;

    @SerializedName("name")
    private String name;

    public String getPlace_id() {
        return place_id;
    }

    public String getName() {
        return name;
    }
}
