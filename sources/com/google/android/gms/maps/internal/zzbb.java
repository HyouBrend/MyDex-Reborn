package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Parcel;
import android.os.RemoteException;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes.dex */
public abstract class zzbb extends com.google.android.gms.internal.maps.zzb implements zzbc {
    public zzbb() {
        super("com.google.android.gms.maps.internal.IOnMyLocationClickListener");
    }

    @Override // com.google.android.gms.internal.maps.zzb
    protected final boolean zza(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            return false;
        }
        Location location = (Location) com.google.android.gms.internal.maps.zzc.zza(parcel, Location.CREATOR);
        com.google.android.gms.internal.maps.zzc.zzc(parcel);
        zzb(location);
        parcel2.writeNoException();
        return true;
    }
}
