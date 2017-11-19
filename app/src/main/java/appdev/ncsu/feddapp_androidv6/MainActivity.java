package appdev.ncsu.feddapp_androidv6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;

import appdev.ncsu.feddapp_androidv6.adapters.ProjectsAdapter;
import appdev.ncsu.feddapp_androidv6.listeners.OnRecyclerItemClickListener;
import appdev.ncsu.feddapp_androidv6.utils.AppUtils;
import appdev.ncsu.feddapp_androidv6.utils.GridSpacingItemDecoration;

public class MainActivity extends BaseActivity {

    private ProjectsAdapter mAdapter;

    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView mRecyclerView = findViewById(R.id.recycler_view);

        int numberOfColumns = 1;
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, numberOfColumns));
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(numberOfColumns,
                (int) AppUtils.convertDpToPixel(16, this), true));

        mAdapter = new ProjectsAdapter(new OnRecyclerItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                ProjectTeamsActivity.start(MainActivity.this);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_activity_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            if (FirebaseAuth.getInstance().getCurrentUser() != null) {
                AuthUI.getInstance().signOut(this);
            }
        }
        return super.onOptionsItemSelected(item);
    }

}
