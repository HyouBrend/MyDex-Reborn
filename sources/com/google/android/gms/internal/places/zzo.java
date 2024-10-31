package com.google.android.gms.internal.places;

import com.google.android.gms.internal.places.zzm;
import com.google.android.gms.internal.places.zzo;

/* loaded from: classes.dex */
public abstract class zzo<MessageType extends zzm<MessageType, BuilderType>, BuilderType extends zzo<MessageType, BuilderType>> implements zzcj {
    protected abstract BuilderType zzb(MessageType messagetype);

    @Override // 
    /* renamed from: zzx, reason: merged with bridge method [inline-methods] */
    public abstract BuilderType clone();

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.google.android.gms.internal.places.zzcj
    public final /* synthetic */ zzcj zzb(zzck zzckVar) {
        if (!zzbg().getClass().isInstance(zzckVar)) {
            throw new IllegalArgumentException("mergeFrom(MessageLite) can only merge messages of the same type.");
        }
        return zzb((zzo<MessageType, BuilderType>) zzckVar);
    }
}
