package tw.com.weather.cwd.weatherforecast.model.api.records;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siang on 2018/1/30.
 */

public class WeatherParameterData implements Parcelable{
    private String parameterName;
    private String parameterValue;
    private String parameterUnit;

    protected WeatherParameterData(Parcel in) {
        parameterName = in.readString();
        parameterValue = in.readString();
        parameterUnit = in.readString();
    }

    public static final Creator<WeatherParameterData> CREATOR = new Creator<WeatherParameterData>() {
        @Override
        public WeatherParameterData createFromParcel(Parcel in) {
            return new WeatherParameterData(in);
        }

        @Override
        public WeatherParameterData[] newArray(int size) {
            return new WeatherParameterData[size];
        }
    };

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public String getParameterValue() {
        return parameterValue;
    }

    public void setParameterValue(String parameterValue) {
        this.parameterValue = parameterValue;
    }

    public String getParameterUnit() {
        return parameterUnit;
    }

    public void setParameterUnit(String parameterUnit) {
        this.parameterUnit = parameterUnit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(parameterName);
        dest.writeString(parameterValue);
        dest.writeString(parameterUnit);
    }
}
