package appdev.ncsu.feddapp_androidv6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import appdev.ncsu.feddapp_androidv6.fragments.MembersFragment;
import appdev.ncsu.feddapp_androidv6.fragments.TeamRequirementsFragment;
import appdev.ncsu.feddapp_androidv6.fragments.TeamScoresListFragment;
import appdev.ncsu.feddapp_androidv6.models.TeamModel;
import appdev.ncsu.feddapp_androidv6.utils.Consts;

public class TeamDetailActivity extends BaseActivity {

    private TeamModel mTeamModel;
    private MembersFragment mMembersFragment;
    private String mProjectName;
    private String mType;
    private TeamScoresListFragment mTeamScoresListFrag;
    private TeamRequirementsFragment mTeamRequirementsFrag;

    public static void start(Context context, String projectName, String type, TeamModel teamModel) {
        Intent starter = new Intent(context, TeamDetailActivity.class);
        starter.putExtra(Consts.KEY_TEAM_MODEL, teamModel);
        starter.putExtra(Consts.KEY_PROJECT_NAME, projectName);
        starter.putExtra(Consts.KEY_TEAM_TYPE, type);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        mTeamModel = getIntent().getParcelableExtra(Consts.KEY_TEAM_MODEL);
        mProjectName = getIntent().getStringExtra(Consts.KEY_PROJECT_NAME);
        mType = getIntent().getStringExtra(Consts.KEY_TEAM_TYPE);

        Toolbar toolbar = findViewById(R.id.toolbar);
        TabLayout mTabLayout = (TabLayout) findViewById(R.id.sliding_tabs);
        // Get the ViewPager and set it's PagerAdapter so that it can display items

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(),
                this));
        mTabLayout.setupWithViewPager(viewPager);
        mTabLayout.setTabMode(TabLayout.MODE_FIXED);
        viewPager.setAdapter(new SampleFragmentPagerAdapter(getSupportFragmentManager(), this));

        setSupportActionBar(toolbar);
    }

    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{getString(R.string.Members), getString(R.string.scores), getString(R.string.requirements)};

        public SampleFragmentPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mMembersFragment == null)
                        mMembersFragment = MembersFragment.newInstance(mTeamModel);

                    return mMembersFragment;
                case 1:
                    if (mTeamScoresListFrag == null)
                        mTeamScoresListFrag = TeamScoresListFragment.newInstance(mProjectName, mType, mTeamModel.getName());

                    return mTeamScoresListFrag;
                case 2:
                    if (mTeamRequirementsFrag == null)
                        mTeamRequirementsFrag = TeamRequirementsFragment.newInstance();

                    return mTeamRequirementsFrag;
                default:
                    if (mTeamRequirementsFrag == null)
                        mTeamRequirementsFrag = TeamRequirementsFragment.newInstance();

                    return mTeamRequirementsFrag;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}
