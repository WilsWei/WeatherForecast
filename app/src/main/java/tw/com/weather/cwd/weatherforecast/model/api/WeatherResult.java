package tw.com.weather.cwd.weatherforecast.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import tw.com.weather.cwd.weatherforecast.model.api.result.Field;

/**
 * Created by siang on 2018/1/29.
 * 變數命名需與Api response
 */

public class WeatherResult implements Parcelable{
    private String resource_id;
    private ArrayList<Field> fields;

    protected WeatherResult(Parcel in) {
        resource_id = in.readString();
        fields = in.createTypedArrayList(Field.CREATOR);
    }

    public static final Creator<WeatherResult> CREATOR = new Creator<WeatherResult>() {
        @Override
        public WeatherResult createFromParcel(Parcel in) {
            return new WeatherResult(in);
        }

        @Override
        public WeatherResult[] newArray(int size) {
            return new WeatherResult[size];
        }
    };

    public String getResource_id() {
        return resource_id;
    }

    public void setResource_id(String resource_id) {
        this.resource_id = resource_id;
    }

    public ArrayList<Field> getFields() {
        return fields;
    }

    public void setFields(ArrayList<Field> fields) {
        this.fields = fields;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(resource_id);
        dest.writeTypedList(fields);
    }
}
