package com.google.android.gms.location.places.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import androidx.fragment.app.Fragment;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.R;
import com.google.android.gms.location.places.ui.PlaceAutocomplete;
import com.google.android.gms.maps.model.LatLngBounds;

@Deprecated
/* loaded from: classes.dex */
public class SupportPlaceAutocompleteFragment extends Fragment {
    private View zzde;
    private View zzdf;
    private EditText zzdg;
    private boolean zzdh;
    private LatLngBounds zzdi;
    private AutocompleteFilter zzdj;
    private PlaceSelectionListener zzdk;

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(R.layout.place_autocomplete_fragment, viewGroup, false);
        this.zzde = inflate.findViewById(R.id.place_autocomplete_search_button);
        this.zzdf = inflate.findViewById(R.id.place_autocomplete_clear_button);
        this.zzdg = (EditText) inflate.findViewById(R.id.place_autocomplete_search_input);
        zzg zzgVar = new zzg(this);
        this.zzde.setOnClickListener(zzgVar);
        this.zzdg.setOnClickListener(zzgVar);
        this.zzdf.setOnClickListener(new zzf(this));
        zzm();
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroyView() {
        this.zzde = null;
        this.zzdf = null;
        this.zzdg = null;
        super.onDestroyView();
    }

    public void setBoundsBias(LatLngBounds latLngBounds) {
        this.zzdi = latLngBounds;
    }

    public void setFilter(AutocompleteFilter autocompleteFilter) {
        this.zzdj = autocompleteFilter;
    }

    public void setText(CharSequence charSequence) {
        this.zzdg.setText(charSequence);
        zzm();
    }

    public void setHint(CharSequence charSequence) {
        this.zzdg.setHint(charSequence);
        this.zzde.setContentDescription(charSequence);
    }

    public void setOnPlaceSelectedListener(PlaceSelectionListener placeSelectionListener) {
        this.zzdk = placeSelectionListener;
    }

    private final void zzm() {
        this.zzdf.setVisibility(this.zzdg.getText().toString().isEmpty() ^ true ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void zzn() {
        int connectionStatusCode;
        try {
            Intent build = new PlaceAutocomplete.IntentBuilder(2).setBoundsBias(this.zzdi).setFilter(this.zzdj).zzg(this.zzdg.getText().toString()).zzd(1).build(getActivity());
            this.zzdh = true;
            startActivityForResult(build, 30421);
            connectionStatusCode = -1;
        } catch (GooglePlayServicesNotAvailableException e) {
            connectionStatusCode = e.errorCode;
            Log.e("Places", "Could not open autocomplete activity", e);
        } catch (GooglePlayServicesRepairableException e2) {
            connectionStatusCode = e2.getConnectionStatusCode();
            Log.e("Places", "Could not open autocomplete activity", e2);
        }
        if (connectionStatusCode != -1) {
            GoogleApiAvailability.getInstance().showErrorDialogFragment(getActivity(), connectionStatusCode, 30422);
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        this.zzdh = false;
        if (i == 30421) {
            if (i2 == -1) {
                Place place = PlaceAutocomplete.getPlace(getActivity(), intent);
                PlaceSelectionListener placeSelectionListener = this.zzdk;
                if (placeSelectionListener != null) {
                    placeSelectionListener.onPlaceSelected(place);
                }
                setText(place.getName().toString());
            } else if (i2 == 2) {
                Status status = PlaceAutocomplete.getStatus(getActivity(), intent);
                PlaceSelectionListener placeSelectionListener2 = this.zzdk;
                if (placeSelectionListener2 != null) {
                    placeSelectionListener2.onError(status);
                }
            }
        }
        super.onActivityResult(i, i2, intent);
    }
}
