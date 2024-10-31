package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zziz implements zziy {
    private final zzhi zza;
    private final zzhm zzb;

    public zziz(zzhm zzhmVar, zzhi zzhiVar) {
        this.zzb = zzhmVar;
        this.zza = zzhiVar;
    }

    @Override // com.google.android.libraries.places.internal.zziy
    public final void zza(zzix zzixVar) {
        zzaan zza = zzaaq.zza();
        zza.zzg(zzixVar.zzz());
        zza.zzd(zzixVar.zzx());
        zza.zze(zzixVar.zzy());
        zza.zzj(zzixVar.zzd());
        zza.zzc(zzixVar.zzb());
        zza.zzb(zzixVar.zza());
        zza.zzk(zzixVar.zze());
        zza.zzh(zzixVar.zzk().length());
        zza.zzl(zzixVar.zzg());
        zza.zzf(zzixVar.zzc());
        zza.zzi(zzixVar.zzA());
        zza.zza(zzixVar.zzf());
        if (zzixVar.zzi() == zzhy.FRAGMENT) {
            zza.zzn(2);
        } else if (zzixVar.zzi() == zzhy.INTENT) {
            zza.zzn(3);
        } else {
            zza.zzn(1);
        }
        if (zzixVar.zzj() == AutocompleteActivityMode.FULLSCREEN) {
            zza.zzm(2);
        } else if (zzixVar.zzj() == AutocompleteActivityMode.OVERLAY) {
            zza.zzm(1);
        }
        zzaaq zzaaqVar = (zzaaq) zza.zzq();
        zzaaw zzb = zzhn.zzb(this.zza);
        zzb.zzl(10);
        zzb.zzc(zzaaqVar);
        this.zzb.zza(zzhn.zza((zzabb) zzb.zzq()));
    }
}
