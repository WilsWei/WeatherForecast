package tw.com.weather.cwd.weatherforecast.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import tw.com.weather.cwd.weatherforecast.R;
import tw.com.weather.cwd.weatherforecast.adapter.WeatherRecyclerAdapter;
import tw.com.weather.cwd.weatherforecast.model.CitysData;
import tw.com.weather.cwd.weatherforecast.model.WeatherData;
import tw.com.weather.cwd.weatherforecast.model.api.WeathersApiData;
import tw.com.weather.cwd.weatherforecast.service.WeatherInetentService;
import tw.com.weather.cwd.weatherforecast.util.AlertUtil;
import tw.com.weather.cwd.weatherforecast.util.FormatUtil;
import tw.com.weather.cwd.weatherforecast.util.WeatherUtil;

public class MainActivity extends ActivityBase {

    private static final String TAG = "MainActivity";

    private String mCurrentCity;

    private WeatherRecyclerAdapter mWeatherRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWeatherList(new ArrayList<WeatherData>());

        Spinner spinnerCitys = (Spinner) findViewById(R.id.spinner_citys);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, CitysData.getCitys());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCitys.setAdapter(adapter);

        spinnerCitys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentCity = CitysData.getCityName(i);
                WeatherInetentService.startQueryWeather(MainActivity.this, mCurrentCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        registerReceiver();
    }

    private void setWeatherList(ArrayList<WeatherData> list) {
        RecyclerView recyclerWeather = findViewById(R.id.recyclerViewWeather);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerWeather.setLayoutManager(layoutManager);

        mWeatherRecyclerAdapter = new WeatherRecyclerAdapter(list, this);
        recyclerWeather.setAdapter(mWeatherRecyclerAdapter);

    }

    private void registerReceiver()
    {
        IntentFilter filter = new IntentFilter();
        filter.addAction(WeatherInetentService.ACTION_RETURN_FINISH);
        filter.addAction(WeatherInetentService.ACTION_RETURN_FAIL);
        registerReceiver(queryWeatherReceiver, filter);
    }
    
    private BroadcastReceiver queryWeatherReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (action.equals(WeatherInetentService.ACTION_RETURN_FINISH)) {
                if(intent.hasExtra(WeatherInetentService.EXTRA_WEATHER_DATA)) {
                    ArrayList<WeatherData> weekWeatherlist = intent.getParcelableArrayListExtra(WeatherInetentService.EXTRA_WEATHER_DATA);

                    if(mWeatherRecyclerAdapter == null) {
                        setWeatherList(weekWeatherlist);
                    } else {
                        mWeatherRecyclerAdapter.setList(weekWeatherlist);
                    }
                }
            } else if(action.equals(WeatherInetentService.ACTION_RETURN_FAIL)) {
                if(intent.hasExtra(WeatherInetentService.EXTRA_ERROR_MESSAGE)) {
                    String message = intent.getStringExtra(WeatherInetentService.EXTRA_ERROR_MESSAGE);
                    AlertUtil.showAlertDialog(context, message);
                }
            }

        }
    };

}
