package com.example.weatherapp;

import com.google.gson.annotations.SerializedName;

public class GeoResponse {
    @SerializedName("lat")
    private double latitude;

    @SerializedName("lon")
    private double longitude;

    @SerializedName("country")
    private String country;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }
}
