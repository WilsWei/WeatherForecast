package tw.com.weather.cwd.weatherforecast.component.comparator;

import android.text.TextUtils;

import java.util.Calendar;
import java.util.Comparator;

import tw.com.weather.cwd.weatherforecast.model.WeatherData;
import tw.com.weather.cwd.weatherforecast.util.FormatUtil;

/**
 * Created by Yu-D-Siang on 2018/1/30.
 */

public class EarlyTemperatureDESCComparator implements Comparator<WeatherData> {
    @Override
    public int compare(WeatherData weatherData1, WeatherData weatherData2) {
        double temperature1 = getTemperature(weatherData1);
        double temperature2 = getTemperature(weatherData2);
        if (temperature2 > temperature1) {
            return 1;
        } else if (temperature2 < temperature1) {
            return -1;
        } else {
            return 0;
        }
    }

    private double getTemperature(WeatherData data) {
        double temperature = 0;
        if(data.getEarlyDetail() != null && !TextUtils.isEmpty(data.getEarlyDetail().getTemperature())) {
            try {
                temperature = Double.valueOf(data.getEarlyDetail().getTemperature());
            } catch (Exception e) {
            }
        }

        return temperature;
    }
}
