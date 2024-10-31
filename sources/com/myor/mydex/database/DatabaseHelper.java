package com.myor.mydex.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes2.dex */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {
    public static final String DBLOCATION = "/data/data/com.myor.mydex/databases/";
    public static final String DBNAME = "mydex.db";
    public static String DB_SDCARD_PATH = "";
    public static final String DEFAULT_PASSWORD_SETTING = "mayoramydex123";
    public static String PHOTO_PATH = "";
    public static String PHOTO_PATH_SDCARD = "";
    public static String TEMP_PATH = "";
    public static String TXTOUTPUT_PATH = "";
    public static String UPDATE_GPRS_PATH = "";
    public static String UPDATE_PATH = "";
    private static DatabaseHelper sInstance;
    File directory;
    File directoryPhoto;
    File directoryTemp;
    InputStream inputStream;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    OutputStream outputStream;

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource) {
    }

    @Override // com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, ConnectionSource connectionSource, int i, int i2) {
    }

    public DatabaseHelper(Context context) {
        super(context, DBNAME, null, 1);
        this.directory = new File(DB_SDCARD_PATH);
        this.directoryPhoto = new File(PHOTO_PATH_SDCARD);
        this.directoryTemp = new File(TEMP_PATH);
        this.mContext = context;
        if (Build.VERSION.SDK_INT >= 30) {
            DB_SDCARD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/databases/";
            PHOTO_PATH_SDCARD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/photo/";
            TXTOUTPUT_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/textoutput/";
            PHOTO_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/photo/";
            UPDATE_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/update/";
            UPDATE_GPRS_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/update/gprs/";
            TEMP_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/temp/";
            return;
        }
        DB_SDCARD_PATH = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/databases/";
        PHOTO_PATH_SDCARD = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/photo/";
        TXTOUTPUT_PATH = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/textoutput/";
        PHOTO_PATH = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/photo/";
        UPDATE_PATH = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/update/";
        UPDATE_GPRS_PATH = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/update/gprs/";
        TEMP_PATH = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/temp/";
    }

    public static DatabaseHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DatabaseHelper(context);
        }
        if (Build.VERSION.SDK_INT >= 30) {
            DB_SDCARD_PATH = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/databases/";
            PHOTO_PATH_SDCARD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/photo/";
        } else {
            DB_SDCARD_PATH = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/databases/";
            PHOTO_PATH_SDCARD = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/photo/";
        }
        return sInstance;
    }

    public void createFolder() {
        File file = new File(DB_SDCARD_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(DBLOCATION);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        File file3 = new File(UPDATE_GPRS_PATH);
        if (!file3.exists()) {
            file3.mkdirs();
        }
        File file4 = new File(TXTOUTPUT_PATH);
        if (!file4.exists()) {
            file4.mkdirs();
        }
        File file5 = new File(PHOTO_PATH);
        if (!file5.exists()) {
            file5.mkdirs();
        }
        File file6 = new File(UPDATE_PATH);
        if (file6.exists()) {
            return;
        }
        file6.mkdirs();
    }

    public void createFolderPhoto() {
        if (this.directoryPhoto.exists()) {
            return;
        }
        this.directoryPhoto.mkdirs();
    }

    public void createFolderTemp() {
        if (this.directoryTemp.exists()) {
            return;
        }
        this.directoryTemp.mkdirs();
    }

    public boolean createDatabase() {
        createFolder();
        createFolderPhoto();
        createFolderTemp();
        if (DatabaseIsExistSDCard()) {
            copyDatabaseSDCard();
            return true;
        }
        if (DatabaseIsExist()) {
            return true;
        }
        copyDatabaseFromAsset();
        return true;
    }

    private boolean DatabaseIsExistSDCard() {
        try {
            String str = DB_SDCARD_PATH + DBNAME;
            File file = new File(DB_SDCARD_PATH);
            if (!file.exists()) {
                file.mkdirs();
                return true;
            }
            SQLiteDatabase.openDatabase(str, null, 16);
            return true;
        } catch (SQLiteException unused) {
            return false;
        }
    }

    public void copyDatabaseSDCard() {
        File file = new File(DB_SDCARD_PATH + DBNAME);
        try {
            this.inputStream = new FileInputStream(file);
            File file2 = new File("/data/data/com.myor.mydex/databases/mydex.db");
            File file3 = new File(DBLOCATION);
            if (!file3.exists()) {
                file3.mkdirs();
            }
            if (file2.exists()) {
                file2.delete();
                System.out.println("Old file is deleted");
            }
            this.outputStream = new FileOutputStream(file2);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = this.inputStream.read(bArr);
                if (read <= 0) {
                    break;
                } else {
                    this.outputStream.write(bArr, 0, read);
                }
            }
            this.inputStream.close();
            this.outputStream.close();
            System.out.println("File SD Card is copied successful!");
            if (file.exists()) {
                file.delete();
            }
            SQLiteDatabase.openDatabase("/data/data/com.myor.mydex/databases/mydex.db", null, 16);
        } catch (Exception unused) {
            System.out.println("Failed Copy db to data");
        }
    }

    private boolean DatabaseIsExist() {
        try {
            File file = new File(DB_SDCARD_PATH);
            if (!file.exists()) {
                file.mkdirs();
            }
            SQLiteDatabase.openDatabase("/data/data/com.myor.mydex/databases/mydex.db", null, 16, new DatabaseErrorHandler() { // from class: com.myor.mydex.database.DatabaseHelper.1
                @Override // android.database.DatabaseErrorHandler
                public void onCorruption(SQLiteDatabase sQLiteDatabase) {
                    DatabaseHelper.this.createDatabase();
                }
            });
            return true;
        } catch (SQLiteException e) {
            Log.e("File not found in data", e.toString());
            return false;
        }
    }

    public boolean copyDatabaseROOTtoSDCARD_COPY() {
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("/data/data/com.myor.mydex/databases/mydex.db"));
            File file = new File(DB_SDCARD_PATH + "BACKUP_mydex.db");
            if (file.exists()) {
                file.delete();
                System.out.println("Old file is deleted");
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = fileInputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileInputStream.close();
                    fileOutputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void openDatabase() {
        String path = this.mContext.getDatabasePath(DBNAME).getPath();
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen()) {
            this.mDatabase = SQLiteDatabase.openDatabase(path, null, 0);
        }
    }

    public void closeDatabase() {
        SQLiteDatabase sQLiteDatabase = this.mDatabase;
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }

    public boolean copyDatabaseFromAsset() {
        try {
            InputStream open = this.mContext.getAssets().open(DBNAME);
            FileOutputStream fileOutputStream = new FileOutputStream("/data/data/com.myor.mydex/databases/mydex.db");
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    Log.w("MainActivity", "DB copied");
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
