package com.myor.mydex.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* loaded from: classes2.dex */
public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "mydex.db";
    private static final int DATABASE_VERSION = 1;
    private Context mContext;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public DatabaseHandler(Context context) {
        super(context, "mydex.db", (SQLiteDatabase.CursorFactory) null, 1);
        this.mContext = context;
    }
}
