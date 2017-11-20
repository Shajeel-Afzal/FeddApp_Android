package appdev.ncsu.feddapp_androidv6.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.models.TeamModel;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class TeamVH extends RecyclerView.ViewHolder {

    private TextView teamNameTV, scoreTV;

    public TeamVH(View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View itemView) {
        teamNameTV = itemView.findViewById(R.id.team_name_tv);
        scoreTV = itemView.findViewById(R.id.score_tv);
    }

    public void setData(TeamModel model) {
        teamNameTV.setText(model.getName());
        scoreTV.setText(String.valueOf(model.getTotal() == null ? "" : model.getTotal()));
    }
}
