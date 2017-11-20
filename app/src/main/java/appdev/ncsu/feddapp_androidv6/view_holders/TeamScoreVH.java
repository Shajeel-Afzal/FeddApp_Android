package appdev.ncsu.feddapp_androidv6.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.listeners.OnRecyclerItemClickListener;
import appdev.ncsu.feddapp_androidv6.models.TeamScoreModel;

/**
 * Created by shajeelafzal on 20/11/2017.
 */

public class TeamScoreVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView teamNameTV, scoreTV;
    private View mItemView;
    private OnRecyclerItemClickListener onRecyclerViewClickListener;

    public TeamScoreVH(View itemView) {
        super(itemView);
        findViews(itemView);
        itemView.setOnClickListener(this);
    }

    private void findViews(View itemView) {
        teamNameTV = itemView.findViewById(R.id.team_name_tv);
        scoreTV = itemView.findViewById(R.id.score_tv);
        this.mItemView = itemView.findViewById(R.id.team_list_item_layout_id);
    }

    public void setData(TeamScoreModel model) {
        teamNameTV.setText(model.getName());
//        scoreTV.setText(String.valueOf(model.getTotal() == null ? "" : model.getTotal()));
    }

    public void setOnRecyclerViewClickListener(OnRecyclerItemClickListener onRecyclerViewClickListener) {
        this.onRecyclerViewClickListener = onRecyclerViewClickListener;
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerViewClickListener != null)
            onRecyclerViewClickListener.onItemClick(view, getAdapterPosition());
    }
}
