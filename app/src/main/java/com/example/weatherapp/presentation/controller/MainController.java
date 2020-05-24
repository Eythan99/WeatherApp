package com.example.weatherapp.presentation.controller;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import com.example.weatherapp.Constants;
import com.example.weatherapp.presentation.Injection;
import com.example.weatherapp.presentation.model.Place;
import com.example.weatherapp.presentation.model.RestWeatherResponse;
import com.example.weatherapp.presentation.model.Weather;
import com.example.weatherapp.presentation.view.MainActivity;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainController implements LocationListener {

    private SharedPreferences sharedPreferences;
    private Gson gson;
    private MainActivity view;

    private LocationManager locationManager;
    private String latitude, longitude;

    private List<String> listWoeid = new ArrayList<>();

    private Integer listWoeidIncrementation;
    private Integer listWoeidSize;

    public MainController(MainActivity mainActivity, Gson gson, SharedPreferences sharedPreferences) {
        this.view = mainActivity;
        this.gson = gson;
        this.sharedPreferences = sharedPreferences;
    }

    public void onStart(){

        view.weatherData = getDataFromCache();

        if(view.weatherData != null){
            view.showList();
        }else{
            view.weatherData = new ArrayList<Weather>();
            locationManager = (LocationManager) view.getSystemService(Context.LOCATION_SERVICE);
            if (ActivityCompat.checkSelfPermission(view, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(view, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        }
    }

    private List<Weather> getDataFromCache() {

        String jsonWeather = sharedPreferences.getString(Constants.KEY_WEATHER_LIST, null);

        if(jsonWeather == null){
            return null;
        }else{
            Type listType = new TypeToken<List<Weather>>(){}.getType();
            return gson.fromJson(jsonWeather, listType);
        }
    }

    private void makeApiWoeidCall(){

        Call<List<Place>> call = Injection.getWeatherApi().getWoeidResponse(latitude + "," + longitude);
        call.enqueue(new Callback<List<Place>>() {
            @Override
            public void onResponse(Call<List<Place>> call, Response<List<Place>> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Place> woeid =response.body();
                    for(int i=0; i<woeid.size(); i++){
                        listWoeid.add(woeid.get(i).woeid);
                    }
                    listWoeidIncrementation = 0;
                    listWoeidSize = woeid.size();
                    makeApiWeatherCall(listWoeid.get(listWoeidIncrementation));
                }else{
                    view.showError();
                }
            }
            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                view.showError();
            }
        });
    }

    private void makeApiWeatherCall(String woeid){


        Call<RestWeatherResponse> call = Injection.getWeatherApi().getWeatherResponse("https://www.metaweather.com/api/location/"+woeid);
        call.enqueue(new Callback<RestWeatherResponse>() {
            @Override
            public void onResponse(Call<RestWeatherResponse> call, Response<RestWeatherResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    RestWeatherResponse currentWeather =response.body();
                    Weather weather = new Weather(currentWeather.getTitle(), currentWeather.getSun_rise(), currentWeather.getSun_set(),
                            currentWeather.getConsolidated_weather().get(0).getWeather_state_name(), currentWeather.getConsolidated_weather().get(0).getWeather_state_abbr(),
                            currentWeather.getConsolidated_weather().get(0).getWind_direction_compass(),
                            currentWeather.getConsolidated_weather().get(0).getMin_temp().substring(0,currentWeather.getConsolidated_weather().get(0).getMin_temp().indexOf(".")),
                            currentWeather.getConsolidated_weather().get(0).getMax_temp().substring(0,currentWeather.getConsolidated_weather().get(0).getMax_temp().indexOf(".")),
                            currentWeather.getConsolidated_weather().get(0).getThe_temp().substring(0,currentWeather.getConsolidated_weather().get(0).getThe_temp().indexOf(".")),
                            currentWeather.getConsolidated_weather().get(0).getWind_speed().substring(0,currentWeather.getConsolidated_weather().get(0).getWind_speed().indexOf(".")),
                            currentWeather.getConsolidated_weather().get(0).getAir_pressure().substring(0,currentWeather.getConsolidated_weather().get(0).getAir_pressure().indexOf(".")),
                            currentWeather.getConsolidated_weather().get(0).getHumidity(),
                            currentWeather.getConsolidated_weather().get(0).getVisibility().substring(0,currentWeather.getConsolidated_weather().get(0).getVisibility().indexOf(".")));
                    view.weatherData.add(weather);
                    listWoeidIncrementation++;
                    if(listWoeidIncrementation.equals(listWoeidSize)){
                        saveList();
                        view.showList();
                    }else{
                        makeApiWeatherCall(listWoeid.get(listWoeidIncrementation));
                    }
                }else{
                    view.showError();
                }
            }

            @Override
            public void onFailure(Call<RestWeatherResponse> call, Throwable t) {
                view.showError();
            }
        });

    }

    private void saveList() {

        String jsonString = gson.toJson(view.weatherData);

        sharedPreferences
                .edit()
                .putString(Constants.KEY_WEATHER_LIST, jsonString)
                .apply();

        Toast.makeText(view.getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = Double.toString(location.getLatitude());
        longitude = Double.toString(location.getLongitude());
        locationManager.removeUpdates(this);
        makeApiWoeidCall();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public void onItemClick(Weather weather) {
        view.navigateToDetails(weather);
    }
}
