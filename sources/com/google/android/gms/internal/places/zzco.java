package com.google.android.gms.internal.places;

import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.internal.places.zzbc;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzco<T> implements zzda<T> {
    private static final int[] zzkq = new int[0];
    private static final Unsafe zzkr = zzdy.zzdn();
    private final int[] zzks;
    private final Object[] zzkt;
    private final int zzku;
    private final int zzkv;
    private final zzck zzkw;
    private final boolean zzkx;
    private final boolean zzky;
    private final boolean zzkz;
    private final boolean zzla;
    private final int[] zzlb;
    private final int zzlc;
    private final int zzld;
    private final zzcs zzle;
    private final zzbu zzlf;
    private final zzds<?, ?> zzlg;
    private final zzar<?> zzlh;
    private final zzcd zzli;

    private zzco(int[] iArr, Object[] objArr, int i, int i2, zzck zzckVar, boolean z, boolean z2, int[] iArr2, int i3, int i4, zzcs zzcsVar, zzbu zzbuVar, zzds<?, ?> zzdsVar, zzar<?> zzarVar, zzcd zzcdVar) {
        this.zzks = iArr;
        this.zzkt = objArr;
        this.zzku = i;
        this.zzkv = i2;
        this.zzky = zzckVar instanceof zzbc;
        this.zzkz = z;
        this.zzkx = zzarVar != null && zzarVar.zzf(zzckVar);
        this.zzla = false;
        this.zzlb = iArr2;
        this.zzlc = i3;
        this.zzld = i4;
        this.zzle = zzcsVar;
        this.zzlf = zzbuVar;
        this.zzlg = zzdsVar;
        this.zzlh = zzarVar;
        this.zzkw = zzckVar;
        this.zzli = zzcdVar;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> zzco<T> zzb(Class<T> cls, zzci zzciVar, zzcs zzcsVar, zzbu zzbuVar, zzds<?, ?> zzdsVar, zzar<?> zzarVar, zzcd zzcdVar) {
        int i;
        int charAt;
        int charAt2;
        int i2;
        int i3;
        int i4;
        int i5;
        int[] iArr;
        int i6;
        int i7;
        char charAt3;
        int i8;
        char charAt4;
        int i9;
        char charAt5;
        int i10;
        char charAt6;
        int i11;
        char charAt7;
        int i12;
        char charAt8;
        int i13;
        char charAt9;
        int i14;
        char charAt10;
        int i15;
        int i16;
        boolean z;
        int i17;
        zzcx zzcxVar;
        int i18;
        int objectFieldOffset;
        int i19;
        int i20;
        Class<?> cls2;
        String str;
        int i21;
        int i22;
        Field zzb;
        int i23;
        char charAt11;
        int i24;
        Field zzb2;
        Field zzb3;
        int i25;
        char charAt12;
        int i26;
        char charAt13;
        int i27;
        char charAt14;
        int i28;
        char charAt15;
        char charAt16;
        if (zzciVar instanceof zzcx) {
            zzcx zzcxVar2 = (zzcx) zzciVar;
            int i29 = 0;
            boolean z2 = zzcxVar2.zzcj() == zzbc.zze.zziu;
            String zzcr = zzcxVar2.zzcr();
            int length = zzcr.length();
            int charAt17 = zzcr.charAt(0);
            if (charAt17 >= 55296) {
                int i30 = charAt17 & 8191;
                int i31 = 1;
                int i32 = 13;
                while (true) {
                    i = i31 + 1;
                    charAt16 = zzcr.charAt(i31);
                    if (charAt16 < 55296) {
                        break;
                    }
                    i30 |= (charAt16 & 8191) << i32;
                    i32 += 13;
                    i31 = i;
                }
                charAt17 = i30 | (charAt16 << i32);
            } else {
                i = 1;
            }
            int i33 = i + 1;
            int charAt18 = zzcr.charAt(i);
            if (charAt18 >= 55296) {
                int i34 = charAt18 & 8191;
                int i35 = 13;
                while (true) {
                    i28 = i33 + 1;
                    charAt15 = zzcr.charAt(i33);
                    if (charAt15 < 55296) {
                        break;
                    }
                    i34 |= (charAt15 & 8191) << i35;
                    i35 += 13;
                    i33 = i28;
                }
                charAt18 = i34 | (charAt15 << i35);
                i33 = i28;
            }
            if (charAt18 == 0) {
                iArr = zzkq;
                i6 = 0;
                i3 = 0;
                charAt = 0;
                i4 = 0;
                charAt2 = 0;
                i5 = 0;
            } else {
                int i36 = i33 + 1;
                int charAt19 = zzcr.charAt(i33);
                if (charAt19 >= 55296) {
                    int i37 = charAt19 & 8191;
                    int i38 = 13;
                    while (true) {
                        i14 = i36 + 1;
                        charAt10 = zzcr.charAt(i36);
                        if (charAt10 < 55296) {
                            break;
                        }
                        i37 |= (charAt10 & 8191) << i38;
                        i38 += 13;
                        i36 = i14;
                    }
                    charAt19 = i37 | (charAt10 << i38);
                    i36 = i14;
                }
                int i39 = i36 + 1;
                int charAt20 = zzcr.charAt(i36);
                if (charAt20 >= 55296) {
                    int i40 = charAt20 & 8191;
                    int i41 = 13;
                    while (true) {
                        i13 = i39 + 1;
                        charAt9 = zzcr.charAt(i39);
                        if (charAt9 < 55296) {
                            break;
                        }
                        i40 |= (charAt9 & 8191) << i41;
                        i41 += 13;
                        i39 = i13;
                    }
                    charAt20 = i40 | (charAt9 << i41);
                    i39 = i13;
                }
                int i42 = i39 + 1;
                charAt = zzcr.charAt(i39);
                if (charAt >= 55296) {
                    int i43 = charAt & 8191;
                    int i44 = 13;
                    while (true) {
                        i12 = i42 + 1;
                        charAt8 = zzcr.charAt(i42);
                        if (charAt8 < 55296) {
                            break;
                        }
                        i43 |= (charAt8 & 8191) << i44;
                        i44 += 13;
                        i42 = i12;
                    }
                    charAt = i43 | (charAt8 << i44);
                    i42 = i12;
                }
                int i45 = i42 + 1;
                int charAt21 = zzcr.charAt(i42);
                if (charAt21 >= 55296) {
                    int i46 = charAt21 & 8191;
                    int i47 = 13;
                    while (true) {
                        i11 = i45 + 1;
                        charAt7 = zzcr.charAt(i45);
                        if (charAt7 < 55296) {
                            break;
                        }
                        i46 |= (charAt7 & 8191) << i47;
                        i47 += 13;
                        i45 = i11;
                    }
                    charAt21 = i46 | (charAt7 << i47);
                    i45 = i11;
                }
                int i48 = i45 + 1;
                charAt2 = zzcr.charAt(i45);
                if (charAt2 >= 55296) {
                    int i49 = charAt2 & 8191;
                    int i50 = 13;
                    while (true) {
                        i10 = i48 + 1;
                        charAt6 = zzcr.charAt(i48);
                        if (charAt6 < 55296) {
                            break;
                        }
                        i49 |= (charAt6 & 8191) << i50;
                        i50 += 13;
                        i48 = i10;
                    }
                    charAt2 = i49 | (charAt6 << i50);
                    i48 = i10;
                }
                int i51 = i48 + 1;
                int charAt22 = zzcr.charAt(i48);
                if (charAt22 >= 55296) {
                    int i52 = charAt22 & 8191;
                    int i53 = 13;
                    while (true) {
                        i9 = i51 + 1;
                        charAt5 = zzcr.charAt(i51);
                        if (charAt5 < 55296) {
                            break;
                        }
                        i52 |= (charAt5 & 8191) << i53;
                        i53 += 13;
                        i51 = i9;
                    }
                    charAt22 = i52 | (charAt5 << i53);
                    i51 = i9;
                }
                int i54 = i51 + 1;
                int charAt23 = zzcr.charAt(i51);
                if (charAt23 >= 55296) {
                    int i55 = charAt23 & 8191;
                    int i56 = i54;
                    int i57 = 13;
                    while (true) {
                        i8 = i56 + 1;
                        charAt4 = zzcr.charAt(i56);
                        if (charAt4 < 55296) {
                            break;
                        }
                        i55 |= (charAt4 & 8191) << i57;
                        i57 += 13;
                        i56 = i8;
                    }
                    charAt23 = i55 | (charAt4 << i57);
                    i2 = i8;
                } else {
                    i2 = i54;
                }
                int i58 = i2 + 1;
                int charAt24 = zzcr.charAt(i2);
                if (charAt24 >= 55296) {
                    int i59 = charAt24 & 8191;
                    int i60 = i58;
                    int i61 = 13;
                    while (true) {
                        i7 = i60 + 1;
                        charAt3 = zzcr.charAt(i60);
                        if (charAt3 < 55296) {
                            break;
                        }
                        i59 |= (charAt3 & 8191) << i61;
                        i61 += 13;
                        i60 = i7;
                    }
                    charAt24 = i59 | (charAt3 << i61);
                    i58 = i7;
                }
                int[] iArr2 = new int[charAt24 + charAt22 + charAt23];
                int i62 = (charAt19 << 1) + charAt20;
                i3 = charAt21;
                i4 = i62;
                i5 = charAt24;
                i29 = charAt19;
                i33 = i58;
                int i63 = charAt22;
                iArr = iArr2;
                i6 = i63;
            }
            Unsafe unsafe = zzkr;
            Object[] zzcs = zzcxVar2.zzcs();
            Class<?> cls3 = zzcxVar2.zzcl().getClass();
            int i64 = i33;
            int[] iArr3 = new int[charAt2 * 3];
            Object[] objArr = new Object[charAt2 << 1];
            int i65 = i5 + i6;
            int i66 = i5;
            int i67 = i64;
            int i68 = i65;
            int i69 = 0;
            int i70 = 0;
            while (i67 < length) {
                int i71 = i67 + 1;
                int charAt25 = zzcr.charAt(i67);
                int i72 = length;
                if (charAt25 >= 55296) {
                    int i73 = charAt25 & 8191;
                    int i74 = i71;
                    int i75 = 13;
                    while (true) {
                        i27 = i74 + 1;
                        charAt14 = zzcr.charAt(i74);
                        i15 = i5;
                        if (charAt14 < 55296) {
                            break;
                        }
                        i73 |= (charAt14 & 8191) << i75;
                        i75 += 13;
                        i74 = i27;
                        i5 = i15;
                    }
                    charAt25 = i73 | (charAt14 << i75);
                    i16 = i27;
                } else {
                    i15 = i5;
                    i16 = i71;
                }
                int i76 = i16 + 1;
                int charAt26 = zzcr.charAt(i16);
                if (charAt26 >= 55296) {
                    int i77 = charAt26 & 8191;
                    int i78 = i76;
                    int i79 = 13;
                    while (true) {
                        i26 = i78 + 1;
                        charAt13 = zzcr.charAt(i78);
                        z = z2;
                        if (charAt13 < 55296) {
                            break;
                        }
                        i77 |= (charAt13 & 8191) << i79;
                        i79 += 13;
                        i78 = i26;
                        z2 = z;
                    }
                    charAt26 = i77 | (charAt13 << i79);
                    i17 = i26;
                } else {
                    z = z2;
                    i17 = i76;
                }
                int i80 = charAt26 & 255;
                int i81 = i3;
                if ((charAt26 & 1024) != 0) {
                    iArr[i69] = i70;
                    i69++;
                }
                int i82 = charAt;
                if (i80 >= 51) {
                    int i83 = i17 + 1;
                    int charAt27 = zzcr.charAt(i17);
                    char c = 55296;
                    if (charAt27 >= 55296) {
                        int i84 = charAt27 & 8191;
                        int i85 = 13;
                        while (true) {
                            i25 = i83 + 1;
                            charAt12 = zzcr.charAt(i83);
                            if (charAt12 < c) {
                                break;
                            }
                            i84 |= (charAt12 & 8191) << i85;
                            i85 += 13;
                            i83 = i25;
                            c = 55296;
                        }
                        charAt27 = i84 | (charAt12 << i85);
                        i83 = i25;
                    }
                    int i86 = i80 - 51;
                    int i87 = i83;
                    if (i86 == 9 || i86 == 17) {
                        objArr[((i70 / 3) << 1) + 1] = zzcs[i4];
                        i4++;
                    } else if (i86 == 12 && (charAt17 & 1) == 1) {
                        objArr[((i70 / 3) << 1) + 1] = zzcs[i4];
                        i4++;
                    }
                    int i88 = charAt27 << 1;
                    Object obj = zzcs[i88];
                    if (obj instanceof Field) {
                        zzb2 = (Field) obj;
                    } else {
                        zzb2 = zzb(cls3, (String) obj);
                        zzcs[i88] = zzb2;
                    }
                    zzcxVar = zzcxVar2;
                    String str2 = zzcr;
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zzb2);
                    int i89 = i88 + 1;
                    Object obj2 = zzcs[i89];
                    if (obj2 instanceof Field) {
                        zzb3 = (Field) obj2;
                    } else {
                        zzb3 = zzb(cls3, (String) obj2);
                        zzcs[i89] = zzb3;
                    }
                    cls2 = cls3;
                    i19 = i4;
                    i17 = i87;
                    str = str2;
                    i22 = 0;
                    i21 = (int) unsafe.objectFieldOffset(zzb3);
                    i20 = i29;
                } else {
                    zzcxVar = zzcxVar2;
                    String str3 = zzcr;
                    int i90 = i4 + 1;
                    Field zzb4 = zzb(cls3, (String) zzcs[i4]);
                    if (i80 == 9 || i80 == 17) {
                        i18 = 1;
                        objArr[((i70 / 3) << 1) + 1] = zzb4.getType();
                    } else {
                        if (i80 == 27 || i80 == 49) {
                            i18 = 1;
                            i24 = i90 + 1;
                            objArr[((i70 / 3) << 1) + 1] = zzcs[i90];
                        } else if (i80 == 12 || i80 == 30 || i80 == 44) {
                            i18 = 1;
                            if ((charAt17 & 1) == 1) {
                                i24 = i90 + 1;
                                objArr[((i70 / 3) << 1) + 1] = zzcs[i90];
                            }
                        } else {
                            if (i80 == 50) {
                                int i91 = i66 + 1;
                                iArr[i66] = i70;
                                int i92 = (i70 / 3) << 1;
                                int i93 = i90 + 1;
                                objArr[i92] = zzcs[i90];
                                if ((charAt26 & 2048) != 0) {
                                    i90 = i93 + 1;
                                    objArr[i92 + 1] = zzcs[i93];
                                    i66 = i91;
                                } else {
                                    i90 = i93;
                                    i18 = 1;
                                    i66 = i91;
                                }
                            }
                            i18 = 1;
                        }
                        i90 = i24;
                    }
                    objectFieldOffset = (int) unsafe.objectFieldOffset(zzb4);
                    if ((charAt17 & 1) != i18 || i80 > 17) {
                        i19 = i90;
                        i20 = i29;
                        cls2 = cls3;
                        str = str3;
                        i21 = 0;
                        i22 = 0;
                    } else {
                        int i94 = i17 + 1;
                        str = str3;
                        int charAt28 = str.charAt(i17);
                        if (charAt28 >= 55296) {
                            int i95 = charAt28 & 8191;
                            int i96 = 13;
                            while (true) {
                                i23 = i94 + 1;
                                charAt11 = str.charAt(i94);
                                if (charAt11 < 55296) {
                                    break;
                                }
                                i95 |= (charAt11 & 8191) << i96;
                                i96 += 13;
                                i94 = i23;
                            }
                            charAt28 = i95 | (charAt11 << i96);
                            i94 = i23;
                        }
                        int i97 = (i29 << 1) + (charAt28 / 32);
                        Object obj3 = zzcs[i97];
                        i19 = i90;
                        if (obj3 instanceof Field) {
                            zzb = (Field) obj3;
                        } else {
                            zzb = zzb(cls3, (String) obj3);
                            zzcs[i97] = zzb;
                        }
                        i20 = i29;
                        cls2 = cls3;
                        i21 = (int) unsafe.objectFieldOffset(zzb);
                        i22 = charAt28 % 32;
                        i17 = i94;
                    }
                    if (i80 >= 18 && i80 <= 49) {
                        iArr[i68] = objectFieldOffset;
                        i68++;
                    }
                }
                int i98 = i70 + 1;
                iArr3[i70] = charAt25;
                int i99 = i98 + 1;
                iArr3[i98] = objectFieldOffset | ((charAt26 & 256) != 0 ? 268435456 : 0) | ((charAt26 & 512) != 0 ? 536870912 : 0) | (i80 << 20);
                i70 = i99 + 1;
                iArr3[i99] = (i22 << 20) | i21;
                i29 = i20;
                zzcr = str;
                i67 = i17;
                cls3 = cls2;
                i3 = i81;
                length = i72;
                i5 = i15;
                z2 = z;
                charAt = i82;
                i4 = i19;
                zzcxVar2 = zzcxVar;
            }
            return new zzco<>(iArr3, objArr, charAt, i3, zzcxVar2.zzcl(), z2, false, iArr, i5, i65, zzcsVar, zzbuVar, zzdsVar, zzarVar, zzcdVar);
        }
        ((zzdl) zzciVar).zzcj();
        int i100 = zzbc.zze.zziu;
        throw new NoSuchMethodError();
    }

    private static Field zzb(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (NoSuchFieldException unused) {
            Field[] declaredFields = cls.getDeclaredFields();
            for (Field field : declaredFields) {
                if (str.equals(field.getName())) {
                    return field;
                }
            }
            String name = cls.getName();
            String arrays = Arrays.toString(declaredFields);
            StringBuilder sb = new StringBuilder(String.valueOf(str).length() + 40 + String.valueOf(name).length() + String.valueOf(arrays).length());
            sb.append("Field ");
            sb.append(str);
            sb.append(" for ");
            sb.append(name);
            sb.append(" not found. Known fields are ");
            sb.append(arrays);
            throw new RuntimeException(sb.toString());
        }
    }

    @Override // com.google.android.gms.internal.places.zzda
    public final T newInstance() {
        return (T) this.zzle.newInstance(this.zzkw);
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x006a, code lost:
    
        if (com.google.android.gms.internal.places.zzdc.zze(com.google.android.gms.internal.places.zzdy.zzp(r10, r6), com.google.android.gms.internal.places.zzdy.zzp(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x007e, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzl(r10, r6) == com.google.android.gms.internal.places.zzdy.zzl(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x0090, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzk(r10, r6) == com.google.android.gms.internal.places.zzdy.zzk(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x00a4, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzl(r10, r6) == com.google.android.gms.internal.places.zzdy.zzl(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x00b6, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzk(r10, r6) == com.google.android.gms.internal.places.zzdy.zzk(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00c8, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzk(r10, r6) == com.google.android.gms.internal.places.zzdy.zzk(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00da, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzk(r10, r6) == com.google.android.gms.internal.places.zzdy.zzk(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00f0, code lost:
    
        if (com.google.android.gms.internal.places.zzdc.zze(com.google.android.gms.internal.places.zzdy.zzp(r10, r6), com.google.android.gms.internal.places.zzdy.zzp(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x0106, code lost:
    
        if (com.google.android.gms.internal.places.zzdc.zze(com.google.android.gms.internal.places.zzdy.zzp(r10, r6), com.google.android.gms.internal.places.zzdy.zzp(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x011c, code lost:
    
        if (com.google.android.gms.internal.places.zzdc.zze(com.google.android.gms.internal.places.zzdy.zzp(r10, r6), com.google.android.gms.internal.places.zzdy.zzp(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x012e, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzm(r10, r6) == com.google.android.gms.internal.places.zzdy.zzm(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x0140, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzk(r10, r6) == com.google.android.gms.internal.places.zzdy.zzk(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0154, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzl(r10, r6) == com.google.android.gms.internal.places.zzdy.zzl(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0165, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzk(r10, r6) == com.google.android.gms.internal.places.zzdy.zzk(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0178, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzl(r10, r6) == com.google.android.gms.internal.places.zzdy.zzl(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x018b, code lost:
    
        if (com.google.android.gms.internal.places.zzdy.zzl(r10, r6) == com.google.android.gms.internal.places.zzdy.zzl(r11, r6)) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x01a4, code lost:
    
        if (java.lang.Float.floatToIntBits(com.google.android.gms.internal.places.zzdy.zzn(r10, r6)) == java.lang.Float.floatToIntBits(com.google.android.gms.internal.places.zzdy.zzn(r11, r6))) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x01bf, code lost:
    
        if (java.lang.Double.doubleToLongBits(com.google.android.gms.internal.places.zzdy.zzo(r10, r6)) == java.lang.Double.doubleToLongBits(com.google.android.gms.internal.places.zzdy.zzo(r11, r6))) goto L105;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0038, code lost:
    
        if (com.google.android.gms.internal.places.zzdc.zze(com.google.android.gms.internal.places.zzdy.zzp(r10, r6), com.google.android.gms.internal.places.zzdy.zzp(r11, r6)) != false) goto L105;
     */
    /* JADX WARN: Removed duplicated region for block: B:86:0x01c5 A[LOOP:0: B:2:0x0005->B:86:0x01c5, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:87:0x01c4 A[SYNTHETIC] */
    @Override // com.google.android.gms.internal.places.zzda
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final boolean equals(T r10, T r11) {
        /*
            Method dump skipped, instructions count: 640
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.equals(java.lang.Object, java.lang.Object):boolean");
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001b. Please report as an issue. */
    @Override // com.google.android.gms.internal.places.zzda
    public final int hashCode(T t) {
        int i;
        int zzl;
        int length = this.zzks.length;
        int i2 = 0;
        for (int i3 = 0; i3 < length; i3 += 3) {
            int zzai = zzai(i3);
            int i4 = this.zzks[i3];
            long j = 1048575 & zzai;
            int i5 = 37;
            switch ((zzai & 267386880) >>> 20) {
                case 0:
                    i = i2 * 53;
                    zzl = zzbd.zzl(Double.doubleToLongBits(zzdy.zzo(t, j)));
                    i2 = i + zzl;
                    break;
                case 1:
                    i = i2 * 53;
                    zzl = Float.floatToIntBits(zzdy.zzn(t, j));
                    i2 = i + zzl;
                    break;
                case 2:
                    i = i2 * 53;
                    zzl = zzbd.zzl(zzdy.zzl(t, j));
                    i2 = i + zzl;
                    break;
                case 3:
                    i = i2 * 53;
                    zzl = zzbd.zzl(zzdy.zzl(t, j));
                    i2 = i + zzl;
                    break;
                case 4:
                    i = i2 * 53;
                    zzl = zzdy.zzk(t, j);
                    i2 = i + zzl;
                    break;
                case 5:
                    i = i2 * 53;
                    zzl = zzbd.zzl(zzdy.zzl(t, j));
                    i2 = i + zzl;
                    break;
                case 6:
                    i = i2 * 53;
                    zzl = zzdy.zzk(t, j);
                    i2 = i + zzl;
                    break;
                case 7:
                    i = i2 * 53;
                    zzl = zzbd.zze(zzdy.zzm(t, j));
                    i2 = i + zzl;
                    break;
                case 8:
                    i = i2 * 53;
                    zzl = ((String) zzdy.zzp(t, j)).hashCode();
                    i2 = i + zzl;
                    break;
                case 9:
                    Object zzp = zzdy.zzp(t, j);
                    if (zzp != null) {
                        i5 = zzp.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
                    break;
                case 10:
                    i = i2 * 53;
                    zzl = zzdy.zzp(t, j).hashCode();
                    i2 = i + zzl;
                    break;
                case 11:
                    i = i2 * 53;
                    zzl = zzdy.zzk(t, j);
                    i2 = i + zzl;
                    break;
                case 12:
                    i = i2 * 53;
                    zzl = zzdy.zzk(t, j);
                    i2 = i + zzl;
                    break;
                case 13:
                    i = i2 * 53;
                    zzl = zzdy.zzk(t, j);
                    i2 = i + zzl;
                    break;
                case 14:
                    i = i2 * 53;
                    zzl = zzbd.zzl(zzdy.zzl(t, j));
                    i2 = i + zzl;
                    break;
                case 15:
                    i = i2 * 53;
                    zzl = zzdy.zzk(t, j);
                    i2 = i + zzl;
                    break;
                case 16:
                    i = i2 * 53;
                    zzl = zzbd.zzl(zzdy.zzl(t, j));
                    i2 = i + zzl;
                    break;
                case 17:
                    Object zzp2 = zzdy.zzp(t, j);
                    if (zzp2 != null) {
                        i5 = zzp2.hashCode();
                    }
                    i2 = (i2 * 53) + i5;
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
                    i = i2 * 53;
                    zzl = zzdy.zzp(t, j).hashCode();
                    i2 = i + zzl;
                    break;
                case 50:
                    i = i2 * 53;
                    zzl = zzdy.zzp(t, j).hashCode();
                    i2 = i + zzl;
                    break;
                case 51:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzbd.zzl(Double.doubleToLongBits(zzf(t, j)));
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 52:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = Float.floatToIntBits(zzg(t, j));
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 53:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzbd.zzl(zzi(t, j));
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 54:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzbd.zzl(zzi(t, j));
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 55:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzh(t, j);
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 56:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzbd.zzl(zzi(t, j));
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 57:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzh(t, j);
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 58:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzbd.zze(zzj(t, j));
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 59:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = ((String) zzdy.zzp(t, j)).hashCode();
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 60:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzdy.zzp(t, j).hashCode();
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 61:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzdy.zzp(t, j).hashCode();
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 62:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzh(t, j);
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 63:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzh(t, j);
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 64:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzh(t, j);
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 65:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzbd.zzl(zzi(t, j));
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 66:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzh(t, j);
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 67:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzbd.zzl(zzi(t, j));
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
                case 68:
                    if (zzb((zzco<T>) t, i4, i3)) {
                        i = i2 * 53;
                        zzl = zzdy.zzp(t, j).hashCode();
                        i2 = i + zzl;
                        break;
                    } else {
                        break;
                    }
            }
        }
        int hashCode = (i2 * 53) + this.zzlg.zzr(t).hashCode();
        return this.zzkx ? (hashCode * 53) + this.zzlh.zzb(t).hashCode() : hashCode;
    }

    @Override // com.google.android.gms.internal.places.zzda
    public final void zzd(T t, T t2) {
        t2.getClass();
        for (int i = 0; i < this.zzks.length; i += 3) {
            int zzai = zzai(i);
            long j = 1048575 & zzai;
            int i2 = this.zzks[i];
            switch ((zzai & 267386880) >>> 20) {
                case 0:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb(t, j, zzdy.zzo(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzn(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 7:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb(t, j, zzdy.zzm(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 8:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb(t, j, zzdy.zzp(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 9:
                    zzb(t, t2, i);
                    break;
                case 10:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb(t, j, zzdy.zzp(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 11:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 12:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 13:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 14:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 15:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzk(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 16:
                    if (zzb((zzco<T>) t2, i)) {
                        zzdy.zzb((Object) t, j, zzdy.zzl(t2, j));
                        zzc((zzco<T>) t, i);
                        break;
                    } else {
                        break;
                    }
                case 17:
                    zzb(t, t2, i);
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
                    this.zzlf.zzb(t, t2, j);
                    break;
                case 50:
                    zzdc.zzb(this.zzli, t, t2, j);
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
                    if (zzb((zzco<T>) t2, i2, i)) {
                        zzdy.zzb(t, j, zzdy.zzp(t2, j));
                        zzc((zzco<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 60:
                    zzc(t, t2, i);
                    break;
                case 61:
                case 62:
                case 63:
                case 64:
                case 65:
                case 66:
                case 67:
                    if (zzb((zzco<T>) t2, i2, i)) {
                        zzdy.zzb(t, j, zzdy.zzp(t2, j));
                        zzc((zzco<T>) t, i2, i);
                        break;
                    } else {
                        break;
                    }
                case 68:
                    zzc(t, t2, i);
                    break;
            }
        }
        if (this.zzkz) {
            return;
        }
        zzdc.zzb(this.zzlg, t, t2);
        if (this.zzkx) {
            zzdc.zzb(this.zzlh, t, t2);
        }
    }

    private final void zzb(T t, T t2, int i) {
        long zzai = zzai(i) & 1048575;
        if (zzb((zzco<T>) t2, i)) {
            Object zzp = zzdy.zzp(t, zzai);
            Object zzp2 = zzdy.zzp(t2, zzai);
            if (zzp != null && zzp2 != null) {
                zzdy.zzb(t, zzai, zzbd.zzb(zzp, zzp2));
                zzc((zzco<T>) t, i);
            } else if (zzp2 != null) {
                zzdy.zzb(t, zzai, zzp2);
                zzc((zzco<T>) t, i);
            }
        }
    }

    private final void zzc(T t, T t2, int i) {
        int zzai = zzai(i);
        int i2 = this.zzks[i];
        long j = zzai & 1048575;
        if (zzb((zzco<T>) t2, i2, i)) {
            Object zzp = zzdy.zzp(t, j);
            Object zzp2 = zzdy.zzp(t2, j);
            if (zzp != null && zzp2 != null) {
                zzdy.zzb(t, j, zzbd.zzb(zzp, zzp2));
                zzc((zzco<T>) t, i2, i);
            } else if (zzp2 != null) {
                zzdy.zzb(t, j, zzp2);
                zzc((zzco<T>) t, i2, i);
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0042. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:299:0x0545. Please report as an issue. */
    @Override // com.google.android.gms.internal.places.zzda
    public final int zzn(T t) {
        int i;
        int i2;
        long j;
        int zze;
        int zzc;
        int zzl;
        int zzw;
        int zzm;
        int zzr;
        int zzt;
        int zzc2;
        int zzm2;
        int zzr2;
        int zzt2;
        int i3 = 267386880;
        int i4 = 1;
        if (this.zzkz) {
            Unsafe unsafe = zzkr;
            int i5 = 0;
            int i6 = 0;
            while (i5 < this.zzks.length) {
                int zzai = zzai(i5);
                int i7 = (zzai & i3) >>> 20;
                int i8 = this.zzks[i5];
                long j2 = zzai & 1048575;
                int i9 = (i7 < zzaw.DOUBLE_LIST_PACKED.id() || i7 > zzaw.SINT64_LIST_PACKED.id()) ? 0 : this.zzks[i5 + 2] & 1048575;
                switch (i7) {
                    case 0:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzc(i8, Utils.DOUBLE_EPSILON);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 1:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzc(i8, 0.0f);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 2:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zze(i8, zzdy.zzl(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 3:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzf(i8, zzdy.zzl(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 4:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzh(i8, zzdy.zzk(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 5:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzh(i8, 0L);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 6:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzk(i8, 0);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 7:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzd(i8, true);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 8:
                        if (zzb((zzco<T>) t, i5)) {
                            Object zzp = zzdy.zzp(t, j2);
                            if (zzp instanceof zzw) {
                                zzc2 = zzaj.zzd(i8, (zzw) zzp);
                            } else {
                                zzc2 = zzaj.zzc(i8, (String) zzp);
                            }
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 9:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzdc.zzd(i8, zzdy.zzp(t, j2), zzaf(i5));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 10:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzd(i8, (zzw) zzdy.zzp(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 11:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzi(i8, zzdy.zzk(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 12:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzm(i8, zzdy.zzk(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 13:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzl(i8, 0);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 14:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzi(i8, 0L);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 15:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzj(i8, zzdy.zzk(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 16:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzg(i8, zzdy.zzl(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 17:
                        if (zzb((zzco<T>) t, i5)) {
                            zzc2 = zzaj.zzd(i8, (zzck) zzdy.zzp(t, j2), zzaf(i5));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 18:
                        zzc2 = zzdc.zzx(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 19:
                        zzc2 = zzdc.zzw(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 20:
                        zzc2 = zzdc.zzp(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 21:
                        zzc2 = zzdc.zzq(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 22:
                        zzc2 = zzdc.zzt(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 23:
                        zzc2 = zzdc.zzx(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 24:
                        zzc2 = zzdc.zzw(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 25:
                        zzc2 = zzdc.zzy(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 26:
                        zzc2 = zzdc.zzd(i8, zze(t, j2));
                        i6 += zzc2;
                        break;
                    case 27:
                        zzc2 = zzdc.zzd(i8, zze(t, j2), zzaf(i5));
                        i6 += zzc2;
                        break;
                    case 28:
                        zzc2 = zzdc.zze(i8, (List<zzw>) zze(t, j2));
                        i6 += zzc2;
                        break;
                    case 29:
                        zzc2 = zzdc.zzu(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 30:
                        zzc2 = zzdc.zzs(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 31:
                        zzc2 = zzdc.zzw(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 32:
                        zzc2 = zzdc.zzx(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 33:
                        zzc2 = zzdc.zzv(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 34:
                        zzc2 = zzdc.zzr(i8, zze(t, j2), false);
                        i6 += zzc2;
                        break;
                    case 35:
                        zzm2 = zzdc.zzm((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 36:
                        zzm2 = zzdc.zzl((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 37:
                        zzm2 = zzdc.zze((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 38:
                        zzm2 = zzdc.zzf((List<Long>) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 39:
                        zzm2 = zzdc.zzi((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 40:
                        zzm2 = zzdc.zzm((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 41:
                        zzm2 = zzdc.zzl((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 42:
                        zzm2 = zzdc.zzn((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 43:
                        zzm2 = zzdc.zzj((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 44:
                        zzm2 = zzdc.zzh((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 45:
                        zzm2 = zzdc.zzl((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 46:
                        zzm2 = zzdc.zzm((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 47:
                        zzm2 = zzdc.zzk((List) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 48:
                        zzm2 = zzdc.zzg((List<Long>) unsafe.getObject(t, j2));
                        if (zzm2 <= 0) {
                            break;
                        } else {
                            if (this.zzla) {
                                unsafe.putInt(t, i9, zzm2);
                            }
                            zzr2 = zzaj.zzr(i8);
                            zzt2 = zzaj.zzt(zzm2);
                            zzc2 = zzr2 + zzt2 + zzm2;
                            i6 += zzc2;
                            break;
                        }
                    case 49:
                        zzc2 = zzdc.zze(i8, zze(t, j2), zzaf(i5));
                        i6 += zzc2;
                        break;
                    case 50:
                        zzc2 = this.zzli.zzc(i8, zzdy.zzp(t, j2), zzag(i5));
                        i6 += zzc2;
                        break;
                    case 51:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzc(i8, Utils.DOUBLE_EPSILON);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 52:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzc(i8, 0.0f);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 53:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zze(i8, zzi(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 54:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzf(i8, zzi(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 55:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzh(i8, zzh(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 56:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzh(i8, 0L);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 57:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzk(i8, 0);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 58:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzd(i8, true);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 59:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            Object zzp2 = zzdy.zzp(t, j2);
                            if (zzp2 instanceof zzw) {
                                zzc2 = zzaj.zzd(i8, (zzw) zzp2);
                            } else {
                                zzc2 = zzaj.zzc(i8, (String) zzp2);
                            }
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 60:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzdc.zzd(i8, zzdy.zzp(t, j2), zzaf(i5));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 61:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzd(i8, (zzw) zzdy.zzp(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 62:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzi(i8, zzh(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 63:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzm(i8, zzh(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 64:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzl(i8, 0);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 65:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzi(i8, 0L);
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 66:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzj(i8, zzh(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 67:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzg(i8, zzi(t, j2));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                    case 68:
                        if (zzb((zzco<T>) t, i8, i5)) {
                            zzc2 = zzaj.zzd(i8, (zzck) zzdy.zzp(t, j2), zzaf(i5));
                            i6 += zzc2;
                            break;
                        } else {
                            break;
                        }
                }
                i5 += 3;
                i3 = 267386880;
            }
            return i6 + zzb(this.zzlg, t);
        }
        Unsafe unsafe2 = zzkr;
        int i10 = -1;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        while (i11 < this.zzks.length) {
            int zzai2 = zzai(i11);
            int[] iArr = this.zzks;
            int i14 = iArr[i11];
            int i15 = (zzai2 & 267386880) >>> 20;
            if (i15 <= 17) {
                int i16 = iArr[i11 + 2];
                int i17 = i16 & 1048575;
                i2 = i4 << (i16 >>> 20);
                if (i17 != i10) {
                    i13 = unsafe2.getInt(t, i17);
                    i10 = i17;
                }
                i = i16;
            } else {
                i = (!this.zzla || i15 < zzaw.DOUBLE_LIST_PACKED.id() || i15 > zzaw.SINT64_LIST_PACKED.id()) ? 0 : this.zzks[i11 + 2] & 1048575;
                i2 = 0;
            }
            long j3 = zzai2 & 1048575;
            switch (i15) {
                case 0:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        i12 += zzaj.zzc(i14, Utils.DOUBLE_EPSILON);
                        break;
                    }
                    break;
                case 1:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        i12 += zzaj.zzc(i14, 0.0f);
                        break;
                    }
                case 2:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        zze = zzaj.zze(i14, unsafe2.getLong(t, j3));
                        i12 += zze;
                    }
                    break;
                case 3:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        zze = zzaj.zzf(i14, unsafe2.getLong(t, j3));
                        i12 += zze;
                    }
                    break;
                case 4:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        zze = zzaj.zzh(i14, unsafe2.getInt(t, j3));
                        i12 += zze;
                    }
                    break;
                case 5:
                    j = 0;
                    if ((i13 & i2) != 0) {
                        zze = zzaj.zzh(i14, 0L);
                        i12 += zze;
                    }
                    break;
                case 6:
                    if ((i13 & i2) != 0) {
                        i12 += zzaj.zzk(i14, 0);
                        j = 0;
                        break;
                    }
                    j = 0;
                case 7:
                    if ((i13 & i2) != 0) {
                        i12 += zzaj.zzd(i14, true);
                        j = 0;
                        break;
                    }
                    j = 0;
                case 8:
                    if ((i13 & i2) != 0) {
                        Object object = unsafe2.getObject(t, j3);
                        if (object instanceof zzw) {
                            zzc = zzaj.zzd(i14, (zzw) object);
                        } else {
                            zzc = zzaj.zzc(i14, (String) object);
                        }
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 9:
                    if ((i13 & i2) != 0) {
                        zzc = zzdc.zzd(i14, unsafe2.getObject(t, j3), zzaf(i11));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 10:
                    if ((i13 & i2) != 0) {
                        zzc = zzaj.zzd(i14, (zzw) unsafe2.getObject(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 11:
                    if ((i13 & i2) != 0) {
                        zzc = zzaj.zzi(i14, unsafe2.getInt(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 12:
                    if ((i13 & i2) != 0) {
                        zzc = zzaj.zzm(i14, unsafe2.getInt(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 13:
                    if ((i13 & i2) != 0) {
                        zzl = zzaj.zzl(i14, 0);
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 14:
                    if ((i13 & i2) != 0) {
                        zzc = zzaj.zzi(i14, 0L);
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 15:
                    if ((i13 & i2) != 0) {
                        zzc = zzaj.zzj(i14, unsafe2.getInt(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 16:
                    if ((i13 & i2) != 0) {
                        zzc = zzaj.zzg(i14, unsafe2.getLong(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 17:
                    if ((i13 & i2) != 0) {
                        zzc = zzaj.zzd(i14, (zzck) unsafe2.getObject(t, j3), zzaf(i11));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 18:
                    zzc = zzdc.zzx(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzc;
                    j = 0;
                    break;
                case 19:
                    zzw = zzdc.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 20:
                    zzw = zzdc.zzp(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 21:
                    zzw = zzdc.zzq(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 22:
                    zzw = zzdc.zzt(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 23:
                    zzw = zzdc.zzx(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 24:
                    zzw = zzdc.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 25:
                    zzw = zzdc.zzy(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 26:
                    zzc = zzdc.zzd(i14, (List) unsafe2.getObject(t, j3));
                    i12 += zzc;
                    j = 0;
                    break;
                case 27:
                    zzc = zzdc.zzd(i14, (List<?>) unsafe2.getObject(t, j3), zzaf(i11));
                    i12 += zzc;
                    j = 0;
                    break;
                case 28:
                    zzc = zzdc.zze(i14, (List<zzw>) unsafe2.getObject(t, j3));
                    i12 += zzc;
                    j = 0;
                    break;
                case 29:
                    zzc = zzdc.zzu(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzc;
                    j = 0;
                    break;
                case 30:
                    zzw = zzdc.zzs(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 31:
                    zzw = zzdc.zzw(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 32:
                    zzw = zzdc.zzx(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 33:
                    zzw = zzdc.zzv(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 34:
                    zzw = zzdc.zzr(i14, (List) unsafe2.getObject(t, j3), false);
                    i12 += zzw;
                    j = 0;
                    break;
                case 35:
                    zzm = zzdc.zzm((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 36:
                    zzm = zzdc.zzl((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 37:
                    zzm = zzdc.zze((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 38:
                    zzm = zzdc.zzf((List<Long>) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 39:
                    zzm = zzdc.zzi((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 40:
                    zzm = zzdc.zzm((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 41:
                    zzm = zzdc.zzl((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 42:
                    zzm = zzdc.zzn((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 43:
                    zzm = zzdc.zzj((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 44:
                    zzm = zzdc.zzh((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 45:
                    zzm = zzdc.zzl((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 46:
                    zzm = zzdc.zzm((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 47:
                    zzm = zzdc.zzk((List) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 48:
                    zzm = zzdc.zzg((List<Long>) unsafe2.getObject(t, j3));
                    if (zzm > 0) {
                        if (this.zzla) {
                            unsafe2.putInt(t, i, zzm);
                        }
                        zzr = zzaj.zzr(i14);
                        zzt = zzaj.zzt(zzm);
                        zzl = zzr + zzt + zzm;
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 49:
                    zzc = zzdc.zze(i14, (List) unsafe2.getObject(t, j3), zzaf(i11));
                    i12 += zzc;
                    j = 0;
                    break;
                case 50:
                    zzc = this.zzli.zzc(i14, unsafe2.getObject(t, j3), zzag(i11));
                    i12 += zzc;
                    j = 0;
                    break;
                case 51:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzc(i14, Utils.DOUBLE_EPSILON);
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 52:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzl = zzaj.zzc(i14, 0.0f);
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 53:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zze(i14, zzi(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 54:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzf(i14, zzi(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 55:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzh(i14, zzh(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 56:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzh(i14, 0L);
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 57:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzl = zzaj.zzk(i14, 0);
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 58:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzl = zzaj.zzd(i14, true);
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 59:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        Object object2 = unsafe2.getObject(t, j3);
                        if (object2 instanceof zzw) {
                            zzc = zzaj.zzd(i14, (zzw) object2);
                        } else {
                            zzc = zzaj.zzc(i14, (String) object2);
                        }
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 60:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzdc.zzd(i14, unsafe2.getObject(t, j3), zzaf(i11));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 61:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzd(i14, (zzw) unsafe2.getObject(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 62:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzi(i14, zzh(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 63:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzm(i14, zzh(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 64:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzl = zzaj.zzl(i14, 0);
                        i12 += zzl;
                    }
                    j = 0;
                    break;
                case 65:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzi(i14, 0L);
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 66:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzj(i14, zzh(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 67:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzg(i14, zzi(t, j3));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                case 68:
                    if (zzb((zzco<T>) t, i14, i11)) {
                        zzc = zzaj.zzd(i14, (zzck) unsafe2.getObject(t, j3), zzaf(i11));
                        i12 += zzc;
                    }
                    j = 0;
                    break;
                default:
                    j = 0;
                    break;
            }
            i11 += 3;
            i4 = 1;
        }
        int i18 = 0;
        int zzb = i12 + zzb(this.zzlg, t);
        if (!this.zzkx) {
            return zzb;
        }
        zzav<?> zzb2 = this.zzlh.zzb(t);
        for (int i19 = 0; i19 < zzb2.zzfj.zzcu(); i19++) {
            Map.Entry<?, Object> zzam = zzb2.zzfj.zzam(i19);
            i18 += zzav.zzc((zzax<?>) zzam.getKey(), zzam.getValue());
        }
        for (Map.Entry<?, Object> entry : zzb2.zzfj.zzcv()) {
            i18 += zzav.zzc((zzax<?>) entry.getKey(), entry.getValue());
        }
        return zzb + i18;
    }

    private static <UT, UB> int zzb(zzds<UT, UB> zzdsVar, T t) {
        return zzdsVar.zzn(zzdsVar.zzr(t));
    }

    private static List<?> zze(Object obj, long j) {
        return (List) zzdy.zzp(obj, j);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x003b  */
    /* JADX WARN: Removed duplicated region for block: B:277:0x0513  */
    /* JADX WARN: Removed duplicated region for block: B:299:0x0552  */
    /* JADX WARN: Removed duplicated region for block: B:566:0x0a2a  */
    @Override // com.google.android.gms.internal.places.zzda
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzb(T r14, com.google.android.gms.internal.places.zzel r15) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 2916
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzb(java.lang.Object, com.google.android.gms.internal.places.zzel):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:230:0x0496  */
    /* JADX WARN: Removed duplicated region for block: B:8:0x002f  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private final void zzc(T r19, com.google.android.gms.internal.places.zzel r20) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 1344
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzc(java.lang.Object, com.google.android.gms.internal.places.zzel):void");
    }

    private final <K, V> void zzb(zzel zzelVar, int i, Object obj, int i2) throws IOException {
        if (obj != null) {
            zzelVar.zzb(i, this.zzli.zzl(zzag(i2)), this.zzli.zzh(obj));
        }
    }

    private static <UT, UB> void zzb(zzds<UT, UB> zzdsVar, T t, zzel zzelVar) throws IOException {
        zzdsVar.zzb(zzdsVar.zzr(t), zzelVar);
    }

    private static zzdr zzo(Object obj) {
        zzbc zzbcVar = (zzbc) obj;
        zzdr zzdrVar = zzbcVar.zzih;
        if (zzdrVar != zzdr.zzdh()) {
            return zzdrVar;
        }
        zzdr zzdi = zzdr.zzdi();
        zzbcVar.zzih = zzdi;
        return zzdi;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0008. Please report as an issue. */
    private static int zzb(byte[] bArr, int i, int i2, zzef zzefVar, Class<?> cls, zzr zzrVar) throws IOException {
        switch (zzcn.zzfi[zzefVar.ordinal()]) {
            case 1:
                int zzc = zzs.zzc(bArr, i, zzrVar);
                zzrVar.zzeb = Boolean.valueOf(zzrVar.zzea != 0);
                return zzc;
            case 2:
                return zzs.zzf(bArr, i, zzrVar);
            case 3:
                zzrVar.zzeb = Double.valueOf(zzs.zzd(bArr, i));
                return i + 8;
            case 4:
            case 5:
                zzrVar.zzeb = Integer.valueOf(zzs.zzb(bArr, i));
                return i + 4;
            case 6:
            case 7:
                zzrVar.zzeb = Long.valueOf(zzs.zzc(bArr, i));
                return i + 8;
            case 8:
                zzrVar.zzeb = Float.valueOf(zzs.zze(bArr, i));
                return i + 4;
            case 9:
            case 10:
            case 11:
                int zzb = zzs.zzb(bArr, i, zzrVar);
                zzrVar.zzeb = Integer.valueOf(zzrVar.zzdz);
                return zzb;
            case 12:
            case 13:
                int zzc2 = zzs.zzc(bArr, i, zzrVar);
                zzrVar.zzeb = Long.valueOf(zzrVar.zzea);
                return zzc2;
            case 14:
                return zzs.zzb(zzcv.zzcq().zzf(cls), bArr, i, i2, zzrVar);
            case 15:
                int zzb2 = zzs.zzb(bArr, i, zzrVar);
                zzrVar.zzeb = Integer.valueOf(zzai.zzm(zzrVar.zzdz));
                return zzb2;
            case 16:
                int zzc3 = zzs.zzc(bArr, i, zzrVar);
                zzrVar.zzeb = Long.valueOf(zzai.zzb(zzrVar.zzea));
                return zzc3;
            case 17:
                return zzs.zze(bArr, i, zzrVar);
            default:
                throw new RuntimeException("unsupported field type.");
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:9:0x0037. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    private final int zzb(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, long j, int i7, long j2, zzr zzrVar) throws IOException {
        int zzb;
        int i8 = i;
        Unsafe unsafe = zzkr;
        zzbh zzbhVar = (zzbh) unsafe.getObject(t, j2);
        if (!zzbhVar.zzaa()) {
            int size = zzbhVar.size();
            zzbhVar = zzbhVar.zzh(size == 0 ? 10 : size << 1);
            unsafe.putObject(t, j2, zzbhVar);
        }
        switch (i7) {
            case 18:
            case 35:
                if (i5 == 2) {
                    zzao zzaoVar = (zzao) zzbhVar;
                    int zzb2 = zzs.zzb(bArr, i8, zzrVar);
                    int i9 = zzrVar.zzdz + zzb2;
                    while (zzb2 < i9) {
                        zzaoVar.zzd(zzs.zzd(bArr, zzb2));
                        zzb2 += 8;
                    }
                    if (zzb2 == i9) {
                        return zzb2;
                    }
                    throw zzbk.zzbp();
                }
                if (i5 == 1) {
                    zzao zzaoVar2 = (zzao) zzbhVar;
                    zzaoVar2.zzd(zzs.zzd(bArr, i));
                    while (true) {
                        int i10 = i8 + 8;
                        if (i10 >= i2) {
                            return i10;
                        }
                        i8 = zzs.zzb(bArr, i10, zzrVar);
                        if (i3 != zzrVar.zzdz) {
                            return i10;
                        }
                        zzaoVar2.zzd(zzs.zzd(bArr, i8));
                    }
                }
                return i8;
            case 19:
            case 36:
                if (i5 == 2) {
                    zzbb zzbbVar = (zzbb) zzbhVar;
                    int zzb3 = zzs.zzb(bArr, i8, zzrVar);
                    int i11 = zzrVar.zzdz + zzb3;
                    while (zzb3 < i11) {
                        zzbbVar.zzf(zzs.zze(bArr, zzb3));
                        zzb3 += 4;
                    }
                    if (zzb3 == i11) {
                        return zzb3;
                    }
                    throw zzbk.zzbp();
                }
                if (i5 == 5) {
                    zzbb zzbbVar2 = (zzbb) zzbhVar;
                    zzbbVar2.zzf(zzs.zze(bArr, i));
                    while (true) {
                        int i12 = i8 + 4;
                        if (i12 >= i2) {
                            return i12;
                        }
                        i8 = zzs.zzb(bArr, i12, zzrVar);
                        if (i3 != zzrVar.zzdz) {
                            return i12;
                        }
                        zzbbVar2.zzf(zzs.zze(bArr, i8));
                    }
                }
                return i8;
            case 20:
            case 21:
            case 37:
            case 38:
                if (i5 == 2) {
                    zzby zzbyVar = (zzby) zzbhVar;
                    int zzb4 = zzs.zzb(bArr, i8, zzrVar);
                    int i13 = zzrVar.zzdz + zzb4;
                    while (zzb4 < i13) {
                        zzb4 = zzs.zzc(bArr, zzb4, zzrVar);
                        zzbyVar.zzm(zzrVar.zzea);
                    }
                    if (zzb4 == i13) {
                        return zzb4;
                    }
                    throw zzbk.zzbp();
                }
                if (i5 == 0) {
                    zzby zzbyVar2 = (zzby) zzbhVar;
                    int zzc = zzs.zzc(bArr, i8, zzrVar);
                    zzbyVar2.zzm(zzrVar.zzea);
                    while (zzc < i2) {
                        int zzb5 = zzs.zzb(bArr, zzc, zzrVar);
                        if (i3 != zzrVar.zzdz) {
                            return zzc;
                        }
                        zzc = zzs.zzc(bArr, zzb5, zzrVar);
                        zzbyVar2.zzm(zzrVar.zzea);
                    }
                    return zzc;
                }
                return i8;
            case 22:
            case 29:
            case 39:
            case 43:
                if (i5 == 2) {
                    return zzs.zzb(bArr, i8, (zzbh<?>) zzbhVar, zzrVar);
                }
                if (i5 == 0) {
                    return zzs.zzb(i3, bArr, i, i2, (zzbh<?>) zzbhVar, zzrVar);
                }
                return i8;
            case 23:
            case 32:
            case 40:
            case 46:
                if (i5 == 2) {
                    zzby zzbyVar3 = (zzby) zzbhVar;
                    int zzb6 = zzs.zzb(bArr, i8, zzrVar);
                    int i14 = zzrVar.zzdz + zzb6;
                    while (zzb6 < i14) {
                        zzbyVar3.zzm(zzs.zzc(bArr, zzb6));
                        zzb6 += 8;
                    }
                    if (zzb6 == i14) {
                        return zzb6;
                    }
                    throw zzbk.zzbp();
                }
                if (i5 == 1) {
                    zzby zzbyVar4 = (zzby) zzbhVar;
                    zzbyVar4.zzm(zzs.zzc(bArr, i));
                    while (true) {
                        int i15 = i8 + 8;
                        if (i15 >= i2) {
                            return i15;
                        }
                        i8 = zzs.zzb(bArr, i15, zzrVar);
                        if (i3 != zzrVar.zzdz) {
                            return i15;
                        }
                        zzbyVar4.zzm(zzs.zzc(bArr, i8));
                    }
                }
                return i8;
            case 24:
            case 31:
            case 41:
            case 45:
                if (i5 == 2) {
                    zzbe zzbeVar = (zzbe) zzbhVar;
                    int zzb7 = zzs.zzb(bArr, i8, zzrVar);
                    int i16 = zzrVar.zzdz + zzb7;
                    while (zzb7 < i16) {
                        zzbeVar.zzac(zzs.zzb(bArr, zzb7));
                        zzb7 += 4;
                    }
                    if (zzb7 == i16) {
                        return zzb7;
                    }
                    throw zzbk.zzbp();
                }
                if (i5 == 5) {
                    zzbe zzbeVar2 = (zzbe) zzbhVar;
                    zzbeVar2.zzac(zzs.zzb(bArr, i));
                    while (true) {
                        int i17 = i8 + 4;
                        if (i17 >= i2) {
                            return i17;
                        }
                        i8 = zzs.zzb(bArr, i17, zzrVar);
                        if (i3 != zzrVar.zzdz) {
                            return i17;
                        }
                        zzbeVar2.zzac(zzs.zzb(bArr, i8));
                    }
                }
                return i8;
            case 25:
            case 42:
                if (i5 == 2) {
                    zzu zzuVar = (zzu) zzbhVar;
                    zzb = zzs.zzb(bArr, i8, zzrVar);
                    int i18 = zzrVar.zzdz + zzb;
                    while (zzb < i18) {
                        zzb = zzs.zzc(bArr, zzb, zzrVar);
                        zzuVar.addBoolean(zzrVar.zzea != 0);
                    }
                    if (zzb != i18) {
                        throw zzbk.zzbp();
                    }
                    return zzb;
                }
                if (i5 == 0) {
                    zzu zzuVar2 = (zzu) zzbhVar;
                    i8 = zzs.zzc(bArr, i8, zzrVar);
                    zzuVar2.addBoolean(zzrVar.zzea != 0);
                    while (i8 < i2) {
                        int zzb8 = zzs.zzb(bArr, i8, zzrVar);
                        if (i3 == zzrVar.zzdz) {
                            i8 = zzs.zzc(bArr, zzb8, zzrVar);
                            zzuVar2.addBoolean(zzrVar.zzea != 0);
                        }
                    }
                }
                return i8;
            case 26:
                if (i5 == 2) {
                    if ((j & 536870912) == 0) {
                        i8 = zzs.zzb(bArr, i8, zzrVar);
                        int i19 = zzrVar.zzdz;
                        if (i19 < 0) {
                            throw zzbk.zzbq();
                        }
                        if (i19 == 0) {
                            zzbhVar.add("");
                        } else {
                            zzbhVar.add(new String(bArr, i8, i19, zzbd.UTF_8));
                            i8 += i19;
                        }
                        while (i8 < i2) {
                            int zzb9 = zzs.zzb(bArr, i8, zzrVar);
                            if (i3 == zzrVar.zzdz) {
                                i8 = zzs.zzb(bArr, zzb9, zzrVar);
                                int i20 = zzrVar.zzdz;
                                if (i20 < 0) {
                                    throw zzbk.zzbq();
                                }
                                if (i20 == 0) {
                                    zzbhVar.add("");
                                } else {
                                    zzbhVar.add(new String(bArr, i8, i20, zzbd.UTF_8));
                                    i8 += i20;
                                }
                            }
                        }
                    } else {
                        i8 = zzs.zzb(bArr, i8, zzrVar);
                        int i21 = zzrVar.zzdz;
                        if (i21 < 0) {
                            throw zzbk.zzbq();
                        }
                        if (i21 == 0) {
                            zzbhVar.add("");
                        } else {
                            int i22 = i8 + i21;
                            if (!zzea.zzf(bArr, i8, i22)) {
                                throw zzbk.zzbu();
                            }
                            zzbhVar.add(new String(bArr, i8, i21, zzbd.UTF_8));
                            i8 = i22;
                        }
                        while (i8 < i2) {
                            int zzb10 = zzs.zzb(bArr, i8, zzrVar);
                            if (i3 == zzrVar.zzdz) {
                                i8 = zzs.zzb(bArr, zzb10, zzrVar);
                                int i23 = zzrVar.zzdz;
                                if (i23 < 0) {
                                    throw zzbk.zzbq();
                                }
                                if (i23 == 0) {
                                    zzbhVar.add("");
                                } else {
                                    int i24 = i8 + i23;
                                    if (!zzea.zzf(bArr, i8, i24)) {
                                        throw zzbk.zzbu();
                                    }
                                    zzbhVar.add(new String(bArr, i8, i23, zzbd.UTF_8));
                                    i8 = i24;
                                }
                            }
                        }
                    }
                }
                return i8;
            case 27:
                if (i5 == 2) {
                    return zzs.zzb(zzaf(i6), i3, bArr, i, i2, zzbhVar, zzrVar);
                }
                return i8;
            case 28:
                if (i5 == 2) {
                    int zzb11 = zzs.zzb(bArr, i8, zzrVar);
                    int i25 = zzrVar.zzdz;
                    if (i25 < 0) {
                        throw zzbk.zzbq();
                    }
                    if (i25 > bArr.length - zzb11) {
                        throw zzbk.zzbp();
                    }
                    if (i25 == 0) {
                        zzbhVar.add(zzw.zzeg);
                    } else {
                        zzbhVar.add(zzw.zzc(bArr, zzb11, i25));
                        zzb11 += i25;
                    }
                    while (zzb11 < i2) {
                        int zzb12 = zzs.zzb(bArr, zzb11, zzrVar);
                        if (i3 != zzrVar.zzdz) {
                            return zzb11;
                        }
                        zzb11 = zzs.zzb(bArr, zzb12, zzrVar);
                        int i26 = zzrVar.zzdz;
                        if (i26 < 0) {
                            throw zzbk.zzbq();
                        }
                        if (i26 > bArr.length - zzb11) {
                            throw zzbk.zzbp();
                        }
                        if (i26 == 0) {
                            zzbhVar.add(zzw.zzeg);
                        } else {
                            zzbhVar.add(zzw.zzc(bArr, zzb11, i26));
                            zzb11 += i26;
                        }
                    }
                    return zzb11;
                }
                return i8;
            case 30:
            case 44:
                if (i5 != 2) {
                    if (i5 == 0) {
                        zzb = zzs.zzb(i3, bArr, i, i2, (zzbh<?>) zzbhVar, zzrVar);
                    }
                    return i8;
                }
                zzb = zzs.zzb(bArr, i8, (zzbh<?>) zzbhVar, zzrVar);
                zzbc zzbcVar = (zzbc) t;
                zzdr zzdrVar = zzbcVar.zzih;
                if (zzdrVar == zzdr.zzdh()) {
                    zzdrVar = null;
                }
                zzdr zzdrVar2 = (zzdr) zzdc.zzb(i4, zzbhVar, zzah(i6), zzdrVar, this.zzlg);
                if (zzdrVar2 != null) {
                    zzbcVar.zzih = zzdrVar2;
                }
                return zzb;
            case 33:
            case 47:
                if (i5 == 2) {
                    zzbe zzbeVar3 = (zzbe) zzbhVar;
                    int zzb13 = zzs.zzb(bArr, i8, zzrVar);
                    int i27 = zzrVar.zzdz + zzb13;
                    while (zzb13 < i27) {
                        zzb13 = zzs.zzb(bArr, zzb13, zzrVar);
                        zzbeVar3.zzac(zzai.zzm(zzrVar.zzdz));
                    }
                    if (zzb13 == i27) {
                        return zzb13;
                    }
                    throw zzbk.zzbp();
                }
                if (i5 == 0) {
                    zzbe zzbeVar4 = (zzbe) zzbhVar;
                    int zzb14 = zzs.zzb(bArr, i8, zzrVar);
                    zzbeVar4.zzac(zzai.zzm(zzrVar.zzdz));
                    while (zzb14 < i2) {
                        int zzb15 = zzs.zzb(bArr, zzb14, zzrVar);
                        if (i3 != zzrVar.zzdz) {
                            return zzb14;
                        }
                        zzb14 = zzs.zzb(bArr, zzb15, zzrVar);
                        zzbeVar4.zzac(zzai.zzm(zzrVar.zzdz));
                    }
                    return zzb14;
                }
                return i8;
            case 34:
            case 48:
                if (i5 == 2) {
                    zzby zzbyVar5 = (zzby) zzbhVar;
                    int zzb16 = zzs.zzb(bArr, i8, zzrVar);
                    int i28 = zzrVar.zzdz + zzb16;
                    while (zzb16 < i28) {
                        zzb16 = zzs.zzc(bArr, zzb16, zzrVar);
                        zzbyVar5.zzm(zzai.zzb(zzrVar.zzea));
                    }
                    if (zzb16 == i28) {
                        return zzb16;
                    }
                    throw zzbk.zzbp();
                }
                if (i5 == 0) {
                    zzby zzbyVar6 = (zzby) zzbhVar;
                    int zzc2 = zzs.zzc(bArr, i8, zzrVar);
                    zzbyVar6.zzm(zzai.zzb(zzrVar.zzea));
                    while (zzc2 < i2) {
                        int zzb17 = zzs.zzb(bArr, zzc2, zzrVar);
                        if (i3 != zzrVar.zzdz) {
                            return zzc2;
                        }
                        zzc2 = zzs.zzc(bArr, zzb17, zzrVar);
                        zzbyVar6.zzm(zzai.zzb(zzrVar.zzea));
                    }
                    return zzc2;
                }
                return i8;
            case 49:
                if (i5 == 3) {
                    zzda zzaf = zzaf(i6);
                    int i29 = (i3 & (-8)) | 4;
                    i8 = zzs.zzb(zzaf, bArr, i, i2, i29, zzrVar);
                    zzbhVar.add(zzrVar.zzeb);
                    while (i8 < i2) {
                        int zzb18 = zzs.zzb(bArr, i8, zzrVar);
                        if (i3 == zzrVar.zzdz) {
                            i8 = zzs.zzb(zzaf, bArr, zzb18, i2, i29, zzrVar);
                            zzbhVar.add(zzrVar.zzeb);
                        }
                    }
                }
                return i8;
            default:
                return i8;
        }
    }

    private final <K, V> int zzb(T t, byte[] bArr, int i, int i2, int i3, long j, zzr zzrVar) throws IOException {
        Unsafe unsafe = zzkr;
        Object zzag = zzag(i3);
        Object object = unsafe.getObject(t, j);
        if (this.zzli.zzi(object)) {
            Object zzk = this.zzli.zzk(zzag);
            this.zzli.zzc(zzk, object);
            unsafe.putObject(t, j, zzk);
            object = zzk;
        }
        zzcb<?, ?> zzl = this.zzli.zzl(zzag);
        Map<?, ?> zzg = this.zzli.zzg(object);
        int zzb = zzs.zzb(bArr, i, zzrVar);
        int i4 = zzrVar.zzdz;
        if (i4 < 0 || i4 > i2 - zzb) {
            throw zzbk.zzbp();
        }
        int i5 = i4 + zzb;
        K k = zzl.zzkk;
        V v = zzl.zzkm;
        while (zzb < i5) {
            int i6 = zzb + 1;
            int i7 = bArr[zzb];
            if (i7 < 0) {
                i6 = zzs.zzb(i7, bArr, i6, zzrVar);
                i7 = zzrVar.zzdz;
            }
            int i8 = i6;
            int i9 = i7 >>> 3;
            int i10 = i7 & 7;
            if (i9 == 1) {
                if (i10 == zzl.zzkj.zzds()) {
                    zzb = zzb(bArr, i8, i2, zzl.zzkj, (Class<?>) null, zzrVar);
                    k = (K) zzrVar.zzeb;
                } else {
                    zzb = zzs.zzb(i7, bArr, i8, i2, zzrVar);
                }
            } else {
                if (i9 == 2 && i10 == zzl.zzkl.zzds()) {
                    zzb = zzb(bArr, i8, i2, zzl.zzkl, zzl.zzkm.getClass(), zzrVar);
                    v = zzrVar.zzeb;
                }
                zzb = zzs.zzb(i7, bArr, i8, i2, zzrVar);
            }
        }
        if (zzb != i5) {
            throw zzbk.zzbt();
        }
        zzg.put(k, v);
        return i5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0023. Please report as an issue. */
    private final int zzb(T t, byte[] bArr, int i, int i2, int i3, int i4, int i5, int i6, int i7, long j, int i8, zzr zzrVar) throws IOException {
        int zzc;
        Unsafe unsafe = zzkr;
        long j2 = this.zzks[i8 + 2] & 1048575;
        switch (i7) {
            case 51:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Double.valueOf(zzs.zzd(bArr, i)));
                    zzc = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 52:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Float.valueOf(zzs.zze(bArr, i)));
                    zzc = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 53:
            case 54:
                if (i5 == 0) {
                    zzc = zzs.zzc(bArr, i, zzrVar);
                    unsafe.putObject(t, j, Long.valueOf(zzrVar.zzea));
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 55:
            case 62:
                if (i5 == 0) {
                    zzc = zzs.zzb(bArr, i, zzrVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzrVar.zzdz));
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 56:
            case 65:
                if (i5 == 1) {
                    unsafe.putObject(t, j, Long.valueOf(zzs.zzc(bArr, i)));
                    zzc = i + 8;
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 57:
            case 64:
                if (i5 == 5) {
                    unsafe.putObject(t, j, Integer.valueOf(zzs.zzb(bArr, i)));
                    zzc = i + 4;
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 58:
                if (i5 == 0) {
                    zzc = zzs.zzc(bArr, i, zzrVar);
                    unsafe.putObject(t, j, Boolean.valueOf(zzrVar.zzea != 0));
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 59:
                if (i5 == 2) {
                    int zzb = zzs.zzb(bArr, i, zzrVar);
                    int i9 = zzrVar.zzdz;
                    if (i9 == 0) {
                        unsafe.putObject(t, j, "");
                    } else {
                        if ((i6 & 536870912) != 0 && !zzea.zzf(bArr, zzb, zzb + i9)) {
                            throw zzbk.zzbu();
                        }
                        unsafe.putObject(t, j, new String(bArr, zzb, i9, zzbd.UTF_8));
                        zzb += i9;
                    }
                    unsafe.putInt(t, j2, i4);
                    return zzb;
                }
                return i;
            case 60:
                if (i5 == 2) {
                    int zzb2 = zzs.zzb(zzaf(i8), bArr, i, i2, zzrVar);
                    Object object = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object == null) {
                        unsafe.putObject(t, j, zzrVar.zzeb);
                    } else {
                        unsafe.putObject(t, j, zzbd.zzb(object, zzrVar.zzeb));
                    }
                    unsafe.putInt(t, j2, i4);
                    return zzb2;
                }
                return i;
            case 61:
                if (i5 == 2) {
                    zzc = zzs.zzf(bArr, i, zzrVar);
                    unsafe.putObject(t, j, zzrVar.zzeb);
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 63:
                if (i5 == 0) {
                    int zzb3 = zzs.zzb(bArr, i, zzrVar);
                    int i10 = zzrVar.zzdz;
                    zzbf zzah = zzah(i8);
                    if (zzah == null || zzah.zzad(i10)) {
                        unsafe.putObject(t, j, Integer.valueOf(i10));
                        zzc = zzb3;
                        unsafe.putInt(t, j2, i4);
                        return zzc;
                    }
                    zzo(t).zzc(i3, Long.valueOf(i10));
                    return zzb3;
                }
                return i;
            case 66:
                if (i5 == 0) {
                    zzc = zzs.zzb(bArr, i, zzrVar);
                    unsafe.putObject(t, j, Integer.valueOf(zzai.zzm(zzrVar.zzdz)));
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 67:
                if (i5 == 0) {
                    zzc = zzs.zzc(bArr, i, zzrVar);
                    unsafe.putObject(t, j, Long.valueOf(zzai.zzb(zzrVar.zzea)));
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            case 68:
                if (i5 == 3) {
                    zzc = zzs.zzb(zzaf(i8), bArr, i, i2, (i3 & (-8)) | 4, zzrVar);
                    Object object2 = unsafe.getInt(t, j2) == i4 ? unsafe.getObject(t, j) : null;
                    if (object2 == null) {
                        unsafe.putObject(t, j, zzrVar.zzeb);
                    } else {
                        unsafe.putObject(t, j, zzbd.zzb(object2, zzrVar.zzeb));
                    }
                    unsafe.putInt(t, j2, i4);
                    return zzc;
                }
                return i;
            default:
                return i;
        }
    }

    private final zzda zzaf(int i) {
        int i2 = (i / 3) << 1;
        zzda zzdaVar = (zzda) this.zzkt[i2];
        if (zzdaVar != null) {
            return zzdaVar;
        }
        zzda<T> zzf = zzcv.zzcq().zzf((Class) this.zzkt[i2 + 1]);
        this.zzkt[i2] = zzf;
        return zzf;
    }

    private final Object zzag(int i) {
        return this.zzkt[(i / 3) << 1];
    }

    private final zzbf zzah(int i) {
        return (zzbf) this.zzkt[((i / 3) << 1) + 1];
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Failed to find 'out' block for switch in B:118:0x007f. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    public final int zzb(T t, byte[] bArr, int i, int i2, int i3, zzr zzrVar) throws IOException {
        Unsafe unsafe;
        int i4;
        Object obj;
        zzco<T> zzcoVar;
        int i5;
        int i6;
        int i7;
        int i8;
        zzbf zzah;
        int i9;
        int i10;
        int zzak;
        int i11;
        int i12;
        int i13;
        Object obj2;
        int i14;
        zzr zzrVar2;
        int i15;
        int i16;
        int i17;
        zzr zzrVar3;
        int i18;
        zzr zzrVar4;
        int i19;
        int i20;
        zzr zzrVar5;
        int i21;
        int i22;
        int i23;
        zzco<T> zzcoVar2 = this;
        Object obj3 = t;
        byte[] bArr2 = bArr;
        int i24 = i2;
        int i25 = i3;
        zzr zzrVar6 = zzrVar;
        Unsafe unsafe2 = zzkr;
        int i26 = i;
        int i27 = -1;
        int i28 = 0;
        int i29 = 0;
        int i30 = 0;
        int i31 = -1;
        while (true) {
            if (i26 < i24) {
                int i32 = i26 + 1;
                byte b = bArr2[i26];
                if (b < 0) {
                    i10 = zzs.zzb(b, bArr2, i32, zzrVar6);
                    i9 = zzrVar6.zzdz;
                } else {
                    i9 = b;
                    i10 = i32;
                }
                int i33 = i9 >>> 3;
                int i34 = i9 & 7;
                if (i33 > i27) {
                    zzak = zzcoVar2.zzq(i33, i28 / 3);
                } else {
                    zzak = zzcoVar2.zzak(i33);
                }
                int i35 = zzak;
                if (i35 == -1) {
                    i11 = i33;
                    i12 = i10;
                    i6 = i9;
                    unsafe = unsafe2;
                    i4 = i25;
                    i13 = 0;
                } else {
                    int[] iArr = zzcoVar2.zzks;
                    int i36 = iArr[i35 + 1];
                    int i37 = (i36 & 267386880) >>> 20;
                    int i38 = i9;
                    long j = i36 & 1048575;
                    if (i37 <= 17) {
                        int i39 = iArr[i35 + 2];
                        int i40 = 1 << (i39 >>> 20);
                        int i41 = i39 & 1048575;
                        if (i41 != i31) {
                            if (i31 != -1) {
                                unsafe2.putInt(obj3, i31, i30);
                            }
                            i30 = unsafe2.getInt(obj3, i41);
                            i31 = i41;
                        }
                        switch (i37) {
                            case 0:
                                i15 = i35;
                                i16 = i33;
                                i18 = i31;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar4 = zzrVar;
                                i19 = i10;
                                if (i34 != 1) {
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    zzdy.zzb(obj3, j, zzs.zzd(bArr2, i19));
                                    i26 = i19 + 8;
                                    i30 |= i40;
                                    i31 = i18;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar4;
                                    i24 = i2;
                                    i25 = i3;
                                }
                            case 1:
                                i15 = i35;
                                i16 = i33;
                                i18 = i31;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar4 = zzrVar;
                                i19 = i10;
                                if (i34 != 5) {
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    zzdy.zzb(obj3, j, zzs.zze(bArr2, i19));
                                    i26 = i19 + 4;
                                    i30 |= i40;
                                    i31 = i18;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar4;
                                    i24 = i2;
                                    i25 = i3;
                                }
                            case 2:
                            case 3:
                                i15 = i35;
                                i16 = i33;
                                i18 = i31;
                                i17 = i38;
                                bArr2 = bArr;
                                i19 = i10;
                                if (i34 != 0) {
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    int zzc = zzs.zzc(bArr2, i19, zzrVar);
                                    unsafe2.putLong(t, j, zzrVar.zzea);
                                    i30 |= i40;
                                    i26 = zzc;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar;
                                    i31 = i18;
                                    i24 = i2;
                                    i25 = i3;
                                }
                            case 4:
                            case 11:
                                i15 = i35;
                                i16 = i33;
                                i18 = i31;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar4 = zzrVar;
                                i19 = i10;
                                if (i34 != 0) {
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    i26 = zzs.zzb(bArr2, i19, zzrVar4);
                                    unsafe2.putInt(obj3, j, zzrVar4.zzdz);
                                    i30 |= i40;
                                    i31 = i18;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar4;
                                    i24 = i2;
                                    i25 = i3;
                                }
                            case 5:
                            case 14:
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar4 = zzrVar;
                                if (i34 != 1) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    i18 = i31;
                                    i19 = i10;
                                    unsafe2.putLong(t, j, zzs.zzc(bArr2, i10));
                                    i26 = i19 + 8;
                                    i30 |= i40;
                                    i31 = i18;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar4;
                                    i24 = i2;
                                    i25 = i3;
                                }
                            case 6:
                            case 13:
                                i20 = i2;
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar5 = zzrVar;
                                if (i34 != 5) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    unsafe2.putInt(obj3, j, zzs.zzb(bArr2, i10));
                                    i26 = i10 + 4;
                                    i30 |= i40;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar5;
                                    i25 = i3;
                                    i24 = i20;
                                }
                            case 7:
                                i20 = i2;
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar5 = zzrVar;
                                if (i34 != 0) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    int zzc2 = zzs.zzc(bArr2, i10, zzrVar5);
                                    zzdy.zzb(obj3, j, zzrVar5.zzea != 0);
                                    i30 |= i40;
                                    i26 = zzc2;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar5;
                                    i25 = i3;
                                    i24 = i20;
                                }
                            case 8:
                                i20 = i2;
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar5 = zzrVar;
                                if (i34 != 2) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    if ((i36 & 536870912) == 0) {
                                        i26 = zzs.zzd(bArr2, i10, zzrVar5);
                                    } else {
                                        i26 = zzs.zze(bArr2, i10, zzrVar5);
                                    }
                                    unsafe2.putObject(obj3, j, zzrVar5.zzeb);
                                    i30 |= i40;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar5;
                                    i25 = i3;
                                    i24 = i20;
                                }
                            case 9:
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar5 = zzrVar;
                                if (i34 != 2) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    i20 = i2;
                                    i26 = zzs.zzb(zzcoVar2.zzaf(i15), bArr2, i10, i20, zzrVar5);
                                    if ((i30 & i40) == 0) {
                                        unsafe2.putObject(obj3, j, zzrVar5.zzeb);
                                    } else {
                                        unsafe2.putObject(obj3, j, zzbd.zzb(unsafe2.getObject(obj3, j), zzrVar5.zzeb));
                                    }
                                    i30 |= i40;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar5;
                                    i25 = i3;
                                    i24 = i20;
                                }
                            case 10:
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar4 = zzrVar;
                                if (i34 != 2) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    i26 = zzs.zzf(bArr2, i10, zzrVar4);
                                    unsafe2.putObject(obj3, j, zzrVar4.zzeb);
                                    i30 |= i40;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar4;
                                    i24 = i2;
                                    i25 = i3;
                                }
                            case 12:
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar4 = zzrVar;
                                if (i34 != 0) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    i26 = zzs.zzb(bArr2, i10, zzrVar4);
                                    int i42 = zzrVar4.zzdz;
                                    zzbf zzah2 = zzcoVar2.zzah(i15);
                                    if (zzah2 == null || zzah2.zzad(i42)) {
                                        unsafe2.putInt(obj3, j, i42);
                                        i30 |= i40;
                                        i29 = i17;
                                        i28 = i15;
                                        i27 = i16;
                                        zzrVar6 = zzrVar4;
                                        i24 = i2;
                                        i25 = i3;
                                    } else {
                                        zzo(t).zzc(i17, Long.valueOf(i42));
                                        i29 = i17;
                                        i28 = i15;
                                        i27 = i16;
                                        zzrVar6 = zzrVar4;
                                        i24 = i2;
                                        i25 = i3;
                                    }
                                }
                                break;
                            case 15:
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                bArr2 = bArr;
                                zzrVar4 = zzrVar;
                                if (i34 != 0) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    i26 = zzs.zzb(bArr2, i10, zzrVar4);
                                    unsafe2.putInt(obj3, j, zzai.zzm(zzrVar4.zzdz));
                                    i30 |= i40;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar4;
                                    i24 = i2;
                                    i25 = i3;
                                }
                            case 16:
                                i15 = i35;
                                i16 = i33;
                                i17 = i38;
                                if (i34 != 0) {
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    bArr2 = bArr;
                                    int zzc3 = zzs.zzc(bArr2, i10, zzrVar);
                                    zzrVar4 = zzrVar;
                                    unsafe2.putLong(t, j, zzai.zzb(zzrVar.zzea));
                                    i30 |= i40;
                                    i26 = zzc3;
                                    i29 = i17;
                                    i28 = i15;
                                    i27 = i16;
                                    zzrVar6 = zzrVar4;
                                    i24 = i2;
                                    i25 = i3;
                                }
                            case 17:
                                if (i34 != 3) {
                                    i15 = i35;
                                    i16 = i33;
                                    i17 = i38;
                                    i18 = i31;
                                    i19 = i10;
                                    i12 = i19;
                                    i13 = i15;
                                    unsafe = unsafe2;
                                    i31 = i18;
                                    i6 = i17;
                                    i11 = i16;
                                    i4 = i3;
                                    break;
                                } else {
                                    i26 = zzs.zzb(zzcoVar2.zzaf(i35), bArr, i10, i2, (i33 << 3) | 4, zzrVar);
                                    if ((i30 & i40) == 0) {
                                        zzrVar3 = zzrVar;
                                        unsafe2.putObject(obj3, j, zzrVar3.zzeb);
                                    } else {
                                        zzrVar3 = zzrVar;
                                        unsafe2.putObject(obj3, j, zzbd.zzb(unsafe2.getObject(obj3, j), zzrVar3.zzeb));
                                    }
                                    i30 |= i40;
                                    bArr2 = bArr;
                                    i24 = i2;
                                    i29 = i38;
                                    i28 = i35;
                                    i27 = i33;
                                    i25 = i3;
                                    zzrVar6 = zzrVar3;
                                }
                            default:
                                i15 = i35;
                                i16 = i33;
                                i18 = i31;
                                i17 = i38;
                                i19 = i10;
                                i12 = i19;
                                i13 = i15;
                                unsafe = unsafe2;
                                i31 = i18;
                                i6 = i17;
                                i11 = i16;
                                i4 = i3;
                                break;
                        }
                    } else {
                        int i43 = i31;
                        int i44 = i10;
                        bArr2 = bArr;
                        zzr zzrVar7 = zzrVar6;
                        if (i37 != 27) {
                            i21 = i30;
                            if (i37 <= 49) {
                                i11 = i33;
                                i23 = i38;
                                i13 = i35;
                                unsafe = unsafe2;
                                i26 = zzb((zzco<T>) t, bArr, i44, i2, i38, i11, i34, i35, i36, i37, j, zzrVar);
                                if (i26 == i44) {
                                    i4 = i3;
                                    i12 = i26;
                                } else {
                                    zzcoVar2 = this;
                                    obj3 = t;
                                    bArr2 = bArr;
                                    i27 = i11;
                                    i24 = i2;
                                    i25 = i3;
                                    zzrVar6 = zzrVar;
                                    i31 = i43;
                                    i28 = i13;
                                    i30 = i21;
                                    i29 = i23;
                                    unsafe2 = unsafe;
                                }
                            } else {
                                i11 = i33;
                                i22 = i44;
                                i23 = i38;
                                i13 = i35;
                                unsafe = unsafe2;
                                if (i37 != 50) {
                                    i26 = zzb((zzco<T>) t, bArr, i22, i2, i23, i11, i34, i36, i37, j, i13, zzrVar);
                                    if (i26 != i22) {
                                        zzcoVar2 = this;
                                        obj3 = t;
                                        i24 = i2;
                                        i25 = i3;
                                        i29 = i23;
                                        i27 = i11;
                                        i31 = i43;
                                        i28 = i13;
                                        i30 = i21;
                                        unsafe2 = unsafe;
                                        bArr2 = bArr;
                                        zzrVar6 = zzrVar;
                                    }
                                } else if (i34 == 2) {
                                    i26 = zzb((zzco<T>) t, bArr, i22, i2, i13, j, zzrVar);
                                    if (i26 != i22) {
                                        zzcoVar2 = this;
                                        obj3 = t;
                                        bArr2 = bArr;
                                        i27 = i11;
                                        i24 = i2;
                                        i25 = i3;
                                        zzrVar6 = zzrVar;
                                        i31 = i43;
                                        i28 = i13;
                                        i30 = i21;
                                        i29 = i23;
                                        unsafe2 = unsafe;
                                    }
                                } else {
                                    i4 = i3;
                                    i12 = i22;
                                }
                                i4 = i3;
                                i12 = i26;
                            }
                        } else if (i34 == 2) {
                            zzbh zzbhVar = (zzbh) unsafe2.getObject(obj3, j);
                            if (!zzbhVar.zzaa()) {
                                int size = zzbhVar.size();
                                zzbhVar = zzbhVar.zzh(size == 0 ? 10 : size << 1);
                                unsafe2.putObject(obj3, j, zzbhVar);
                            }
                            i26 = zzs.zzb(zzcoVar2.zzaf(i35), i38, bArr, i44, i2, zzbhVar, zzrVar);
                            i25 = i3;
                            i27 = i33;
                            i29 = i38;
                            i28 = i35;
                            zzrVar6 = zzrVar7;
                            i31 = i43;
                            i30 = i30;
                            i24 = i2;
                        } else {
                            i21 = i30;
                            i11 = i33;
                            i22 = i44;
                            i23 = i38;
                            i13 = i35;
                            unsafe = unsafe2;
                            i4 = i3;
                            i12 = i22;
                        }
                        i31 = i43;
                        i30 = i21;
                        i6 = i23;
                    }
                }
                if (i6 != i4 || i4 == 0) {
                    if (this.zzkx) {
                        zzrVar2 = zzrVar;
                        if (zzrVar2.zzec != zzap.zzao()) {
                            int i45 = i11;
                            if (zzrVar2.zzec.zzb(this.zzkw, i45) == null) {
                                i26 = zzs.zzb(i6, bArr, i12, i2, zzo(t), zzrVar);
                                obj3 = t;
                                i24 = i2;
                                i29 = i6;
                                zzcoVar2 = this;
                                zzrVar6 = zzrVar2;
                                i27 = i45;
                                i28 = i13;
                                unsafe2 = unsafe;
                                bArr2 = bArr;
                                i25 = i4;
                            } else {
                                zzbc.zzc zzcVar = (zzbc.zzc) t;
                                zzcVar.zzbm();
                                zzav<Object> zzavVar = zzcVar.zzik;
                                throw new NoSuchMethodError();
                            }
                        } else {
                            obj2 = t;
                            i14 = i11;
                        }
                    } else {
                        obj2 = t;
                        i14 = i11;
                        zzrVar2 = zzrVar;
                    }
                    i26 = zzs.zzb(i6, bArr, i12, i2, zzo(t), zzrVar);
                    i29 = i6;
                    zzcoVar2 = this;
                    zzrVar6 = zzrVar2;
                    i27 = i14;
                    obj3 = obj2;
                    i28 = i13;
                    unsafe2 = unsafe;
                    bArr2 = bArr;
                    i24 = i2;
                    i25 = i4;
                } else {
                    zzcoVar = this;
                    obj = t;
                    i7 = i31;
                    i8 = -1;
                    i5 = i12;
                }
            } else {
                int i46 = i31;
                unsafe = unsafe2;
                i4 = i25;
                obj = obj3;
                zzcoVar = zzcoVar2;
                i5 = i26;
                i6 = i29;
                i7 = i46;
                i8 = -1;
            }
        }
        if (i7 != i8) {
            unsafe.putInt(obj, i7, i30);
        }
        Object obj4 = null;
        for (int i47 = zzcoVar.zzlc; i47 < zzcoVar.zzld; i47++) {
            int i48 = zzcoVar.zzlb[i47];
            zzds zzdsVar = zzcoVar.zzlg;
            int i49 = zzcoVar.zzks[i48];
            Object zzp = zzdy.zzp(obj, zzcoVar.zzai(i48) & 1048575);
            if (zzp != null && (zzah = zzcoVar.zzah(i48)) != null) {
                obj4 = zzb(i48, i49, zzcoVar.zzli.zzg(zzp), zzah, (zzbf) obj4, (zzds<UT, zzbf>) zzdsVar);
            }
            obj4 = (zzdr) obj4;
        }
        if (obj4 != null) {
            zzcoVar.zzlg.zzg(obj, obj4);
        }
        if (i4 == 0) {
            if (i5 != i2) {
                throw zzbk.zzbt();
            }
        } else if (i5 > i2 || i6 != i4) {
            throw zzbk.zzbt();
        }
        return i5;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Code restructure failed: missing block: B:102:0x020f, code lost:
    
        if (r0 == r15) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:104:0x022e, code lost:
    
        if (r0 == r15) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:95:0x01e2, code lost:
    
        if (r0 == r15) goto L104;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0230, code lost:
    
        r2 = r0;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x0061. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r3v13, types: [int] */
    @Override // com.google.android.gms.internal.places.zzda
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void zzb(T r28, byte[] r29, int r30, int r31, com.google.android.gms.internal.places.zzr r32) throws java.io.IOException {
        /*
            Method dump skipped, instructions count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzco.zzb(java.lang.Object, byte[], int, int, com.google.android.gms.internal.places.zzr):void");
    }

    @Override // com.google.android.gms.internal.places.zzda
    public final void zzd(T t) {
        int i;
        int i2 = this.zzlc;
        while (true) {
            i = this.zzld;
            if (i2 >= i) {
                break;
            }
            long zzai = zzai(this.zzlb[i2]) & 1048575;
            Object zzp = zzdy.zzp(t, zzai);
            if (zzp != null) {
                zzdy.zzb(t, zzai, this.zzli.zzj(zzp));
            }
            i2++;
        }
        int length = this.zzlb.length;
        while (i < length) {
            this.zzlf.zzb(t, this.zzlb[i]);
            i++;
        }
        this.zzlg.zzd(t);
        if (this.zzkx) {
            this.zzlh.zzd(t);
        }
    }

    private final <K, V, UT, UB> UB zzb(int i, int i2, Map<K, V> map, zzbf zzbfVar, UB ub, zzds<UT, UB> zzdsVar) {
        zzcb<?, ?> zzl = this.zzli.zzl(zzag(i));
        Iterator<Map.Entry<K, V>> it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<K, V> next = it.next();
            if (!zzbfVar.zzad(((Integer) next.getValue()).intValue())) {
                if (ub == null) {
                    ub = zzdsVar.zzdk();
                }
                zzae zzk = zzw.zzk(zzcc.zzb(zzl, next.getKey(), next.getValue()));
                try {
                    zzcc.zzb(zzk.zzai(), zzl, next.getKey(), next.getValue());
                    zzdsVar.zzb((zzds<UT, UB>) ub, i2, zzk.zzah());
                    it.remove();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return ub;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v13 */
    /* JADX WARN: Type inference failed for: r4v14, types: [com.google.android.gms.internal.places.zzda] */
    /* JADX WARN: Type inference failed for: r4v17 */
    /* JADX WARN: Type inference failed for: r4v18 */
    /* JADX WARN: Type inference failed for: r4v5, types: [com.google.android.gms.internal.places.zzda] */
    @Override // com.google.android.gms.internal.places.zzda
    public final boolean zzp(T t) {
        int i;
        int i2 = -1;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            boolean z = true;
            if (i3 >= this.zzlc) {
                return !this.zzkx || this.zzlh.zzb(t).isInitialized();
            }
            int i5 = this.zzlb[i3];
            int i6 = this.zzks[i5];
            int zzai = zzai(i5);
            if (this.zzkz) {
                i = 0;
            } else {
                int i7 = this.zzks[i5 + 2];
                int i8 = i7 & 1048575;
                i = 1 << (i7 >>> 20);
                if (i8 != i2) {
                    i4 = zzkr.getInt(t, i8);
                    i2 = i8;
                }
            }
            if (((268435456 & zzai) != 0) && !zzb((zzco<T>) t, i5, i4, i)) {
                return false;
            }
            int i9 = (267386880 & zzai) >>> 20;
            if (i9 == 9 || i9 == 17) {
                if (zzb((zzco<T>) t, i5, i4, i) && !zzb(t, zzai, zzaf(i5))) {
                    return false;
                }
            } else {
                if (i9 != 27) {
                    if (i9 == 60 || i9 == 68) {
                        if (zzb((zzco<T>) t, i6, i5) && !zzb(t, zzai, zzaf(i5))) {
                            return false;
                        }
                    } else if (i9 != 49) {
                        if (i9 != 50) {
                            continue;
                        } else {
                            Map<?, ?> zzh = this.zzli.zzh(zzdy.zzp(t, zzai & 1048575));
                            if (!zzh.isEmpty()) {
                                if (this.zzli.zzl(zzag(i5)).zzkl.zzdr() == zzem.MESSAGE) {
                                    ?? r4 = 0;
                                    Iterator<?> it = zzh.values().iterator();
                                    while (true) {
                                        if (!it.hasNext()) {
                                            break;
                                        }
                                        Object next = it.next();
                                        r4 = r4;
                                        if (r4 == 0) {
                                            r4 = zzcv.zzcq().zzf(next.getClass());
                                        }
                                        if (!r4.zzp(next)) {
                                            z = false;
                                            break;
                                        }
                                    }
                                }
                            }
                            if (!z) {
                                return false;
                            }
                        }
                    }
                }
                List list = (List) zzdy.zzp(t, zzai & 1048575);
                if (!list.isEmpty()) {
                    ?? zzaf = zzaf(i5);
                    int i10 = 0;
                    while (true) {
                        if (i10 >= list.size()) {
                            break;
                        }
                        if (!zzaf.zzp(list.get(i10))) {
                            z = false;
                            break;
                        }
                        i10++;
                    }
                }
                if (!z) {
                    return false;
                }
            }
            i3++;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static boolean zzb(Object obj, int i, zzda zzdaVar) {
        return zzdaVar.zzp(zzdy.zzp(obj, i & 1048575));
    }

    private static void zzb(int i, Object obj, zzel zzelVar) throws IOException {
        if (obj instanceof String) {
            zzelVar.zzb(i, (String) obj);
        } else {
            zzelVar.zzb(i, (zzw) obj);
        }
    }

    private final int zzai(int i) {
        return this.zzks[i + 1];
    }

    private final int zzaj(int i) {
        return this.zzks[i + 2];
    }

    private static <T> double zzf(T t, long j) {
        return ((Double) zzdy.zzp(t, j)).doubleValue();
    }

    private static <T> float zzg(T t, long j) {
        return ((Float) zzdy.zzp(t, j)).floatValue();
    }

    private static <T> int zzh(T t, long j) {
        return ((Integer) zzdy.zzp(t, j)).intValue();
    }

    private static <T> long zzi(T t, long j) {
        return ((Long) zzdy.zzp(t, j)).longValue();
    }

    private static <T> boolean zzj(T t, long j) {
        return ((Boolean) zzdy.zzp(t, j)).booleanValue();
    }

    private final boolean zzd(T t, T t2, int i) {
        return zzb((zzco<T>) t, i) == zzb((zzco<T>) t2, i);
    }

    private final boolean zzb(T t, int i, int i2, int i3) {
        if (this.zzkz) {
            return zzb((zzco<T>) t, i);
        }
        return (i2 & i3) != 0;
    }

    private final boolean zzb(T t, int i) {
        if (this.zzkz) {
            int zzai = zzai(i);
            long j = zzai & 1048575;
            switch ((zzai & 267386880) >>> 20) {
                case 0:
                    return zzdy.zzo(t, j) != Utils.DOUBLE_EPSILON;
                case 1:
                    return zzdy.zzn(t, j) != 0.0f;
                case 2:
                    return zzdy.zzl(t, j) != 0;
                case 3:
                    return zzdy.zzl(t, j) != 0;
                case 4:
                    return zzdy.zzk(t, j) != 0;
                case 5:
                    return zzdy.zzl(t, j) != 0;
                case 6:
                    return zzdy.zzk(t, j) != 0;
                case 7:
                    return zzdy.zzm(t, j);
                case 8:
                    Object zzp = zzdy.zzp(t, j);
                    if (zzp instanceof String) {
                        return !((String) zzp).isEmpty();
                    }
                    if (zzp instanceof zzw) {
                        return !zzw.zzeg.equals(zzp);
                    }
                    throw new IllegalArgumentException();
                case 9:
                    return zzdy.zzp(t, j) != null;
                case 10:
                    return !zzw.zzeg.equals(zzdy.zzp(t, j));
                case 11:
                    return zzdy.zzk(t, j) != 0;
                case 12:
                    return zzdy.zzk(t, j) != 0;
                case 13:
                    return zzdy.zzk(t, j) != 0;
                case 14:
                    return zzdy.zzl(t, j) != 0;
                case 15:
                    return zzdy.zzk(t, j) != 0;
                case 16:
                    return zzdy.zzl(t, j) != 0;
                case 17:
                    return zzdy.zzp(t, j) != null;
                default:
                    throw new IllegalArgumentException();
            }
        }
        int zzaj = zzaj(i);
        return (zzdy.zzk(t, (long) (zzaj & 1048575)) & (1 << (zzaj >>> 20))) != 0;
    }

    private final void zzc(T t, int i) {
        if (this.zzkz) {
            return;
        }
        int zzaj = zzaj(i);
        long j = zzaj & 1048575;
        zzdy.zzb((Object) t, j, zzdy.zzk(t, j) | (1 << (zzaj >>> 20)));
    }

    private final boolean zzb(T t, int i, int i2) {
        return zzdy.zzk(t, (long) (zzaj(i2) & 1048575)) == i;
    }

    private final void zzc(T t, int i, int i2) {
        zzdy.zzb((Object) t, zzaj(i2) & 1048575, i);
    }

    private final int zzak(int i) {
        if (i < this.zzku || i > this.zzkv) {
            return -1;
        }
        return zzr(i, 0);
    }

    private final int zzq(int i, int i2) {
        if (i < this.zzku || i > this.zzkv) {
            return -1;
        }
        return zzr(i, i2);
    }

    private final int zzr(int i, int i2) {
        int length = (this.zzks.length / 3) - 1;
        while (i2 <= length) {
            int i3 = (length + i2) >>> 1;
            int i4 = i3 * 3;
            int i5 = this.zzks[i4];
            if (i == i5) {
                return i4;
            }
            if (i < i5) {
                length = i3 - 1;
            } else {
                i2 = i3 + 1;
            }
        }
        return -1;
    }
}
