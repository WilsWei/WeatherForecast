package tw.com.weather.cwd.weatherforecast.util.http.weather.listener;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONObject;

import tw.com.weather.cwd.weatherforecast.R;
import tw.com.weather.cwd.weatherforecast.util.http.listener.ResponseListener;
import tw.com.weather.cwd.weatherforecast.util.http.listener.ResponseResult;
import tw.com.weather.cwd.weatherforecast.util.http.response.WeatherResponseBodyUtil;

/**
 * Created by siang on 2018/1/29.
 */

public class JsonResponseListener implements Response.Listener<JSONObject>{

    private static final String TAG = "JsonResponseListener";

    private ResponseListener mResultListener;
    private String mApiName;
    private Context mContext;

    public JsonResponseListener(Context context, String apiName, ResponseListener resultListener){
        this.mApiName = apiName;
        this.mResultListener = resultListener;
        this.mContext = context;
    }

    @Override
    public void onResponse(JSONObject response) {
        Log.d(TAG, response.toString());

        ResponseResult responseResult = new ResponseResult();
        responseResult.setApiName(mApiName);

        boolean success = WeatherResponseBodyUtil.getSuccess(response);

        if(!success) {
            responseResult.setReturnCode(ResponseResult.RESULT_ERROR);
            responseResult.setReturnMessage(mContext.getString(R.string.connection_return_error));

            if(mResultListener != null) {
                mResultListener.onResponse(responseResult);
            }
            return;
        }


        String resourceID = WeatherResponseBodyUtil.getResourceID(response);
        if(TextUtils.isEmpty(resourceID) || !resourceID.equalsIgnoreCase(mApiName)) {
            responseResult.setReturnCode(ResponseResult.RESULT_API_ID_FAIL);
            responseResult.setReturnMessage(mContext.getString(R.string.data_error));
        } else if(TextUtils.isEmpty(response.toString())) {
            responseResult.setReturnCode(ResponseResult.RESULT_JSON_ERROR);
            responseResult.setReturnMessage(mContext.getString(R.string.data_error));
        } else {
            responseResult.setReturnCode(ResponseResult.RESULT_SUCCESS);
            responseResult.setReturnMessage("");
        }

        responseResult.setBody(response);

        if(mResultListener != null) {
            mResultListener.onResponse(responseResult);
        }
    }
}
