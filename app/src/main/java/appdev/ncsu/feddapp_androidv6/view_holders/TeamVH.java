package appdev.ncsu.feddapp_androidv6.view_holders;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.listeners.OnRecyclerItemClickListener;
import appdev.ncsu.feddapp_androidv6.models.TeamModel;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class TeamVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView teamNameTV, scoreTV;
    private View mItemView;
    private OnRecyclerItemClickListener onRecyclerViewClickListener;

    public TeamVH(View itemView) {
        super(itemView);
        findViews(itemView);
        itemView.setOnClickListener(this);
    }

    private void findViews(View itemView) {
        teamNameTV = itemView.findViewById(R.id.team_name_tv);
        scoreTV = itemView.findViewById(R.id.score_tv);
        this.mItemView = itemView.findViewById(R.id.team_list_item_layout_id);
    }

    public void setData(TeamModel model) {
        teamNameTV.setText(model.getName());
        scoreTV.setText(String.valueOf(model.getTotal() == null ? "0" : String.format(Locale.US,
                "%.3f", model.getTotal())));

        if (!model.isPublished()) {
            mItemView.setBackgroundColor(Color.GRAY);
        } else {
            mItemView.setBackgroundColor(Color.TRANSPARENT);
        }
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
