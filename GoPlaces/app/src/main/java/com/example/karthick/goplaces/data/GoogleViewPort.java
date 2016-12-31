package com.example.karthick.goplaces.data;

import com.google.gson.annotations.SerializedName;

/**
 * Created by KarthicK on 12/31/2016.
 */

public class GoogleViewPort {

    @SerializedName("northeast")
    private GoogleLatLong northeast;

    @SerializedName("southwest")
    private GoogleLatLong southwest;

    public GoogleLatLong getNortheast() {
        return northeast;
    }

    public GoogleLatLong getSouthwest() {
        return southwest;
    }
}
