package tw.com.weather.cwd.weatherforecast.model.api.records;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by siang on 2018/1/29.
 */

public class WeatherDetailInfo implements Parcelable {

    private String elementName;
    private String elementMeasure;
    private ArrayList<WeatherTimeData> time;

    protected WeatherDetailInfo(Parcel in) {
        elementName = in.readString();
        elementMeasure = in.readString();
        time = in.createTypedArrayList(WeatherTimeData.CREATOR);
    }

    public static final Creator<WeatherDetailInfo> CREATOR = new Creator<WeatherDetailInfo>() {
        @Override
        public WeatherDetailInfo createFromParcel(Parcel in) {
            return new WeatherDetailInfo(in);
        }

        @Override
        public WeatherDetailInfo[] newArray(int size) {
            return new WeatherDetailInfo[size];
        }
    };

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public String getElementMeasure() {
        return elementMeasure;
    }

    public void setElementMeasure(String elementMeasure) {
        this.elementMeasure = elementMeasure;
    }

    public ArrayList<WeatherTimeData> getTime() {
        return time;
    }

    public void setTime(ArrayList<WeatherTimeData> time) {
        this.time = time;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(elementName);
        dest.writeString(elementMeasure);
        dest.writeTypedList(time);
    }
}
