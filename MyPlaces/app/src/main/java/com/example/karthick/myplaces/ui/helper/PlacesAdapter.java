package com.example.karthick.myplaces.ui.helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.karthick.myplaces.R;

import java.util.List;

/**
 * Created by KarthicK on 12/24/2016.
 *
 * {@link PlacesAdapter} exposes a list of places
 * from a {@link android.database.Cursor} to a {@link android.support.v7.widget.RecyclerView}.
 */

public class PlacesAdapter extends RecyclerView.Adapter<PlacesAdapter.PlacesAdapterViewHolder>{

    public List<String> mLabelList;

    /**
     * Cache of child views for a places list item
     */
    public class PlacesAdapterViewHolder extends RecyclerView.ViewHolder{

        public final TextView mLabelView;

        public PlacesAdapterViewHolder (View view){
            super(view);
            mLabelView = (TextView) view.findViewById(R.id.list_item_place_label);
        }
    }

    public static interface PlacesAdapterOnClickHandler{
        void onClick(PlacesAdapterViewHolder vh);
    }

    public PlacesAdapter(List<String> labelList){
        mLabelList = labelList;
    }


    /**
     * Called when RecyclerView needs a new {@link PlacesAdapterViewHolder} of the given type to represent
     * an item.
     * <p>
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     * <p>
     * The new ViewHolder will be used to display items of the adapter using
     * {@link #onBindViewHolder(PlacesAdapterViewHolder, int)}. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary {@link View#findViewById(int)} calls.
     *
     * @param viewGroup   The ViewGroup into which the new View will be added after it is bound to
     *                 an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     * @see #getItemViewType(int)
     * @see #onBindViewHolder(PlacesAdapterViewHolder, int)
     */
    @Override
    public PlacesAdapterViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
       if(viewGroup instanceof RecyclerView){
           View view = LayoutInflater.from(viewGroup.getContext())
                   .inflate(R.layout.list_item_place, viewGroup, false);
           view.setFocusable(true);
           return new PlacesAdapterViewHolder(view);
       }
       else{
           throw new RuntimeException("Not bound to RecyclerView");
       }
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the {@link PlacesAdapterViewHolder#itemView} to reflect the item at the given
     * position.
     * <p>
     * Note that unlike {@link ListView}, RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the <code>position</code> parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use {@link PlacesAdapterViewHolder#getAdapterPosition()} which will
     * have the updated adapter position.
     * <p>
     * Override {@link #onBindViewHolder(PlacesAdapterViewHolder, int)}  instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder   The ViewHolder which should be updated to represent the contents of the
     *                 item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(PlacesAdapterViewHolder holder, int position) {
        holder.mLabelView.setText(mLabelList.get(position));
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return mLabelList.size();
    }
}
