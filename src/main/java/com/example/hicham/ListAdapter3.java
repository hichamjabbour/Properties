package com.example.hicham.properties;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Hicham on 6/8/2016.
 */

public class ListAdapter3 extends BaseAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;
    public static List<Agent> mEntries = new ArrayList<Agent>();

    public ListAdapter3(Context context) {

        mContext = context;

        mLayoutInflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
                    R.layout.saved_agent_item_layout, parent, false);

        } else {
            itemView = (RelativeLayout) convertView;
        }


        TextView agent_name = (TextView) itemView.findViewById(R.id.agent_name);
        TextView agent_address = (TextView) itemView.findViewById(R.id.agent_address);
        TextView agent_phone = (TextView) itemView.findViewById(R.id.agent_phone);

        final ImageView agent_logo = (ImageView) itemView.findViewById(R.id.agent_logo);

        String agentName = mEntries.get(position).getAgent_name();
        String address = "Address : " + mEntries.get(position).getAgent_address();
        String phone = "Phone :" + mEntries.get(position).getAgent_phone();
        String logo =  mEntries.get(position).getAgent_logo();

        agent_name.setText(agentName);
        agent_address.setText(address);

        agent_phone.setText(phone);

        PropertiesActivity.imageLoader.DisplayImage(logo,agent_logo);

        return itemView;
    }


    public void upDateEntries(List<Agent> entries) {
        mEntries = entries;
        notifyDataSetChanged();
    }
}