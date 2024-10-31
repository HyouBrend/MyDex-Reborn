package com.google.android.gms.internal.places;

import com.google.android.libraries.places.api.model.PlaceTypes;
import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzdy {
    private static final Logger logger = Logger.getLogger(zzdy.class.getName());
    private static final Class<?> zzdw;
    private static final boolean zzer;
    private static final Unsafe zzkr;
    private static final boolean zzmn;
    private static final boolean zzmo;
    private static final zzd zzmp;
    private static final boolean zzmq;
    private static final long zzmr;
    private static final long zzms;
    private static final long zzmt;
    private static final long zzmu;
    private static final long zzmv;
    private static final long zzmw;
    private static final long zzmx;
    private static final long zzmy;
    private static final long zzmz;
    private static final long zzna;
    private static final long zznb;
    private static final long zznc;
    private static final long zznd;
    private static final long zzne;
    private static final int zznf;
    static final boolean zzng;

    private zzdy() {
    }

    /* loaded from: classes.dex */
    static final class zzb extends zzd {
        zzb(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final byte zzy(Object obj, long j) {
            if (zzdy.zzng) {
                return zzdy.zzq(obj, j);
            }
            return zzdy.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzf(Object obj, long j, byte b) {
            if (zzdy.zzng) {
                zzdy.zzb(obj, j, b);
            } else {
                zzdy.zzc(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzdy.zzng) {
                return zzdy.zzs(obj, j);
            }
            return zzdy.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, boolean z) {
            if (zzdy.zzng) {
                zzdy.zzc(obj, j, z);
            } else {
                zzdy.zzd(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, double d) {
            zzb(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* loaded from: classes.dex */
    static final class zzc extends zzd {
        zzc(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final byte zzy(Object obj, long j) {
            if (zzdy.zzng) {
                return zzdy.zzq(obj, j);
            }
            return zzdy.zzr(obj, j);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzf(Object obj, long j, byte b) {
            if (zzdy.zzng) {
                zzdy.zzb(obj, j, b);
            } else {
                zzdy.zzc(obj, j, b);
            }
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final boolean zzm(Object obj, long j) {
            if (zzdy.zzng) {
                return zzdy.zzs(obj, j);
            }
            return zzdy.zzt(obj, j);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, boolean z) {
            if (zzdy.zzng) {
                zzdy.zzc(obj, j, z);
            } else {
                zzdy.zzd(obj, j, z);
            }
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final float zzn(Object obj, long j) {
            return Float.intBitsToFloat(zzk(obj, j));
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, float f) {
            zzb(obj, j, Float.floatToIntBits(f));
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final double zzo(Object obj, long j) {
            return Double.longBitsToDouble(zzl(obj, j));
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, double d) {
            zzb(obj, j, Double.doubleToLongBits(d));
        }
    }

    /* loaded from: classes.dex */
    static final class zze extends zzd {
        zze(Unsafe unsafe) {
            super(unsafe);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final byte zzy(Object obj, long j) {
            return this.zznh.getByte(obj, j);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzf(Object obj, long j, byte b) {
            this.zznh.putByte(obj, j, b);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final boolean zzm(Object obj, long j) {
            return this.zznh.getBoolean(obj, j);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, boolean z) {
            this.zznh.putBoolean(obj, j, z);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final float zzn(Object obj, long j) {
            return this.zznh.getFloat(obj, j);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, float f) {
            this.zznh.putFloat(obj, j, f);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final double zzo(Object obj, long j) {
            return this.zznh.getDouble(obj, j);
        }

        @Override // com.google.android.gms.internal.places.zzdy.zzd
        public final void zzb(Object obj, long j, double d) {
            this.zznh.putDouble(obj, j, d);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzdl() {
        return zzer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes.dex */
    public static abstract class zzd {
        Unsafe zznh;

        zzd(Unsafe unsafe) {
            this.zznh = unsafe;
        }

        public abstract void zzb(Object obj, long j, double d);

        public abstract void zzb(Object obj, long j, float f);

        public abstract void zzb(Object obj, long j, boolean z);

        public abstract void zzf(Object obj, long j, byte b);

        public abstract boolean zzm(Object obj, long j);

        public abstract float zzn(Object obj, long j);

        public abstract double zzo(Object obj, long j);

        public abstract byte zzy(Object obj, long j);

        public final int zzk(Object obj, long j) {
            return this.zznh.getInt(obj, j);
        }

        public final void zzb(Object obj, long j, int i) {
            this.zznh.putInt(obj, j, i);
        }

        public final long zzl(Object obj, long j) {
            return this.zznh.getLong(obj, j);
        }

        public final void zzb(Object obj, long j, long j2) {
            this.zznh.putLong(obj, j, j2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzdm() {
        return zzmq;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T> T zzh(Class<T> cls) {
        try {
            return (T) zzkr.allocateInstance(cls);
        } catch (InstantiationException e) {
            throw new IllegalStateException(e);
        }
    }

    private static int zzi(Class<?> cls) {
        if (zzer) {
            return zzmp.zznh.arrayBaseOffset(cls);
        }
        return -1;
    }

    private static int zzj(Class<?> cls) {
        if (zzer) {
            return zzmp.zznh.arrayIndexScale(cls);
        }
        return -1;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int zzk(Object obj, long j) {
        return zzmp.zzk(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(Object obj, long j, int i) {
        zzmp.zzb(obj, j, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static long zzl(Object obj, long j) {
        return zzmp.zzl(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(Object obj, long j, long j2) {
        zzmp.zzb(obj, j, j2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean zzm(Object obj, long j) {
        return zzmp.zzm(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(Object obj, long j, boolean z) {
        zzmp.zzb(obj, j, z);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float zzn(Object obj, long j) {
        return zzmp.zzn(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(Object obj, long j, float f) {
        zzmp.zzb(obj, j, f);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static double zzo(Object obj, long j) {
        return zzmp.zzo(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(Object obj, long j, double d) {
        zzmp.zzb(obj, j, d);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzp(Object obj, long j) {
        return zzmp.zznh.getObject(obj, j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(Object obj, long j, Object obj2) {
        zzmp.zznh.putObject(obj, j, obj2);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static byte zzb(byte[] bArr, long j) {
        return zzmp.zzy(bArr, zzmr + j);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void zzb(byte[] bArr, long j, byte b) {
        zzmp.zzf(bArr, zzmr + j, b);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Unsafe zzdn() {
        try {
            return (Unsafe) AccessController.doPrivileged(new zzdx());
        } catch (Throwable unused) {
            return null;
        }
    }

    private static boolean zzdo() {
        Unsafe unsafe = zzkr;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("arrayBaseOffset", Class.class);
            cls.getMethod("arrayIndexScale", Class.class);
            cls.getMethod("getInt", Object.class, Long.TYPE);
            cls.getMethod("putInt", Object.class, Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            cls.getMethod("putLong", Object.class, Long.TYPE, Long.TYPE);
            cls.getMethod("getObject", Object.class, Long.TYPE);
            cls.getMethod("putObject", Object.class, Long.TYPE, Object.class);
            if (zzp.zzy()) {
                return true;
            }
            cls.getMethod("getByte", Object.class, Long.TYPE);
            cls.getMethod("putByte", Object.class, Long.TYPE, Byte.TYPE);
            cls.getMethod("getBoolean", Object.class, Long.TYPE);
            cls.getMethod("putBoolean", Object.class, Long.TYPE, Boolean.TYPE);
            cls.getMethod("getFloat", Object.class, Long.TYPE);
            cls.getMethod("putFloat", Object.class, Long.TYPE, Float.TYPE);
            cls.getMethod("getDouble", Object.class, Long.TYPE);
            cls.getMethod("putDouble", Object.class, Long.TYPE, Double.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzdp() {
        Unsafe unsafe = zzkr;
        if (unsafe == null) {
            return false;
        }
        try {
            Class<?> cls = unsafe.getClass();
            cls.getMethod("objectFieldOffset", Field.class);
            cls.getMethod("getLong", Object.class, Long.TYPE);
            if (zzdq() == null) {
                return false;
            }
            if (zzp.zzy()) {
                return true;
            }
            cls.getMethod("getByte", Long.TYPE);
            cls.getMethod("putByte", Long.TYPE, Byte.TYPE);
            cls.getMethod("getInt", Long.TYPE);
            cls.getMethod("putInt", Long.TYPE, Integer.TYPE);
            cls.getMethod("getLong", Long.TYPE);
            cls.getMethod("putLong", Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Long.TYPE, Long.TYPE, Long.TYPE);
            cls.getMethod("copyMemory", Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE);
            return true;
        } catch (Throwable th) {
            Logger logger2 = logger;
            Level level = Level.WARNING;
            String valueOf = String.valueOf(th);
            StringBuilder sb = new StringBuilder(String.valueOf(valueOf).length() + 71);
            sb.append("platform method missing - proto runtime falling back to safer methods: ");
            sb.append(valueOf);
            logger2.logp(level, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", sb.toString());
            return false;
        }
    }

    private static boolean zzk(Class<?> cls) {
        if (!zzp.zzy()) {
            return false;
        }
        try {
            Class<?> cls2 = zzdw;
            cls2.getMethod("peekLong", cls, Boolean.TYPE);
            cls2.getMethod("pokeLong", cls, Long.TYPE, Boolean.TYPE);
            cls2.getMethod("pokeInt", cls, Integer.TYPE, Boolean.TYPE);
            cls2.getMethod("peekInt", cls, Boolean.TYPE);
            cls2.getMethod("pokeByte", cls, Byte.TYPE);
            cls2.getMethod("peekByte", cls);
            cls2.getMethod("pokeByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            cls2.getMethod("peekByteArray", cls, byte[].class, Integer.TYPE, Integer.TYPE);
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    private static Field zzdq() {
        Field zzc2;
        if (zzp.zzy() && (zzc2 = zzc(Buffer.class, "effectiveDirectAddress")) != null) {
            return zzc2;
        }
        Field zzc3 = zzc(Buffer.class, PlaceTypes.ADDRESS);
        if (zzc3 == null || zzc3.getType() != Long.TYPE) {
            return null;
        }
        return zzc3;
    }

    private static Field zzc(Class<?> cls, String str) {
        try {
            return cls.getDeclaredField(str);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzq(Object obj, long j) {
        return (byte) (zzk(obj, (-4) & j) >>> ((int) (((~j) & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte zzr(Object obj, long j) {
        return (byte) (zzk(obj, (-4) & j) >>> ((int) ((j & 3) << 3)));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzb(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int zzk = zzk(obj, j2);
        int i = ((~((int) j)) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk & (~(255 << i))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j, byte b) {
        long j2 = (-4) & j;
        int i = (((int) j) & 3) << 3;
        zzb(obj, j2, ((255 & b) << i) | (zzk(obj, j2) & (~(255 << i))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzs(Object obj, long j) {
        return zzq(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean zzt(Object obj, long j) {
        return zzr(obj, j) != 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzc(Object obj, long j, boolean z) {
        zzb(obj, j, z ? (byte) 1 : (byte) 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void zzd(Object obj, long j, boolean z) {
        zzc(obj, j, z ? (byte) 1 : (byte) 0);
    }

    static {
        Unsafe zzdn = zzdn();
        zzkr = zzdn;
        zzdw = zzp.zzz();
        boolean zzk = zzk(Long.TYPE);
        zzmn = zzk;
        boolean zzk2 = zzk(Integer.TYPE);
        zzmo = zzk2;
        zzd zzdVar = null;
        if (zzdn != null) {
            if (!zzp.zzy()) {
                zzdVar = new zze(zzdn);
            } else if (zzk) {
                zzdVar = new zzb(zzdn);
            } else if (zzk2) {
                zzdVar = new zzc(zzdn);
            }
        }
        zzmp = zzdVar;
        zzmq = zzdp();
        zzer = zzdo();
        long zzi = zzi(byte[].class);
        zzmr = zzi;
        zzms = zzi(boolean[].class);
        zzmt = zzj(boolean[].class);
        zzmu = zzi(int[].class);
        zzmv = zzj(int[].class);
        zzmw = zzi(long[].class);
        zzmx = zzj(long[].class);
        zzmy = zzi(float[].class);
        zzmz = zzj(float[].class);
        zzna = zzi(double[].class);
        zznb = zzj(double[].class);
        zznc = zzi(Object[].class);
        zznd = zzj(Object[].class);
        Field zzdq = zzdq();
        zzne = (zzdq == null || zzdVar == null) ? -1L : zzdVar.zznh.objectFieldOffset(zzdq);
        zznf = (int) (7 & zzi);
        zzng = ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN;
    }
}
