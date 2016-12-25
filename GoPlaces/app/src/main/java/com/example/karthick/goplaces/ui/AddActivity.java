package com.example.karthick.goplaces.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.karthick.goplaces.R;

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
