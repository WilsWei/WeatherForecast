package tw.com.weather.cwd.weatherforecast.util.http.listener;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;

import org.json.JSONObject;

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


        if(TextUtils.isEmpty(response.toString())) {
            responseResult.setReturnCode(ResponseResult.RESULT_JSON_ERROR);
            responseResult.setReturnMessage("查無資料錯誤，請稍候再試。");
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
