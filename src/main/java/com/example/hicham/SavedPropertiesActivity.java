package com.example.hicham.properties;

import android.app.ListActivity;
import android.os.Bundle;

public class SavedPropertiesActivity extends ListActivity {
ListAdapter2 listAdapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.saved_properties);
        listAdapter2 = new ListAdapter2(this);
        setListAdapter(listAdapter2);


    }
}
