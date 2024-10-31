package com.google.android.gms.internal.places;

import java.io.IOException;

/* loaded from: classes.dex */
interface zzda<T> {
    boolean equals(T t, T t2);

    int hashCode(T t);

    T newInstance();

    void zzb(T t, zzel zzelVar) throws IOException;

    void zzb(T t, byte[] bArr, int i, int i2, zzr zzrVar) throws IOException;

    void zzd(T t);

    void zzd(T t, T t2);

    int zzn(T t);

    boolean zzp(T t);
}
