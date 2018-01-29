package tw.com.weather.cwd.weatherforecast.util.http.listener;

import android.app.Activity;

import tw.com.weather.cwd.weatherforecast.util.AlertUtil;

/**
 * Created by siang on 2018/1/29.
 */

public abstract class ResponseListener {
    public abstract void onResponse(ResponseResult result);

    /**
     * 需要特別處理之error
     * @param result
     * @param activity
     * @return
     */
    public static boolean handleCommonError(ResponseResult result, final Activity activity) {

        String returnCode = result.getReturnCode();


        return false;
    }


    /**
     * 預設處理錯誤代碼的方法
     */
    public static void handleResponseError(ResponseResult result, Activity activity){

        if(!handleCommonError(result, activity)){
            AlertUtil.showAlertDialog(activity, result.getReturnMessage());
        }
    }

}
