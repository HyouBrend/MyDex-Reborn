package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.nio.charset.Charset;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
class zzafq extends zzafp {
    protected final byte[] zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzafq(byte[] bArr) {
        bArr.getClass();
        this.zza = bArr;
    }

    @Override // com.google.android.libraries.places.internal.zzaft
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzaft) || zzd() != ((zzaft) obj).zzd()) {
            return false;
        }
        if (zzd() == 0) {
            return true;
        }
        if (obj instanceof zzafq) {
            zzafq zzafqVar = (zzafq) obj;
            int zzk = zzk();
            int zzk2 = zzafqVar.zzk();
            if (zzk != 0 && zzk2 != 0 && zzk != zzk2) {
                return false;
            }
            int zzd = zzd();
            if (zzd > zzafqVar.zzd()) {
                throw new IllegalArgumentException("Length too large: " + zzd + zzd());
            }
            if (zzd <= zzafqVar.zzd()) {
                if (zzafqVar instanceof zzafq) {
                    byte[] bArr = this.zza;
                    byte[] bArr2 = zzafqVar.zza;
                    zzafqVar.zzc();
                    int i = 0;
                    int i2 = 0;
                    while (i < zzd) {
                        if (bArr[i] != bArr2[i2]) {
                            return false;
                        }
                        i++;
                        i2++;
                    }
                    return true;
                }
                return zzafqVar.zzf(0, zzd).equals(zzf(0, zzd));
            }
            throw new IllegalArgumentException("Ran off end of other: 0, " + zzd + ", " + zzafqVar.zzd());
        }
        return obj.equals(this);
    }

    @Override // com.google.android.libraries.places.internal.zzaft
    public byte zza(int i) {
        return this.zza[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzaft
    public byte zzb(int i) {
        return this.zza[i];
    }

    protected int zzc() {
        return 0;
    }

    @Override // com.google.android.libraries.places.internal.zzaft
    public int zzd() {
        return this.zza.length;
    }

    @Override // com.google.android.libraries.places.internal.zzaft
    protected final int zze(int i, int i2, int i3) {
        return zzagx.zzb(i, this.zza, 0, i3);
    }

    @Override // com.google.android.libraries.places.internal.zzaft
    public final zzaft zzf(int i, int i2) {
        zzj(0, i2, zzd());
        return i2 == 0 ? zzaft.zzb : new zzafn(this.zza, 0, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzaft
    protected final String zzg(Charset charset) {
        return new String(this.zza, 0, zzd(), charset);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzaft
    public final void zzh(zzafj zzafjVar) throws IOException {
        ((zzafy) zzafjVar).zzc(this.zza, 0, zzd());
    }

    @Override // com.google.android.libraries.places.internal.zzaft
    public final boolean zzi() {
        return zzajm.zze(this.zza, 0, zzd());
    }
}
