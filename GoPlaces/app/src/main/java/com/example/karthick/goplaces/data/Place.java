package com.example.karthick.goplaces.data;

/**
 * Created by KarthicK on 12/25/2016.
 */

public class Place {

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
