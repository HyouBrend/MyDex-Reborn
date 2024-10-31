package com.google.android.libraries.places.internal;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiInfo;
import android.text.TextUtils;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzds {
    private final String zza;
    private final int zzb;
    private final zzdr zzc;
    private final boolean zzd;
    private final int zze;

    public zzds(WifiInfo wifiInfo, ScanResult scanResult) {
        zzdr zzdrVar;
        String str = scanResult.BSSID;
        String str2 = scanResult.capabilities;
        int i = scanResult.level;
        int i2 = scanResult.frequency;
        if (TextUtils.isEmpty(str2)) {
            zzdrVar = zzdr.OTHER;
        } else {
            String upperCase = str2.toUpperCase();
            if (upperCase.equals("[ESS]") || upperCase.equals("[IBSS]")) {
                zzdrVar = zzdr.NONE;
            } else if (upperCase.matches(".*WPA[0-9]*-PSK.*")) {
                zzdrVar = zzdr.PSK;
            } else if (upperCase.matches(".*WPA[0-9]*-EAP.*")) {
                zzdrVar = zzdr.EAP;
            } else {
                zzdrVar = zzdr.OTHER;
            }
        }
        boolean z = false;
        if (wifiInfo != null && !TextUtils.isEmpty(str) && str.equalsIgnoreCase(wifiInfo.getBSSID())) {
            z = true;
        }
        this.zza = str;
        this.zzb = i;
        this.zzc = zzdrVar;
        this.zzd = z;
        this.zze = i2;
    }

    public final int zza() {
        return this.zze;
    }

    public final int zzb() {
        return this.zzb;
    }

    public final zzdr zzc() {
        return this.zzc;
    }

    public final String zzd() {
        return this.zza;
    }

    public final boolean zze() {
        return this.zzd;
    }
}
