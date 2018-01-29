package tw.com.weather.cwd.weatherforecast.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import tw.com.weather.cwd.weatherforecast.R;
import tw.com.weather.cwd.weatherforecast.model.CitysData;
import tw.com.weather.cwd.weatherforecast.model.api.WeathersApiData;
import tw.com.weather.cwd.weatherforecast.util.WeatherUtil;

public class MainActivity extends ActivityBase {

    private String mCurrentCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("台北市");

        Spinner spinnerCitys = (Spinner) findViewById(R.id.spinner_citys);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.spinner_item, CitysData.getCitys());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCitys.setAdapter(adapter);

        spinnerCitys.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mCurrentCity = CitysData.getCityName(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

}
