package com.myor.mydex.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import java.net.InetAddress;
import java.net.UnknownHostException;

/* loaded from: classes2.dex */
public class CheckConnectionService {
    private static CheckConnectionService sInstance;
    public Context context;

    public CheckConnectionService(Context context) {
        this.context = context;
    }

    public static CheckConnectionService getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new CheckConnectionService(context.getApplicationContext());
        }
        return sInstance;
    }

    public boolean isInternetAvailable() {
        try {
            return !InetAddress.getByName("www.google.com").equals("");
        } catch (UnknownHostException unused) {
            return false;
        }
    }

    public boolean isInternet() {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) this.context.getSystemService("connectivity")).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
