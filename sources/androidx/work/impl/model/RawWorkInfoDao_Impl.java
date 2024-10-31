package androidx.work.impl.model;

import android.database.Cursor;
import androidx.collection.ArrayMap;
import androidx.lifecycle.LiveData;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteQuery;
import androidx.work.Data;
import androidx.work.impl.model.WorkSpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;

/* loaded from: classes.dex */
public final class RawWorkInfoDao_Impl implements RawWorkInfoDao {
    private final RoomDatabase __db;

    public RawWorkInfoDao_Impl(RoomDatabase __db) {
        this.__db = __db;
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:25:0x00a2  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00c5  */
    /* JADX WARN: Removed duplicated region for block: B:33:0x00da A[Catch: all -> 0x0103, TryCatch #0 {all -> 0x0103, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c8, B:41:0x00bd, B:42:0x00a5, B:45:0x00b1, B:46:0x00ad, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:36:0x00ed A[Catch: all -> 0x0103, TryCatch #0 {all -> 0x0103, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c8, B:41:0x00bd, B:42:0x00a5, B:45:0x00b1, B:46:0x00ad, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:39:0x00f2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x00c8 A[Catch: all -> 0x0103, TryCatch #0 {all -> 0x0103, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c8, B:41:0x00bd, B:42:0x00a5, B:45:0x00b1, B:46:0x00ad, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x00bd A[Catch: all -> 0x0103, TryCatch #0 {all -> 0x0103, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c8, B:41:0x00bd, B:42:0x00a5, B:45:0x00b1, B:46:0x00ad, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00a5 A[Catch: all -> 0x0103, TryCatch #0 {all -> 0x0103, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c8, B:41:0x00bd, B:42:0x00a5, B:45:0x00b1, B:46:0x00ad, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x0095 A[Catch: all -> 0x0103, TryCatch #0 {all -> 0x0103, blocks: (B:3:0x0011, B:4:0x0039, B:6:0x003f, B:8:0x004b, B:9:0x0053, B:12:0x005f, B:17:0x0068, B:18:0x007b, B:31:0x00ce, B:33:0x00da, B:34:0x00df, B:36:0x00ed, B:38:0x00f2, B:40:0x00c8, B:41:0x00bd, B:42:0x00a5, B:45:0x00b1, B:46:0x00ad, B:47:0x0095, B:48:0x0085, B:51:0x008c), top: B:2:0x0011 }] */
    @Override // androidx.work.impl.model.RawWorkInfoDao
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<androidx.work.impl.model.WorkSpec.WorkInfoPojo> getWorkInfoPojos(final androidx.sqlite.db.SupportSQLiteQuery r22) {
        /*
            Method dump skipped, instructions count: 264
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.RawWorkInfoDao_Impl.getWorkInfoPojos(androidx.sqlite.db.SupportSQLiteQuery):java.util.List");
    }

    @Override // androidx.work.impl.model.RawWorkInfoDao
    public LiveData<List<WorkSpec.WorkInfoPojo>> getWorkInfoPojosLiveData(final SupportSQLiteQuery query) {
        return this.__db.getInvalidationTracker().createLiveData(new String[]{"WorkTag", "WorkProgress", "WorkSpec"}, false, new Callable<List<WorkSpec.WorkInfoPojo>>() { // from class: androidx.work.impl.model.RawWorkInfoDao_Impl.1
            /* JADX WARN: Multi-variable type inference failed */
            /* JADX WARN: Removed duplicated region for block: B:23:0x0096  */
            /* JADX WARN: Removed duplicated region for block: B:25:0x00a5  */
            /* JADX WARN: Removed duplicated region for block: B:28:0x00bd  */
            /* JADX WARN: Removed duplicated region for block: B:30:0x00c8  */
            /* JADX WARN: Removed duplicated region for block: B:33:0x00dd A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00d1, B:33:0x00dd, B:34:0x00e2, B:36:0x00f0, B:38:0x00f5, B:40:0x00cb, B:41:0x00c0, B:42:0x00a8, B:45:0x00b4, B:46:0x00b0, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:36:0x00f0 A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00d1, B:33:0x00dd, B:34:0x00e2, B:36:0x00f0, B:38:0x00f5, B:40:0x00cb, B:41:0x00c0, B:42:0x00a8, B:45:0x00b4, B:46:0x00b0, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:39:0x00f5 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:40:0x00cb A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00d1, B:33:0x00dd, B:34:0x00e2, B:36:0x00f0, B:38:0x00f5, B:40:0x00cb, B:41:0x00c0, B:42:0x00a8, B:45:0x00b4, B:46:0x00b0, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:41:0x00c0 A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00d1, B:33:0x00dd, B:34:0x00e2, B:36:0x00f0, B:38:0x00f5, B:40:0x00cb, B:41:0x00c0, B:42:0x00a8, B:45:0x00b4, B:46:0x00b0, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:42:0x00a8 A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00d1, B:33:0x00dd, B:34:0x00e2, B:36:0x00f0, B:38:0x00f5, B:40:0x00cb, B:41:0x00c0, B:42:0x00a8, B:45:0x00b4, B:46:0x00b0, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            /* JADX WARN: Removed duplicated region for block: B:47:0x0098 A[Catch: all -> 0x0106, TryCatch #0 {all -> 0x0106, blocks: (B:3:0x0010, B:4:0x0038, B:6:0x003e, B:8:0x004a, B:9:0x0052, B:12:0x005e, B:17:0x0067, B:18:0x007e, B:31:0x00d1, B:33:0x00dd, B:34:0x00e2, B:36:0x00f0, B:38:0x00f5, B:40:0x00cb, B:41:0x00c0, B:42:0x00a8, B:45:0x00b4, B:46:0x00b0, B:47:0x0098, B:48:0x0088, B:51:0x008f), top: B:2:0x0010 }] */
            @Override // java.util.concurrent.Callable
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public java.util.List<androidx.work.impl.model.WorkSpec.WorkInfoPojo> call() throws java.lang.Exception {
                /*
                    Method dump skipped, instructions count: 267
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: androidx.work.impl.model.RawWorkInfoDao_Impl.AnonymousClass1.call():java.util.List");
            }
        });
    }

    public static List<Class<?>> getRequiredConverters() {
        return Collections.emptyList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkTagAsjavaLangString(final ArrayMap<String, ArrayList<String>> _map) {
        int i;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<String>> arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i2 = 0;
            loop0: while (true) {
                i = 0;
                while (i2 < size) {
                    arrayMap.put(_map.keyAt(i2), _map.valueAt(i2));
                    i2++;
                    i++;
                    if (i == 999) {
                        break;
                    }
                }
                __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (i > 0) {
                __fetchRelationshipWorkTagAsjavaLangString(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `tag`,`work_spec_id` FROM `WorkTag` WHERE `work_spec_id` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (query.moveToNext()) {
                ArrayList<String> arrayList = _map.get(query.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(query.isNull(0) ? null : query.getString(0));
                }
            }
        } finally {
            query.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void __fetchRelationshipWorkProgressAsandroidxWorkData(final ArrayMap<String, ArrayList<Data>> _map) {
        int i;
        Set<String> keySet = _map.keySet();
        if (keySet.isEmpty()) {
            return;
        }
        if (_map.size() > 999) {
            ArrayMap<String, ArrayList<Data>> arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            int size = _map.size();
            int i2 = 0;
            loop0: while (true) {
                i = 0;
                while (i2 < size) {
                    arrayMap.put(_map.keyAt(i2), _map.valueAt(i2));
                    i2++;
                    i++;
                    if (i == 999) {
                        break;
                    }
                }
                __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap);
                arrayMap = new ArrayMap<>(RoomDatabase.MAX_BIND_PARAMETER_CNT);
            }
            if (i > 0) {
                __fetchRelationshipWorkProgressAsandroidxWorkData(arrayMap);
                return;
            }
            return;
        }
        StringBuilder newStringBuilder = StringUtil.newStringBuilder();
        newStringBuilder.append("SELECT `progress`,`work_spec_id` FROM `WorkProgress` WHERE `work_spec_id` IN (");
        int size2 = keySet.size();
        StringUtil.appendPlaceholders(newStringBuilder, size2);
        newStringBuilder.append(")");
        RoomSQLiteQuery acquire = RoomSQLiteQuery.acquire(newStringBuilder.toString(), size2 + 0);
        int i3 = 1;
        for (String str : keySet) {
            if (str == null) {
                acquire.bindNull(i3);
            } else {
                acquire.bindString(i3, str);
            }
            i3++;
        }
        Cursor query = DBUtil.query(this.__db, acquire, false, null);
        try {
            int columnIndex = CursorUtil.getColumnIndex(query, "work_spec_id");
            if (columnIndex == -1) {
                return;
            }
            while (query.moveToNext()) {
                ArrayList<Data> arrayList = _map.get(query.getString(columnIndex));
                if (arrayList != null) {
                    arrayList.add(Data.fromByteArray(query.isNull(0) ? null : query.getBlob(0)));
                }
            }
        } finally {
            query.close();
        }
    }
}
