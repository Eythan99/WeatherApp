package com.example.weatherapp.presentation.model;

import java.util.List;

public class RestWeatherResponse {

    public String title;
    public String sun_rise;
    public String sun_set;
    public List<Consolidated_weather> consolidated_weather;

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
