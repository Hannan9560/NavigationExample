package com.example.hannantalukder.navigationexample.PlacesActivity.ModelClass;

/**
 * Created by Hannan Talukder on 5/15/2017.
 */

public class PlacesName {
    private String name;
    private String address;
    private Double lat;
    private Double lon;

    public PlacesName(String name, String address, Double lat, Double lon) {
        this.name = name;
        this.address = address;
        this.lat = lat;
        this.lon = lon;
    }

    public PlacesName() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }
}
