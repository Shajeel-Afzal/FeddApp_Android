package appdev.ncsu.feddapp_androidv6;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import appdev.ncsu.feddapp_androidv6.utils.Consts;
import appdev.ncsu.feddapp_androidv6.utils.PrefUtils;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class BaseActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    public void onStart() {
        super.onStart();
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null && !PrefUtils.getBoolean(BaseActivity.this, Consts.LEADERBOARD_KEY)) {
                    /* Move user to LoginActivity, and remove the backstack */
                    Intent intent = new Intent(BaseActivity.this, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }
        };

        if (!((this instanceof LoginActivity)) && !((this instanceof LoginActivity)))
            mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null && !((this instanceof LoginActivity))) {
            mAuth.removeAuthStateListener(mAuthListener);
        }

    }


}
