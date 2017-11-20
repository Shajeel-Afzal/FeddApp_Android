package appdev.ncsu.feddapp_androidv6.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.listeners.OnRecyclerItemClickListener;

/**
 * Created by shajeelafzal on 20/11/2017.
 */

public class TeamRequirementsVH extends RecyclerView.ViewHolder {

    private final TextView mRequirementsTV;
    private OnRecyclerItemClickListener onRecyclerViewClickListener;

    public TeamRequirementsVH(View itemView) {
        super(itemView);
        mRequirementsTV = itemView.findViewById(R.id.requirements_tv);
    }

    public void setOnRecyclerViewClickListener(OnRecyclerItemClickListener onRecyclerViewClickListener) {
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
    }

    public void setData(String teamRequirements) {
        mRequirementsTV.setText(teamRequirements);
    }

}
