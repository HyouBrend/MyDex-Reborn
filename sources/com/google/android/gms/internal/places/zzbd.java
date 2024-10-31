package com.google.android.gms.internal.places;

import com.bumptech.glide.load.Key;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import org.apache.commons.net.ftp.FTP;

/* loaded from: classes.dex */
public final class zzbd {
    public static final byte[] zziz;
    private static final ByteBuffer zzja;
    private static final zzai zzjb;
    static final Charset UTF_8 = Charset.forName(Key.STRING_CHARSET_NAME);
    private static final Charset ISO_8859_1 = Charset.forName(FTP.DEFAULT_CONTROL_ENCODING);

    public static int zze(boolean z) {
        return z ? 1231 : 1237;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzg(zzck zzckVar) {
        return false;
    }

    public static int zzl(long j) {
        return (int) (j ^ (j >>> 32));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T checkNotNull(T t) {
        t.getClass();
        return t;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zzb(T t, String str) {
        if (t != null) {
            return t;
        }
        throw new NullPointerException(str);
    }

    public static boolean zze(byte[] bArr) {
        return zzea.zze(bArr);
    }

    public static String zzf(byte[] bArr) {
        return new String(bArr, UTF_8);
    }

    public static int hashCode(byte[] bArr) {
        int length = bArr.length;
        int zzb = zzb(length, bArr, 0, length);
        if (zzb == 0) {
            return 1;
        }
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, byte[] bArr, int i2, int i3) {
        for (int i4 = i2; i4 < i2 + i3; i4++) {
            i = (i * 31) + bArr[i4];
        }
        return i;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzb(Object obj, Object obj2) {
        return ((zzck) obj).zzbk().zzb((zzck) obj2).zzbe();
    }

    static {
        byte[] bArr = new byte[0];
        zziz = bArr;
        zzja = ByteBuffer.wrap(bArr);
        zzjb = zzai.zzb(bArr, 0, bArr.length, false);
    }
}
