package tw.com.weather.cwd.weatherforecast.util.http.listener;

import android.content.Context;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import tw.com.weather.cwd.weatherforecast.R;

/**
 * Created by siang on 2018/1/29.
 */

public class JsonResponseErrorListener implements Response.ErrorListener {

    protected String TAG = JsonResponseErrorListener.class.getSimpleName();
    protected ResponseListener mResponseListener;

    private String mApiName;
    private Context mContext;

    public JsonResponseErrorListener(Context context, String apiName, ResponseListener responseListener){
        this.mResponseListener = responseListener;
        this.mApiName = apiName;
        this.mContext = context;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

        ResponseResult responseResult = new ResponseResult();
        responseResult.setApiName(mApiName);

        Log.d(TAG, "Error! LocalizedMessage: " + error.getClass());
        Log.d(TAG, "Error! Message: " + error.getMessage());
        if (error.networkResponse != null) {
            Log.d(TAG, "NetworkResponse Error! Status code: " + error.networkResponse.statusCode);
            responseResult.setReturnCode(ResponseResult.RESULT_HTTP_RESPONSE_ERROR);
            responseResult.setReturnMessage(mContext.getString(R.string.api_response_error));
        } else {
            if (error.getClass().equals(TimeoutError.class)) {
                responseResult.setReturnCode(ResponseResult.RESULT_CONNECTION_TIMEOUT);
                responseResult.setReturnMessage(mContext.getString(R.string.connection_timeout));
            } else {
                responseResult.setReturnCode(ResponseResult.RESULT_CONNECTION_ERROR);
                responseResult.setReturnMessage(mContext.getString(R.string.can_not_connection));
            }
        }
        if(mResponseListener != null) {
            mResponseListener.onResponse(responseResult);
        }
    }
}
