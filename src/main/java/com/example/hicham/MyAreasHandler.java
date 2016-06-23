package com.example.hicham.properties;

/**
 * Created by Hicham on 6/16/2016.
 */

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hicham on 6/7/2016.
 */
public class MyAreasHandler extends DefaultHandler {

    boolean current;
    public String currentValue = null;
    public static List<String> list;

    public MyAreasHandler()
    {
        current=false;
        list = new ArrayList<String>();

    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub

        current = true;


    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        current = false;

        if(localName=="value")
        {
          list.add(currentValue);
        }



    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {
        // TODO Auto-generated method stub

        if(current)
        {
            currentValue = new String(ch, start, length);
            current=false;
        }
    }

}
