package com.example.karthick.goplaces.google;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KarthicK on 12/31/2016.
 */

public class GoogleGeometry {

    @SerializedName("location")
    private GoogleLatLong location;

    @SerializedName("viewport")
    private GoogleViewPort viewport;

    public GoogleLatLong getLocation() {
        return location;
    }

    public GoogleViewPort getViewport() {
        return viewport;
    }
}
