package appdev.ncsu.feddapp_androidv6.view_holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.models.ProjectModel;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class ProjectVH extends RecyclerView.ViewHolder {

    private ImageView projectIV;
    private TextView projectName;

    public ProjectVH(View itemView) {
        super(itemView);
        findViews(itemView);
    }

    private void findViews(View itemView) {
        projectIV = itemView.findViewById(R.id.project_iv);
        projectName = itemView.findViewById(R.id.project_name_tv);
    }

    public void setData(ProjectModel projectModel) {
        projectName.setText(projectModel.getProjectName());

        Glide.with(projectName.getContext())
                .load(projectModel.getDrawableId())
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(projectIV);
    }
}
