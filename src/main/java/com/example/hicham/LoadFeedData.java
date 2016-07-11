package com.example.hicham.properties;

import android.app.ProgressDialog;
import android.content.Context;
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
        //static ListAdapter2 mAdapter2;
        ProgressDialog progress;
        TextView label;
        MyHandler myhandler;
        MyAsyncHttpResponseHandler responseHandler;

        public LoadFeedData(BaseExpandableListAdapter _adapter, ProgressDialog _progress, TextView _label,String min_bathrooms, String max_bathrooms, boolean params, List<Object> entries)
        {
        if(!params)
        mAdapter1 = (ListAdapter1) _adapter;
           /* else
        mAdapter2 = (ListAdapter2) _adapter;*/
        progress = _progress;
        label=_label;
        myhandler = new MyHandler();
        responseHandler = new MyAsyncHttpResponseHandler(progress,myhandler,label,min_bathrooms,max_bathrooms);
        }

        public String getArea_name() {
            return responseHandler.getArea_name();
        }

        public void setArea_name(String area_name) {
            responseHandler.setArea_name(area_name);
        }

    public  void doInBackground(String property_type,String min_price,String max_price,String min_bedrooms,String max_bedrooms) {
        RequestParams param = new RequestParams();
        param.put("area",getArea_name());
        param.put("api_key", "32bx8jj66kdxp6shyu88tp47");
        param.put("minimum_price",min_price);
        param.put("maximum_price",max_price);
        param.put("minimum_beds",min_bedrooms);
        param.put("maximum_beds",max_bedrooms);
        param.put("property_type",property_type);
        AsyncHttpClient client = new AsyncHttpClient();
        client.get("http://api.zoopla.co.uk/api/v1/property_listings.xml", param, responseHandler);

    }

        public static void onPostExecute(List<List<Appartment>> _entries) {

           /* if(!price.isEmpty())
            {
                ItemFilter myFilter = (ItemFilter) mAdapter2.getFilter();
                myFilter.filter(price);
                mAdapter2.upDateEntries(_entries);

            }
            else
                {*/
                    mAdapter1.upDateEntries(_entries);
               // }


        }
}