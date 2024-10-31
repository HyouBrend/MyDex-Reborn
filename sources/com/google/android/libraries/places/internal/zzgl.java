package com.google.android.libraries.places.internal;

import android.location.Location;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.LocationBias;
import com.google.android.libraries.places.api.model.LocationRestriction;
import com.google.android.libraries.places.api.model.RectangularBounds;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.IOException;
import java.util.Iterator;
import java.util.Locale;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzgl {
    private static final zzkk zza;

    static {
        zzkj zzkjVar = new zzkj();
        zzkjVar.zza(zzdr.NONE, "NONE");
        zzkjVar.zza(zzdr.PSK, "WPA_PSK");
        zzkjVar.zza(zzdr.EAP, "WPA_EAP");
        zzkjVar.zza(zzdr.OTHER, "SECURED_NONE");
        zza = zzkjVar.zzb();
    }

    public static String zza(Location location) {
        if (location == null) {
            return null;
        }
        return zzf(location.getLatitude(), location.getLongitude());
    }

    public static String zzb(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        return zzf(latLng.latitude, latLng.longitude);
    }

    public static String zze(zzkh zzkhVar, int i) {
        StringBuilder sb = new StringBuilder();
        int size = zzkhVar.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzds zzdsVar = (zzds) zzkhVar.get(i2);
            int length = sb.length();
            zzkj zzkjVar = new zzkj();
            zzkjVar.zza("mac", zzdsVar.zzd());
            zzkjVar.zza("strength_dbm", Integer.valueOf(zzdsVar.zzb()));
            zzkjVar.zza("wifi_auth_type", zza.get(zzdsVar.zzc()));
            zzkjVar.zza("is_connected", Boolean.valueOf(zzdsVar.zze()));
            zzkjVar.zza("frequency_mhz", Integer.valueOf(zzdsVar.zza()));
            zzkk zzb = zzkjVar.zzb();
            zzjk zzb2 = zzjk.zzb(",");
            Iterator<E> it = zzb.entrySet().iterator();
            StringBuilder sb2 = new StringBuilder();
            try {
                zzji.zza(sb2, it, zzb2, SimpleComparison.EQUAL_TO_OPERATION);
                String sb3 = sb2.toString();
                int length2 = sb.length();
                String concat = (length > 0 ? "|" : "").concat(sb3);
                if (length2 + concat.length() > 4000) {
                    break;
                }
                sb.append(concat);
            } catch (IOException e) {
                throw new AssertionError(e);
            }
        }
        return sb.toString();
    }

    private static String zzf(double d, double d2) {
        return String.format(Locale.US, "%.15f,%.15f", Double.valueOf(d), Double.valueOf(d2));
    }

    private static String zzg(RectangularBounds rectangularBounds) {
        LatLng southwest = rectangularBounds.getSouthwest();
        double d = southwest.latitude;
        double d2 = southwest.longitude;
        LatLng northeast = rectangularBounds.getNortheast();
        return String.format(Locale.US, "rectangle:%.15f,%.15f|%.15f,%.15f", Double.valueOf(d), Double.valueOf(d2), Double.valueOf(northeast.latitude), Double.valueOf(northeast.longitude));
    }

    public static String zzc(LocationBias locationBias) {
        if (locationBias == null) {
            return null;
        }
        if (locationBias instanceof RectangularBounds) {
            return zzg((RectangularBounds) locationBias);
        }
        throw new AssertionError("Unknown LocationBias type.");
    }

    public static String zzd(LocationRestriction locationRestriction) {
        if (locationRestriction == null) {
            return null;
        }
        if (locationRestriction instanceof RectangularBounds) {
            return zzg((RectangularBounds) locationRestriction);
        }
        throw new AssertionError("Unknown LocationRestriction type.");
    }
}
