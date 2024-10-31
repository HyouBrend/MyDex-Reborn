package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzaff;
import com.google.android.libraries.places.internal.zzafg;
import java.io.IOException;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzafg<MessageType extends zzafg<MessageType, BuilderType>, BuilderType extends zzaff<MessageType, BuilderType>> implements zzahw {
    protected int zza = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzr(zzaih zzaihVar) {
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzahw
    public final zzaft zzs() {
        try {
            int zzv = zzv();
            zzaft zzaftVar = zzaft.zzb;
            byte[] bArr = new byte[zzv];
            zzagb zzz = zzagb.zzz(bArr, 0, zzv);
            zzK(zzz);
            zzz.zzA();
            return new zzafq(bArr);
        } catch (IOException e) {
            throw new RuntimeException("Serializing " + getClass().getName() + " to a ByteString threw an IOException (should never happen).", e);
        }
    }
}
