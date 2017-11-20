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
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.listeners.OnRecyclerItemClickListener;
import appdev.ncsu.feddapp_androidv6.models.TeamScoreModel;
import appdev.ncsu.feddapp_androidv6.utils.Consts;
import appdev.ncsu.feddapp_androidv6.view_holders.TeamScoreVH;
import cz.kinst.jakub.view.SimpleStatefulLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamScoresListFragment extends Fragment {

    private String mTeamName;
    private SimpleStatefulLayout mStatefulLayout;
    private RecyclerView mRV;
    private String mProjectName;
    private String mTeamType;
    private FirestoreRecyclerAdapter<TeamScoreModel, TeamScoreVH> mAdapter;

    public static TeamScoresListFragment newInstance(String projectName, String teamType, String teamName) {

        Bundle args = new Bundle();
        args.putString(Consts.KEY_TEAM_NAME, teamName);
        args.putString(Consts.KEY_PROJECT_NAME, projectName);
        args.putString(Consts.KEY_TEAM_TYPE, teamType);
        TeamScoresListFragment fragment = new TeamScoresListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TeamScoresListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTeamName = getArguments().getString(Consts.KEY_TEAM_NAME);
        mProjectName = getArguments().getString(Consts.KEY_PROJECT_NAME);
        mTeamType = getArguments().getString(Consts.KEY_TEAM_TYPE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_scores, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);

        if (mTeamName == null || mTeamName.equals("")) {
            mStatefulLayout.setEmptyText("Team name is empty or null!");
            mStatefulLayout.showEmpty();
            return;
        }

        mStatefulLayout.showProgress();

        Query query = FirebaseFirestore.getInstance()
                .collection(Consts.KEY_FIRSTORE_COLLECTION_PROJECTS)
                .document(mProjectName)
                .collection(mTeamType)
                .document(mTeamName)
                .collection(Consts.KEY_FIRSTORE_COLLECTION_TEAM_SCORES);

        FirestoreRecyclerOptions<TeamScoreModel> options = new FirestoreRecyclerOptions.Builder<TeamScoreModel>()
                .setQuery(query, TeamScoreModel.class)
                .build();

        mAdapter = new FirestoreRecyclerAdapter<TeamScoreModel, TeamScoreVH>(options) {
            @Override
            public void onBindViewHolder(TeamScoreVH holder, int position, final TeamScoreModel model) {
                holder.setData(model);
                holder.setOnRecyclerViewClickListener(new OnRecyclerItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                    }
                });
            }

            @Override
            public TeamScoreVH onCreateViewHolder(ViewGroup group, int i) {
                View view = LayoutInflater.from(group.getContext())
                        .inflate(R.layout.team_score_list_item_layout, group, false);

                return new TeamScoreVH(view);
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
