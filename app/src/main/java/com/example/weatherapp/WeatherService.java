package com.example.weatherapp;

import android.util.Log;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherService {
    private static final String BASE_URL = "https://api.openweathermap.org/";

    // Define endpoints
    private static final String GEO_ENDPOINT = "geo/1.0/";
    private static final String WEATHER_ENDPOINT = "data/2.5/";

    // Define API key
    private static final String API_KEY = "2ad573b092765246adb97cc34be9aedd";

    // Define interfaces
    private GeoApiService geoApiService;
    private WeatherApiService weatherApiService;

    public WeatherService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        geoApiService = retrofit.create(GeoApiService.class);
        weatherApiService = retrofit.create(WeatherApiService.class);
    }

    public void getWeather(String city, final WeatherCallback callback) {
        final String STATE_CODE_NEW_YORK = "WA"; // State code for New York
        final String COUNTRY_CODE_USA = "US"; // Country code for USA

        // Encode the city name
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8);

        Call<List<GeoResponse>> geoCall = geoApiService.getGeoData(encodedCity, STATE_CODE_NEW_YORK, COUNTRY_CODE_USA, 1, API_KEY);
        Log.d("WeatherService", "Geo API URL: " + geoCall.request().url().toString());
        geoCall.enqueue(new Callback<List<GeoResponse>>() {
            @Override
            public void onResponse(Call<List<GeoResponse>> call, Response<List<GeoResponse>> response) {
                if (response.isSuccessful() && response.body() != null && !response.body().isEmpty()) {
                    GeoResponse geoResponse = response.body().get(0);
                    double latitude = geoResponse.getLatitude();
                    double longitude = geoResponse.getLongitude();
                    String countryCode = geoResponse.getCountry();
                    String cityWeatherUrl = WEATHER_ENDPOINT + "weather?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
                    Call<WeatherResponse> weatherCall = weatherApiService.getWeather(latitude, longitude, API_KEY);
                    weatherCall.enqueue(new Callback<WeatherResponse>() {
                        @Override
                        public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                WeatherResponse weatherResponse = response.body();
                                double temperature = weatherResponse.getMain().getTemp();
                                // Convert temperature units based on country code
                                if ("US".equalsIgnoreCase(countryCode)) {
                                    // Convert to Fahrenheit and round to the nearest whole number
                                    temperature = Math.round(convertKelvinToFahrenheit(temperature));
                                } else {
                                    // Convert to Celsius (assuming default is Kelvin) and round to the nearest whole number
                                    temperature = Math.round(convertKelvinToCelsius(temperature));
                                }
                                String weatherCondition = weatherResponse.getWeather().get(0).getMain();
                                String city = weatherResponse.getName();
                                callback.onWeatherReceived(temperature, weatherCondition, city);
                            } else {
                                callback.onWeatherError("Failed to fetch weather data");
                            }
                        }

                        @Override
                        public void onFailure(Call<WeatherResponse> call, Throwable t) {
                            callback.onWeatherError(t.getMessage());
                        }
                    });
                } else {
                    callback.onWeatherError("Failed to fetch location data");
                }
            }

            @Override
            public void onFailure(Call<List<GeoResponse>> call, Throwable t) {
                callback.onWeatherError(t.getMessage());
            }
        });
    }

    private double convertKelvinToFahrenheit(double kelvin) {
        return (kelvin - 273.15) * 9 / 5 + 32;
    }

    private double convertKelvinToCelsius(double kelvin) {
        return kelvin - 273.15;
    }

    public interface WeatherCallback {
        void onWeatherReceived(double temperature, String weatherCondition, String city);
        void onWeatherError(String message);
    }
}
