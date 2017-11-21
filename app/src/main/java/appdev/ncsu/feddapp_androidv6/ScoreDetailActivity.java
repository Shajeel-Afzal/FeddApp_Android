package appdev.ncsu.feddapp_androidv6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

import appdev.ncsu.feddapp_androidv6.models.TeamModel;
import appdev.ncsu.feddapp_androidv6.models.TeamScoreModel;
import appdev.ncsu.feddapp_androidv6.utils.Consts;
import appdev.ncsu.feddapp_androidv6.view_holders.TeamVH;
import cz.kinst.jakub.view.SimpleStatefulLayout;

public class ScoreDetailActivity extends BaseActivity {

    private FirestoreRecyclerAdapter<TeamModel, TeamVH> mAdapter;

    public static void start(Context context, String projectName, String type, String teamName, TeamScoreModel teamScoreModel) {
        Intent starter = new Intent(context, ScoreDetailActivity.class);
        starter.putExtra(Consts.KEY_PROJECT_NAME, projectName);
        starter.putExtra(Consts.KEY_TEAM_TYPE, type);
        starter.putExtra(Consts.KEY_TEAM_NAME, teamName);
        starter.putExtra(Consts.KEY_TEAM_SCORE_MODEL, teamScoreModel);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_detail);

        String projectName = getIntent().getStringExtra(Consts.KEY_PROJECT_NAME);
        String type = getIntent().getStringExtra(Consts.KEY_TEAM_TYPE);
        String teamName = getIntent().getStringExtra(Consts.KEY_TEAM_NAME);
        TeamScoreModel teamScoreModel = getIntent().getParcelableExtra(Consts.KEY_TEAM_SCORE_MODEL);

        RecyclerView mRV = findViewById(R.id.recycler_view);
        SimpleStatefulLayout mStatefulLayout = findViewById(R.id.state_full_layout);

        mStatefulLayout.showProgress();

//        Query query = FirebaseFirestore.getInstance()
//                .collection(Consts.KEY_FIRSTORE_COLLECTION_PROJECTS)
//                .document(projectName)
//                .collection(type)
//                .document();
//
//        FirestoreRecyclerOptions<TeamModel> options = new FirestoreRecyclerOptions.Builder<TeamModel>()
//                .setQuery(query, TeamModel.class)
//                .build();
//
//        mAdapter = new FirestoreRecyclerAdapter<TeamModel, TeamVH>(options) {
//            @Override
//            public void onBindViewHolder(TeamVH holder, int position, final TeamModel model) {
//                holder.setData(model);
//                holder.setOnRecyclerViewClickListener(new OnRecyclerItemClickListener() {
//                    @Override
//                    public void onItemClick(View view, int position) {
//                        TeamDetailActivity.start(getActivity(), projectName, type, model);
//                    }
//                });
//            }
//
//            @Override
//            public TeamVH onCreateViewHolder(ViewGroup group, int i) {
//                View view = LayoutInflater.from(group.getContext())
//                        .inflate(R.layout.team_list_item_layout, group, false);
//
//                return new TeamVH(view);
//            }
//
//            @Override
//            public void onDataChanged() {
//                super.onDataChanged();
//                mStatefulLayout.showContent();
//            }
//
//            @Override
//            public void onError(FirebaseFirestoreException e) {
//                super.onError(e);
//
//                if (e.getCode() == FirebaseFirestoreException.Code.PERMISSION_DENIED) {
//                    mStatefulLayout.setEmptyText("No Permission");
//                    mStatefulLayout.showEmpty();
//                } else {
//                    mStatefulLayout.showEmpty();
//                    mStatefulLayout.setEmptyText(R.string.something_went_wrong_while_getting_teams);
//                }
//            }
//        };
//
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
//                LinearLayoutManager.VERTICAL, false);
//
//        mRV.setLayoutManager(linearLayoutManager);
//
//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mRV.getContext(),
//                linearLayoutManager.getOrientation());
//        mRV.addItemDecoration(dividerItemDecoration);
//
//        mRV.setAdapter(mAdapter);

    }
}
