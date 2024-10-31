package com.google.android.gms.internal.places;

/* loaded from: classes.dex */
final class zzeb extends zzec {
    @Override // com.google.android.gms.internal.places.zzec
    final int zzc(int i, byte[] bArr, int i2, int i3) {
        int zzg;
        int zzg2;
        while (i2 < i3 && bArr[i2] >= 0) {
            i2++;
        }
        if (i2 >= i3) {
            return 0;
        }
        while (i2 < i3) {
            int i4 = i2 + 1;
            byte b = bArr[i2];
            if (b < 0) {
                if (b < -32) {
                    if (i4 >= i3) {
                        return b;
                    }
                    if (b >= -62) {
                        i2 = i4 + 1;
                        if (bArr[i4] > -65) {
                        }
                    }
                    return -1;
                }
                if (b >= -16) {
                    if (i4 < i3 - 2) {
                        int i5 = i4 + 1;
                        byte b2 = bArr[i4];
                        if (b2 <= -65 && (((b << 28) + (b2 + 112)) >> 30) == 0) {
                            int i6 = i5 + 1;
                            if (bArr[i5] <= -65) {
                                i4 = i6 + 1;
                                if (bArr[i6] > -65) {
                                }
                            }
                        }
                        return -1;
                    }
                    zzg2 = zzea.zzg(bArr, i4, i3);
                    return zzg2;
                }
                if (i4 < i3 - 1) {
                    int i7 = i4 + 1;
                    byte b3 = bArr[i4];
                    if (b3 <= -65 && ((b != -32 || b3 >= -96) && (b != -19 || b3 < -96))) {
                        i2 = i7 + 1;
                        if (bArr[i7] > -65) {
                        }
                    }
                    return -1;
                }
                zzg = zzea.zzg(bArr, i4, i3);
                return zzg;
            }
            i2 = i4;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.gms.internal.places.zzec
    public final String zzh(byte[] bArr, int i, int i2) throws zzbk {
        boolean zze;
        boolean zze2;
        boolean zzf;
        boolean zzg;
        boolean zze3;
        if ((i | i2 | ((bArr.length - i) - i2)) < 0) {
            throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
        }
        int i3 = i + i2;
        char[] cArr = new char[i2];
        int i4 = 0;
        while (i < i3) {
            byte b = bArr[i];
            zze3 = zzdz.zze(b);
            if (!zze3) {
                break;
            }
            i++;
            zzdz.zzb(b, cArr, i4);
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte b2 = bArr[i];
            zze = zzdz.zze(b2);
            if (zze) {
                int i7 = i5 + 1;
                zzdz.zzb(b2, cArr, i5);
                while (i6 < i3) {
                    byte b3 = bArr[i6];
                    zze2 = zzdz.zze(b3);
                    if (!zze2) {
                        break;
                    }
                    i6++;
                    zzdz.zzb(b3, cArr, i7);
                    i7++;
                }
                i = i6;
                i5 = i7;
            } else {
                zzf = zzdz.zzf(b2);
                if (!zzf) {
                    zzg = zzdz.zzg(b2);
                    if (zzg) {
                        if (i6 < i3 - 1) {
                            int i8 = i6 + 1;
                            zzdz.zzb(b2, bArr[i6], bArr[i8], cArr, i5);
                            i = i8 + 1;
                            i5++;
                        } else {
                            throw zzbk.zzbu();
                        }
                    } else {
                        if (i6 >= i3 - 2) {
                            throw zzbk.zzbu();
                        }
                        int i9 = i6 + 1;
                        byte b4 = bArr[i6];
                        int i10 = i9 + 1;
                        zzdz.zzb(b2, b4, bArr[i9], bArr[i10], cArr, i5);
                        i = i10 + 1;
                        i5 = i5 + 1 + 1;
                    }
                } else if (i6 < i3) {
                    zzdz.zzb(b2, bArr[i6], cArr, i5);
                    i = i6 + 1;
                    i5++;
                } else {
                    throw zzbk.zzbu();
                }
            }
        }
        return new String(cArr, 0, i5);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x001d, code lost:
    
        return r10 + r0;
     */
    @Override // com.google.android.gms.internal.places.zzec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int zzc(java.lang.CharSequence r8, byte[] r9, int r10, int r11) {
        /*
            Method dump skipped, instructions count: 256
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzeb.zzc(java.lang.CharSequence, byte[], int, int):int");
    }
}
