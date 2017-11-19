package appdev.ncsu.feddapp_androidv6.models;

import java.util.List;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class TeamModel {

    private String Name;
    private Double Score;
    private boolean Published;
    private List<String> Members;

    public TeamModel() {
    }

    public TeamModel(String name, Double score, boolean published, List<String> members) {
        Name = name;
        Score = score;
        Published = published;
        Members = members;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getScore() {
        return Score;
    }

    public void setScore(Double score) {
        Score = score;
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
}
