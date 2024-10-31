package com.myor.mydex.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/* loaded from: classes2.dex */
public class SettingPreferences {
    static final String KEY_BASECONTEXT = "key_bcip";
    static final String KEY_BASECONTEXT_IMG = "key_bcip_img";
    static final String KEY_BASECONTEXT_IP_FTP = "key_img_ftp";
    static final String KEY_IP = "key_ip";
    static final String KEY_IP_IMG = "key_ip_img";
    static final String KEY_MASTER_OUTLET = "key_master_outlet";
    static final String KEY_PORTIP = "key_portip";
    static final String KEY_PORTIPSENDDATA = "key_portip_data";
    static final String KEY_PORTIPSENDIMG = "key_portip_img";
    static final String KEY_POS_REG = "key_region_reg";
    static final String KEY_REGION = "key_region";
    static final String KEY_UNV_ACTIVE = "key_unv_active";

    private static SharedPreferences getSharedPreference(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void setKeyIp(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_IP, str);
        edit.apply();
    }

    public static String getKeyIp(Context context) {
        return getSharedPreference(context).getString(KEY_IP, "");
    }

    public static void setKeyPortIp(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_PORTIP, str);
        edit.apply();
    }

    public static String getKeyPortIp(Context context) {
        return getSharedPreference(context).getString(KEY_PORTIP, "");
    }

    public static void setKeyPortipsenddatap(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_PORTIPSENDDATA, str);
        edit.apply();
    }

    public static String getKeyPortipsenddata(Context context) {
        return getSharedPreference(context).getString(KEY_PORTIPSENDDATA, "");
    }

    public static void setKeyPortipsendimg(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_PORTIPSENDIMG, str);
        edit.apply();
    }

    public static String getKeyPortipsendimg(Context context) {
        return getSharedPreference(context).getString(KEY_PORTIPSENDIMG, "");
    }

    public static void setKeyBaseContextIp(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_BASECONTEXT, str);
        edit.apply();
    }

    public static String getKeyBaseContextIp(Context context) {
        return getSharedPreference(context).getString(KEY_BASECONTEXT, "");
    }

    public static void setKeyBaseContextIpImg(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_BASECONTEXT_IMG, str);
        edit.apply();
    }

    public static String getKeyBaseContextIpImg(Context context) {
        return getSharedPreference(context).getString(KEY_BASECONTEXT_IMG, "");
    }

    public static String setKeyBaseContextIpImg(Context context) {
        return getSharedPreference(context).getString(KEY_BASECONTEXT_IMG, "");
    }

    public static void setKeyIpImg(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_IP_IMG, str);
        edit.apply();
    }

    public static String getKeyIpImg(Context context) {
        return getSharedPreference(context).getString(KEY_IP_IMG, "");
    }

    public static void setMasterOutlet(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_MASTER_OUTLET, str);
        edit.apply();
    }

    public static String getMasterOutlet(Context context) {
        return getSharedPreference(context).getString(KEY_MASTER_OUTLET, "");
    }

    public static void clearMasterOutlet(Context context) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.remove(KEY_MASTER_OUTLET);
        edit.apply();
    }

    public static void setRegionNm(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_REGION, str);
        edit.apply();
    }

    public static String getRegionNm(Context context) {
        return getSharedPreference(context).getString(KEY_REGION, "");
    }

    public static void clearRegionNm(Context context) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.remove(KEY_REGION);
        edit.apply();
    }

    public static void setRegionPos(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_POS_REG, str);
        edit.apply();
    }

    public static String getRegionPos(Context context) {
        return getSharedPreference(context).getString(KEY_POS_REG, "");
    }

    public static void clearRegionPos(Context context) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.remove(KEY_POS_REG);
        edit.apply();
    }

    public static void setKeyUnvActive(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_UNV_ACTIVE, str);
        edit.apply();
    }

    public static String getKeyUnvActive(Context context) {
        return getSharedPreference(context).getString(KEY_UNV_ACTIVE, "");
    }

    public static void clearKeyUnvActive(Context context) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.remove(KEY_UNV_ACTIVE);
        edit.apply();
    }

    public static void setKeyBaseContextIpImgFtp(Context context, String str) {
        SharedPreferences.Editor edit = getSharedPreference(context).edit();
        edit.putString(KEY_BASECONTEXT_IP_FTP, str);
        edit.apply();
    }

    public static String getKeyBaseContextIpImgFtp(Context context) {
        return getSharedPreference(context).getString(KEY_BASECONTEXT_IP_FTP, "");
    }
}
