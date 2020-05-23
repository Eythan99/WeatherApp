package com.example.weatherapp.presentation;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.weatherapp.Constants;
import com.example.weatherapp.data.WeatherApi;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Injection {

    private static Gson gsonInstance;
    private static WeatherApi weatherApiInstance;
    private static SharedPreferences sharedPreferencesInstance;

    public static Gson getGson(){

        if(gsonInstance == null){
            gsonInstance = new GsonBuilder()
                    .setLenient()
                    .create();
        }
        return gsonInstance;
    }

    public static WeatherApi getWeatherApi(){

        if(weatherApiInstance == null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(getGson()))
                    .build();

            weatherApiInstance = retrofit.create(WeatherApi.class);
        }
        return weatherApiInstance;
    }

    public static SharedPreferences getSharedPreferences(Context context){

        if(sharedPreferencesInstance == null){
            sharedPreferencesInstance = context.getSharedPreferences("application_WeatherA pp", Context.MODE_PRIVATE);
        }
        return sharedPreferencesInstance;
    }
}
