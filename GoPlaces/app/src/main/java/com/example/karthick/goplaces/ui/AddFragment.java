package com.example.karthick.goplaces.ui;


import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.karthick.goplaces.R;
import com.example.karthick.goplaces.data.Place;
import com.example.karthick.goplaces.data.PlacesContract;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {


    public AddFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        Button addButton = (Button) rootView.findViewById(R.id.add_button);
        final EditText nameEditText = (EditText) rootView.findViewById(R.id.editName);
        final EditText addressEditText = (EditText) rootView.findViewById(R.id.editAddress);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String address = addressEditText.getText().toString();
                Place place = new Place(name, address);
                if(validatePlace(place)){
                   addPlace(place);
                }
            }
        });
        ImageView placePickerImageView = (ImageView) rootView.findViewById(R.id.place_picker);
        // Check to see if Google Play services is available. The Place Picker API is available
        // through Google Play services, so if this is false, we'll just carry on as though this
        // feature does not exist. If it is true, however, we can add a place picker widget.
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(getContext());
        // Add the get current location widget to our location preference based on resultCode
        if(resultCode == ConnectionResult.SUCCESS ){
            placePickerImageView.setVisibility(View.VISIBLE);
            addressEditText.setText(getContext().getString(R.string.enter_address_or_pick));
            addressEditText.setContentDescription(getContext().getString(R.string.enter_address_or_pick_desc));
        }
        else{
            placePickerImageView.setVisibility(View.GONE);
            addressEditText.setText(getContext().getString(R.string.enter_address));
        }
        return rootView;
    }

    private boolean validatePlace(Place place){
        //TODO - Actual validation
        return true;
    }

    private void addPlace(Place place){
        //Create content values
        ContentValues placeContentValues = new ContentValues();
        placeContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_NAME, place.getName());
        placeContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_ADDRESS, place.getAddress());

        //add place
        getContext().getContentResolver().insert(PlacesContract.PlaceEntry.CONTENT_URI, placeContentValues);
        Toast toast = Toast.makeText(getContext(), "Added to Places", Toast.LENGTH_SHORT);
        toast.show();
    }

}
