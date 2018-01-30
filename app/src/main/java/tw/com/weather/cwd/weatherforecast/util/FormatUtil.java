package tw.com.weather.cwd.weatherforecast.util;

import android.text.TextUtils;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by siang on 2018/1/30.
 */

public class FormatUtil {
    private static final String TAG = "FormatUtil";

    private static final String DATA_FORMAT = "yyyy-MM-dd";
    private static final String DATATIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    /**
     * 時間字串轉為date物件
     * @param timeString yyyy-MM-dd HH:mm:ss
     */
    public static Date timeStringToDate(String timeString){
        if(TextUtils.isEmpty(timeString)) {
            return null;
        }

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat(DATATIME_FORMAT);
        try {
            Date date = dateTimeFormat.parse(timeString);
            return date;
        } catch (ParseException e) {
            Log.d(TAG, Log.getStackTraceString(e));
            return null;
        }

    }

    /**
     * 抓取日期
     * @param date
     * @return 回傳String  (yyyy-MM-dd)
     */
    public static String getDate(Date date) {

        SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT);
        return dateFormat.format(date);
    }

    /**
     *  日期
     * @param dateString  (yyyy-MM-dd)
     * @return Calendar
     */
    public static Calendar getDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(DATA_FORMAT);

        try {
            Date date = dateFormat.parse(dateString);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE,0);
            calendar.set(Calendar.SECOND,0);
            calendar.set(Calendar.MILLISECOND,0);
            return calendar;
        } catch (ParseException e) {
            return null;
        }


    }


}
