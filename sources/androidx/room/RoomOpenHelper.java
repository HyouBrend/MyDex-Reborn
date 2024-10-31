package androidx.room;

import android.database.Cursor;
import android.os.Build;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SimpleSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.util.Iterator;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RoomOpenHelper.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\b\b\u0017\u0018\u0000 \u00192\u00020\u0001:\u0003\u0019\u001a\u001bB\u001f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bB%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u000f\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0010\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u0010\u0010\u0011\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u0012\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J \u0010\u0017\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014H\u0016J\u0010\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Landroidx/room/RoomOpenHelper;", "Landroidx/sqlite/db/SupportSQLiteOpenHelper$Callback;", "configuration", "Landroidx/room/DatabaseConfiguration;", "delegate", "Landroidx/room/RoomOpenHelper$Delegate;", "legacyHash", "", "(Landroidx/room/DatabaseConfiguration;Landroidx/room/RoomOpenHelper$Delegate;Ljava/lang/String;)V", "identityHash", "(Landroidx/room/DatabaseConfiguration;Landroidx/room/RoomOpenHelper$Delegate;Ljava/lang/String;Ljava/lang/String;)V", "checkIdentity", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "createMasterTableIfNotExists", "onConfigure", "onCreate", "onDowngrade", "oldVersion", "", "newVersion", "onOpen", "onUpgrade", "updateIdentity", "Companion", "Delegate", "ValidationResult", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
/* loaded from: classes.dex */
public class RoomOpenHelper extends SupportSQLiteOpenHelper.Callback {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private DatabaseConfiguration configuration;
    private final Delegate delegate;
    private final String identityHash;
    private final String legacyHash;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public RoomOpenHelper(DatabaseConfiguration configuration, Delegate delegate, String identityHash, String legacyHash) {
        super(delegate.version);
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(identityHash, "identityHash");
        Intrinsics.checkNotNullParameter(legacyHash, "legacyHash");
        this.configuration = configuration;
        this.delegate = delegate;
        this.identityHash = identityHash;
        this.legacyHash = legacyHash;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public RoomOpenHelper(DatabaseConfiguration configuration, Delegate delegate, String legacyHash) {
        this(configuration, delegate, "", legacyHash);
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(delegate, "delegate");
        Intrinsics.checkNotNullParameter(legacyHash, "legacyHash");
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onConfigure(SupportSQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        super.onConfigure(db);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onCreate(SupportSQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        boolean hasEmptySchema$room_runtime_release = INSTANCE.hasEmptySchema$room_runtime_release(db);
        this.delegate.createAllTables(db);
        if (!hasEmptySchema$room_runtime_release) {
            ValidationResult onValidateSchema = this.delegate.onValidateSchema(db);
            if (!onValidateSchema.isValid) {
                throw new IllegalStateException("Pre-packaged database has an invalid schema: " + onValidateSchema.expectedFoundMsg);
            }
        }
        updateIdentity(db);
        this.delegate.onCreate(db);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onUpgrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
        List<Migration> findMigrationPath;
        Intrinsics.checkNotNullParameter(db, "db");
        DatabaseConfiguration databaseConfiguration = this.configuration;
        boolean z = false;
        if (databaseConfiguration != null && (findMigrationPath = databaseConfiguration.migrationContainer.findMigrationPath(oldVersion, newVersion)) != null) {
            this.delegate.onPreMigrate(db);
            Iterator<T> it = findMigrationPath.iterator();
            while (it.hasNext()) {
                ((Migration) it.next()).migrate(db);
            }
            ValidationResult onValidateSchema = this.delegate.onValidateSchema(db);
            if (!onValidateSchema.isValid) {
                throw new IllegalStateException("Migration didn't properly handle: " + onValidateSchema.expectedFoundMsg);
            }
            this.delegate.onPostMigrate(db);
            updateIdentity(db);
            z = true;
        }
        if (z) {
            return;
        }
        DatabaseConfiguration databaseConfiguration2 = this.configuration;
        if (databaseConfiguration2 != null && !databaseConfiguration2.isMigrationRequired(oldVersion, newVersion)) {
            this.delegate.dropAllTables(db);
            this.delegate.createAllTables(db);
            return;
        }
        throw new IllegalStateException("A migration from " + oldVersion + " to " + newVersion + " was required but not found. Please provide the necessary Migration path via RoomDatabase.Builder.addMigration(Migration ...) or allow for destructive migrations via one of the RoomDatabase.Builder.fallbackToDestructiveMigration* methods.");
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onDowngrade(SupportSQLiteDatabase db, int oldVersion, int newVersion) {
        Intrinsics.checkNotNullParameter(db, "db");
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override // androidx.sqlite.db.SupportSQLiteOpenHelper.Callback
    public void onOpen(SupportSQLiteDatabase db) {
        Intrinsics.checkNotNullParameter(db, "db");
        super.onOpen(db);
        checkIdentity(db);
        this.delegate.onOpen(db);
        this.configuration = null;
    }

    private final void checkIdentity(SupportSQLiteDatabase db) {
        String str;
        if (INSTANCE.hasRoomMasterTable$room_runtime_release(db)) {
            Cursor query = db.query(new SimpleSQLiteQuery(RoomMasterTable.READ_QUERY));
            String str2 = null;
            if (Build.VERSION.SDK_INT <= 15) {
                try {
                    if (query.moveToFirst()) {
                        str2 = query.getString(0);
                    }
                    query.close();
                    str = str2;
                } catch (Throwable th) {
                    query.close();
                    throw th;
                }
            } else {
                Cursor cursor = query;
                try {
                    Cursor cursor2 = cursor;
                    if (cursor2.moveToFirst()) {
                        str = cursor2.getString(0);
                    } else {
                        str = null;
                    }
                    CloseableKt.closeFinally(cursor, null);
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(cursor, th2);
                        throw th3;
                    }
                }
            }
            if (Intrinsics.areEqual(this.identityHash, str) || Intrinsics.areEqual(this.legacyHash, str)) {
                return;
            }
            throw new IllegalStateException("Room cannot verify the data integrity. Looks like you've changed schema but forgot to update the version number. You can simply fix this by increasing the version number. Expected identity hash: " + this.identityHash + ", found: " + str);
        }
        ValidationResult onValidateSchema = this.delegate.onValidateSchema(db);
        if (!onValidateSchema.isValid) {
            throw new IllegalStateException("Pre-packaged database has an invalid schema: " + onValidateSchema.expectedFoundMsg);
        }
        this.delegate.onPostMigrate(db);
        updateIdentity(db);
    }

    private final void updateIdentity(SupportSQLiteDatabase db) {
        createMasterTableIfNotExists(db);
        db.execSQL(RoomMasterTable.createInsertQuery(this.identityHash));
    }

    private final void createMasterTableIfNotExists(SupportSQLiteDatabase db) {
        db.execSQL(RoomMasterTable.CREATE_QUERY);
    }

    /* compiled from: RoomOpenHelper.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH&J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\r\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\bH\u0016J\u0010\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0015R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/room/RoomOpenHelper$Delegate;", "", "version", "", "(I)V", "createAllTables", "", "database", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "dropAllTables", "onCreate", "onOpen", "onPostMigrate", "onPreMigrate", "onValidateSchema", "Landroidx/room/RoomOpenHelper$ValidationResult;", "db", "validateMigration", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes.dex */
    public static abstract class Delegate {
        public final int version;

        public abstract void createAllTables(SupportSQLiteDatabase database);

        public abstract void dropAllTables(SupportSQLiteDatabase database);

        public abstract void onCreate(SupportSQLiteDatabase database);

        public abstract void onOpen(SupportSQLiteDatabase database);

        public void onPostMigrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
        }

        public void onPreMigrate(SupportSQLiteDatabase database) {
            Intrinsics.checkNotNullParameter(database, "database");
        }

        public Delegate(int i) {
            this.version = i;
        }

        @Deprecated(message = "Use [onValidateSchema(SupportSQLiteDatabase)]")
        protected void validateMigration(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            throw new UnsupportedOperationException("validateMigration is deprecated");
        }

        public ValidationResult onValidateSchema(SupportSQLiteDatabase db) {
            Intrinsics.checkNotNullParameter(db, "db");
            validateMigration(db);
            return new ValidationResult(true, null);
        }
    }

    /* compiled from: RoomOpenHelper.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Landroidx/room/RoomOpenHelper$ValidationResult;", "", "isValid", "", "expectedFoundMsg", "", "(ZLjava/lang/String;)V", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes.dex */
    public static class ValidationResult {
        public final String expectedFoundMsg;
        public final boolean isValid;

        public ValidationResult(boolean z, String str) {
            this.isValid = z;
            this.expectedFoundMsg = str;
        }
    }

    /* compiled from: RoomOpenHelper.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0015\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\u0007J\u0015\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¢\u0006\u0002\b\t¨\u0006\n"}, d2 = {"Landroidx/room/RoomOpenHelper$Companion;", "", "()V", "hasEmptySchema", "", "db", "Landroidx/sqlite/db/SupportSQLiteDatabase;", "hasEmptySchema$room_runtime_release", "hasRoomMasterTable", "hasRoomMasterTable$room_runtime_release", "room-runtime_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
    /* loaded from: classes.dex */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x003c, code lost:
        
            if (r6.getInt(0) != 0) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
        
            if (r3.getInt(0) != 0) goto L11;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean hasRoomMasterTable$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase r6) {
            /*
                r5 = this;
                java.lang.String r0 = "db"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "SELECT 1 FROM sqlite_master WHERE type = 'table' AND name='room_master_table'"
                android.database.Cursor r6 = r6.query(r0)
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 1
                r2 = 0
                r3 = 15
                if (r0 <= r3) goto L32
                java.io.Closeable r6 = (java.io.Closeable) r6
                r0 = 0
                r3 = r6
                android.database.Cursor r3 = (android.database.Cursor) r3     // Catch: java.lang.Throwable -> L2b
                boolean r4 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L2b
                if (r4 == 0) goto L26
                int r3 = r3.getInt(r2)     // Catch: java.lang.Throwable -> L2b
                if (r3 == 0) goto L26
                goto L27
            L26:
                r1 = 0
            L27:
                kotlin.io.CloseableKt.closeFinally(r6, r0)
                return r1
            L2b:
                r0 = move-exception
                throw r0     // Catch: java.lang.Throwable -> L2d
            L2d:
                r1 = move-exception
                kotlin.io.CloseableKt.closeFinally(r6, r0)
                throw r1
            L32:
                boolean r0 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L44
                if (r0 == 0) goto L3f
                int r0 = r6.getInt(r2)     // Catch: java.lang.Throwable -> L44
                if (r0 == 0) goto L3f
                goto L40
            L3f:
                r1 = 0
            L40:
                r6.close()
                return r1
            L44:
                r0 = move-exception
                r6.close()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.Companion.hasRoomMasterTable$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase):boolean");
        }

        /* JADX WARN: Code restructure failed: missing block: B:25:0x003c, code lost:
        
            if (r6.getInt(0) == 0) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:8:0x0023, code lost:
        
            if (r3.getInt(0) == 0) goto L11;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final boolean hasEmptySchema$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase r6) {
            /*
                r5 = this;
                java.lang.String r0 = "db"
                kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r6, r0)
                java.lang.String r0 = "SELECT count(*) FROM sqlite_master WHERE name != 'android_metadata'"
                android.database.Cursor r6 = r6.query(r0)
                int r0 = android.os.Build.VERSION.SDK_INT
                r1 = 1
                r2 = 0
                r3 = 15
                if (r0 <= r3) goto L32
                java.io.Closeable r6 = (java.io.Closeable) r6
                r0 = 0
                r3 = r6
                android.database.Cursor r3 = (android.database.Cursor) r3     // Catch: java.lang.Throwable -> L2b
                boolean r4 = r3.moveToFirst()     // Catch: java.lang.Throwable -> L2b
                if (r4 == 0) goto L26
                int r3 = r3.getInt(r2)     // Catch: java.lang.Throwable -> L2b
                if (r3 != 0) goto L26
                goto L27
            L26:
                r1 = 0
            L27:
                kotlin.io.CloseableKt.closeFinally(r6, r0)
                return r1
            L2b:
                r0 = move-exception
                throw r0     // Catch: java.lang.Throwable -> L2d
            L2d:
                r1 = move-exception
                kotlin.io.CloseableKt.closeFinally(r6, r0)
                throw r1
            L32:
                boolean r0 = r6.moveToFirst()     // Catch: java.lang.Throwable -> L44
                if (r0 == 0) goto L3f
                int r0 = r6.getInt(r2)     // Catch: java.lang.Throwable -> L44
                if (r0 != 0) goto L3f
                goto L40
            L3f:
                r1 = 0
            L40:
                r6.close()
                return r1
            L44:
                r0 = move-exception
                r6.close()
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.room.RoomOpenHelper.Companion.hasEmptySchema$room_runtime_release(androidx.sqlite.db.SupportSQLiteDatabase):boolean");
        }
    }
}