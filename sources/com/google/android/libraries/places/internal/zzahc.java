package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public class zzahc {
    private static final zzagd zzb = zzagd.zza;
    protected volatile zzahw zza;
    private volatile zzaft zzc;

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzahc)) {
            return false;
        }
        zzahc zzahcVar = (zzahc) obj;
        zzahw zzahwVar = this.zza;
        zzahw zzahwVar2 = zzahcVar.zza;
        if (zzahwVar == null && zzahwVar2 == null) {
            return zzb().equals(zzahcVar.zzb());
        }
        if (zzahwVar != null && zzahwVar2 != null) {
            return zzahwVar.equals(zzahwVar2);
        }
        if (zzahwVar != null) {
            zzahcVar.zzc(zzahwVar.zzt());
            return zzahwVar.equals(zzahcVar.zza);
        }
        zzc(zzahwVar2.zzt());
        return this.zza.equals(zzahwVar2);
    }

    public int hashCode() {
        return 1;
    }

    public final int zza() {
        if (this.zzc != null) {
            return ((zzafq) this.zzc).zza.length;
        }
        if (this.zza != null) {
            return this.zza.zzv();
        }
        return 0;
    }

    public final zzaft zzb() {
        if (this.zzc != null) {
            return this.zzc;
        }
        synchronized (this) {
            if (this.zzc != null) {
                return this.zzc;
            }
            if (this.zza == null) {
                this.zzc = zzaft.zzb;
            } else {
                this.zzc = this.zza.zzs();
            }
            return this.zzc;
        }
    }

    protected final void zzc(zzahw zzahwVar) {
        if (this.zza != null) {
            return;
        }
        synchronized (this) {
            if (this.zza == null) {
                try {
                    this.zza = zzahwVar;
                    this.zzc = zzaft.zzb;
                } catch (zzagz unused) {
                    this.zza = zzahwVar;
                    this.zzc = zzaft.zzb;
                }
            }
        }
    }
}
