package com.rey.material.util;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.TypedValue;

/* loaded from: classes2.dex */
public class ThemeUtil {
    private static TypedValue value;

    public static int dpToPx(Context context, int i) {
        return (int) (TypedValue.applyDimension(1, i, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    public static int spToPx(Context context, int i) {
        return (int) (TypedValue.applyDimension(2, i, context.getResources().getDisplayMetrics()) + 0.5f);
    }

    private static int getColor(Context context, int i, int i2) {
        if (value == null) {
            value = new TypedValue();
        }
        try {
            Resources.Theme theme = context.getTheme();
            if (theme != null && theme.resolveAttribute(i, value, true)) {
                if (value.type >= 16 && value.type <= 31) {
                    return value.data;
                }
                if (value.type == 3) {
                    return context.getResources().getColor(value.resourceId);
                }
            }
        } catch (Exception unused) {
        }
        return i2;
    }

    public static int windowBackground(Context context, int i) {
        return getColor(context, R.attr.windowBackground, i);
    }

    public static int textColorPrimary(Context context, int i) {
        return getColor(context, R.attr.textColorPrimary, i);
    }

    public static int textColorSecondary(Context context, int i) {
        return getColor(context, R.attr.textColorSecondary, i);
    }

    public static int colorPrimary(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getColor(context, R.attr.colorPrimary, i);
        }
        return getColor(context, com.rey.material.R.attr.colorPrimary, i);
    }

    public static int colorPrimaryDark(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getColor(context, R.attr.colorPrimaryDark, i);
        }
        return getColor(context, com.rey.material.R.attr.colorPrimaryDark, i);
    }

    public static int colorAccent(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getColor(context, R.attr.colorAccent, i);
        }
        return getColor(context, com.rey.material.R.attr.colorAccent, i);
    }

    public static int colorControlNormal(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getColor(context, R.attr.colorControlNormal, i);
        }
        return getColor(context, com.rey.material.R.attr.colorControlNormal, i);
    }

    public static int colorControlActivated(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getColor(context, R.attr.colorControlActivated, i);
        }
        return getColor(context, com.rey.material.R.attr.colorControlActivated, i);
    }

    public static int colorControlHighlight(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getColor(context, R.attr.colorControlHighlight, i);
        }
        return getColor(context, com.rey.material.R.attr.colorControlHighlight, i);
    }

    public static int colorButtonNormal(Context context, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return getColor(context, R.attr.colorButtonNormal, i);
        }
        return getColor(context, com.rey.material.R.attr.colorButtonNormal, i);
    }

    public static int colorSwitchThumbNormal(Context context, int i) {
        return getColor(context, com.rey.material.R.attr.colorSwitchThumbNormal, i);
    }

    public static int getType(TypedArray typedArray, int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            return typedArray.getType(i);
        }
        TypedValue peekValue = typedArray.peekValue(i);
        if (peekValue == null) {
            return 0;
        }
        return peekValue.type;
    }

    public static CharSequence getString(TypedArray typedArray, int i, CharSequence charSequence) {
        String string = typedArray.getString(i);
        return string == null ? charSequence : string;
    }
}
