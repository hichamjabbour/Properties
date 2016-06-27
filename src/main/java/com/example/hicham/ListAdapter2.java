package com.example.hicham.properties;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hicham on 6/8/2016.
 */

public class ListAdapter2 extends BaseAdapter implements Filterable {
    Context mContext;
    LayoutInflater mLayoutInflater;
    public static List<Appartment> mEntries;
    Filter mFilter;

    public ListAdapter2(Context context) {

        mContext = context;

        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mEntries = new ArrayList<Appartment>();
        mFilter = new ItemFilter();

    }

    @Override
    public int getCount() {
        return mEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return mEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView,
                        ViewGroup parent) {
        RelativeLayout itemView;
        if (convertView == null) {
            itemView = (RelativeLayout) mLayoutInflater.inflate(
                    R.layout.listview_layout, parent, false);

        } else {
            itemView = (RelativeLayout) convertView;
        }


        TextView descriptionText = (TextView) itemView.findViewById(R.id.description);
        TextView dateText = (TextView) itemView.findViewById(R.id.date);
        TextView bathroomsText = (TextView) itemView.findViewById(R.id.bathrooms);
        TextView bedroomsText = (TextView) itemView.findViewById(R.id.bedrooms);
        TextView floorsText = (TextView) itemView.findViewById(R.id.floors);
        TextView displayableAddressText = (TextView) itemView.findViewById(R.id.address);
        TextView priceText = (TextView) itemView.findViewById(R.id.price);

        final ImageView image = (ImageView) itemView.findViewById(R.id.image);

        String date = mEntries.get(position).getAppartmentCharacteristics().getDate();
        String agentName = mEntries.get(position).getAgent().getAgent_name();


        String short_description =
                mEntries.get(position).getAppartmentCharacteristics().getShort_description();
        if (short_description.trim().length() == 0) {
            short_description = "Sorry, no description";
        }

        String bathrooms = "Number of Bathrooms : " + mEntries.get(position).getAppartment_constitution().getNum_bathrooms();
        String bedrooms = "Number of Bedrooms : " + mEntries.get(position).getAppartment_constitution().getNum_bedrooms();
        String floors = "Number of Floors : " + mEntries.get(position).getAppartment_constitution().getNum_floors();
        String displayableAddress = "Address :" + mEntries.get(position).getAppartmentCharacteristics().getDisplayable_address();
        String price = "Price :" + mEntries.get(position).getAppartmentCharacteristics().getPrice();

        dateText.setText(date);
        descriptionText.setText(short_description);
        displayableAddressText.setText(displayableAddress);

        bathroomsText.setText(bathrooms);
        bedroomsText.setText(bedrooms);
        floorsText.setText(floors);
        priceText.setText(price);
        String url = mEntries.get(position).getAppartmentCharacteristics().getImage_url();
        PropertiesActivity.imageLoader.DisplayImage(url, image);

        return itemView;
    }


    public void upDateEntries(List<Appartment> entries) {
        mEntries = entries;
        notifyDataSetChanged();
    }


    @Override
    public Filter getFilter() {
        return mFilter;
    }


}