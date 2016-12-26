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

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.address)
    TextView addressTextView;
    @BindView(R.id.car_fab)
    FloatingActionButton carFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        if(savedInstanceState == null){

            //Get the data passed in the intent
            Intent intent = getIntent();
            String placeName = intent.getStringExtra(Place.PLACE_NAME_KEY);
            final String placeAddress = intent.getStringExtra(Place.PLACE_ADDRESS_KEY);

            //Toolbar
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            //Collapsing tool bar with the place name as title
            collapsingToolbar.setTitle(placeName);

            //Set the address
            addressTextView.setText(placeAddress);

            //Maps - Drive Intent on click of car FAB.
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
