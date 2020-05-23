package com.example.weatherapp;

class Consolidated_weather {

    private String weather_state_name;
    private String weather_state_abbr;
    private String wind_direction_compass;
    private String min_temp;
    private String max_temp;
    private String the_temp;
    private String wind_speed;
    private String air_pressure;
    private String humidity;
    private String visibility;

    public Consolidated_weather(String weather_state_name, String weather_state_abbr, String wind_direction_compass, String min_temp, String max_temp, String the_temp, String wind_speed, String air_pressure, String humidity, String visibility) {
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

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public String getWeather_state_abbr() {
        return weather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public String getMin_temp() {
        return min_temp;
    }

    public String getMax_temp() {
        return max_temp;
    }

    public String getThe_temp() {
        return the_temp;
    }

    public String getWind_speed() {
        return wind_speed;
    }

    public String getAir_pressure() {
        return air_pressure;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getVisibility() {
        return visibility;
    }
}
