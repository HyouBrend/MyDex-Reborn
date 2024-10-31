package com.google.android.libraries.places.internal;

import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzaia implements zzaih {
    private final zzahw zza;
    private final zzaiy zzb;
    private final boolean zzc;
    private final zzage zzd;

    private zzaia(zzaiy zzaiyVar, zzage zzageVar, zzahw zzahwVar) {
        this.zzb = zzaiyVar;
        this.zzc = zzageVar.zzc(zzahwVar);
        this.zzd = zzageVar;
        this.zza = zzahwVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzaia zzi(zzaiy zzaiyVar, zzage zzageVar, zzahw zzahwVar) {
        return new zzaia(zzaiyVar, zzageVar, zzahwVar);
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final int zza(Object obj) {
        zzaiy zzaiyVar = this.zzb;
        int zzb = zzaiyVar.zzb(zzaiyVar.zzc(obj));
        if (!this.zzc) {
            return zzb;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final int zzb(Object obj) {
        int hashCode = this.zzb.zzc(obj).hashCode();
        if (!this.zzc) {
            return hashCode;
        }
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final Object zzc() {
        zzahw zzahwVar = this.zza;
        if (zzahwVar instanceof zzago) {
            return ((zzago) zzahwVar).zzy();
        }
        return zzahwVar.zzD().zzs();
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final void zzd(Object obj) {
        this.zzb.zze(obj);
        this.zzd.zzb(obj);
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final void zze(Object obj, Object obj2) {
        zzaij.zzA(this.zzb, obj, obj2);
        if (this.zzc) {
            this.zzd.zza(obj2);
            throw null;
        }
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final void zzf(Object obj, zzajp zzajpVar) throws IOException {
        this.zzd.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final boolean zzg(Object obj, Object obj2) {
        if (!this.zzb.zzc(obj).equals(this.zzb.zzc(obj2))) {
            return false;
        }
        if (!this.zzc) {
            return true;
        }
        this.zzd.zza(obj);
        this.zzd.zza(obj2);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final boolean zzh(Object obj) {
        this.zzd.zza(obj);
        throw null;
    }
}
