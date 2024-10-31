package com.google.android.datatransport.cct.a;

import android.util.SparseArray;

/* JADX WARN: $VALUES field not found */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public final class zzu {
    public static final zzu zza;
    public static final zzu zzb;
    public static final zzu zzc;
    public static final zzu zzd;
    public static final zzu zze;
    public static final zzu zzf;
    private static final SparseArray<zzu> zzg;

    static {
        zzu zzuVar = new zzu("DEFAULT", 0, 0);
        zza = zzuVar;
        zzu zzuVar2 = new zzu("UNMETERED_ONLY", 1, 1);
        zzb = zzuVar2;
        zzu zzuVar3 = new zzu("UNMETERED_OR_DAILY", 2, 2);
        zzc = zzuVar3;
        zzu zzuVar4 = new zzu("FAST_IF_RADIO_AWAKE", 3, 3);
        zzd = zzuVar4;
        zzu zzuVar5 = new zzu("NEVER", 4, 4);
        zze = zzuVar5;
        zzu zzuVar6 = new zzu("UNRECOGNIZED", 5, -1);
        zzf = zzuVar6;
        SparseArray<zzu> sparseArray = new SparseArray<>();
        zzg = sparseArray;
        sparseArray.put(0, zzuVar);
        sparseArray.put(1, zzuVar2);
        sparseArray.put(2, zzuVar3);
        sparseArray.put(3, zzuVar4);
        sparseArray.put(4, zzuVar5);
        sparseArray.put(-1, zzuVar6);
    }

    private zzu(String str, int i, int i2) {
    }
}
