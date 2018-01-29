package tw.com.weather.cwd.weatherforecast.model.api.result;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by siang on 2018/1/29.
 */

public class Field implements Parcelable {

    private String id;
    private String type;

    protected Field(Parcel in) {
        id = in.readString();
        type = in.readString();
    }

    public static final Creator<Field> CREATOR = new Creator<Field>() {
        @Override
        public Field createFromParcel(Parcel in) {
            return new Field(in);
        }

        @Override
        public Field[] newArray(int size) {
            return new Field[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(type);
    }
}
