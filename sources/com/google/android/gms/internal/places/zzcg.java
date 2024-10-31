package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes.dex */
final class zzcg implements zzcd {
    @Override // com.google.android.gms.internal.places.zzcd
    public final Map<?, ?> zzg(Object obj) {
        return (zzce) obj;
    }

    @Override // com.google.android.gms.internal.places.zzcd
    public final zzcb<?, ?> zzl(Object obj) {
        throw new NoSuchMethodError();
    }

    @Override // com.google.android.gms.internal.places.zzcd
    public final Map<?, ?> zzh(Object obj) {
        return (zzce) obj;
    }

    @Override // com.google.android.gms.internal.places.zzcd
    public final boolean zzi(Object obj) {
        return !((zzce) obj).isMutable();
    }

    @Override // com.google.android.gms.internal.places.zzcd
    public final Object zzj(Object obj) {
        ((zzce) obj).zzab();
        return obj;
    }

    @Override // com.google.android.gms.internal.places.zzcd
    public final Object zzk(Object obj) {
        return zzce.zzcd().zzce();
    }

    @Override // com.google.android.gms.internal.places.zzcd
    public final Object zzc(Object obj, Object obj2) {
        zzce zzceVar = (zzce) obj;
        zzce zzceVar2 = (zzce) obj2;
        if (!zzceVar2.isEmpty()) {
            if (!zzceVar.isMutable()) {
                zzceVar = zzceVar.zzce();
            }
            zzceVar.zzb(zzceVar2);
        }
        return zzceVar;
    }

    @Override // com.google.android.gms.internal.places.zzcd
    public final int zzc(int i, Object obj, Object obj2) {
        zzce zzceVar = (zzce) obj;
        if (zzceVar.isEmpty()) {
            return 0;
        }
        Iterator it = zzceVar.entrySet().iterator();
        if (!it.hasNext()) {
            return 0;
        }
        Map.Entry entry = (Map.Entry) it.next();
        entry.getKey();
        entry.getValue();
        throw new NoSuchMethodError();
    }
}
