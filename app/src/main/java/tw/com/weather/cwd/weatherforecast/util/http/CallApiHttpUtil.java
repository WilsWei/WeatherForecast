package tw.com.weather.cwd.weatherforecast.util.http;

import android.app.Activity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;

import java.util.HashMap;

import tw.com.weather.cwd.weatherforecast.util.http.listener.JsonResponseErrorListener;
import tw.com.weather.cwd.weatherforecast.util.http.listener.JsonResponseListener;
import tw.com.weather.cwd.weatherforecast.util.http.listener.ResponseListener;

/**
 * Created by siang on 2018/1/29.
 */

public class CallApiHttpUtil extends HttpUtilBase{


    public static void getWeekWeather(String locationName,ResponseListener responseListener, Activity activity) throws JSONException {
        String apiName = "F-D0047-091";
        String apiUrl = getApiUrl(apiName);
        publicQueue = newRequestQueue(activity);

        HashMap<String, String> params = new HashMap<>();

        params.put("elementName", "T");
        params.put("locationName", locationName);
        params.put("sort", "time");

        String requestURL = HttpUtilBase.addRequestParam(apiUrl, null);
        JsonResponseListener jsonResponseListener = new JsonResponseListener(activity, apiName, responseListener);
        JsonResponseErrorListener errorListener = new JsonResponseErrorListener(activity, apiName, responseListener);

        JsonObjectRequest request = new JsonObjectRequest(requestURL, null, jsonResponseListener, errorListener);
        request.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, MAX_RETRY_TIMES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        request.setTag(activity.getClass().getSimpleName());
        publicQueue.add(request);
    }

}
