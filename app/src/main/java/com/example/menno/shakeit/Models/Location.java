package com.example.menno.shakeit.Models;

/**
 * Created by Menno on 9-6-2016.
 */
public class Location {
    private int id;
    private double longitude;
    private double latitude;
    private String name;
    private String city;

    public Location(int id, double longitude, double latitude, String city) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.city = city;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
