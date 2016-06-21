package com.example.hicham.properties;

import android.app.DownloadManager;
import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.Layout;
import android.view.View;
import android.widget.*;

import java.io.FileDescriptor;
import java.io.FileNotFoundException;

/**
 * Created by Hicham on 6/7/2016.
 */
public class Agent {
private static int i=0;
private int position;
private long myDownloadReference;
private String agent_address;
private Bitmap agent_logo;
private String agent_name;
private String agent_phone;
private String agent_country;
private String agent_county;
private String agentImageURL;
    public Agent()
    {
        position = i;
        ++i;
    }

    public String getAgent_address() {
        return agent_address;
    }

    public void setAgent_address(String agent_address) {
        this.agent_address = agent_address;
    }

    public void setAgent_logo(final String _agentImageURL) {
                this.agentImageURL = _agentImageURL;

    }

    public String getAgent_logo()
    {
        return this.agentImageURL;
    }
    public String getAgent_name() {
        return agent_name;
    }

    public void setAgent_name(String agent_name) {
        this.agent_name = agent_name;
    }

    public String getAgent_phone() {
        return agent_phone;
    }

    public void setAgent_phone(String agent_phone) {
        this.agent_phone = agent_phone;
    }

    public String getAgent_country() {
        return agent_country;
    }

    public void setAgent_country(String agent_country) {
        this.agent_country = agent_country;
    }

    public String getAgent_county() {
        return agent_county;
    }

    public void setAgent_county(String agent_county) {
        this.agent_county = agent_county;
    }

}
