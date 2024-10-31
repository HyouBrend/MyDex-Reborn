package com.google.android.libraries.places.internal;

import com.google.common.flogger.backend.google.GooglePlatform;
import com.google.common.flogger.backend.system.DefaultPlatform;
import java.lang.reflect.InvocationTargetException;

/* compiled from: com.google.android.libraries.places:places@@3.2.0 */
/* loaded from: classes2.dex */
public final class zzmh {
    public static zzmg zza() {
        try {
            try {
                try {
                    return (zzmg) zzml.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (IllegalAccessException | InstantiationException | NoClassDefFoundError | NoSuchMethodException | InvocationTargetException unused) {
                    return null;
                }
            } catch (IllegalAccessException | InstantiationException | NoClassDefFoundError | NoSuchMethodException | InvocationTargetException unused2) {
                return (zzmg) DefaultPlatform.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
            }
        } catch (IllegalAccessException | InstantiationException | NoClassDefFoundError | NoSuchMethodException | InvocationTargetException unused3) {
            return (zzmg) GooglePlatform.class.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        }
    }
}
