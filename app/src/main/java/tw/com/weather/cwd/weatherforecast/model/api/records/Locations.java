package tw.com.weather.cwd.weatherforecast.model.api.records;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by siang on 2018/1/29.
 */

public class Locations implements Parcelable {

    private String datasetDescription;
    private String dataid;
    private ArrayList<Location> location;

    protected Locations(Parcel in) {
        datasetDescription = in.readString();
        dataid = in.readString();
        location = in.createTypedArrayList(Location.CREATOR);
    }

    public static final Creator<Locations> CREATOR = new Creator<Locations>() {
        @Override
        public Locations createFromParcel(Parcel in) {
            return new Locations(in);
        }

        @Override
        public Locations[] newArray(int size) {
            return new Locations[size];
        }
    };

    public String getDatasetDescription() {
        return datasetDescription;
    }

    public void setDatasetDescription(String datasetDescription) {
        this.datasetDescription = datasetDescription;
    }

    public String getDataid() {
        return dataid;
    }

    public void setDataid(String dataid) {
        this.dataid = dataid;
    }

    public ArrayList<Location> getLocation() {
        return location;
    }

    public void setLocation(ArrayList<Location> location) {
        this.location = location;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(datasetDescription);
        dest.writeString(dataid);
        dest.writeTypedList(location);
    }
}
