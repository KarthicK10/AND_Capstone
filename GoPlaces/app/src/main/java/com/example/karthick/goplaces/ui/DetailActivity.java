package com.example.karthick.goplaces.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.karthick.goplaces.R;
import com.example.karthick.goplaces.data.Place;
import com.example.karthick.goplaces.data.PlacesContract;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.karthick.goplaces.R.id.address;

public class DetailActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    @BindView(R.id.toolbar_detail)
    Toolbar toolbar;
    @BindView(R.id.collapsing_toolbar_detail)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(address)
    TextView mAddressTextView;
    @BindView(R.id.car_fab)
    FloatingActionButton carFAB;
    @BindView(R.id.adView)
    AdView mAdView;
    @BindView(R.id.googleName)
    TextView mGoogleName;
    @BindView(R.id.lat)
    TextView mLat;
    @BindView(R.id.lng)
    TextView mLng;

    private static final int DETAIL_LOADER = 0;
    private long mPlaceId;
    private static final String LOG_TAG = DetailActivity.class.getSimpleName();

    //Columns to select for detail view
    private static final String[] DETAIL_COLUMNS = {
            PlacesContract.PlaceEntry._ID,
            PlacesContract.PlaceEntry.COLUMN_PLACE_GOOGLE_ID,
            PlacesContract.PlaceEntry.COLUMN_PLACE_NAME,
            PlacesContract.PlaceEntry.COLUMN_PLACE_ADDRESS,
            PlacesContract.PlaceEntry.COLUMN_PLACE_GOOGLE_NAME,
            PlacesContract.PlaceEntry.COLUMN_PLACE_PHONE,
            PlacesContract.PlaceEntry.COLUMN_PLACE_WEBSITE,
            PlacesContract.PlaceEntry.COLUMN_PLACE_LATITUDE,
            PlacesContract.PlaceEntry.COLUMN_PLACE_LONGITUDE
    };

    // These indices are tied to DETAIL_COLUMNS.  If DETAIL_COLUMNS changes, these
    // must change.
    public static final int COL_PLACE_ID = 0;
    public static final int COL_PLACE_GOOGLE_ID = 1;
    public static final int COL_PLACE_NAME = 2;
    public static final int COL_PLACE_ADDRESS = 3;
    public static final int COL_PLACE_GOOGLE_NAME = 4;
    public static final int COL_PLACE_PHONE = 5;
    public static final int COL_PLACE_WEBSITE = 6;
    public static final int COL_PLACE_LAT = 7;
    public static final int COL_PLACE_LNG = 8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        if(savedInstanceState == null){

            //Get the data passed in the intent
            Intent intent = getIntent();
            mPlaceId = intent.getLongExtra(Place.PLACE_ID_KEY, 0);

            //Toolbar
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device. e.g.
            // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
            AdRequest adRequest = new AdRequest.Builder()
                    .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                    .build();
            mAdView.loadAd(adRequest);
            getLoaderManager().initLoader(DETAIL_LOADER, null, this);
        }

    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if(mPlaceId != 0){
            String[] whereClauseArgs = {new Long(mPlaceId).toString()};
            return new CursorLoader(
                    this,
                    PlacesContract.PlaceEntry.CONTENT_URI,
                    DETAIL_COLUMNS,
                    PlacesContract.PlaceEntry._ID + " = ?",
                    whereClauseArgs,
                    null
            );
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if(data != null && data.moveToFirst()){
            //Collapsing tool bar with the place name as title
            collapsingToolbar.setTitle(data.getString(COL_PLACE_NAME));

            //Set the address
            final String address = data.getString(COL_PLACE_ADDRESS);
            mAddressTextView.setText(data.getString(COL_PLACE_ADDRESS));

            //set the place details
            if(!(TextUtils.isEmpty(data.getString(COL_PLACE_GOOGLE_NAME))))
                Log.i(LOG_TAG, data.getString(COL_PLACE_GOOGLE_NAME));
            mGoogleName.setText(data.getString(COL_PLACE_GOOGLE_NAME));
            int latFormat = R.string.format_lat;
            int lngFormat = R.string.format_lng;
            mLat.setText(String.format(getString(latFormat), data.getString(COL_PLACE_LAT)));
            mLng.setText(String.format(getString(lngFormat), data.getString(COL_PLACE_LNG)));

            //Maps - Drive Intent on click of car FAB.
            carFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri gmmIntentUri = Uri.parse("google.navigation:")
                            .buildUpon()
                            .appendQueryParameter("q", address)
                            .build();
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }

            });
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

}
