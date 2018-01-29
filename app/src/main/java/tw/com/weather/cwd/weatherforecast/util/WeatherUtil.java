package tw.com.weather.cwd.weatherforecast.util;

import android.content.Context;

import org.json.JSONException;

import java.util.HashMap;

import tw.com.weather.cwd.weatherforecast.model.api.WeathersApiData;
import tw.com.weather.cwd.weatherforecast.util.http.listener.ResponseListener;
import tw.com.weather.cwd.weatherforecast.util.http.listener.ResponseResult;
import tw.com.weather.cwd.weatherforecast.util.http.weather.WeatherHttpUtil;

/**
 * Created by Yu-D-Siang on 2018/1/29.
 */

public class WeatherUtil {

    private static HashMap<String, WeathersApiData> weatherDatas;

    private Context mContext;

    public interface OnWeatherListener {
        void OnReturnDate(WeathersApiData data);
        void OnFail();
    }
    private OnWeatherListener mOnWeatherListener;
    public void setOnWeatherListener(OnWeatherListener listener) {
        mOnWeatherListener = listener;
    }

    private void retureDate(WeathersApiData data) {
        if(mOnWeatherListener != null) {
            mOnWeatherListener.OnReturnDate(data);
        }
    }

    private void fail() {
        if(mOnWeatherListener != null) {
            mOnWeatherListener.OnFail();
        }
    }

    public void getWeekWeather(Context context, String locationName, OnWeatherListener listener) {
        setOnWeatherListener(listener);
        getWeekWeather(context, locationName);
    }

    public void getWeekWeather(Context context, String locationName) {

        if(weatherDatas == null) {
            weatherDatas = new HashMap<>();
        }

        WeathersApiData data = weatherDatas.get(locationName);
        if(data == null) {

        } else {
            mOnWeatherListener.OnReturnDate(data);
        }
    }

    private void callGetWeekWeather(String locationName) {
        if(!NetworkUtil.isConnected(mContext)) {

        } else {
            try {
                WeatherHttpUtil.getWeekWeather(locationName, responseListener, mContext);
            } catch (JSONException e) {
                fail();
            }
        }
    }

    private ResponseListener responseListener = new ResponseListener() {
        @Override
        public void onResponse(ResponseResult result) {
            if(mContext != null)
                return;

            if(result.getReturnCode().equals(ResponseResult.RESULT_SUCCESS)) {

            } else {
                ResponseListener.handleResponseError(result, mContext);
            }
        }
    };

}
