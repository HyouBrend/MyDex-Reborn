package com.google.android.libraries.places.widget;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesStatusCodes;
import com.google.android.libraries.places.internal.zzhk;
import com.google.android.libraries.places.internal.zzia;
import com.google.android.libraries.places.internal.zzjp;
import com.google.android.libraries.places.widget.internal.ui.AutocompleteImplFragment;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public class AutocompleteActivity extends AppCompatActivity implements PlaceSelectionListener {
    public static final int RESULT_ERROR = 2;
    private int zza;
    private int zzb;
    private boolean zzc;

    public AutocompleteActivity() {
        super(R.layout.places_autocomplete_activity);
        this.zzc = false;
    }

    private final void zzc(int i, Place place, Status status) {
        try {
            Intent intent = new Intent();
            if (place != null) {
                intent.putExtra("places/selected_place", place);
            }
            intent.putExtra("places/status", status);
            setResult(i, intent);
            finish();
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        try {
            zzjp.zzk(Places.isInitialized(), "Places must be initialized.");
            zzjp.zzk(getCallingActivity() != null, "Cannot find caller. startActivityForResult should be used.");
            zzia zziaVar = (zzia) getIntent().getParcelableExtra("places/AutocompleteOptions");
            zziaVar.getClass();
            AutocompleteActivityMode autocompleteActivityMode = AutocompleteActivityMode.FULLSCREEN;
            int ordinal = zziaVar.zzh().ordinal();
            if (ordinal == 0) {
                this.zza = R.layout.places_autocomplete_impl_fragment_fullscreen;
                this.zzb = R.style.PlacesAutocompleteFullscreen;
            } else if (ordinal == 1) {
                this.zza = R.layout.places_autocomplete_impl_fragment_overlay;
                this.zzb = R.style.PlacesAutocompleteOverlay;
            }
            getSupportFragmentManager().setFragmentFactory(new com.google.android.libraries.places.widget.internal.ui.zzh(this.zza, this, zziaVar));
            setTheme(this.zzb);
            super.onCreate(bundle);
            final AutocompleteImplFragment autocompleteImplFragment = (AutocompleteImplFragment) getSupportFragmentManager().findFragmentById(R.id.places_autocomplete_content);
            zzjp.zzj(autocompleteImplFragment != null);
            autocompleteImplFragment.zzh(this);
            final View findViewById = findViewById(android.R.id.content);
            findViewById.setOnTouchListener(new View.OnTouchListener() { // from class: com.google.android.libraries.places.widget.zza
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return AutocompleteActivity.this.zzb(autocompleteImplFragment, findViewById, view, motionEvent);
                }
            });
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.zzb
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AutocompleteActivity.this.zza(view);
                }
            });
            if (zziaVar.zzj().isEmpty()) {
                zzc(2, null, new Status(PlacesStatusCodes.INVALID_REQUEST, "Place Fields must not be empty."));
            }
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
    public void onError(Status status) {
        zzc(true != status.isCanceled() ? 2 : 0, null, status);
    }

    @Override // com.google.android.libraries.places.widget.listener.PlaceSelectionListener
    public void onPlaceSelected(Place place) {
        zzc(-1, place, Status.RESULT_SUCCESS);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ void zza(View view) {
        if (this.zzc) {
            zzc(0, null, new Status(16));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public final /* synthetic */ boolean zzb(AutocompleteImplFragment autocompleteImplFragment, View view, View view2, MotionEvent motionEvent) {
        this.zzc = false;
        if (autocompleteImplFragment.getView() == null || motionEvent.getY() <= r1.getBottom()) {
            return false;
        }
        this.zzc = true;
        view.performClick();
        return true;
    }
}
