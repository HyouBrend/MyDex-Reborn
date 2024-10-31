package com.google.android.gms.internal.places;

/* loaded from: classes.dex */
final class zzdo {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static String zzd(zzw zzwVar) {
        zzdn zzdnVar = new zzdn(zzwVar);
        StringBuilder sb = new StringBuilder(zzdnVar.size());
        for (int i = 0; i < zzdnVar.size(); i++) {
            byte zzi = zzdnVar.zzi(i);
            if (zzi == 34) {
                sb.append("\\\"");
            } else if (zzi == 39) {
                sb.append("\\'");
            } else if (zzi != 92) {
                switch (zzi) {
                    case 7:
                        sb.append("\\a");
                        break;
                    case 8:
                        sb.append("\\b");
                        break;
                    case 9:
                        sb.append("\\t");
                        break;
                    case 10:
                        sb.append("\\n");
                        break;
                    case 11:
                        sb.append("\\v");
                        break;
                    case 12:
                        sb.append("\\f");
                        break;
                    case 13:
                        sb.append("\\r");
                        break;
                    default:
                        if (zzi >= 32 && zzi <= 126) {
                            sb.append((char) zzi);
                            break;
                        } else {
                            sb.append('\\');
                            sb.append((char) (((zzi >>> 6) & 3) + 48));
                            sb.append((char) (((zzi >>> 3) & 7) + 48));
                            sb.append((char) ((zzi & 7) + 48));
                            break;
                        }
                }
            } else {
                sb.append("\\\\");
            }
        }
        return sb.toString();
    }
}
