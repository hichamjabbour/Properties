package com.example.hicham.properties;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hicham on 6/15/2016.
 */
public class ListAdapter1 extends BaseExpandableListAdapter{
    PropertiesActivity activity;
    Context mContext;
    LayoutInflater mLayoutInflater;
    public static List<List<Appartment>> mEntries;
    public List<String> group;
    public ListAdapter1(PropertiesActivity _activity) {
        super();
        activity = _activity;
        mContext = activity.getBaseContext();
        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        mEntries = new ArrayList<List<Appartment>>();
        group = new ArrayList<String>();
        group.add("rent");
        group.add("sale");

    }


    @Override
    public int getGroupCount() {
        return group.size();
    }

    @Override
    public int getChildrenCount(int position) {
        return ((ArrayList<Appartment>)(mEntries.get(position))).size();
    }

    @Override
    public Object getGroup(int position) {
        return group.get(position);
    }



    @Override
    public long getGroupId(int position) {
        return 0;
    }



    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        LinearLayout itemView;
        if (convertView == null) {
            itemView = (LinearLayout) mLayoutInflater.inflate(
                    R.layout.grouprow,null);

        } else {
            itemView = (LinearLayout) convertView;
        }
        CheckedTextView view = (CheckedTextView)itemView.findViewById(R.id.checkedTextView);
        view.setText(group.get(groupPosition));
        view.setChecked(isExpanded);
        return itemView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean b, View convertView , ViewGroup parent) {
        RelativeLayout itemView;
        if (convertView == null) {
            itemView = (RelativeLayout) mLayoutInflater.inflate(
                    R.layout.listview_layout, parent, false);

        } else {
            itemView = (RelativeLayout) convertView;
        }
        TextView descriptionText = (TextView)itemView.findViewById(R.id.description);
        TextView dateText = (TextView)itemView.findViewById(R.id.date);
        TextView bathroomsText = (TextView)itemView.findViewById(R.id.bathrooms);
        TextView bedroomsText = (TextView)itemView.findViewById(R.id.bedrooms);
        TextView floorsText = (TextView)itemView.findViewById(R.id.floors);
        TextView displayableAddressText = (TextView)itemView.findViewById(R.id.address);
        TextView priceText = (TextView)itemView.findViewById(R.id.price);
        final ImageView image = (ImageView)itemView.findViewById(R.id.image);

        List<Appartment> list = (ArrayList<Appartment>)mEntries.get(groupPosition);


        String date = list.get(childPosition).getAppartmentCharacteristics().getDate();
        String agentName = list.get(childPosition).getAgent().getAgent_name();


        String short_description =
                list.get(childPosition).getAppartmentCharacteristics().getShort_description();
        if (short_description.trim().length() == 0) {
            short_description = "Sorry, no description";
        }

        String bathrooms = "Number of Bathrooms : " + list.get(childPosition).getAppartment_constitution().getNum_bathrooms();
        String bedrooms = "Number of Bedrooms : " +  list.get(childPosition).getAppartment_constitution().getNum_bedrooms();
        String floors =  "Number of Floors : " + list.get(childPosition).getAppartment_constitution().getNum_floors();
        String displayableAddress = "Address :" + list.get(childPosition).getAppartmentCharacteristics().getDisplayable_address();
        String price = "Price :" + list.get(childPosition).getAppartmentCharacteristics().getPrice();

        dateText.setText(date);
        descriptionText.setText(short_description);
        displayableAddressText.setText(displayableAddress);

        bathroomsText.setText(bathrooms);
        bedroomsText.setText(bedrooms);
        floorsText.setText(floors);
        priceText.setText(price);
        String url = list.get(childPosition).getAppartmentCharacteristics().getImage_url();
        PropertiesActivity.imageLoader.DisplayImage(url,image);
        return  itemView;
    }


    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public Object getChild(int groupPosition,int childPosition) {
        return ((ArrayList<Appartment>)mEntries.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getChildId(int groupPosition,int childPosition) {
        return 0;
    }




    public void upDateEntries(List<List<Appartment>> entries) {
        mEntries = entries;
        group.set(0,"rent("+ mEntries.get(0).size()+")");
        group.set(1,"sale("+ mEntries.get(1).size()+")");
        notifyDataSetChanged();
    }
}