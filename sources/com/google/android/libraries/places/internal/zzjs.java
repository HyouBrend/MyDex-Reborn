package com.google.android.libraries.places.internal;

import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzjs extends zzja {
    final CharSequence zzb;
    final zzjf zzc;
    int zzd = 0;
    int zze;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzjs(zzjt zzjtVar, CharSequence charSequence) {
        zzjf zzjfVar;
        zzjfVar = zzjtVar.zza;
        this.zzc = zzjfVar;
        this.zze = Integer.MAX_VALUE;
        this.zzb = "3.2.0";
    }

    @Override // com.google.android.libraries.places.internal.zzja
    @CheckForNull
    protected final /* bridge */ /* synthetic */ Object zza() {
        int zzc;
        int i = this.zzd;
        while (true) {
            int i2 = this.zzd;
            if (i2 == -1) {
                zzb();
                return null;
            }
            int zzd = zzd(i2);
            if (zzd == -1) {
                zzd = this.zzb.length();
                this.zzd = -1;
                zzc = -1;
            } else {
                zzc = zzc(zzd);
                this.zzd = zzc;
            }
            if (zzc == i) {
                int i3 = zzc + 1;
                this.zzd = i3;
                if (i3 > this.zzb.length()) {
                    this.zzd = -1;
                }
            } else {
                if (i < zzd) {
                    this.zzb.charAt(i);
                }
                if (i < zzd) {
                    this.zzb.charAt(zzd - 1);
                }
                int i4 = this.zze;
                if (i4 == 1) {
                    zzd = this.zzb.length();
                    this.zzd = -1;
                    if (zzd > i) {
                        this.zzb.charAt(zzd - 1);
                    }
                } else {
                    this.zze = i4 - 1;
                }
                return this.zzb.subSequence(i, zzd).toString();
            }
        }
    }

    abstract int zzc(int i);

    abstract int zzd(int i);
}
