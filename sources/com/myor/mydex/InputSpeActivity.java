package com.myor.mydex;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.material.snackbar.Snackbar;
import com.myor.mydex.apiclient.ApiClient;
import com.myor.mydex.entity.BranchDto;
import com.myor.mydex.entity.InputSPEDto;
import com.myor.mydex.entity.Reason;
import com.myor.mydex.entity.ResponseDto;
import com.myor.mydex.entity.Xkey;
import com.myor.mydex.preferences.DriverPreferences;
import com.myor.mydex.preferences.SettingPreferences;
import com.myor.mydex.repository.SpeRepo;
import com.myor.mydex.repository.SyncRepo;
import com.myor.mydex.rest.SpeRest;
import com.myor.mydex.util.CheckConnectionService;
import com.myor.mydex.util.UtilsTools;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class InputSpeActivity extends AppCompatActivity {
    private ApiClient apiClient;
    private CheckConnectionService checkConnectionService;
    private Context context;
    private String driverName;
    private String driverPhone;
    private DriverPreferences driverPreferences;
    private EditText edtNamaDriver;
    private EditText edtNoSpe;
    private EditText edtNomorMobil;
    private EditText edtNomorTlp;
    private String inputDate;
    private View mLayout;
    private String noMobil;
    private ProgressDialog progressDialog;
    private String speNo;
    private SpeRepo speRepo;
    private SpeRest speRest;
    private String status;
    private SwipeButton swipeSave;
    private SyncRepo syncRepo;
    private String serialPhone = "";
    private SettingPreferences settingPreferences = new SettingPreferences();
    private UtilsTools utilsTools = new UtilsTools();

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AppCompatDelegate.setDefaultNightMode(1);
        setContentView(R.layout.activity_input_spe);
        this.context = getApplicationContext();
        this.serialPhone = getIntent().getStringExtra("serialPhone");
        initView();
    }

    private void initView() {
        this.apiClient = new ApiClient(this.context);
        this.checkConnectionService = new CheckConnectionService(this.context);
        this.speRest = (SpeRest) this.apiClient.getClientToWsMobile(this.context).create(SpeRest.class);
        this.speRepo = new SpeRepo(this.context);
        this.syncRepo = new SyncRepo(this.context);
        this.mLayout = findViewById(android.R.id.content);
        this.swipeSave = (SwipeButton) findViewById(R.id.swipeSave);
        this.edtNoSpe = (EditText) findViewById(R.id.edtNoSpe);
        this.edtNamaDriver = (EditText) findViewById(R.id.edtNamaDriver);
        this.edtNomorTlp = (EditText) findViewById(R.id.edtNomorTlp);
        this.edtNomorMobil = (EditText) findViewById(R.id.edtNomorMobil);
        this.swipeSave.setOnStateChangeListener(new OnStateChangeListener() { // from class: com.myor.mydex.InputSpeActivity.1
            @Override // com.ebanx.swipebtn.OnStateChangeListener
            public void onStateChange(boolean z) {
                if (z) {
                    InputSpeActivity.this.simpanData();
                }
            }
        });
        this.speRest = (SpeRest) this.apiClient.getClientToWsMobile(this.context).create(SpeRest.class);
        this.driverPreferences = new DriverPreferences();
        String keyDrivername = DriverPreferences.getKeyDrivername(getApplicationContext());
        String keyDriverphone = DriverPreferences.getKeyDriverphone(getApplicationContext());
        String keyPoliceno = DriverPreferences.getKeyPoliceno(getApplicationContext());
        if (keyDrivername.equalsIgnoreCase("")) {
            return;
        }
        this.edtNamaDriver.setText(keyDrivername);
        this.edtNomorTlp.setText(keyDriverphone);
        this.edtNomorMobil.setText(keyPoliceno);
    }

    public void simpanData() {
        if (this.edtNoSpe.getText().length() == 0 || this.edtNamaDriver.getText().length() == 0 || this.edtNomorMobil.getText().length() == 0 || this.edtNomorTlp.getText().length() == 0) {
            new AlertDialog.Builder(this).setTitle(R.string.informasi).setMessage(R.string.lengkapi_data).setCancelable(false).setPositiveButton(R.string.ya, new DialogInterface.OnClickListener() { // from class: com.myor.mydex.InputSpeActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                    InputSpeActivity.this.swipeSave.toggleState();
                }
            }).show();
            return;
        }
        new AlertDialog.Builder(this).setTitle(R.string.konfirmasi).setMessage("Apakah anda yakin untuk simpan data : \nNomor SPE : " + ((Object) this.edtNoSpe.getText()) + "\nDriver : " + ((Object) this.edtNamaDriver.getText()) + "\nNomor Driver : " + ((Object) this.edtNomorTlp.getText()) + "\nNomor Mobil : " + ((Object) this.edtNomorMobil.getText()) + "\n").setCancelable(false).setPositiveButton(R.string.ya, new AnonymousClass4()).setNegativeButton(R.string.tidak, new DialogInterface.OnClickListener() { // from class: com.myor.mydex.InputSpeActivity.3
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                InputSpeActivity.this.swipeSave.toggleState();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.myor.mydex.InputSpeActivity$4, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass4 implements DialogInterface.OnClickListener {
        AnonymousClass4() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            InputSpeActivity inputSpeActivity = InputSpeActivity.this;
            inputSpeActivity.getApplicationContext();
            LocationManager locationManager = (LocationManager) inputSpeActivity.getSystemService("location");
            if (ActivityCompat.checkSelfPermission(InputSpeActivity.this.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(InputSpeActivity.this.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null && lastKnownLocation.isFromMockProvider()) {
                    UtilsTools utilsTools = InputSpeActivity.this.utilsTools;
                    InputSpeActivity inputSpeActivity2 = InputSpeActivity.this;
                    utilsTools.dialogDisableFakeGps(inputSpeActivity2, inputSpeActivity2.getApplicationContext());
                    return;
                }
                if (!InputSpeActivity.this.utilsTools.checkMockLocationAndDeveloperOption(InputSpeActivity.this.getApplicationContext())) {
                    InputSpeActivity inputSpeActivity3 = InputSpeActivity.this;
                    inputSpeActivity3.getApplicationContext();
                    if (!((LocationManager) inputSpeActivity3.getSystemService("location")).isProviderEnabled("gps")) {
                        new LoginPage().onGPS();
                        return;
                    }
                    try {
                        if (!InputSpeActivity.this.utilsTools.initCheckDate(InputSpeActivity.this.getApplicationContext())) {
                            Snackbar.make(InputSpeActivity.this.mLayout, InputSpeActivity.this.getApplicationContext().getString(R.string.tanggal_dan_waktu_otomatis_belum_diset), 0).show();
                        } else if (InputSpeActivity.this.checkConnectionService.isInternet()) {
                            try {
                                InputSpeActivity inputSpeActivity4 = InputSpeActivity.this;
                                inputSpeActivity4.progressDialog = ProgressDialog.show(inputSpeActivity4, "Synchronize ...", inputSpeActivity4.context.getString(R.string.please_wait), true);
                                InputSpeActivity.this.progressDialog.setCancelable(false);
                                InputSpeActivity inputSpeActivity5 = InputSpeActivity.this;
                                inputSpeActivity5.speNo = inputSpeActivity5.edtNoSpe.getText().toString().toUpperCase().replaceAll("[^A-Z0-9]", "");
                                InputSpeActivity inputSpeActivity6 = InputSpeActivity.this;
                                inputSpeActivity6.driverName = inputSpeActivity6.edtNamaDriver.getText().toString();
                                InputSpeActivity inputSpeActivity7 = InputSpeActivity.this;
                                inputSpeActivity7.driverPhone = inputSpeActivity7.edtNomorTlp.getText().toString();
                                InputSpeActivity inputSpeActivity8 = InputSpeActivity.this;
                                inputSpeActivity8.noMobil = inputSpeActivity8.edtNomorMobil.getText().toString();
                                InputSpeActivity.this.status = "0";
                                InputSpeActivity inputSpeActivity9 = InputSpeActivity.this;
                                inputSpeActivity9.inputDate = inputSpeActivity9.utilsTools.getCurrentDateSample11();
                                InputSPEDto inputSPEDto = new InputSPEDto();
                                inputSPEDto.setSpeNo(InputSpeActivity.this.speNo);
                                inputSPEDto.setDriverName(InputSpeActivity.this.driverName);
                                inputSPEDto.setDriverPhone(InputSpeActivity.this.driverPhone);
                                inputSPEDto.setPoliceNo(InputSpeActivity.this.noMobil);
                                inputSPEDto.setSerialPhone(InputSpeActivity.this.serialPhone);
                                inputSPEDto.setInputDate(InputSpeActivity.this.inputDate);
                                InputSpeActivity.this.speRest.saveData(inputSPEDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.InputSpeActivity.4.1
                                    @Override // retrofit2.Callback
                                    public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                                        if (response.isSuccessful()) {
                                            String errorCode = response.body().getErrorCode();
                                            String errorMessage = response.body().getErrorMessage();
                                            if (errorCode.equalsIgnoreCase("200")) {
                                                if (errorMessage.equalsIgnoreCase("")) {
                                                    InputSpeActivity.this.speRepo.inputMonitoring(InputSpeActivity.this.speNo, InputSpeActivity.this.driverName, InputSpeActivity.this.driverPhone, InputSpeActivity.this.noMobil, InputSpeActivity.this.serialPhone, InputSpeActivity.this.status, InputSpeActivity.this.inputDate);
                                                } else if (errorMessage.equalsIgnoreCase("exists")) {
                                                    InputSpeActivity.this.speNo = response.body().getMonitoring().getSpeNo();
                                                    InputSpeActivity.this.driverName = response.body().getMonitoring().getDriver();
                                                    InputSpeActivity.this.driverPhone = response.body().getMonitoring().getDriverPhone();
                                                    InputSpeActivity.this.noMobil = response.body().getMonitoring().getPoliceNo();
                                                    InputSpeActivity.this.status = response.body().getMonitoring().getStatus();
                                                    InputSpeActivity.this.inputDate = response.body().getMonitoring().getTimeInput();
                                                    InputSpeActivity.this.deleteTemp();
                                                    InputSpeActivity.this.speRepo.inputMonitoring(InputSpeActivity.this.speNo, InputSpeActivity.this.driverName, InputSpeActivity.this.driverPhone, InputSpeActivity.this.noMobil, InputSpeActivity.this.serialPhone, InputSpeActivity.this.status, InputSpeActivity.this.inputDate);
                                                }
                                                InputSpeActivity.this.syncMaster(response.body().getmXkey(), response.body().getmReason(), response.body().getmWarehouse());
                                                InputSpeActivity.this.progressDialog.dismiss();
                                                InputSpeActivity.this.swipeSave.toggleState();
                                                DriverPreferences unused = InputSpeActivity.this.driverPreferences;
                                                DriverPreferences.clearKeyToken(InputSpeActivity.this.getApplicationContext());
                                                DriverPreferences unused2 = InputSpeActivity.this.driverPreferences;
                                                DriverPreferences.setKeyDrivername(InputSpeActivity.this.getApplicationContext(), InputSpeActivity.this.driverName);
                                                DriverPreferences unused3 = InputSpeActivity.this.driverPreferences;
                                                DriverPreferences.setKeyDriverphone(InputSpeActivity.this.getApplicationContext(), InputSpeActivity.this.driverPhone);
                                                DriverPreferences unused4 = InputSpeActivity.this.driverPreferences;
                                                DriverPreferences.setKeyPoliceno(InputSpeActivity.this.getApplicationContext(), InputSpeActivity.this.noMobil);
                                                InputSpeActivity.this.intentMainActivity(InputSpeActivity.this.speNo, InputSpeActivity.this.driverName, InputSpeActivity.this.driverPhone, InputSpeActivity.this.noMobil, InputSpeActivity.this.status);
                                                return;
                                            }
                                            InputSpeActivity.this.progressDialog.dismiss();
                                            new AlertDialog.Builder(InputSpeActivity.this).setTitle(InputSpeActivity.this.getApplicationContext().getString(R.string.informasi)).setMessage(errorMessage).setCancelable(false).setPositiveButton(InputSpeActivity.this.getApplicationContext().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.InputSpeActivity.4.1.1
                                                @Override // android.content.DialogInterface.OnClickListener
                                                public void onClick(DialogInterface dialogInterface2, int i2) {
                                                    dialogInterface2.dismiss();
                                                    InputSpeActivity.this.swipeSave.toggleState();
                                                }
                                            }).show();
                                            return;
                                        }
                                        InputSpeActivity.this.progressDialog.dismiss();
                                        InputSpeActivity.this.swipeSave.toggleState();
                                        Toast.makeText(InputSpeActivity.this.getApplicationContext(), InputSpeActivity.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                                    }

                                    @Override // retrofit2.Callback
                                    public void onFailure(Call<ResponseDto> call, Throwable th) {
                                        InputSpeActivity.this.progressDialog.dismiss();
                                        InputSpeActivity.this.swipeSave.toggleState();
                                        Toast.makeText(InputSpeActivity.this.getApplicationContext(), InputSpeActivity.this.getApplicationContext().getString(R.string.failed_sync), 1).show();
                                    }
                                });
                            } catch (Exception e) {
                                try {
                                    e.getMessage();
                                    e.printStackTrace();
                                    InputSpeActivity.this.progressDialog.dismiss();
                                    InputSpeActivity.this.swipeSave.toggleState();
                                    Toast.makeText(InputSpeActivity.this.getApplicationContext(), InputSpeActivity.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1);
                                } catch (Exception e2) {
                                    e2.getMessage();
                                    e2.printStackTrace();
                                }
                            }
                        } else {
                            Snackbar.make(InputSpeActivity.this.mLayout, InputSpeActivity.this.context.getString(R.string.please_check_internet_connection), 0).show();
                        }
                        return;
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        e3.getMessage();
                        return;
                    }
                }
                UtilsTools utilsTools2 = InputSpeActivity.this.utilsTools;
                InputSpeActivity inputSpeActivity10 = InputSpeActivity.this;
                utilsTools2.dialogDisableFakeGps(inputSpeActivity10, inputSpeActivity10.getApplicationContext());
            }
        }
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(getString(R.string.keluar_aplikasi)).setCancelable(false).setPositiveButton(getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.InputSpeActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                InputSpeActivity.this.startActivity(new Intent(InputSpeActivity.this.getApplicationContext(), (Class<?>) LoginPage.class));
            }
        }).setNegativeButton(getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.InputSpeActivity.5
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        builder.create().show();
    }

    public void intentMainActivity(String str, String str2, String str3, String str4, String str5) {
        Intent intent = new Intent(getApplicationContext(), (Class<?>) DashboardPage.class);
        intent.putExtra("speNo", str);
        intent.putExtra("driver", str2);
        intent.putExtra("driverPhone", str3);
        intent.putExtra("policeNo", str4);
        intent.putExtra(NotificationCompat.CATEGORY_STATUS, str5);
        startActivity(intent);
    }

    public void deleteTemp() {
        this.speRepo.deleteMonitoring("t_monitoring_h");
        this.speRepo.deleteMonitoring("t_monitoring_d");
    }

    public void syncMaster(List<Xkey> list, List<Reason> list2, List<BranchDto> list3) {
        if (list.size() > 0) {
            this.syncRepo.deleteMXkey();
            this.syncRepo.insertmXkey(list);
        }
        if (list2.size() > 0) {
            this.syncRepo.deleteMReason();
            this.syncRepo.insertReason(list2);
        }
        if (list3.size() > 0) {
            this.syncRepo.insertBranch(list3);
        }
    }
}
