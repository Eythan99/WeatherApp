package com.example.weatherapp.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;
import com.example.weatherapp.presentation.Injection;
import com.example.weatherapp.presentation.model.Weather;

public class DetailActivity extends AppCompatActivity{

    private TextView txtDetail;
    private ImageView imageWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.textView);
        imageWeather = findViewById(R.id.image);

        Intent intent = getIntent();
        String weatherJson = intent.getStringExtra("weatherKey");
        Weather weather =  Injection.getGson().fromJson(weatherJson, Weather.class);
        showDetail(weather);

        imageWeather.setImageIcon();

    }

    private void showDetail(Weather weather) {
        txtDetail.setText(weather.getAir_pressure());
    }

}
