package appdev.ncsu.feddapp_androidv6.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.TeamDetailActivity;
import appdev.ncsu.feddapp_androidv6.listeners.OnRecyclerItemClickListener;
import appdev.ncsu.feddapp_androidv6.models.TeamModel;
import appdev.ncsu.feddapp_androidv6.utils.Consts;
import appdev.ncsu.feddapp_androidv6.view_holders.TeamVH;
import cz.kinst.jakub.view.SimpleStatefulLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamsFragment extends Fragment {

    private static final String TAG = "TeamsFragment";
    private SimpleStatefulLayout mStatefulLayout;
    private RecyclerView mRV;
    private FirestoreRecyclerAdapter<TeamModel, TeamVH> mAdapter;

    public static TeamsFragment newInstance(String projectName, String type) {

        Bundle args = new Bundle();
        args.putString(Consts.KEY_PROJECT_NAME, projectName);
        args.putString(Consts.KEY_TEAM_TYPE, type);
        TeamsFragment fragment = new TeamsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TeamsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_morning_teams, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);

        final String projectName = getArguments().getString(Consts.KEY_PROJECT_NAME);
        final String type = getArguments().getString(Consts.KEY_TEAM_TYPE, Consts.KEY_FIRSTORE_COLLECTION_MORNING);

        if (projectName == null || projectName.equals("")) {
            mStatefulLayout.setEmptyText("Team Name is empty or null!");
            mStatefulLayout.showEmpty();
            return;
        }

        mStatefulLayout.showProgress();

        Query query = FirebaseFirestore.getInstance()
                .collection(Consts.KEY_FIRSTORE_COLLECTION_PROJECTS)
                .document(projectName)
                .collection(type);

        FirestoreRecyclerOptions<TeamModel> options = new FirestoreRecyclerOptions.Builder<TeamModel>()
                .setQuery(query, TeamModel.class)
                .build();

        mAdapter = new FirestoreRecyclerAdapter<TeamModel, TeamVH>(options) {
            @Override
            public void onBindViewHolder(TeamVH holder, int position, final TeamModel model) {
                holder.setData(model);
                holder.setOnRecyclerViewClickListener(new OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        if (FirebaseAuth.getInstance().getCurrentUser() != null)
                            TeamDetailActivity.start(getActivity(), projectName, type, model);
                    }
                });
            }

            @Override
            public TeamVH onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.team_list_item_layout, group, false);

                return new TeamVH(view);
            }

            @Override
            public void onDataChanged() {
                super.onDataChanged();
                mStatefulLayout.showContent();
            }

            @Override
            public void onError(FirebaseFirestoreException e) {
                super.onError(e);

                if (e.getCode() == FirebaseFirestoreException.Code.PERMISSION_DENIED) {
                    mStatefulLayout.setEmptyText("No Permission");
                    mStatefulLayout.showEmpty();
                } else {
                    mStatefulLayout.showEmpty();
                    mStatefulLayout.setEmptyText(R.string.something_went_wrong_while_getting_teams);
                }
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        mRV.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRV.getContext(),
                linearLayoutManager.getOrientation());
        mRV.addItemDecoration(dividerItemDecoration);

        mRV.setAdapter(mAdapter);
    }

    private void findViews(View view) {
        mStatefulLayout = view.findViewById(R.id.state_full_layout);
        mRV = view.findViewById(R.id.recycler_view);
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
    }

}
