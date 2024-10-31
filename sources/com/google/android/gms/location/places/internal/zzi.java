package com.google.android.gms.location.places.internal;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import java.util.Collection;
import java.util.List;
import javax.annotation.Nullable;

/* loaded from: classes.dex */
public final class zzi {
    @Nullable
    public static String zzc(@Nullable Collection<String> collection) {
        if (collection == null || collection.isEmpty()) {
            return null;
        }
        return TextUtils.join(", ", collection);
    }

    public static CharSequence zzb(String str, List<zzb> list, @Nullable CharacterStyle characterStyle) {
        if (characterStyle == null) {
            return str;
        }
        SpannableString spannableString = new SpannableString(str);
        for (zzb zzbVar : list) {
            spannableString.setSpan(CharacterStyle.wrap(characterStyle), zzbVar.offset, zzbVar.offset + zzbVar.length, 0);
        }
        return spannableString;
    }
}
