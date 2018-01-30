package tw.com.weather.cwd.weatherforecast.util;

import android.content.Context;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.HashMap;

import tw.com.weather.cwd.weatherforecast.R;
import tw.com.weather.cwd.weatherforecast.model.WeatherData;
import tw.com.weather.cwd.weatherforecast.model.api.WeathersApiData;
import tw.com.weather.cwd.weatherforecast.model.api.records.Location;
import tw.com.weather.cwd.weatherforecast.model.api.records.Locations;
import tw.com.weather.cwd.weatherforecast.model.api.records.WeatherElement;
import tw.com.weather.cwd.weatherforecast.model.api.records.WeatherTimeData;
import tw.com.weather.cwd.weatherforecast.util.http.listener.ResponseListener;
import tw.com.weather.cwd.weatherforecast.util.http.listener.ResponseResult;
import tw.com.weather.cwd.weatherforecast.util.http.response.WeatherResponseBodyUtil;
import tw.com.weather.cwd.weatherforecast.util.http.weather.WeatherHttpUtil;

/**
 * Created by Yu-D-Siang on 2018/1/29.
 */

public class WeatherUtil {

    private static HashMap<String, ArrayList<WeatherData>> sWeekWeathers;

    private Context mContext;

    public interface OnWeatherListener {
        void OnWeatherReturn(ArrayList<WeatherData> weekWeathers);
        void OnWeatherFail(String errorMessage);
    }
    private OnWeatherListener mOnWeatherListener;
    public void setOnWeatherListener(OnWeatherListener listener) {
        mOnWeatherListener = listener;
    }

    private void retureDate(ArrayList<WeatherData> data) {
        if(mOnWeatherListener != null) {
            mOnWeatherListener.OnWeatherReturn(data);
        }
    }

    private void fail(String errorMessage) {
        if(mOnWeatherListener != null) {
            mOnWeatherListener.OnWeatherFail(errorMessage);
        }
    }

    public WeatherUtil(Context mContext) {
        this.mContext = mContext;
    }

    public void getWeekWeather(String locationName, OnWeatherListener listener) {
        setOnWeatherListener(listener);
        getWeekWeather(locationName);
    }

    public void getWeekWeather(String locationName) {

        if(sWeekWeathers == null) {
            sWeekWeathers = new HashMap<>();
        }

        ArrayList<WeatherData> data = sWeekWeathers.get(locationName);
        if(data == null || data.size() == 0) {
            callGetWeekWeather(locationName);
        } else {
            mOnWeatherListener.OnWeatherReturn(data);
        }
    }

    private void callGetWeekWeather(final String locationName) {
        if(!NetworkUtil.isConnected(mContext)) {
            fail(mContext.getString(R.string.msg_no_available_network));
        } else {
            try {
                WeatherHttpUtil.getWeekWeather(locationName, new ResponseListener() {
                    @Override
                    public void onResponse(ResponseResult result) {
                        if(mContext == null)
                            return;

                        if(result.getReturnCode().equals(ResponseResult.RESULT_SUCCESS)) {
                            WeathersApiData weathersApiData = WeatherResponseBodyUtil.getWeathersApiData(result.getBody());
                            mappingWeekWeatherDataToViewModel(weathersApiData, locationName);

                        } else {
                            fail(result.getReturnMessage());
//                            ResponseListener.handleResponseError(result, mContext);
                        }
                    }
                }, mContext);
            } catch (JSONException e) {
                fail(mContext.getString(R.string.data_error));
            }
        }
    }

    private void mappingWeekWeatherDataToViewModel(WeathersApiData data, String currentLocation) {

        Locations locations = data.getRecords().getLocations().get(0);

        for (Location location : locations.getLocation()) {
            String locationName = location.getLocationName();
            ArrayList<WeatherData> weekWeatherList = new ArrayList<>();

            for (WeatherElement weatherElement : location.getWeatherElement()) {
                if(weatherElement.getElementName().equalsIgnoreCase("T")) {
                    mappingTemperatureType(weatherElement.getTime(), weekWeatherList);
                } else if(weatherElement.getElementName().equalsIgnoreCase("Wx")) {
                    mappingIconIDType(weatherElement.getTime(), weekWeatherList);
                }
            }

            if(sWeekWeathers == null) {
                sWeekWeathers = new HashMap<>();
            }

            sWeekWeathers.put(locationName, weekWeatherList);
        }


        retureDate(sWeekWeathers.get(currentLocation));
    }

    private void mappingTemperatureType(ArrayList<WeatherTimeData> timeData, ArrayList<WeatherData> weeklist) {
        for (WeatherTimeData data: timeData) {
            String startDate = FormatUtil.getDate(FormatUtil.timeStringToDate(data.getStartTime()));

            int weekListIndex = findWeatherDataList(weeklist, startDate);
            WeatherData currentWeatherData = getWeatherData(weeklist, startDate, weekListIndex);

            if(FormatUtil.isNight(data.getStartTime())) {
                currentWeatherData.getNightDetail().setTemperature(data.getElementValue());
            } else {
                currentWeatherData.getEarlyDetail().setTemperature(data.getElementValue());
            }


            if(weekListIndex == -1) {
                weeklist.add(currentWeatherData);
            } else {
                weeklist.set(weekListIndex, currentWeatherData);
            }

        }
    }

    private void mappingIconIDType(ArrayList<WeatherTimeData> timeData, ArrayList<WeatherData> weeklist) {
        for (WeatherTimeData data: timeData) {
            String startDate = FormatUtil.getDate(FormatUtil.timeStringToDate(data.getStartTime()));

            int weekListIndex = findWeatherDataList(weeklist, startDate);
            WeatherData currentWeatherData = getWeatherData(weeklist, startDate, weekListIndex);

            if(FormatUtil.isNight(data.getStartTime())) {
                currentWeatherData.getNightDetail().setStatusDec(data.getElementValue());
                if(data.getParameter() != null) {
                    currentWeatherData.getNightDetail().setIconID(data.getParameter().get(0).getParameterValue());
                }
            } else {
                currentWeatherData.getEarlyDetail().setStatusDec(data.getElementValue());
                if(data.getParameter() != null) {
                    currentWeatherData.getEarlyDetail().setIconID(data.getParameter().get(0).getParameterValue());
                }
            }


            if(weekListIndex == -1) {
                weeklist.add(currentWeatherData);
            } else {
                weeklist.set(weekListIndex, currentWeatherData);
            }

        }
    }

    private int findWeatherDataList(ArrayList<WeatherData> weeklist, String startDate) {
        for (int i = 0; i < weeklist.size() ; i++) {
            WeatherData weatherData = weeklist.get(i);

            if(weatherData.getData().equals(startDate)) {
                return  i;
            }
        }

        return -1;
    }

    private WeatherData getWeatherData(ArrayList<WeatherData> weeklist, String startDate, int index) {
        WeatherData weatherData = null;
        if(index != -1) {
            weatherData = weeklist.get(index);
        }

        if (weatherData == null) {
            weatherData = new WeatherData();
            weatherData.setData(startDate);
        }

        return weatherData;
    }
}
