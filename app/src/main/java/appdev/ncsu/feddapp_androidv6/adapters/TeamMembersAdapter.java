package appdev.ncsu.feddapp_androidv6.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.view_holders.TeamMemberVH;

/**
 * Created by shajeelafzal on 20/11/2017.
 */

public class TeamMembersAdapter extends RecyclerView.Adapter<TeamMemberVH> {

    private final List<String> mTeamMembers;

    public TeamMembersAdapter(List<String> teamMembers) {
        this.mTeamMembers = teamMembers;
    }

    @Override
    public TeamMemberVH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TeamMemberVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_members_list_item_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(TeamMemberVH holder, int position) {
        holder.setData(mTeamMembers.get(position));
    }

    @Override
    public int getItemCount() {
        return mTeamMembers.size();
    }

}
