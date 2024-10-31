package com.google.android.libraries.places.internal;

import java.io.Serializable;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzkt extends zzku implements Serializable {
    public static final /* synthetic */ int zzc = 0;
    private static final zzkt zzd;
    final zzkc zza;
    final zzkc zzb;

    static {
        zzka zzkaVar;
        zzjy zzjyVar;
        zzkaVar = zzka.zzb;
        zzjyVar = zzjy.zzb;
        zzd = new zzkt(zzkaVar, zzjyVar);
    }

    private zzkt(zzkc zzkcVar, zzkc zzkcVar2) {
        zzjy zzjyVar;
        zzka zzkaVar;
        this.zza = zzkcVar;
        this.zzb = zzkcVar2;
        if (zzkcVar.compareTo(zzkcVar2) <= 0) {
            zzjyVar = zzjy.zzb;
            if (zzkcVar != zzjyVar) {
                zzkaVar = zzka.zzb;
                if (zzkcVar2 != zzkaVar) {
                    return;
                }
            }
        }
        throw new IllegalArgumentException("Invalid range: ".concat(zze(zzkcVar, zzkcVar2)));
    }

    public static zzkt zza(Comparable comparable) {
        zzjy zzjyVar;
        zzkb zzkbVar = new zzkb(comparable);
        zzjyVar = zzjy.zzb;
        return new zzkt(zzkbVar, zzjyVar);
    }

    public static zzkt zzb(Comparable comparable, Comparable comparable2) {
        return new zzkt(new zzkb(comparable), new zzjz(comparable2));
    }

    public static zzkt zzc(Comparable comparable, Comparable comparable2) {
        return new zzkt(new zzkb(comparable), new zzkb(comparable2));
    }

    private static String zze(zzkc zzkcVar, zzkc zzkcVar2) {
        StringBuilder sb = new StringBuilder(16);
        zzkcVar.zzc(sb);
        sb.append("..");
        zzkcVar2.zzd(sb);
        return sb.toString();
    }

    public final boolean equals(@CheckForNull Object obj) {
        if (obj instanceof zzkt) {
            zzkt zzktVar = (zzkt) obj;
            if (this.zza.equals(zzktVar.zza) && this.zzb.equals(zzktVar.zzb)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        return (this.zza.hashCode() * 31) + this.zzb.hashCode();
    }

    public final String toString() {
        return zze(this.zza, this.zzb);
    }

    public final boolean zzd(Comparable comparable) {
        comparable.getClass();
        return this.zza.zze(comparable) && !this.zzb.zze(comparable);
    }
}
