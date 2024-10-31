package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzahd extends zzafh implements RandomAccess, zzahe {

    @Deprecated
    public static final zzahe zza;
    private static final zzahd zzb;
    private final List zzc;

    static {
        zzahd zzahdVar = new zzahd(false);
        zzb = zzahdVar;
        zza = zzahdVar;
    }

    public zzahd() {
        this(10);
    }

    private static String zzi(Object obj) {
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzaft) {
            return ((zzaft) obj).zzl(zzagx.zzb);
        }
        return zzagx.zzd((byte[]) obj);
    }

    @Override // com.google.android.libraries.places.internal.zzafh, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ void add(int i, Object obj) {
        zza();
        this.zzc.add(i, (String) obj);
        this.modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzafh, java.util.AbstractList, java.util.List
    public final boolean addAll(int i, Collection collection) {
        zza();
        if (collection instanceof zzahe) {
            collection = ((zzahe) collection).zzh();
        }
        boolean addAll = this.zzc.addAll(i, collection);
        this.modCount++;
        return addAll;
    }

    @Override // com.google.android.libraries.places.internal.zzafh, java.util.AbstractList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final void clear() {
        zza();
        this.zzc.clear();
        this.modCount++;
    }

    @Override // com.google.android.libraries.places.internal.zzafh, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object remove(int i) {
        zza();
        Object remove = this.zzc.remove(i);
        this.modCount++;
        return zzi(remove);
    }

    @Override // com.google.android.libraries.places.internal.zzafh, java.util.AbstractList, java.util.List
    public final /* bridge */ /* synthetic */ Object set(int i, Object obj) {
        zza();
        return zzi(this.zzc.set(i, (String) obj));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.zzc.size();
    }

    @Override // com.google.android.libraries.places.internal.zzahe
    public final zzahe zzd() {
        return zzc() ? new zzajd(this) : this;
    }

    @Override // com.google.android.libraries.places.internal.zzahe
    public final Object zze(int i) {
        return this.zzc.get(i);
    }

    @Override // com.google.android.libraries.places.internal.zzagw
    public final /* bridge */ /* synthetic */ zzagw zzf(int i) {
        if (i < size()) {
            throw new IllegalArgumentException();
        }
        ArrayList arrayList = new ArrayList(i);
        arrayList.addAll(this.zzc);
        return new zzahd(arrayList);
    }

    @Override // java.util.AbstractList, java.util.List
    /* renamed from: zzg, reason: merged with bridge method [inline-methods] */
    public final String get(int i) {
        Object obj = this.zzc.get(i);
        if (obj instanceof String) {
            return (String) obj;
        }
        if (obj instanceof zzaft) {
            zzaft zzaftVar = (zzaft) obj;
            String zzl = zzaftVar.zzl(zzagx.zzb);
            if (zzaftVar.zzi()) {
                this.zzc.set(i, zzl);
            }
            return zzl;
        }
        byte[] bArr = (byte[]) obj;
        String zzd = zzagx.zzd(bArr);
        if (zzajm.zzd(bArr)) {
            this.zzc.set(i, zzd);
        }
        return zzd;
    }

    @Override // com.google.android.libraries.places.internal.zzahe
    public final List zzh() {
        return Collections.unmodifiableList(this.zzc);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public zzahd(int i) {
        super(true);
        ArrayList arrayList = new ArrayList(i);
        this.zzc = arrayList;
    }

    private zzahd(ArrayList arrayList) {
        super(true);
        this.zzc = arrayList;
    }

    private zzahd(boolean z) {
        super(false);
        this.zzc = Collections.emptyList();
    }

    @Override // com.google.android.libraries.places.internal.zzafh, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final boolean addAll(Collection collection) {
        return addAll(size(), collection);
    }
}
