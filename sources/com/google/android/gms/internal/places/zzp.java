package com.google.android.gms.internal.places;

/* loaded from: classes.dex */
final class zzp {
    private static final Class<?> zzdw = zzh("libcore.io.Memory");
    private static final boolean zzdx;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzy() {
        return (zzdw == null || zzdx) ? false : true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Class<?> zzz() {
        return zzdw;
    }

    private static <T> Class<T> zzh(String str) {
        try {
            return (Class<T>) Class.forName(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    static {
        zzdx = zzh("org.robolectric.Robolectric") != null;
    }
}
