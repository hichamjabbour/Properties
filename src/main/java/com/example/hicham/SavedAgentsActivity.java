package com.example.hicham.properties;

import android.app.ListActivity;
import android.os.Bundle;

public class SavedAgentsActivity extends ListActivity {
ListAdapter3 listAdapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_agents);
        listAdapter3 = new ListAdapter3(this);
        setListAdapter(listAdapter3);
    }
}
