package com.example.karthick.goplaces.ui;


import android.content.ContentValues;
import android.content.Intent;
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
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    protected final static int PLACE_PICKER_REQUEST = 1010;

    EditText mAddressEditText;

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
        mAddressEditText = (EditText) rootView.findViewById(R.id.editAddress);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameEditText.getText().toString();
                String address = mAddressEditText.getText().toString();
                Place place = new Place(name, address);
                if(validatePlace(place)){
                   addPlace(place);
                }
            }
        });
        ImageView attribution = (ImageView) rootView.findViewById(R.id.attribution);
        ImageView placePickerImageView = (ImageView) rootView.findViewById(R.id.place_picker);
        // Check to see if Google Play services is available. The Place Picker API is available
        // through Google Play services, so if this is false, we'll just carry on as though this
        // feature does not exist. If it is true, however, we can add a place picker widget.
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(getContext());
        // Add the get current location widget to our location preference based on resultCode
        if(resultCode == ConnectionResult.SUCCESS ){
            placePickerImageView.setVisibility(View.VISIBLE);
            mAddressEditText.setText(getContext().getString(R.string.enter_address_or_pick));
            mAddressEditText.setContentDescription(getContext().getString(R.string.enter_address_or_pick_desc));
            placePickerImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), "Location picker", Toast.LENGTH_SHORT).show();
                    //Launch the place picker
                    PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                    try{
                        startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
                    } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException e){
                       //do nothing
                    }
                }
            });
            //Add attribution
            attribution.setVisibility(View.VISIBLE);
        }
        else{
            placePickerImageView.setVisibility(View.GONE);
            mAddressEditText.setText(getContext().getString(R.string.enter_address));
            attribution.setVisibility(View.GONE);
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

    /**
     * Receive the result from a previous call to
     * {@link #startActivityForResult(Intent, int)}.  This follows the
     * related Activity API as described there in
     * {@link AddActivity#onActivityResult(int, int, Intent)}.
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == PLACE_PICKER_REQUEST){
            if(resultCode == RESULT_OK){
                //Get the place picked
                com.google.android.gms.location.places.Place pickedPlace = PlacePicker.getPlace(getActivity(), data);
                //Set the address of the place into the address edit text field.
                mAddressEditText.setText(pickedPlace.getAddress());
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
