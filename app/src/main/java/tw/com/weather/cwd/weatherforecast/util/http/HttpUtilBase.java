package tw.com.weather.cwd.weatherforecast.util.http;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;

import tw.com.weather.cwd.weatherforecast.setted.GlobalConst;

/**
 * Created by siang on 2018/1/29.
 */

public class HttpUtilBase {

    private static final String TAG = "HttpUtilBase";

    public static final int MAX_RETRY_TIMES = -1;
    public static int TIMEOUT = 60 * 1000;

    public static RequestQueue publicQueue;

    public static String addRequestParam(String apiNameUrl, HashMap<String, String> params){
        publicQueue.getCache().clear();

        if(params != null) {
            for (String key : params.keySet()) {
                apiNameUrl += String.format("&%1$s=%2$s", key, params.get(key));
            }
        }
        Log.d(TAG, apiNameUrl);
        return apiNameUrl;
    }

    public static String getApiUrl(String apiName) {
        String apiUrl = String.format("%1$s%2$2?Authorization=%3$3", GlobalConst.CWD_WEATHER_API_URL, apiName, GlobalConst.CWD_WEATHER_API_AUTH_KEY);

        return apiUrl;
    }

    public static RequestQueue newRequestQueue(Context context) {
        if(publicQueue == null) {
            return Volley.newRequestQueue(context);
        } else {
            return publicQueue;
        }
    }

    public static void cancelQueue(String tag) {
        if (publicQueue != null) {
            publicQueue.cancelAll(tag);
        }
    }
}