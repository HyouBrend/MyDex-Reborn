package com.google.android.libraries.places.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import org.checkerframework.checker.nullness.compatqual.NullableDecl;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzni {
    private static final String[] zza;
    private static final zznm zzb;

    static {
        zznm zznnVar;
        String[] strArr = {"com.google.common.flogger.util.StackWalkerStackGetter", "com.google.common.flogger.util.JavaLangAccessStackGetter"};
        zza = strArr;
        int i = 0;
        while (true) {
            if (i >= 2) {
                zznnVar = new zznn();
                break;
            }
            try {
                zznnVar = (zznm) Class.forName(strArr[i]).asSubclass(zznm.class).getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            } catch (Throwable unused) {
                zznnVar = null;
            }
            if (zznnVar != null) {
                break;
            } else {
                i++;
            }
        }
        zzb = zznnVar;
    }

    @NullableDecl
    public static StackTraceElement zza(Class cls, int i) {
        zznj.zza(cls, TypedValues.AttributesType.S_TARGET);
        return zzb.zza(cls, 2);
    }
}
