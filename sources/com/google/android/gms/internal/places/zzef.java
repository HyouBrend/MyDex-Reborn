package com.google.android.gms.internal.places;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'zznr' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* loaded from: classes.dex */
public class zzef {
    public static final zzef zznj;
    public static final zzef zznk;
    public static final zzef zznl;
    public static final zzef zznm;
    public static final zzef zznn;
    public static final zzef zzno;
    public static final zzef zznp;
    public static final zzef zznq;
    public static final zzef zznr;
    public static final zzef zzns;
    public static final zzef zznt;
    public static final zzef zznu;
    public static final zzef zznv;
    public static final zzef zznw;
    public static final zzef zznx;
    public static final zzef zzny;
    public static final zzef zznz;
    public static final zzef zzoa;
    private static final /* synthetic */ zzef[] zzod;
    private final zzem zzob;
    private final int zzoc;

    public static zzef[] values() {
        return (zzef[]) zzod.clone();
    }

    private zzef(String str, int i, zzem zzemVar, int i2) {
        this.zzob = zzemVar;
        this.zzoc = i2;
    }

    public final zzem zzdr() {
        return this.zzob;
    }

    public final int zzds() {
        return this.zzoc;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public /* synthetic */ zzef(String str, int i, zzem zzemVar, int i2, zzeg zzegVar) {
        this(str, i, zzemVar, i2);
    }

    static {
        zzef zzefVar = new zzef("DOUBLE", 0, zzem.DOUBLE, 1);
        zznj = zzefVar;
        zzef zzefVar2 = new zzef("FLOAT", 1, zzem.FLOAT, 5);
        zznk = zzefVar2;
        final int i = 2;
        zzef zzefVar3 = new zzef("INT64", 2, zzem.LONG, 0);
        zznl = zzefVar3;
        final int i2 = 3;
        zzef zzefVar4 = new zzef("UINT64", 3, zzem.LONG, 0);
        zznm = zzefVar4;
        zzef zzefVar5 = new zzef("INT32", 4, zzem.INT, 0);
        zznn = zzefVar5;
        zzef zzefVar6 = new zzef("FIXED64", 5, zzem.LONG, 1);
        zzno = zzefVar6;
        zzef zzefVar7 = new zzef("FIXED32", 6, zzem.INT, 5);
        zznp = zzefVar7;
        zzef zzefVar8 = new zzef("BOOL", 7, zzem.BOOLEAN, 0);
        zznq = zzefVar8;
        final zzem zzemVar = zzem.STRING;
        final String str = "STRING";
        final int i3 = 8;
        zzef zzefVar9 = new zzef(str, i3, zzemVar, i) { // from class: com.google.android.gms.internal.places.zzei
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i4 = 8;
                int i5 = 2;
                zzeg zzegVar = null;
            }
        };
        zznr = zzefVar9;
        final zzem zzemVar2 = zzem.MESSAGE;
        final String str2 = "GROUP";
        final int i4 = 9;
        zzef zzefVar10 = new zzef(str2, i4, zzemVar2, i2) { // from class: com.google.android.gms.internal.places.zzeh
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i5 = 9;
                int i6 = 3;
                zzeg zzegVar = null;
            }
        };
        zzns = zzefVar10;
        final zzem zzemVar3 = zzem.MESSAGE;
        final String str3 = "MESSAGE";
        final int i5 = 10;
        zzef zzefVar11 = new zzef(str3, i5, zzemVar3, i) { // from class: com.google.android.gms.internal.places.zzek
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i6 = 10;
                int i7 = 2;
                zzeg zzegVar = null;
            }
        };
        zznt = zzefVar11;
        final zzem zzemVar4 = zzem.BYTE_STRING;
        final String str4 = "BYTES";
        final int i6 = 11;
        zzef zzefVar12 = new zzef(str4, i6, zzemVar4, i) { // from class: com.google.android.gms.internal.places.zzej
            /* JADX INFO: Access modifiers changed from: package-private */
            {
                int i7 = 11;
                int i8 = 2;
                zzeg zzegVar = null;
            }
        };
        zznu = zzefVar12;
        zzef zzefVar13 = new zzef("UINT32", 12, zzem.INT, 0);
        zznv = zzefVar13;
        zzef zzefVar14 = new zzef("ENUM", 13, zzem.ENUM, 0);
        zznw = zzefVar14;
        zzef zzefVar15 = new zzef("SFIXED32", 14, zzem.INT, 5);
        zznx = zzefVar15;
        zzef zzefVar16 = new zzef("SFIXED64", 15, zzem.LONG, 1);
        zzny = zzefVar16;
        zzef zzefVar17 = new zzef("SINT32", 16, zzem.INT, 0);
        zznz = zzefVar17;
        zzef zzefVar18 = new zzef("SINT64", 17, zzem.LONG, 0);
        zzoa = zzefVar18;
        zzod = new zzef[]{zzefVar, zzefVar2, zzefVar3, zzefVar4, zzefVar5, zzefVar6, zzefVar7, zzefVar8, zzefVar9, zzefVar10, zzefVar11, zzefVar12, zzefVar13, zzefVar14, zzefVar15, zzefVar16, zzefVar17, zzefVar18};
    }
}
