package com.google.android.gms.internal.places;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.text.Typography;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes.dex */
public final class zzcl {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzb(zzck zzckVar, String str) {
        StringBuilder sb = new StringBuilder();
        sb.append("# ");
        sb.append(str);
        zzb(zzckVar, sb, 0);
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:100:0x0227, code lost:
    
        if (((java.lang.Double) r11).doubleValue() == com.github.mikephil.charting.utils.Utils.DOUBLE_EPSILON) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:78:0x01f2, code lost:
    
        if (((java.lang.Boolean) r11).booleanValue() == false) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x01f4, code lost:
    
        r7 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:92:0x0204, code lost:
    
        if (((java.lang.Integer) r11).intValue() == 0) goto L80;
     */
    /* JADX WARN: Code restructure failed: missing block: B:96:0x0215, code lost:
    
        if (((java.lang.Float) r11).floatValue() == 0.0f) goto L80;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static void zzb(com.google.android.gms.internal.places.zzck r18, java.lang.StringBuilder r19, int r20) {
        /*
            Method dump skipped, instructions count: 677
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.places.zzcl.zzb(com.google.android.gms.internal.places.zzck, java.lang.StringBuilder, int):void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static final void zzb(StringBuilder sb, int i, String str, Object obj) {
        if (obj instanceof List) {
            Iterator it = ((List) obj).iterator();
            while (it.hasNext()) {
                zzb(sb, i, str, it.next());
            }
            return;
        }
        if (obj instanceof Map) {
            Iterator it2 = ((Map) obj).entrySet().iterator();
            while (it2.hasNext()) {
                zzb(sb, i, str, (Map.Entry) it2.next());
            }
            return;
        }
        sb.append('\n');
        int i2 = 0;
        for (int i3 = 0; i3 < i; i3++) {
            sb.append(' ');
        }
        sb.append(str);
        if (obj instanceof String) {
            sb.append(": \"");
            sb.append(zzdo.zzd(zzw.zzi((String) obj)));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzw) {
            sb.append(": \"");
            sb.append(zzdo.zzd((zzw) obj));
            sb.append(Typography.quote);
            return;
        }
        if (obj instanceof zzbc) {
            sb.append(" {");
            zzb((zzbc) obj, sb, i + 2);
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        if (obj instanceof Map.Entry) {
            sb.append(" {");
            Map.Entry entry = (Map.Entry) obj;
            int i4 = i + 2;
            zzb(sb, i4, "key", entry.getKey());
            zzb(sb, i4, "value", entry.getValue());
            sb.append("\n");
            while (i2 < i) {
                sb.append(' ');
                i2++;
            }
            sb.append("}");
            return;
        }
        sb.append(": ");
        sb.append(obj.toString());
    }

    private static final String zzl(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                sb.append("_");
            }
            sb.append(Character.toLowerCase(charAt));
        }
        return sb.toString();
    }
}
