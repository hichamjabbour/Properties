package com.example.hicham.properties;

import android.app.ProgressDialog;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Hicham on 6/7/2016.
 */
public class MyAsyncHttpResponseHandler extends AsyncHttpResponseHandler {

    ProgressDialog progress;
    SAXParser saxParser;
    MyHandler  myhandler;
    List<Appartment> temp_appartments;
    public static List<List<Appartment>> appartments;
    int min_bathrooms,max_bathrooms;
    List<Appartment> sale , rent;
    TextView label;
    private String area_name;

    public MyAsyncHttpResponseHandler(ProgressDialog _progress,MyHandler myhandler,TextView label,String min_bathrooms,String max_bathrooms)
    {
        this.progress = _progress;
        this.myhandler = myhandler;
        this.label = label;

        this.min_bathrooms = Integer.decode(min_bathrooms);
        this.max_bathrooms = Integer.decode(max_bathrooms);

        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();

            saxParser = factory.newSAXParser();

        }
        catch (Exception e) {label.setText(e.getMessage());}
    }
    @Override
    public void onStart() {
        // called before request is started

        progress.setTitle("Loading");
        progress.setMessage("Wait while loading...");
        progress.show();
        appartments = new ArrayList<List<Appartment>>();
        sale = new ArrayList<Appartment>();
        rent = new ArrayList<Appartment>();
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
        // called when response HTTP status is "200 OK"
        progress.dismiss();
        String s = new String(response);

        try{
            InputStream stream = new ByteArrayInputStream(response);
            saxParser.parse(stream,myhandler);
            temp_appartments = myhandler.getAppartments();
            for(int i=0;i<temp_appartments.size();++i)
            {
                Appartment appartment = temp_appartments.get(i);
                int num = Integer.decode((String)appartment.getAppartment_constitution().getNum_bathrooms());
                if((((String)appartment.getAppartmentCharacteristics().getListing_status()).equals("rent")))
                {
                    if(num>=min_bathrooms && num<=max_bathrooms)
                        rent.add(appartment);

                }

                else if((((String)appartment.getAppartmentCharacteristics().getListing_status()).equals("sale")))
                {
                    if(num>=min_bathrooms && num<=max_bathrooms)
                        sale.add(appartment);
                }

            }

            appartments.add(rent);
            appartments.add(sale);
            LoadFeedData.onPostExecute(appartments);

            //StringBuilder builtstring = new StringBuilder(" ");
            //label.setText("success");
        }
        catch(Exception e)
        {

            label.setText( e.getMessage());

        }

    }

    @Override
    public void onFailure(int statusCode, Header[] headers, byte[] errorResponse, Throwable e) {
        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
        label.setText("failure");
    }

    @Override
    public void onRetry(int retryNo) {
        // called when request is retried
    }

    public String getArea_name() {
        return area_name;
    }

    public void setArea_name(String area_name) {
        this.area_name = area_name;
    }
}
