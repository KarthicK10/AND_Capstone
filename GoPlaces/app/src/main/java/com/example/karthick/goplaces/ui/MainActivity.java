package com.example.karthick.goplaces.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.karthick.goplaces.R;
import com.example.karthick.goplaces.data.Place;

public class MainActivity extends AppCompatActivity implements MainActivityFragment.Callback{

    private Context mContext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(mContext, AddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onItemSelected(long placeId) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra(Place.PLACE_ID_KEY, placeId);
        startActivity(intent);
    }
}
