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

import appdev.ncsu.feddapp_androidv6.R;
import appdev.ncsu.feddapp_androidv6.adapters.TeamRequirementsAdapter;
import cz.kinst.jakub.view.SimpleStatefulLayout;

/**
 * A simple {@link Fragment} subclass.
 */
public class TeamRequirementsFragment extends Fragment {

    private SimpleStatefulLayout mStatefulLayout;
    private RecyclerView mRV;

    public static TeamRequirementsFragment newInstance() {

        Bundle args = new Bundle();

        TeamRequirementsFragment fragment = new TeamRequirementsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public TeamRequirementsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team_requirements, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViews(view);

        mStatefulLayout.showProgress();

        TeamRequirementsAdapter mAdapter = new TeamRequirementsAdapter();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                LinearLayoutManager.VERTICAL, false);

        mRV.setLayoutManager(linearLayoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRV.getContext(),
                linearLayoutManager.getOrientation());
        mRV.addItemDecoration(dividerItemDecoration);

        mRV.setAdapter(mAdapter);

        mStatefulLayout.showContent();
    }

    private void findViews(View view) {
        mStatefulLayout = view.findViewById(R.id.state_full_layout);
        mRV = view.findViewById(R.id.recycler_view);
    }

}
