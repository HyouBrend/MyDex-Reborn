package com.google.android.libraries.places.internal;

import java.io.IOException;
import net.lingala.zip4j.crypto.PBKDF2.BinTools;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzns {
    private static final zzns zza = new zznq("base64()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/", '=');
    private static final zzns zzb = new zznq("base64Url()", "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_", '=');
    private static final zzns zzc = new zznr("base32()", "ABCDEFGHIJKLMNOPQRSTUVWXYZ234567", '=');
    private static final zzns zzd = new zznr("base32Hex()", "0123456789ABCDEFGHIJKLMNOPQRSTUV", '=');
    private static final zzns zze = new zznp("base16()", BinTools.hex);

    public static zzns zzd() {
        return zze;
    }

    abstract void zza(Appendable appendable, byte[] bArr, int i, int i2) throws IOException;

    abstract int zzb(int i);

    public final String zze(byte[] bArr, int i, int i2) {
        zzjp.zzi(0, i2, bArr.length);
        StringBuilder sb = new StringBuilder(zzb(i2));
        try {
            zza(sb, bArr, 0, i2);
            return sb.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
