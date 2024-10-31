package com.google.android.material.color.utilities;

import com.github.mikephil.charting.utils.Utils;

/* loaded from: classes2.dex */
public class SchemeMonochrome extends DynamicScheme {
    public SchemeMonochrome(Hct hct, boolean z, double d) {
        super(hct, Variant.MONOCHROME, z, d, TonalPalette.fromHueAndChroma(hct.getHue(), Utils.DOUBLE_EPSILON), TonalPalette.fromHueAndChroma(hct.getHue(), Utils.DOUBLE_EPSILON), TonalPalette.fromHueAndChroma(hct.getHue(), Utils.DOUBLE_EPSILON), TonalPalette.fromHueAndChroma(hct.getHue(), Utils.DOUBLE_EPSILON), TonalPalette.fromHueAndChroma(hct.getHue(), Utils.DOUBLE_EPSILON));
    }
}
