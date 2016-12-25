package com.example.karthick.myplaces;

/**
 * Created by KarthicK on 12/25/2016.
 */

public class Place {

    private String name;
    private String address;

    public Place(String name){
        this.name = name;
        this.address = "203, Community Dr, Apt E, Reading, PA 19607";
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }
}
