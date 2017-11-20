package appdev.ncsu.feddapp_androidv6;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.ArrayMap;

/**
 * Created by shajeelafzal on 19/11/2017.
 */

public class GlobalApplication extends Application {

    private ArrayMap<String, Integer> projectNameSrcMap;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

}
