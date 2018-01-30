package tw.com.weather.cwd.weatherforecast.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siang on 2018/1/30.
 */

public class WeatherData implements Parcelable {
    private String data;
    private WeatherDetail earlyDetail;
    private WeatherDetail nightDetail;

    public WeatherData() {
        this.data = "";
        this.earlyDetail = new WeatherDetail();
        this.nightDetail = new WeatherDetail();
    }

    protected WeatherData(Parcel in) {
        data = in.readString();
        earlyDetail = in.readParcelable(WeatherDetail.class.getClassLoader());
        nightDetail = in.readParcelable(WeatherDetail.class.getClassLoader());
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(data);
        dest.writeParcelable(earlyDetail, flags);
        dest.writeParcelable(nightDetail, flags);
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public WeatherDetail getEarlyDetail() {
        return earlyDetail;
    }

    public void setEarlyDetail(WeatherDetail earlyDetail) {
        this.earlyDetail = earlyDetail;
    }

    public WeatherDetail getNightDetail() {
        return nightDetail;
    }

    public void setNightDetail(WeatherDetail nightDetail) {
        this.nightDetail = nightDetail;
    }
}
