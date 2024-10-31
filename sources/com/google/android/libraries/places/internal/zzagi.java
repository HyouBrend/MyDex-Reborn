package com.google.android.libraries.places.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzagi {
    private static final zzagi zzb = new zzagi(true);
    final zzaiu zza = new zzaik(16);
    private boolean zzc;
    private boolean zzd;

    private zzagi() {
    }

    public static zzagi zza() {
        throw null;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARN: Failed to find 'out' block for switch in B:2:0x0015. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static final void zzd(com.google.android.libraries.places.internal.zzagh r4, java.lang.Object r5) {
        /*
            com.google.android.libraries.places.internal.zzajn r0 = r4.zzb()
            byte[] r1 = com.google.android.libraries.places.internal.zzagx.zzd
            r5.getClass()
            com.google.android.libraries.places.internal.zzajn r1 = com.google.android.libraries.places.internal.zzajn.DOUBLE
            com.google.android.libraries.places.internal.zzajo r1 = com.google.android.libraries.places.internal.zzajo.INT
            com.google.android.libraries.places.internal.zzajo r0 = r0.zza()
            int r0 = r0.ordinal()
            switch(r0) {
                case 0: goto L43;
                case 1: goto L40;
                case 2: goto L3d;
                case 3: goto L3a;
                case 4: goto L37;
                case 5: goto L34;
                case 6: goto L2b;
                case 7: goto L22;
                case 8: goto L19;
                default: goto L18;
            }
        L18:
            goto L48
        L19:
            boolean r0 = r5 instanceof com.google.android.libraries.places.internal.zzahw
            if (r0 != 0) goto L47
            boolean r0 = r5 instanceof com.google.android.libraries.places.internal.zzahb
            if (r0 == 0) goto L48
            goto L47
        L22:
            boolean r0 = r5 instanceof java.lang.Integer
            if (r0 != 0) goto L47
            boolean r0 = r5 instanceof com.google.android.libraries.places.internal.zzagq
            if (r0 == 0) goto L48
            goto L47
        L2b:
            boolean r0 = r5 instanceof com.google.android.libraries.places.internal.zzaft
            if (r0 != 0) goto L47
            boolean r0 = r5 instanceof byte[]
            if (r0 == 0) goto L48
            goto L47
        L34:
            boolean r0 = r5 instanceof java.lang.String
            goto L45
        L37:
            boolean r0 = r5 instanceof java.lang.Boolean
            goto L45
        L3a:
            boolean r0 = r5 instanceof java.lang.Double
            goto L45
        L3d:
            boolean r0 = r5 instanceof java.lang.Float
            goto L45
        L40:
            boolean r0 = r5 instanceof java.lang.Long
            goto L45
        L43:
            boolean r0 = r5 instanceof java.lang.Integer
        L45:
            if (r0 == 0) goto L48
        L47:
            return
        L48:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            int r3 = r4.zza()
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r1[r2] = r3
            r2 = 1
            com.google.android.libraries.places.internal.zzajn r4 = r4.zzb()
            com.google.android.libraries.places.internal.zzajo r4 = r4.zza()
            r1[r2] = r4
            r4 = 2
            java.lang.Class r5 = r5.getClass()
            java.lang.String r5 = r5.getName()
            r1[r4] = r5
            java.lang.String r4 = "Wrong object type used with protocol message reflection.\nField number: %d, field java type: %s, value type: %s\n"
            java.lang.String r4 = java.lang.String.format(r4, r1)
            r0.<init>(r4)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.libraries.places.internal.zzagi.zzd(com.google.android.libraries.places.internal.zzagh, java.lang.Object):void");
    }

    public final /* bridge */ /* synthetic */ Object clone() throws CloneNotSupportedException {
        zzagi zzagiVar = new zzagi();
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            zzagiVar.zzc((zzagh) zzg.getKey(), zzg.getValue());
        }
        for (Map.Entry entry : this.zza.zzc()) {
            zzagiVar.zzc((zzagh) entry.getKey(), entry.getValue());
        }
        zzagiVar.zzd = this.zzd;
        return zzagiVar;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof zzagi) {
            return this.zza.equals(((zzagi) obj).zza);
        }
        return false;
    }

    public final int hashCode() {
        return this.zza.hashCode();
    }

    public final void zzb() {
        if (this.zzc) {
            return;
        }
        for (int i = 0; i < this.zza.zzb(); i++) {
            Map.Entry zzg = this.zza.zzg(i);
            if (zzg.getValue() instanceof zzago) {
                ((zzago) zzg.getValue()).zzG();
            }
        }
        this.zza.zza();
        this.zzc = true;
    }

    public final void zzc(zzagh zzaghVar, Object obj) {
        if (zzaghVar.zzc()) {
            if (!(obj instanceof List)) {
                throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
            }
            ArrayList arrayList = new ArrayList();
            arrayList.addAll((List) obj);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                zzd(zzaghVar, arrayList.get(i));
            }
            obj = arrayList;
        } else {
            zzd(zzaghVar, obj);
        }
        if (obj instanceof zzahb) {
            this.zzd = true;
        }
        this.zza.put(zzaghVar, obj);
    }

    private zzagi(boolean z) {
        zzb();
        zzb();
    }
}
