package appdev.ncsu.feddapp_androidv6.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.listeners.OnRecyclerItemClickListener;
import appdev.ncsu.feddapp_androidv6.models.ProjectModel;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class ProjectVH extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView projectIV;
    private TextView projectName;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public ProjectVH(View itemView) {
        super(itemView);
        findViews(itemView);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    private void findViews(View itemView) {
        projectIV = itemView.findViewById(R.id.project_iv);
        projectName = itemView.findViewById(R.id.project_name_tv);
        itemView.setOnClickListener(this);
    }

    public void setData(ProjectModel projectModel) {
        projectName.setText(projectModel.getProjectName());

        Glide.with(projectName.getContext())
                .load(projectModel.getDrawableId())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(projectIV);
    }

    @Override
    public void onClick(View view) {
        if (onRecyclerItemClickListener != null)
            onRecyclerItemClickListener.onItemClick(view, getAdapterPosition());
    }
}
