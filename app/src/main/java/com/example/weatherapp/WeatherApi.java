package com.example.weatherapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface WeatherApi {

    @GET
    Call<RestWeatherResponse> getWeatherResponse (@Url String url);

    @GET("/api/location/search/")
    Call<List<Place>> getWoeidResponse(@Query("lattlong") String gpscoordinate);
}
