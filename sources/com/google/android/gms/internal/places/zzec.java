package com.google.android.gms.internal.places;

/* loaded from: classes.dex */
abstract class zzec {
    abstract int zzc(int i, byte[] bArr, int i2, int i3);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract int zzc(CharSequence charSequence, byte[] bArr, int i, int i2);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract String zzh(byte[] bArr, int i, int i2) throws zzbk;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzf(byte[] bArr, int i, int i2) {
        return zzc(0, bArr, i, i2) == 0;
    }
}
