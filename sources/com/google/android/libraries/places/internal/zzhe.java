package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzhe extends zzhh {
    private String zza;
    private int zzb;
    private byte zzc;
    private int zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzhh zza(String str) {
        if (str == null) {
            throw new NullPointerException("Null packageName");
        }
        this.zza = str;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhh
    final zzhh zzb(int i) {
        this.zzb = i;
        this.zzc = (byte) 1;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzhh
    final zzhi zzc() {
        String str;
        int i;
        if (this.zzc != 1 || (str = this.zza) == null || (i = this.zzd) == 0) {
            StringBuilder sb = new StringBuilder();
            if (this.zza == null) {
                sb.append(" packageName");
            }
            if (this.zzc == 0) {
                sb.append(" versionCode");
            }
            if (this.zzd == 0) {
                sb.append(" requestSource");
            }
            throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
        }
        return new zzhg(str, this.zzb, i, null);
    }

    @Override // com.google.android.libraries.places.internal.zzhh
    public final zzhh zzd(int i) {
        this.zzd = i;
        return this;
    }
}
