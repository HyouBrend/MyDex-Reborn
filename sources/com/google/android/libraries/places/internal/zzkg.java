package com.google.android.libraries.places.internal;

import java.util.List;
import javax.annotation.CheckForNull;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzkg extends zzkh {
    final transient int zza;
    final transient int zzb;
    final /* synthetic */ zzkh zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkg(zzkh zzkhVar, int i, int i2) {
        this.zzc = zzkhVar;
        this.zza = i;
        this.zzb = i2;
    }

    @Override // java.util.List
    public final Object get(int i) {
        zzjp.zza(i, this.zzb, "index");
        return this.zzc.get(i + this.zza);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzb;
    }

    @Override // com.google.android.libraries.places.internal.zzkh, java.util.List
    public final /* bridge */ /* synthetic */ List subList(int i, int i2) {
        return subList(i, i2);
    }

    @Override // com.google.android.libraries.places.internal.zzke
    final int zzb() {
        return this.zzc.zzc() + this.zza + this.zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzke
    public final int zzc() {
        return this.zzc.zzc() + this.zza;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzke
    public final boolean zzf() {
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzke
    @CheckForNull
    public final Object[] zzg() {
        return this.zzc.zzg();
    }

    @Override // com.google.android.libraries.places.internal.zzkh
    /* renamed from: zzh */
    public final zzkh subList(int i, int i2) {
        zzjp.zzi(i, i2, this.zzb);
        zzkh zzkhVar = this.zzc;
        int i3 = this.zza;
        return zzkhVar.subList(i + i3, i2 + i3);
    }
}
