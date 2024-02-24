package com.example.weatherapp;
import com.example.weatherapp.GeoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeoApiService {
    @GET("geo/1.0/direct")
    Call<List<GeoResponse>> getGeoData(
            @Query("q") String cityName,
            @Query("state") String stateCode,
            @Query("country") String countryCode,
            @Query("limit") int limit,
            @Query("appid") String apiKey
    );

}
