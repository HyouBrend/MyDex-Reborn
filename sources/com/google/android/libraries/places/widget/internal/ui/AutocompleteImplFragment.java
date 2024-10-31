package com.google.android.libraries.places.widget.internal.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.common.api.Status;
import com.google.android.libraries.places.R;
import com.google.android.libraries.places.api.model.AutocompletePrediction;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.internal.zzde;
import com.google.android.libraries.places.internal.zzhk;
import com.google.android.libraries.places.internal.zzia;
import com.google.android.libraries.places.internal.zzic;
import com.google.android.libraries.places.internal.zzid;
import com.google.android.libraries.places.internal.zzie;
import com.google.android.libraries.places.internal.zzio;
import com.google.android.libraries.places.internal.zzis;
import com.google.android.libraries.places.internal.zziu;
import com.google.android.libraries.places.internal.zzix;
import com.google.android.libraries.places.internal.zziy;
import com.google.android.libraries.places.internal.zzjp;
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class AutocompleteImplFragment extends Fragment {
    private final PlacesClient zza;
    private final zzia zzb;
    private final zziy zzc;
    private final zzde zzd;
    private zziu zze;
    private PlaceSelectionListener zzf;
    private EditText zzg;
    private RecyclerView zzh;
    private View zzi;
    private View zzj;
    private View zzk;
    private View zzl;
    private View zzm;
    private View zzn;
    private View zzo;
    private View zzp;
    private TextView zzq;
    private TextView zzr;
    private zzr zzs;
    private final zzj zzt;

    /* JADX INFO: Access modifiers changed from: private */
    public AutocompleteImplFragment(int i, PlacesClient placesClient, zzia zziaVar, zziy zziyVar, zzde zzdeVar) {
        super(i);
        this.zzt = new zzj(this, null);
        this.zza = placesClient;
        this.zzb = zziaVar;
        this.zzc = zziyVar;
        this.zzd = zzdeVar;
    }

    public /* synthetic */ AutocompleteImplFragment(int i, PlacesClient placesClient, zzia zziaVar, zziy zziyVar, zzde zzdeVar, zzm zzmVar) {
        this(i, placesClient, zziaVar, zziyVar, zzdeVar);
    }

    @Override // androidx.fragment.app.Fragment
    public final void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            zzix zzixVar = new zzix(this.zzb.zzf(), this.zzb.zzh(), this.zzb.zzm(), this.zzd);
            zziu zziuVar = (zziu) new ViewModelProvider(this, new zzis(new zzio(this.zza, this.zzb, zzixVar.zzh()), zzixVar, this.zzc)).get(zziu.class);
            this.zze = zziuVar;
            zziuVar.zze(bundle);
            requireActivity().getOnBackPressedDispatcher().addCallback(this, new zzf(this, true));
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    @Override // androidx.fragment.app.Fragment
    public final void onPause() {
        super.onPause();
        this.zze.zzi();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onResume() {
        super.onResume();
        this.zze.zzh();
    }

    @Override // androidx.fragment.app.Fragment
    public final void onViewCreated(View view, Bundle bundle) {
        String zzl;
        int identifier;
        try {
            this.zzg = (EditText) view.findViewById(R.id.places_autocomplete_search_bar);
            this.zzh = (RecyclerView) view.findViewById(R.id.places_autocomplete_list);
            this.zzi = view.findViewById(R.id.places_autocomplete_back_button);
            this.zzj = view.findViewById(R.id.places_autocomplete_clear_button);
            this.zzk = view.findViewById(R.id.places_autocomplete_search_bar_separator);
            this.zzl = view.findViewById(R.id.places_autocomplete_progress);
            this.zzm = view.findViewById(R.id.places_autocomplete_try_again_progress);
            this.zzn = view.findViewById(R.id.places_autocomplete_powered_by_google);
            this.zzo = view.findViewById(R.id.places_autocomplete_powered_by_google_separator);
            this.zzp = view.findViewById(R.id.places_autocomplete_sad_cloud);
            this.zzq = (TextView) view.findViewById(R.id.places_autocomplete_error_message);
            this.zzr = (TextView) view.findViewById(R.id.places_autocomplete_try_again);
            this.zzg.addTextChangedListener(this.zzt);
            this.zzg.setOnFocusChangeListener(new zzl(null));
            EditText editText = this.zzg;
            if (TextUtils.isEmpty(this.zzb.zzl())) {
                zzl = zzid.zzc(requireContext(), R.string.places_autocomplete_search_hint);
            } else {
                zzl = this.zzb.zzl();
            }
            editText.setHint(zzl);
            AutocompleteActivityMode autocompleteActivityMode = AutocompleteActivityMode.FULLSCREEN;
            int ordinal = this.zzb.zzh().ordinal();
            if (ordinal == 0) {
                int zza = this.zzb.zza();
                int zzb = this.zzb.zzb();
                if (Color.alpha(zza) < 255) {
                    zza = 0;
                }
                if (zza != 0 && zzb != 0) {
                    int zza2 = zzie.zza(zza, ContextCompat.getColor(requireContext(), R.color.places_text_white_alpha_87), ContextCompat.getColor(requireContext(), R.color.places_text_black_alpha_87));
                    int zza3 = zzie.zza(zza, ContextCompat.getColor(requireContext(), R.color.places_text_white_alpha_26), ContextCompat.getColor(requireContext(), R.color.places_text_black_alpha_26));
                    view.findViewById(R.id.places_autocomplete_search_bar_container).setBackgroundColor(zza);
                    Window window = requireActivity().getWindow();
                    if (!zzie.zzc(zzb, -1, -16777216)) {
                        window.setStatusBarColor(zzb);
                    } else if (Build.VERSION.SDK_INT >= 23) {
                        window.setStatusBarColor(zzb);
                        window.getDecorView().setSystemUiVisibility(8192);
                    }
                    this.zzg.setTextColor(zza2);
                    this.zzg.setHintTextColor(zza3);
                    zzie.zzb((ImageView) this.zzi, zza2);
                    zzie.zzb((ImageView) this.zzj, zza2);
                }
            } else if (ordinal == 1 && (identifier = getResources().getIdentifier("status_bar_height", "dimen", "android")) > 0) {
                requireActivity().getWindow().addFlags(67108864);
                ViewCompat.setPaddingRelative(view, view.getPaddingLeft(), view.getPaddingTop() + getResources().getDimensionPixelSize(identifier), view.getPaddingRight(), view.getPaddingBottom());
            }
            this.zzi.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.internal.ui.zzd
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AutocompleteImplFragment.this.zzc(view2);
                }
            });
            this.zzj.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.internal.ui.zzc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AutocompleteImplFragment.this.zzd(view2);
                }
            });
            this.zzr.setOnClickListener(new View.OnClickListener() { // from class: com.google.android.libraries.places.widget.internal.ui.zza
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AutocompleteImplFragment.this.zzf(view2);
                }
            });
            this.zzs = new zzr(new zzb(this));
            this.zzh.setLayoutManager(new LinearLayoutManager(requireContext()));
            this.zzh.setItemAnimator(new zzo(getResources()));
            this.zzh.setAdapter(this.zzs);
            this.zzh.addOnScrollListener(new zzg(this));
            this.zze.zza().observe(getViewLifecycleOwner(), new Observer() { // from class: com.google.android.libraries.places.widget.internal.ui.zze
                @Override // androidx.lifecycle.Observer
                public final void onChanged(Object obj) {
                    AutocompleteImplFragment.this.zzg((zzic) obj);
                }
            });
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public final /* synthetic */ void zzc(View view) {
        this.zze.zzj();
    }

    public final /* synthetic */ void zzg(zzic zzicVar) {
        try {
            this.zzj.setVisibility(0);
            this.zzk.setVisibility(0);
            this.zzl.setVisibility(8);
            this.zzm.setVisibility(8);
            this.zzn.setVisibility(0);
            this.zzo.setVisibility(8);
            this.zzp.setVisibility(8);
            this.zzq.setVisibility(8);
            this.zzr.setVisibility(8);
            AutocompleteActivityMode autocompleteActivityMode = AutocompleteActivityMode.FULLSCREEN;
            switch (zzicVar.zzf() - 1) {
                case 0:
                    if (TextUtils.isEmpty(this.zzb.zzm())) {
                        this.zzj.setVisibility(8);
                    }
                    this.zzg.requestFocus();
                    this.zzg.setText(this.zzb.zzm());
                    EditText editText = this.zzg;
                    editText.setSelection(editText.getText().length());
                    return;
                case 1:
                    this.zzs.submitList(null);
                    this.zzj.setVisibility(8);
                    this.zzg.getText().clear();
                    return;
                case 2:
                    this.zzl.setVisibility(0);
                    return;
                case 3:
                    this.zzr.setVisibility(8);
                    this.zzm.setVisibility(0);
                    this.zzn.setVisibility(8);
                    this.zzp.setVisibility(0);
                    this.zzq.setVisibility(0);
                    return;
                case 4:
                    this.zzs.submitList(zzicVar.zzd());
                    this.zzo.setVisibility(0);
                    return;
                case 5:
                    this.zzs.submitList(null);
                    this.zzn.setVisibility(8);
                    this.zzp.setVisibility(0);
                    this.zzr.setVisibility(4);
                    this.zzq.setText(getString(R.string.places_autocomplete_no_results_for_query, zzicVar.zze()));
                    this.zzq.setVisibility(0);
                    return;
                case 6:
                    break;
                case 7:
                default:
                    PlaceSelectionListener placeSelectionListener = this.zzf;
                    Place zzc = zzicVar.zzc();
                    zzc.getClass();
                    placeSelectionListener.onPlaceSelected(zzc);
                    return;
                case 8:
                    AutocompletePrediction zzb = zzicVar.zzb();
                    zzjp.zzc(zzb, "Prediction should not be null.");
                    this.zzg.clearFocus();
                    this.zzg.removeTextChangedListener(this.zzt);
                    this.zzg.setText(zzb.getPrimaryText(null));
                    this.zzg.addTextChangedListener(this.zzt);
                    break;
                case 9:
                    PlaceSelectionListener placeSelectionListener2 = this.zzf;
                    Status zza = zzicVar.zza();
                    zza.getClass();
                    placeSelectionListener2.onError(zza);
                    return;
            }
            this.zzs.submitList(null);
            this.zzn.setVisibility(8);
            this.zzp.setVisibility(0);
            this.zzr.setVisibility(0);
            this.zzq.setText(getString(R.string.places_search_error));
            this.zzq.setVisibility(0);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public final void zzh(PlaceSelectionListener placeSelectionListener) {
        this.zzf = placeSelectionListener;
    }

    public final /* synthetic */ void zzd(View view) {
        try {
            this.zze.zzk();
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public final /* synthetic */ void zze(AutocompletePrediction autocompletePrediction, int i) {
        try {
            this.zze.zzf(autocompletePrediction, i);
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }

    public final /* synthetic */ void zzf(View view) {
        try {
            this.zze.zzl(this.zzg.getText().toString());
        } catch (Error | RuntimeException e) {
            zzhk.zzb(e);
            throw e;
        }
    }
}
