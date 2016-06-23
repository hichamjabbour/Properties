package com.example.hicham.properties;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hicham on 6/7/2016.
 */
public class MyHandler extends DefaultHandler {

    boolean current;
    public String currentValue = null;
    private List<Appartment> appartments;
    private Appartment appartment;
    private Appartment appartmentBoundingBox;

    public MyHandler()
    {
      current=false;
      setAppartments(new ArrayList<Appartment>());

    }

    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        // TODO Auto-generated method stub

        current = true;

        if(localName.equals("response"))
        {
            appartmentBoundingBox = new Appartment();
            appartment = appartmentBoundingBox;
        }
        if (localName.equals("listing"))
        {
            /** Start */
            appartment = new Appartment();
            appartment.setBounding_box(appartmentBoundingBox.getBounding_box());
            addAppartment();
        }

    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {
        // TODO Auto-generated method stub
        current = false;

        switch(localName)
        {
            case "area_name" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,1);
                break;
            }
            case "country" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,2);
                break;
            }
            case "latitude" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,3);
                break;
            }
            case "description" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,4);
                break;
            }
            case "listing_id" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,5);
                break;
            }
            case "listing_status" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,6);
                break;
            }
            case "longitude" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,7);
                break;
            }


            case "outcode" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,8);
                break;
            }
            case "post_town" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,9);
                break;
            }
            case "price" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,10);
                break;
            }
            case "date" :
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,11);
                break;
            }

            case "image_url":
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,12);
                break;
            }
            case "displayable_address":
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,13);
                break;
            }
            case "short_description":
            {
                appartment.set_nonNull_AppartmentCharacteristicsAttributes(currentValue,14);
                break;
            }
            case "num_bathrooms" :
            {
                appartment.set_nonNull_AppartmentConstituionAttributes(currentValue,1);
                break;
            }
            case "num_bedrooms" :
            {
                appartment.set_nonNull_AppartmentConstituionAttributes(currentValue,2);
                break;
            }
            case "num_floors" :
            {
                appartment.set_nonNull_AppartmentConstituionAttributes(currentValue,3);
                break;
            }
            case "num_recepts" :
            {
                appartment.set_nonNull_AppartmentConstituionAttributes(currentValue,4);
                break;
            }
            case "agent_address" :
            {
                appartment.set_nonNull_AgentAttributes(currentValue,1);
                break;
            }
            case "agent_logo" :
            {
                appartment.set_nonNull_AgentAttributes(currentValue,2);
                break;
            }
            case "agent_name" :
            {
                appartment.set_nonNull_AgentAttributes(currentValue,3);
                break;
            }
            case "agent_phone" :
            {
                appartment.set_nonNull_AgentAttributes(currentValue,4);
                break;
            }

            case "county" :
            {
                appartment.set_nonNull_AgentAttributes(currentValue,6);
                break;
            }

            case "latitude_max" :
            {
                appartment.set_nonNull_BoundingBoxes(currentValue,1);
                break;
            }
            case "latitude_min" :
            {
                appartment.set_nonNull_BoundingBoxes(currentValue,2);
                break;
            }
            case "longitude_max" :
            {
                appartment.set_nonNull_BoundingBoxes(currentValue,3);
                break;
            }
            case "longitude_min" :
            {
                appartment.set_nonNull_BoundingBoxes(currentValue,4);
                break;
            }




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

    public List<Appartment> getAppartments() {
        return appartments;
    }


    public Appartment getAppartment() {
        return appartment;
    }

    public void setAppartment(Appartment _appartment) {
        this.appartment = _appartment;
    }

    public void addAppartment() {
        getAppartments().add(appartment);
    }

    public void setAppartments(List<Appartment> appartments) {
        this.appartments = appartments;
    }
}
