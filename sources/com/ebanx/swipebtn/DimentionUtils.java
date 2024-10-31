package com.ebanx.swipebtn;

import android.content.Context;

/* loaded from: classes.dex */
final class DimentionUtils {
    private DimentionUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static float converPixelsToSp(float f, Context context) {
        return f / context.getResources().getDisplayMetrics().scaledDensity;
    }
}
