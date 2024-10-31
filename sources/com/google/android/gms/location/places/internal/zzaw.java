package com.google.android.gms.location.places.internal;

import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.data.DataBufferRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelableSerializer;
import com.google.android.gms.internal.places.zzbk;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class zzaw extends DataBufferRef {
    public zzaw(DataHolder dataHolder, int i) {
        super(dataHolder, i);
    }

    private final byte[] zzb(String str, byte[] bArr) {
        if (!hasColumn(str) || hasNull(str)) {
            return null;
        }
        return getByteArray(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final float zzb(String str, float f) {
        return (!hasColumn(str) || hasNull(str)) ? f : getFloat(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<Integer> zzb(String str, List<Integer> list) {
        byte[] zzb = zzb(str, (byte[]) null);
        if (zzb == null) {
            return list;
        }
        try {
            com.google.android.gms.internal.places.zzl zzb2 = com.google.android.gms.internal.places.zzl.zzb(zzb);
            return zzb2.zzr() == 0 ? list : zzb2.zzq();
        } catch (zzbk e) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
                Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
            }
            return list;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final int zzc(String str, int i) {
        return (!hasColumn(str) || hasNull(str)) ? i : getInteger(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <E extends SafeParcelable> List<E> zzb(String str, Parcelable.Creator<E> creator, List<E> list) {
        byte[] zzb = zzb(str, (byte[]) null);
        if (zzb == null) {
            return list;
        }
        try {
            com.google.android.gms.internal.places.zzl zzb2 = com.google.android.gms.internal.places.zzl.zzb(zzb);
            if (zzb2.zzt() == 0) {
                return list;
            }
            ArrayList arrayList = new ArrayList(zzb2.zzt());
            Iterator<com.google.android.gms.internal.places.zzw> it = zzb2.zzs().iterator();
            while (it.hasNext()) {
                arrayList.add(SafeParcelableSerializer.deserializeFromBytes(it.next().toByteArray(), creator));
            }
            return arrayList;
        } catch (zzbk e) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
                Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
            }
            return list;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final <E extends SafeParcelable> E zzb(String str, Parcelable.Creator<E> creator) {
        byte[] zzb = zzb(str, (byte[]) null);
        if (zzb == null) {
            return null;
        }
        return (E) SafeParcelableSerializer.deserializeFromBytes(zzb, creator);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final List<String> zzc(String str, List<String> list) {
        byte[] zzb = zzb(str, (byte[]) null);
        if (zzb == null) {
            return list;
        }
        try {
            com.google.android.gms.internal.places.zzl zzb2 = com.google.android.gms.internal.places.zzl.zzb(zzb);
            return zzb2.zzp() == 0 ? list : zzb2.zzo();
        } catch (zzbk e) {
            if (Log.isLoggable("SafeDataBufferRef", 6)) {
                Log.e("SafeDataBufferRef", "Cannot parse byte[]", e);
            }
            return list;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final String zzb(String str, String str2) {
        return (!hasColumn(str) || hasNull(str)) ? str2 : getString(str);
    }
}
