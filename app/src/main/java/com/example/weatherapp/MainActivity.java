package com.example.weatherapp;

import android.os.Bundle;
import android.util.Log;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

import com.example.weatherapp.WeatherService;

public class MainActivity extends AppCompatActivity {

    private TextView textViewWeather;
    private WeatherService weatherService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewWeather = findViewById(R.id.textViewWeather);
        weatherService = new WeatherService();

        // Fetch weather data for "New York" city
        weatherService.getWeather("Seattle", new WeatherService.WeatherCallback() {
            @Override
            public void onWeatherReceived(double temperature, String weatherCondition, String location) {
                // Log the received data
                Log.d("WeatherApp", "Location: " + location);
                Log.d("WeatherApp", "Temperature: " + temperature + "°C");
                Log.d("WeatherApp", "Weather Condition: " + weatherCondition);

                // Display the weather data in the TextView
                String weatherText = "Location: " + location + "\n"
                        + "Temperature: " + temperature + "°C\n"
                        + "Weather Condition: " + weatherCondition;
                textViewWeather.setText(weatherText);
            }

            @Override
            public void onWeatherError(String message) {
                // Log the error message
                Log.e("WeatherApp", "Error: " + message);

                // Display the error message in the TextView
                textViewWeather.setText("Error: " + message);
            }
        });
    }
}
