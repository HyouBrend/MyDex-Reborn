package com.google.android.libraries.places.api.model;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzk extends zzbn {
    private int zza;
    private int zzb;
    private byte zzc;

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzbn zza(int i) {
        this.zza = i;
        this.zzc = (byte) (this.zzc | 1);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbn
    public final zzbn zzb(int i) {
        this.zzb = i;
        this.zzc = (byte) (this.zzc | 2);
        return this;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.api.model.zzbn
    public final LocalTime zzc() {
        if (this.zzc == 3) {
            return new zzaq(this.zza, this.zzb);
        }
        StringBuilder sb = new StringBuilder();
        if ((this.zzc & 1) == 0) {
            sb.append(" hours");
        }
        if ((this.zzc & 2) == 0) {
            sb.append(" minutes");
        }
        throw new IllegalStateException("Missing required properties:".concat(sb.toString()));
    }
}
