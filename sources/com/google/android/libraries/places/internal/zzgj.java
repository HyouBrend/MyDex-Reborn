package com.google.android.libraries.places.internal;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzgj {
    private zza[] addressComponents;
    private String businessStatus;
    private Boolean curbsidePickup;
    private zzd currentOpeningHours;
    private Boolean delivery;
    private Boolean dineIn;
    private zzb editorialSummary;
    private String formattedAddress;
    private zzc geometry;
    private String icon;
    private String iconBackgroundColor;
    private String iconMaskBaseUri;
    private String internationalPhoneNumber;
    private String name;
    private zzd openingHours;
    private zze[] photos;
    private String placeId;
    private zzf plusCode;
    private Integer priceLevel;
    private Double rating;
    private Boolean reservable;
    private zzd[] secondaryOpeningHours;
    private Boolean servesBeer;
    private Boolean servesBreakfast;
    private Boolean servesBrunch;
    private Boolean servesDinner;
    private Boolean servesLunch;
    private Boolean servesVegetarianFood;
    private Boolean servesWine;
    private Boolean takeout;
    private String[] types;
    private Integer userRatingsTotal;
    private Integer utcOffset;
    private String website;
    private Boolean wheelchairAccessibleEntrance;

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    class zza {
        private String longName;
        private String shortName;
        private String[] types;

        zza() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzkh zza() {
            String[] strArr = this.types;
            if (strArr != null) {
                return zzkh.zzk(strArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zzb() {
            return this.longName;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zzc() {
            return this.shortName;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    class zzb {
        private String language;
        private String overview;

        zzb() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zza() {
            return this.language;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zzb() {
            return this.overview;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    class zzc {
        private zza location;
        private zzb viewport;

        /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
        /* loaded from: classes2.dex */
        class zza {
            private Double lat;
            private Double lng;

            zza() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final Double zza() {
                return this.lat;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final Double zzb() {
                return this.lng;
            }
        }

        /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
        /* loaded from: classes2.dex */
        class zzb {
            private zza northeast;
            private zza southwest;

            zzb() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final zza zza() {
                return this.northeast;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final zza zzb() {
                return this.southwest;
            }
        }

        zzc() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zza zza() {
            return this.location;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzb zzb() {
            return this.viewport;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    class zzd {
        private zza[] periods;
        private zzb[] specialDays;
        private String type;
        private String[] weekdayText;

        /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
        /* loaded from: classes2.dex */
        class zza {
            private zzc close;
            private zzc open;

            zza() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final zzc zza() {
                return this.close;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final zzc zzb() {
                return this.open;
            }
        }

        /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
        /* loaded from: classes2.dex */
        class zzb {
            private String date;
            private Boolean exceptionalHours;

            zzb() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final Boolean zza() {
                return this.exceptionalHours;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final String zzb() {
                return this.date;
            }
        }

        /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
        /* loaded from: classes2.dex */
        class zzc {
            private String date;
            private Integer day;
            private String time;
            private Boolean truncated;

            zzc() {
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final Boolean zza() {
                return this.truncated;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final Integer zzb() {
                return this.day;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final String zzc() {
                return this.date;
            }

            /* JADX INFO: Access modifiers changed from: package-private */
            public final String zzd() {
                return this.time;
            }
        }

        zzd() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzkh zza() {
            zza[] zzaVarArr = this.periods;
            if (zzaVarArr != null) {
                return zzkh.zzk(zzaVarArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzkh zzb() {
            zzb[] zzbVarArr = this.specialDays;
            if (zzbVarArr != null) {
                return zzkh.zzk(zzbVarArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzkh zzc() {
            String[] strArr = this.weekdayText;
            if (strArr != null) {
                return zzkh.zzk(strArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zzd() {
            return this.type;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    class zze {
        private Integer height;
        private String[] htmlAttributions;
        private String photoReference;
        private Integer width;

        zze() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final zzkh zza() {
            String[] strArr = this.htmlAttributions;
            if (strArr != null) {
                return zzkh.zzk(strArr);
            }
            return null;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Integer zzb() {
            return this.height;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final Integer zzc() {
            return this.width;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zzd() {
            return this.photoReference;
        }
    }

    /* compiled from: com.google.android.libraries.places:places@@3.2.0 */
    /* loaded from: classes2.dex */
    class zzf {
        private String compoundCode;
        private String globalCode;

        zzf() {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zza() {
            return this.compoundCode;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final String zzb() {
            return this.globalCode;
        }
    }

    zzgj() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzA() {
        return this.formattedAddress;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzB() {
        return this.iconBackgroundColor;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzC() {
        return this.iconMaskBaseUri;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzD() {
        return this.internationalPhoneNumber;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzE() {
        return this.name;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzF() {
        return this.placeId;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzG() {
        return this.website;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzb zza() {
        return this.editorialSummary;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzc zzb() {
        return this.geometry;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzd zzc() {
        return this.currentOpeningHours;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzd zzd() {
        return this.openingHours;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzf zze() {
        return this.plusCode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzkh zzf() {
        zza[] zzaVarArr = this.addressComponents;
        if (zzaVarArr != null) {
            return zzkh.zzk(zzaVarArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzkh zzg() {
        zze[] zzeVarArr = this.photos;
        if (zzeVarArr != null) {
            return zzkh.zzk(zzeVarArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzkh zzh() {
        zzd[] zzdVarArr = this.secondaryOpeningHours;
        if (zzdVarArr != null) {
            return zzkh.zzk(zzdVarArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final zzkh zzi() {
        String[] strArr = this.types;
        if (strArr != null) {
            return zzkh.zzk(strArr);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzj() {
        return this.curbsidePickup;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzk() {
        return this.delivery;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzl() {
        return this.dineIn;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzm() {
        return this.reservable;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzn() {
        return this.servesBeer;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzo() {
        return this.servesBreakfast;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzp() {
        return this.servesDinner;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzq() {
        return this.servesLunch;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzr() {
        return this.servesVegetarianFood;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzs() {
        return this.servesWine;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzt() {
        return this.takeout;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Boolean zzu() {
        return this.wheelchairAccessibleEntrance;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Double zzv() {
        return this.rating;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Integer zzw() {
        return this.priceLevel;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Integer zzx() {
        return this.userRatingsTotal;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final Integer zzy() {
        return this.utcOffset;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final String zzz() {
        return this.businessStatus;
    }
}
