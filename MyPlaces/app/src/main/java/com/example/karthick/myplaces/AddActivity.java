package com.example.karthick.myplaces;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        if(savedInstanceState == null){
            AddFragment addFragment = new AddFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.add_container, addFragment)
                    .commit();
        }
    }
}
