package com.example.karthick.goplaces.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.karthick.goplaces.data.Place;
import com.example.karthick.goplaces.ui.helper.PlacesAdapter;
import com.example.karthick.goplaces.R;
import com.example.karthick.goplaces.ui.helper.RecyclerItemClickListener;

import java.util.Arrays;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public List<String> labelList = Arrays.asList(
            "Bhavani Indian Stores",
            "Schuylkill River Trail",
            "Nikhil's Home",
            "Newyork Parking",
            "Bhavani Indian Stores",
            "Schuylkill River Trail",
            "Nikhil's Home",
            "Newyork Parking",
            "Bhavani Indian Stores",
            "Schuylkill River Trail",
            "Nikhil's Home",
            "Newyork Parking"
    );

    private RecyclerView mRecyclerView;
    private PlacesAdapter mPlacesAdapter;

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callback{
        public void onItemSelected(Place place);
    }

    public MainActivityFragment() {
    }

    /**
     * Called to do initial creation of a fragment.  This is called after
     * {@link #onAttach(Context)}  and before
     * {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * <p>
     * <p>Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, see {@link #onActivityCreated(Bundle)}.
     * <p>
     * <p>Any restored child fragments will be created before the base
     * <code>Fragment.onCreate</code> method returns.</p>
     *
     * @param savedInstanceState If the fragment is being re-created from
     *                           a previous saved state, this is the state.
     */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Get a reference to the RecyclerView
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview_places);
        //Set the layout manager
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //Get reference to empty view
        View emptyView = rootView.findViewById(R.id.recyclerview_places_empty);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        mRecyclerView.setHasFixedSize(true);

        //The PlacesAdapter will take data from a source and
        //use it to populate the RecylcerView it's attached to.
        mPlacesAdapter = new PlacesAdapter(labelList);
        mRecyclerView.setAdapter(mPlacesAdapter);
        mRecyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getContext(), new RecyclerItemClickListener.OnItemClickListener(){
                    @Override
                    public void onItemClick(View view, int position) {
                        Place place = new Place(labelList.get(position));
                        ((Callback)getActivity()).onItemSelected(place);
                    }
                })
        );

        return rootView;
    }
}
