package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzjq extends zzjs {
    final /* synthetic */ zzjr zza;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzjq(zzjr zzjrVar, zzjt zzjtVar, CharSequence charSequence) {
        super(zzjtVar, "3.2.0");
        this.zza = zzjrVar;
    }

    @Override // com.google.android.libraries.places.internal.zzjs
    final int zzc(int i) {
        return i + 1;
    }

    @Override // com.google.android.libraries.places.internal.zzjs
    final int zzd(int i) {
        int length = "3.2.0".length();
        zzjp.zzb(i, length, "index");
        while (i < length) {
            if ("3.2.0".charAt(i) == '.') {
                return i;
            }
            i++;
        }
        return -1;
    }
}
