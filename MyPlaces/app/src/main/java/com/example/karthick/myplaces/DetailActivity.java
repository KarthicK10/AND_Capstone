package com.example.karthick.myplaces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class DetailActivity extends AppCompatActivity {

    public static final String FRAGMENT_KEY = "FRAGMNET_KEY";
    public static final String ADD_FRAGEMNET_TAG = "ADDFRG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if(savedInstanceState == null){
            //Find which fragmnet to create based on fragment tag passed in intent.
            String fragmentTag = getIntent().getStringExtra(FRAGMENT_KEY);
            if(fragmentTag.equals(ADD_FRAGEMNET_TAG)){
                AddFragment addFragment = new AddFragment();
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.detail_container, addFragment)
                        .commit();
            }
        }
    }
}
