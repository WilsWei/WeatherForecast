package tw.com.weather.cwd.weatherforecast.util.http.listener;

import android.content.Context;

import tw.com.weather.cwd.weatherforecast.util.AlertUtil;

/**
 * Created by siang on 2018/1/29.
 */

public abstract class ResponseListener {
    public abstract void onResponse(ResponseResult result);

    /**
     * 需要特別處理之error
     * @param result
     * @param context
     * @return
     */
    public static boolean handleCommonError(ResponseResult result, Context context) {
        return false;
    }


    /**
     * 預設處理錯誤代碼的方法
     */
    public static void handleResponseError(ResponseResult result, Context context){

        if(!handleCommonError(result, context)){
            AlertUtil.showAlertDialog(context, result.getReturnMessage());
        }
    }

}
