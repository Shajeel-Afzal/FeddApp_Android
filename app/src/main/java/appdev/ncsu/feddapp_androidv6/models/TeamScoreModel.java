package appdev.ncsu.feddapp_androidv6.models;

/**
 * Created by shajeelafzal on 20/11/2017.
 */

public class TeamScoreModel {

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
}
