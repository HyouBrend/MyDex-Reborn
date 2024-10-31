package com.google.android.libraries.places.internal;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.util.Log;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzhd {
    public static String zza(PackageManager packageManager, String str) {
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(str, 64);
            if (packageInfo != null && packageInfo.signatures != null && packageInfo.signatures.length != 0 && packageInfo.signatures[0] != null) {
                return zzb(packageInfo.signatures[0]);
            }
            return null;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("CredentialsHelper", "Unable to get certificate fingerprint for package: ".concat(String.valueOf(str)), e);
            return null;
        }
    }

    private static String zzb(Signature signature) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-1").digest(signature.toByteArray());
            return zzns.zzd().zze(digest, 0, digest.length);
        } catch (NoSuchAlgorithmException e) {
            Log.e("CredentialsHelper", "Unable to get certificate fingerprint.", e);
            return null;
        }
    }
}
