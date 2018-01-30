package tw.com.weather.cwd.weatherforecast.util.http.response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import tw.com.weather.cwd.weatherforecast.model.api.WeathersApiData;

/**
 * Created by Yu-D-Siang on 2018/1/29.
 */

public class WeatherResponseBodyUtil {

    public static boolean getSuccess(JSONObject body) {
        if(body.has("success")) {
            try {
                return body.getBoolean("success");
            } catch (JSONException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static JSONObject getResult(JSONObject body) {
        if(body.has("result")) {
            try {
                return body.getJSONObject("result");
            } catch (JSONException e) {
                return null;
            }
        } else {
            return null;
        }
    }

    public static String getResourceID(JSONObject body) {

        JSONObject result = getResult(body);
        if(result != null) {
            if (result.has("resource_id")) {
                try {
                    return result.getString("resource_id");
                } catch (JSONException e) {
                    return "";
                }
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    public static WeathersApiData getWeathersApiData(JSONObject object){
        Gson gson = new Gson();
        return gson.fromJson(object.toString(), WeathersApiData.class);
    }
}
