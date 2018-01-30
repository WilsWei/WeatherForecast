package tw.com.weather.cwd.weatherforecast.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import java.util.ArrayList;

import tw.com.weather.cwd.weatherforecast.model.WeatherData;
import tw.com.weather.cwd.weatherforecast.util.WeatherUtil;

/**
 * Created by siang on 2018/1/30.
 */

public class WeatherInetentService extends IntentService {

    private static final String TAG = "WeatherInetentService";
    //start action
    private static final String ACTION_QUERY_WEATHER = "action_query_city";
    //end action
    public static final String ACTION_RETURN_FINISH = "action_return_finish";
    public static final String ACTION_RETURN_FAIL = "action_return_fail";

    private static final String EXTRA_CITY = "extra_city";
    public static final String EXTRA_WEATHER_DATA = "extra_weather_data";
    public static final String EXTRA_ERROR_MESSAGE = "extra_error_message";



    public WeatherInetentService() {
        super(TAG);
    }

    public static void startQueryWeather(Context context, String cityName)
    {
        Intent intent = new Intent(context, WeatherInetentService.class);
        intent.setAction(ACTION_QUERY_WEATHER);
        intent.putExtra(EXTRA_CITY, cityName);
        context.startService(intent);
    }


    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if(intent != null) {
            if (intent.getAction().equals(ACTION_QUERY_WEATHER)) {
                runQueryWeather(intent);
            }

        }


    }

    private void runQueryWeather(@Nullable Intent intent) {
        if(intent.hasExtra(EXTRA_CITY)) {
            String city = intent.getStringExtra(EXTRA_CITY);
            WeatherUtil weatherUtil = new WeatherUtil(this);
            weatherUtil.getWeekWeather(city, new WeatherUtil.OnWeatherListener() {
                @Override
                public void OnWeatherReturn(ArrayList<WeatherData> weekWeathers) {
                    returnSevice(ACTION_RETURN_FINISH, weekWeathers, null);
                }

                @Override
                public void OnWeatherFail(String errorMessage) {
                    returnSevice(ACTION_RETURN_FAIL, null, errorMessage);
                }
            });

        }
    }


    private void returnSevice(@Nullable String action, ArrayList<WeatherData> data, String errorMessage) {
        Intent intent = new Intent(action);
        if(data != null) {
            intent.putExtra(EXTRA_WEATHER_DATA, data);
        }

        if(!TextUtils.isEmpty(errorMessage)) {
            intent.putExtra(EXTRA_ERROR_MESSAGE, errorMessage);
        }

        sendBroadcast(intent);

    }
}
