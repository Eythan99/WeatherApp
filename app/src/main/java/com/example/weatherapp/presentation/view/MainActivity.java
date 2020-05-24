package com.example.weatherapp.presentation.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.weatherapp.R;
import com.example.weatherapp.presentation.Injection;
import com.example.weatherapp.presentation.controller.MainController;
import com.example.weatherapp.presentation.model.Weather;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public List<Weather> weatherData = new ArrayList<Weather>();

    private MainController controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        controller = new MainController(
                this,
                Injection.getGson(),
                Injection.getSharedPreferences(getApplicationContext())
        );

        controller.onStart();

    }

    public void showList() {

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(weatherData, new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Weather item) {
                controller.onItemClick(item);
            }
        });
        recyclerView.setAdapter(mAdapter);
    }

    public void showError() {
        Toast.makeText(getApplicationContext(), "API error", Toast.LENGTH_SHORT).show();
    }

    public void navigateToDetails(Weather weather) {
        Intent myIntent = new Intent(MainActivity.this, DetailActivity.class);
        myIntent.putExtra("weatherKey", Injection.getGson().toJson(weather));
        MainActivity.this.startActivity(myIntent);
    }
}
