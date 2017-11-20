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

import appdev.ncsu.feddapp_androidv6.fragments.TeamsFragment;
import appdev.ncsu.feddapp_androidv6.utils.Consts;

public class TeamsActivity extends BaseActivity {

    private TeamsFragment mMorningTeamsFrag;
    private TeamsFragment mAfternoonTeamsFrag;
    private String mProjectName;

    public static void start(Context context, String projectName) {
        Intent starter = new Intent(context, TeamsActivity.class);
        starter.putExtra(Consts.KEY_PROJECT_NAME, projectName);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_teams);

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

        mProjectName = getIntent().getStringExtra(Consts.KEY_PROJECT_NAME);
    }

    public class SampleFragmentPagerAdapter extends FragmentPagerAdapter {
        final int PAGE_COUNT = 2;
        private String tabTitles[] = new String[]{getString(R.string.morning), getString(R.string.afternoon)};

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
                    if (mMorningTeamsFrag == null)
                        mMorningTeamsFrag = TeamsFragment.newInstance(mProjectName, Consts.KEY_FIRSTORE_COLLECTION_MORNING);

                    return mMorningTeamsFrag;
                case 1:
                    if (mAfternoonTeamsFrag == null)
                        mAfternoonTeamsFrag = TeamsFragment.newInstance(mProjectName, Consts.KEY_FIRSTORE_COLLECTION_AFTERNOON);

                    return mAfternoonTeamsFrag;
                default:
                    if (mAfternoonTeamsFrag == null)
                        mAfternoonTeamsFrag = TeamsFragment.newInstance(mProjectName, Consts.KEY_FIRSTORE_COLLECTION_AFTERNOON);

                    return mAfternoonTeamsFrag;
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            // Generate title based on item position
            return tabTitles[position];
        }
    }
}
