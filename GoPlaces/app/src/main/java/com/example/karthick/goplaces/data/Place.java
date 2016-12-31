package com.example.karthick.goplaces.data;

import com.example.karthick.goplaces.google.GooglePlace;

/**
 * Created by KarthicK on 12/25/2016.
 */

public class Place {

    public static final String PLACE_NAME_KEY = "PLACE_NAME";
    public static final String PLACE_ADDRESS_KEY = "PLACE_ADDRESS";
    public static final String PLACE_ID_KEY = "PLACE_ID";

    private String name;
    private String address;
    private String googleName;
    private String googleId;

    public Place() {
    }

    public Place(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public void setGooglePlaceDetails(GooglePlace googlePlace){
        this.googleId = googlePlace.getPlace_id();
        this.googleName = googlePlace.getName();
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getGoogleName() {
        return googleName;
    }

    public String getGoogleId() {
        return googleId;
    }

}
