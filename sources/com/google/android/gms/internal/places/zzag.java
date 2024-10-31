package com.google.android.gms.internal.places;

import java.io.IOException;
import java.nio.charset.Charset;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public class zzag extends zzad {
    protected final byte[] zzen;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzag(byte[] bArr) {
        bArr.getClass();
        this.zzen = bArr;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public int zzag() {
        return 0;
    }

    @Override // com.google.android.gms.internal.places.zzw
    public byte zzi(int i) {
        return this.zzen[i];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.places.zzw
    public byte zzj(int i) {
        return this.zzen[i];
    }

    @Override // com.google.android.gms.internal.places.zzw
    public int size() {
        return this.zzen.length;
    }

    @Override // com.google.android.gms.internal.places.zzw
    public final zzw zzb(int i, int i2) {
        int zzc = zzc(0, i2, size());
        if (zzc == 0) {
            return zzw.zzeg;
        }
        return new zzz(this.zzen, zzag(), zzc);
    }

    @Override // com.google.android.gms.internal.places.zzw
    protected void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzen, 0, bArr, 0, i3);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.places.zzw
    public final void zzb(zzt zztVar) throws IOException {
        zztVar.zzb(this.zzen, zzag(), size());
    }

    @Override // com.google.android.gms.internal.places.zzw
    protected final String zzb(Charset charset) {
        return new String(this.zzen, zzag(), size(), charset);
    }

    @Override // com.google.android.gms.internal.places.zzw
    public final boolean zzae() {
        int zzag = zzag();
        return zzea.zzf(this.zzen, zzag, size() + zzag);
    }

    @Override // com.google.android.gms.internal.places.zzw
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzw) || size() != ((zzw) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (obj instanceof zzag) {
            zzag zzagVar = (zzag) obj;
            int zzaf = zzaf();
            int zzaf2 = zzagVar.zzaf();
            if (zzaf == 0 || zzaf2 == 0 || zzaf == zzaf2) {
                return zzb(zzagVar, 0, size());
            }
            return false;
        }
        return obj.equals(this);
    }

    @Override // com.google.android.gms.internal.places.zzad
    final boolean zzb(zzw zzwVar, int i, int i2) {
        if (i2 > zzwVar.size()) {
            int size = size();
            StringBuilder sb = new StringBuilder(40);
            sb.append("Length too large: ");
            sb.append(i2);
            sb.append(size);
            throw new IllegalArgumentException(sb.toString());
        }
        if (i2 > zzwVar.size()) {
            int size2 = zzwVar.size();
            StringBuilder sb2 = new StringBuilder(59);
            sb2.append("Ran off end of other: 0, ");
            sb2.append(i2);
            sb2.append(", ");
            sb2.append(size2);
            throw new IllegalArgumentException(sb2.toString());
        }
        if (zzwVar instanceof zzag) {
            zzag zzagVar = (zzag) zzwVar;
            byte[] bArr = this.zzen;
            byte[] bArr2 = zzagVar.zzen;
            int zzag = zzag() + i2;
            int zzag2 = zzag();
            int zzag3 = zzagVar.zzag();
            while (zzag2 < zzag) {
                if (bArr[zzag2] != bArr2[zzag3]) {
                    return false;
                }
                zzag2++;
                zzag3++;
            }
            return true;
        }
        return zzwVar.zzb(0, i2).equals(zzb(0, i2));
    }

    @Override // com.google.android.gms.internal.places.zzw
    protected final int zzb(int i, int i2, int i3) {
        return zzbd.zzb(i, this.zzen, zzag(), i3);
    }
}
