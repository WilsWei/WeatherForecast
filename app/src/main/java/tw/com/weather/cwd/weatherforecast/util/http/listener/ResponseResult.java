package tw.com.weather.cwd.weatherforecast.util.http.listener;

import org.json.JSONObject;

/**
 * Created by siang on 2018/1/29.
 */

public class ResponseResult {

    // 成功
    public static final String RESULT_SUCCESS = "S0000";

    //回傳失敗
    public static final String RESULT_ERROR = "S9999";
    //API Name不匹配
    public static final String RESULT_API_ID_FAIL = "S888";

    public static final String RESULT_CONNECTION_ERROR = "-200";
    // Volley request timeout
    public static final String RESULT_CONNECTION_TIMEOUT = "-210";
    // Http回傳的error
    public static final String RESULT_HTTP_RESPONSE_ERROR = "-300";
    // 解析json資料error
    public static final String RESULT_JSON_ERROR = "-400";


    protected String mReturnCode;
    protected String mReturnMessage;
    protected String mApiName;
    private JSONObject mBody;
    public JSONObject getBody() {
        return mBody;
    }

    public void setBody(JSONObject object) {
        this.mBody = object;
    }

    /**
     *
     * @return
     *     The mReturnCode
     */
    public String getReturnCode() {
        return mReturnCode;
    }

    /**
     *
     * @param returnCode
     *     The mReturnCode
     */
    public void setReturnCode(String returnCode) {
        this.mReturnCode = returnCode;
    }

    /**
     *
     * @return
     *     The mReturnMessage
     */
    public String getReturnMessage() {
        return mReturnMessage;
    }

    /**
     *
     * @param returnMessage
     *     The mReturnMessage
     */
    public void setReturnMessage(String returnMessage) {
        this.mReturnMessage = returnMessage;
    }

    public String getApiName() {
        return mApiName;
    }

    public void setApiName(String apiName) {
        this.mApiName = apiName;
    }

    /**
     * 回傳錯誤代碼是否為app自定義的
     */
    public static boolean isAppError(String errorCode){
        return errorCode.equals(RESULT_CONNECTION_ERROR)
                || errorCode.equals(RESULT_HTTP_RESPONSE_ERROR)
                || errorCode.equals(RESULT_JSON_ERROR);
    }

}
