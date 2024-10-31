package com.google.android.libraries.places.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzkf extends zzjv {
    private final zzkh zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzkf(zzkh zzkhVar, int i) {
        super(zzkhVar.size(), i);
        this.zza = zzkhVar;
    }

    @Override // com.google.android.libraries.places.internal.zzjv
    protected final Object zza(int i) {
        return this.zza.get(i);
    }
}
