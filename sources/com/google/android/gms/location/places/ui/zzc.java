package com.google.android.gms.location.places.ui;

import android.R;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.util.TypedValue;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtilLight;

/* loaded from: classes.dex */
public class zzc {
    protected final Intent intent;

    public zzc(String str) {
        Intent intent = new Intent(str);
        this.intent = intent;
        intent.setPackage("com.google.android.gms");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Intent build(Activity activity) throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException {
        Resources.Theme theme = activity.getTheme();
        TypedValue typedValue = new TypedValue();
        TypedValue typedValue2 = new TypedValue();
        if (theme.resolveAttribute(R.attr.colorPrimary, typedValue, true) && !this.intent.hasExtra("primary_color")) {
            this.intent.putExtra("primary_color", typedValue.data);
        }
        if (theme.resolveAttribute(R.attr.colorPrimaryDark, typedValue2, true) && !this.intent.hasExtra("primary_color_dark")) {
            this.intent.putExtra("primary_color_dark", typedValue2.data);
        }
        GoogleApiAvailability.getInstance().verifyGooglePlayServicesIsAvailable(activity, GooglePlayServicesUtilLight.GOOGLE_PLAY_SERVICES_VERSION_CODE);
        return this.intent;
    }
}
