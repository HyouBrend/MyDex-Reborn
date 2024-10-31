package com.google.android.libraries.places.internal;

import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.net.ftp.FTP;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzagx {
    static final Charset zza = Charset.forName("US-ASCII");
    static final Charset zzb = Charset.forName(Key.STRING_CHARSET_NAME);
    static final Charset zzc = Charset.forName(FTP.DEFAULT_CONTROL_ENCODING);
    public static final byte[] zzd;
    public static final ByteBuffer zze;
    public static final zzafx zzf;

    static {
        byte[] bArr = new byte[0];
        zzd = bArr;
        zze = ByteBuffer.wrap(bArr);
        int i = zzafx.zza;
        zzafv zzafvVar = new zzafv(bArr, 0, 0, false, null);
        try {
            zzafvVar.zza(0);
            zzf = zzafvVar;
        } catch (zzagz e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static int zza(boolean z) {
        return z ? 1231 : 1237;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = 0; i4 < i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzc(Object obj, String str) {
        if (obj != null) {
            return obj;
        }
        throw new NullPointerException(str);
    }

    public static String zzd(byte[] bArr) {
        return new String(bArr, zzb);
    }
}
