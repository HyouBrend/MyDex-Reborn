package com.google.android.libraries.places.internal;

import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzjp {
    public static int zza(int i, int i2, String str) {
        String zza;
        if (i >= 0 && i < i2) {
            return i;
        }
        if (i < 0) {
            zza = zzju.zza("%s (%s) must not be negative", "index", Integer.valueOf(i));
        } else {
            if (i2 < 0) {
                throw new IllegalArgumentException("negative size: " + i2);
            }
            zza = zzju.zza("%s (%s) must be less than size (%s)", "index", Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IndexOutOfBoundsException(zza);
    }

    public static int zzb(int i, int i2, String str) {
        if (i < 0 || i > i2) {
            throw new IndexOutOfBoundsException(zzn(i, i2, "index"));
        }
        return i;
    }

    public static Object zzc(@CheckForNull Object obj, @CheckForNull Object obj2) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException((String) obj2);
    }

    public static void zzd(boolean z) {
        if (!z) {
            throw new IllegalArgumentException();
        }
    }

    public static void zze(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalArgumentException(String.valueOf(obj));
        }
    }

    public static void zzf(boolean z, String str, char c) {
        if (!z) {
            throw new IllegalArgumentException(zzju.zza(str, Character.valueOf(c)));
        }
    }

    public static void zzg(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalArgumentException(zzju.zza(str, Integer.valueOf(i)));
        }
    }

    public static void zzh(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (!z) {
            throw new IllegalArgumentException(zzju.zza(str, obj, obj2, obj3));
        }
    }

    public static void zzi(int i, int i2, int i3) {
        String zzn;
        if (i < 0 || i2 < i || i2 > i3) {
            if (i < 0 || i > i3) {
                zzn = zzn(i, i3, "start index");
            } else {
                zzn = (i2 < 0 || i2 > i3) ? zzn(i2, i3, "end index") : zzju.zza("end index (%s) must not be less than start index (%s)", Integer.valueOf(i2), Integer.valueOf(i));
            }
            throw new IndexOutOfBoundsException(zzn);
        }
    }

    public static void zzj(boolean z) {
        if (!z) {
            throw new IllegalStateException();
        }
    }

    public static void zzk(boolean z, @CheckForNull Object obj) {
        if (!z) {
            throw new IllegalStateException((String) obj);
        }
    }

    public static void zzl(boolean z, String str, int i) {
        if (!z) {
            throw new IllegalStateException(zzju.zza(str, Integer.valueOf(i)));
        }
    }

    public static void zzm(boolean z, String str, @CheckForNull Object obj, @CheckForNull Object obj2, @CheckForNull Object obj3) {
        if (!z) {
            throw new IllegalStateException(zzju.zza(str, obj, obj2, obj3));
        }
    }

    private static String zzn(int i, int i2, String str) {
        if (i < 0) {
            return zzju.zza("%s (%s) must not be negative", str, Integer.valueOf(i));
        }
        if (i2 >= 0) {
            return zzju.zza("%s (%s) must not be greater than size (%s)", str, Integer.valueOf(i), Integer.valueOf(i2));
        }
        throw new IllegalArgumentException("negative size: " + i2);
    }
}
