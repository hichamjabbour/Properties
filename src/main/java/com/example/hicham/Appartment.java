package com.example.hicham.properties;

/**
 * Created by Hicham on 6/6/2016.
 */
public class Appartment {

    public Bounding_Box getBounding_box() {
        return bounding_box;
    }

    public void setBounding_box(Bounding_Box bounding_box) {
        this.bounding_box = bounding_box;
    }

    public Agent getAgent() {
        return agent;
    }

    public void setAgent(Agent agent) {
        this.agent = agent;
    }

    public Appartment_Constitution getAppartment_constitution() {
        return appartment_constitution;
    }

    public void setAppartment_constitution(Appartment_Constitution appartment_constitution) {
        this.appartment_constitution = appartment_constitution;
    }

    public void set_nonNull_AppartmentCharacteristicsAttributes(String value,int index)
    {
        switch(index)
        {
            case 1:
            {
                appartmentCharacteristics.setArea_name(value);
                break;
            }
            case 2:
            {
                appartmentCharacteristics.setCountry(value);
                break;
            }
            case 3:
            {
                appartmentCharacteristics.setLatitude(value);
                break;
            }
            case 4:
            {
                appartmentCharacteristics.setDescription(value);
                break;
            }
            case 5:
            {
                appartmentCharacteristics.setListing_id(value);
                break;
            }
            case 6:
            {
                appartmentCharacteristics.setListing_status(value);
                break;
            }
            case 7:
            {
                appartmentCharacteristics.setLongitude(value);
                break;
            }
            case 8:
            {
                appartmentCharacteristics.setOutcode(value);
                break;
            }
            case 9:
            {
                appartmentCharacteristics.setPost_town(value);
                break;
            }
            case 10:
            {
                appartmentCharacteristics.setPrice(value);
                break;
            }
            case 11:
            {
                appartmentCharacteristics.setDate(value);
                break;
            }

            case 12:
            {
                appartmentCharacteristics.setImage_url(value);
                break;
            }

            case 13:
            {
                appartmentCharacteristics.setDisplayable_address(value);
                break;
            }
            case 14:
            {
                appartmentCharacteristics.setShort_description(value);
                break;
            }
        }
    }

    public void set_nonNull_AppartmentConstituionAttributes(String value,int index)
    {
        switch(index)
        {
            case 1:
            {
                appartment_constitution.setNum_bathrooms(value);
                break;
            }
            case 2:
            {
                appartment_constitution.setNum_bedrooms(value);
                break;
            }
            case 3:
            {
                appartment_constitution.setNum_floors(value);
                break;
            }
            case 4:
            {
                appartment_constitution.setNum_recepts(value);
                break;
            }
        }
    }

    public void set_nonNull_AgentAttributes(String value,int index)
    {
        switch(index)
        {
            case 1:
            {
                agent.setAgent_address(value);
                break;
            }
            case 2:
            {
                agent.setAgent_logo(value);
                break;
            }
            case 3:
            {
                agent.setAgent_name(value);
                break;
            }
            case 4:
            {
                agent.setAgent_phone(value);
                break;
            }
            case 5:
            {
                agent.setAgent_country(value);
                break;
            }
            case 6:
            {
                agent.setAgent_county(value);
                break;
            }
        }
    }

    public void set_nonNull_BoundingBoxes(String value,int index)
    {
        switch(index)
        {
            case 1:
            {
                bounding_box.setLatitude_max(value);
                break;
            }
            case 2:
            {
                bounding_box.setLatitude_min(value);
                break;
            }
            case 3:
            {
                bounding_box.setLongitude_max(value);
                break;
            }
            case 4:
            {
                bounding_box.setLongitude_min(value);
                break;
            }
        }
    }



    private AppartmentCharacteristics appartmentCharacteristics;
    private Bounding_Box bounding_box;
    private Agent agent;
    private Appartment_Constitution appartment_constitution;


    public Appartment()
    {
        agent = new Agent();
        bounding_box = new Bounding_Box();
        setAppartmentCharacteristics(new AppartmentCharacteristics());
        appartment_constitution = new Appartment_Constitution();
    }


    public AppartmentCharacteristics getAppartmentCharacteristics() {
        return appartmentCharacteristics;
    }

    public void setAppartmentCharacteristics(AppartmentCharacteristics appartmentCharacteristics) {
        this.appartmentCharacteristics = appartmentCharacteristics;
    }
}
