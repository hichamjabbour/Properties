package com.example.hicham.properties;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.widget.TextView;

import com.google.android.gms.common.images.ImageManager;
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
    MyAreasHandler myAreasHandler;
    public static List<Appartment> appartments;
    TextView label;
    private String area_name;

    public MyAsyncHttpResponseHandler(ProgressDialog _progress,MyHandler myhandler,TextView label)
    {
        this.progress = _progress;
        this.myhandler = myhandler;
        this.label = label;
        appartments = new ArrayList<Appartment>();
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
    }

    @Override
    public void onSuccess(int statusCode, Header[] headers, byte[] response) {
        // called when response HTTP status is "200 OK"
        progress.dismiss();
        String s = new String(response);

        try{
            InputStream stream = new ByteArrayInputStream(response);
            saxParser.parse(stream,myhandler);
            appartments = myhandler.getAppartments();
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
