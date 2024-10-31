package com.google.android.libraries.places.internal;

import android.content.Context;
import android.os.Build;
import android.os.DropBoxManager;
import android.util.Log;
import java.util.LinkedHashMap;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzhk {
    private static DropBoxManager zza;
    private static final LinkedHashMap zzb = new zzhj(16, 0.75f, true);
    private static String zzc;

    public static synchronized void zza(Context context, boolean z) {
        synchronized (zzhk.class) {
            if (zza == null) {
                zza = (DropBoxManager) context.getApplicationContext().getSystemService("dropbox");
                zzc = "com.google.android.libraries.places";
            }
        }
    }

    public static synchronized void zzb(Throwable th) {
        long j;
        synchronized (zzhk.class) {
            long id = Thread.currentThread().getId();
            int hashCode = th.hashCode();
            Integer num = (Integer) zzb.get(Long.valueOf(id));
            if (num == null || num.intValue() != hashCode) {
                DropBoxManager dropBoxManager = zza;
                if (dropBoxManager == null || !dropBoxManager.isTagEnabled("system_app_crash")) {
                    return;
                }
                DropBoxManager dropBoxManager2 = zza;
                StringBuilder sb = new StringBuilder();
                Object[] objArr = new Object[3];
                objArr[0] = zzc;
                List zzc2 = zzjt.zzb('.').zzc("3.2.0");
                if (zzc2.size() == 3) {
                    long j2 = 0;
                    for (int i = 0; i < zzc2.size(); i++) {
                        try {
                            j2 = (j2 * 100) + Integer.parseInt((String) zzc2.get(i));
                        } catch (NumberFormatException unused) {
                        }
                    }
                    j = j2;
                    objArr[1] = Long.valueOf(j);
                    objArr[2] = "3.2.0";
                    sb.append(String.format("Package: %s v%d (%s)\n", objArr));
                    sb.append(String.format("Build: %s\n", Build.FINGERPRINT));
                    sb.append("\n");
                    sb.append(Log.getStackTraceString(th));
                    dropBoxManager2.addText("system_app_crash", sb.toString());
                    zzb.put(Long.valueOf(id), Integer.valueOf(hashCode));
                }
                j = -1;
                objArr[1] = Long.valueOf(j);
                objArr[2] = "3.2.0";
                sb.append(String.format("Package: %s v%d (%s)\n", objArr));
                sb.append(String.format("Build: %s\n", Build.FINGERPRINT));
                sb.append("\n");
                sb.append(Log.getStackTraceString(th));
                dropBoxManager2.addText("system_app_crash", sb.toString());
                zzb.put(Long.valueOf(id), Integer.valueOf(hashCode));
            }
        }
    }
}
