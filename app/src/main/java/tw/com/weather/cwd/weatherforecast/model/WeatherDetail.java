package tw.com.weather.cwd.weatherforecast.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siang on 2018/1/30.
 */

public class WeatherDetail implements Parcelable{
    private String temperature;
    private String statusDec;
    private String iconID;

    public WeatherDetail() {
        this.temperature = "";
        this.statusDec = "";
        this.iconID = "";
    }

    protected WeatherDetail(Parcel in) {
        temperature = in.readString();
        statusDec = in.readString();
        iconID = in.readString();
    }

    public static final Creator<WeatherDetail> CREATOR = new Creator<WeatherDetail>() {
        @Override
        public WeatherDetail createFromParcel(Parcel in) {
            return new WeatherDetail(in);
        }

        @Override
        public WeatherDetail[] newArray(int size) {
            return new WeatherDetail[size];
        }
    };

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getStatusDec() {
        return statusDec;
    }

    public void setStatusDec(String statusDec) {
        this.statusDec = statusDec;
    }

    public String getIconID() {
        return iconID;
    }

    public void setIconID(String iconID) {
        this.iconID = iconID;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(temperature);
        dest.writeString(statusDec);
        dest.writeString(iconID);
    }
}
