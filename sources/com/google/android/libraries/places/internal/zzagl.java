package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzagl;
import com.google.android.libraries.places.internal.zzago;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public class zzagl<MessageType extends zzago<MessageType, BuilderType>, BuilderType extends zzagl<MessageType, BuilderType>> extends zzaff<MessageType, BuilderType> {
    protected zzago zza;
    private final zzago zzb;

    /* JADX INFO: Access modifiers changed from: protected */
    public zzagl(MessageType messagetype) {
        this.zzb = messagetype;
        if (!messagetype.zzL()) {
            this.zza = messagetype.zzy();
            return;
        }
        throw new IllegalArgumentException("Default instance must be immutable.");
    }

    @Override // com.google.android.libraries.places.internal.zzaff
    /* renamed from: zzp, reason: merged with bridge method [inline-methods] */
    public final zzagl clone() {
        zzagl zzaglVar = (zzagl) this.zzb.zzb(5, null, null);
        zzaglVar.zza = zzs();
        return zzaglVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:8:0x0030, code lost:
    
        if (r3 != false) goto L12;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final MessageType zzq() {
        /*
            r5 = this;
            com.google.android.libraries.places.internal.zzago r0 = r5.zzs()
            java.lang.Boolean r1 = java.lang.Boolean.TRUE
            r1 = 1
            r2 = 0
            java.lang.Object r3 = r0.zzb(r1, r2, r2)
            java.lang.Byte r3 = (java.lang.Byte) r3
            byte r3 = r3.byteValue()
            if (r3 != r1) goto L15
            goto L32
        L15:
            if (r3 == 0) goto L33
            com.google.android.libraries.places.internal.zzaie r3 = com.google.android.libraries.places.internal.zzaie.zza()
            java.lang.Class r4 = r0.getClass()
            com.google.android.libraries.places.internal.zzaih r3 = r3.zzb(r4)
            boolean r3 = r3.zzh(r0)
            if (r1 == r3) goto L2b
            r1 = r2
            goto L2c
        L2b:
            r1 = r0
        L2c:
            r4 = 2
            r0.zzb(r4, r1, r2)
            if (r3 == 0) goto L33
        L32:
            return r0
        L33:
            com.google.android.libraries.places.internal.zzaix r1 = new com.google.android.libraries.places.internal.zzaix
            r1.<init>(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzagl.zzq():com.google.android.libraries.places.internal.zzago");
    }

    @Override // com.google.android.libraries.places.internal.zzahv
    /* renamed from: zzr, reason: merged with bridge method [inline-methods] */
    public MessageType zzs() {
        if (!this.zza.zzL()) {
            return (MessageType) this.zza;
        }
        this.zza.zzG();
        return (MessageType) this.zza;
    }

    @Override // com.google.android.libraries.places.internal.zzahx
    public final /* bridge */ /* synthetic */ zzahw zzt() {
        throw null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzu() {
        if (this.zza.zzL()) {
            return;
        }
        zzv();
    }

    protected void zzv() {
        zzago zzy = this.zzb.zzy();
        zzaie.zza().zzb(zzy.getClass()).zze(zzy, this.zza);
        this.zza = zzy;
    }
}
