package com.example.karthick.goplaces.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KarthicK on 12/30/2016.
 */

public class GooglePlaceDetail {

    @SerializedName("place_id")
    private String place_id;

    @SerializedName("name")
    private String name;

    @SerializedName("formatted_address")
    private String formatted_address;

    @SerializedName("formatted_phone_number")
    private String formatted_phone_number;

    @SerializedName("website")
    private String website;

    @SerializedName("rating")
    private String rating;

    @SerializedName("geometry")
    private GoogleGeometry geometry;



    public String getPlace_id() {
        return place_id;
    }

    public String getName() {
        return name;
    }

    public String getFormatted_phone_number() {
        return formatted_phone_number;
    }
    public String getWebsite() {
        return website;
    }

    public String getFormatted_address() {
        return formatted_address;
    }

    public String getRating() {
        return rating;
    }

    public GoogleGeometry getGeometry() {
        return geometry;
    }
}
