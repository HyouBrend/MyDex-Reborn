package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzahi extends zzahk {
    private zzahi() {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzahi(zzahh zzahhVar) {
        super(null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzahk
    public final void zza(Object obj, long j) {
        ((zzagw) zzaji.zzf(obj, j)).zzb();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzahk
    public final void zzb(Object obj, Object obj2, long j) {
        zzagw zzagwVar = (zzagw) zzaji.zzf(obj, j);
        zzagw zzagwVar2 = (zzagw) zzaji.zzf(obj2, j);
        int size = zzagwVar.size();
        int size2 = zzagwVar2.size();
        if (size > 0 && size2 > 0) {
            if (!zzagwVar.zzc()) {
                zzagwVar = zzagwVar.zzf(size2 + size);
            }
            zzagwVar.addAll(zzagwVar2);
        }
        if (size > 0) {
            zzagwVar2 = zzagwVar;
        }
        zzaji.zzs(obj, j, zzagwVar2);
    }
}
