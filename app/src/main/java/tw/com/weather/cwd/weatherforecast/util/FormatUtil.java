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
    /**
     * 時間字串轉為date物件
     * @param timeString yyyy-MM-dd HH:mm:ss"
     */
    public static Date timeStringToDate(String timeString){
        if(TextUtils.isEmpty(timeString)) {
            return null;
        }

        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = dayFormat.parse(timeString);
            return date;
        } catch (ParseException e) {
            Log.d(TAG, Log.getStackTraceString(e));
            return null;
        }

    }

    /**
     * 抓取日期
     * @param date
     * @return
     */
    public static String getDate(Date date) {

        SimpleDateFormat dayFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dayFormat.format(date);
    }


    /**
     * 判斷是否為晚上時間
     * @param timeString
     */
    public static boolean isNight(String timeString) {
        Date targetDate = timeStringToDate(timeString);

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

}
