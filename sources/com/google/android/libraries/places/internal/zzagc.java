package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.List;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzagc implements zzajp {
    private final zzagb zza;

    private zzagc(zzagb zzagbVar) {
        byte[] bArr = zzagx.zzd;
        this.zza = zzagbVar;
        zzagbVar.zza = this;
    }

    public static zzagc zza(zzagb zzagbVar) {
        zzagc zzagcVar = zzagbVar.zza;
        return zzagcVar != null ? zzagcVar : new zzagc(zzagbVar);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzB(int i, long j) throws IOException {
        this.zza.zzr(i, (j >> 63) ^ (j + j));
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzD(int i, String str) throws IOException {
        this.zza.zzm(i, str);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzF(int i, int i2) throws IOException {
        this.zza.zzp(i, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzH(int i, long j) throws IOException {
        this.zza.zzr(i, j);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzb(int i, boolean z) throws IOException {
        this.zza.zzd(i, z);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzd(int i, zzaft zzaftVar) throws IOException {
        this.zza.zze(i, zzaftVar);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zze(int i, List list) throws IOException {
        for (int i2 = 0; i2 < list.size(); i2++) {
            this.zza.zze(i, (zzaft) list.get(i2));
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzf(int i, double d) throws IOException {
        this.zza.zzh(i, Double.doubleToRawLongBits(d));
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzh(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzj(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzl(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzn(int i, float f) throws IOException {
        this.zza.zzf(i, Float.floatToRawIntBits(f));
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzp(int i, Object obj, zzaih zzaihVar) throws IOException {
        zzagb zzagbVar = this.zza;
        zzagbVar.zzo(i, 3);
        zzaihVar.zzf((zzahw) obj, zzagbVar.zza);
        zzagbVar.zzo(i, 4);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzq(int i, int i2) throws IOException {
        this.zza.zzj(i, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzs(int i, long j) throws IOException {
        this.zza.zzr(i, j);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzu(int i, Object obj, zzaih zzaihVar) throws IOException {
        zzahw zzahwVar = (zzahw) obj;
        zzafy zzafyVar = (zzafy) this.zza;
        zzafyVar.zzq((i << 3) | 2);
        zzafyVar.zzq(((zzafg) zzahwVar).zzr(zzaihVar));
        zzaihVar.zzf(zzahwVar, zzafyVar.zza);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzv(int i, int i2) throws IOException {
        this.zza.zzf(i, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzx(int i, long j) throws IOException {
        this.zza.zzh(i, j);
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzz(int i, int i2) throws IOException {
        this.zza.zzp(i, (i2 >> 31) ^ (i2 + i2));
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzE(int i, List list) throws IOException {
        int i2 = 0;
        if (!(list instanceof zzahe)) {
            while (i2 < list.size()) {
                this.zza.zzm(i, (String) list.get(i2));
                i2++;
            }
            return;
        }
        zzahe zzaheVar = (zzahe) list;
        while (i2 < list.size()) {
            Object zze = zzaheVar.zze(i2);
            if (zze instanceof String) {
                this.zza.zzm(i, (String) zze);
            } else {
                this.zza.zze(i, (zzaft) zze);
            }
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzG(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzp(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzagb.zzx(((Integer) list.get(i4)).intValue());
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzq(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzI(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzr(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzagb.zzy(((Long) list.get(i4)).longValue());
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzs(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzc(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzd(i, ((Boolean) list.get(i2)).booleanValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Boolean) list.get(i4)).booleanValue();
            i3++;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzb(((Boolean) list.get(i2)).booleanValue() ? (byte) 1 : (byte) 0);
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzk(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).intValue();
            i3 += 4;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzg(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzm(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).longValue();
            i3 += 8;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzi(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzr(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzagb.zzu(((Integer) list.get(i4)).intValue());
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzk(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzA(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                zzagb zzagbVar = this.zza;
                int intValue = ((Integer) list.get(i2)).intValue();
                zzagbVar.zzp(i, (intValue >> 31) ^ (intValue + intValue));
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            int intValue2 = ((Integer) list.get(i4)).intValue();
            i3 += zzagb.zzx((intValue2 >> 31) ^ (intValue2 + intValue2));
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            zzagb zzagbVar2 = this.zza;
            int intValue3 = ((Integer) list.get(i2)).intValue();
            zzagbVar2.zzq((intValue3 >> 31) ^ (intValue3 + intValue3));
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzC(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                zzagb zzagbVar = this.zza;
                long longValue = ((Long) list.get(i2)).longValue();
                zzagbVar.zzr(i, (longValue >> 63) ^ (longValue + longValue));
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            long longValue2 = ((Long) list.get(i4)).longValue();
            i3 += zzagb.zzy((longValue2 >> 63) ^ (longValue2 + longValue2));
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            zzagb zzagbVar2 = this.zza;
            long longValue3 = ((Long) list.get(i2)).longValue();
            zzagbVar2.zzs((longValue3 >> 63) ^ (longValue3 + longValue3));
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzg(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzh(i, Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Double) list.get(i4)).doubleValue();
            i3 += 8;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzi(Double.doubleToRawLongBits(((Double) list.get(i2)).doubleValue()));
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzi(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzj(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzagb.zzu(((Integer) list.get(i4)).intValue());
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzk(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzo(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzf(i, Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Float) list.get(i4)).floatValue();
            i3 += 4;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzg(Float.floatToRawIntBits(((Float) list.get(i2)).floatValue()));
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzt(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzr(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            i3 += zzagb.zzy(((Long) list.get(i4)).longValue());
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzs(((Long) list.get(i2)).longValue());
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzw(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzf(i, ((Integer) list.get(i2)).intValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Integer) list.get(i4)).intValue();
            i3 += 4;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzg(((Integer) list.get(i2)).intValue());
            i2++;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzajp
    public final void zzy(int i, List list, boolean z) throws IOException {
        int i2 = 0;
        if (!z) {
            while (i2 < list.size()) {
                this.zza.zzh(i, ((Long) list.get(i2)).longValue());
                i2++;
            }
            return;
        }
        this.zza.zzo(i, 2);
        int i3 = 0;
        for (int i4 = 0; i4 < list.size(); i4++) {
            ((Long) list.get(i4)).longValue();
            i3 += 8;
        }
        this.zza.zzq(i3);
        while (i2 < list.size()) {
            this.zza.zzi(((Long) list.get(i2)).longValue());
            i2++;
        }
    }
}
