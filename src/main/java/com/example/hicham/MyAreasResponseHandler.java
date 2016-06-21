package com.example.hicham.properties;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import com.loopj.android.http.AsyncHttpResponseHandler;

import org.xml.sax.SAXException;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cz.msebera.android.httpclient.Header;

/**
 * Created by Hicham on 6/16/2016.
 */
public class MyAreasResponseHandler extends AsyncTask<Void,Void,List<String>> {
    public static SAXParser parser;
    MyAreasHandler handler;
    ProgressDialog progress;
    TextView label;
    File file;
    public MyAreasResponseHandler(ProgressDialog _progress, MyAreasHandler _handler, TextView _label)
    {
     handler = _handler;
     progress = _progress;
     label = _label;
    }

    @Override
    protected List<String> doInBackground(Void... params) {
        try {

            parser.parse(file,handler);


        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return MyAreasHandler.list;
    }

    @Override
    public void onPostExecute(List<String> list) {
        LoadFeedData.adapter.addAll(list);
        LoadFeedData.adapter.notifyDataSetChanged();
    }
}
