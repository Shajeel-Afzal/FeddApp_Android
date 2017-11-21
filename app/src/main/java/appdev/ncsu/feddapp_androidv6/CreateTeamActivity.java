package appdev.ncsu.feddapp_androidv6;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

import appdev.ncsu.feddapp_androidv6.models.TeamModel;
import appdev.ncsu.feddapp_androidv6.utils.Consts;

public class CreateTeamActivity extends BaseActivity {

    private EditText mTeamNameET;
    private EditText mTeamMember1;
    private EditText mTeamMember2;
    private EditText mTeamMember3;
    private EditText mTeamMember4;
    private EditText mTeamMember5;
    private String mProjectName;
    private Switch mTeamTypeSwitch;

    public static void start(Context context, String projectName) {
        Intent starter = new Intent(context, CreateTeamActivity.class);
        starter.putExtra(Consts.KEY_PROJECT_NAME, projectName);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_team);

        mProjectName = getIntent().getStringExtra(Consts.KEY_PROJECT_NAME);

        mTeamNameET = findViewById(R.id.team_name_et);
        mTeamMember1 = findViewById(R.id.team_member_1);
        mTeamMember2 = findViewById(R.id.team_member_2);
        mTeamMember3 = findViewById(R.id.team_member_3);
        mTeamMember4 = findViewById(R.id.team_member_4);
        mTeamMember5 = findViewById(R.id.team_member_5);
        final TextView sessionTypeTV = findViewById(R.id.session_type_tv);
        Toolbar mToolbar = findViewById(R.id.toolbar);

        setSupportActionBar(mToolbar);

        mTeamTypeSwitch = findViewById(R.id.team_type_switch);

        mTeamTypeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    sessionTypeTV.setText("Session (Afternoon)");
                } else {
                    sessionTypeTV.setText("Session (Morning)");
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_team_activity, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.create_team_item) {
            // TODO Save the team on Firestore
            if (TextUtils.isEmpty(mTeamNameET.getText())) {
                Toast.makeText(this, "Team is required!", Toast.LENGTH_SHORT).show();
                return true;
            }

            DocumentReference newTeamRef = FirebaseFirestore.getInstance()
                    .collection(Consts.KEY_FIRSTORE_COLLECTION_PROJECTS)
                    .document(mProjectName)
                    .collection(mTeamTypeSwitch.isChecked() ?
                            Consts.KEY_FIRSTORE_COLLECTION_AFTERNOON :
                            Consts.KEY_FIRSTORE_COLLECTION_MORNING)
                    .document();

            List<String> membersArrayList = new ArrayList<>();

            if (!TextUtils.isEmpty(mTeamMember1.getText()))
                membersArrayList.add(mTeamMember1.getText().toString());

            if (!TextUtils.isEmpty(mTeamMember2.getText()))
                membersArrayList.add(mTeamMember2.getText().toString());

            if (!TextUtils.isEmpty(mTeamMember3.getText()))
                membersArrayList.add(mTeamMember3.getText().toString());

            if (!TextUtils.isEmpty(mTeamMember4.getText()))
                membersArrayList.add(mTeamMember4.getText().toString());

            if (!TextUtils.isEmpty(mTeamMember5.getText()))
                membersArrayList.add(mTeamMember5.getText().toString());

            TeamModel teamModel = new TeamModel(mTeamNameET.getText().toString(),
                    0.0, true, membersArrayList);


            final ProgressDialog progressDialog = ProgressDialog.show(this, "Adding Team", "Please wait!");
            newTeamRef.set(teamModel).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    progressDialog.dismiss();
                    if (!task.isSuccessful()) {
                        Exception exception = task.getException();
                    } else {
                        Toast.makeText(CreateTeamActivity.this, "Team Added!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            });
        }
        return true;
    }
}
