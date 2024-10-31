package com.myor.mydex.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* loaded from: classes2.dex */
public class DriverPreferences {
    static final String KEY_DRIVERNAME = "key_drivername";
    static final String KEY_DRIVERPHONE = "key_driverphone";
    static final String KEY_POLICENO = "key_policeno";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKeyDrivername(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_DRIVERNAME, str);
        edit.apply();
    }

    public static String getKeyDrivername(Context context) {
        return getSharedPreference(context).getString(KEY_DRIVERNAME, "");
    }

    public static void setKeyDriverphone(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_DRIVERPHONE, str);
        edit.apply();
    }

    public static String getKeyDriverphone(Context context) {
        return getSharedPreference(context).getString(KEY_DRIVERPHONE, "");
    }

    public static void setKeyPoliceno(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_POLICENO, str);
        edit.apply();
    }

    public static String getKeyPoliceno(Context context) {
        return getSharedPreference(context).getString(KEY_POLICENO, "");
    }

    public static void clearKeyToken(Context context) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.remove(KEY_DRIVERNAME);
        edit.remove(KEY_DRIVERPHONE);
        edit.remove(KEY_POLICENO);
        edit.apply();
    }
}
