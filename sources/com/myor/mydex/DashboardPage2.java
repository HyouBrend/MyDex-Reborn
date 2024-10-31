package com.myor.mydex;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.zxing.integration.android.IntentIntegrator;
import com.myor.mydex.MainActivity;
import com.myor.mydex.adapter.ViewDoAdapter;
import com.myor.mydex.apiclient.ApiClient;
import com.myor.mydex.entity.InputSPEDto;
import com.myor.mydex.entity.MonitoringD;
import com.myor.mydex.entity.MonitoringDto;
import com.myor.mydex.entity.MonitoringH;
import com.myor.mydex.entity.ResponseDto;
import com.myor.mydex.repository.SpeRepo;
import com.myor.mydex.rest.SpeRest;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* loaded from: classes2.dex */
public class DashboardPage2 extends Fragment implements MainActivity.Testing {
    public static final String TAG = "DashboardPage";
    private AlertDialog alertDialog;
    private ApiClient apiClient;
    private Button btnAction;
    private Button btnRefresh;
    private Button btnViewDo;
    private String driverPhone;
    private Handler handler;
    private RecyclerView listViewDo;
    private ProgressDialog progressDialog;
    private IntentIntegrator qrScan;
    private Runnable runnable;
    private SpeRepo speRepo;
    private SpeRest speRest;
    private String status;
    private SwipeButton swipeButton;
    private TextView txtDriver;
    private TextView txtJalur;
    private TextView txtMobil;
    private TextView txtSpe;
    private TextView txtStatus;
    private TextView txtTanggal;
    private TextView txtTotalDo;
    private TextView txtTujuan;
    private ViewDoAdapter viewDoAdapter;
    private String barcodeOrigin = "0";
    private View.OnClickListener refreshListener = new View.OnClickListener() { // from class: com.myor.mydex.DashboardPage2.3
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            DashboardPage2 dashboardPage2 = DashboardPage2.this;
            dashboardPage2.syncData(dashboardPage2.txtSpe.getText().toString());
        }
    };
    private View.OnClickListener viewDoListener = new View.OnClickListener() { // from class: com.myor.mydex.DashboardPage2.5
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DashboardPage2.this.txtTotalDo.getText().toString().equalsIgnoreCase("0")) {
                new AlertDialog.Builder(DashboardPage2.this.getActivity()).setTitle(DashboardPage2.this.getActivity().getString(R.string.informasi)).setMessage(DashboardPage2.this.getActivity().getString(R.string.detail_do_tidak_ada)).setCancelable(false).setPositiveButton(DashboardPage2.this.getActivity().getText(R.string.ok), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.DashboardPage2.5.1
                    @Override // android.content.DialogInterface.OnClickListener
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
            } else {
                DashboardPage2.this.showDialogDo();
            }
        }
    };

    public static DashboardPage2 newInstance(String str, String str2) {
        DashboardPage2 dashboardPage2 = new DashboardPage2();
        dashboardPage2.setArguments(new Bundle());
        return dashboardPage2;
    }

    @Override // androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Log.d("DashboardPage", "onCreate: ");
        getArguments();
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        Log.d("DashboardPage", "onCreateView: ");
        viewGroup.removeAllViews();
        layoutInflater.cloneInContext(new ContextThemeWrapper(getActivity(), R.style.hideActionBar));
        return layoutInflater.inflate(R.layout.fragment_dashboard_page, viewGroup, false);
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        Log.d("DashboardPage", "onViewCreated: ");
        initView(view);
        SpeRepo speRepo = new SpeRepo(getActivity());
        this.speRepo = speRepo;
        final long parseLong = Long.parseLong(speRepo.getXkey("sync_dashboard").get(0).getMemoint());
        this.handler = new Handler();
        this.runnable = new Runnable() { // from class: com.myor.mydex.DashboardPage2.1
            @Override // java.lang.Runnable
            public void run() {
                DashboardPage2.this.syncData("");
                DashboardPage2.this.handler.postDelayed(DashboardPage2.this.runnable, parseLong);
            }
        };
        populateData();
        this.handler.postDelayed(this.runnable, parseLong);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void populateData() {
        String str;
        List<MonitoringH> dataMonitoring = this.speRepo.getDataMonitoring();
        this.txtSpe.setText(dataMonitoring.get(0).getSpeNo());
        this.txtDriver.setText(dataMonitoring.get(0).getDriver());
        this.txtMobil.setText(dataMonitoring.get(0).getPoliceNo());
        String status = dataMonitoring.get(0).getStatus();
        this.status = status;
        if (status.equalsIgnoreCase("0")) {
            this.txtStatus.setText(getActivity().getString(R.string.menunggu_jalan));
            this.txtStatus.setTextColor(getResources().getColor(R.color.red));
            this.swipeButton.setText(getActivity().getString(R.string.swipe_to_scan_out));
        } else if (this.status.equalsIgnoreCase("1")) {
            this.txtStatus.setText(getActivity().getString(R.string.dalam_perjalanan));
            this.txtStatus.setTextColor(getResources().getColor(R.color.purple));
            this.swipeButton.setText(getActivity().getString(R.string.swipe_to_scan_in));
        } else if (this.status.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
            this.txtStatus.setText(getActivity().getString(R.string.in_transit));
            this.txtStatus.setTextColor(getResources().getColor(R.color.blue));
            this.swipeButton.setText(getActivity().getString(R.string.swipe_to_in_transit));
        } else if (this.status.equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_3D)) {
            this.txtStatus.setText(getActivity().getString(R.string.menunggu_bongkar));
            this.txtStatus.setTextColor(getResources().getColor(R.color.blue));
            this.swipeButton.setText(getActivity().getString(R.string.swipe_to_start_bongkar));
        } else if (this.status.equalsIgnoreCase("4")) {
            this.txtStatus.setText(getActivity().getString(R.string.proses_bongkar));
            this.txtStatus.setTextColor(getResources().getColor(R.color.green));
            this.swipeButton.setText(getActivity().getString(R.string.swipe_to_finish_bongkar));
        } else if (this.status.equalsIgnoreCase("5")) {
            this.txtStatus.setText(getActivity().getString(R.string.selesai_bongkar));
            this.txtStatus.setTextColor(getResources().getColor(R.color.light_blue));
            this.swipeButton.setText(getActivity().getString(R.string.swipe_to_finish));
        } else if (this.status.equalsIgnoreCase("6")) {
            this.txtStatus.setText(getActivity().getString(R.string.finish));
            this.txtStatus.setTextColor(getResources().getColor(R.color.green_army));
        }
        this.txtTanggal.setText(dataMonitoring.get(0).getTimeInput());
        this.txtTujuan.setText(dataMonitoring.get(0).getDestinationName());
        this.txtTotalDo.setText(dataMonitoring.size() > 0 ? String.valueOf(this.speRepo.countDataMonitoring(dataMonitoring.get(0).getSpeNo())) : "0");
        if (dataMonitoring.get(0).getFlagRoute() != null) {
            if (dataMonitoring.get(0).getFlagRoute().equalsIgnoreCase("1")) {
                str = "Darat";
            } else if (dataMonitoring.get(0).getFlagRoute().equalsIgnoreCase(ExifInterface.GPS_MEASUREMENT_2D)) {
                str = "Laut";
            }
            this.txtJalur.setText(str);
        }
        str = "-";
        this.txtJalur.setText(str);
    }

    private void initView(View view) {
        IntentIntegrator intentIntegrator = new IntentIntegrator(getActivity());
        this.qrScan = intentIntegrator;
        intentIntegrator.setBeepEnabled(false);
        ApiClient apiClient = new ApiClient(getActivity());
        this.apiClient = apiClient;
        this.speRest = (SpeRest) apiClient.getClientToWsMobile(getActivity()).create(SpeRest.class);
        this.txtMobil = (TextView) view.findViewById(R.id.txtMobil);
        this.txtDriver = (TextView) view.findViewById(R.id.txtDriver);
        this.txtTanggal = (TextView) view.findViewById(R.id.txtTanggal);
        this.txtJalur = (TextView) view.findViewById(R.id.txtJalur);
        this.txtTujuan = (TextView) view.findViewById(R.id.txtTujuan);
        this.txtSpe = (TextView) view.findViewById(R.id.txtSpe);
        this.txtTotalDo = (TextView) view.findViewById(R.id.txtTotalDo);
        this.txtStatus = (TextView) view.findViewById(R.id.txtStatus);
        Button button = (Button) view.findViewById(R.id.btnRefresh);
        this.btnRefresh = button;
        button.setOnClickListener(this.refreshListener);
        Button button2 = (Button) view.findViewById(R.id.btnViewDo);
        this.btnViewDo = button2;
        button2.setOnClickListener(this.viewDoListener);
        SwipeButton swipeButton = (SwipeButton) view.findViewById(R.id.swipeButton);
        this.swipeButton = swipeButton;
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() { // from class: com.myor.mydex.DashboardPage2.2
            @Override // com.ebanx.swipebtn.OnStateChangeListener
            public void onStateChange(boolean z) {
                if (z) {
                    DashboardPage2.this.scanQR();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void scanQR() {
        this.qrScan.initiateScan();
        this.swipeButton.toggleState();
    }

    public void syncData(String str) {
        InputSPEDto inputSPEDto = new InputSPEDto();
        if (str.equalsIgnoreCase("")) {
            inputSPEDto.setSpeNo(this.speRepo.getDataMonitoring().get(0).getSpeNo());
        } else {
            inputSPEDto.setSpeNo(this.txtSpe.getText().toString());
        }
        this.speRest.syncData(inputSPEDto).enqueue(new Callback<ResponseDto>() { // from class: com.myor.mydex.DashboardPage2.4
            @Override // retrofit2.Callback
            public void onResponse(Call<ResponseDto> call, Response<ResponseDto> response) {
                if (response.isSuccessful()) {
                    String errorCode = response.body().getErrorCode();
                    response.body().getErrorMessage();
                    if (errorCode.equalsIgnoreCase("200")) {
                        DashboardPage2.this.deleteTemp();
                        DashboardPage2.this.saveTemp(response.body().getMonitoring(), response.body().getMonitoringDetail());
                        DashboardPage2.this.populateData();
                        Toast.makeText(DashboardPage2.this.getActivity(), DashboardPage2.this.getActivity().getString(R.string.success_sync), 0).show();
                        return;
                    }
                    Toast.makeText(DashboardPage2.this.getActivity(), DashboardPage2.this.getActivity().getString(R.string.failed_sync), 0).show();
                    return;
                }
                Toast.makeText(DashboardPage2.this.getActivity(), DashboardPage2.this.getActivity().getString(R.string.failed_sync), 0).show();
            }

            @Override // retrofit2.Callback
            public void onFailure(Call<ResponseDto> call, Throwable th) {
                Toast.makeText(DashboardPage2.this.getActivity(), DashboardPage2.this.getActivity().getString(R.string.failed_sync), 0).show();
            }
        });
    }

    @Override // androidx.fragment.app.Fragment
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
        this.speRepo.getDataMonitoringDetail(this.txtSpe.getText().toString());
        View inflate = LayoutInflater.from(getActivity()).inflate(R.layout.view_list_do, (ViewGroup) null);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(inflate);
        builder.setCancelable(false);
        builder.setTitle("View DO");
        RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.listViewDo);
        this.listViewDo = recyclerView;
        recyclerView.setAdapter(this.viewDoAdapter);
        this.listViewDo.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.listViewDo.setHasFixedSize(true);
        ((Button) inflate.findViewById(R.id.btnOk)).setOnClickListener(new View.OnClickListener() { // from class: com.myor.mydex.DashboardPage2.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DashboardPage2.this.dismissSpinnerDialog();
            }
        });
        AlertDialog create = builder.create();
        this.alertDialog = create;
        create.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissSpinnerDialog() {
        AlertDialog alertDialog = this.alertDialog;
        if (alertDialog == null || !alertDialog.isShowing()) {
            return;
        }
        this.alertDialog.dismiss();
    }

    @Override // com.myor.mydex.MainActivity.Testing
    public void test() {
        Toast.makeText(getActivity(), "Testing", 1).show();
    }
}
