package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzm;
import com.google.android.gms.internal.places.zzo;
import java.io.IOException;

/* loaded from: classes.dex */
public abstract class zzm<MessageType extends zzm<MessageType, BuilderType>, BuilderType extends zzo<MessageType, BuilderType>> implements zzck {
    private static boolean zzdu = false;
    protected int zzdt = 0;

    @Override // com.google.android.gms.internal.places.zzck
    public final zzw zzv() {
        try {
            zzae zzk = zzw.zzk(zzbh());
            zzc(zzk.zzai());
            return zzk.zzah();
        } catch (IOException e) {
            String name = getClass().getName();
            StringBuilder sb = new StringBuilder(String.valueOf(name).length() + 62 + "ByteString".length());
            sb.append("Serializing ");
            sb.append(name);
            sb.append(" to a ");
            sb.append("ByteString");
            sb.append(" threw an IOException (should never happen).");
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int zzw() {
        throw new UnsupportedOperationException();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void zze(int i) {
        throw new UnsupportedOperationException();
    }
}
