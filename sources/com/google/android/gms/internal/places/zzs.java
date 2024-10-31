package com.google.android.gms.internal.places;

import java.io.IOException;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlinx.coroutines.scheduling.WorkQueueKt;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzs {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i, zzr zzrVar) {
        int i2 = i + 1;
        byte b = bArr[i];
        if (b >= 0) {
            zzrVar.zzdz = b;
            return i2;
        }
        return zzb(b, bArr, i2, zzrVar);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, byte[] bArr, int i2, zzr zzrVar) {
        int i3 = i & WorkQueueKt.MASK;
        int i4 = i2 + 1;
        byte b = bArr[i2];
        if (b >= 0) {
            zzrVar.zzdz = i3 | (b << 7);
            return i4;
        }
        int i5 = i3 | ((b & ByteCompanionObject.MAX_VALUE) << 7);
        int i6 = i4 + 1;
        byte b2 = bArr[i4];
        if (b2 >= 0) {
            zzrVar.zzdz = i5 | (b2 << 14);
            return i6;
        }
        int i7 = i5 | ((b2 & ByteCompanionObject.MAX_VALUE) << 14);
        int i8 = i6 + 1;
        byte b3 = bArr[i6];
        if (b3 >= 0) {
            zzrVar.zzdz = i7 | (b3 << 21);
            return i8;
        }
        int i9 = i7 | ((b3 & ByteCompanionObject.MAX_VALUE) << 21);
        int i10 = i8 + 1;
        byte b4 = bArr[i8];
        if (b4 >= 0) {
            zzrVar.zzdz = i9 | (b4 << 28);
            return i10;
        }
        int i11 = i9 | ((b4 & ByteCompanionObject.MAX_VALUE) << 28);
        while (true) {
            int i12 = i10 + 1;
            if (bArr[i10] >= 0) {
                zzrVar.zzdz = i11;
                return i12;
            }
            i10 = i12;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzc(byte[] bArr, int i, zzr zzrVar) {
        int i2 = i + 1;
        long j = bArr[i];
        if (j >= 0) {
            zzrVar.zzea = j;
            return i2;
        }
        int i3 = i2 + 1;
        byte b = bArr[i2];
        long j2 = (j & 127) | ((b & ByteCompanionObject.MAX_VALUE) << 7);
        int i4 = 7;
        while (b < 0) {
            int i5 = i3 + 1;
            i4 += 7;
            j2 |= (r10 & ByteCompanionObject.MAX_VALUE) << i4;
            b = bArr[i3];
            i3 = i5;
        }
        zzrVar.zzea = j2;
        return i3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i) {
        return ((bArr[i + 3] & UByte.MAX_VALUE) << 24) | (bArr[i] & UByte.MAX_VALUE) | ((bArr[i + 1] & UByte.MAX_VALUE) << 8) | ((bArr[i + 2] & UByte.MAX_VALUE) << 16);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzc(byte[] bArr, int i) {
        return ((bArr[i + 7] & 255) << 56) | (bArr[i] & 255) | ((bArr[i + 1] & 255) << 8) | ((bArr[i + 2] & 255) << 16) | ((bArr[i + 3] & 255) << 24) | ((bArr[i + 4] & 255) << 32) | ((bArr[i + 5] & 255) << 40) | ((bArr[i + 6] & 255) << 48);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zzd(byte[] bArr, int i) {
        return Double.longBitsToDouble(zzc(bArr, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zze(byte[] bArr, int i) {
        return Float.intBitsToFloat(zzb(bArr, i));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzd(byte[] bArr, int i, zzr zzrVar) throws zzbk {
        int zzb = zzb(bArr, i, zzrVar);
        int i2 = zzrVar.zzdz;
        if (i2 < 0) {
            throw zzbk.zzbq();
        }
        if (i2 == 0) {
            zzrVar.zzeb = "";
            return zzb;
        }
        zzrVar.zzeb = new String(bArr, zzb, i2, zzbd.UTF_8);
        return zzb + i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zze(byte[] bArr, int i, zzr zzrVar) throws zzbk {
        int zzb = zzb(bArr, i, zzrVar);
        int i2 = zzrVar.zzdz;
        if (i2 < 0) {
            throw zzbk.zzbq();
        }
        if (i2 == 0) {
            zzrVar.zzeb = "";
            return zzb;
        }
        zzrVar.zzeb = zzea.zzh(bArr, zzb, i2);
        return zzb + i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzf(byte[] bArr, int i, zzr zzrVar) throws zzbk {
        int zzb = zzb(bArr, i, zzrVar);
        int i2 = zzrVar.zzdz;
        if (i2 < 0) {
            throw zzbk.zzbq();
        }
        if (i2 > bArr.length - zzb) {
            throw zzbk.zzbp();
        }
        if (i2 == 0) {
            zzrVar.zzeb = zzw.zzeg;
            return zzb;
        }
        zzrVar.zzeb = zzw.zzc(bArr, zzb, i2);
        return zzb + i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(zzda zzdaVar, byte[] bArr, int i, int i2, zzr zzrVar) throws IOException {
        int i3 = i + 1;
        int i4 = bArr[i];
        if (i4 < 0) {
            i3 = zzb(i4, bArr, i3, zzrVar);
            i4 = zzrVar.zzdz;
        }
        int i5 = i3;
        if (i4 < 0 || i4 > i2 - i5) {
            throw zzbk.zzbp();
        }
        Object newInstance = zzdaVar.newInstance();
        int i6 = i4 + i5;
        zzdaVar.zzb(newInstance, bArr, i5, i6, zzrVar);
        zzdaVar.zzd(newInstance);
        zzrVar.zzeb = newInstance;
        return i6;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(zzda zzdaVar, byte[] bArr, int i, int i2, int i3, zzr zzrVar) throws IOException {
        zzco zzcoVar = (zzco) zzdaVar;
        Object newInstance = zzcoVar.newInstance();
        int zzb = zzcoVar.zzb((zzco) newInstance, bArr, i, i2, i3, zzrVar);
        zzcoVar.zzd(newInstance);
        zzrVar.zzeb = newInstance;
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, byte[] bArr, int i2, int i3, zzbh<?> zzbhVar, zzr zzrVar) {
        zzbe zzbeVar = (zzbe) zzbhVar;
        int zzb = zzb(bArr, i2, zzrVar);
        zzbeVar.zzac(zzrVar.zzdz);
        while (zzb < i3) {
            int zzb2 = zzb(bArr, zzb, zzrVar);
            if (i != zzrVar.zzdz) {
                break;
            }
            zzb = zzb(bArr, zzb2, zzrVar);
            zzbeVar.zzac(zzrVar.zzdz);
        }
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(byte[] bArr, int i, zzbh<?> zzbhVar, zzr zzrVar) throws IOException {
        zzbe zzbeVar = (zzbe) zzbhVar;
        int zzb = zzb(bArr, i, zzrVar);
        int i2 = zzrVar.zzdz + zzb;
        while (zzb < i2) {
            zzb = zzb(bArr, zzb, zzrVar);
            zzbeVar.zzac(zzrVar.zzdz);
        }
        if (zzb == i2) {
            return zzb;
        }
        throw zzbk.zzbp();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(zzda<?> zzdaVar, int i, byte[] bArr, int i2, int i3, zzbh<?> zzbhVar, zzr zzrVar) throws IOException {
        int zzb = zzb(zzdaVar, bArr, i2, i3, zzrVar);
        zzbhVar.add(zzrVar.zzeb);
        while (zzb < i3) {
            int zzb2 = zzb(bArr, zzb, zzrVar);
            if (i != zzrVar.zzdz) {
                break;
            }
            zzb = zzb(zzdaVar, bArr, zzb2, i3, zzrVar);
            zzbhVar.add(zzrVar.zzeb);
        }
        return zzb;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, byte[] bArr, int i2, int i3, zzdr zzdrVar, zzr zzrVar) throws zzbk {
        if ((i >>> 3) == 0) {
            throw zzbk.zzbr();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            int zzc = zzc(bArr, i2, zzrVar);
            zzdrVar.zzc(i, Long.valueOf(zzrVar.zzea));
            return zzc;
        }
        if (i4 == 1) {
            zzdrVar.zzc(i, Long.valueOf(zzc(bArr, i2)));
            return i2 + 8;
        }
        if (i4 == 2) {
            int zzb = zzb(bArr, i2, zzrVar);
            int i5 = zzrVar.zzdz;
            if (i5 < 0) {
                throw zzbk.zzbq();
            }
            if (i5 > bArr.length - zzb) {
                throw zzbk.zzbp();
            }
            if (i5 == 0) {
                zzdrVar.zzc(i, zzw.zzeg);
            } else {
                zzdrVar.zzc(i, zzw.zzc(bArr, zzb, i5));
            }
            return zzb + i5;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                zzdrVar.zzc(i, Integer.valueOf(zzb(bArr, i2)));
                return i2 + 4;
            }
            throw zzbk.zzbr();
        }
        zzdr zzdi = zzdr.zzdi();
        int i6 = (i & (-8)) | 4;
        int i7 = 0;
        while (true) {
            if (i2 >= i3) {
                break;
            }
            int zzb2 = zzb(bArr, i2, zzrVar);
            int i8 = zzrVar.zzdz;
            i7 = i8;
            if (i8 == i6) {
                i2 = zzb2;
                break;
            }
            int zzb3 = zzb(i7, bArr, zzb2, i3, zzdi, zzrVar);
            i7 = i8;
            i2 = zzb3;
        }
        if (i2 > i3 || i7 != i6) {
            throw zzbk.zzbt();
        }
        zzdrVar.zzc(i, zzdi);
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzb(int i, byte[] bArr, int i2, int i3, zzr zzrVar) throws zzbk {
        if ((i >>> 3) == 0) {
            throw zzbk.zzbr();
        }
        int i4 = i & 7;
        if (i4 == 0) {
            return zzc(bArr, i2, zzrVar);
        }
        if (i4 == 1) {
            return i2 + 8;
        }
        if (i4 == 2) {
            return zzb(bArr, i2, zzrVar) + zzrVar.zzdz;
        }
        if (i4 != 3) {
            if (i4 == 5) {
                return i2 + 4;
            }
            throw zzbk.zzbr();
        }
        int i5 = (i & (-8)) | 4;
        int i6 = 0;
        while (i2 < i3) {
            i2 = zzb(bArr, i2, zzrVar);
            i6 = zzrVar.zzdz;
            if (i6 == i5) {
                break;
            }
            i2 = zzb(i6, bArr, i2, i3, zzrVar);
        }
        if (i2 > i3 || i6 != i5) {
            throw zzbk.zzbt();
        }
        return i2;
    }
}
