package com.google.android.libraries.places.internal;

import com.google.android.libraries.places.internal.zzagl;
import com.google.android.libraries.places.internal.zzago;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public abstract class zzago<MessageType extends zzago<MessageType, BuilderType>, BuilderType extends zzagl<MessageType, BuilderType>> extends zzafg<MessageType, BuilderType> {
    private static final Map zzb = new ConcurrentHashMap();
    private int zzd = -1;
    protected zzaiz zzc = zzaiz.zzc();

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzagv zzA() {
        return zzahl.zze();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzagw zzB() {
        return zzaif.zzd();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzagw zzC(zzagw zzagwVar) {
        int size = zzagwVar.size();
        return zzagwVar.zzf(size == 0 ? 10 : size + size);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Object zzE(Method method, Object obj, Object... objArr) {
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

    /* JADX INFO: Access modifiers changed from: protected */
    public static Object zzF(zzahw zzahwVar, String str, Object[] objArr) {
        return new zzaig(zzahwVar, str, objArr);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void zzI(Class cls, zzago zzagoVar) {
        zzagoVar.zzH();
        zzb.put(cls, zzagoVar);
    }

    private final int zza(zzaih zzaihVar) {
        if (zzaihVar == null) {
            return zzaie.zza().zzb(getClass()).zza(this);
        }
        return zzaihVar.zza(this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static zzago zzx(Class cls) {
        Map map = zzb;
        zzago zzagoVar = (zzago) map.get(cls);
        if (zzagoVar == null) {
            try {
                Class.forName(cls.getName(), true, cls.getClassLoader());
                zzagoVar = (zzago) map.get(cls);
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException("Class initialization cannot fail.", e);
            }
        }
        if (zzagoVar == null) {
            zzagoVar = (zzago) ((zzago) zzaji.zze(cls)).zzb(6, null, null);
            if (zzagoVar == null) {
                throw new IllegalStateException();
            }
            map.put(cls, zzagoVar);
        }
        return zzagoVar;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static zzagt zzz() {
        return zzagp.zze();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return zzaie.zza().zzb(getClass()).zzg(this, (zzago) obj);
    }

    public final int hashCode() {
        if (zzL()) {
            return zzu();
        }
        int i = this.zza;
        if (i != 0) {
            return i;
        }
        int zzu = zzu();
        this.zza = zzu;
        return zzu;
    }

    public final String toString() {
        return zzahy.zza(this, super.toString());
    }

    @Override // com.google.android.libraries.places.internal.zzahw
    public final /* synthetic */ zzahv zzD() {
        return (zzagl) zzb(5, null, null);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void zzG() {
        zzaie.zza().zzb(getClass()).zzd(this);
        zzH();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzH() {
        this.zzd &= Integer.MAX_VALUE;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final void zzJ(int i) {
        this.zzd = (this.zzd & Integer.MIN_VALUE) | Integer.MAX_VALUE;
    }

    @Override // com.google.android.libraries.places.internal.zzahw
    public final void zzK(zzagb zzagbVar) throws IOException {
        zzaie.zza().zzb(getClass()).zzf(this, zzagc.zza(zzagbVar));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final boolean zzL() {
        return (this.zzd & Integer.MIN_VALUE) != 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public abstract Object zzb(int i, Object obj, Object obj2);

    @Override // com.google.android.libraries.places.internal.zzahx
    public final /* synthetic */ zzahw zzt() {
        return (zzago) zzb(6, null, null);
    }

    final int zzu() {
        return zzaie.zza().zzb(getClass()).zzb(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final zzagl zzw() {
        return (zzagl) zzb(5, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzago zzy() {
        return (zzago) zzb(4, null, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.google.android.libraries.places.internal.zzafg
    public final int zzr(zzaih zzaihVar) {
        if (zzL()) {
            int zza = zza(zzaihVar);
            if (zza >= 0) {
                return zza;
            }
            throw new IllegalStateException("serialized size must be non-negative, was " + zza);
        }
        int i = this.zzd & Integer.MAX_VALUE;
        if (i != Integer.MAX_VALUE) {
            return i;
        }
        int zza2 = zza(zzaihVar);
        if (zza2 >= 0) {
            this.zzd = (this.zzd & Integer.MIN_VALUE) | zza2;
            return zza2;
        }
        throw new IllegalStateException("serialized size must be non-negative, was " + zza2);
    }

    @Override // com.google.android.libraries.places.internal.zzahw
    public final int zzv() {
        int i;
        if (zzL()) {
            i = zza(null);
            if (i < 0) {
                throw new IllegalStateException("serialized size must be non-negative, was " + i);
            }
        } else {
            i = this.zzd & Integer.MAX_VALUE;
            if (i == Integer.MAX_VALUE) {
                i = zza(null);
                if (i >= 0) {
                    this.zzd = (this.zzd & Integer.MIN_VALUE) | i;
                } else {
                    throw new IllegalStateException("serialized size must be non-negative, was " + i);
                }
            }
        }
        return i;
    }
}
