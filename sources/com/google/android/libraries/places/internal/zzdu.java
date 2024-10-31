package com.google.android.libraries.places.internal;

import android.content.Context;
import java.util.concurrent.TimeUnit;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzdu {
    public static final /* synthetic */ int zza = 0;
    private static final long zzb = TimeUnit.MINUTES.toMicros(1);
    private final zzde zzc;
    private final Context zzd;

    /* JADX INFO: Access modifiers changed from: package-private */
    public zzdu(Context context, zzde zzdeVar) {
        this.zzd = context;
        this.zzc = zzdeVar;
    }

    /* JADX WARN: Code restructure failed: missing block: B:31:0x0080, code lost:
    
        if (r10.contains("_optout") == false) goto L23;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final com.google.android.libraries.places.internal.zzkh zza(java.lang.String r14) {
        /*
            r13 = this;
            android.content.Context r14 = r13.zzd
            java.lang.String r0 = "wifi"
            java.lang.Object r14 = r14.getSystemService(r0)
            android.net.wifi.WifiManager r14 = (android.net.wifi.WifiManager) r14
            if (r14 == 0) goto La6
            boolean r0 = r14.isWifiEnabled()
            if (r0 != 0) goto L14
            goto La6
        L14:
            java.util.List r0 = r14.getScanResults()
            if (r0 == 0) goto La1
            boolean r1 = r0.isEmpty()
            if (r1 == 0) goto L22
            goto La1
        L22:
            com.google.android.libraries.places.internal.zzdt r1 = new java.util.Comparator() { // from class: com.google.android.libraries.places.internal.zzdt
                static {
                    /*
                        com.google.android.libraries.places.internal.zzdt r0 = new com.google.android.libraries.places.internal.zzdt
                        r0.<init>()
                        
                        // error: 0x0005: SPUT (r0 I:com.google.android.libraries.places.internal.zzdt) com.google.android.libraries.places.internal.zzdt.zza com.google.android.libraries.places.internal.zzdt
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzdt.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzdt.<init>():void");
                }

                @Override // java.util.Comparator
                public final int compare(java.lang.Object r2, java.lang.Object r3) {
                    /*
                        r1 = this;
                        android.net.wifi.ScanResult r2 = (android.net.wifi.ScanResult) r2
                        android.net.wifi.ScanResult r3 = (android.net.wifi.ScanResult) r3
                        int r0 = com.google.android.libraries.places.internal.zzdu.zza
                        int r3 = r3.level
                        int r2 = r2.level
                        int r3 = r3 - r2
                        return r3
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzdt.compare(java.lang.Object, java.lang.Object):int");
                }
            }
            com.google.android.libraries.places.internal.zzks r1 = com.google.android.libraries.places.internal.zzks.zza(r1)
            com.google.android.libraries.places.internal.zzkh r0 = com.google.android.libraries.places.internal.zzkh.zzo(r1, r0)
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            android.net.wifi.WifiInfo r14 = r14.getConnectionInfo()
            int r2 = r0.size()
            r3 = 0
            r4 = 0
        L3b:
            if (r4 >= r2) goto L9c
            java.lang.Object r5 = r0.get(r4)
            android.net.wifi.ScanResult r5 = (android.net.wifi.ScanResult) r5
            if (r5 == 0) goto L99
            java.lang.String r6 = r5.SSID
            boolean r6 = android.text.TextUtils.isEmpty(r6)
            if (r6 == 0) goto L4e
            goto L99
        L4e:
            com.google.android.libraries.places.internal.zzde r6 = r13.zzc
            long r6 = r6.zza()
            r8 = 1000(0x3e8, double:4.94E-321)
            long r6 = r6 * r8
            long r8 = r5.timestamp
            long r6 = r6 - r8
            long r8 = com.google.android.libraries.places.internal.zzdu.zzb
            java.lang.String r10 = r5.SSID
            if (r10 == 0) goto L91
            r11 = 95
            int r11 = r10.indexOf(r11)
            r12 = 1
            if (r11 >= 0) goto L6c
        L6a:
            r12 = 0
            goto L82
        L6c:
            java.util.Locale r11 = java.util.Locale.ENGLISH
            java.lang.String r10 = r10.toLowerCase(r11)
            java.lang.String r11 = "_nomap"
            boolean r11 = r10.contains(r11)
            if (r11 != 0) goto L82
            java.lang.String r11 = "_optout"
            boolean r10 = r10.contains(r11)
            if (r10 == 0) goto L6a
        L82:
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 > 0) goto L99
            if (r12 != 0) goto L99
            com.google.android.libraries.places.internal.zzds r6 = new com.google.android.libraries.places.internal.zzds
            r6.<init>(r14, r5)
            r1.add(r6)
            goto L99
        L91:
            java.lang.IllegalArgumentException r14 = new java.lang.IllegalArgumentException
            java.lang.String r0 = "Null SSID."
            r14.<init>(r0)
            throw r14
        L99:
            int r4 = r4 + 1
            goto L3b
        L9c:
            com.google.android.libraries.places.internal.zzkh r14 = com.google.android.libraries.places.internal.zzkh.zzj(r1)
            return r14
        La1:
            com.google.android.libraries.places.internal.zzkh r14 = com.google.android.libraries.places.internal.zzkh.zzl()
            return r14
        La6:
            com.google.android.libraries.places.internal.zzkh r14 = com.google.android.libraries.places.internal.zzkh.zzl()
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzdu.zza(java.lang.String):com.google.android.libraries.places.internal.zzkh");
    }
}
