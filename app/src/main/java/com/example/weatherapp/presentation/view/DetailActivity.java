package com.example.weatherapp.presentation.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.weatherapp.R;
import com.example.weatherapp.presentation.Injection;
import com.example.weatherapp.presentation.model.Weather;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity{

    private TextView txtDetail;
    private ImageView imageWeather;
    private ImageView imageLeve;
    private ImageView imageCouche;
    private TextView txtLeve;
    private TextView txtCouche;
    private TextView txtMin;
    private TextView txtTemp;
    private TextView txtMax;
    private TextView txtPressure;
    private TextView txtHumidity;
    private TextView txtVisibility;
    private TextView txtVent;
    private ImageView imagePressure;
    private ImageView imageHumidity;
    private ImageView imageVisibility;
    private ImageView imageVent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        txtDetail = findViewById(R.id.titre);
        imageWeather = findViewById(R.id.image);
        imageLeve = findViewById(R.id.leve);
        imageCouche = findViewById(R.id.couche);
        txtLeve = findViewById(R.id.leveH);
        txtCouche = findViewById(R.id.coucheH);
        txtMin = findViewById(R.id.min);
        txtTemp = findViewById(R.id.temp);
        txtMax = findViewById(R.id.max);
        txtPressure = findViewById(R.id.pression);
        txtHumidity = findViewById(R.id.humidity);
        txtVisibility = findViewById(R.id.visibility);
        txtVent = findViewById(R.id.vent);
        imagePressure = findViewById(R.id.imagePression);
        imageHumidity = findViewById(R.id.imageHumidity);
        imageVisibility = findViewById(R.id.imageVisibility);
        imageVent = findViewById(R.id.imageVent);

        Intent intent = getIntent();
        String weatherJson = intent.getStringExtra("weatherKey");
        Weather weather =  Injection.getGson().fromJson(weatherJson, Weather.class);
        showDetail(weather);

    }

    private void showDetail(Weather weather) {
        Picasso.get().load("https://www.metaweather.com/static/img/weather/png/" + weather.getWeather_state_abbr() + ".png").into(imageWeather);

        Picasso.get().load("https://github.com/Eythan99/WeatherApp/blob/master/Image/sunrise.png?raw=true").into(imageLeve);
        Picasso.get().load("https://github.com/Eythan99/WeatherApp/blob/master/Image/sunset.png?raw=true").into(imageCouche);

        txtDetail.setText(weather.getTitle());

        txtLeve.setText(weather.getSun_rise().substring(weather.getSun_rise().indexOf(":")-2, weather.getSun_rise().indexOf(":")+3).replace(":","h"));
        txtCouche.setText(weather.getSun_set().substring(weather.getSun_set().indexOf(":")-2, weather.getSun_set().indexOf(":")+3).replace(":","h"));

        txtMin.setText(weather.getMin_temp()+"°C");
        txtTemp.setText(weather.getThe_temp()+"°C");
        txtMax.setText(weather.getMax_temp()+"°C");
        txtPressure.setText(weather.getAir_pressure()+" mbar");
        txtHumidity.setText(weather.getHumidity()+" %");
        txtVisibility.setText(weather.getVisibility()+" %");
        txtVent.setText(weather.getWind_speed()+" mph");

    }

}
