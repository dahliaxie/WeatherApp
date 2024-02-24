package com.example.weatherapp;

public class Weather {

    private String location;
    private int temperature;
    private String weatherCondition;

    public Weather(String location, int temperature, String weatherCondition) {
        this.location = location;
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
    }

    public String getLocation() {
        return location;
    }

    public int getTemperature() {
        return temperature;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }
}
