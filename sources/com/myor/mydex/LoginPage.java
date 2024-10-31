package com.myor.mydex;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import com.google.android.material.snackbar.Snackbar;
import com.myor.mydex.apiclient.ApiClient;
import com.myor.mydex.entity.MonitoringH;
import com.myor.mydex.repository.SpeRepo;
import com.myor.mydex.util.CheckConnectionService;
import com.myor.mydex.util.DeviceIdentifier;
import com.myor.mydex.util.TriggerGetDataWorker;
import com.myor.mydex.util.UtilsTools;
import java.util.List;

/* loaded from: classes2.dex */
public class LoginPage extends AppCompatActivity {
    private static final String TAG = "LoginPage";
    private ApiClient apiClient;
    private Button btnLogin;
    private CheckConnectionService checkConnectionService;
    private Context context;
    private EditText edtPassword;
    private DeviceIdentifier identifier;
    private LocationManager locationManager;
    private View mLayout;
    private ProgressDialog progressDialog;
    private SpeRepo speRepo;
    private TextView txtSerial;
    private UtilsTools utilsTools = new UtilsTools();
    private View.OnClickListener loginListener = new View.OnClickListener() { // from class: com.myor.mydex.LoginPage.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            LoginPage loginPage = LoginPage.this;
            loginPage.getApplicationContext();
            LocationManager locationManager = (LocationManager) loginPage.getSystemService("location");
            if (ActivityCompat.checkSelfPermission(LoginPage.this.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(LoginPage.this.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null && lastKnownLocation.isFromMockProvider()) {
                    UtilsTools utilsTools = LoginPage.this.utilsTools;
                    LoginPage loginPage2 = LoginPage.this;
                    utilsTools.dialogDisableFakeGps(loginPage2, loginPage2.getApplicationContext());
                    return;
                }
                if (!LoginPage.this.utilsTools.checkMockLocationAndDeveloperOption(LoginPage.this.getApplicationContext())) {
                    String obj = LoginPage.this.edtPassword.getText().toString();
                    if (obj.trim().equalsIgnoreCase("")) {
                        LoginPage.this.edtPassword.setError(LoginPage.this.context.getString(R.string.password));
                        return;
                    }
                    if (!obj.trim().equalsIgnoreCase("123")) {
                        LoginPage.this.edtPassword.setError(LoginPage.this.context.getString(R.string.password_tidak_sesuai));
                        return;
                    }
                    LoginPage loginPage3 = LoginPage.this;
                    loginPage3.getApplicationContext();
                    if (!((LocationManager) loginPage3.getSystemService("location")).isProviderEnabled("gps")) {
                        LoginPage.this.onGPS();
                        return;
                    }
                    try {
                        if (!LoginPage.this.utilsTools.initCheckDate(LoginPage.this.getApplicationContext())) {
                            Snackbar.make(LoginPage.this.mLayout, LoginPage.this.getApplicationContext().getString(R.string.tanggal_dan_waktu_otomatis_belum_diset), 0).show();
                        } else if (LoginPage.this.checkConnectionService.isInternet()) {
                            LoginPage.this.login();
                            LoginPage.this.initMainActivity();
                        } else {
                            Snackbar.make(LoginPage.this.mLayout, LoginPage.this.context.getString(R.string.please_check_internet_connection), 0).show();
                        }
                        return;
                    } catch (Exception e) {
                        e.getMessage();
                        return;
                    }
                }
                UtilsTools utilsTools2 = LoginPage.this.utilsTools;
                LoginPage loginPage4 = LoginPage.this;
                utilsTools2.dialogDisableFakeGps(loginPage4, loginPage4.getApplicationContext());
            }
        }
    };

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().build());
        super.onCreate(bundle);
        hideSystemUI();
        AppCompatDelegate.setDefaultNightMode(1);
        this.context = getApplicationContext();
        setContentView(R.layout.activity_login_page);
        initView();
        populateData();
        DeviceIdentifier deviceIdentifier = new DeviceIdentifier(getApplicationContext());
        this.identifier = deviceIdentifier;
        this.txtSerial.setText(deviceIdentifier.getUniqueID().toLowerCase());
    }

    private void initView() {
        ApiClient apiClient = new ApiClient(this.context);
        this.apiClient = apiClient;
        apiClient.setDefaultSetting();
        this.edtPassword = (EditText) findViewById(R.id.edtPassword);
        Button button = (Button) findViewById(R.id.btnLogin);
        this.btnLogin = button;
        button.setOnClickListener(this.loginListener);
        this.mLayout = findViewById(android.R.id.content);
        this.txtSerial = (TextView) findViewById(R.id.txtSerial);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void login() {
        Intent intent;
        List<MonitoringH> dataMonitoring = this.speRepo.getDataMonitoring();
        if (dataMonitoring.size() > 0) {
            intent = new Intent(getApplicationContext(), (Class<?>) DashboardPage.class);
            intent.putExtra("speNo", dataMonitoring.get(0).getSpeNo());
            intent.putExtra("driver", dataMonitoring.get(0).getDriver());
            intent.putExtra("driverPhone", dataMonitoring.get(0).getDriverPhone());
            intent.putExtra("policeNo", dataMonitoring.get(0).getPoliceNo());
            intent.putExtra(NotificationCompat.CATEGORY_STATUS, dataMonitoring.get(0).getStatus());
        } else {
            intent = new Intent(getApplicationContext(), (Class<?>) InputSpeActivity.class);
            intent.putExtra("serialPhone", this.txtSerial.getText());
        }
        startActivity(intent);
    }

    private void populateData() {
        this.checkConnectionService = new CheckConnectionService(getApplicationContext());
        this.speRepo = new SpeRepo(this.context);
    }

    public void onGPS() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.aktifkan_gps)).setCancelable(false).setPositiveButton(getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.LoginPage.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                LoginPage.this.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
            }
        }).setNegativeButton(getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.LoginPage.2
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    private void hideSystemUI() {
        getWindow().getDecorView().setSystemUiVisibility(3846);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initMainActivity() {
        new TriggerGetDataWorker(getApplicationContext()).runSchedulerSyncDataAutomatic();
    }
}
