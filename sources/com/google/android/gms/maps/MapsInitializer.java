package com.google.android.gms.maps;

import android.content.Context;

/* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
/* loaded from: classes.dex */
public final class MapsInitializer {
    private static final String zza = "MapsInitializer";
    private static boolean zzb = false;
    private static Renderer zzc = Renderer.LEGACY;

    /* compiled from: com.google.android.gms:play-services-maps@@18.1.0 */
    /* loaded from: classes.dex */
    public enum Renderer {
        LEGACY,
        LATEST
    }

    private MapsInitializer() {
    }

    public static synchronized int initialize(Context context) {
        int initialize;
        synchronized (MapsInitializer.class) {
            initialize = initialize(context, null, null);
        }
        return initialize;
    }

    /* JADX WARN: Can't wrap try/catch for region: R(16:11|12|14|15|16|17|(10:19|(1:(1:22))|23|24|(1:26)|27|28|(1:30)|31|32)|36|23|24|(0)|27|28|(0)|31|32) */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x005f, code lost:
    
        r5 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0060, code lost:
    
        android.util.Log.e(com.google.android.gms.maps.MapsInitializer.zza, "Failed to retrieve renderer type or log initialization.", r5);
     */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0053 A[Catch: RemoteException -> 0x005f, all -> 0x0091, TryCatch #1 {RemoteException -> 0x005f, blocks: (B:24:0x004d, B:26:0x0053, B:27:0x0057), top: B:23:0x004d, outer: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x007e A[Catch: all -> 0x0091, TRY_LEAVE, TryCatch #0 {, blocks: (B:4:0x0003, B:7:0x0022, B:12:0x0029, B:15:0x002d, B:17:0x003c, B:19:0x0041, B:24:0x004d, B:26:0x0053, B:27:0x0057, B:28:0x0067, B:30:0x007e, B:35:0x0060, B:39:0x0086, B:40:0x008b, B:42:0x008d), top: B:3:0x0003, inners: #1, #2, #3 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized int initialize(android.content.Context r5, com.google.android.gms.maps.MapsInitializer.Renderer r6, com.google.android.gms.maps.OnMapsSdkInitializedCallback r7) {
        /*
            java.lang.Class<com.google.android.gms.maps.MapsInitializer> r0 = com.google.android.gms.maps.MapsInitializer.class
            monitor-enter(r0)
            java.lang.String r1 = "Context is null"
            com.google.android.gms.common.internal.Preconditions.checkNotNull(r5, r1)     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L91
            java.lang.String r2 = "preferredRenderer: "
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch: java.lang.Throwable -> L91
            java.lang.String r3 = java.lang.String.valueOf(r3)     // Catch: java.lang.Throwable -> L91
            java.lang.String r2 = r2.concat(r3)     // Catch: java.lang.Throwable -> L91
            android.util.Log.d(r1, r2)     // Catch: java.lang.Throwable -> L91
            boolean r1 = com.google.android.gms.maps.MapsInitializer.zzb     // Catch: java.lang.Throwable -> L91
            r2 = 0
            if (r1 == 0) goto L29
            if (r7 == 0) goto L27
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L91
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L91
        L27:
            monitor-exit(r0)
            return r2
        L29:
            com.google.android.gms.maps.internal.zzf r1 = com.google.android.gms.maps.internal.zzcb.zza(r5, r6)     // Catch: com.google.android.gms.common.GooglePlayServicesNotAvailableException -> L8c java.lang.Throwable -> L91
            com.google.android.gms.maps.internal.ICameraUpdateFactoryDelegate r3 = r1.zze()     // Catch: android.os.RemoteException -> L85 java.lang.Throwable -> L91
            com.google.android.gms.maps.CameraUpdateFactory.zza(r3)     // Catch: android.os.RemoteException -> L85 java.lang.Throwable -> L91
            com.google.android.gms.internal.maps.zzi r3 = r1.zzj()     // Catch: android.os.RemoteException -> L85 java.lang.Throwable -> L91
            com.google.android.gms.maps.model.BitmapDescriptorFactory.zza(r3)     // Catch: android.os.RemoteException -> L85 java.lang.Throwable -> L91
            r3 = 1
            com.google.android.gms.maps.MapsInitializer.zzb = r3     // Catch: java.lang.Throwable -> L91
            r4 = 2
            if (r6 == 0) goto L4c
            int r6 = r6.ordinal()     // Catch: java.lang.Throwable -> L91
            if (r6 == 0) goto L4d
            if (r6 == r3) goto L4a
            goto L4c
        L4a:
            r3 = 2
            goto L4d
        L4c:
            r3 = 0
        L4d:
            int r6 = r1.zzd()     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
            if (r6 != r4) goto L57
            com.google.android.gms.maps.MapsInitializer$Renderer r6 = com.google.android.gms.maps.MapsInitializer.Renderer.LATEST     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
            com.google.android.gms.maps.MapsInitializer.zzc = r6     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
        L57:
            com.google.android.gms.dynamic.IObjectWrapper r5 = com.google.android.gms.dynamic.ObjectWrapper.wrap(r5)     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
            r1.zzl(r5, r3)     // Catch: android.os.RemoteException -> L5f java.lang.Throwable -> L91
            goto L67
        L5f:
            r5 = move-exception
            java.lang.String r6 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = "Failed to retrieve renderer type or log initialization."
            android.util.Log.e(r6, r1, r5)     // Catch: java.lang.Throwable -> L91
        L67:
            java.lang.String r5 = com.google.android.gms.maps.MapsInitializer.zza     // Catch: java.lang.Throwable -> L91
            java.lang.String r6 = "loadedRenderer: "
            com.google.android.gms.maps.MapsInitializer$Renderer r1 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L91
            java.lang.String r1 = java.lang.String.valueOf(r1)     // Catch: java.lang.Throwable -> L91
            java.lang.String r6 = r6.concat(r1)     // Catch: java.lang.Throwable -> L91
            android.util.Log.d(r5, r6)     // Catch: java.lang.Throwable -> L91
            if (r7 == 0) goto L83
            com.google.android.gms.maps.MapsInitializer$Renderer r5 = com.google.android.gms.maps.MapsInitializer.zzc     // Catch: java.lang.Throwable -> L91
            r7.onMapsSdkInitialized(r5)     // Catch: java.lang.Throwable -> L91
        L83:
            monitor-exit(r0)
            return r2
        L85:
            r5 = move-exception
            com.google.android.gms.maps.model.RuntimeRemoteException r6 = new com.google.android.gms.maps.model.RuntimeRemoteException     // Catch: java.lang.Throwable -> L91
            r6.<init>(r5)     // Catch: java.lang.Throwable -> L91
            throw r6     // Catch: java.lang.Throwable -> L91
        L8c:
            r5 = move-exception
            int r5 = r5.errorCode     // Catch: java.lang.Throwable -> L91
            monitor-exit(r0)
            return r5
        L91:
            r5 = move-exception
            monitor-exit(r0)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.maps.MapsInitializer.initialize(android.content.Context, com.google.android.gms.maps.MapsInitializer$Renderer, com.google.android.gms.maps.OnMapsSdkInitializedCallback):int");
    }
}
