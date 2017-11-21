package appdev.ncsu.feddapp_androidv6.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.listeners.OnRecyclerItemClickListener;
import appdev.ncsu.feddapp_androidv6.models.ProjectModel;
import appdev.ncsu.feddapp_androidv6.view_holders.ProjectVH;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class ProjectsAdapter extends RecyclerView.Adapter<ProjectVH> {

    private final ArrayList<ProjectModel> projectModels;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private ProjectVH mProjectVH;

    public ProjectsAdapter(OnRecyclerItemClickListener onRecyclerItemClickListener) {

        this.onRecyclerItemClickListener = onRecyclerItemClickListener;

        projectModels = new ArrayList<>();
        projectModels.add(new ProjectModel("3D Printing", R.drawable.dprinting));
        projectModels.add(new ProjectModel("Animatronics", R.drawable.animatronics));
        projectModels.add(new ProjectModel("Arcade Game", R.drawable.arcadegame));
        projectModels.add(new ProjectModel("Automatic Chicken Coop Door", R.drawable.chicken));
        projectModels.add(new ProjectModel("Bubble Blowing Machine", R.drawable.bubble));
        projectModels.add(new ProjectModel("Collapsible Bridge", R.drawable.bridge));
        projectModels.add(new ProjectModel("Concrete Canoe", R.drawable.canoe));
        projectModels.add(new ProjectModel("Educational Computer Game", R.drawable.computergame));
        projectModels.add(new ProjectModel("Fabric Bucket", R.drawable.bucket));
        projectModels.add(new ProjectModel("Fountain", R.drawable.fountain));
        projectModels.add(new ProjectModel("Hovercraft", R.drawable.hovercraft));
        projectModels.add(new ProjectModel("Mechanical Music Machine", R.drawable.music));
        projectModels.add(new ProjectModel("Nuclear Power Probe", R.drawable.nuclear));
        projectModels.add(new ProjectModel("Precision Launcher", R.drawable.launcher));
        projectModels.add(new ProjectModel("Toy Design", R.drawable.toy));
        projectModels.add(new ProjectModel("Shajeel Test Category", R.drawable.dprinting));
    }

    public ProjectModel getProjectModel(int position) {
        return projectModels.get(position);
    }

    @Override
    public ProjectVH onCreateViewHolder(ViewGroup parent, int viewType) {
        mProjectVH = new ProjectVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.project_list_item_layout, parent, false));
        mProjectVH.setOnRecyclerItemClickListener(onRecyclerItemClickListener);
        return mProjectVH;
    }

    @Override
    public void onBindViewHolder(ProjectVH holder, int position) {
        holder.setData(projectModels.get(position));
    }

    @Override
    public int getItemCount() {
        return projectModels.size();
    }
}
