package appdev.ncsu.feddapp_androidv6.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class TeamModel implements Parcelable {

    private String Name;
    private Double Total;
    private boolean Published;
    private List<String> Members;

    public TeamModel() {
    }

    public TeamModel(String name, Double total, boolean published, List<String> members) {
        Name = name;
        Total = total;
        Published = published;
        Members = members;
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

    public boolean isPublished() {
        return Published;
    }

    public void setPublished(boolean published) {
        Published = published;
    }

    public List<String> getMembers() {
        return Members;
    }

    public void setMembers(List<String> members) {
        Members = members;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.Name);
        dest.writeValue(this.Total);
        dest.writeByte(this.Published ? (byte) 1 : (byte) 0);
        dest.writeStringList(this.Members);
    }

    protected TeamModel(Parcel in) {
        this.Name = in.readString();
        this.Total = (Double) in.readValue(Double.class.getClassLoader());
        this.Published = in.readByte() != 0;
        this.Members = in.createStringArrayList();
    }

    public static final Parcelable.Creator<TeamModel> CREATOR = new Parcelable.Creator<TeamModel>() {
        @Override
        public TeamModel createFromParcel(Parcel source) {
            return new TeamModel(source);
        }

        @Override
        public TeamModel[] newArray(int size) {
            return new TeamModel[size];
        }
    };
}
