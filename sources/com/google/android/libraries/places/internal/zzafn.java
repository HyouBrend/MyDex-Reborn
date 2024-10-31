package com.google.android.libraries.places.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzafn extends zzafq {
    private final int zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzafn(byte[] bArr, int i, int i2) {
        super(bArr);
        zzj(0, i2, bArr.length);
        this.zzc = i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzafq, com.google.android.libraries.places.internal.zzaft
    public final byte zzb(int i) {
        return this.zza[i];
    }

    @Override // com.google.android.libraries.places.internal.zzafq
    protected final int zzc() {
        return 0;
    }

    @Override // com.google.android.libraries.places.internal.zzafq, com.google.android.libraries.places.internal.zzaft
    public final int zzd() {
        return this.zzc;
    }

    @Override // com.google.android.libraries.places.internal.zzafq, com.google.android.libraries.places.internal.zzaft
    public final byte zza(int i) {
        int i2 = this.zzc;
        if (((i2 - (i + 1)) | i) >= 0) {
            return this.zza[i];
        }
        if (i < 0) {
            throw new ArrayIndexOutOfBoundsException("Index < 0: " + i);
        }
        throw new ArrayIndexOutOfBoundsException("Index > length: " + i + ", " + i2);
    }
}
