package androidx.room.util;

import android.database.Cursor;
import android.os.Build;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: TableInfo.kt */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\"\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002\u001a \u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\r2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0000¨\u0006\u0017"}, d2 = {"readColumns", "", "", "Landroidx/room/util/TableInfo$Column;", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "tableName", "readForeignKeyFieldMappings", "", "Landroidx/room/util/TableInfo$ForeignKeyWithSequence;", "cursor", "Landroid/database/Cursor;", "readForeignKeys", "", "Landroidx/room/util/TableInfo$ForeignKey;", "readIndex", "Landroidx/room/util/TableInfo$Index;", "name", "unique", "", "readIndices", "readTableInfo", "Landroidx/room/util/TableInfo;", "room-runtime_release"}, k = 2, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public final class TableInfoKt {
    public static final TableInfo readTableInfo(SupportSQLiteDatabase database, String tableName) {
        Intrinsics.checkNotNullParameter(database, "database");
        Intrinsics.checkNotNullParameter(tableName, "tableName");
        return new TableInfo(tableName, readColumns(database, tableName), readForeignKeys(database, tableName), readIndices(database, tableName));
    }

    private static final Set<TableInfo.ForeignKey> readForeignKeys(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor query = supportSQLiteDatabase.query("PRAGMA foreign_key_list(`" + str + "`)");
        if (Build.VERSION.SDK_INT <= 15) {
            try {
                int columnIndex = query.getColumnIndex("id");
                int columnIndex2 = query.getColumnIndex("seq");
                int columnIndex3 = query.getColumnIndex("table");
                int columnIndex4 = query.getColumnIndex("on_delete");
                int columnIndex5 = query.getColumnIndex("on_update");
                List<TableInfo.ForeignKeyWithSequence> readForeignKeyFieldMappings = readForeignKeyFieldMappings(query);
                query.moveToPosition(-1);
                Set createSetBuilder = SetsKt.createSetBuilder();
                while (query.moveToNext()) {
                    if (query.getInt(columnIndex2) == 0) {
                        int i = query.getInt(columnIndex);
                        ArrayList arrayList = new ArrayList();
                        ArrayList arrayList2 = new ArrayList();
                        ArrayList<TableInfo.ForeignKeyWithSequence> arrayList3 = new ArrayList();
                        for (Object obj : readForeignKeyFieldMappings) {
                            int i2 = columnIndex;
                            int i3 = columnIndex2;
                            if (((TableInfo.ForeignKeyWithSequence) obj).getId() == i) {
                                arrayList3.add(obj);
                            }
                            columnIndex = i2;
                            columnIndex2 = i3;
                        }
                        int i4 = columnIndex;
                        int i5 = columnIndex2;
                        for (TableInfo.ForeignKeyWithSequence foreignKeyWithSequence : arrayList3) {
                            arrayList.add(foreignKeyWithSequence.getFrom());
                            arrayList2.add(foreignKeyWithSequence.getTo());
                        }
                        String string = query.getString(columnIndex3);
                        Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(tableColumnIndex)");
                        String string2 = query.getString(columnIndex4);
                        Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(onDeleteColumnIndex)");
                        String string3 = query.getString(columnIndex5);
                        Intrinsics.checkNotNullExpressionValue(string3, "cursor.getString(onUpdateColumnIndex)");
                        createSetBuilder.add(new TableInfo.ForeignKey(string, string2, string3, arrayList, arrayList2));
                        columnIndex = i4;
                        columnIndex2 = i5;
                    }
                }
                return SetsKt.build(createSetBuilder);
            } finally {
                query.close();
            }
        }
        Cursor cursor = query;
        try {
            Cursor cursor2 = cursor;
            int columnIndex6 = cursor2.getColumnIndex("id");
            int columnIndex7 = cursor2.getColumnIndex("seq");
            int columnIndex8 = cursor2.getColumnIndex("table");
            int columnIndex9 = cursor2.getColumnIndex("on_delete");
            int columnIndex10 = cursor2.getColumnIndex("on_update");
            List<TableInfo.ForeignKeyWithSequence> readForeignKeyFieldMappings2 = readForeignKeyFieldMappings(cursor2);
            cursor2.moveToPosition(-1);
            Set createSetBuilder2 = SetsKt.createSetBuilder();
            while (cursor2.moveToNext()) {
                if (cursor2.getInt(columnIndex7) == 0) {
                    int i6 = cursor2.getInt(columnIndex6);
                    ArrayList arrayList4 = new ArrayList();
                    ArrayList arrayList5 = new ArrayList();
                    ArrayList<TableInfo.ForeignKeyWithSequence> arrayList6 = new ArrayList();
                    for (Object obj2 : readForeignKeyFieldMappings2) {
                        int i7 = columnIndex7;
                        int i8 = columnIndex6;
                        if (((TableInfo.ForeignKeyWithSequence) obj2).getId() == i6) {
                            arrayList6.add(obj2);
                        }
                        columnIndex7 = i7;
                        columnIndex6 = i8;
                    }
                    int i9 = columnIndex7;
                    int i10 = columnIndex6;
                    for (TableInfo.ForeignKeyWithSequence foreignKeyWithSequence2 : arrayList6) {
                        arrayList4.add(foreignKeyWithSequence2.getFrom());
                        arrayList5.add(foreignKeyWithSequence2.getTo());
                    }
                    String string4 = cursor2.getString(columnIndex8);
                    Intrinsics.checkNotNullExpressionValue(string4, "cursor.getString(tableColumnIndex)");
                    String string5 = cursor2.getString(columnIndex9);
                    Intrinsics.checkNotNullExpressionValue(string5, "cursor.getString(onDeleteColumnIndex)");
                    String string6 = cursor2.getString(columnIndex10);
                    Intrinsics.checkNotNullExpressionValue(string6, "cursor.getString(onUpdateColumnIndex)");
                    createSetBuilder2.add(new TableInfo.ForeignKey(string4, string5, string6, arrayList4, arrayList5));
                    columnIndex7 = i9;
                    columnIndex6 = i10;
                }
            }
            Set<TableInfo.ForeignKey> build = SetsKt.build(createSetBuilder2);
            CloseableKt.closeFinally(cursor, null);
            return build;
        } finally {
        }
    }

    private static final List<TableInfo.ForeignKeyWithSequence> readForeignKeyFieldMappings(Cursor cursor) {
        int columnIndex = cursor.getColumnIndex("id");
        int columnIndex2 = cursor.getColumnIndex("seq");
        int columnIndex3 = cursor.getColumnIndex(TypedValues.TransitionType.S_FROM);
        int columnIndex4 = cursor.getColumnIndex(TypedValues.TransitionType.S_TO);
        List createListBuilder = CollectionsKt.createListBuilder();
        while (cursor.moveToNext()) {
            int i = cursor.getInt(columnIndex);
            int i2 = cursor.getInt(columnIndex2);
            String string = cursor.getString(columnIndex3);
            Intrinsics.checkNotNullExpressionValue(string, "cursor.getString(fromColumnIndex)");
            String string2 = cursor.getString(columnIndex4);
            Intrinsics.checkNotNullExpressionValue(string2, "cursor.getString(toColumnIndex)");
            createListBuilder.add(new TableInfo.ForeignKeyWithSequence(i, i2, string, string2));
        }
        return CollectionsKt.sorted(CollectionsKt.build(createListBuilder));
    }

    private static final Map<String, TableInfo.Column> readColumns(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor query = supportSQLiteDatabase.query("PRAGMA table_info(`" + str + "`)");
        if (Build.VERSION.SDK_INT <= 15) {
            try {
                if (query.getColumnCount() <= 0) {
                    return MapsKt.emptyMap();
                }
                int columnIndex = query.getColumnIndex("name");
                int columnIndex2 = query.getColumnIndex("type");
                int columnIndex3 = query.getColumnIndex("notnull");
                int columnIndex4 = query.getColumnIndex("pk");
                int columnIndex5 = query.getColumnIndex("dflt_value");
                Map createMapBuilder = MapsKt.createMapBuilder();
                while (query.moveToNext()) {
                    String name = query.getString(columnIndex);
                    String type = query.getString(columnIndex2);
                    boolean z = query.getInt(columnIndex3) != 0;
                    int i = query.getInt(columnIndex4);
                    String string = query.getString(columnIndex5);
                    Intrinsics.checkNotNullExpressionValue(name, "name");
                    Intrinsics.checkNotNullExpressionValue(type, "type");
                    createMapBuilder.put(name, new TableInfo.Column(name, type, z, i, string, 2));
                }
                return MapsKt.build(createMapBuilder);
            } finally {
                query.close();
            }
        }
        Cursor cursor = query;
        try {
            Cursor cursor2 = cursor;
            if (cursor2.getColumnCount() <= 0) {
                Map<String, TableInfo.Column> emptyMap = MapsKt.emptyMap();
                CloseableKt.closeFinally(cursor, null);
                return emptyMap;
            }
            int columnIndex6 = cursor2.getColumnIndex("name");
            int columnIndex7 = cursor2.getColumnIndex("type");
            int columnIndex8 = cursor2.getColumnIndex("notnull");
            int columnIndex9 = cursor2.getColumnIndex("pk");
            int columnIndex10 = cursor2.getColumnIndex("dflt_value");
            Map createMapBuilder2 = MapsKt.createMapBuilder();
            while (cursor2.moveToNext()) {
                String name2 = cursor2.getString(columnIndex6);
                String type2 = cursor2.getString(columnIndex7);
                boolean z2 = cursor2.getInt(columnIndex8) != 0;
                int i2 = cursor2.getInt(columnIndex9);
                String string2 = cursor2.getString(columnIndex10);
                Intrinsics.checkNotNullExpressionValue(name2, "name");
                Intrinsics.checkNotNullExpressionValue(type2, "type");
                createMapBuilder2.put(name2, new TableInfo.Column(name2, type2, z2, i2, string2, 2));
            }
            Map<String, TableInfo.Column> build = MapsKt.build(createMapBuilder2);
            CloseableKt.closeFinally(cursor, null);
            return build;
        } finally {
        }
    }

    private static final Set<TableInfo.Index> readIndices(SupportSQLiteDatabase supportSQLiteDatabase, String str) {
        Cursor query = supportSQLiteDatabase.query("PRAGMA index_list(`" + str + "`)");
        if (Build.VERSION.SDK_INT <= 15) {
            try {
                int columnIndex = query.getColumnIndex("name");
                int columnIndex2 = query.getColumnIndex("origin");
                int columnIndex3 = query.getColumnIndex("unique");
                if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1) {
                    Set createSetBuilder = SetsKt.createSetBuilder();
                    while (query.moveToNext()) {
                        if (Intrinsics.areEqual("c", query.getString(columnIndex2))) {
                            String name = query.getString(columnIndex);
                            boolean z = query.getInt(columnIndex3) == 1;
                            Intrinsics.checkNotNullExpressionValue(name, "name");
                            TableInfo.Index readIndex = readIndex(supportSQLiteDatabase, name, z);
                            if (readIndex == null) {
                                return null;
                            }
                            createSetBuilder.add(readIndex);
                        }
                    }
                    return SetsKt.build(createSetBuilder);
                }
                return null;
            } finally {
                query.close();
            }
        }
        Cursor cursor = query;
        try {
            Cursor cursor2 = cursor;
            int columnIndex4 = cursor2.getColumnIndex("name");
            int columnIndex5 = cursor2.getColumnIndex("origin");
            int columnIndex6 = cursor2.getColumnIndex("unique");
            if (columnIndex4 != -1 && columnIndex5 != -1 && columnIndex6 != -1) {
                Set createSetBuilder2 = SetsKt.createSetBuilder();
                while (cursor2.moveToNext()) {
                    if (Intrinsics.areEqual("c", cursor2.getString(columnIndex5))) {
                        String name2 = cursor2.getString(columnIndex4);
                        boolean z2 = cursor2.getInt(columnIndex6) == 1;
                        Intrinsics.checkNotNullExpressionValue(name2, "name");
                        TableInfo.Index readIndex2 = readIndex(supportSQLiteDatabase, name2, z2);
                        if (readIndex2 == null) {
                            CloseableKt.closeFinally(cursor, null);
                            return null;
                        }
                        createSetBuilder2.add(readIndex2);
                    }
                }
                Set<TableInfo.Index> build = SetsKt.build(createSetBuilder2);
                CloseableKt.closeFinally(cursor, null);
                return build;
            }
            CloseableKt.closeFinally(cursor, null);
            return null;
        } finally {
        }
    }

    private static final TableInfo.Index readIndex(SupportSQLiteDatabase supportSQLiteDatabase, String str, boolean z) {
        TableInfo.Index index;
        Cursor cursor;
        String str2;
        String str3;
        Cursor query = supportSQLiteDatabase.query("PRAGMA index_xinfo(`" + str + "`)");
        String str4 = "DESC";
        String str5 = "ASC";
        if (Build.VERSION.SDK_INT <= 15) {
            try {
                int columnIndex = query.getColumnIndex("seqno");
                int columnIndex2 = query.getColumnIndex("cid");
                int columnIndex3 = query.getColumnIndex("name");
                int columnIndex4 = query.getColumnIndex("desc");
                if (columnIndex != -1 && columnIndex2 != -1 && columnIndex3 != -1 && columnIndex4 != -1) {
                    TreeMap treeMap = new TreeMap();
                    TreeMap treeMap2 = new TreeMap();
                    while (query.moveToNext()) {
                        if (query.getInt(columnIndex2) >= 0) {
                            int i = query.getInt(columnIndex);
                            String columnName = query.getString(columnIndex3);
                            String str6 = query.getInt(columnIndex4) > 0 ? "DESC" : "ASC";
                            Integer valueOf = Integer.valueOf(i);
                            Intrinsics.checkNotNullExpressionValue(columnName, "columnName");
                            treeMap.put(valueOf, columnName);
                            treeMap2.put(Integer.valueOf(i), str6);
                            columnIndex = columnIndex;
                        }
                    }
                    Collection values = treeMap.values();
                    Intrinsics.checkNotNullExpressionValue(values, "columnsMap.values");
                    List list = CollectionsKt.toList(values);
                    Collection values2 = treeMap2.values();
                    Intrinsics.checkNotNullExpressionValue(values2, "ordersMap.values");
                    index = new TableInfo.Index(str, z, list, CollectionsKt.toList(values2));
                }
                query.close();
                return null;
            } finally {
                query.close();
            }
        }
        Cursor cursor2 = query;
        try {
            Cursor cursor3 = cursor2;
            int columnIndex5 = cursor3.getColumnIndex("seqno");
            int columnIndex6 = cursor3.getColumnIndex("cid");
            int columnIndex7 = cursor3.getColumnIndex("name");
            int columnIndex8 = cursor3.getColumnIndex("desc");
            if (columnIndex5 != -1 && columnIndex6 != -1 && columnIndex7 != -1 && columnIndex8 != -1) {
                TreeMap treeMap3 = new TreeMap();
                TreeMap treeMap4 = new TreeMap();
                while (cursor3.moveToNext()) {
                    if (cursor3.getInt(columnIndex6) >= 0) {
                        int i2 = cursor3.getInt(columnIndex5);
                        String columnName2 = cursor3.getString(columnIndex7);
                        if (cursor3.getInt(columnIndex8) > 0) {
                            cursor = cursor3;
                            str3 = str4;
                            str2 = str3;
                        } else {
                            cursor = cursor3;
                            str2 = str4;
                            str3 = str5;
                        }
                        Integer valueOf2 = Integer.valueOf(i2);
                        Intrinsics.checkNotNullExpressionValue(columnName2, "columnName");
                        treeMap3.put(valueOf2, columnName2);
                        treeMap4.put(Integer.valueOf(i2), str3);
                        cursor3 = cursor;
                        str4 = str2;
                        str5 = str5;
                    }
                }
                Collection values3 = treeMap3.values();
                Intrinsics.checkNotNullExpressionValue(values3, "columnsMap.values");
                List list2 = CollectionsKt.toList(values3);
                Collection values4 = treeMap4.values();
                Intrinsics.checkNotNullExpressionValue(values4, "ordersMap.values");
                index = new TableInfo.Index(str, z, list2, CollectionsKt.toList(values4));
                CloseableKt.closeFinally(cursor2, null);
            }
            CloseableKt.closeFinally(cursor2, null);
            return null;
        } finally {
        }
        return index;
    }
}
