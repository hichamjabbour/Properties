package com.example.hicham.properties;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.*;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;

import java.util.List;

/**
 * Created by Hicham on 6/8/2016.
 */
public class LoadFeedData

    {
        Context mContext;
        static ListAdapter1 mAdapter1;
        static ListAdapter2 mAdapter2;
        ProgressDialog progress;
        TextView label;
        MyHandler myhandler;
        MyAreasHandler myAreasHandler;
        MyAsyncHttpResponseHandler responseHandler;
        public static MyAreasResponseHandler myAreaResponseHandler;
        public static ArrayAdapter<String> adapter;
        static String price;

        public LoadFeedData(Context _mContext,ArrayAdapter<String> areas_adapter, ListAdapter _adapter, ProgressDialog _progress, TextView _label, boolean params, List<Appartment> entries)
        {
          mContext = _mContext;
          adapter = areas_adapter;
        if(!params)
        mAdapter1 = (ListAdapter1) _adapter;
            else
        mAdapter2 = (ListAdapter2) _adapter;
        progress = _progress;
        label=_label;
        myhandler = new MyHandler();
        myAreasHandler = new MyAreasHandler();
        responseHandler = new MyAsyncHttpResponseHandler(progress,myhandler,label);
        myAreaResponseHandler = new MyAreasResponseHandler(progress,myAreasHandler,label);
        myAreaResponseHandler.execute();
        }

        public String getArea_name() {
            return responseHandler.getArea_name();
        }

        public void setArea_name(String area_name) {
            responseHandler.setArea_name(area_name);
        }

    public  void doInBackground(String _price) {
        RequestParams param = new RequestParams();
        param.put("area",getArea_name());
        param.put("api_key", "32bx8jj66kdxp6shyu88tp47");
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://api.zoopla.co.uk/api/v1/property_listings.xml", param, responseHandler);
        this.price=_price;
    }

        public static void onPostAreasExecute() {
            adapter.addAll(MyAreasHandler.list);
            adapter.notifyDataSetChanged();
        }

        public static void onPostExecute(List<Appartment>_entries) {

            if(!price.isEmpty())
            {
                ItemFilter myFilter = (ItemFilter) mAdapter2.getFilter();
                myFilter.filter(price);
                mAdapter2.upDateEntries(_entries);

            }
            else
                {
                    mAdapter1.upDateEntries(_entries);
                }


        }
}