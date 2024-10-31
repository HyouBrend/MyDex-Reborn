package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzagb extends zzafj {
    public static final /* synthetic */ int zzb = 0;
    private static final Logger zzc = Logger.getLogger(zzagb.class.getName());
    private static final boolean zzd = zzaji.zzx();
    zzagc zza;

    private zzagb() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzagb(zzaga zzagaVar) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Deprecated
    public static int zzt(int i, zzahw zzahwVar, zzaih zzaihVar) {
        int zzr = ((zzafg) zzahwVar).zzr(zzaihVar);
        int zzx = zzx(i << 3);
        return zzx + zzx + zzr;
    }

    public static int zzu(int i) {
        if (i >= 0) {
            return zzx(i);
        }
        return 10;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzv(zzahw zzahwVar, zzaih zzaihVar) {
        int zzr = ((zzafg) zzahwVar).zzr(zzaihVar);
        return zzx(zzr) + zzr;
    }

    public static int zzw(String str) {
        int length;
        try {
            length = zzajm.zzc(str);
        } catch (zzajl unused) {
            length = str.getBytes(zzagx.zzb).length;
        }
        return zzx(length) + length;
    }

    public static int zzx(int i) {
        if ((i & (-128)) == 0) {
            return 1;
        }
        if ((i & (-16384)) == 0) {
            return 2;
        }
        if (((-2097152) & i) == 0) {
            return 3;
        }
        return (i & (-268435456)) == 0 ? 4 : 5;
    }

    public static int zzy(long j) {
        int i;
        if (((-128) & j) == 0) {
            return 1;
        }
        if (j < 0) {
            return 10;
        }
        if (((-34359738368L) & j) != 0) {
            i = 6;
            j >>>= 28;
        } else {
            i = 2;
        }
        if (((-2097152) & j) != 0) {
            j >>>= 14;
            i += 2;
        }
        return (j & (-16384)) != 0 ? i + 1 : i;
    }

    public static zzagb zzz(byte[] bArr, int i, int i2) {
        return new zzafy(bArr, 0, i2);
    }

    public final void zzA() {
        if (zza() != 0) {
            throw new IllegalStateException("Did not write as much data as expected.");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzB(String str, zzajl zzajlVar) throws IOException {
        zzc.logp(Level.WARNING, "com.google.protobuf.CodedOutputStream", "inefficientWriteStringNoTag", "Converting ill-formed UTF-16. Your Protocol Buffer will not round trip correctly!", (Throwable) zzajlVar);
        byte[] bytes = str.getBytes(zzagx.zzb);
        try {
            int length = bytes.length;
            zzq(length);
            zzl(bytes, 0, length);
        } catch (IndexOutOfBoundsException e) {
            throw new zzafz(e);
        }
    }

    public abstract int zza();

    public abstract void zzb(byte b) throws IOException;

    public abstract void zzd(int i, boolean z) throws IOException;

    public abstract void zze(int i, zzaft zzaftVar) throws IOException;

    public abstract void zzf(int i, int i2) throws IOException;

    public abstract void zzg(int i) throws IOException;

    public abstract void zzh(int i, long j) throws IOException;

    public abstract void zzi(long j) throws IOException;

    public abstract void zzj(int i, int i2) throws IOException;

    public abstract void zzk(int i) throws IOException;

    public abstract void zzl(byte[] bArr, int i, int i2) throws IOException;

    public abstract void zzm(int i, String str) throws IOException;

    public abstract void zzo(int i, int i2) throws IOException;

    public abstract void zzp(int i, int i2) throws IOException;

    public abstract void zzq(int i) throws IOException;

    public abstract void zzr(int i, long j) throws IOException;

    public abstract void zzs(long j) throws IOException;
}
