package com.example.karthick.myplaces;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null){

            //Get the data passed in the intent
            String placeName = getIntent().getStringExtra("PLACE_NAME");

            final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            CollapsingToolbarLayout collapsingToolbar =
                    (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar_detail);
            collapsingToolbar.setTitle(placeName);

        }
    }
}
