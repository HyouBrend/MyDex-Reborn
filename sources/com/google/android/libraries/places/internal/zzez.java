package com.google.android.libraries.places.internal;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
final class zzez {
    private String description;
    private Integer distanceMeters;
    private zzb[] matchedSubstrings;
    private String placeId;
    private zza structuredFormatting;
    private String[] types;

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    class zza {
        private String mainText;
        private zzb[] mainTextMatchedSubstrings;
        private String secondaryText;
        private zzb[] secondaryTextMatchedSubstrings;

        zza() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzkh zza() {
            zzb[] zzbVarArr = this.mainTextMatchedSubstrings;
            if (zzbVarArr != null) {
                return zzkh.zzk(zzbVarArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzkh zzb() {
            zzb[] zzbVarArr = this.secondaryTextMatchedSubstrings;
            if (zzbVarArr != null) {
                return zzkh.zzk(zzbVarArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zzc() {
            return this.mainText;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zzd() {
            return this.secondaryText;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    class zzb {
        Integer length;
        Integer offset;

        zzb() {
        }
    }

    zzez() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zza zza() {
        return this.structuredFormatting;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzkh zzb() {
        zzb[] zzbVarArr = this.matchedSubstrings;
        if (zzbVarArr != null) {
            return zzkh.zzk(zzbVarArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzkh zzc() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzkh.zzk(strArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Integer zzd() {
        return this.distanceMeters;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zze() {
        return this.description;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzf() {
        return this.placeId;
    }
}
