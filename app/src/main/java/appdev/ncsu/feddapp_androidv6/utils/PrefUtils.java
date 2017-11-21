package appdev.ncsu.feddapp_androidv6.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class PrefUtils {

    private static String getDefaultSharedPreferencesName(Context context) {
        return context.getPackageName() + "app_prefs";
    }

    public static void saveBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static void putString(Context context, String key, String value) {
        if (context == null)
            return;

        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        return prefs.getString(key, "");
    }

    public static void saveInteger(Context context, String key, Integer value) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInteger(Context context, String key) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getInt(key, 0);
    }

    public static boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        return prefs.getBoolean(key, false);
    }

    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        SharedPreferences prefs = context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE);
        return prefs.getBoolean(key, defaultValue);
    }

    public static int getInteger(Context context, String key, int defaultValue) {
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(context);
        return prefs.getInt(key, defaultValue);
    }

    public static void clearAllPrefs(Context context) {
        context.getSharedPreferences(getDefaultSharedPreferencesName(context),
                Context.MODE_PRIVATE).edit().clear().apply();
    }


}

