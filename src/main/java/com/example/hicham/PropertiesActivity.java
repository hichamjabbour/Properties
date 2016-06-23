package com.example.hicham.properties;

import android.app.DownloadManager;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.os.Bundle;
import android.os.DropBoxManager;
import android.support.v7.widget.ListPopupWindow.*;
import android.view.View;
import android.widget.*;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;

/**
 * Created by Hicham on 6/3/2016.
 */
public class PropertiesActivity extends ListActivity{
    URL url;
    Object array;
    TextView label;
    TextView price;
    Button search;
    ProgressDialog progress;
    public static BaseAdapter list;
    ListAdapter1 list1;
    ListAdapter2 list2;
    LoadFeedData loadFeedData;
    public static DownloadManager manager;
    public static ImageLoader  imageLoader;
    public static GPSTracker gpsTracker;
    public static Location location;
    private List<Appartment>originalData;
    private List<Appartment>filteredData;
    public MyAreasHandler handler;
    public ArrayAdapter<String> adapter;
    public static List<String> areas;
    public static final int URL_LOADER = 0;
    public static Spinner area_names;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.properties);
        area_names = (Spinner)findViewById(R.id.area_name);
        manager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        label = (TextView) findViewById(R.id.label);
        price = (TextView) findViewById(R.id.price);
        search = (Button)findViewById(R.id.search);
        progress = new ProgressDialog(this);
        imageLoader = new ImageLoader(this);
        adapter = new ArrayAdapter<String>(this,R.layout.properties,R.id.area_name, MyAreasHandler.list);

        list1 = new ListAdapter1(this);
        list2 = new ListAdapter2(this);
        list = list1;

        setListAdapter(list1);

        //gpsTracker = new GPSTracker(this);
        //Intent intent = new Intent(PropertiesActivity.this,GPSTracker.class);
       // startService(intent);
        this.getListView().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent MyIntent = new Intent(PropertiesActivity.this,ItemActivity.class);
                MyIntent.putExtra("position",position);
                startActivity(MyIntent);
            }
        });

        loadFeedData = new LoadFeedData(this,adapter,list1,progress,label,false,new ArrayList<Appartment>());
        loadFeedData.setArea_name("Oxford");
        loadFeedData.doInBackground("");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = price.getText().toString();
                loadFeedData.responseHandler.myhandler.getAppartments().clear();
                if(s.isEmpty())
                {
                    list1.mEntries.clear();
                    list = list1;
                    setListAdapter(list1);
                }
                else
                {
                    ListAdapter2.mEntries.clear();
                    list=list2;
                    setListAdapter(list2);
                    loadFeedData = new LoadFeedData(v.getContext(),adapter,list2,progress,label,true,new ArrayList<Appartment>());
                }

                loadFeedData.setArea_name(area_names.getSelectedItem().toString());
                loadFeedData.doInBackground(s);
            }
        });

        /*Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String email = extras.getString("email");
            String password = extras.getString("password");
            label.setText("email:" + email + " password:" + password);
        }
        label = (EditText) findViewById(R.id.label);*/
    }




}
