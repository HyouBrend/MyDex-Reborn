package com.myor.mydex;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.exifinterface.media.ExifInterface;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.myor.mydex.DashboardPage;
import com.myor.mydex.adapter.ViewDoAdapter;
import com.myor.mydex.adapter.ViewDoDetailAdapter;
import com.myor.mydex.apiclient.ApiClient;
import com.myor.mydex.database.DatabaseHelper;
import com.myor.mydex.entity.BranchDto;
import com.myor.mydex.entity.InputSPEDto;
import com.myor.mydex.entity.LatLong;
import com.myor.mydex.entity.MonitoringD;
import com.myor.mydex.entity.MonitoringDto;
import com.myor.mydex.entity.MonitoringH;
import com.myor.mydex.entity.MonitoringPhoto;
import com.myor.mydex.entity.Reason;
import com.myor.mydex.entity.ResponseDto;
import com.myor.mydex.entity.ValidateDto;
import com.myor.mydex.repository.SpeRepo;
import com.myor.mydex.repository.SyncRepo;
import com.myor.mydex.rest.SpeRest;
import com.myor.mydex.util.CheckConnectionService;
import com.myor.mydex.util.DistanceCalculator;
import com.myor.mydex.util.NotificationService;
import com.myor.mydex.util.TriggerGetDataWorker;
import com.myor.mydex.util.UtilsTools;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class DashboardPage extends AppCompatActivity implements ViewDoAdapter.OnClickDetail {
    public static final String TAG = "DashboardPage";
    private AlertDialog alertDialog;
    private AlertDialog alertDialogDetail;
    private ApiClient apiClient;
    private Button btnAction;
    private Button btnRefresh;
    private Button btnViewDo;
    private CheckConnectionService checkConnectionService;
    private Context context;
    private DatabaseHelper databaseHelper;
    private String driverPhone;
    private RecyclerView listViewDetailDo;
    private RecyclerView listViewDo;
    private View mLayout;
    private ProgressDialog progressDialog;
    private List<Reason> reasonNotScan;
    private List<Reason> reasonOutRadius;
    private Runnable runnable;
    private SpeRepo speRepo;
    private SpeRest speRest;
    private String status;
    private SwipeButton swipeButton;
    private SyncRepo syncRepo;
    private String tujuanId;
    private TextView txtDriver;
    private TextView txtInfoFinishBongkar;
    private TextView txtInfoMulaiBongkar;
    private TextView txtInfoStart;
    private TextView txtJalur;
    private TextView txtMobil;
    private TextView txtSpe;
    private TextView txtStatus;
    private TextView txtTanggal;
    private TextView txtTotalDo;
    private TextView txtTujuan;
    private ViewDoAdapter viewDoAdapter;
    private ViewDoDetailAdapter viewDoDetailAdapter;
    private String barcodeOrigin = "0";
    private Handler handler = new Handler();
    private double latitude = Utils.DOUBLE_EPSILON;
    private double longitude = Utils.DOUBLE_EPSILON;
    private double radiusLimit = Utils.DOUBLE_EPSILON;
    private UtilsTools utilsTools = new UtilsTools();
    private String tujuanType = "";
    private long rangeWaktu = 15;
    private String timeCheckout = "";
    private String timeArrive = "";
    private String timeStartBongkar = "";
    private String timeFinishBongkar = "";
    private String timeCheckoutSubdist = "";
    private View.OnClickListener refreshListener = new View.OnClickListener() { // from class: com.myor.mydex.DashboardPage.13
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DashboardPage dashboardPage = DashboardPage.this;
            dashboardPage.getApplicationContext();
            LocationManager locationManager = (LocationManager) dashboardPage.getSystemService("location");
            if (ActivityCompat.checkSelfPermission(DashboardPage.this.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(DashboardPage.this.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null && lastKnownLocation.isFromMockProvider()) {
                    UtilsTools utilsTools = DashboardPage.this.utilsTools;
                    DashboardPage dashboardPage2 = DashboardPage.this;
                    utilsTools.dialogDisableFakeGps(dashboardPage2, dashboardPage2.getApplicationContext());
                    return;
                }
                if (!DashboardPage.this.utilsTools.checkMockLocationAndDeveloperOption(DashboardPage.this.getApplicationContext())) {
                    DashboardPage dashboardPage3 = DashboardPage.this;
                    dashboardPage3.getApplicationContext();
                    if (!((LocationManager) dashboardPage3.getSystemService("location")).isProviderEnabled("gps")) {
                        new LoginPage().onGPS();
                        return;
                    }
                    try {
                        if (!DashboardPage.this.utilsTools.initCheckDate(DashboardPage.this.getApplicationContext())) {
                            Snackbar.make(DashboardPage.this.mLayout, DashboardPage.this.getApplicationContext().getString(R.string.tanggal_dan_waktu_otomatis_belum_diset), 0).show();
                        } else if (DashboardPage.this.checkConnectionService.isInternet()) {
                            try {
                                DashboardPage dashboardPage4 = DashboardPage.this;
                                dashboardPage4.syncData(dashboardPage4.txtSpe.getText().toString());
                            } catch (Exception e) {
                                e.getMessage();
                                e.printStackTrace();
                            }
                        } else {
                            Snackbar.make(DashboardPage.this.mLayout, DashboardPage.this.context.getString(R.string.please_check_internet_connection), 0).show();
                        }
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        e2.getMessage();
                        return;
                    }
                }
                UtilsTools utilsTools2 = DashboardPage.this.utilsTools;
                DashboardPage dashboardPage5 = DashboardPage.this;
                utilsTools2.dialogDisableFakeGps(dashboardPage5, dashboardPage5.getApplicationContext());
            }
        }
    };
    private View.OnClickListener viewDoListener = new View.OnClickListener() { // from class: com.myor.mydex.DashboardPage.18
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0115 -> B:22:0x014e). Please report as a decompilation issue!!! */
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DashboardPage dashboardPage = DashboardPage.this;
            dashboardPage.getApplicationContext();
            LocationManager locationManager = (LocationManager) dashboardPage.getSystemService("location");
            if (ActivityCompat.checkSelfPermission(DashboardPage.this.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(DashboardPage.this.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                if (lastKnownLocation != null && lastKnownLocation.isFromMockProvider()) {
                    UtilsTools utilsTools = DashboardPage.this.utilsTools;
                    DashboardPage dashboardPage2 = DashboardPage.this;
                    utilsTools.dialogDisableFakeGps(dashboardPage2, dashboardPage2.getApplicationContext());
                    return;
                }
                if (!DashboardPage.this.utilsTools.checkMockLocationAndDeveloperOption(DashboardPage.this.getApplicationContext())) {
                    DashboardPage dashboardPage3 = DashboardPage.this;
                    dashboardPage3.getApplicationContext();
                    if (!((LocationManager) dashboardPage3.getSystemService("location")).isProviderEnabled("gps")) {
                        new LoginPage().onGPS();
                        return;
                    }
                    try {
                        if (!DashboardPage.this.utilsTools.initCheckDate(DashboardPage.this.getApplicationContext())) {
                            Snackbar.make(DashboardPage.this.mLayout, DashboardPage.this.getApplicationContext().getString(R.string.tanggal_dan_waktu_otomatis_belum_diset), 0).show();
                        } else if (DashboardPage.this.checkConnectionService.isInternet()) {
                            try {
                                if (DashboardPage.this.txtTotalDo.getText().toString().equalsIgnoreCase("0")) {
                                    new AlertDialog.Builder(DashboardPage.this).setTitle(DashboardPage.this.getApplicationContext().getString(R.string.informasi)).setMessage(DashboardPage.this.getApplicationContext().getString(R.string.detail_do_tidak_ada)).setCancelable(false).setPositiveButton(DashboardPage.this.getApplicationContext().getText(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.18.1
                                        @Override // android.content.DialogInterface.OnClickListener
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.dismiss();
                                        }
                                    }).show();
                                } else {
                                    DashboardPage.this.showDialogDo();
                                }
                            } catch (Exception e) {
                                e.getMessage();
                                e.printStackTrace();
                            }
                        } else {
                            Snackbar.make(DashboardPage.this.mLayout, DashboardPage.this.context.getString(R.string.please_check_internet_connection), 0).show();
                        }
                        return;
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        e2.getMessage();
                        return;
                    }
                }
                UtilsTools utilsTools2 = DashboardPage.this.utilsTools;
                DashboardPage dashboardPage4 = DashboardPage.this;
                utilsTools2.dialogDisableFakeGps(dashboardPage4, dashboardPage4.getApplicationContext());
            }
        }
    };

    private void uploadAndSendingPhoto(MonitoringPhoto monitoringPhoto) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AppCompatDelegate.setDefaultNightMode(1);
        setContentView(R.layout.fragment_dashboard_page);
        this.context = getApplicationContext();
        initView();
    }

    private void initView() {
        this.apiClient = new ApiClient(this.context);
        this.speRepo = new SpeRepo(this.context);
        this.syncRepo = new SyncRepo(this.context);
        this.databaseHelper = new DatabaseHelper(this.context);
        this.checkConnectionService = new CheckConnectionService(this.context);
        this.speRest = (SpeRest) this.apiClient.getClientToWsMobile(this.context).create(SpeRest.class);
        this.txtMobil = (TextView) findViewById(R.id.txtMobil);
        this.txtDriver = (TextView) findViewById(R.id.txtDriver);
        this.txtTanggal = (TextView) findViewById(R.id.txtTanggal);
        this.txtJalur = (TextView) findViewById(R.id.txtJalur);
        this.txtTujuan = (TextView) findViewById(R.id.txtTujuan);
        this.txtSpe = (TextView) findViewById(R.id.txtSpe);
        this.txtTotalDo = (TextView) findViewById(R.id.txtTotalDo);
        this.txtStatus = (TextView) findViewById(R.id.txtStatus);
        Button button = (Button) findViewById(R.id.btnRefresh);
        this.btnRefresh = button;
        button.setOnClickListener(this.refreshListener);
        Button button2 = (Button) findViewById(R.id.btnViewDo);
        this.btnViewDo = button2;
        button2.setOnClickListener(this.viewDoListener);
        this.mLayout = findViewById(android.R.id.content);
        this.txtInfoStart = (TextView) findViewById(R.id.txtInfoStart);
        this.txtInfoMulaiBongkar = (TextView) findViewById(R.id.txtInfoMulaiBongkar);
        this.txtInfoFinishBongkar = (TextView) findViewById(R.id.txtInfoFinishBongkar);
        SwipeButton swipeButton = (SwipeButton) findViewById(R.id.swipeButton);
        this.swipeButton = swipeButton;
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() { // from class: com.myor.mydex.DashboardPage.1
            @Override // com.ebanx.swipebtn.OnStateChangeListener
            public void onStateChange(boolean z) {
                if (z) {
                    DashboardPage dashboardPage = DashboardPage.this;
                    dashboardPage.getApplicationContext();
                    LocationManager locationManager = (LocationManager) dashboardPage.getSystemService("location");
                    if (ActivityCompat.checkSelfPermission(DashboardPage.this.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(DashboardPage.this.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                        Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                        if (lastKnownLocation != null && lastKnownLocation.isFromMockProvider()) {
                            UtilsTools utilsTools = DashboardPage.this.utilsTools;
                            DashboardPage dashboardPage2 = DashboardPage.this;
                            utilsTools.dialogDisableFakeGps(dashboardPage2, dashboardPage2.getApplicationContext());
                            return;
                        }
                        if (!DashboardPage.this.utilsTools.checkMockLocationAndDeveloperOption(DashboardPage.this.getApplicationContext())) {
                            DashboardPage dashboardPage3 = DashboardPage.this;
                            dashboardPage3.getApplicationContext();
                            if (!((LocationManager) dashboardPage3.getSystemService("location")).isProviderEnabled("gps")) {
                                new LoginPage().onGPS();
                                return;
                            }
                            try {
                                if (!DashboardPage.this.utilsTools.initCheckDate(DashboardPage.this.getApplicationContext())) {
                                    Snackbar.make(DashboardPage.this.mLayout, DashboardPage.this.getApplicationContext().getString(R.string.tanggal_dan_waktu_otomatis_belum_diset), 0).show();
                                } else if (DashboardPage.this.checkConnectionService.isInternet()) {
                                    DashboardPage.this.actionSwipe();
                                } else {
                                    Snackbar.make(DashboardPage.this.mLayout, DashboardPage.this.context.getString(R.string.please_check_internet_connection), 0).show();
                                }
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                e.getMessage();
                                return;
                            }
                        }
                        UtilsTools utilsTools2 = DashboardPage.this.utilsTools;
                        DashboardPage dashboardPage4 = DashboardPage.this;
                        utilsTools2.dialogDisableFakeGps(dashboardPage4, dashboardPage4.getApplicationContext());
                    }
                }
            }
        });
        populateData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void actionSwipe() throws ParseException {
        List<MonitoringH> dataMonitoring = this.speRepo.getDataMonitoring();
        TriggerGetDataWorker triggerGetDataWorker = new TriggerGetDataWorker(getApplicationContext());
        if (this.status.equalsIgnoreCase("0")) {
            scanQR();
            triggerGetDataWorker.runSchedulerSyncDataAutomatic();
            this.swipeButton.toggleState();
            return;
        }
        if (this.status.equalsIgnoreCase("1")) {
            if (dataMonitoring.get(0).getDestinationId() != null || !dataMonitoring.get(0).getDestinationId().equalsIgnoreCase("")) {
                if (dataMonitoring.get(0).getFlagRoute() != null) {
                    if (dataMonitoring.get(0).getFlagRoute().equalsIgnoreCase("1")) {
                        ValidateDto validasiWaktu = validasiWaktu(this.timeCheckout);
                        if (validasiWaktu.isValidasi()) {
                            new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.konfirmasi)).setMessage("Anda baru saja berangkat pada " + validasiWaktu.getValidasiMessage() + " menit yang lalu. Apakah benar akan lanjut ke status tiba ?").setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.3
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DashboardPage.this.scanQR();
                                    dialogInterface.dismiss();
                                }
                            }).setNegativeButton(getApplicationContext().getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.2
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                        } else {
                            scanQR();
                        }
                        triggerGetDataWorker.closeTriggerRunning();
                    } else if (dataMonitoring.get(0).getFlagRoute().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                        ValidateDto validasiWaktu2 = validasiWaktu(this.timeCheckout);
                        if (validasiWaktu2.isValidasi()) {
                            new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.konfirmasi)).setMessage("Anda baru saja berangkat pada " + validasiWaktu2.getValidasiMessage() + " menit yang lalu. Apakah benar akan lanjut ke status in transit ?").setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.5
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    DashboardPage.this.inTransit();
                                    dialogInterface.dismiss();
                                }
                            }).setNegativeButton(getApplicationContext().getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.4
                                @Override // android.content.DialogInterface.OnClickListener
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    dialogInterface.dismiss();
                                }
                            }).show();
                        } else {
                            inTransit();
                        }
                        triggerGetDataWorker.closeTriggerRunning();
                    } else {
                        Toast.makeText(this.context, getApplicationContext().getString(R.string.data_rute_belum_ada), 1).show();
                    }
                    this.swipeButton.toggleState();
                } else {
                    Toast.makeText(this.context, getApplicationContext().getString(R.string.data_rute_belum_ada), 1).show();
                }
            } else {
                Toast.makeText(this.context, getApplicationContext().getString(R.string.data_tujuan_belum_ada), 1).show();
            }
            this.swipeButton.toggleState();
            return;
        }
        if (this.status.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
            ValidateDto validasiWaktu3 = validasiWaktu(this.timeArrive);
            if (validasiWaktu3.isValidasi()) {
                new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.konfirmasi)).setMessage("Anda baru saja tiba pada " + validasiWaktu3.getValidasiMessage() + " menit yang lalu. Apakah benar akan lanjut ke status mulai bongkar ?").setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.7
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DashboardPage.this.mulaiBongkar();
                        dialogInterface.dismiss();
                    }
                }).setNegativeButton(getApplicationContext().getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.6
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            } else {
                mulaiBongkar();
            }
            this.swipeButton.toggleState();
            return;
        }
        if (this.status.equalsIgnoreCase("4")) {
            finishBongkar();
            this.swipeButton.toggleState();
        } else if (this.status.equalsIgnoreCase("5")) {
            scanQR();
            this.swipeButton.toggleState();
        } else if (this.status.equalsIgnoreCase("6")) {
            scanOutSubdist();
            this.swipeButton.toggleState();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void mulaiBongkar() {
        new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.konfirmasi)).setMessage(getApplicationContext().getString(R.string.anda_akan_memulai_proses_bongkar)).setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.9
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DashboardPage dashboardPage = DashboardPage.this;
                dashboardPage.progressDialog = ProgressDialog.show(dashboardPage, "Update Data ...", dashboardPage.context.getString(R.string.please_wait), true);
                DashboardPage.this.progressDialog.setCancelable(false);
                final String currentDateSample11 = DashboardPage.this.utilsTools.getCurrentDateSample11();
                LatLong currentLocation = DashboardPage.this.getCurrentLocation();
                if (DashboardPage.this.speRepo.updateStatus("4", currentDateSample11, DashboardPage.this.txtSpe.getText().toString())) {
                    MonitoringDto monitoringDto = new MonitoringDto();
                    monitoringDto.setSpeNo(DashboardPage.this.txtSpe.getText().toString());
                    monitoringDto.setStatus("4");
                    monitoringDto.setTimeInput(currentDateSample11);
                    monitoringDto.setDriver(DashboardPage.this.txtDriver.getText().toString());
                    monitoringDto.setLatitude(String.valueOf(currentLocation.getLatitude()));
                    monitoringDto.setLongitude(String.valueOf(currentLocation.getLongitude()));
                    DashboardPage.this.speRest.updateData(monitoringDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.9.1
                        @Override // retrofit2.Callback
                        public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getErrorCode().equalsIgnoreCase("200")) {
                                    NotificationService.getInstance(DashboardPage.this.getApplicationContext()).syncNotification("Process Mulai Bongkar Success");
                                    Intent intent = new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) TakePhotoActivity.class);
                                    intent.putExtra("tipe_photo", "mulaibongkar");
                                    intent.putExtra("speno", DashboardPage.this.txtSpe.getText());
                                    intent.putExtra("driver", DashboardPage.this.txtDriver.getText());
                                    intent.putExtra("time", currentDateSample11);
                                    DashboardPage.this.startActivity(intent);
                                    DashboardPage.this.progressDialog.dismiss();
                                    return;
                                }
                                NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Mulai Bongkar Failed");
                                DashboardPage.this.progressDialog.dismiss();
                                return;
                            }
                            NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Mulai Bongkar Failed");
                            DashboardPage.this.progressDialog.dismiss();
                        }

                        @Override // retrofit2.Callback
                        public void onFailure(Call<ResponseDto> call, Throwable th) {
                            NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Mulai Bongkar Failed");
                            DashboardPage.this.progressDialog.dismiss();
                        }
                    });
                }
            }
        }).setNegativeButton(getApplicationContext().getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.8
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void finishBongkar() {
        new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.konfirmasi)).setMessage(getApplicationContext().getString(R.string.anda_akan_mengakhiri_proses_bongkar)).setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.11
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DashboardPage dashboardPage = DashboardPage.this;
                dashboardPage.progressDialog = ProgressDialog.show(dashboardPage, "Update Data ...", dashboardPage.context.getString(R.string.please_wait), true);
                DashboardPage.this.progressDialog.setCancelable(false);
                String currentDateSample11 = DashboardPage.this.utilsTools.getCurrentDateSample11();
                LatLong currentLocation = DashboardPage.this.getCurrentLocation();
                if (DashboardPage.this.speRepo.updateStatus("5", currentDateSample11, DashboardPage.this.txtSpe.getText().toString())) {
                    MonitoringDto monitoringDto = new MonitoringDto();
                    monitoringDto.setSpeNo(DashboardPage.this.txtSpe.getText().toString());
                    monitoringDto.setStatus("5");
                    monitoringDto.setTimeInput(currentDateSample11);
                    monitoringDto.setDriver(DashboardPage.this.txtDriver.getText().toString());
                    monitoringDto.setLatitude(String.valueOf(currentLocation.getLatitude()));
                    monitoringDto.setLongitude(String.valueOf(currentLocation.getLongitude()));
                    DashboardPage.this.speRest.updateData(monitoringDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.11.1
                        @Override // retrofit2.Callback
                        public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getErrorCode().equalsIgnoreCase("200")) {
                                    NotificationService.getInstance(DashboardPage.this.getApplicationContext()).syncNotification("Process Finish Bongkar Success");
                                    DashboardPage.this.populateData();
                                    DashboardPage.this.progressDialog.dismiss();
                                    return;
                                } else {
                                    NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Finish Bongkar Failed");
                                    DashboardPage.this.progressDialog.dismiss();
                                    return;
                                }
                            }
                            NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Finish Bongkar Failed");
                            DashboardPage.this.progressDialog.dismiss();
                        }

                        @Override // retrofit2.Callback
                        public void onFailure(Call<ResponseDto> call, Throwable th) {
                            NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Finish Bongkar Failed");
                            DashboardPage.this.progressDialog.dismiss();
                        }
                    });
                }
            }
        }).setNegativeButton(getApplicationContext().getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.10
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void scanOutSubdist() {
        try {
            ProgressDialog show = ProgressDialog.show(this, "Sedang Proses Kirim Data ...", this.context.getString(R.string.please_wait), true);
            this.progressDialog = show;
            show.setCancelable(false);
            final List<MonitoringPhoto> dataPhoto = this.speRepo.getDataPhoto(String.valueOf(this.txtSpe.getText()));
            this.speRest.dataPhoto(dataPhoto.get(0)).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.12
                @Override // retrofit2.Callback
                public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                    if (response.isSuccessful()) {
                        if (response.body().getErrorCode().equalsIgnoreCase("200")) {
                            NotificationService.getInstance(DashboardPage.this.getApplicationContext()).syncNotification("Process Finish Success");
                            ArrayList arrayList = new ArrayList();
                            StringBuilder sb = new StringBuilder();
                            DatabaseHelper unused = DashboardPage.this.databaseHelper;
                            sb.append(DatabaseHelper.PHOTO_PATH_SDCARD);
                            sb.append(((MonitoringPhoto) dataPhoto.get(0)).getPhotoArrive());
                            File file = new File(sb.toString());
                            StringBuilder sb2 = new StringBuilder();
                            DatabaseHelper unused2 = DashboardPage.this.databaseHelper;
                            sb2.append(DatabaseHelper.PHOTO_PATH_SDCARD);
                            sb2.append(((MonitoringPhoto) dataPhoto.get(0)).getPhotoStartBongkar());
                            File file2 = new File(sb2.toString());
                            RequestBody create = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                            RequestBody create2 = RequestBody.create(MediaType.parse("multipart/form-data"), file2);
                            MultipartBody.Part createFormData = MultipartBody.Part.createFormData("file", ((MonitoringPhoto) dataPhoto.get(0)).getPhotoArrive(), create);
                            MultipartBody.Part createFormData2 = MultipartBody.Part.createFormData("file", ((MonitoringPhoto) dataPhoto.get(0)).getPhotoStartBongkar(), create2);
                            arrayList.add(createFormData);
                            arrayList.add(createFormData2);
                            Log.i("Upload Foto", "Image File Upload " + arrayList.size());
                            DashboardPage.this.speRest.uploadPhoto(String.valueOf(DashboardPage.this.txtSpe.getText()), arrayList).enqueue(new AnonymousClass1());
                            return;
                        }
                        NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Finish  Failed");
                        DashboardPage.this.progressDialog.dismiss();
                        return;
                    }
                    NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Finish  Failed");
                    DashboardPage.this.progressDialog.dismiss();
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                /* renamed from: com.myor.mydex.DashboardPage$12$1, reason: invalid class name */
                /* loaded from: classes2.dex */
                public class AnonymousClass1 implements Callback<ResponseBody> {
                    AnonymousClass1() {
                    }

                    @Override // retrofit2.Callback
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            DashboardPage.this.deleteTemp();
                            DashboardPage.this.progressDialog.dismiss();
                            Intent intent = new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) LoginPage.class);
                            NotificationService.getInstance(DashboardPage.this.getApplicationContext()).syncNotification("Finish Success");
                            DashboardPage.this.startActivity(intent);
                            return;
                        }
                        DashboardPage.this.runOnUiThread(new Runnable() { // from class: com.myor.mydex.DashboardPage$12$1$$ExternalSyntheticLambda0
                            @Override // java.lang.Runnable
                            public final void run() {
                                DashboardPage.AnonymousClass12.AnonymousClass1.this.m248lambda$onResponse$0$commyormydexDashboardPage$12$1();
                            }
                        });
                        DashboardPage.this.progressDialog.dismiss();
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* renamed from: lambda$onResponse$0$com-myor-mydex-DashboardPage$12$1, reason: not valid java name */
                    public /* synthetic */ void m248lambda$onResponse$0$commyormydexDashboardPage$12$1() {
                        Snackbar.make(DashboardPage.this.mLayout, DashboardPage.this.context.getString(R.string.upload_foto_gagal), 0).show();
                    }

                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* renamed from: lambda$onFailure$1$com-myor-mydex-DashboardPage$12$1, reason: not valid java name */
                    public /* synthetic */ void m247lambda$onFailure$1$commyormydexDashboardPage$12$1(Throwable th) {
                        Snackbar.make(DashboardPage.this.mLayout, th.getMessage(), 0).show();
                    }

                    @Override // retrofit2.Callback
                    public void onFailure(Call<ResponseBody> call, final Throwable th) {
                        DashboardPage.this.runOnUiThread(new Runnable() { // from class: com.myor.mydex.DashboardPage$12$1$$ExternalSyntheticLambda1
                            @Override // java.lang.Runnable
                            public final void run() {
                                DashboardPage.AnonymousClass12.AnonymousClass1.this.m247lambda$onFailure$1$commyormydexDashboardPage$12$1(th);
                            }
                        });
                        DashboardPage.this.progressDialog.dismiss();
                    }
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ResponseDto> call, Throwable th) {
                    NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Finish Failed");
                    DashboardPage.this.progressDialog.dismiss();
                }
            });
        } catch (Exception e) {
            e.getMessage();
            this.progressDialog.dismiss();
            NotificationService.getInstance(getApplicationContext()).failedNotification("Process Finish Failed");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scanQR() {
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.setPrompt("Scan a barcode or QR Code");
        intentIntegrator.setOrientationLocked(true);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.initiateScan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't wrap try/catch for region: R(12:1|(1:3)(2:34|(3:36|(2:38|(1:40)(2:41|(1:43)))|44)(2:45|(1:47)(2:48|(1:50)(2:51|(1:53)(2:54|(1:56)(2:57|(1:59)))))))|4|(1:6)(1:33)|7|(7:9|(1:11)(2:29|(1:31))|12|13|14|15|(1:24)(2:21|22))|32|12|13|14|15|(2:17|25)(1:26)) */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0626, code lost:
    
        r14.rangeWaktu = 15;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void populateData() {
        /*
            Method dump skipped, instructions count: 1642
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myor.mydex.DashboardPage.populateData():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void inTransit() {
        new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.konfirmasi)).setMessage(getApplicationContext().getString(R.string.anda_akan_transit)).setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new AnonymousClass16()).setNegativeButton(getApplicationContext().getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.15
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.myor.mydex.DashboardPage$16, reason: invalid class name */
    /* loaded from: classes2.dex */
    public class AnonymousClass16 implements DialogInterface.OnClickListener {
        AnonymousClass16() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            String currentDateSample11 = DashboardPage.this.utilsTools.getCurrentDateSample11();
            LatLong currentLocation = DashboardPage.this.getCurrentLocation();
            try {
                if (DashboardPage.this.speRepo.updateStatus(ExifInterface.GPS_MEASUREMENT_2D, currentDateSample11, DashboardPage.this.txtSpe.getText().toString())) {
                    MonitoringDto monitoringDto = new MonitoringDto();
                    monitoringDto.setSpeNo(DashboardPage.this.txtSpe.getText().toString());
                    monitoringDto.setStatus(ExifInterface.GPS_MEASUREMENT_2D);
                    monitoringDto.setTimeInput(currentDateSample11);
                    monitoringDto.setDriver(DashboardPage.this.txtDriver.getText().toString());
                    monitoringDto.setLatitude(String.valueOf(currentLocation.getLatitude()));
                    monitoringDto.setLongitude(String.valueOf(currentLocation.getLongitude()));
                    DashboardPage.this.speRest.updateData(monitoringDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.16.1
                        @Override // retrofit2.Callback
                        public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getErrorCode().equalsIgnoreCase("200")) {
                                    DashboardPage.this.populateData();
                                    new AlertDialog.Builder(DashboardPage.this).setTitle(DashboardPage.this.getApplicationContext().getString(R.string.konfirmasi)).setMessage(DashboardPage.this.getApplicationContext().getString(R.string.success_in_transit)).setCancelable(false).setPositiveButton(DashboardPage.this.getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.16.1.1
                                        @Override // android.content.DialogInterface.OnClickListener
                                        public void onClick(DialogInterface dialogInterface2, int i2) {
                                            DashboardPage.this.deleteTemp();
                                            new TriggerGetDataWorker(DashboardPage.this.getApplicationContext()).closeTriggerRunning();
                                            DashboardPage.this.startActivity(new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) LoginPage.class));
                                        }
                                    }).show();
                                    return;
                                } else {
                                    Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                                    return;
                                }
                            }
                            Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                        }

                        @Override // retrofit2.Callback
                        public void onFailure(Call<ResponseDto> call, Throwable th) {
                            Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                        }
                    });
                }
            } catch (Exception e) {
                e.getMessage();
                e.printStackTrace();
            }
        }
    }

    public void syncData(String str) {
        InputSPEDto inputSPEDto = new InputSPEDto();
        if (str.equalsIgnoreCase("")) {
            inputSPEDto.setSpeNo(this.speRepo.getDataMonitoring().get(0).getSpeNo());
        } else {
            inputSPEDto.setSpeNo(this.txtSpe.getText().toString());
        }
        this.speRest.syncData(inputSPEDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.17
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                if (response.isSuccessful()) {
                    String errorCode = response.body().getErrorCode();
                    String errorMessage = response.body().getErrorMessage();
                    if (errorCode.equalsIgnoreCase("200")) {
                        if (response.body().getMonitoring().getSpeNo().length() > 0) {
                            DashboardPage.this.deleteTemp();
                            DashboardPage.this.saveTemp(response.body().getMonitoring(), response.body().getMonitoringDetail());
                            DashboardPage.this.syncRepo.insertBranch(response.body().getmWarehouse());
                            DashboardPage.this.populateData();
                            NotificationService.getInstance(DashboardPage.this.getApplicationContext()).syncNotification("Process Sync Data Success");
                            return;
                        }
                        DashboardPage.this.deleteTemp();
                        DashboardPage.this.handler.removeCallbacks(DashboardPage.this.runnable);
                        DashboardPage.this.startActivity(new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) LoginPage.class));
                        return;
                    }
                    if (errorCode.equalsIgnoreCase("999")) {
                        DashboardPage.this.deleteTemp();
                        DashboardPage.this.handler.removeCallbacks(DashboardPage.this.runnable);
                        DashboardPage.this.startActivity(new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) LoginPage.class));
                        return;
                    }
                    String status = response.body().getMonitoring().getStatus();
                    if (status.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D) || status.equalsIgnoreCase("6")) {
                        new AlertDialog.Builder(DashboardPage.this).setTitle(DashboardPage.this.getApplicationContext().getString(R.string.informasi)).setMessage(errorMessage).setCancelable(false).setPositiveButton(DashboardPage.this.getApplicationContext().getString(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.17.1
                            @Override // android.content.DialogInterface.OnClickListener
                            public void onClick(DialogInterface dialogInterface, int i) {
                                DashboardPage.this.deleteTemp();
                                new TriggerGetDataWorker(DashboardPage.this.getApplicationContext()).closeTriggerRunning();
                                DashboardPage.this.startActivity(new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) LoginPage.class));
                            }
                        }).show();
                    }
                    NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Sync Data Failed");
                    return;
                }
                NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Sync Data Failed");
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseDto> call, Throwable th) {
                NotificationService.getInstance(DashboardPage.this.getApplicationContext()).failedNotification("Process Sync Data Failed");
            }
        });
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        this.handler.removeCallbacks(this.runnable);
    }

    public void deleteTemp() {
        this.speRepo.deleteMonitoring("t_monitoring_h");
        this.speRepo.deleteMonitoring("t_monitoring_d");
    }

    public void saveTemp(MonitoringDto monitoringDto, List<MonitoringD> list) {
        this.speRepo.insertMonitoringH(monitoringDto);
        this.speRepo.insertMonitoringD(list);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialogDo() {
        new ArrayList();
        List<MonitoringD> dataMonitoringDetail = this.speRepo.getDataMonitoringDetail(this.txtSpe.getText().toString());
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.view_list_do, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setTitle("View DO");
        this.listViewDo = (RecyclerView) inflate.findViewById(R.id.listViewDo);
        this.listViewDo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.listViewDo.setHasFixedSize(true);
        ViewDoAdapter viewDoAdapter = new ViewDoAdapter(dataMonitoringDetail, getApplicationContext(), this);
        this.viewDoAdapter = viewDoAdapter;
        this.listViewDo.setAdapter(viewDoAdapter);
        ((Button) inflate.findViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.myor.mydex.DashboardPage.19
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DashboardPage.this.alertDialog == null || !DashboardPage.this.alertDialog.isShowing()) {
                    return;
                }
                DashboardPage.this.alertDialog.dismiss();
            }
        });
        AlertDialog create = builder.create();
        this.alertDialog = create;
        create.show();
    }

    private void dismissSpinnerDialog() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.alertDialog.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        IntentResult parseActivityResult = IntentIntegrator.parseActivityResult(i, i2, intent);
        if (parseActivityResult.getContents() == null) {
            Toast.makeText(getApplicationContext(), "Scan Cancelled", 1).show();
            reasonGagalScan();
            return;
        }
        String contents = parseActivityResult.getContents();
        parseActivityResult.getFormatName();
        List<BranchDto> arrayList = new ArrayList<>();
        if (this.status.equalsIgnoreCase("0")) {
            arrayList = this.speRepo.getBranchLocation(contents);
        } else if (this.status.equalsIgnoreCase("1") || this.status.equalsIgnoreCase("5")) {
            arrayList = this.speRepo.getBranchLocationBySubdist(contents, this.tujuanId);
        }
        if (arrayList.size() > 0) {
            checkLocation("scan", arrayList.get(0).getBranchId());
        } else {
            Toast.makeText(getBaseContext(), "Barcode Doesn't Match!", 0).show();
        }
    }

    private void reasonGagalScan() {
        this.reasonNotScan = this.speRepo.getReason("1");
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.context, R.layout.select_dialog_singlechoice);
        for (int i = 0; i < this.reasonNotScan.size(); i++) {
            arrayAdapter.add(this.reasonNotScan.get(i).getReasonName());
        }
        new AlertDialog.Builder(this).setTitle("Alasan Tidak Scan").setCancelable(false).setAdapter(arrayAdapter, new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.20
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                String str;
                String reasonId = ((Reason) DashboardPage.this.reasonNotScan.get(i2)).getReasonId();
                DashboardPage.this.speRepo.updateReasonNotScan(reasonId, DashboardPage.this.txtSpe.getText().toString(), DashboardPage.this.status);
                if (DashboardPage.this.status.equalsIgnoreCase("0")) {
                    str = "out_rdc";
                } else if (DashboardPage.this.status.equalsIgnoreCase("1")) {
                    str = "in_subdist";
                } else {
                    str = DashboardPage.this.status.equalsIgnoreCase("5") ? "out_subdist" : "";
                }
                DashboardPage.this.speRest.updateReason(DashboardPage.this.txtSpe.getText().toString(), reasonId, str).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.20.1
                    @Override // retrofit2.Callback
                    public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getErrorCode().equalsIgnoreCase("200")) {
                                DashboardPage.this.checkLocation("notscan", "");
                                return;
                            } else {
                                Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                                return;
                            }
                        }
                        Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                    }

                    @Override // retrofit2.Callback
                    public void onFailure(Call<ResponseDto> call, Throwable th) {
                        Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                    }
                });
            }
        }).show();
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.konfirmasi)).setMessage(getApplicationContext().getString(R.string.keluar_aplikasi)).setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.22
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                DashboardPage.this.startActivity(new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) LoginPage.class));
            }
        }).setNegativeButton(getApplicationContext().getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.21
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAlasanOutRadius() {
        this.reasonOutRadius = this.speRepo.getReason(ExifInterface.GPS_MEASUREMENT_2D);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this.context, R.layout.select_dialog_singlechoice);
        for (int i = 0; i < this.reasonOutRadius.size(); i++) {
            arrayAdapter.add(this.reasonOutRadius.get(i).getReasonName());
        }
        new AlertDialog.Builder(this).setTitle("Alasan Out of Radius").setCancelable(false).setAdapter(arrayAdapter, new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage.23
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i2) {
                String str;
                String reasonId = ((Reason) DashboardPage.this.reasonOutRadius.get(i2)).getReasonId();
                DashboardPage.this.speRepo.updateReasonOutRadius(DashboardPage.this.status, reasonId, DashboardPage.this.txtSpe.getText().toString());
                if (DashboardPage.this.status.equalsIgnoreCase("0")) {
                    str = "outradius_rdc";
                } else if (DashboardPage.this.status.equalsIgnoreCase("1")) {
                    str = "outradius_subdist";
                } else {
                    str = DashboardPage.this.status.equalsIgnoreCase("5") ? "outradius_outsubdist" : "";
                }
                DashboardPage.this.speRest.updateReason(DashboardPage.this.txtSpe.getText().toString(), reasonId, str).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.23.1
                    @Override // retrofit2.Callback
                    public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                        if (response.isSuccessful()) {
                            if (response.body().getErrorCode().equalsIgnoreCase("200")) {
                                if (DashboardPage.this.status.equalsIgnoreCase("1")) {
                                    List<MonitoringH> dataMonitoring = DashboardPage.this.speRepo.getDataMonitoring();
                                    Intent intent = new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) TakePhotoActivity.class);
                                    intent.putExtra("tipe_photo", "tiba");
                                    intent.putExtra("speno", DashboardPage.this.txtSpe.getText());
                                    intent.putExtra("driver", DashboardPage.this.txtDriver.getText());
                                    intent.putExtra("time", dataMonitoring.get(0).getTimeArrive());
                                    DashboardPage.this.startActivity(intent);
                                    return;
                                }
                                DashboardPage.this.populateData();
                                return;
                            }
                            Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                            return;
                        }
                        Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                    }

                    @Override // retrofit2.Callback
                    public void onFailure(Call<ResponseDto> call, Throwable th) {
                        Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                    }
                });
            }
        }).show();
    }

    public LatLong getCurrentLocation() {
        final LatLong latLong = new LatLong();
        LocationManager locationManager = (LocationManager) getSystemService("location");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            new LoginPage().onGPS();
            return latLong;
        }
        new LocationListener() { // from class: com.myor.mydex.DashboardPage.24
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
            }
        };
        LocationRequest.create().setPriority(100);
        LocationServices.getFusedLocationProviderClient(getApplicationContext()).getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() { // from class: com.myor.mydex.DashboardPage.25
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Location location) {
                if (location != null) {
                    DashboardPage.this.latitude = location.getLatitude();
                    DashboardPage.this.longitude = location.getLongitude();
                    latLong.setLatitude(DashboardPage.this.latitude);
                    latLong.setLongitude(DashboardPage.this.longitude);
                    return;
                }
                DashboardPage.this.latitude = Utils.DOUBLE_EPSILON;
                DashboardPage.this.longitude = Utils.DOUBLE_EPSILON;
                latLong.setLatitude(DashboardPage.this.latitude);
                latLong.setLongitude(DashboardPage.this.longitude);
            }
        });
        return latLong;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkLocation(final String str, final String str2) {
        LocationManager locationManager = (LocationManager) getSystemService("location");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        locationManager.getBestProvider(criteria, false);
        if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") != 0 && ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") != 0) {
            new LoginPage().onGPS();
            return;
        }
        new LocationListener() { // from class: com.myor.mydex.DashboardPage.26
            @Override // android.location.LocationListener
            public void onLocationChanged(Location location) {
            }
        };
        LocationRequest.create().setPriority(100);
        LocationServices.getFusedLocationProviderClient(getApplicationContext()).getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() { // from class: com.myor.mydex.DashboardPage.27
            @Override // com.google.android.gms.tasks.OnSuccessListener
            public void onSuccess(Location location) {
                if (location != null) {
                    DashboardPage.this.latitude = location.getLatitude();
                    DashboardPage.this.longitude = location.getLongitude();
                    List<BranchDto> arrayList = new ArrayList<>();
                    if (DashboardPage.this.status.equalsIgnoreCase("0")) {
                        arrayList = DashboardPage.this.speRepo.getMasterBranch("1");
                    } else if (DashboardPage.this.status.equalsIgnoreCase("1") || DashboardPage.this.status.equalsIgnoreCase("5")) {
                        arrayList = DashboardPage.this.speRepo.getMasterBranchById(DashboardPage.this.tujuanId);
                    }
                    if (arrayList.size() > 0) {
                        if (DashboardPage.this.status.equalsIgnoreCase("0")) {
                            DashboardPage.this.updateScanOutRDC(arrayList, str, str2);
                        } else if (DashboardPage.this.status.equalsIgnoreCase("1")) {
                            DashboardPage.this.updateScanSubdist(arrayList);
                        } else if (DashboardPage.this.status.equalsIgnoreCase("5")) {
                            DashboardPage.this.updateScanOutSubdist(arrayList);
                        }
                    }
                }
            }
        });
    }

    public void updateScanSubdist(final List<BranchDto> list) {
        list.get(0).setRadius(new DistanceCalculator().calculateDistance(this.latitude, this.longitude, Double.parseDouble(list.get(0).getLatitude().replaceAll(",", ".")), Double.parseDouble(list.get(0).getLongitude().replaceAll(",", "."))));
        final String currentDateSample11 = this.utilsTools.getCurrentDateSample11();
        if (this.speRepo.updateStatus(ExifInterface.GPS_MEASUREMENT_3D, currentDateSample11, this.txtSpe.getText().toString())) {
            MonitoringDto monitoringDto = new MonitoringDto();
            monitoringDto.setSpeNo(this.txtSpe.getText().toString());
            monitoringDto.setStatus(ExifInterface.GPS_MEASUREMENT_3D);
            monitoringDto.setTimeInput(currentDateSample11);
            monitoringDto.setOriginId("");
            monitoringDto.setOriginName("");
            monitoringDto.setDriver(this.txtDriver.getText().toString());
            monitoringDto.setLatitude(String.valueOf(this.latitude));
            monitoringDto.setLongitude(String.valueOf(this.longitude));
            this.speRest.updateData(monitoringDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.28
                @Override // retrofit2.Callback
                public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                    if (response.isSuccessful()) {
                        if (!response.body().getErrorCode().equalsIgnoreCase("200")) {
                            Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                            return;
                        }
                        if (((BranchDto) list.get(0)).getRadius() > DashboardPage.this.radiusLimit) {
                            DashboardPage.this.showAlasanOutRadius();
                            return;
                        }
                        Intent intent = new Intent(DashboardPage.this.getApplicationContext(), (Class<?>) TakePhotoActivity.class);
                        intent.putExtra("tipe_photo", "tiba");
                        intent.putExtra("speno", DashboardPage.this.txtSpe.getText());
                        intent.putExtra("driver", DashboardPage.this.txtDriver.getText());
                        intent.putExtra("time", currentDateSample11);
                        DashboardPage.this.startActivity(intent);
                        return;
                    }
                    Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ResponseDto> call, Throwable th) {
                    Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                }
            });
        }
    }

    public void updateScanOutSubdist(final List<BranchDto> list) {
        list.get(0).setRadius(new DistanceCalculator().calculateDistance(this.latitude, this.longitude, Double.parseDouble(list.get(0).getLatitude()), Double.parseDouble(list.get(0).getLongitude())));
        String currentDateSample11 = this.utilsTools.getCurrentDateSample11();
        if (this.speRepo.updateStatus("6", currentDateSample11, this.txtSpe.getText().toString())) {
            MonitoringDto monitoringDto = new MonitoringDto();
            monitoringDto.setSpeNo(this.txtSpe.getText().toString());
            monitoringDto.setStatus("6");
            monitoringDto.setTimeInput(currentDateSample11);
            monitoringDto.setOriginId("");
            monitoringDto.setOriginName("");
            monitoringDto.setDriver(this.txtDriver.getText().toString());
            monitoringDto.setLatitude(String.valueOf(this.latitude));
            monitoringDto.setLongitude(String.valueOf(this.longitude));
            this.speRest.updateData(monitoringDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.29
                @Override // retrofit2.Callback
                public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                    if (response.isSuccessful()) {
                        if (!response.body().getErrorCode().equalsIgnoreCase("200")) {
                            Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                            return;
                        } else if (((BranchDto) list.get(0)).getRadius() > DashboardPage.this.radiusLimit) {
                            DashboardPage.this.showAlasanOutRadius();
                            return;
                        } else {
                            DashboardPage.this.populateData();
                            return;
                        }
                    }
                    Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                }

                @Override // retrofit2.Callback
                public void onFailure(Call<ResponseDto> call, Throwable th) {
                    Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                }
            });
        }
    }

    public void updateScanOutRDC(List<BranchDto> list, String str, String str2) {
        String branchName;
        DistanceCalculator distanceCalculator = new DistanceCalculator();
        if (str.equalsIgnoreCase("SCAN")) {
            list = this.speRepo.getMasterBranchById(str2);
            if (list.size() > 0) {
                list.get(0).setRadius(distanceCalculator.calculateDistance(this.latitude, this.longitude, Double.parseDouble(list.get(0).getLatitude()), Double.parseDouble(list.get(0).getLongitude())));
                branchName = list.get(0).getBranchName();
            } else {
                branchName = "";
            }
        } else {
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setRadius(distanceCalculator.calculateDistance(this.latitude, this.longitude, Double.parseDouble(list.get(i).getLatitude()), Double.parseDouble(list.get(i).getLongitude())));
            }
            Collections.sort(list, new BranchDto.SortByRadius());
            str2 = list.get(0).getBranchId();
            branchName = list.get(0).getBranchName();
        }
        String currentDateSample11 = this.utilsTools.getCurrentDateSample11();
        this.speRepo.updateWhIdScanOut(str2, branchName, currentDateSample11, this.txtSpe.getText().toString());
        MonitoringDto monitoringDto = new MonitoringDto();
        monitoringDto.setSpeNo(this.txtSpe.getText().toString());
        monitoringDto.setStatus("1");
        monitoringDto.setTimeInput(currentDateSample11);
        monitoringDto.setOriginId(str2);
        monitoringDto.setOriginName(branchName);
        monitoringDto.setDriver(this.txtDriver.getText().toString());
        monitoringDto.setLatitude(String.valueOf(this.latitude));
        monitoringDto.setLongitude(String.valueOf(this.longitude));
        updateDataScanOutWarehouse(monitoringDto, list);
    }

    public void updateDataScanOutWarehouse(MonitoringDto monitoringDto, final List<BranchDto> list) {
        this.speRest.updateData(monitoringDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage.30
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                if (response.isSuccessful()) {
                    if (!response.body().getErrorCode().equalsIgnoreCase("200")) {
                        Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
                        return;
                    } else if (((BranchDto) list.get(0)).getRadius() > DashboardPage.this.radiusLimit) {
                        DashboardPage.this.showAlasanOutRadius();
                        return;
                    } else {
                        DashboardPage.this.populateData();
                        return;
                    }
                }
                Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseDto> call, Throwable th) {
                Toast.makeText(DashboardPage.this.context, DashboardPage.this.getApplicationContext().getString(R.string.gagal_simpan_data), 1).show();
            }
        });
    }

    @Override // com.myor.mydex.adapter.ViewDoAdapter.OnClickDetail
    public void onClickDetail(String str) {
        List<MonitoringD> dataDetail = this.speRepo.getDataDetail(str);
        View inflate = LayoutInflater.from(this.context).inflate(R.layout.view_list_do, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setTitle("View DO - " + str);
        this.listViewDetailDo = (RecyclerView) inflate.findViewById(R.id.listViewDo);
        this.listViewDetailDo.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        this.listViewDetailDo.setHasFixedSize(true);
        ViewDoDetailAdapter viewDoDetailAdapter = new ViewDoDetailAdapter(dataDetail, getApplicationContext());
        this.viewDoDetailAdapter = viewDoDetailAdapter;
        this.listViewDetailDo.setAdapter(viewDoDetailAdapter);
        ((Button) inflate.findViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.myor.mydex.DashboardPage.31
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (DashboardPage.this.alertDialogDetail == null || !DashboardPage.this.alertDialogDetail.isShowing()) {
                    return;
                }
                DashboardPage.this.alertDialogDetail.dismiss();
            }
        });
        AlertDialog create = builder.create();
        this.alertDialogDetail = create;
        create.show();
    }

    private ValidateDto validasiWaktu(String str) throws ParseException {
        String currentDateSample11 = this.utilsTools.getCurrentDateSample11();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long minutes = TimeUnit.MILLISECONDS.toMinutes(simpleDateFormat.parse(currentDateSample11).getTime() - simpleDateFormat.parse(str).getTime());
        return new ValidateDto(minutes <= this.rangeWaktu, String.valueOf(minutes));
    }
}
