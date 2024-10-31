package com.ebanx.swipebtn;

import android.view.MotionEvent;
import android.view.View;

/* loaded from: classes.dex */
final class TouchUtils {
    private TouchUtils() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean isTouchOutsideInitialPosition(MotionEvent motionEvent, View view) {
        return motionEvent.getX() > view.getX() + ((float) view.getWidth());
    }
}
