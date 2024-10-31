package com.google.android.libraries.places.internal;

import android.content.Context;
import android.content.pm.PackageManager;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzhi {
    public static zzhh zzd(Context context) {
        String packageName = context.getPackageName();
        int i = 0;
        try {
            i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException unused) {
        }
        zzhe zzheVar = new zzhe();
        zzheVar.zza(packageName);
        zzheVar.zzb(i);
        zzheVar.zzd(1);
        return zzheVar;
    }

    public abstract int zza();

    public abstract String zzb();

    public abstract int zzc();
}
