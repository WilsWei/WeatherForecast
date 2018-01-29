package tw.com.weather.cwd.weatherforecast.controller;

import android.os.Bundle;

import tw.com.weather.cwd.weatherforecast.R;

public class MainActivity extends ActivityBase {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("台北市");



    }
}
