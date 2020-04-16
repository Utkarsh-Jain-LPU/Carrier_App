package com.utkarsh.carrierapp;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.Toast;

import androidx.annotation.NonNull;
import java.util.ArrayList;

public class PlaceAutoSuggestAdapter extends ArrayAdapter implements Filterable {

    private ArrayList<String> result;
    private PlaceApi placeApi;

    PlaceAutoSuggestAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        placeApi = new PlaceApi();
    }

    @Override
    public int getCount() {
        return result.size();
    }

    @Override
    public String getItem(int position) {
        return result.get(position);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new  Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults filterResults = new FilterResults();
                if (constraint != null) {
                    result = placeApi.autoComplete(constraint.toString());
                    filterResults.values = result;
                    filterResults.count = result.size();
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                if (results!=null && results.count>0) {
                    notifyDataSetChanged();
                }
                else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }
}
