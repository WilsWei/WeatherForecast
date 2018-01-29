package tw.com.weather.cwd.weatherforecast.model.api;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import tw.com.weather.cwd.weatherforecast.model.api.records.Locations;

/**
 * Created by siang on 2018/1/29.
 * 變數命名需與Api response
 */

public class WeatherRecords implements Parcelable {
    private String contentDescription;
    private ArrayList<Locations> locations;

    protected WeatherRecords(Parcel in) {
        contentDescription = in.readString();
        locations = in.createTypedArrayList(Locations.CREATOR);
    }

    public static final Creator<WeatherRecords> CREATOR = new Creator<WeatherRecords>() {
        @Override
        public WeatherRecords createFromParcel(Parcel in) {
            return new WeatherRecords(in);
        }

        @Override
        public WeatherRecords[] newArray(int size) {
            return new WeatherRecords[size];
        }
    };

    public String getContentDescription() {
        return contentDescription;
    }

    public void setContentDescription(String contentDescription) {
        this.contentDescription = contentDescription;
    }

    public ArrayList<Locations> getLocations() {
        return locations;
    }

    public void setLocations(ArrayList<Locations> locations) {
        this.locations = locations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(contentDescription);
        dest.writeTypedList(locations);
    }
}
