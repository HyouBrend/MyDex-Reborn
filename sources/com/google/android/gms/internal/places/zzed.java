package com.google.android.gms.internal.places;

/* loaded from: classes.dex */
final class zzed extends zzec {
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00b6, code lost:
    
        return -1;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x0061, code lost:
    
        return -1;
     */
    @Override // com.google.android.gms.internal.places.zzec
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    final int zzc(int r16, byte[] r17, int r18, int r19) {
        /*
            Method dump skipped, instructions count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzed.zzc(int, byte[], int, int):int");
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
            byte zzb = zzdy.zzb(bArr, i);
            zze3 = zzdz.zze(zzb);
            if (!zze3) {
                break;
            }
            i++;
            zzdz.zzb(zzb, cArr, i4);
            i4++;
        }
        int i5 = i4;
        while (i < i3) {
            int i6 = i + 1;
            byte zzb2 = zzdy.zzb(bArr, i);
            zze = zzdz.zze(zzb2);
            if (zze) {
                int i7 = i5 + 1;
                zzdz.zzb(zzb2, cArr, i5);
                while (i6 < i3) {
                    byte zzb3 = zzdy.zzb(bArr, i6);
                    zze2 = zzdz.zze(zzb3);
                    if (!zze2) {
                        break;
                    }
                    i6++;
                    zzdz.zzb(zzb3, cArr, i7);
                    i7++;
                }
                i = i6;
                i5 = i7;
            } else {
                zzf = zzdz.zzf(zzb2);
                if (!zzf) {
                    zzg = zzdz.zzg(zzb2);
                    if (zzg) {
                        if (i6 < i3 - 1) {
                            int i8 = i6 + 1;
                            zzdz.zzb(zzb2, zzdy.zzb(bArr, i6), zzdy.zzb(bArr, i8), cArr, i5);
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
                        byte zzb4 = zzdy.zzb(bArr, i6);
                        int i10 = i9 + 1;
                        zzdz.zzb(zzb2, zzb4, zzdy.zzb(bArr, i9), zzdy.zzb(bArr, i10), cArr, i5);
                        i = i10 + 1;
                        i5 = i5 + 1 + 1;
                    }
                } else if (i6 < i3) {
                    zzdz.zzb(zzb2, zzdy.zzb(bArr, i6), cArr, i5);
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
    @Override // com.google.android.gms.internal.places.zzec
    public final int zzc(CharSequence charSequence, byte[] bArr, int i, int i2) {
        char c;
        long j;
        long j2;
        long j3;
        int i3;
        char charAt;
        long j4 = i;
        long j5 = i2 + j4;
        int length = charSequence.length();
        if (length > i2 || bArr.length - i2 < i) {
            char charAt2 = charSequence.charAt(length - 1);
            StringBuilder sb = new StringBuilder(37);
            sb.append("Failed writing ");
            sb.append(charAt2);
            sb.append(" at index ");
            sb.append(i + i2);
            throw new ArrayIndexOutOfBoundsException(sb.toString());
        }
        int i4 = 0;
        while (true) {
            c = 128;
            j = 1;
            if (i4 >= length || (charAt = charSequence.charAt(i4)) >= 128) {
                break;
            }
            zzdy.zzb(bArr, j4, (byte) charAt);
            i4++;
            j4 = 1 + j4;
        }
        if (i4 == length) {
            return (int) j4;
        }
        while (i4 < length) {
            char charAt3 = charSequence.charAt(i4);
            if (charAt3 >= c || j4 >= j5) {
                if (charAt3 < 2048 && j4 <= j5 - 2) {
                    long j6 = j4 + j;
                    zzdy.zzb(bArr, j4, (byte) ((charAt3 >>> 6) | 960));
                    zzdy.zzb(bArr, j6, (byte) ((charAt3 & '?') | 128));
                    j2 = j6 + j;
                    j3 = j;
                } else {
                    if ((charAt3 >= 55296 && 57343 >= charAt3) || j4 > j5 - 3) {
                        if (j4 <= j5 - 4) {
                            int i5 = i4 + 1;
                            if (i5 != length) {
                                char charAt4 = charSequence.charAt(i5);
                                if (Character.isSurrogatePair(charAt3, charAt4)) {
                                    int codePoint = Character.toCodePoint(charAt3, charAt4);
                                    long j7 = j4 + 1;
                                    zzdy.zzb(bArr, j4, (byte) ((codePoint >>> 18) | 240));
                                    long j8 = j7 + 1;
                                    zzdy.zzb(bArr, j7, (byte) (((codePoint >>> 12) & 63) | 128));
                                    long j9 = j8 + 1;
                                    zzdy.zzb(bArr, j8, (byte) (((codePoint >>> 6) & 63) | 128));
                                    j3 = 1;
                                    j2 = j9 + 1;
                                    zzdy.zzb(bArr, j9, (byte) ((codePoint & 63) | 128));
                                    i4 = i5;
                                } else {
                                    i4 = i5;
                                }
                            }
                            throw new zzee(i4 - 1, length);
                        }
                        if (55296 <= charAt3 && charAt3 <= 57343 && ((i3 = i4 + 1) == length || !Character.isSurrogatePair(charAt3, charSequence.charAt(i3)))) {
                            throw new zzee(i4, length);
                        }
                        StringBuilder sb2 = new StringBuilder(46);
                        sb2.append("Failed writing ");
                        sb2.append(charAt3);
                        sb2.append(" at index ");
                        sb2.append(j4);
                        throw new ArrayIndexOutOfBoundsException(sb2.toString());
                    }
                    long j10 = j4 + j;
                    zzdy.zzb(bArr, j4, (byte) ((charAt3 >>> '\f') | 480));
                    long j11 = j10 + j;
                    zzdy.zzb(bArr, j10, (byte) (((charAt3 >>> 6) & 63) | 128));
                    zzdy.zzb(bArr, j11, (byte) ((charAt3 & '?') | 128));
                    j2 = j11 + 1;
                    j3 = 1;
                }
                i4++;
                c = 128;
                long j12 = j3;
                j4 = j2;
                j = j12;
            } else {
                long j13 = j4 + j;
                zzdy.zzb(bArr, j4, (byte) charAt3);
                j3 = j;
                j2 = j13;
            }
            i4++;
            c = 128;
            long j122 = j3;
            j4 = j2;
            j = j122;
        }
        return (int) j4;
    }

    private static int zzb(byte[] bArr, int i, long j, int i2) {
        int zzao;
        int zzs;
        int zzd;
        if (i2 == 0) {
            zzao = zzea.zzao(i);
            return zzao;
        }
        if (i2 == 1) {
            zzs = zzea.zzs(i, zzdy.zzb(bArr, j));
            return zzs;
        }
        if (i2 == 2) {
            zzd = zzea.zzd(i, zzdy.zzb(bArr, j), zzdy.zzb(bArr, j + 1));
            return zzd;
        }
        throw new AssertionError();
    }
}
