package tw.com.weather.cwd.weatherforecast.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import tw.com.weather.cwd.weatherforecast.R;
import tw.com.weather.cwd.weatherforecast.adapter.WeatherRecyclerAdapter;
import tw.com.weather.cwd.weatherforecast.component.comparator.EarlyTemperatureASCComparator;
import tw.com.weather.cwd.weatherforecast.component.comparator.EarlyTemperatureDESCComparator;
import tw.com.weather.cwd.weatherforecast.component.comparator.NightTemperatureASCComparator;
import tw.com.weather.cwd.weatherforecast.component.comparator.NightTemperatureDESCComparator;
import tw.com.weather.cwd.weatherforecast.component.comparator.TimeASCComparator;
import tw.com.weather.cwd.weatherforecast.component.comparator.TimeDESCComparator;
import tw.com.weather.cwd.weatherforecast.model.CitysData;
import tw.com.weather.cwd.weatherforecast.model.WeatherData;
import tw.com.weather.cwd.weatherforecast.service.WeatherInetentService;
import tw.com.weather.cwd.weatherforecast.util.AlertUtil;

public class MainActivity extends ActivityBase {

    private static final String TAG = "MainActivity";

    private String mCurrentCity;
    private int mCurrentSort = 0;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    private WeatherRecyclerAdapter mWeatherRecyclerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setWeatherList(new ArrayList<WeatherData>());
        setCityOption();
        setSortOption();
        setRefresh();
        registerReceiver();
    }

    private void setWeatherList(@NonNull List<WeatherData> list) {
        RecyclerView weatherRecycler = findViewById(R.id.recyclerViewWeather);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        weatherRecycler.setLayoutManager(layoutManager);

        mWeatherRecyclerAdapter = new WeatherRecyclerAdapter(list, this);
        weatherRecycler.setAdapter(mWeatherRecyclerAdapter);

    }

    private void setRefresh() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                WeatherInetentService.startRefreshWeather(MainActivity.this, mCurrentCity);
            }
        });
    }

    private void updateList(List<WeatherData> weekWeatherlist) {
        Collections.sort(weekWeatherlist, getSortComparator());

        if(mWeatherRecyclerAdapter == null) {
            setWeatherList(weekWeatherlist);
        } else {
            mWeatherRecyclerAdapter.setList(weekWeatherlist);
        }
    }

    private Comparator getSortComparator() {
        switch (mCurrentSort) {
            case 1:
                return new TimeDESCComparator();

            case 2:
                return new EarlyTemperatureDESCComparator();

            case 3:
                return new EarlyTemperatureASCComparator();

            case 4:
                return new NightTemperatureDESCComparator();

            case 5:
                return new NightTemperatureASCComparator();

            default:
                return new TimeASCComparator();
        }
    }

    private void setCityOption() {
        Spinner citySpinner = (Spinner) findViewById(R.id.spinner_city);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, CitysData.getCitys());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        citySpinner.setAdapter(adapter);

        citySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentCity = CitysData.getCityName(i);
                WeatherInetentService.startQueryWeather(MainActivity.this, mCurrentCity);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setSortOption() {
        Spinner sortSpinner = (Spinner) findViewById(R.id.spinner_sort);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_sort_array, R.layout.spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adapter);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentSort = i;
                if(mWeatherRecyclerAdapter != null) {
                    updateList(mWeatherRecyclerAdapter.getList());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
            if(mSwipeRefreshLayout != null && mSwipeRefreshLayout.isRefreshing()) {
                mSwipeRefreshLayout.setRefreshing(false);
            }

            if (action.equals(WeatherInetentService.ACTION_RETURN_FINISH)) {
                if(intent.hasExtra(WeatherInetentService.EXTRA_WEATHER_DATA)) {
                    ArrayList<WeatherData> weekWeatherlist = intent.getParcelableArrayListExtra(WeatherInetentService.EXTRA_WEATHER_DATA);

                    updateList(weekWeatherlist);
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
