package com.google.android.libraries.places.internal;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import sun.misc.Unsafe;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzahz<T> implements zzaih<T> {
    private static final int[] zza = new int[0];
    private static final Unsafe zzb = zzaji.zzg();
    private final int[] zzc;
    private final Object[] zzd;
    private final zzahw zze;
    private final boolean zzf;
    private final int[] zzg;
    private final int zzh;
    private final zzahk zzi;
    private final zzaiy zzj;
    private final zzage zzk;
    private final int zzl;
    private final zzaib zzm;
    private final zzahr zzn;

    private zzahz(int[] iArr, Object[] objArr, int i, int i2, zzahw zzahwVar, int i3, boolean z, int[] iArr2, int i4, int i5, zzaib zzaibVar, zzahk zzahkVar, zzaiy zzaiyVar, zzage zzageVar, zzahr zzahrVar) {
        this.zzc = iArr;
        this.zzd = objArr;
        this.zzl = i3;
        boolean z2 = false;
        if (zzageVar != null && zzageVar.zzc(zzahwVar)) {
            z2 = true;
        }
        this.zzf = z2;
        this.zzg = iArr2;
        this.zzh = i4;
        this.zzm = zzaibVar;
        this.zzi = zzahkVar;
        this.zzj = zzaiyVar;
        this.zzk = zzageVar;
        this.zze = zzahwVar;
        this.zzn = zzahrVar;
    }

    private final boolean zzA(Object obj, int i) {
        int zzn = zzn(i);
        long j = zzn & 1048575;
        if (j != 1048575) {
            return (zzaji.zzc(obj, j) & (1 << (zzn >>> 20))) != 0;
        }
        int zzp = zzp(i);
        long j2 = zzp & 1048575;
        switch (zzo(zzp)) {
            case 0:
                return Double.doubleToRawLongBits(zzaji.zza(obj, j2)) != 0;
            case 1:
                return Float.floatToRawIntBits(zzaji.zzb(obj, j2)) != 0;
            case 2:
                return zzaji.zzd(obj, j2) != 0;
            case 3:
                return zzaji.zzd(obj, j2) != 0;
            case 4:
                return zzaji.zzc(obj, j2) != 0;
            case 5:
                return zzaji.zzd(obj, j2) != 0;
            case 6:
                return zzaji.zzc(obj, j2) != 0;
            case 7:
                return zzaji.zzw(obj, j2);
            case 8:
                Object zzf = zzaji.zzf(obj, j2);
                if (zzf instanceof String) {
                    return !((String) zzf).isEmpty();
                }
                if (zzf instanceof zzaft) {
                    return !zzaft.zzb.equals(zzf);
                }
                throw new IllegalArgumentException();
            case 9:
                return zzaji.zzf(obj, j2) != null;
            case 10:
                return !zzaft.zzb.equals(zzaji.zzf(obj, j2));
            case 11:
                return zzaji.zzc(obj, j2) != 0;
            case 12:
                return zzaji.zzc(obj, j2) != 0;
            case 13:
                return zzaji.zzc(obj, j2) != 0;
            case 14:
                return zzaji.zzd(obj, j2) != 0;
            case 15:
                return zzaji.zzc(obj, j2) != 0;
            case 16:
                return zzaji.zzd(obj, j2) != 0;
            case 17:
                return zzaji.zzf(obj, j2) != null;
            default:
                throw new IllegalArgumentException();
        }
    }

    private final boolean zzB(Object obj, int i, int i2, int i3, int i4) {
        if (i2 == 1048575) {
            return zzA(obj, i);
        }
        return (i3 & i4) != 0;
    }

    private static boolean zzC(Object obj, int i, zzaih zzaihVar) {
        return zzaihVar.zzh(zzaji.zzf(obj, i & 1048575));
    }

    private static boolean zzD(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof zzago) {
            return ((zzago) obj).zzL();
        }
        return true;
    }

    private final boolean zzE(Object obj, int i, int i2) {
        return zzaji.zzc(obj, (long) (zzn(i2) & 1048575)) == i;
    }

    private static boolean zzF(Object obj, long j) {
        return ((Boolean) zzaji.zzf(obj, j)).booleanValue();
    }

    private static final void zzG(int i, Object obj, zzajp zzajpVar) throws IOException {
        if (obj instanceof String) {
            zzajpVar.zzD(i, (String) obj);
        } else {
            zzajpVar.zzd(i, (zzaft) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Removed duplicated region for block: B:65:0x024e  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x0269  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x026c  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0251  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static com.google.android.libraries.places.internal.zzahz zzi(java.lang.Class r30, com.google.android.libraries.places.internal.zzaht r31, com.google.android.libraries.places.internal.zzaib r32, com.google.android.libraries.places.internal.zzahk r33, com.google.android.libraries.places.internal.zzaiy r34, com.google.android.libraries.places.internal.zzage r35, com.google.android.libraries.places.internal.zzahr r36) {
        /*
            Method dump skipped, instructions count: 995
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzahz.zzi(java.lang.Class, com.google.android.libraries.places.internal.zzaht, com.google.android.libraries.places.internal.zzaib, com.google.android.libraries.places.internal.zzahk, com.google.android.libraries.places.internal.zzaiy, com.google.android.libraries.places.internal.zzage, com.google.android.libraries.places.internal.zzahr):com.google.android.libraries.places.internal.zzahz");
    }

    private static double zzj(Object obj, long j) {
        return ((Double) zzaji.zzf(obj, j)).doubleValue();
    }

    private static float zzk(Object obj, long j) {
        return ((Float) zzaji.zzf(obj, j)).floatValue();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x003d. Please report as an issue. */
    private final int zzl(Object obj) {
        int i;
        int zzx;
        int zzx2;
        int zzy;
        int zzx3;
        int zzx4;
        int zzx5;
        int zzx6;
        int zzt;
        int zzh;
        int zzx7;
        int zzx8;
        int i2;
        int zzx9;
        int zzx10;
        int zzx11;
        int zzx12;
        Unsafe unsafe = zzb;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        int i6 = 1048575;
        int i7 = 0;
        while (i4 < this.zzc.length) {
            int zzp = zzp(i4);
            int[] iArr = this.zzc;
            int i8 = iArr[i4];
            int zzo = zzo(zzp);
            if (zzo <= 17) {
                int i9 = iArr[i4 + 2];
                int i10 = i9 & i3;
                int i11 = i9 >>> 20;
                if (i10 != i6) {
                    i7 = unsafe.getInt(obj, i10);
                    i6 = i10;
                }
                i = 1 << i11;
            } else {
                i = 0;
            }
            long j = zzp & i3;
            switch (zzo) {
                case 0:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx = zzagb.zzx(i8 << 3);
                        zzx4 = zzx + 8;
                        i5 += zzx4;
                        break;
                    }
                case 1:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx2 = zzagb.zzx(i8 << 3);
                        zzx4 = zzx2 + 4;
                        i5 += zzx4;
                        break;
                    }
                case 2:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzagb.zzy(unsafe.getLong(obj, j));
                        zzx3 = zzagb.zzx(i8 << 3);
                        i5 += zzx3 + zzy;
                        break;
                    }
                case 3:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzagb.zzy(unsafe.getLong(obj, j));
                        zzx3 = zzagb.zzx(i8 << 3);
                        i5 += zzx3 + zzy;
                        break;
                    }
                case 4:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzagb.zzu(unsafe.getInt(obj, j));
                        zzx3 = zzagb.zzx(i8 << 3);
                        i5 += zzx3 + zzy;
                        break;
                    }
                case 5:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx = zzagb.zzx(i8 << 3);
                        zzx4 = zzx + 8;
                        i5 += zzx4;
                        break;
                    }
                case 6:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx2 = zzagb.zzx(i8 << 3);
                        zzx4 = zzx2 + 4;
                        i5 += zzx4;
                        break;
                    }
                case 7:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx4 = zzagb.zzx(i8 << 3) + 1;
                        i5 += zzx4;
                        break;
                    }
                case 8:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        Object object = unsafe.getObject(obj, j);
                        if (object instanceof zzaft) {
                            int i12 = zzagb.zzb;
                            int zzd = ((zzaft) object).zzd();
                            zzx5 = zzagb.zzx(zzd) + zzd;
                            zzx6 = zzagb.zzx(i8 << 3);
                            zzx4 = zzx6 + zzx5;
                            i5 += zzx4;
                            break;
                        } else {
                            zzy = zzagb.zzw((String) object);
                            zzx3 = zzagb.zzx(i8 << 3);
                            i5 += zzx3 + zzy;
                            break;
                        }
                    }
                case 9:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx4 = zzaij.zzn(i8, unsafe.getObject(obj, j), zzr(i4));
                        i5 += zzx4;
                        break;
                    }
                case 10:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzaft zzaftVar = (zzaft) unsafe.getObject(obj, j);
                        int i13 = zzagb.zzb;
                        int zzd2 = zzaftVar.zzd();
                        zzx5 = zzagb.zzx(zzd2) + zzd2;
                        zzx6 = zzagb.zzx(i8 << 3);
                        zzx4 = zzx6 + zzx5;
                        i5 += zzx4;
                        break;
                    }
                case 11:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzagb.zzx(unsafe.getInt(obj, j));
                        zzx3 = zzagb.zzx(i8 << 3);
                        i5 += zzx3 + zzy;
                        break;
                    }
                case 12:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzy = zzagb.zzu(unsafe.getInt(obj, j));
                        zzx3 = zzagb.zzx(i8 << 3);
                        i5 += zzx3 + zzy;
                        break;
                    }
                case 13:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx2 = zzagb.zzx(i8 << 3);
                        zzx4 = zzx2 + 4;
                        i5 += zzx4;
                        break;
                    }
                case 14:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx = zzagb.zzx(i8 << 3);
                        zzx4 = zzx + 8;
                        i5 += zzx4;
                        break;
                    }
                case 15:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        int i14 = unsafe.getInt(obj, j);
                        zzx3 = zzagb.zzx(i8 << 3);
                        zzy = zzagb.zzx((i14 >> 31) ^ (i14 + i14));
                        i5 += zzx3 + zzy;
                        break;
                    }
                case 16:
                    if ((i & i7) == 0) {
                        break;
                    } else {
                        long j2 = unsafe.getLong(obj, j);
                        i5 += zzagb.zzx(i8 << 3) + zzagb.zzy((j2 >> 63) ^ (j2 + j2));
                        break;
                    }
                case 17:
                    if ((i7 & i) == 0) {
                        break;
                    } else {
                        zzx4 = zzagb.zzt(i8, (zzahw) unsafe.getObject(obj, j), zzr(i4));
                        i5 += zzx4;
                        break;
                    }
                case 18:
                    zzx4 = zzaij.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzx4;
                    break;
                case 19:
                    zzx4 = zzaij.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzx4;
                    break;
                case 20:
                    zzx4 = zzaij.zzl(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzx4;
                    break;
                case 21:
                    zzx4 = zzaij.zzw(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzx4;
                    break;
                case 22:
                    zzx4 = zzaij.zzj(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzx4;
                    break;
                case 23:
                    zzx4 = zzaij.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzx4;
                    break;
                case 24:
                    zzx4 = zzaij.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzx4;
                    break;
                case 25:
                    zzx4 = zzaij.zza(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzx4;
                    break;
                case 26:
                    zzt = zzaij.zzt(i8, (List) unsafe.getObject(obj, j));
                    i5 += zzt;
                    break;
                case 27:
                    zzt = zzaij.zzo(i8, (List) unsafe.getObject(obj, j), zzr(i4));
                    i5 += zzt;
                    break;
                case 28:
                    zzt = zzaij.zzb(i8, (List) unsafe.getObject(obj, j));
                    i5 += zzt;
                    break;
                case 29:
                    zzt = zzaij.zzu(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 30:
                    zzt = zzaij.zzc(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 31:
                    zzt = zzaij.zze(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 32:
                    zzt = zzaij.zzg(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 33:
                    zzt = zzaij.zzp(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 34:
                    zzt = zzaij.zzr(i8, (List) unsafe.getObject(obj, j), false);
                    i5 += zzt;
                    break;
                case 35:
                    zzh = zzaij.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 36:
                    zzh = zzaij.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 37:
                    zzh = zzaij.zzm((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 38:
                    zzh = zzaij.zzx((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 39:
                    zzh = zzaij.zzk((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 40:
                    zzh = zzaij.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 41:
                    zzh = zzaij.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 42:
                    List list = (List) unsafe.getObject(obj, j);
                    int i15 = zzaij.zza;
                    zzh = list.size();
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 43:
                    zzh = zzaij.zzv((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 44:
                    zzh = zzaij.zzd((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 45:
                    zzh = zzaij.zzf((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 46:
                    zzh = zzaij.zzh((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 47:
                    zzh = zzaij.zzq((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 48:
                    zzh = zzaij.zzs((List) unsafe.getObject(obj, j));
                    if (zzh > 0) {
                        zzx7 = zzagb.zzx(zzh);
                        zzx8 = zzagb.zzx(i8 << 3);
                        i2 = zzx8 + zzx7;
                        i5 += i2 + zzh;
                    }
                    break;
                case 49:
                    zzt = zzaij.zzi(i8, (List) unsafe.getObject(obj, j), zzr(i4));
                    i5 += zzt;
                    break;
                case 50:
                    zzahr.zza(i8, unsafe.getObject(obj, j), zzs(i4));
                    break;
                case 51:
                    if (zzE(obj, i8, i4)) {
                        zzx9 = zzagb.zzx(i8 << 3);
                        zzt = zzx9 + 8;
                        i5 += zzt;
                    }
                    break;
                case 52:
                    if (zzE(obj, i8, i4)) {
                        zzx10 = zzagb.zzx(i8 << 3);
                        zzt = zzx10 + 4;
                        i5 += zzt;
                    }
                    break;
                case 53:
                    if (zzE(obj, i8, i4)) {
                        zzh = zzagb.zzy(zzq(obj, j));
                        i2 = zzagb.zzx(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 54:
                    if (zzE(obj, i8, i4)) {
                        zzh = zzagb.zzy(zzq(obj, j));
                        i2 = zzagb.zzx(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 55:
                    if (zzE(obj, i8, i4)) {
                        zzh = zzagb.zzu(zzm(obj, j));
                        i2 = zzagb.zzx(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 56:
                    if (zzE(obj, i8, i4)) {
                        zzx9 = zzagb.zzx(i8 << 3);
                        zzt = zzx9 + 8;
                        i5 += zzt;
                    }
                    break;
                case 57:
                    if (zzE(obj, i8, i4)) {
                        zzx10 = zzagb.zzx(i8 << 3);
                        zzt = zzx10 + 4;
                        i5 += zzt;
                    }
                    break;
                case 58:
                    if (zzE(obj, i8, i4)) {
                        zzt = zzagb.zzx(i8 << 3) + 1;
                        i5 += zzt;
                    }
                    break;
                case 59:
                    if (zzE(obj, i8, i4)) {
                        Object object2 = unsafe.getObject(obj, j);
                        if (object2 instanceof zzaft) {
                            int i16 = zzagb.zzb;
                            int zzd3 = ((zzaft) object2).zzd();
                            zzx11 = zzagb.zzx(zzd3) + zzd3;
                            zzx12 = zzagb.zzx(i8 << 3);
                            zzt = zzx12 + zzx11;
                            i5 += zzt;
                        } else {
                            zzh = zzagb.zzw((String) object2);
                            i2 = zzagb.zzx(i8 << 3);
                            i5 += i2 + zzh;
                        }
                    }
                    break;
                case 60:
                    if (zzE(obj, i8, i4)) {
                        zzt = zzaij.zzn(i8, unsafe.getObject(obj, j), zzr(i4));
                        i5 += zzt;
                    }
                    break;
                case 61:
                    if (zzE(obj, i8, i4)) {
                        zzaft zzaftVar2 = (zzaft) unsafe.getObject(obj, j);
                        int i17 = zzagb.zzb;
                        int zzd4 = zzaftVar2.zzd();
                        zzx11 = zzagb.zzx(zzd4) + zzd4;
                        zzx12 = zzagb.zzx(i8 << 3);
                        zzt = zzx12 + zzx11;
                        i5 += zzt;
                    }
                    break;
                case 62:
                    if (zzE(obj, i8, i4)) {
                        zzh = zzagb.zzx(zzm(obj, j));
                        i2 = zzagb.zzx(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 63:
                    if (zzE(obj, i8, i4)) {
                        zzh = zzagb.zzu(zzm(obj, j));
                        i2 = zzagb.zzx(i8 << 3);
                        i5 += i2 + zzh;
                    }
                    break;
                case 64:
                    if (zzE(obj, i8, i4)) {
                        zzx10 = zzagb.zzx(i8 << 3);
                        zzt = zzx10 + 4;
                        i5 += zzt;
                    }
                    break;
                case 65:
                    if (zzE(obj, i8, i4)) {
                        zzx9 = zzagb.zzx(i8 << 3);
                        zzt = zzx9 + 8;
                        i5 += zzt;
                    }
                    break;
                case 66:
                    if (zzE(obj, i8, i4)) {
                        int zzm = zzm(obj, j);
                        i2 = zzagb.zzx(i8 << 3);
                        zzh = zzagb.zzx((zzm >> 31) ^ (zzm + zzm));
                        i5 += i2 + zzh;
                    }
                    break;
                case 67:
                    if (zzE(obj, i8, i4)) {
                        long zzq = zzq(obj, j);
                        i5 += zzagb.zzx(i8 << 3) + zzagb.zzy((zzq >> 63) ^ (zzq + zzq));
                    }
                    break;
                case 68:
                    if (zzE(obj, i8, i4)) {
                        zzt = zzagb.zzt(i8, (zzahw) unsafe.getObject(obj, j), zzr(i4));
                        i5 += zzt;
                    }
                    break;
            }
            i4 += 3;
            i3 = 1048575;
        }
        zzaiy zzaiyVar = this.zzj;
        int zza2 = i5 + zzaiyVar.zza(zzaiyVar.zzc(obj));
        if (!this.zzf) {
            return zza2;
        }
        this.zzk.zza(obj);
        throw null;
    }

    private static int zzm(Object obj, long j) {
        return ((Integer) zzaji.zzf(obj, j)).intValue();
    }

    private final int zzn(int i) {
        return this.zzc[i + 2];
    }

    private static int zzo(int i) {
        return (i >>> 20) & 255;
    }

    private final int zzp(int i) {
        return this.zzc[i + 1];
    }

    private static long zzq(Object obj, long j) {
        return ((Long) zzaji.zzf(obj, j)).longValue();
    }

    private final zzaih zzr(int i) {
        int i2 = i / 3;
        int i3 = i2 + i2;
        zzaih zzaihVar = (zzaih) this.zzd[i3];
        if (zzaihVar != null) {
            return zzaihVar;
        }
        zzaih zzb2 = zzaie.zza().zzb((Class) this.zzd[i3 + 1]);
        this.zzd[i3] = zzb2;
        return zzb2;
    }

    private final Object zzs(int i) {
        int i2 = i / 3;
        return this.zzd[i2 + i2];
    }

    private static Field zzt(Class cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            throw new RuntimeException("Field " + str + " for " + cls.getName() + " not found. Known fields are " + Arrays.toString(declaredFields));
        }
    }

    private final void zzu(Object obj, Object obj2, int i) {
        if (zzA(obj2, i)) {
            int zzp = zzp(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = zzp;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzaih zzr = zzr(i);
            if (!zzA(obj, i)) {
                if (!zzD(object)) {
                    unsafe.putObject(obj, j, object);
                } else {
                    Object zzc = zzr.zzc();
                    zzr.zze(zzc, object);
                    unsafe.putObject(obj, j, zzc);
                }
                zzw(obj, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzD(object2)) {
                Object zzc2 = zzr.zzc();
                zzr.zze(zzc2, object2);
                unsafe.putObject(obj, j, zzc2);
                object2 = zzc2;
            }
            zzr.zze(object2, object);
        }
    }

    private final void zzv(Object obj, Object obj2, int i) {
        int i2 = this.zzc[i];
        if (zzE(obj2, i2, i)) {
            int zzp = zzp(i) & 1048575;
            Unsafe unsafe = zzb;
            long j = zzp;
            Object object = unsafe.getObject(obj2, j);
            if (object == null) {
                throw new IllegalStateException("Source subfield " + this.zzc[i] + " is present but null: " + obj2.toString());
            }
            zzaih zzr = zzr(i);
            if (!zzE(obj, i2, i)) {
                if (!zzD(object)) {
                    unsafe.putObject(obj, j, object);
                } else {
                    Object zzc = zzr.zzc();
                    zzr.zze(zzc, object);
                    unsafe.putObject(obj, j, zzc);
                }
                zzx(obj, i2, i);
                return;
            }
            Object object2 = unsafe.getObject(obj, j);
            if (!zzD(object2)) {
                Object zzc2 = zzr.zzc();
                zzr.zze(zzc2, object2);
                unsafe.putObject(obj, j, zzc2);
                object2 = zzc2;
            }
            zzr.zze(object2, object);
        }
    }

    private final void zzw(Object obj, int i) {
        int zzn = zzn(i);
        long j = 1048575 & zzn;
        if (j == 1048575) {
            return;
        }
        zzaji.zzq(obj, j, (1 << (zzn >>> 20)) | zzaji.zzc(obj, j));
    }

    private final void zzx(Object obj, int i, int i2) {
        zzaji.zzq(obj, zzn(i2) & 1048575, i);
    }

    private final void zzy(zzajp zzajpVar, int i, Object obj, int i2) throws IOException {
        if (obj == null) {
            return;
        }
        throw null;
    }

    private final boolean zzz(Object obj, Object obj2, int i) {
        return zzA(obj, i) == zzA(obj2, i);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x003b. Please report as an issue. */
    @Override // com.google.android.libraries.places.internal.zzaih
    public final int zza(Object obj) {
        int zzx;
        int zzx2;
        int zzy;
        int zzx3;
        int zzx4;
        int zzx5;
        int zzx6;
        int zzn;
        int zzx7;
        int zzy2;
        int zzx8;
        int zzx9;
        zzajn zzajnVar = zzajn.DOUBLE;
        if (this.zzl - 1 != 0) {
            Unsafe unsafe = zzb;
            int i = 0;
            for (int i2 = 0; i2 < this.zzc.length; i2 += 3) {
                int zzp = zzp(i2);
                int zzo = zzo(zzp);
                int i3 = this.zzc[i2];
                int i4 = zzp & 1048575;
                if (zzo >= zzagj.DOUBLE_LIST_PACKED.zza() && zzo <= zzagj.SINT64_LIST_PACKED.zza()) {
                    int i5 = this.zzc[i2 + 2];
                }
                long j = i4;
                switch (zzo) {
                    case 0:
                        if (zzA(obj, i2)) {
                            zzx = zzagb.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzA(obj, i2)) {
                            zzx2 = zzagb.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzA(obj, i2)) {
                            zzy = zzagb.zzy(zzaji.zzd(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzA(obj, i2)) {
                            zzy = zzagb.zzy(zzaji.zzd(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzA(obj, i2)) {
                            zzy = zzagb.zzu(zzaji.zzc(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzA(obj, i2)) {
                            zzx = zzagb.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzA(obj, i2)) {
                            zzx2 = zzagb.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzA(obj, i2)) {
                            zzx4 = zzagb.zzx(i3 << 3);
                            zzn = zzx4 + 1;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzA(obj, i2)) {
                            Object zzf = zzaji.zzf(obj, j);
                            if (zzf instanceof zzaft) {
                                int i6 = i3 << 3;
                                int i7 = zzagb.zzb;
                                int zzd = ((zzaft) zzf).zzd();
                                zzx5 = zzagb.zzx(zzd) + zzd;
                                zzx6 = zzagb.zzx(i6);
                                zzn = zzx6 + zzx5;
                                i += zzn;
                                break;
                            } else {
                                zzy = zzagb.zzw((String) zzf);
                                zzx3 = zzagb.zzx(i3 << 3);
                                i += zzx3 + zzy;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 9:
                        if (zzA(obj, i2)) {
                            zzn = zzaij.zzn(i3, zzaji.zzf(obj, j), zzr(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzA(obj, i2)) {
                            zzaft zzaftVar = (zzaft) zzaji.zzf(obj, j);
                            int i8 = i3 << 3;
                            int i9 = zzagb.zzb;
                            int zzd2 = zzaftVar.zzd();
                            zzx5 = zzagb.zzx(zzd2) + zzd2;
                            zzx6 = zzagb.zzx(i8);
                            zzn = zzx6 + zzx5;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzA(obj, i2)) {
                            zzy = zzagb.zzx(zzaji.zzc(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzA(obj, i2)) {
                            zzy = zzagb.zzu(zzaji.zzc(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzA(obj, i2)) {
                            zzx2 = zzagb.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzA(obj, i2)) {
                            zzx = zzagb.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzA(obj, i2)) {
                            int zzc = zzaji.zzc(obj, j);
                            zzx3 = zzagb.zzx(i3 << 3);
                            zzy = zzagb.zzx((zzc >> 31) ^ (zzc + zzc));
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzA(obj, i2)) {
                            long zzd3 = zzaji.zzd(obj, j);
                            zzx7 = zzagb.zzx(i3 << 3);
                            zzy2 = zzagb.zzy((zzd3 + zzd3) ^ (zzd3 >> 63));
                            zzn = zzx7 + zzy2;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzA(obj, i2)) {
                            zzn = zzagb.zzt(i3, (zzahw) zzaji.zzf(obj, j), zzr(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzn = zzaij.zzg(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 19:
                        zzn = zzaij.zze(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 20:
                        zzn = zzaij.zzl(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 21:
                        zzn = zzaij.zzw(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 22:
                        zzn = zzaij.zzj(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 23:
                        zzn = zzaij.zzg(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 24:
                        zzn = zzaij.zze(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 25:
                        zzn = zzaij.zza(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 26:
                        zzn = zzaij.zzt(i3, (List) zzaji.zzf(obj, j));
                        i += zzn;
                        break;
                    case 27:
                        zzn = zzaij.zzo(i3, (List) zzaji.zzf(obj, j), zzr(i2));
                        i += zzn;
                        break;
                    case 28:
                        zzn = zzaij.zzb(i3, (List) zzaji.zzf(obj, j));
                        i += zzn;
                        break;
                    case 29:
                        zzn = zzaij.zzu(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 30:
                        zzn = zzaij.zzc(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 31:
                        zzn = zzaij.zze(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 32:
                        zzn = zzaij.zzg(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 33:
                        zzn = zzaij.zzp(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 34:
                        zzn = zzaij.zzr(i3, (List) zzaji.zzf(obj, j), false);
                        i += zzn;
                        break;
                    case 35:
                        zzy = zzaij.zzh((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i10 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i10);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 36:
                        zzy = zzaij.zzf((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i11 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i11);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 37:
                        zzy = zzaij.zzm((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i12 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i12);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 38:
                        zzy = zzaij.zzx((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i13 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i13);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 39:
                        zzy = zzaij.zzk((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i14 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i14);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 40:
                        zzy = zzaij.zzh((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i15 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i15);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 41:
                        zzy = zzaij.zzf((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i16 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i16);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 42:
                        List list = (List) unsafe.getObject(obj, j);
                        int i17 = zzaij.zza;
                        zzy = list.size();
                        if (zzy > 0) {
                            int i18 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i18);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 43:
                        zzy = zzaij.zzv((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i19 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i19);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 44:
                        zzy = zzaij.zzd((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i20 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i20);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 45:
                        zzy = zzaij.zzf((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i21 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i21);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 46:
                        zzy = zzaij.zzh((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i22 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i22);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 47:
                        zzy = zzaij.zzq((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i23 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i23);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 48:
                        zzy = zzaij.zzs((List) unsafe.getObject(obj, j));
                        if (zzy > 0) {
                            int i24 = i3 << 3;
                            zzx8 = zzagb.zzx(zzy);
                            zzx9 = zzagb.zzx(i24);
                            zzx3 = zzx9 + zzx8;
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 49:
                        zzn = zzaij.zzi(i3, (List) zzaji.zzf(obj, j), zzr(i2));
                        i += zzn;
                        break;
                    case 50:
                        zzahr.zza(i3, zzaji.zzf(obj, j), zzs(i2));
                        break;
                    case 51:
                        if (zzE(obj, i3, i2)) {
                            zzx = zzagb.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzE(obj, i3, i2)) {
                            zzx2 = zzagb.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzE(obj, i3, i2)) {
                            zzy = zzagb.zzy(zzq(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzE(obj, i3, i2)) {
                            zzy = zzagb.zzy(zzq(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzE(obj, i3, i2)) {
                            zzy = zzagb.zzu(zzm(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzE(obj, i3, i2)) {
                            zzx = zzagb.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzE(obj, i3, i2)) {
                            zzx2 = zzagb.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzE(obj, i3, i2)) {
                            zzx4 = zzagb.zzx(i3 << 3);
                            zzn = zzx4 + 1;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzE(obj, i3, i2)) {
                            Object zzf2 = zzaji.zzf(obj, j);
                            if (zzf2 instanceof zzaft) {
                                int i25 = i3 << 3;
                                int i26 = zzagb.zzb;
                                int zzd4 = ((zzaft) zzf2).zzd();
                                zzx5 = zzagb.zzx(zzd4) + zzd4;
                                zzx6 = zzagb.zzx(i25);
                                zzn = zzx6 + zzx5;
                                i += zzn;
                                break;
                            } else {
                                zzy = zzagb.zzw((String) zzf2);
                                zzx3 = zzagb.zzx(i3 << 3);
                                i += zzx3 + zzy;
                                break;
                            }
                        } else {
                            break;
                        }
                    case 60:
                        if (zzE(obj, i3, i2)) {
                            zzn = zzaij.zzn(i3, zzaji.zzf(obj, j), zzr(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzE(obj, i3, i2)) {
                            zzaft zzaftVar2 = (zzaft) zzaji.zzf(obj, j);
                            int i27 = i3 << 3;
                            int i28 = zzagb.zzb;
                            int zzd5 = zzaftVar2.zzd();
                            zzx5 = zzagb.zzx(zzd5) + zzd5;
                            zzx6 = zzagb.zzx(i27);
                            zzn = zzx6 + zzx5;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzE(obj, i3, i2)) {
                            zzy = zzagb.zzx(zzm(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzE(obj, i3, i2)) {
                            zzy = zzagb.zzu(zzm(obj, j));
                            zzx3 = zzagb.zzx(i3 << 3);
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzE(obj, i3, i2)) {
                            zzx2 = zzagb.zzx(i3 << 3);
                            zzn = zzx2 + 4;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzE(obj, i3, i2)) {
                            zzx = zzagb.zzx(i3 << 3);
                            zzn = zzx + 8;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzE(obj, i3, i2)) {
                            int zzm = zzm(obj, j);
                            zzx3 = zzagb.zzx(i3 << 3);
                            zzy = zzagb.zzx((zzm >> 31) ^ (zzm + zzm));
                            i += zzx3 + zzy;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzE(obj, i3, i2)) {
                            long zzq = zzq(obj, j);
                            zzx7 = zzagb.zzx(i3 << 3);
                            zzy2 = zzagb.zzy((zzq + zzq) ^ (zzq >> 63));
                            zzn = zzx7 + zzy2;
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzE(obj, i3, i2)) {
                            zzn = zzagb.zzt(i3, (zzahw) zzaji.zzf(obj, j), zzr(i2));
                            i += zzn;
                            break;
                        } else {
                            break;
                        }
                }
            }
            zzaiy zzaiyVar = this.zzj;
            return i + zzaiyVar.zza(zzaiyVar.zzc(obj));
        }
        return zzl(obj);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001c. Please report as an issue. */
    @Override // com.google.android.libraries.places.internal.zzaih
    public final int zzb(Object obj) {
        int i;
        long doubleToLongBits;
        int i2;
        int floatToIntBits;
        int length = this.zzc.length;
        int i3 = 0;
        for (int i4 = 0; i4 < length; i4 += 3) {
            int zzp = zzp(i4);
            int i5 = this.zzc[i4];
            long j = 1048575 & zzp;
            int i6 = 37;
            switch (zzo(zzp)) {
                case 0:
                    i = i3 * 53;
                    doubleToLongBits = Double.doubleToLongBits(zzaji.zza(obj, j));
                    byte[] bArr = zzagx.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 1:
                    i2 = i3 * 53;
                    floatToIntBits = Float.floatToIntBits(zzaji.zzb(obj, j));
                    i3 = i2 + floatToIntBits;
                    break;
                case 2:
                    i = i3 * 53;
                    doubleToLongBits = zzaji.zzd(obj, j);
                    byte[] bArr2 = zzagx.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 3:
                    i = i3 * 53;
                    doubleToLongBits = zzaji.zzd(obj, j);
                    byte[] bArr3 = zzagx.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 4:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 5:
                    i = i3 * 53;
                    doubleToLongBits = zzaji.zzd(obj, j);
                    byte[] bArr4 = zzagx.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 6:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 7:
                    i2 = i3 * 53;
                    floatToIntBits = zzagx.zza(zzaji.zzw(obj, j));
                    i3 = i2 + floatToIntBits;
                    break;
                case 8:
                    i2 = i3 * 53;
                    floatToIntBits = ((String) zzaji.zzf(obj, j)).hashCode();
                    i3 = i2 + floatToIntBits;
                    break;
                case 9:
                    Object zzf = zzaji.zzf(obj, j);
                    if (zzf != null) {
                        i6 = zzf.hashCode();
                    }
                    i3 = (i3 * 53) + i6;
                    break;
                case 10:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzf(obj, j).hashCode();
                    i3 = i2 + floatToIntBits;
                    break;
                case 11:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 12:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 13:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 14:
                    i = i3 * 53;
                    doubleToLongBits = zzaji.zzd(obj, j);
                    byte[] bArr5 = zzagx.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 15:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzc(obj, j);
                    i3 = i2 + floatToIntBits;
                    break;
                case 16:
                    i = i3 * 53;
                    doubleToLongBits = zzaji.zzd(obj, j);
                    byte[] bArr6 = zzagx.zzd;
                    i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                    break;
                case 17:
                    Object zzf2 = zzaji.zzf(obj, j);
                    if (zzf2 != null) {
                        i6 = zzf2.hashCode();
                    }
                    i3 = (i3 * 53) + i6;
                    break;
                case 18:
                case 19:
                case 20:
                case 21:
                case 22:
                case 23:
                case 24:
                case 25:
                case 26:
                case 27:
                case 28:
                case 29:
                case 30:
                case 31:
                case 32:
                case 33:
                case 34:
                case 35:
                case 36:
                case 37:
                case 38:
                case 39:
                case 40:
                case 41:
                case 42:
                case 43:
                case 44:
                case 45:
                case 46:
                case 47:
                case 48:
                case 49:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzf(obj, j).hashCode();
                    i3 = i2 + floatToIntBits;
                    break;
                case 50:
                    i2 = i3 * 53;
                    floatToIntBits = zzaji.zzf(obj, j).hashCode();
                    i3 = i2 + floatToIntBits;
                    break;
                case 51:
                    if (zzE(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = Double.doubleToLongBits(zzj(obj, j));
                        byte[] bArr7 = zzagx.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = Float.floatToIntBits(zzk(obj, j));
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzE(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzq(obj, j);
                        byte[] bArr8 = zzagx.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzE(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzq(obj, j);
                        byte[] bArr9 = zzagx.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzm(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzE(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzq(obj, j);
                        byte[] bArr10 = zzagx.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzm(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzagx.zza(zzF(obj, j));
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = ((String) zzaji.zzf(obj, j)).hashCode();
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzaji.zzf(obj, j).hashCode();
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzaji.zzf(obj, j).hashCode();
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzm(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzm(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzm(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzE(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzq(obj, j);
                        byte[] bArr11 = zzagx.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzm(obj, j);
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzE(obj, i5, i4)) {
                        i = i3 * 53;
                        doubleToLongBits = zzq(obj, j);
                        byte[] bArr12 = zzagx.zzd;
                        i3 = i + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)));
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzE(obj, i5, i4)) {
                        i2 = i3 * 53;
                        floatToIntBits = zzaji.zzf(obj, j).hashCode();
                        i3 = i2 + floatToIntBits;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i3 * 53) + this.zzj.zzc(obj).hashCode();
        if (!this.zzf) {
            return hashCode;
        }
        this.zzk.zza(obj);
        throw null;
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final Object zzc() {
        return ((zzago) this.zze).zzy();
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final void zzd(Object obj) {
        if (zzD(obj)) {
            if (obj instanceof zzago) {
                zzago zzagoVar = (zzago) obj;
                zzagoVar.zzJ(Integer.MAX_VALUE);
                zzagoVar.zza = 0;
                zzagoVar.zzH();
            }
            int length = this.zzc.length;
            for (int i = 0; i < length; i += 3) {
                int zzp = zzp(i);
                int i2 = 1048575 & zzp;
                int zzo = zzo(zzp);
                long j = i2;
                if (zzo != 9) {
                    if (zzo == 60 || zzo == 68) {
                        if (zzE(obj, this.zzc[i], i)) {
                            zzr(i).zzd(zzb.getObject(obj, j));
                        }
                    } else {
                        switch (zzo) {
                            case 18:
                            case 19:
                            case 20:
                            case 21:
                            case 22:
                            case 23:
                            case 24:
                            case 25:
                            case 26:
                            case 27:
                            case 28:
                            case 29:
                            case 30:
                            case 31:
                            case 32:
                            case 33:
                            case 34:
                            case 35:
                            case 36:
                            case 37:
                            case 38:
                            case 39:
                            case 40:
                            case 41:
                            case 42:
                            case 43:
                            case 44:
                            case 45:
                            case 46:
                            case 47:
                            case 48:
                            case 49:
                                this.zzi.zza(obj, j);
                                break;
                            case 50:
                                Unsafe unsafe = zzb;
                                Object object = unsafe.getObject(obj, j);
                                if (object != null) {
                                    ((zzahq) object).zzb();
                                    unsafe.putObject(obj, j, object);
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
                if (zzA(obj, i)) {
                    zzr(i).zzd(zzb.getObject(obj, j));
                }
            }
            this.zzj.zze(obj);
            if (this.zzf) {
                this.zzk.zzb(obj);
            }
        }
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final void zze(Object obj, Object obj2) {
        if (zzD(obj)) {
            obj2.getClass();
            for (int i = 0; i < this.zzc.length; i += 3) {
                int zzp = zzp(i);
                int i2 = this.zzc[i];
                long j = 1048575 & zzp;
                switch (zzo(zzp)) {
                    case 0:
                        if (zzA(obj2, i)) {
                            zzaji.zzo(obj, j, zzaji.zza(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzA(obj2, i)) {
                            zzaji.zzp(obj, j, zzaji.zzb(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzA(obj2, i)) {
                            zzaji.zzr(obj, j, zzaji.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzA(obj2, i)) {
                            zzaji.zzr(obj, j, zzaji.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzA(obj2, i)) {
                            zzaji.zzq(obj, j, zzaji.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzA(obj2, i)) {
                            zzaji.zzr(obj, j, zzaji.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzA(obj2, i)) {
                            zzaji.zzq(obj, j, zzaji.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzA(obj2, i)) {
                            zzaji.zzm(obj, j, zzaji.zzw(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzA(obj2, i)) {
                            zzaji.zzs(obj, j, zzaji.zzf(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        zzu(obj, obj2, i);
                        break;
                    case 10:
                        if (zzA(obj2, i)) {
                            zzaji.zzs(obj, j, zzaji.zzf(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzA(obj2, i)) {
                            zzaji.zzq(obj, j, zzaji.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzA(obj2, i)) {
                            zzaji.zzq(obj, j, zzaji.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzA(obj2, i)) {
                            zzaji.zzq(obj, j, zzaji.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzA(obj2, i)) {
                            zzaji.zzr(obj, j, zzaji.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzA(obj2, i)) {
                            zzaji.zzq(obj, j, zzaji.zzc(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzA(obj2, i)) {
                            zzaji.zzr(obj, j, zzaji.zzd(obj2, j));
                            zzw(obj, i);
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        zzu(obj, obj2, i);
                        break;
                    case 18:
                    case 19:
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                    case 25:
                    case 26:
                    case 27:
                    case 28:
                    case 29:
                    case 30:
                    case 31:
                    case 32:
                    case 33:
                    case 34:
                    case 35:
                    case 36:
                    case 37:
                    case 38:
                    case 39:
                    case 40:
                    case 41:
                    case 42:
                    case 43:
                    case 44:
                    case 45:
                    case 46:
                    case 47:
                    case 48:
                    case 49:
                        this.zzi.zzb(obj, obj2, j);
                        break;
                    case 50:
                        int i3 = zzaij.zza;
                        zzahq zzahqVar = (zzahq) zzaji.zzf(obj, j);
                        zzahq zzahqVar2 = (zzahq) zzaji.zzf(obj2, j);
                        if (!zzahqVar2.isEmpty()) {
                            if (!zzahqVar.zzd()) {
                                zzahqVar = zzahqVar.zza();
                            }
                            zzahqVar.zzc(zzahqVar2);
                        }
                        zzaji.zzs(obj, j, zzahqVar);
                        break;
                    case 51:
                    case 52:
                    case 53:
                    case 54:
                    case 55:
                    case 56:
                    case 57:
                    case 58:
                    case 59:
                        if (zzE(obj2, i2, i)) {
                            zzaji.zzs(obj, j, zzaji.zzf(obj2, j));
                            zzx(obj, i2, i);
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        zzv(obj, obj2, i);
                        break;
                    case 61:
                    case 62:
                    case 63:
                    case 64:
                    case 65:
                    case 66:
                    case 67:
                        if (zzE(obj2, i2, i)) {
                            zzaji.zzs(obj, j, zzaji.zzf(obj2, j));
                            zzx(obj, i2, i);
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        zzv(obj, obj2, i);
                        break;
                }
            }
            zzaij.zzA(this.zzj, obj, obj2);
            if (this.zzf) {
                this.zzk.zza(obj2);
                throw null;
            }
            return;
        }
        throw new IllegalArgumentException("Mutating immutable message: ".concat(String.valueOf(String.valueOf(obj))));
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final void zzf(Object obj, zzajp zzajpVar) throws IOException {
        int i;
        zzajn zzajnVar = zzajn.DOUBLE;
        int i2 = 1048575;
        if (this.zzl - 1 != 0) {
            if (!this.zzf) {
                int length = this.zzc.length;
                for (int i3 = 0; i3 < length; i3 += 3) {
                    int zzp = zzp(i3);
                    int i4 = this.zzc[i3];
                    switch (zzo(zzp)) {
                        case 0:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzf(i4, zzaji.zza(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 1:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzn(i4, zzaji.zzb(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 2:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzs(i4, zzaji.zzd(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 3:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzH(i4, zzaji.zzd(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 4:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzq(i4, zzaji.zzc(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 5:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzl(i4, zzaji.zzd(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 6:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzj(i4, zzaji.zzc(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 7:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzb(i4, zzaji.zzw(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 8:
                            if (zzA(obj, i3)) {
                                zzG(i4, zzaji.zzf(obj, zzp & 1048575), zzajpVar);
                                break;
                            } else {
                                break;
                            }
                        case 9:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzu(i4, zzaji.zzf(obj, zzp & 1048575), zzr(i3));
                                break;
                            } else {
                                break;
                            }
                        case 10:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzd(i4, (zzaft) zzaji.zzf(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 11:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzF(i4, zzaji.zzc(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 12:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzh(i4, zzaji.zzc(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 13:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzv(i4, zzaji.zzc(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 14:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzx(i4, zzaji.zzd(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 15:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzz(i4, zzaji.zzc(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 16:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzB(i4, zzaji.zzd(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 17:
                            if (zzA(obj, i3)) {
                                zzajpVar.zzp(i4, zzaji.zzf(obj, zzp & 1048575), zzr(i3));
                                break;
                            } else {
                                break;
                            }
                        case 18:
                            zzaij.zzE(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 19:
                            zzaij.zzI(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 20:
                            zzaij.zzL(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 21:
                            zzaij.zzT(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 22:
                            zzaij.zzK(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 23:
                            zzaij.zzH(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 24:
                            zzaij.zzG(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 25:
                            zzaij.zzC(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 26:
                            zzaij.zzR(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar);
                            break;
                        case 27:
                            zzaij.zzM(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, zzr(i3));
                            break;
                        case 28:
                            zzaij.zzD(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar);
                            break;
                        case 29:
                            zzaij.zzS(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 30:
                            zzaij.zzF(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 31:
                            zzaij.zzN(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 32:
                            zzaij.zzO(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 33:
                            zzaij.zzP(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 34:
                            zzaij.zzQ(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, false);
                            break;
                        case 35:
                            zzaij.zzE(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 36:
                            zzaij.zzI(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 37:
                            zzaij.zzL(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 38:
                            zzaij.zzT(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 39:
                            zzaij.zzK(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 40:
                            zzaij.zzH(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 41:
                            zzaij.zzG(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 42:
                            zzaij.zzC(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 43:
                            zzaij.zzS(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 44:
                            zzaij.zzF(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 45:
                            zzaij.zzN(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 46:
                            zzaij.zzO(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 47:
                            zzaij.zzP(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 48:
                            zzaij.zzQ(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, true);
                            break;
                        case 49:
                            zzaij.zzJ(i4, (List) zzaji.zzf(obj, zzp & 1048575), zzajpVar, zzr(i3));
                            break;
                        case 50:
                            zzy(zzajpVar, i4, zzaji.zzf(obj, zzp & 1048575), i3);
                            break;
                        case 51:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzf(i4, zzj(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 52:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzn(i4, zzk(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 53:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzs(i4, zzq(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 54:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzH(i4, zzq(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 55:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzq(i4, zzm(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 56:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzl(i4, zzq(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 57:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzj(i4, zzm(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 58:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzb(i4, zzF(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 59:
                            if (zzE(obj, i4, i3)) {
                                zzG(i4, zzaji.zzf(obj, zzp & 1048575), zzajpVar);
                                break;
                            } else {
                                break;
                            }
                        case 60:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzu(i4, zzaji.zzf(obj, zzp & 1048575), zzr(i3));
                                break;
                            } else {
                                break;
                            }
                        case 61:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzd(i4, (zzaft) zzaji.zzf(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 62:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzF(i4, zzm(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 63:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzh(i4, zzm(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 64:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzv(i4, zzm(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 65:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzx(i4, zzq(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 66:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzz(i4, zzm(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 67:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzB(i4, zzq(obj, zzp & 1048575));
                                break;
                            } else {
                                break;
                            }
                        case 68:
                            if (zzE(obj, i4, i3)) {
                                zzajpVar.zzp(i4, zzaji.zzf(obj, zzp & 1048575), zzr(i3));
                                break;
                            } else {
                                break;
                            }
                    }
                }
                zzaiy zzaiyVar = this.zzj;
                zzaiyVar.zzg(zzaiyVar.zzc(obj), zzajpVar);
                return;
            }
            this.zzk.zza(obj);
            throw null;
        }
        if (!this.zzf) {
            int length2 = this.zzc.length;
            Unsafe unsafe = zzb;
            int i5 = 0;
            int i6 = 1048575;
            int i7 = 0;
            while (i5 < length2) {
                int zzp2 = zzp(i5);
                int[] iArr = this.zzc;
                int i8 = iArr[i5];
                int zzo = zzo(zzp2);
                if (zzo <= 17) {
                    int i9 = iArr[i5 + 2];
                    int i10 = i9 & i2;
                    if (i10 != i6) {
                        i7 = unsafe.getInt(obj, i10);
                        i6 = i10;
                    }
                    i = 1 << (i9 >>> 20);
                } else {
                    i = 0;
                }
                long j = zzp2 & i2;
                switch (zzo) {
                    case 0:
                        if ((i7 & i) == 0) {
                            break;
                        } else {
                            zzajpVar.zzf(i8, zzaji.zza(obj, j));
                            continue;
                        }
                    case 1:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzn(i8, zzaji.zzb(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 2:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzs(i8, unsafe.getLong(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 3:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzH(i8, unsafe.getLong(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 4:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzq(i8, unsafe.getInt(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 5:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzl(i8, unsafe.getLong(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 6:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzj(i8, unsafe.getInt(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 7:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzb(i8, zzaji.zzw(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 8:
                        if ((i7 & i) != 0) {
                            zzG(i8, unsafe.getObject(obj, j), zzajpVar);
                            break;
                        } else {
                            continue;
                        }
                    case 9:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzu(i8, unsafe.getObject(obj, j), zzr(i5));
                            break;
                        } else {
                            continue;
                        }
                    case 10:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzd(i8, (zzaft) unsafe.getObject(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 11:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzF(i8, unsafe.getInt(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 12:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzh(i8, unsafe.getInt(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 13:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzv(i8, unsafe.getInt(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 14:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzx(i8, unsafe.getLong(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 15:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzz(i8, unsafe.getInt(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 16:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzB(i8, unsafe.getLong(obj, j));
                            break;
                        } else {
                            continue;
                        }
                    case 17:
                        if ((i7 & i) != 0) {
                            zzajpVar.zzp(i8, unsafe.getObject(obj, j), zzr(i5));
                            break;
                        } else {
                            continue;
                        }
                    case 18:
                        zzaij.zzE(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        continue;
                    case 19:
                        zzaij.zzI(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        continue;
                    case 20:
                        zzaij.zzL(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        continue;
                    case 21:
                        zzaij.zzT(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        continue;
                    case 22:
                        zzaij.zzK(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        continue;
                    case 23:
                        zzaij.zzH(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        continue;
                    case 24:
                        zzaij.zzG(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        continue;
                    case 25:
                        zzaij.zzC(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        continue;
                    case 26:
                        zzaij.zzR(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar);
                        break;
                    case 27:
                        zzaij.zzM(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, zzr(i5));
                        break;
                    case 28:
                        zzaij.zzD(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar);
                        break;
                    case 29:
                        zzaij.zzS(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        break;
                    case 30:
                        zzaij.zzF(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        break;
                    case 31:
                        zzaij.zzN(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        break;
                    case 32:
                        zzaij.zzO(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        break;
                    case 33:
                        zzaij.zzP(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        break;
                    case 34:
                        zzaij.zzQ(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, false);
                        break;
                    case 35:
                        zzaij.zzE(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 36:
                        zzaij.zzI(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 37:
                        zzaij.zzL(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 38:
                        zzaij.zzT(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 39:
                        zzaij.zzK(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 40:
                        zzaij.zzH(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 41:
                        zzaij.zzG(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 42:
                        zzaij.zzC(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 43:
                        zzaij.zzS(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 44:
                        zzaij.zzF(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 45:
                        zzaij.zzN(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 46:
                        zzaij.zzO(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 47:
                        zzaij.zzP(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 48:
                        zzaij.zzQ(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, true);
                        break;
                    case 49:
                        zzaij.zzJ(this.zzc[i5], (List) unsafe.getObject(obj, j), zzajpVar, zzr(i5));
                        break;
                    case 50:
                        zzy(zzajpVar, i8, unsafe.getObject(obj, j), i5);
                        break;
                    case 51:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzf(i8, zzj(obj, j));
                            break;
                        }
                        break;
                    case 52:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzn(i8, zzk(obj, j));
                            break;
                        }
                        break;
                    case 53:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzs(i8, zzq(obj, j));
                            break;
                        }
                        break;
                    case 54:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzH(i8, zzq(obj, j));
                            break;
                        }
                        break;
                    case 55:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzq(i8, zzm(obj, j));
                            break;
                        }
                        break;
                    case 56:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzl(i8, zzq(obj, j));
                            break;
                        }
                        break;
                    case 57:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzj(i8, zzm(obj, j));
                            break;
                        }
                        break;
                    case 58:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzb(i8, zzF(obj, j));
                            break;
                        }
                        break;
                    case 59:
                        if (zzE(obj, i8, i5)) {
                            zzG(i8, unsafe.getObject(obj, j), zzajpVar);
                            break;
                        }
                        break;
                    case 60:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzu(i8, unsafe.getObject(obj, j), zzr(i5));
                            break;
                        }
                        break;
                    case 61:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzd(i8, (zzaft) unsafe.getObject(obj, j));
                            break;
                        }
                        break;
                    case 62:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzF(i8, zzm(obj, j));
                            break;
                        }
                        break;
                    case 63:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzh(i8, zzm(obj, j));
                            break;
                        }
                        break;
                    case 64:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzv(i8, zzm(obj, j));
                            break;
                        }
                        break;
                    case 65:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzx(i8, zzq(obj, j));
                            break;
                        }
                        break;
                    case 66:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzz(i8, zzm(obj, j));
                            break;
                        }
                        break;
                    case 67:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzB(i8, zzq(obj, j));
                            break;
                        }
                        break;
                    case 68:
                        if (zzE(obj, i8, i5)) {
                            zzajpVar.zzp(i8, unsafe.getObject(obj, j), zzr(i5));
                            break;
                        }
                        break;
                }
                i5 += 3;
                i2 = 1048575;
            }
            zzaiy zzaiyVar2 = this.zzj;
            zzaiyVar2.zzg(zzaiyVar2.zzc(obj), zzajpVar);
            return;
        }
        this.zzk.zza(obj);
        throw null;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:17:0x01c2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:18:0x01c3 A[SYNTHETIC] */
    @Override // com.google.android.libraries.places.internal.zzaih
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean zzg(java.lang.Object r9, java.lang.Object r10) {
        /*
            Method dump skipped, instructions count: 634
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzahz.zzg(java.lang.Object, java.lang.Object):boolean");
    }

    @Override // com.google.android.libraries.places.internal.zzaih
    public final boolean zzh(Object obj) {
        int i;
        int i2;
        int i3 = 1048575;
        int i4 = 0;
        int i5 = 0;
        while (i5 < this.zzh) {
            int i6 = this.zzg[i5];
            int i7 = this.zzc[i6];
            int zzp = zzp(i6);
            int i8 = this.zzc[i6 + 2];
            int i9 = i8 & 1048575;
            int i10 = 1 << (i8 >>> 20);
            if (i9 != i3) {
                if (i9 != 1048575) {
                    i4 = zzb.getInt(obj, i9);
                }
                i2 = i4;
                i = i9;
            } else {
                i = i3;
                i2 = i4;
            }
            if ((268435456 & zzp) != 0 && !zzB(obj, i6, i, i2, i10)) {
                return false;
            }
            int zzo = zzo(zzp);
            if (zzo != 9 && zzo != 17) {
                if (zzo != 27) {
                    if (zzo == 60 || zzo == 68) {
                        if (zzE(obj, i7, i6) && !zzC(obj, zzp, zzr(i6))) {
                            return false;
                        }
                    } else if (zzo != 49) {
                        if (zzo == 50 && !((zzahq) zzaji.zzf(obj, zzp & 1048575)).isEmpty()) {
                            throw null;
                        }
                    }
                }
                List list = (List) zzaji.zzf(obj, zzp & 1048575);
                if (list.isEmpty()) {
                    continue;
                } else {
                    zzaih zzr = zzr(i6);
                    for (int i11 = 0; i11 < list.size(); i11++) {
                        if (!zzr.zzh(list.get(i11))) {
                            return false;
                        }
                    }
                }
            } else if (zzB(obj, i6, i, i2, i10) && !zzC(obj, zzp, zzr(i6))) {
                return false;
            }
            i5++;
            i3 = i;
            i4 = i2;
        }
        if (!this.zzf) {
            return true;
        }
        this.zzk.zza(obj);
        throw null;
    }
}
