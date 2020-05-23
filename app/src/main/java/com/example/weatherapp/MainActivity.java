package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements LocationListener {

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    protected LocationManager locationManager;
    protected String latitude, longitude;

    List<Weather> weatherData = new ArrayList<Weather>();

    private List<String> listWoeid = new ArrayList<>();

    private Integer listWoeidIncrementation;
    private Integer listWoeidSize;

    String fin = null;

    private static final String BASE_URL = "https://www.metaweather.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);

    }

    private void showList() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(weatherData);
        recyclerView.setAdapter(mAdapter);
    }

    private void makeApiWoeidCall(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        Call<List<Place>> call = weatherApi.getWoeidResponse(latitude + "," + longitude);
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
                    showError();
                }
            }
            @Override
            public void onFailure(Call<List<Place>> call, Throwable t) {
                showError();
            }
        });
    }

    private void makeApiWeatherCall(String woeid){

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);

        Call<RestWeatherResponse> call = weatherApi.getWeatherResponse("https://www.metaweather.com/api/location/"+woeid);
        call.enqueue(new Callback<RestWeatherResponse>() {
            @Override
            public void onResponse(Call<RestWeatherResponse> call, Response<RestWeatherResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    RestWeatherResponse currentWeather =response.body();
                    Weather weather = new Weather(currentWeather.getTitle(), currentWeather.getSun_rise(), currentWeather.getSun_set(), currentWeather.getConsolidated_weather().get(0).getWeather_state_name(),
                            currentWeather.getConsolidated_weather().get(0).getWeather_state_abbr(), currentWeather.getConsolidated_weather().get(0).getWind_direction_compass(), currentWeather.getConsolidated_weather().get(0).getMin_temp(),
                            currentWeather.getConsolidated_weather().get(0).getMax_temp(), currentWeather.getConsolidated_weather().get(0).getThe_temp(), currentWeather.getConsolidated_weather().get(0).getWind_speed(),
                            currentWeather.getConsolidated_weather().get(0).getAir_pressure(), currentWeather.getConsolidated_weather().get(0).getHumidity(), currentWeather.getConsolidated_weather().get(0).getVisibility());
                    weatherData.add(weather);
                    listWoeidIncrementation++;
                    if(listWoeidIncrementation.equals(listWoeidSize)){
                        showList();
                    }else{
                        makeApiWeatherCall(listWoeid.get(listWoeidIncrementation));
                    }
                }else{
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestWeatherResponse> call, Throwable t) {
                showError();
            }
        });

    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
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
}
