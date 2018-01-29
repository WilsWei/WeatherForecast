package tw.com.weather.cwd.weatherforecast.model.api.records;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by siang on 2018/1/29.
 */

public class Location implements Parcelable {
    private String locationName;
    private String geocode;
    private String lat;
    private String lon;
    private ArrayList<WeatherDetailInfo> weatherElement;

    protected Location(Parcel in) {
        locationName = in.readString();
        geocode = in.readString();
        lat = in.readString();
        lon = in.readString();
        weatherElement = in.createTypedArrayList(WeatherDetailInfo.CREATOR);
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getGeocode() {
        return geocode;
    }

    public void setGeocode(String geocode) {
        this.geocode = geocode;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public ArrayList<WeatherDetailInfo> getWeatherElement() {
        return weatherElement;
    }

    public void setWeatherElement(ArrayList<WeatherDetailInfo> weatherElement) {
        this.weatherElement = weatherElement;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(locationName);
        dest.writeString(geocode);
        dest.writeString(lat);
        dest.writeString(lon);
        dest.writeTypedList(weatherElement);
    }
}
