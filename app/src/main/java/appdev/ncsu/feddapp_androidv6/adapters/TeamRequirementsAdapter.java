package appdev.ncsu.feddapp_androidv6.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.view_holders.TeamRequirementsVH;

/**
 * Created by shajeelafzal on 20/11/2017.
 */

public class TeamRequirementsAdapter extends RecyclerView.Adapter<TeamRequirementsVH> {

    List<String> mRequirementsList;
    private TeamRequirementsVH holder;

    public TeamRequirementsAdapter() {

        mRequirementsList = new ArrayList<String>();
        mRequirementsList.add("Easily Portable");
        mRequirementsList.add("How two distinct functions");
        mRequirementsList.add("Comprised 25% of 3D printing material Research component provided.");
    }

    @Override
    public TeamRequirementsVH onCreateViewHolder(ViewGroup parent, int viewType) {
        holder = new TeamRequirementsVH(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.team_requirements_list_item_layout, parent, false));
        return holder;
    }

    @Override
    public void onBindViewHolder(TeamRequirementsVH holder, int position) {
        holder.setData(mRequirementsList.get(position));
    }

    @Override
    public int getItemCount() {
        return mRequirementsList.size();
    }

}
