package com.example.hicham.properties;

/**
 * Created by Hicham on 6/7/2016.
 */
class Bounding_Box
{
    private String latitude_max;
    private String latitude_min;
    private String longitude_max;
    private String longitude_min;

    public String getLatitude_max() {
        return latitude_max;
    }

    public void setLatitude_max(String latitude_max) {
        this.latitude_max = latitude_max;
    }

    public String getLatitude_min() {
        return latitude_min;
    }

    public void setLatitude_min(String latitude_min) {
        this.latitude_min = latitude_min;
    }

    public String getLongitude_max() {
        return longitude_max;
    }

    public void setLongitude_max(String longitude_max) {
        this.longitude_max = longitude_max;
    }

    public String getLongitude_min() {
        return longitude_min;
    }

    public void setLongitude_min(String longitude_min) {
        this.longitude_min = longitude_min;
    }
}
