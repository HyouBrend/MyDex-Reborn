package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcx implements zzci {
    private final int flags;
    private final String info;
    private final Object[] zzkt;
    private final zzck zzkw;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzcx(zzck zzckVar, String str, Object[] objArr) {
        this.zzkw = zzckVar;
        this.info = str;
        this.zzkt = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.flags = i | (charAt2 << i2);
                return;
            } else {
                i |= (charAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzcr() {
        return this.info;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Object[] zzcs() {
        return this.zzkt;
    }

    @Override // com.google.android.gms.internal.places.zzci
    public final zzck zzcl() {
        return this.zzkw;
    }

    @Override // com.google.android.gms.internal.places.zzci
    public final int zzcj() {
        return (this.flags & 1) == 1 ? zzbc.zze.zzit : zzbc.zze.zziu;
    }

    @Override // com.google.android.gms.internal.places.zzci
    public final boolean zzck() {
        return (this.flags & 2) == 2;
    }
}
