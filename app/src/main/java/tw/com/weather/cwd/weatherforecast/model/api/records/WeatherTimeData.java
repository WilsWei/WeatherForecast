package tw.com.weather.cwd.weatherforecast.model.api.records;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by siang on 2018/1/29.
 */

public class WeatherTimeData implements Parcelable{
    private String startTime;
    private String endTime;
    private String elementValue;
    private ArrayList<WeatherParameterData> parameter;

    protected WeatherTimeData(Parcel in) {
        startTime = in.readString();
        endTime = in.readString();
        elementValue = in.readString();
        parameter = in.createTypedArrayList(WeatherParameterData.CREATOR);
    }

    public static final Creator<WeatherTimeData> CREATOR = new Creator<WeatherTimeData>() {
        @Override
        public WeatherTimeData createFromParcel(Parcel in) {
            return new WeatherTimeData(in);
        }

        @Override
        public WeatherTimeData[] newArray(int size) {
            return new WeatherTimeData[size];
        }
    };

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getElementValue() {
        return elementValue;
    }

    public void setElementValue(String elementValue) {
        this.elementValue = elementValue;
    }

    public ArrayList<WeatherParameterData> getParameter() {
        return parameter;
    }

    public void setParameter(ArrayList<WeatherParameterData> parameter) {
        this.parameter = parameter;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(elementValue);
        dest.writeTypedList(parameter);
    }
}
