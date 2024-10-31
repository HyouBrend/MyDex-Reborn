package com.myor.mydex;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.inputmethod.InputMethodManager;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.myor.mydex.database.DatabaseHelper;
import com.myor.mydex.entity.Reason;
import com.myor.mydex.repository.SpeRepo;
import java.util.List;

/* loaded from: classes2.dex */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private DatabaseHelper helper;
    private InputMethodManager imm;
    private AppBarConfiguration mAppBarConfiguration;
    private List<Reason> reasonGagalScan;
    private SpeRepo speRepo;
    private Testing test;

    /* loaded from: classes2.dex */
    public interface Testing {
        void test();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        this.speRepo = new SpeRepo(this);
        Log.d(TAG, "onCreate: ");
        setContentView(R.layout.activity_main);
        getApplicationContext();
        this.imm = (InputMethodManager) getSystemService("input_method");
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        this.mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_dashboard).setDrawerLayout(drawerLayout).build();
        NavController findNavController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, findNavController, this.mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, findNavController);
    }

    private void logOut() {
        startActivity(new Intent(getApplicationContext(), (Class<?>) LoginPage.class));
    }

    @Override // androidx.appcompat.app.AppCompatActivity
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this, R.id.nav_host_fragment), this.mAppBarConfiguration) || super.onSupportNavigateUp();
    }

    public boolean popFragment() {
        getSupportFragmentManager().findFragmentById(R.id.container);
        if (getSupportFragmentManager().getBackStackEntryCount() <= 0) {
            return false;
        }
        getSupportFragmentManager().popBackStackImmediate();
        return true;
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        if (popFragment()) {
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.keluar_aplikasi)).setCancelable(false).setPositiveButton(getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.MainActivity.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                MainActivity.this.startActivity(new Intent(MainActivity.this.getApplicationContext(), (Class<?>) LoginPage.class));
            }
        }).setNegativeButton(getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.MainActivity.1
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        this.speRepo.getDataMonitoring();
        if (parseActivityResult.getContents() == null) {
            this.test.test();
        }
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle bundle, PersistableBundle persistableBundle) {
        super.onSaveInstanceState(bundle, persistableBundle);
        Log.d(TAG, "onSaveInstanceState");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }

    public void setActionBarTitle(String str) {
        getSupportActionBar().setTitle(str);
    }
}
