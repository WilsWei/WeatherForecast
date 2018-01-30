package tw.com.weather.cwd.weatherforecast.util.http.weather;

import android.content.Context;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;

import java.util.HashMap;

import tw.com.weather.cwd.weatherforecast.util.http.listener.JsonResponseErrorListener;
import tw.com.weather.cwd.weatherforecast.util.http.weather.listener.JsonResponseListener;
import tw.com.weather.cwd.weatherforecast.util.http.listener.ResponseListener;

/**
 * Created by siang on 2018/1/29.
 */

public class WeatherHttpUtil extends HttpUtilBase{

    /**
     * 查詢一週天氣
     * @param locationName 城市
     * @param responseListener
     * @param context
     * @throws JSONException
     */
    public static void getWeekWeather(String locationName, ResponseListener responseListener, Context context) throws JSONException {
        String apiName = "F-D0047-091";
        String apiUrl = getApiUrl(apiName);
        RequestQueue queue = newRequestQueue(context);

        HashMap<String, String> params = new HashMap<>();

        params.put("elementName", "T");
        params.put("locationName", locationName);
        params.put("sort", "time");

        String requestURL = HttpUtilBase.addRequestParam(apiUrl, params);
        JsonResponseListener jsonResponseListener = new JsonResponseListener(context, apiName, responseListener);
        JsonResponseErrorListener errorListener = new JsonResponseErrorListener(context, apiName, responseListener);

        JsonObjectRequest request = new JsonObjectRequest(requestURL, null, jsonResponseListener, errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, MAX_RETRY_TIMES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag(context.getClass().getSimpleName());
        queue.add(request);
    }

}
