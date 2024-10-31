package com.google.android.gms.internal.places;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzak extends zzai {
    private final byte[] buffer;
    private int limit;
    private int pos;
    private final boolean zzet;
    private int zzeu;
    private int zzev;
    private int zzew;

    private zzak(byte[] bArr, int i, int i2, boolean z) {
        super();
        this.zzew = Integer.MAX_VALUE;
        this.buffer = bArr;
        this.limit = i2 + i;
        this.pos = i;
        this.zzev = i;
        this.zzet = z;
    }

    @Override // com.google.android.gms.internal.places.zzai
    public final int zzl(int i) throws zzbk {
        if (i < 0) {
            throw zzbk.zzbq();
        }
        int zzaj = i + zzaj();
        int i2 = this.zzew;
        if (zzaj > i2) {
            throw zzbk.zzbp();
        }
        this.zzew = zzaj;
        int i3 = this.limit + this.zzeu;
        this.limit = i3;
        int i4 = i3 - this.zzev;
        if (i4 > zzaj) {
            int i5 = i4 - zzaj;
            this.zzeu = i5;
            this.limit = i3 - i5;
        } else {
            this.zzeu = 0;
        }
        return i2;
    }

    @Override // com.google.android.gms.internal.places.zzai
    public final int zzaj() {
        return this.pos - this.zzev;
    }
}
