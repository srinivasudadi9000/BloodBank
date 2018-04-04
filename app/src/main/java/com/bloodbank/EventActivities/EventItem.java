package com.bloodbank.EventActivities;

/**
 * Created by USER on 2/20/2018.
 */

public class EventItem {
    String id;
    String date1;
    String location;
    String name;
    Double lat,longitude;

    public EventItem() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EventItem(String id, String date1, String location, String name,Double lat,Double longitude) {
        this.id = id;
        this.date1 = date1;
        this.location = location;
        this.name = name;
        this.lat = lat;
        this.longitude = longitude;
    }
}