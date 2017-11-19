package appdev.ncsu.feddapp_androidv6.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import appdev.ncsu.feddapp_androidv6.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AfternoonTeamsFragment extends Fragment {

    public static AfternoonTeamsFragment newInstance() {

        Bundle args = new Bundle();

        AfternoonTeamsFragment fragment = new AfternoonTeamsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public AfternoonTeamsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_afternoon_teams, container, false);
    }

}
