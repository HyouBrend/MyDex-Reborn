package com.rey.material.util;

import android.content.Context;
import android.graphics.Typeface;
import java.util.HashMap;

/* loaded from: classes2.dex */
public class TypefaceUtil {
    private static final String PREFIX_ASSET = "asset:";
    private static final HashMap<String, Typeface> sCachedFonts = new HashMap<>();

    private TypefaceUtil() {
    }

    public static Typeface load(Context context, String str, int i) {
        if (str != null && str.startsWith(PREFIX_ASSET)) {
            HashMap<String, Typeface> hashMap = sCachedFonts;
            synchronized (hashMap) {
                try {
                    try {
                        if (!hashMap.containsKey(str)) {
                            Typeface createFromAsset = Typeface.createFromAsset(context.getAssets(), str.substring(6));
                            hashMap.put(str, createFromAsset);
                            return createFromAsset;
                        }
                        return hashMap.get(str);
                    } catch (Exception unused) {
                        return Typeface.DEFAULT;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        return Typeface.create(str, i);
    }
}
