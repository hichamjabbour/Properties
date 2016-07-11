package com.example.hicham.properties;

import android.app.DownloadManager;
import android.app.ExpandableListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.firebase.client.Firebase;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hicham on 6/3/2016.
 */
public class PropertiesActivity extends ExpandableListActivity implements View.OnClickListener{
    URL url;
    Object array;
    TextView label;
    Spinner spinner;
    Spinner min_price,max_price,min_bedrooms,max_bedrooms,property_type;
    Button search;
    ProgressDialog progress;
    public static BaseExpandableListAdapter list;
    ListAdapter1 list1;
    ListAdapter2 list2;
    LoadFeedData loadFeedData;
    public static DownloadManager manager;
    public static ImageLoader  imageLoader;
    public static GPSTracker gpsTracker;
    public static Location location;
    private List<Appartment>originalData;
    private List<Appartment>filteredData;
    public static List<String> areas;
    public static final int URL_LOADER = 0;
    public static Spinner area_names;
    public Spinner min_bathroom,max_bathroom;
    public ListView left_drawer;
    public TextView displayName;
    public ImageView imageView;
    public static List<View> propertiesListView;
    public static List<View> agentsListView;
    private static final int TAKE_PHOTO_REQUEST_CODE = 1;
    private static final int PHOTO_REQUEST_CODE = -1;
    Firebase userRef;

    String email;
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.properties);
        area_names = (Spinner) findViewById(R.id.area_name);
        manager = (DownloadManager) this.getSystemService(Context.DOWNLOAD_SERVICE);
        label = (TextView) findViewById(R.id.label);
        min_price = (Spinner) findViewById(R.id.min_price);
        max_price = (Spinner) findViewById(R.id.max_price);
        min_bedrooms = (Spinner) findViewById(R.id.min_bedrooms) ;
        max_bedrooms = (Spinner) findViewById(R.id.max_bedrooms);
        min_bathroom = (Spinner) findViewById(R.id.min_bathrooms);
        max_bathroom = (Spinner) findViewById(R.id.min_bathrooms);
        property_type = (Spinner) findViewById(R.id.property_type);
        spinner = (Spinner)findViewById(R.id.spinner);
        search = (Button) findViewById(R.id.search);
        progress = new ProgressDialog(this);
        imageLoader = new ImageLoader(this);
        left_drawer = (ListView)findViewById(R.id.left_drawer);
        imageView = (ImageView)findViewById(R.id.icon);
        displayName = (TextView)findViewById(R.id.name);
        Bundle bundle = this.getIntent().getExtras();
        final String[] Picture = {(String) bundle.get("Picture")};
        final String[] Name = new String[]{(String) bundle.get("Name")};
        email = (String)bundle.get("email");
        displayName.setText(Name[0]);
        userRef = LoginActivity.myFirebaseRef.child("Users");
        if(Picture[0] == null)
        {
            imageLoader.DisplayImage(Picture[0],imageView);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Name[0] = userRef.child(email.substring(0,email.indexOf("."))).child("name").toString();
                    Picture[0] = userRef.child(email.substring(0,email.indexOf("."))).child("Uri").toString();
                    Bitmap image = imageView.getDrawingCache();
                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                    intent.setType("image/jpeg");
                    startActivityForResult(intent,TAKE_PHOTO_REQUEST_CODE);

                }
            });
        }
        else
        {
            imageLoader.DisplayImage(Picture[0],imageView);
        }
        propertiesListView = new ArrayList<View>();
        agentsListView = new ArrayList<View>();
        list1 = new ListAdapter1(this);
        list2 = new ListAdapter2(this);
        list = list1;

        setListAdapter(list1);
        //gpsTracker = new GPSTracker(this);
        //Intent intent = new Intent(PropertiesActivity.this,GPSTracker.class);
       // startService(intent);

        left_drawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 0 :
                        Intent intent = new Intent(PropertiesActivity.this,SavedPropertiesActivity.class);
                        startActivity(intent);
                        break;

                    case 1:
                        Intent intent1 = new Intent(PropertiesActivity.this,SavedAgentsActivity.class);
                        startActivity(intent1);
                        break;
                }
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i)
                {
                    case 1 :
                        Intent intent = new Intent(PropertiesActivity.this,LoginActivity.class);
                        startActivity(intent);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        this.getExpandableListView().setOnChildClickListener(new ChildClickListener(this));

        this.getExpandableListView().setOnItemLongClickListener(new MyItemListener());

        String min_bathrooms = "0";
        String max_bathrooms = "10";


        loadFeedData = new LoadFeedData(list1,progress,label,min_bathrooms,max_bathrooms,false, new ArrayList<Object>());
        loadFeedData.setArea_name("Oxford");
        loadFeedData.doInBackground("","","","","");

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String min = (String) Array.get(getResources().getStringArray(R.array.prices_values),min_price.getSelectedItemPosition());
                String max = (String) Array.get(getResources().getStringArray(R.array.prices_values),max_price.getSelectedItemPosition());
                String min_bed = (String) Array.get(getResources().getStringArray(R.array.num_bedroom_values),min_bedrooms.getSelectedItemPosition());
                String max_bed = (String) Array.get(getResources().getStringArray(R.array.num_bedroom_values),max_bedrooms.getSelectedItemPosition());
                String min_bathrooms = min_bathroom.getSelectedItem().toString();
                String max_bathrooms = max_bathroom.getSelectedItem().toString();
                loadFeedData = new LoadFeedData(list1,progress,label,min_bathrooms,max_bathrooms,false, new ArrayList<Object>());
                /*if(s.isEmpty())
                {*/
                    list = list1;
                    setListAdapter(list1);
                /*}
                else
                {
                    ListAdapter2.mEntries.clear();
                    list=list2;
                    setListAdapter(list2);
                    loadFeedData = new LoadFeedData(list2,progress,label,true,new ArrayList<Appartment>());
                }
*/
                loadFeedData.setArea_name(area_names.getSelectedItem().toString());
                loadFeedData.doInBackground(property_type.getSelectedItem().toString(),min,max,min_bed,max_bed);
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


    @Override
    public void onClick(View view) {
        this.getExpandableListView().expandGroup((int) this.getSelectedPosition());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==PHOTO_REQUEST_CODE)
        {
            Uri uri = data.getData();
            imageView.setImageURI(uri);
            Map<String, Object> fields = new HashMap<String, Object>();
            SignUpActivity.User user = new SignUpActivity.User("hicham",email,uri.toString());
            fields.put(email, user);
            userRef.setValue(fields);
        }




    }
}
