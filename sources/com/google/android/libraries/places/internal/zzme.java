package com.google.android.libraries.places.internal;

import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzme {
    private static final zzmg zza;

    static {
        String[] strArr;
        strArr = zzmg.zzd;
        zza = zzb(strArr);
    }

    public static /* bridge */ /* synthetic */ zzmg zza() {
        return zza;
    }

    private static zzmg zzb(String[] strArr) {
        zzmg zzmgVar;
        try {
            zzmgVar = zzmh.zza();
        } catch (NoClassDefFoundError unused) {
            zzmgVar = null;
        }
        if (zzmgVar != null) {
            return zzmgVar;
        }
        StringBuilder sb = new StringBuilder();
        for (String str : strArr) {
            try {
                return (zzmg) Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable th) {
                th = th;
                if (th instanceof InvocationTargetException) {
                    th = th.getCause();
                }
                sb.append('\n');
                sb.append(str);
                sb.append(": ");
                sb.append(th);
            }
        }
        throw new IllegalStateException(sb.insert(0, "No logging platforms found:").toString());
    }
}
