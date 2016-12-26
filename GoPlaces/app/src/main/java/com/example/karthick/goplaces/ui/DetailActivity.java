package com.example.karthick.goplaces.ui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
            final String placeAddress = intent.getStringExtra(Place.PLACE_ADDRESS_KEY);

            //Toolbar
            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //Collapsing tool bar with the place name as title
            CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_detail);
            collapsingToolbar.setTitle(placeName);

            //Set the address
            TextView addressTextView = (TextView) findViewById(R.id.address);
            addressTextView.setText(placeAddress);

            //Maps - Drive Intent on click of car FAB.
            FloatingActionButton carFAB = (FloatingActionButton) findViewById(R.id.car_fab);
            carFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri gmmIntentUri = Uri.parse("google.navigation:")
                            .buildUpon()
                            .appendQueryParameter("q", placeAddress)
                            .build();
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }
            });

        }
    }
}
