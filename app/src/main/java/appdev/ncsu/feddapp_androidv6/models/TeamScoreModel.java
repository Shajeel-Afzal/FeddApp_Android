package appdev.ncsu.feddapp_androidv6.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by shajeelafzal on 20/11/2017.
 */

public class TeamScoreModel implements Parcelable {

    private String Name;
    private Double Total;

    public TeamScoreModel() {
    }

    public TeamScoreModel(String name, Double total) {
        Name = name;
        Total = total;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double total) {
        Total = total;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeValue(this.Total);
    }

    protected TeamScoreModel(Parcel in) {
        this.Name = in.readString();
        this.Total = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Parcelable.Creator<TeamScoreModel> CREATOR = new Parcelable.Creator<TeamScoreModel>() {
        @Override
        public TeamScoreModel createFromParcel(Parcel source) {
            return new TeamScoreModel(source);
        }

        @Override
        public TeamScoreModel[] newArray(int size) {
            return new TeamScoreModel[size];
        }
    };
}
