# Weather App

This Weather App is a simple application that allows users to retrieve weather information for a given city. It uses the OpenWeatherMap API to fetch weather data based on the provided city name, state, and country.

## Features

- Retrieve current weather information for a specific city.
- Display temperature, weather condition, and city name.
- Support for converting temperature units based on the country.

## Getting Started

To get started with the Weather App, follow these steps:

1. Clone the repository to your local machine:

2. Open the project in Android Studio.

3. Replace the API key:
    - Obtain an API key from [OpenWeatherMap](https://openweathermap.org/api).
    - Replace the `API_KEY` variable in the `WeatherService.java` file located in the `com.example.weatherapp` package with your API key.

4. Replace the city, state, and country code:
    - In the `WeatherService.java` file, modify the `getWeather()` method to use the desired city, state, and country code.

5. Build and run the application on your device or emulator.

## Next Steps

- Implement functionality to retrieve the current location of the user's device.
- Enhance the user interface to allow users to input different locations.
- Improve the frontend by adding graphics and visual elements to enhance the user experience.
