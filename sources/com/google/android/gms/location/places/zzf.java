package com.google.android.gms.location.places;

import android.os.RemoteException;
import com.google.android.gms.location.places.internal.zzy;

/* loaded from: classes.dex */
public final class zzf extends zzy {
    private final zzg zzn;
    private final zze zzo;

    public zzf(zzg zzgVar) {
        this.zzn = zzgVar;
        this.zzo = null;
    }

    public zzf(zze zzeVar) {
        this.zzn = null;
        this.zzo = zzeVar;
    }

    @Override // com.google.android.gms.location.places.internal.zzv
    public final void zzb(PlacePhotoMetadataResult placePhotoMetadataResult) throws RemoteException {
        this.zzn.setResult((zzg) placePhotoMetadataResult);
    }

    @Override // com.google.android.gms.location.places.internal.zzv
    public final void zzb(PlacePhotoResult placePhotoResult) throws RemoteException {
        this.zzo.setResult((zze) placePhotoResult);
    }
}
