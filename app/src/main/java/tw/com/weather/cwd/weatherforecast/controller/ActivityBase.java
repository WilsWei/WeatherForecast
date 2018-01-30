package tw.com.weather.cwd.weatherforecast.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import tw.com.weather.cwd.weatherforecast.util.http.weather.HttpUtilBase;


public class ActivityBase extends AppCompatActivity {

    private String mTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTag = this.getClass().getSimpleName();
    }

    @Override
    protected void onPause() {
        super.onPause();
        HttpUtilBase.cancelQueue(mTag);
    }

}