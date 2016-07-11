package com.example.hicham.properties;

import android.content.Intent;
import android.view.View;
import android.widget.ExpandableListView;

/**
 * Created by Hicham on 6/30/2016.
 */
public class ChildClickListener implements ExpandableListView.OnChildClickListener {
    PropertiesActivity activity;
    public ChildClickListener(PropertiesActivity _activity)
    {
        activity = _activity;
    }


    @Override
    public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
            Intent MyIntent = new Intent(activity,ItemActivity.class);
            MyIntent.putExtra("i",i);
            MyIntent.putExtra("i1",i1);
            activity.startActivity(MyIntent);
            return false;
    }
}

