package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzi extends zzbm {
    private int zza;
    private int zzb;
    private int zzc;
    private byte zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbm
    public final zzbm zza(int i) {
        this.zzc = i;
        this.zzd = (byte) (this.zzd | 4);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbm
    public final zzbm zzb(int i) {
        this.zzb = i;
        this.zzd = (byte) (this.zzd | 2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzbm zzc(int i) {
        this.zza = i;
        this.zzd = (byte) (this.zzd | 1);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbm
    public final LocalDate zzd() {
        if (this.zzd == 7) {
            return new zzao(this.zza, this.zzb, this.zzc);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.zzd & 1) == 0) {
            sb.append(" year");
        }
        if ((this.zzd & 2) == 0) {
            sb.append(" month");
        }
        if ((this.zzd & 4) == 0) {
            sb.append(" day");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
