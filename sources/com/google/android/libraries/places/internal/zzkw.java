package com.google.android.libraries.places.internal;

import java.util.AbstractMap;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzkw extends zzkh {
    final /* synthetic */ zzkx zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkw(zzkx zzkxVar) {
        this.zza = zzkxVar;
    }

    @Override // java.util.List
    public final /* bridge */ /* synthetic */ Object get(int i) {
        int i2;
        Object[] objArr;
        Object[] objArr2;
        i2 = this.zza.zzc;
        zzjp.zza(i, i2, "index");
        zzkx zzkxVar = this.zza;
        objArr = zzkxVar.zzb;
        int i3 = i + i;
        Object obj = objArr[i3];
        obj.getClass();
        objArr2 = zzkxVar.zzb;
        Object obj2 = objArr2[i3 + 1];
        obj2.getClass();
        return new AbstractMap.SimpleImmutableEntry(obj, obj2);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        int i;
        i = this.zza.zzc;
        return i;
    }

    @Override // com.google.android.libraries.places.internal.zzke
    public final boolean zzf() {
        return true;
    }
}
