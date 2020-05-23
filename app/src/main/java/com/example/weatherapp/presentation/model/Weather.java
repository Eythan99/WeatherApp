package com.example.weatherapp.presentation.model;

public class Weather {

    public String title;
    public String sun_rise;
    public String sun_set;
    public String weather_state_name;
    public String weather_state_abbr;
    public String wind_direction_compass;
    public String min_temp;
    public String max_temp;
    public String the_temp;
    public String wind_speed;
    public String air_pressure;
    public String humidity;
    public String visibility;

    public Weather(String title, String sun_rise, String sun_set, String weather_state_name, String weather_state_abbr, String wind_direction_compass, String min_temp, String max_temp, String the_temp, String wind_speed, String air_pressure, String humidity, String visibility) {
        this.title = title;
        this.sun_rise = sun_rise;
        this.sun_set = sun_set;
        this.weather_state_name = weather_state_name;
        this.weather_state_abbr = weather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.the_temp = the_temp;
        this.wind_speed = wind_speed;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
        this.visibility = visibility;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSun_rise() {
        return sun_rise;
    }

    public void setSun_rise(String sun_rise) {
        this.sun_rise = sun_rise;
    }

    public String getSun_set() {
        return sun_set;
    }

    public void setSun_set(String sun_set) {
        this.sun_set = sun_set;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public void setWeather_state_abbr(String weather_state_abbr) {
        this.weather_state_abbr = weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public void setWind_direction_compass(String wind_direction_compass) {
        this.wind_direction_compass = wind_direction_compass;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(String min_temp) {
        this.min_temp = min_temp;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(String max_temp) {
        this.max_temp = max_temp;
    }

    public String getThe_temp() {
        return the_temp;
    }

    public void setThe_temp(String the_temp) {
        this.the_temp = the_temp;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(String wind_speed) {
        this.wind_speed = wind_speed;
    }

    public String getAir_pressure() {
        return air_pressure;
    }

    public void setAir_pressure(String air_pressure) {
        this.air_pressure = air_pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }
}
