package com.example.karthick.goplaces.data;

/**
 * Created by KarthicK on 12/25/2016.
 */

public class Place {

    public static final String PLACE_NAME_KEY = "PLACE_NAME";
    public static final String PLACE_ADDRESS_KEY = "PLACE_ADDRESS";
    private String name;
    private String address;

    public Place(String name, String address){
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
