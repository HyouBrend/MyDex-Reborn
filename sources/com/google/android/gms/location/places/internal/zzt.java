package com.google.android.gms.location.places.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.location.places.AddPlaceRequest;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

/* loaded from: classes.dex */
public interface zzt extends IInterface {
    void zzb(AddPlaceRequest addPlaceRequest, zzau zzauVar, zzx zzxVar) throws RemoteException;

    void zzb(String str, int i, int i2, int i3, zzau zzauVar, zzv zzvVar) throws RemoteException;

    void zzb(String str, zzau zzauVar, zzv zzvVar) throws RemoteException;

    void zzb(String str, LatLngBounds latLngBounds, int i, AutocompleteFilter autocompleteFilter, zzau zzauVar, zzx zzxVar) throws RemoteException;

    void zzb(List<String> list, zzau zzauVar, zzx zzxVar) throws RemoteException;
}
