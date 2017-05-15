package com.example.hannantalukder.navigationexample.PlacesActivity.Adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.hannantalukder.navigationexample.PlacesActivity.ModelClass.PlacesName;
import com.example.hannantalukder.navigationexample.R;

import java.util.ArrayList;

import static android.R.attr.resource;

/**
 * Created by Hannan Talukder on 5/15/2017.
 */

public class PlacesAdapter extends ArrayAdapter<PlacesName>{
    private Context context;
    private ArrayList<PlacesName> placesNames;

    public PlacesAdapter(@NonNull Context context, ArrayList<PlacesName> placesNames) {
        super(context, R.layout.places_row, placesNames);
        this.context = context;
        this.placesNames = placesNames;
    }
    class ViewHolder{
        TextView nameTV;
        TextView addressTV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null)
        {
            holder = new ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.places_row,parent, false);

            holder.nameTV = (TextView) convertView.findViewById(R.id.nameET);
            holder.addressTV = (TextView) convertView.findViewById(R.id.addressET);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.nameTV.setText(placesNames.get(position).getName());
        holder.addressTV.setText(placesNames.get(position).getAddress());
        return convertView;
    }
}

