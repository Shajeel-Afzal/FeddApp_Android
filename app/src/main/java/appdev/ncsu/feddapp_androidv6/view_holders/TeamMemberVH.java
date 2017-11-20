package appdev.ncsu.feddapp_androidv6.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import appdev.ncsu.feddapp_androidv6.R;

/**
 * Created by shajeelafzal on 20/11/2017.
 */

public class TeamMemberVH extends RecyclerView.ViewHolder {

    private TextView mTeamMemberNameTV;

    public TeamMemberVH(View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View itemView) {
        mTeamMemberNameTV = itemView.findViewById(R.id.team_member_name);
    }

    public void setData(String s) {
        mTeamMemberNameTV.setText(s);
    }
}
