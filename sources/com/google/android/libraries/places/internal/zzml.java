package com.google.android.libraries.places.internal;

import android.os.Build;
import dalvik.system.VMStack;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzml extends zzmg {
    private static final boolean zza = zza.zza();
    private static final boolean zzb;
    private static final zzmf zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    public final class zza {
        zza() {
        }

        static boolean zza() {
            return zzml.zzt();
        }
    }

    static {
        boolean z = true;
        if (Build.FINGERPRINT != null && !"robolectric".equals(Build.FINGERPRINT)) {
            z = false;
        }
        zzb = z;
        zzc = new zzmf() { // from class: com.google.android.libraries.places.internal.zzml.1
            @Override // com.google.android.libraries.places.internal.zzmf
            public zzll zza(Class<?> cls, int i) {
                return zzll.zza;
            }

            @Override // com.google.android.libraries.places.internal.zzmf
            public String zzb(Class cls) {
                StackTraceElement zza2;
                if (zzml.zza) {
                    try {
                        if (cls.equals(zzml.zzp())) {
                            return VMStack.getStackClass2().getName();
                        }
                    } catch (Throwable unused) {
                    }
                }
                if (!zzml.zzb || (zza2 = zzni.zza(cls, 1)) == null) {
                    return null;
                }
                return zza2.getClassName();
            }
        };
    }

    static Class<?> zzp() {
        return VMStack.getStackClass2();
    }

    static String zzq() {
        try {
            return VMStack.getStackClass2().getName();
        } catch (Throwable unused) {
            return null;
        }
    }

    static boolean zzt() {
        try {
            Class.forName("dalvik.system.VMStack").getMethod("getStackClass2", new Class[0]);
            return zza.class.getName().equals(zzq());
        } catch (Throwable unused) {
            return false;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzmg
    protected zzlp zze(String str) {
        return zzmo.zzb(str);
    }

    @Override // com.google.android.libraries.places.internal.zzmg
    protected zzmf zzh() {
        return zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzmg
    protected zzmu zzj() {
        return zzmp.zzb();
    }

    @Override // com.google.android.libraries.places.internal.zzmg
    protected String zzm() {
        return "platform: Android";
    }
}
