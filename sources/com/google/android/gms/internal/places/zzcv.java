package com.google.android.gms.internal.places;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcv {
    private static final zzcv zzll = new zzcv();
    private final ConcurrentMap<Class<?>, zzda<?>> zzln = new ConcurrentHashMap();
    private final zzcz zzlm = new zzbx();

    public static zzcv zzcq() {
        return zzll;
    }

    public final <T> zzda<T> zzf(Class<T> cls) {
        zzbd.zzb(cls, "messageType");
        zzda<T> zzdaVar = (zzda) this.zzln.get(cls);
        if (zzdaVar != null) {
            return zzdaVar;
        }
        zzda<T> zze = this.zzlm.zze(cls);
        zzbd.zzb(cls, "messageType");
        zzbd.zzb(zze, "schema");
        zzda<T> zzdaVar2 = (zzda) this.zzln.putIfAbsent(cls, zze);
        return zzdaVar2 != null ? zzdaVar2 : zze;
    }

    public final <T> zzda<T> zzq(T t) {
        return zzf(t.getClass());
    }

    private zzcv() {
    }
}
