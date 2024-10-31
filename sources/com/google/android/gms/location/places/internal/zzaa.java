package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

/* loaded from: classes.dex */
public abstract class zzaa extends com.google.android.gms.internal.places.zzb implements zzx {
    public zzaa() {
        super("com.google.android.gms.location.places.internal.IPlacesCallbacks");
    }

    @Override // com.google.android.gms.internal.places.zzb
    protected final boolean dispatchTransaction(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i == 1) {
            zzb((DataHolder) com.google.android.gms.internal.places.zze.zzb(parcel, DataHolder.CREATOR));
        } else if (i == 2) {
            zzc((DataHolder) com.google.android.gms.internal.places.zze.zzb(parcel, DataHolder.CREATOR));
        } else if (i == 3) {
            zzd((DataHolder) com.google.android.gms.internal.places.zze.zzb(parcel, DataHolder.CREATOR));
        } else if (i == 4) {
            zzb((Status) com.google.android.gms.internal.places.zze.zzb(parcel, Status.CREATOR));
        } else {
            if (i != 5) {
                return false;
            }
            zze((DataHolder) com.google.android.gms.internal.places.zze.zzb(parcel, DataHolder.CREATOR));
        }
        return true;
    }
}
