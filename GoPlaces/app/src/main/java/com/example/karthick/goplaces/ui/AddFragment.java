package com.example.karthick.goplaces.ui;


import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.karthick.goplaces.R;
import com.example.karthick.goplaces.data.GooglePlace;
import com.example.karthick.goplaces.data.GooglePlacesIntentService;
import com.example.karthick.goplaces.data.Place;
import com.example.karthick.goplaces.data.PlacesContract;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFragment extends Fragment {

    private static final String LOG_TAG = AddFragment.class.getSimpleName();

    protected final static int PLACE_PICKER_REQUEST = 1010;
    public static final String ACTION_DATA_UPDATED =
            "com.example.karthick.goplaces.ACTION_DATA_UPDATED";

    @BindView(R.id.add_button) Button addButton;
    @BindView(R.id.editName) EditText nameEditText;
    @BindView(R.id.editAddress) EditText mAddressEditText;
    @BindView(R.id.attribution) ImageView attribution;
    @BindView(R.id.place_picker) ImageView placePickerImageView;

    private GooglePlace mGooglePlace;

    public AddFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        ButterKnife.bind(this, rootView);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name, address, googleName, googleId;

                name = nameEditText.getText().toString();
                address = mAddressEditText.getText().toString();

                Place place = new Place(name, address);

                if(validatePlace(place)){
                    long addedPlace_id = addPlace(place);
                    Toast toast = Toast.makeText(getContext(), place.getName() + " added to Places", Toast.LENGTH_SHORT);
                    toast.show();
                    //Update the widgets
                    updateWidgets();
                    //update place details from Google
                    Intent serviceIntent = new Intent(getActivity(), GooglePlacesIntentService.class);
                    serviceIntent.putExtra(GooglePlacesIntentService.PLACE_ID_KEY, addedPlace_id);
                    serviceIntent.putExtra(GooglePlacesIntentService.ADDRESS_KEY, address);
                    getActivity().startService(serviceIntent);
                }
                NavUtils.navigateUpFromSameTask(getActivity());
            }
        });
        // Check to see if Google Play services is available. The Place Picker API is available
        // through Google Play services, so if this is false, we'll just carry on as though this
        // feature does not exist. If it is true, however, we can add a place picker widget.
        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
        int resultCode = apiAvailability.isGooglePlayServicesAvailable(getContext());
        // Add the get current location widget to our location preference based on resultCode
        if(resultCode == ConnectionResult.SUCCESS ){
            placePickerImageView.setVisibility(View.VISIBLE);
            mAddressEditText.setHint(getContext().getString(R.string.enter_address_or_pick));
            placePickerImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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
            mAddressEditText.setHint(getContext().getString(R.string.enter_address));
            attribution.setVisibility(View.GONE);
        }
        return rootView;
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

    private boolean validatePlace(Place place){
        if(place.getName() == null || place.getName().trim().equals(""))
            return false;
        if(place.getAddress() == null || place.getAddress().trim().equals(""))
            return false;
        return true;
    }

    private long addPlace(Place place){
        //Create content values
        ContentValues placeContentValues = new ContentValues();
        placeContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_NAME, place.getName());
        placeContentValues.put(PlacesContract.PlaceEntry.COLUMN_PLACE_ADDRESS, place.getAddress());

        //add place
        Uri insertedUri = getContext().getContentResolver().insert(PlacesContract.PlaceEntry.CONTENT_URI, placeContentValues);
        long locationId = ContentUris.parseId(insertedUri);

        return locationId;
    }

    private void updateWidgets(){
        //Update the widgets.
        Context context = getContext();
        Intent dataUpdatedIntent = new Intent(ACTION_DATA_UPDATED)
                .setPackage(getContext().getPackageName());
        context.sendBroadcast(dataUpdatedIntent);
    }

}
