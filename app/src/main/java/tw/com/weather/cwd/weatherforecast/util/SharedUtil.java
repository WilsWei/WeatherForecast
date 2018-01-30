package tw.com.weather.cwd.weatherforecast.util;

import android.content.Context;

import java.util.Calendar;
import java.util.Date;

import tw.com.weather.cwd.weatherforecast.R;

/**
 * Created by Yu-D-Siang on 2018/1/30.
 */

public class SharedUtil {

    /**
     * 判斷是否為晚上時間
     * @param timeString (yyyy-MM-dd HH:mm:ss)
     */
    public static boolean isNight(String timeString) {
        Date targetDate = FormatUtil.timeStringToDate(timeString);

        if(targetDate != null) {
            Calendar target = Calendar.getInstance();
            target.setTime(targetDate);

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(targetDate);
            calendar.set(Calendar.HOUR_OF_DAY, 18);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);

            if(target.before(calendar)) {
                return false;
            } else {
                return true;
            }

        }
        return false;
    }

    public static Calendar getToday() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE,0);
        calendar.set(Calendar.SECOND,0);
        calendar.set(Calendar.MILLISECOND,0);

        return calendar;
    }

    /**
     *
     * @param dateString (yyyy-MM-dd)
     * @return
     */
    public static int compareToToday(String dateString) {
        Calendar date = FormatUtil.getDate(dateString);
        Calendar today = getToday();

        return (int)((date.getTime().getTime() - today.getTime().getTime()) / (1000*3600*24));
    }

    public static String getDayOfWeek(Context context, String dateString) {

        Calendar calendar = FormatUtil.getDate(dateString);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        switch(dayOfWeek) {
            case Calendar.SUNDAY:
                return context.getString(R.string.sunday);
            case Calendar.MONDAY:
                return context.getString(R.string.monday);
            case Calendar.TUESDAY:
                return context.getString(R.string.tuesday);
            case Calendar.WEDNESDAY:
                return context.getString(R.string.wednesday);
            case Calendar.THURSDAY:
                return context.getString(R.string.thursday);
            case Calendar.FRIDAY:
                return context.getString(R.string.friday);
            case Calendar.SATURDAY:
                return context.getString(R.string.saturday);
        }

        return "";
    }
}
