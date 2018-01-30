package tw.com.weather.cwd.weatherforecast.component.comparator;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

import tw.com.weather.cwd.weatherforecast.model.WeatherData;
import tw.com.weather.cwd.weatherforecast.util.FormatUtil;

/**
 * Created by Yu-D-Siang on 2018/1/30.
 */

public class TimeASCComparator implements Comparator<WeatherData> {
    @Override
    public int compare(WeatherData weatherData1, WeatherData weatherData2) {
        Calendar calendar1 = FormatUtil.getDate(weatherData1.getData());
        Calendar calendar2 = FormatUtil.getDate(weatherData2.getData());
        return calendar1.compareTo(calendar2);
    }
}
