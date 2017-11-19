package appdev.ncsu.feddapp_androidv6.models;

import java.util.List;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class ProjectModel {

    private String projectName;
    private int drawableId;
    private List<String> morningTeams;
    private List<String> afternoonTeams;

    public ProjectModel() {
    }

    public ProjectModel(String projectName, int drawableId) {
        this.projectName = projectName;
        this.drawableId = drawableId;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<String> getMorningTeams() {
        return morningTeams;
    }

    public void setMorningTeams(List<String> morningTeams) {
        this.morningTeams = morningTeams;
    }

    public List<String> getAfternoonTeams() {
        return afternoonTeams;
    }

    public void setAfternoonTeams(List<String> afternoonTeams) {
        this.afternoonTeams = afternoonTeams;
    }
}
