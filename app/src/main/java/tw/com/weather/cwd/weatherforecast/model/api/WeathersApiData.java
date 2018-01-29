package tw.com.weather.cwd.weatherforecast.model.api;


import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siang on 2018/1/29.
 * 變數命名需與Api response
 */

public class WeathersApiData implements Parcelable {
    private String success;
    private WeatherResult result;
    private WeatherRecords records;

    protected WeathersApiData(Parcel in) {
        success = in.readString();
        result = in.readParcelable(WeatherResult.class.getClassLoader());
        records = in.readParcelable(WeatherRecords.class.getClassLoader());
    }

    public static final Creator<WeathersApiData> CREATOR = new Creator<WeathersApiData>() {
        @Override
        public WeathersApiData createFromParcel(Parcel in) {
            return new WeathersApiData(in);
        }

        @Override
        public WeathersApiData[] newArray(int size) {
            return new WeathersApiData[size];
        }
    };

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public WeatherResult getResult() {
        return result;
    }

    public void setResult(WeatherResult result) {
        this.result = result;
    }

    public WeatherRecords getRecords() {
        return records;
    }

    public void setRecords(WeatherRecords records) {
        this.records = records;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(success);
        dest.writeParcelable(result, flags);
        dest.writeParcelable(records, flags);
    }
}
