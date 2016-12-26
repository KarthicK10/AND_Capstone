package com.example.karthick.goplaces.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.karthick.goplaces.R;
import com.example.karthick.goplaces.data.Place;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null){

            //Get the data passed in the intent
            Intent intent = getIntent();
            String placeName = intent.getStringExtra(Place.PLACE_NAME_KEY);
            String placeAddress = intent.getStringExtra(Place.PLACE_ADDRESS_KEY);

            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_detail);
            collapsingToolbar.setTitle(placeName);

            TextView addressTextView = (TextView) findViewById(R.id.address);
            addressTextView.setText(placeAddress);

        }
    }
}
