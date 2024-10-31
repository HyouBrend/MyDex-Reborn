package com.google.android.libraries.places.internal;

import java.util.Set;
import javax.annotation.CheckForNull;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzkl extends zzke implements Set {

    @CheckForNull
    private transient zzkh zza;

    @Override // java.util.Collection, java.util.Set
    public final boolean equals(@CheckForNull Object obj) {
        if (obj == this || obj == this) {
            return true;
        }
        if (obj instanceof Set) {
            Set set = (Set) obj;
            try {
                if (size() == set.size()) {
                    if (containsAll(set)) {
                        return true;
                    }
                }
            } catch (ClassCastException | NullPointerException unused) {
            }
        }
        return false;
    }

    @Override // java.util.Collection, java.util.Set
    public final int hashCode() {
        return zzlb.zza(this);
    }

    @Override // com.google.android.libraries.places.internal.zzke
    public zzkh zzd() {
        zzkh zzkhVar = this.zza;
        if (zzkhVar != null) {
            return zzkhVar;
        }
        zzkh zzh = zzh();
        this.zza = zzh;
        return zzh;
    }

    @Override // com.google.android.libraries.places.internal.zzke, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
    /* renamed from: zze */
    public abstract zzle iterator();

    zzkh zzh() {
        Object[] array = toArray();
        int i = zzkh.zzd;
        return zzkh.zzi(array, array.length);
    }
}
