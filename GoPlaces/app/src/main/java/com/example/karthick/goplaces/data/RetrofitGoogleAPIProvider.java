package com.example.karthick.goplaces.data;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by KarthicK on 10/24/2016.
 */

public class RetrofitGoogleAPIProvider {
    private static final String GOOGLE_API_BASE_URL = "https://maps.googleapis.com/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(GOOGLE_API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    private static final GoogleAPI googleAPI = retrofit.create(GoogleAPI.class);

    public static GoogleAPI getGoogleAPI(){
        return googleAPI;
    }
}
