package com.google.android.gms.internal.places;

/* loaded from: classes.dex */
public abstract class zzai {
    private int zzeo;
    private int zzep;
    private boolean zzeq;

    public static long zzb(long j) {
        return (-(j & 1)) ^ (j >>> 1);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzai zzb(byte[] bArr, int i, int i2, boolean z) {
        zzak zzakVar = new zzak(bArr, 0, i2, false);
        try {
            zzakVar.zzl(i2);
            return zzakVar;
        } catch (zzbk e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zzm(int i) {
        return (-(i & 1)) ^ (i >>> 1);
    }

    public abstract int zzaj();

    public abstract int zzl(int i) throws zzbk;

    private zzai() {
        this.zzeo = 100;
        this.zzep = Integer.MAX_VALUE;
        this.zzeq = false;
    }
}
