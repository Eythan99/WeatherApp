package com.example.weatherapp;

import java.util.List;

public class RestWeatherResponse {

    private String title;
    private String sun_rise;
    private String sun_set;
    private List<Consolidated_weather> consolidated_weather;

    public RestWeatherResponse(String title, String sun_rise, String sun_set, List<Consolidated_weather> consolidated_weather) {
        this.title = title;
        this.sun_rise = sun_rise;
        this.sun_set = sun_set;
        this.consolidated_weather = consolidated_weather;
    }

    public String getTitle() {
        return title;
    }

    public String getSun_rise() {
        return sun_rise;
    }

    public String getSun_set() {
        return sun_set;
    }

    public List<Consolidated_weather> getConsolidated_weather() {
        return consolidated_weather;
    }
}
