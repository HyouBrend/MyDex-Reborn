package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzbc;
import com.google.android.gms.internal.places.zzbc.zzb;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: classes.dex */
public abstract class zzbc<MessageType extends zzbc<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzm<MessageType, BuilderType> {
    private static Map<Object, zzbc<?, ?>> zzij = new ConcurrentHashMap();
    protected zzdr zzih = zzdr.zzdh();
    private int zzii = -1;

    /* loaded from: classes.dex */
    public static class zzd<T extends zzbc<T, ?>> extends zzn<T> {
        private final T zzie;

        public zzd(T t) {
            this.zzie = t;
        }
    }

    /* loaded from: classes.dex */
    public enum zze {
        public static final int zzil = 1;
        public static final int zzim = 2;
        public static final int zzin = 3;
        public static final int zzio = 4;
        public static final int zzip = 5;
        public static final int zziq = 6;
        public static final int zzir = 7;
        public static final int zzit = 1;
        public static final int zziu = 2;
        public static final int zziw = 1;
        public static final int zzix = 2;
        private static final /* synthetic */ int[] zzis = {1, 2, 3, 4, 5, 6, 7};
        private static final /* synthetic */ int[] zziv = {1, 2};
        private static final /* synthetic */ int[] zziy = {1, 2};

        public static int[] zzbn() {
            return (int[]) zzis.clone();
        }
    }

    /* loaded from: classes.dex */
    public static class zzf<ContainingType extends zzck, Type> extends zzan<ContainingType, Type> {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzb(int i, Object obj, Object obj2);

    /* loaded from: classes.dex */
    public static abstract class zzc<MessageType extends zzc<MessageType, BuilderType>, BuilderType> extends zzbc<MessageType, BuilderType> implements zzcm {
        protected zzav<Object> zzik = zzav.zzau();

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzav<Object> zzbm() {
            if (this.zzik.isImmutable()) {
                this.zzik = (zzav) this.zzik.clone();
            }
            return this.zzik;
        }
    }

    public String toString() {
        return zzcl.zzb(this, super.toString());
    }

    public int hashCode() {
        if (this.zzdt != 0) {
            return this.zzdt;
        }
        this.zzdt = zzcv.zzcq().zzq(this).hashCode(this);
        return this.zzdt;
    }

    /* loaded from: classes.dex */
    public static abstract class zzb<MessageType extends zzbc<MessageType, BuilderType>, BuilderType extends zzb<MessageType, BuilderType>> extends zzo<MessageType, BuilderType> {
        private final MessageType zzie;
        private MessageType zzif;
        private boolean zzig = false;

        /* JADX INFO: Access modifiers changed from: protected */
        public zzb(MessageType messagetype) {
            this.zzie = messagetype;
            this.zzif = (MessageType) messagetype.zzb(zze.zzio, null, null);
        }

        @Override // com.google.android.gms.internal.places.zzcm
        public final boolean isInitialized() {
            return zzbc.zzb(this.zzif, false);
        }

        @Override // com.google.android.gms.internal.places.zzcj
        /* renamed from: zzbc, reason: merged with bridge method [inline-methods] */
        public MessageType zzbe() {
            if (this.zzig) {
                return this.zzif;
            }
            this.zzif.zzab();
            this.zzig = true;
            return this.zzif;
        }

        @Override // com.google.android.gms.internal.places.zzcj
        /* renamed from: zzbd, reason: merged with bridge method [inline-methods] */
        public final MessageType zzbf() {
            MessageType messagetype = (MessageType) zzbe();
            if (messagetype.isInitialized()) {
                return messagetype;
            }
            throw new zzdp(messagetype);
        }

        @Override // com.google.android.gms.internal.places.zzo
        public final BuilderType zzb(MessageType messagetype) {
            if (this.zzig) {
                MessageType messagetype2 = (MessageType) this.zzif.zzb(zze.zzio, null, null);
                zzb(messagetype2, this.zzif);
                this.zzif = messagetype2;
                this.zzig = false;
            }
            zzb(this.zzif, messagetype);
            return this;
        }

        private static void zzb(MessageType messagetype, MessageType messagetype2) {
            zzcv.zzcq().zzq(messagetype).zzd(messagetype, messagetype2);
        }

        @Override // com.google.android.gms.internal.places.zzo
        /* renamed from: zzx */
        public final /* synthetic */ zzo clone() {
            return (zzb) clone();
        }

        @Override // com.google.android.gms.internal.places.zzcm
        public final /* synthetic */ zzck zzbg() {
            return this.zzie;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.gms.internal.places.zzo
        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            zzb zzbVar = (zzb) this.zzie.zzb(zze.zzip, null, null);
            zzbVar.zzb((zzb) zzbe());
            return zzbVar;
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (((zzbc) zzb(zze.zziq, (Object) null, (Object) null)).getClass().isInstance(obj)) {
            return zzcv.zzcq().zzq(this).equals(this, (zzbc) obj);
        }
        return false;
    }

    protected final void zzab() {
        zzcv.zzcq().zzq(this).zzd(this);
    }

    @Override // com.google.android.gms.internal.places.zzcm
    public final boolean isInitialized() {
        Boolean bool = Boolean.TRUE;
        return zzb(this, true);
    }

    @Override // com.google.android.gms.internal.places.zzm
    final int zzw() {
        return this.zzii;
    }

    @Override // com.google.android.gms.internal.places.zzm
    final void zze(int i) {
        this.zzii = i;
    }

    @Override // com.google.android.gms.internal.places.zzck
    public final void zzc(zzaj zzajVar) throws IOException {
        zzcv.zzcq().zzf(getClass()).zzb(this, zzam.zzb(zzajVar));
    }

    @Override // com.google.android.gms.internal.places.zzck
    public final int zzbh() {
        if (this.zzii == -1) {
            this.zzii = zzcv.zzcq().zzq(this).zzn(this);
        }
        return this.zzii;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static <T extends zzbc<?, ?>> T zzd(Class<T> cls) {
        zzbc<?, ?> zzbcVar = zzij.get(cls);
        if (zzbcVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzbcVar = zzij.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzbcVar == null) {
            zzbcVar = (T) ((zzbc) zzdy.zzh(cls)).zzb(zze.zziq, (Object) null, (Object) null);
            if (zzbcVar == null) {
                throw new IllegalStateException();
            }
            zzij.put(cls, zzbcVar);
        }
        return (T) zzbcVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends zzbc<?, ?>> void zzb(Class<T> cls, T t) {
        zzij.put(cls, t);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zzb(zzck zzckVar, String str, Object[] objArr) {
        return new zzcx(zzckVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzb(Method method, Object obj, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (IllegalAccessException e) {
            throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", e);
        } catch (InvocationTargetException e2) {
            Throwable cause = e2.getCause();
            if (cause instanceof RuntimeException) {
                throw ((RuntimeException) cause);
            }
            if (cause instanceof Error) {
                throw ((Error) cause);
            }
            throw new RuntimeException("Unexpected exception thrown by generated accessor method.", cause);
        }
    }

    protected static final <T extends zzbc<T, ?>> boolean zzb(T t, boolean z) {
        byte byteValue = ((Byte) t.zzb(zze.zzil, null, null)).byteValue();
        if (byteValue == 1) {
            return true;
        }
        if (byteValue == 0) {
            return false;
        }
        boolean zzp = zzcv.zzcq().zzq(t).zzp(t);
        if (z) {
            t.zzb(zze.zzim, zzp ? t : null, null);
        }
        return zzp;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzbi zzbi() {
        return zzbe.zzbo();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <E> zzbh<E> zzbj() {
        return zzcy.zzct();
    }

    private static <T extends zzbc<T, ?>> T zzb(T t, byte[] bArr, int i, int i2, zzap zzapVar) throws zzbk {
        T t2 = (T) t.zzb(zze.zzio, null, null);
        try {
            zzcv.zzcq().zzq(t2).zzb(t2, bArr, 0, i2, new zzr(zzapVar));
            t2.zzab();
            if (t2.zzdt == 0) {
                return t2;
            }
            throw new RuntimeException();
        } catch (IOException e) {
            if (e.getCause() instanceof zzbk) {
                throw ((zzbk) e.getCause());
            }
            throw new zzbk(e.getMessage()).zzh(t2);
        } catch (IndexOutOfBoundsException unused) {
            throw zzbk.zzbp().zzh(t2);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static <T extends zzbc<T, ?>> T zzb(T t, byte[] bArr) throws zzbk {
        T t2 = (T) zzb(t, bArr, 0, bArr.length, zzap.zzao());
        if (t2 == null || t2.isInitialized()) {
            return t2;
        }
        throw new zzbk(new zzdp(t2).getMessage()).zzh(t2);
    }

    @Override // com.google.android.gms.internal.places.zzck
    public final /* synthetic */ zzcj zzbk() {
        zzb zzbVar = (zzb) zzb(zze.zzip, (Object) null, (Object) null);
        zzbVar.zzb((zzb) this);
        return zzbVar;
    }

    @Override // com.google.android.gms.internal.places.zzck
    public final /* synthetic */ zzcj zzbl() {
        return (zzb) zzb(zze.zzip, (Object) null, (Object) null);
    }

    @Override // com.google.android.gms.internal.places.zzcm
    public final /* synthetic */ zzck zzbg() {
        return (zzbc) zzb(zze.zziq, (Object) null, (Object) null);
    }
}
