package com.example.weatherapp;

import java.util.List;

public class WeatherResponse {
    private WeatherData main;
    private List<WeatherDescription> weather;
    private String name; // Add a field to store the name of the city

    public WeatherData getMain() {
        return main;
    }

    public List<WeatherDescription> getWeather() {
        return weather;
    }

    // Getter method for the city name
    public String getName() {
        return name;
    }

    // Setter method for the city name
    public void setName(String name) {
        this.name = name;
    }
}
