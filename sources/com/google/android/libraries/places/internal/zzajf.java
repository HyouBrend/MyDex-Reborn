package com.google.android.libraries.places.internal;

import sun.misc.Unsafe;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzajf extends zzajh {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzajf(Unsafe unsafe) {
        super(unsafe);
    }

    @Override // com.google.android.libraries.places.internal.zzajh
    public final double zza(Object obj, long j) {
        return Double.longBitsToDouble(this.zza.getLong(obj, j));
    }

    @Override // com.google.android.libraries.places.internal.zzajh
    public final float zzb(Object obj, long j) {
        return Float.intBitsToFloat(this.zza.getInt(obj, j));
    }

    @Override // com.google.android.libraries.places.internal.zzajh
    public final void zzc(Object obj, long j, boolean z) {
        if (zzaji.zzb) {
            zzaji.zzD(obj, j, r3 ? (byte) 1 : (byte) 0);
        } else {
            zzaji.zzE(obj, j, r3 ? (byte) 1 : (byte) 0);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajh
    public final void zzd(Object obj, long j, byte b) {
        if (zzaji.zzb) {
            zzaji.zzD(obj, j, b);
        } else {
            zzaji.zzE(obj, j, b);
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajh
    public final void zze(Object obj, long j, double d) {
        this.zza.putLong(obj, j, Double.doubleToLongBits(d));
    }

    @Override // com.google.android.libraries.places.internal.zzajh
    public final void zzf(Object obj, long j, float f) {
        this.zza.putInt(obj, j, Float.floatToIntBits(f));
    }

    @Override // com.google.android.libraries.places.internal.zzajh
    public final boolean zzg(Object obj, long j) {
        if (zzaji.zzb) {
            return zzaji.zzt(obj, j);
        }
        return zzaji.zzu(obj, j);
    }
}
