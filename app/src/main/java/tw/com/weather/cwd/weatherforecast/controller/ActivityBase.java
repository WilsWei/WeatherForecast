package tw.com.weather.cwd.weatherforecast.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;

import tw.com.weather.cwd.weatherforecast.util.http.weather.HttpUtilBase;


public class ActivityBase extends AppCompatActivity {

    private String TAG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = this.getClass().getSimpleName();
    }


// 如果閒置要方是使用這個
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onPause() {
        super.onPause();
        HttpUtilBase.cancelQueue(TAG);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}