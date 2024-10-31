package com.myor.mydex;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.lifecycle.CoroutineLiveDataKt;
import androidx.swiperefreshlayout.widget.CircularProgressDrawable;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.github.mikephil.charting.utils.Utils;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.zxing.integration.android.IntentIntegrator;
import com.makeramen.roundedimageview.RoundedImageView;
import com.myor.mydex.database.DatabaseHelper;
import com.myor.mydex.repository.SpeRepo;
import com.myor.mydex.util.CheckConnectionService;
import com.myor.mydex.util.PhotoActivity;
import com.myor.mydex.util.UtilsTools;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

/* loaded from: classes2.dex */
public class TakePhotoActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private Button btnTakePhoto;
    private CheckConnectionService checkConnectionService;
    private Context context;
    private String imageFilePath;
    private RoundedImageView imgPhoto;
    private FusedLocationProviderClient mFusedLocation;
    private View mLayout;
    private File mPhotoFile;
    private PhotoActivity photoUtils;
    private SpeRepo speRepo;
    private SwipeButton swipeButton;
    private TextView txtAddress;
    private TextView txtLatitude;
    private TextView txtLongitude;
    private double latitude = Utils.DOUBLE_EPSILON;
    private double longitude = Utils.DOUBLE_EPSILON;
    private UtilsTools utilsTools = new UtilsTools();
    private String imageFileName = "";
    private String tipePhoto = "";
    private String speno = "";
    private String driver = "";
    private String time = "";
    private View.OnClickListener takePhotoListener = new View.OnClickListener() { // from class: com.myor.mydex.TakePhotoActivity.2
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            TakePhotoActivity.this.createIntentPicture();
        }
    };

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        AppCompatDelegate.setDefaultNightMode(1);
        setContentView(R.layout.activity_take_photo);
        this.tipePhoto = getIntent().getStringExtra("tipe_photo");
        this.speno = getIntent().getStringExtra("speno");
        this.driver = getIntent().getStringExtra("driver");
        this.time = getIntent().getStringExtra("time");
        this.context = getApplicationContext();
        initView();
    }

    private void initView() {
        this.speRepo = new SpeRepo(this.context);
        this.checkConnectionService = new CheckConnectionService(this.context);
        this.imgPhoto = (RoundedImageView) findViewById(R.id.imgPhoto);
        this.txtLatitude = (TextView) findViewById(R.id.txtLatitude);
        this.txtLongitude = (TextView) findViewById(R.id.txtLongitude);
        this.txtAddress = (TextView) findViewById(R.id.txtAddress);
        Button button = (Button) findViewById(R.id.btnTakePhoto);
        this.btnTakePhoto = button;
        button.setOnClickListener(this.takePhotoListener);
        this.mLayout = findViewById(android.R.id.content);
        this.photoUtils = new PhotoActivity(this.context);
        SwipeButton swipeButton = (SwipeButton) findViewById(R.id.swipeButtonSave);
        this.swipeButton = swipeButton;
        swipeButton.setOnStateChangeListener(new OnStateChangeListener() { // from class: com.myor.mydex.TakePhotoActivity.1
            @Override // com.ebanx.swipebtn.OnStateChangeListener
            public void onStateChange(boolean z) {
                if (z) {
                    TakePhotoActivity takePhotoActivity = TakePhotoActivity.this;
                    takePhotoActivity.getApplicationContext();
                    LocationManager locationManager = (LocationManager) takePhotoActivity.getSystemService("location");
                    if (ActivityCompat.checkSelfPermission(TakePhotoActivity.this.getApplicationContext(), "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(TakePhotoActivity.this.getApplicationContext(), "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                        Location lastKnownLocation = locationManager.getLastKnownLocation("gps");
                        if (lastKnownLocation != null && lastKnownLocation.isFromMockProvider()) {
                            UtilsTools utilsTools = TakePhotoActivity.this.utilsTools;
                            TakePhotoActivity takePhotoActivity2 = TakePhotoActivity.this;
                            utilsTools.dialogDisableFakeGps(takePhotoActivity2, takePhotoActivity2.getApplicationContext());
                            return;
                        }
                        if (!TakePhotoActivity.this.utilsTools.checkMockLocationAndDeveloperOption(TakePhotoActivity.this.getApplicationContext())) {
                            TakePhotoActivity takePhotoActivity3 = TakePhotoActivity.this;
                            takePhotoActivity3.getApplicationContext();
                            if (!((LocationManager) takePhotoActivity3.getSystemService("location")).isProviderEnabled("gps")) {
                                new LoginPage().onGPS();
                                return;
                            }
                            try {
                                if (!TakePhotoActivity.this.utilsTools.initCheckDate(TakePhotoActivity.this.getApplicationContext())) {
                                    Snackbar.make(TakePhotoActivity.this.mLayout, TakePhotoActivity.this.getApplicationContext().getString(R.string.tanggal_dan_waktu_otomatis_belum_diset), 0).show();
                                } else if (TakePhotoActivity.this.checkConnectionService.isInternet()) {
                                    TakePhotoActivity.this.actionSwipe();
                                } else {
                                    Snackbar.make(TakePhotoActivity.this.mLayout, TakePhotoActivity.this.context.getString(R.string.please_check_internet_connection), 0).show();
                                }
                                return;
                            } catch (Exception e) {
                                e.printStackTrace();
                                e.getMessage();
                                return;
                            }
                        }
                        UtilsTools utilsTools2 = TakePhotoActivity.this.utilsTools;
                        TakePhotoActivity takePhotoActivity4 = TakePhotoActivity.this;
                        utilsTools2.dialogDisableFakeGps(takePhotoActivity4, takePhotoActivity4.getApplicationContext());
                    }
                }
            }
        });
        populateData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createIntentPicture() {
        String str;
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        intent.putExtra("android.intent.extras.CAMERA_REAR", 1);
        if (intent.resolveActivity(this.context.getPackageManager()) != null) {
            File file = null;
            if (this.tipePhoto.equalsIgnoreCase("tiba")) {
                str = "PHOTOTIBA_" + this.speno + "_" + this.driver;
            } else {
                str = "PHOTOBONGKAR_" + this.speno + "_" + this.driver;
            }
            try {
                file = this.photoUtils.createImageFile(str);
                this.imageFileName = file.getName();
                this.imageFilePath = file.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
                this.imageFileName = "";
            }
            if (file != null) {
                Uri uriForFile = FileProvider.getUriForFile(this.context, "com.myor.mydex.provider", file);
                this.mPhotoFile = file;
                try {
                    intent.putExtra("output", uriForFile);
                    startActivityForResult(intent, 1);
                    return;
                } catch (Exception unused) {
                    this.imageFileName = "";
                    return;
                }
            }
            this.imageFileName = "";
        }
    }

    private void setImage(String str, RoundedImageView roundedImageView) {
        CircularProgressDrawable circularProgressDrawable = new CircularProgressDrawable(this.context);
        circularProgressDrawable.setStrokeWidth(5.0f);
        circularProgressDrawable.setCenterRadius(30.0f);
        circularProgressDrawable.start();
        Glide.with(this.context).load(new File(DatabaseHelper.PHOTO_PATH_SDCARD + str).getAbsoluteFile()).fitCenter().placeholder(circularProgressDrawable).diskCacheStrategy(DiskCacheStrategy.NONE).skipMemoryCache(true).into(roundedImageView);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        IntentIntegrator.parseActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            try {
                this.photoUtils.setPicNew(this.imageFilePath, this.imageFileName, "");
                setImage(this.imageFileName, this.imgPhoto);
            } catch (Exception e) {
                Toast.makeText(this.context, e.getMessage(), 1).show();
                this.imageFileName = "";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void actionSwipe() {
        if (this.imageFileName.equalsIgnoreCase("")) {
            new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.informasi)).setMessage("Silahkan ambil foto terlebih dahulu").setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.TakePhotoActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        } else {
            new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.informasi)).setMessage(getApplicationContext().getString(R.string.apakah_anda_yakin_untuk_simpan_foto_ini)).setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.TakePhotoActivity.5
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    boolean updatePhoto;
                    if (TakePhotoActivity.this.speRepo.getDataPhoto(TakePhotoActivity.this.speno).size() == 0) {
                        updatePhoto = TakePhotoActivity.this.speRepo.insertPhoto(TakePhotoActivity.this.speno, TakePhotoActivity.this.imageFileName, TakePhotoActivity.this.time, String.valueOf(TakePhotoActivity.this.txtAddress.getText()), String.valueOf(TakePhotoActivity.this.txtLatitude.getText()), String.valueOf(TakePhotoActivity.this.txtLongitude.getText()), TakePhotoActivity.this.tipePhoto);
                    } else {
                        updatePhoto = TakePhotoActivity.this.speRepo.updatePhoto(TakePhotoActivity.this.speno, TakePhotoActivity.this.imageFileName, TakePhotoActivity.this.time, String.valueOf(TakePhotoActivity.this.txtAddress.getText()), String.valueOf(TakePhotoActivity.this.txtLatitude.getText()), String.valueOf(TakePhotoActivity.this.txtLongitude.getText()), TakePhotoActivity.this.tipePhoto);
                    }
                    if (updatePhoto) {
                        TakePhotoActivity.this.startActivity(new Intent(TakePhotoActivity.this.getApplicationContext(), (Class<?>) DashboardPage.class));
                    }
                }
            }).setNegativeButton(getApplicationContext().getString(R.string.tidak), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.TakePhotoActivity.4
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).show();
        }
        this.swipeButton.toggleState();
    }

    private void populateData() {
        this.mFusedLocation = LocationServices.getFusedLocationProviderClient((Activity) this);
        LocationRequest create = LocationRequest.create();
        create.setPriority(100);
        create.setInterval(CoroutineLiveDataKt.DEFAULT_TIMEOUT);
        create.setFastestInterval(3000L);
        getCurrentLocation();
        this.swipeButton.setText(this.context.getString(R.string.swipe_to_save_photo));
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle(getApplicationContext().getString(R.string.konfirmasi)).setMessage(getApplicationContext().getString(R.string.tidak_bisa_kembali)).setCancelable(false).setPositiveButton(getApplicationContext().getString(R.string.ya), new DialogInterface.OnClickListener() { // from class: com.myor.mydex.TakePhotoActivity.6
            @Override // android.content.DialogInterface.OnClickListener
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        }).show();
    }

    private void getCurrentLocation() {
        try {
            if (ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_FINE_LOCATION") == 0 || ActivityCompat.checkSelfPermission(this, "android.permission.ACCESS_COARSE_LOCATION") == 0) {
                this.mFusedLocation.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() { // from class: com.myor.mydex.TakePhotoActivity.8
                    @Override // com.google.android.gms.tasks.OnSuccessListener
                    public void onSuccess(Location location) {
                        if (location != null) {
                            TakePhotoActivity.this.latitude = location.getLatitude();
                            TakePhotoActivity.this.longitude = location.getLongitude();
                            TakePhotoActivity.this.txtLatitude.setText(String.valueOf(TakePhotoActivity.this.latitude));
                            TakePhotoActivity.this.txtLongitude.setText(String.valueOf(TakePhotoActivity.this.longitude));
                            Log.d("Location", "Latitude: " + TakePhotoActivity.this.latitude + ", Longitude: " + TakePhotoActivity.this.longitude);
                            TakePhotoActivity takePhotoActivity = TakePhotoActivity.this;
                            takePhotoActivity.getAddressFromLocation(takePhotoActivity.latitude, TakePhotoActivity.this.longitude);
                            return;
                        }
                        Log.e("Location", "Location is null");
                    }
                }).addOnFailureListener(new OnFailureListener() { // from class: com.myor.mydex.TakePhotoActivity.7
                    @Override // com.google.android.gms.tasks.OnFailureListener
                    public void onFailure(Exception exc) {
                        Log.e("Location", "Failed to get location", exc);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Location", "Location permission not granted", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getAddressFromLocation(double d, double d2) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        int i = 0;
        boolean z = false;
        while (i < 5 && !z) {
            try {
                List<Address> fromLocation = geocoder.getFromLocation(d, d2, 1);
                if (fromLocation != null && !fromLocation.isEmpty()) {
                    String addressLine = fromLocation.get(0).getAddressLine(0);
                    this.txtAddress.setText(addressLine);
                    Log.d("Location", "Address: " + addressLine);
                    z = true;
                } else {
                    Log.e("Location", "No address found");
                }
            } catch (IOException e) {
                i++;
                e.printStackTrace();
                Log.e("Location", "Failed to get address from location", e);
            }
        }
        if (z) {
            return;
        }
        Log.e("Location", "Failed to get address after 5 attempts");
    }
}
