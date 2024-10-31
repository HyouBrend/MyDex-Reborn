package com.google.android.libraries.places.internal;

import android.content.Context;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzgu implements zzgy {
    private Context zza;
    private zzhb zzb;
    private zzhi zzc;

    private zzgu() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzgu(zzgt zzgtVar) {
    }

    @Override // com.google.android.libraries.places.internal.zzgy
    public final /* synthetic */ zzgy zza(zzhb zzhbVar) {
        this.zzb = zzhbVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzgy
    public final /* synthetic */ zzgy zzb(zzhi zzhiVar) {
        this.zzc = zzhiVar;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzgy
    public final /* synthetic */ zzgy zzc(Context context) {
        context.getClass();
        this.zza = context;
        return this;
    }

    @Override // com.google.android.libraries.places.internal.zzgy
    public final zzgz zzd() {
        zzajt.zzb(this.zza, Context.class);
        zzajt.zzb(this.zzb, zzhb.class);
        zzajt.zzb(this.zzc, zzhi.class);
        return new zzgw(this.zza, this.zzb, this.zzc, null);
    }
}
