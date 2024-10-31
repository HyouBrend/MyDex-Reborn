package com.myor.mydex;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.myor.mydex.database.DatabaseHelper;
import com.myor.mydex.repository.CreateTableRepo;
import com.myor.mydex.util.UtilsTools;
import java.io.File;
import java.io.FileOutputStream;
import net.lingala.zip4j.util.InternalZipConstants;

/* loaded from: classes2.dex */
public class SplashScreenPage extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CODE = 123;
    public static String PHOTO_PATH_SDCARD = "";
    private boolean connect = false;
    private Context ctx;
    private DatabaseHelper helper;
    private UtilsTools utilsTools;

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        hideSystemUI();
        setContentView(R.layout.activity_splash_screen_page);
        new Handler().postDelayed(new Runnable() { // from class: com.myor.mydex.SplashScreenPage.1
            @Override // java.lang.Runnable
            public void run() {
                if (Build.VERSION.SDK_INT >= 23) {
                    SplashScreenPage.this.checkPermission();
                } else {
                    SplashScreenPage.this.doIntent();
                }
            }
        }, 2000L);
    }

    public boolean isConnect() {
        return this.connect;
    }

    public void setConnect(boolean z) {
        this.connect = z;
    }

    public void checkPermission() {
        if (ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.INTERNET") + ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.CAMERA") + ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_EXTERNAL_STORAGE") + ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.WRITE_EXTERNAL_STORAGE") + ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.READ_PHONE_STATE") + ContextCompat.checkSelfPermission(getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") != 0) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.INTERNET") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.CAMERA") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_EXTERNAL_STORAGE") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.WRITE_EXTERNAL_STORAGE") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.READ_PHONE_STATE") || ActivityCompat.shouldShowRequestPermissionRationale(this, "android.permission.ACCESS_FINE_LOCATION")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Camera, Read Contacts and Write External Storage permissions are required to do the task.");
                builder.setTitle("Please grant those permissions");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { // from class: com.myor.mydex.SplashScreenPage.2
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        ActivityCompat.requestPermissions(SplashScreenPage.this, new String[]{"android.permission.INTERNET", "android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_FINE_LOCATION"}, 123);
                    }
                });
                builder.setNeutralButton("Cancel", (DialogInterface.OnClickListener) null);
                builder.create().show();
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{"android.permission.INTERNET", "android.permission.CAMERA", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_FINE_LOCATION"}, 123);
            return;
        }
        doIntent();
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(3846);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doIntent() {
        new Handler().postDelayed(new Runnable() { // from class: com.myor.mydex.SplashScreenPage.3
            @Override // java.lang.Runnable
            public void run() {
                SplashScreenPage.this.helper = new DatabaseHelper(SplashScreenPage.this.getApplicationContext());
                SplashScreenPage.this.helper.createFolder();
                if (!SplashScreenPage.this.createNoMedia()) {
                    Toast.makeText(SplashScreenPage.this.getApplicationContext(), "Hidden Photo Failed!", 1).show();
                }
                SplashScreenPage splashScreenPage = SplashScreenPage.this;
                DatabaseHelper unused = splashScreenPage.helper;
                splashScreenPage.setConnect(DatabaseHelper.getInstance(SplashScreenPage.this.getApplicationContext()).createDatabase());
                if (SplashScreenPage.this.isConnect()) {
                    new CreateTableRepo(SplashScreenPage.this.getApplicationContext()).updateTable();
                    SplashScreenPage.this.startActivity(new Intent(SplashScreenPage.this.getApplicationContext(), (Class<?>) LoginPage.class));
                }
            }
        }, 2000L);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        super.onRequestPermissionsResult(i, strArr, iArr);
        if (i != 123) {
            return;
        }
        if (iArr.length > 0 && iArr[0] + iArr[1] + iArr[2] + iArr[3] + iArr[4] + iArr[5] == 0) {
            Toast.makeText(getApplicationContext(), "Permissions granted.", 0).show();
            doIntent();
        } else {
            Toast.makeText(getApplicationContext(), "Permissions denied.", 0).show();
            doIntent();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean createNoMedia() {
        try {
            if (Build.VERSION.SDK_INT >= 30) {
                PHOTO_PATH_SDCARD = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/myor-mobile/mydex-apps/photo/";
            } else {
                PHOTO_PATH_SDCARD = Environment.getExternalStorageDirectory() + "/myor-mobile/mydex-apps/photo/";
            }
            File file = new File(PHOTO_PATH_SDCARD + InternalZipConstants.ZIP_FILE_SEPARATOR + File.separator + ".nomedia");
            byte[] bArr = {1, 1, 0, 0};
            if (!file.exists()) {
                file.createNewFile();
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(bArr);
                fileOutputStream.close();
                System.out.println("file created: " + file);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
