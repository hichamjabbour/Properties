package com.example.hicham.properties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemActivity extends AppCompatActivity {
    TextView descriptionText;
    TextView dateText;
    TextView bathroomsText;
    TextView bedroomsText;
    TextView floorsText;
    TextView displayableAddressText;
    ImageView image;
    TextView priceText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);
        descriptionText = (TextView)findViewById(R.id.description);
        dateText = (TextView)findViewById(R.id.date);
        bathroomsText = (TextView)findViewById(R.id.bathrooms);
        bedroomsText = (TextView)findViewById(R.id.bedrooms);
        floorsText = (TextView)findViewById(R.id.floors);
        displayableAddressText = (TextView)findViewById(R.id.address);
        priceText = (TextView)findViewById(R.id.price);
        image = (ImageView)findViewById(R.id.image);
        Bundle bundle = this.getIntent().getExtras();
        int i = (int)bundle.get("i");
        int i1 = (int)bundle.get("i1");

        String date = ((Appartment)PropertiesActivity.list.getChild(i,i1)).getAppartmentCharacteristics().getDate();
        String description = ((Appartment)PropertiesActivity.list.getChild(i,i1)).getAppartmentCharacteristics().getDescription();
        String bathrooms = "Number of Bathrooms : " + ((Appartment)PropertiesActivity.list.getChild(i,i1)).getAppartment_constitution().getNum_bathrooms();
        String bedrooms = "Number of Bedrooms : " + ((Appartment)PropertiesActivity.list.getChild(i,i1)).getAppartment_constitution().getNum_bedrooms();
        String floors = "Number of Floors : " + ((Appartment)PropertiesActivity.list.getChild(i,i1)).getAppartment_constitution().getNum_floors();
        String displayableAddress = "Address : " + ((Appartment)PropertiesActivity.list.getChild(i,i1)).getAppartmentCharacteristics().getDisplayable_address();
        String price = "Price :" + ((Appartment)PropertiesActivity.list.getChild(i,i1)).getAppartmentCharacteristics().getPrice();

        String url = ((Appartment)PropertiesActivity.list.getChild(i,i1)).getAppartmentCharacteristics().getImage_url();

        dateText.setText(date);
        displayableAddressText.setText(displayableAddress);
        descriptionText.setText(description);
        bathroomsText.setText(bathrooms);
        bedroomsText.setText(bedrooms);
        floorsText.setText(floors);
        priceText.setText(price);
        PropertiesActivity.imageLoader.DisplayImage(url,image);
    }
}
