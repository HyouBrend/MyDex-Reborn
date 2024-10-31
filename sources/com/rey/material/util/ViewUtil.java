package com.rey.material.util;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import com.rey.material.R;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes2.dex */
public class ViewUtil {
    public static final long FRAME_DURATION = 16;
    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        AtomicInteger atomicInteger;
        int i;
        int i2;
        if (Build.VERSION.SDK_INT >= 17) {
            return View.generateViewId();
        }
        do {
            atomicInteger = sNextGeneratedId;
            i = atomicInteger.get();
            i2 = i + 1;
            if (i2 > 16777215) {
                i2 = 1;
            }
        } while (!atomicInteger.compareAndSet(i, i2));
        return i;
    }

    public static boolean hasState(int[] iArr, int i) {
        if (iArr == null) {
            return false;
        }
        for (int i2 : iArr) {
            if (i2 == i) {
                return true;
            }
        }
        return false;
    }

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void applyStyle(View view, int i) {
        applyStyle(view, (AttributeSet) null, 0, i);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void applyStyle(View view, AttributeSet attributeSet, int i, int i2) {
        int i3;
        TypedArray obtainStyledAttributes = view.getContext().obtainStyledAttributes(attributeSet, R.styleable.View, i, i2);
        int i4 = 0;
        int i5 = -1;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i6 = Integer.MIN_VALUE;
        boolean z4 = false;
        int i7 = -1;
        int i8 = Integer.MIN_VALUE;
        int i9 = -1;
        int i10 = -1;
        int i11 = -1;
        for (int indexCount = obtainStyledAttributes.getIndexCount(); i4 < indexCount; indexCount = i3) {
            int index = obtainStyledAttributes.getIndex(i4);
            if (index == R.styleable.View_android_background) {
                setBackground(view, obtainStyledAttributes.getDrawable(index));
            } else if (index == R.styleable.View_android_backgroundTint) {
                if (Build.VERSION.SDK_INT >= 21) {
                    view.setBackgroundTintList(obtainStyledAttributes.getColorStateList(index));
                }
            } else {
                if (index == R.styleable.View_android_backgroundTintMode) {
                    i3 = indexCount;
                    if (Build.VERSION.SDK_INT >= 21) {
                        int i12 = obtainStyledAttributes.getInt(index, 3);
                        if (i12 == 3) {
                            view.setBackgroundTintMode(PorterDuff.Mode.SRC_OVER);
                        } else if (i12 == 5) {
                            view.setBackgroundTintMode(PorterDuff.Mode.SRC_IN);
                        } else if (i12 == 9) {
                            view.setBackgroundTintMode(PorterDuff.Mode.SRC_ATOP);
                        } else {
                            switch (i12) {
                                case 14:
                                    view.setBackgroundTintMode(PorterDuff.Mode.MULTIPLY);
                                    break;
                                case 15:
                                    view.setBackgroundTintMode(PorterDuff.Mode.SCREEN);
                                    break;
                                case 16:
                                    view.setBackgroundTintMode(PorterDuff.Mode.ADD);
                                    break;
                            }
                            i4++;
                        }
                    }
                } else {
                    i3 = indexCount;
                    if (index == R.styleable.View_android_elevation) {
                        if (Build.VERSION.SDK_INT >= 21) {
                            view.setElevation(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                        }
                    } else {
                        if (index == R.styleable.View_android_padding) {
                            i5 = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                            z = true;
                        } else {
                            if (index == R.styleable.View_android_paddingLeft) {
                                i7 = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                                z = true;
                            } else if (index == R.styleable.View_android_paddingTop) {
                                i9 = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                            } else if (index == R.styleable.View_android_paddingRight) {
                                i10 = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                            } else if (index == R.styleable.View_android_paddingBottom) {
                                i11 = obtainStyledAttributes.getDimensionPixelSize(index, -1);
                            } else if (index == R.styleable.View_android_paddingStart) {
                                if (Build.VERSION.SDK_INT >= 17) {
                                    i6 = obtainStyledAttributes.getDimensionPixelSize(index, Integer.MIN_VALUE);
                                    z2 = i6 != Integer.MIN_VALUE;
                                }
                            } else if (index == R.styleable.View_android_paddingEnd) {
                                if (Build.VERSION.SDK_INT >= 17) {
                                    int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(index, Integer.MIN_VALUE);
                                    z4 = dimensionPixelSize != Integer.MIN_VALUE;
                                    i8 = dimensionPixelSize;
                                }
                            } else if (index == R.styleable.View_android_fadeScrollbars) {
                                view.setScrollbarFadingEnabled(obtainStyledAttributes.getBoolean(index, true));
                            } else if (index == R.styleable.View_android_fadingEdgeLength) {
                                view.setFadingEdgeLength(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                            } else if (index == R.styleable.View_android_minHeight) {
                                view.setMinimumHeight(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                            } else if (index == R.styleable.View_android_minWidth) {
                                view.setMinimumWidth(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                            } else if (index == R.styleable.View_android_requiresFadingEdge) {
                                view.setVerticalFadingEdgeEnabled(obtainStyledAttributes.getBoolean(index, true));
                            } else if (index == R.styleable.View_android_scrollbarDefaultDelayBeforeFade) {
                                if (Build.VERSION.SDK_INT >= 16) {
                                    view.setScrollBarDefaultDelayBeforeFade(obtainStyledAttributes.getInteger(index, 0));
                                }
                            } else if (index == R.styleable.View_android_scrollbarFadeDuration) {
                                if (Build.VERSION.SDK_INT >= 16) {
                                    view.setScrollBarFadeDuration(obtainStyledAttributes.getInteger(index, 0));
                                }
                            } else if (index == R.styleable.View_android_scrollbarSize) {
                                if (Build.VERSION.SDK_INT >= 16) {
                                    view.setScrollBarSize(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                                }
                            } else if (index == R.styleable.View_android_scrollbarStyle) {
                                int integer = obtainStyledAttributes.getInteger(index, 0);
                                if (integer == 0) {
                                    view.setScrollBarStyle(0);
                                } else if (integer == 16777216) {
                                    view.setScrollBarStyle(16777216);
                                } else if (integer == 33554432) {
                                    view.setScrollBarStyle(33554432);
                                } else if (integer == 50331648) {
                                    view.setScrollBarStyle(50331648);
                                }
                            } else if (index == R.styleable.View_android_soundEffectsEnabled) {
                                view.setSoundEffectsEnabled(obtainStyledAttributes.getBoolean(index, true));
                            } else if (index == R.styleable.View_android_textAlignment) {
                                if (Build.VERSION.SDK_INT >= 17) {
                                    switch (obtainStyledAttributes.getInteger(index, 0)) {
                                        case 0:
                                            view.setTextAlignment(0);
                                            continue;
                                        case 1:
                                            view.setTextAlignment(1);
                                            break;
                                        case 2:
                                            view.setTextAlignment(2);
                                            break;
                                        case 3:
                                            view.setTextAlignment(3);
                                            break;
                                        case 4:
                                            view.setTextAlignment(4);
                                            break;
                                        case 5:
                                            view.setTextAlignment(5);
                                            break;
                                        case 6:
                                            view.setTextAlignment(6);
                                            break;
                                    }
                                }
                            } else if (index == R.styleable.View_android_textDirection) {
                                if (Build.VERSION.SDK_INT >= 17) {
                                    int integer2 = obtainStyledAttributes.getInteger(index, 0);
                                    if (integer2 == 0) {
                                        view.setTextDirection(0);
                                    } else if (integer2 == 1) {
                                        view.setTextDirection(1);
                                    } else if (integer2 == 2) {
                                        view.setTextDirection(2);
                                    } else if (integer2 == 3) {
                                        view.setTextDirection(3);
                                    } else if (integer2 == 4) {
                                        view.setTextDirection(4);
                                    } else if (integer2 == 5) {
                                        view.setTextDirection(5);
                                    }
                                }
                            } else if (index == R.styleable.View_android_visibility) {
                                int integer3 = obtainStyledAttributes.getInteger(index, 0);
                                if (integer3 == 0) {
                                    view.setVisibility(0);
                                } else if (integer3 == 1) {
                                    view.setVisibility(4);
                                } else if (integer3 == 2) {
                                    view.setVisibility(8);
                                }
                            } else if (index == R.styleable.View_android_layoutDirection) {
                                if (Build.VERSION.SDK_INT >= 17) {
                                    int integer4 = obtainStyledAttributes.getInteger(index, 0);
                                    if (integer4 == 0) {
                                        view.setLayoutDirection(0);
                                    } else if (integer4 == 1) {
                                        view.setLayoutDirection(1);
                                    } else if (integer4 == 2) {
                                        view.setLayoutDirection(2);
                                    } else if (integer4 == 3) {
                                        view.setLayoutDirection(3);
                                    }
                                }
                            } else if (index == R.styleable.View_android_src && (view instanceof ImageView)) {
                                ((ImageView) view).setImageResource(obtainStyledAttributes.getResourceId(index, 0));
                            }
                            i4++;
                        }
                        z3 = true;
                        i4++;
                    }
                }
                i4++;
            }
            i3 = indexCount;
            i4++;
        }
        if (i5 >= 0) {
            view.setPadding(i5, i5, i5, i5);
        } else if (Build.VERSION.SDK_INT < 17) {
            if (!z2) {
                i6 = i7;
            }
            if (!z4) {
                i8 = i10;
            }
            if (i6 < 0) {
                i6 = view.getPaddingLeft();
            }
            if (i9 < 0) {
                i9 = view.getPaddingTop();
            }
            int i13 = i9;
            if (i8 < 0) {
                i8 = view.getPaddingRight();
            }
            int i14 = i8;
            if (i11 < 0) {
                i11 = view.getPaddingBottom();
            }
            view.setPadding(i6, i13, i14, i11);
        } else {
            if (z || z3) {
                if (!z) {
                    i7 = view.getPaddingLeft();
                }
                int i15 = i7;
                int paddingTop = i9 >= 0 ? i9 : view.getPaddingTop();
                if (!z3) {
                    i10 = view.getPaddingRight();
                }
                view.setPadding(i15, paddingTop, i10, i11 >= 0 ? i11 : view.getPaddingBottom());
            }
            if (z2 || z4) {
                if (!z2) {
                    i6 = view.getPaddingStart();
                }
                if (i9 < 0) {
                    i9 = view.getPaddingTop();
                }
                int i16 = i9;
                if (!z4) {
                    i8 = view.getPaddingEnd();
                }
                int i17 = i8;
                if (i11 < 0) {
                    i11 = view.getPaddingBottom();
                }
                view.setPaddingRelative(i6, i16, i17, i11);
            }
        }
        obtainStyledAttributes.recycle();
        if (view instanceof TextView) {
            applyStyle((TextView) view, attributeSet, i, i2);
        }
    }

    public static void applyFont(TextView textView, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = textView.getContext().obtainStyledAttributes(attributeSet, new int[]{R.attr.tv_fontFamily}, i, i2);
        String string = obtainStyledAttributes.getString(0);
        if (string != null) {
            textView.setTypeface(TypefaceUtil.load(textView.getContext(), string, 0));
        }
        obtainStyledAttributes.recycle();
    }

    public static void applyTextAppearance(TextView textView, int i) {
        int i2;
        String str;
        float f;
        float f2;
        int i3;
        Typeface typeface;
        if (i == 0) {
            return;
        }
        TypedArray obtainStyledAttributes = textView.getContext().obtainStyledAttributes(i, R.styleable.TextAppearance);
        int i4 = -1;
        float f3 = 0.0f;
        int i5 = 0;
        if (obtainStyledAttributes != null) {
            int indexCount = obtainStyledAttributes.getIndexCount();
            int i6 = 0;
            str = null;
            int i7 = -1;
            int i8 = 0;
            f = 0.0f;
            f2 = 0.0f;
            float f4 = 0.0f;
            i3 = -1;
            while (i6 < indexCount) {
                int index = obtainStyledAttributes.getIndex(i6);
                if (index == R.styleable.TextAppearance_android_textColorHighlight) {
                    textView.setHighlightColor(obtainStyledAttributes.getColor(index, 0));
                } else if (index == R.styleable.TextAppearance_android_textColor) {
                    textView.setTextColor(obtainStyledAttributes.getColorStateList(index));
                } else if (index == R.styleable.TextAppearance_android_textColorHint) {
                    textView.setHintTextColor(obtainStyledAttributes.getColorStateList(index));
                } else if (index == R.styleable.TextAppearance_android_textColorLink) {
                    textView.setLinkTextColor(obtainStyledAttributes.getColorStateList(index));
                } else if (index == R.styleable.TextAppearance_android_textSize) {
                    textView.setTextSize(0, obtainStyledAttributes.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.TextAppearance_android_typeface) {
                    i3 = obtainStyledAttributes.getInt(index, i4);
                } else if (index == R.styleable.TextAppearance_android_fontFamily) {
                    str = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.TextAppearance_tv_fontFamily) {
                    str = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.TextAppearance_android_textStyle) {
                    i7 = obtainStyledAttributes.getInt(index, i4);
                } else if (index == R.styleable.TextAppearance_android_textAllCaps) {
                    if (Build.VERSION.SDK_INT >= 14) {
                        textView.setAllCaps(obtainStyledAttributes.getBoolean(index, false));
                    }
                } else if (index == R.styleable.TextAppearance_android_shadowColor) {
                    i8 = obtainStyledAttributes.getInt(index, 0);
                } else if (index == R.styleable.TextAppearance_android_shadowDx) {
                    f = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.TextAppearance_android_shadowDy) {
                    f2 = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.TextAppearance_android_shadowRadius) {
                    f4 = obtainStyledAttributes.getFloat(index, 0.0f);
                } else if (index == R.styleable.TextAppearance_android_elegantTextHeight) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        textView.setElegantTextHeight(obtainStyledAttributes.getBoolean(index, false));
                    }
                } else if (index == R.styleable.TextAppearance_android_letterSpacing) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        textView.setLetterSpacing(obtainStyledAttributes.getFloat(index, 0.0f));
                    }
                } else if (index == R.styleable.TextAppearance_android_fontFeatureSettings && Build.VERSION.SDK_INT >= 21) {
                    textView.setFontFeatureSettings(obtainStyledAttributes.getString(index));
                }
                i6++;
                i4 = -1;
            }
            obtainStyledAttributes.recycle();
            i2 = i7;
            i5 = i8;
            f3 = f4;
        } else {
            i2 = -1;
            str = null;
            f = 0.0f;
            f2 = 0.0f;
            i3 = -1;
        }
        if (i5 != 0) {
            textView.setShadowLayer(f3, f, f2, i5);
        }
        if (str != null) {
            typeface = TypefaceUtil.load(textView.getContext(), str, i2);
            if (typeface != null) {
                textView.setTypeface(typeface);
            }
        } else {
            typeface = null;
        }
        if (typeface != null) {
            if (i3 == 1) {
                typeface = Typeface.SANS_SERIF;
            } else if (i3 == 2) {
                typeface = Typeface.SERIF;
            } else if (i3 == 3) {
                typeface = Typeface.MONOSPACE;
            }
            textView.setTypeface(typeface, i2);
        }
    }

    private static void applyStyle(TextView textView, AttributeSet attributeSet, int i, int i2) {
        int i3;
        float f;
        float f2;
        float f3;
        String str;
        int i4;
        int i5;
        Typeface typeface;
        int i6;
        String string;
        TypedArray obtainStyledAttributes = textView.getContext().obtainStyledAttributes(attributeSet, R.styleable.TextViewAppearance, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.TextViewAppearance_android_textAppearance, 0);
        obtainStyledAttributes.recycle();
        TypedArray obtainStyledAttributes2 = resourceId != 0 ? textView.getContext().obtainStyledAttributes(resourceId, R.styleable.TextAppearance) : null;
        int i7 = 14;
        if (obtainStyledAttributes2 != null) {
            int indexCount = obtainStyledAttributes2.getIndexCount();
            int i8 = 0;
            i3 = 0;
            f = 0.0f;
            f2 = 0.0f;
            f3 = 0.0f;
            str = null;
            i4 = -1;
            i5 = -1;
            while (i8 < indexCount) {
                int index = obtainStyledAttributes2.getIndex(i8);
                if (index == R.styleable.TextAppearance_android_textColorHighlight) {
                    textView.setHighlightColor(obtainStyledAttributes2.getColor(index, 0));
                } else if (index == R.styleable.TextAppearance_android_textColor) {
                    textView.setTextColor(obtainStyledAttributes2.getColorStateList(index));
                } else if (index == R.styleable.TextAppearance_android_textColorHint) {
                    textView.setHintTextColor(obtainStyledAttributes2.getColorStateList(index));
                } else if (index == R.styleable.TextAppearance_android_textColorLink) {
                    textView.setLinkTextColor(obtainStyledAttributes2.getColorStateList(index));
                } else if (index == R.styleable.TextAppearance_android_textSize) {
                    textView.setTextSize(0, obtainStyledAttributes2.getDimensionPixelSize(index, 0));
                } else if (index == R.styleable.TextAppearance_android_typeface) {
                    i4 = obtainStyledAttributes2.getInt(index, -1);
                } else if (index == R.styleable.TextAppearance_android_fontFamily) {
                    str = obtainStyledAttributes2.getString(index);
                } else if (index == R.styleable.TextAppearance_tv_fontFamily) {
                    str = obtainStyledAttributes2.getString(index);
                } else if (index == R.styleable.TextAppearance_android_textStyle) {
                    i5 = obtainStyledAttributes2.getInt(index, -1);
                } else if (index == R.styleable.TextAppearance_android_textAllCaps) {
                    if (Build.VERSION.SDK_INT >= i7) {
                        textView.setAllCaps(obtainStyledAttributes2.getBoolean(index, false));
                    }
                } else if (index == R.styleable.TextAppearance_android_shadowColor) {
                    i3 = obtainStyledAttributes2.getInt(index, 0);
                } else if (index == R.styleable.TextAppearance_android_shadowDx) {
                    f = obtainStyledAttributes2.getFloat(index, 0.0f);
                } else if (index == R.styleable.TextAppearance_android_shadowDy) {
                    f2 = obtainStyledAttributes2.getFloat(index, 0.0f);
                } else if (index == R.styleable.TextAppearance_android_shadowRadius) {
                    f3 = obtainStyledAttributes2.getFloat(index, 0.0f);
                } else if (index == R.styleable.TextAppearance_android_elegantTextHeight) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        textView.setElegantTextHeight(obtainStyledAttributes2.getBoolean(index, false));
                    }
                } else if (index == R.styleable.TextAppearance_android_letterSpacing) {
                    if (Build.VERSION.SDK_INT >= 21) {
                        textView.setLetterSpacing(obtainStyledAttributes2.getFloat(index, 0.0f));
                    }
                } else if (index == R.styleable.TextAppearance_android_fontFeatureSettings && Build.VERSION.SDK_INT >= 21) {
                    textView.setFontFeatureSettings(obtainStyledAttributes2.getString(index));
                }
                i8++;
                i7 = 14;
            }
            obtainStyledAttributes2.recycle();
        } else {
            i3 = 0;
            f = 0.0f;
            f2 = 0.0f;
            f3 = 0.0f;
            str = null;
            i4 = -1;
            i5 = -1;
        }
        TypedArray obtainStyledAttributes3 = textView.getContext().obtainStyledAttributes(attributeSet, R.styleable.TextView, i, i2);
        float f4 = f3;
        String str2 = str;
        int i9 = i4;
        int i10 = i5;
        int i11 = 0;
        boolean z = false;
        Drawable drawable = null;
        Drawable drawable2 = null;
        Drawable drawable3 = null;
        Drawable drawable4 = null;
        Drawable drawable5 = null;
        Drawable drawable6 = null;
        boolean z2 = false;
        for (int indexCount2 = obtainStyledAttributes3.getIndexCount(); i11 < indexCount2; indexCount2 = i6) {
            int index2 = obtainStyledAttributes3.getIndex(i11);
            if (index2 == R.styleable.TextView_android_drawableLeft) {
                drawable2 = obtainStyledAttributes3.getDrawable(index2);
            } else if (index2 == R.styleable.TextView_android_drawableTop) {
                drawable3 = obtainStyledAttributes3.getDrawable(index2);
            } else if (index2 == R.styleable.TextView_android_drawableRight) {
                drawable5 = obtainStyledAttributes3.getDrawable(index2);
            } else if (index2 == R.styleable.TextView_android_drawableBottom) {
                drawable6 = obtainStyledAttributes3.getDrawable(index2);
            } else {
                if (index2 == R.styleable.TextView_android_drawableStart) {
                    drawable = obtainStyledAttributes3.getDrawable(index2);
                } else if (index2 == R.styleable.TextView_android_drawableEnd) {
                    drawable4 = obtainStyledAttributes3.getDrawable(index2);
                } else {
                    if (index2 == R.styleable.TextView_android_drawablePadding) {
                        textView.setCompoundDrawablePadding(obtainStyledAttributes3.getDimensionPixelSize(index2, 0));
                    } else if (index2 == R.styleable.TextView_android_maxLines) {
                        textView.setMaxLines(obtainStyledAttributes3.getInt(index2, -1));
                    } else {
                        i6 = indexCount2;
                        if (index2 == R.styleable.TextView_android_maxHeight) {
                            textView.setMaxHeight(obtainStyledAttributes3.getDimensionPixelSize(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_lines) {
                            textView.setLines(obtainStyledAttributes3.getInt(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_height) {
                            textView.setHeight(obtainStyledAttributes3.getDimensionPixelSize(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_minLines) {
                            textView.setMinLines(obtainStyledAttributes3.getInt(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_minHeight) {
                            textView.setMinHeight(obtainStyledAttributes3.getDimensionPixelSize(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_maxEms) {
                            textView.setMaxEms(obtainStyledAttributes3.getInt(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_maxWidth) {
                            textView.setMaxWidth(obtainStyledAttributes3.getDimensionPixelSize(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_ems) {
                            textView.setEms(obtainStyledAttributes3.getInt(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_width) {
                            textView.setWidth(obtainStyledAttributes3.getDimensionPixelSize(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_minEms) {
                            textView.setMinEms(obtainStyledAttributes3.getInt(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_minWidth) {
                            textView.setMinWidth(obtainStyledAttributes3.getDimensionPixelSize(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_gravity) {
                            textView.setGravity(obtainStyledAttributes3.getInt(index2, -1));
                        } else if (index2 == R.styleable.TextView_android_scrollHorizontally) {
                            textView.setHorizontallyScrolling(obtainStyledAttributes3.getBoolean(index2, false));
                        } else if (index2 == R.styleable.TextView_android_includeFontPadding) {
                            textView.setIncludeFontPadding(obtainStyledAttributes3.getBoolean(index2, true));
                        } else if (index2 == R.styleable.TextView_android_cursorVisible) {
                            textView.setCursorVisible(obtainStyledAttributes3.getBoolean(index2, true));
                        } else if (index2 == R.styleable.TextView_android_textScaleX) {
                            textView.setTextScaleX(obtainStyledAttributes3.getFloat(index2, 1.0f));
                        } else if (index2 == R.styleable.TextView_android_shadowColor) {
                            i3 = obtainStyledAttributes3.getInt(index2, 0);
                        } else {
                            if (index2 == R.styleable.TextView_android_shadowDx) {
                                f = obtainStyledAttributes3.getFloat(index2, 0.0f);
                            } else if (index2 == R.styleable.TextView_android_shadowDy) {
                                f2 = obtainStyledAttributes3.getFloat(index2, 0.0f);
                            } else if (index2 == R.styleable.TextView_android_shadowRadius) {
                                f4 = obtainStyledAttributes3.getFloat(index2, 0.0f);
                            } else if (index2 == R.styleable.TextView_android_textColorHighlight) {
                                textView.setHighlightColor(obtainStyledAttributes3.getColor(index2, 0));
                            } else if (index2 == R.styleable.TextView_android_textColor) {
                                textView.setTextColor(obtainStyledAttributes3.getColorStateList(index2));
                            } else if (index2 == R.styleable.TextView_android_textColorHint) {
                                textView.setHintTextColor(obtainStyledAttributes3.getColorStateList(index2));
                            } else if (index2 == R.styleable.TextView_android_textColorLink) {
                                textView.setLinkTextColor(obtainStyledAttributes3.getColorStateList(index2));
                            } else if (index2 == R.styleable.TextView_android_textSize) {
                                textView.setTextSize(0, obtainStyledAttributes3.getDimensionPixelSize(index2, 0));
                            } else if (index2 == R.styleable.TextView_android_typeface) {
                                i9 = obtainStyledAttributes3.getInt(index2, -1);
                            } else if (index2 == R.styleable.TextView_android_textStyle) {
                                i10 = obtainStyledAttributes3.getInt(index2, -1);
                            } else {
                                if (index2 == R.styleable.TextView_android_fontFamily) {
                                    string = obtainStyledAttributes3.getString(index2);
                                } else if (index2 == R.styleable.TextView_tv_fontFamily) {
                                    string = obtainStyledAttributes3.getString(index2);
                                } else if (index2 == R.styleable.TextView_android_textAllCaps) {
                                    if (Build.VERSION.SDK_INT >= 14) {
                                        textView.setAllCaps(obtainStyledAttributes3.getBoolean(index2, false));
                                    }
                                } else {
                                    if (index2 == R.styleable.TextView_android_elegantTextHeight) {
                                        if (Build.VERSION.SDK_INT >= 21) {
                                            textView.setElegantTextHeight(obtainStyledAttributes3.getBoolean(index2, false));
                                        }
                                    } else {
                                        if (index2 == R.styleable.TextView_android_letterSpacing) {
                                            if (Build.VERSION.SDK_INT >= 21) {
                                                textView.setLetterSpacing(obtainStyledAttributes3.getFloat(index2, 0.0f));
                                            }
                                        } else if (index2 == R.styleable.TextView_android_fontFeatureSettings && Build.VERSION.SDK_INT >= 21) {
                                            textView.setFontFeatureSettings(obtainStyledAttributes3.getString(index2));
                                        }
                                        i11++;
                                    }
                                    i11++;
                                }
                                str2 = string;
                            }
                            i11++;
                        }
                        i11++;
                    }
                    i6 = indexCount2;
                    i11++;
                }
                i6 = indexCount2;
                z2 = true;
                i11++;
            }
            i6 = indexCount2;
            z = true;
            i11++;
        }
        obtainStyledAttributes3.recycle();
        if (i3 != 0) {
            textView.setShadowLayer(f4, f, f2, i3);
        }
        if (z) {
            Drawable[] compoundDrawables = textView.getCompoundDrawables();
            if (drawable != null) {
                compoundDrawables[0] = drawable;
            } else if (drawable2 != null) {
                compoundDrawables[0] = drawable2;
            }
            if (drawable3 != null) {
                compoundDrawables[1] = drawable3;
            }
            if (drawable4 != null) {
                compoundDrawables[2] = drawable4;
            } else if (drawable5 != null) {
                compoundDrawables[2] = drawable5;
            }
            if (drawable6 != null) {
                compoundDrawables[3] = drawable6;
            }
            textView.setCompoundDrawablesWithIntrinsicBounds(compoundDrawables[0], compoundDrawables[1], compoundDrawables[2], compoundDrawables[3]);
        }
        if (z2 && Build.VERSION.SDK_INT >= 17) {
            Drawable[] compoundDrawablesRelative = textView.getCompoundDrawablesRelative();
            if (drawable != null) {
                compoundDrawablesRelative[0] = drawable;
            }
            if (drawable4 != null) {
                compoundDrawablesRelative[2] = drawable4;
            }
            textView.setCompoundDrawablesRelativeWithIntrinsicBounds(compoundDrawablesRelative[0], compoundDrawablesRelative[1], compoundDrawablesRelative[2], compoundDrawablesRelative[3]);
        }
        if (str2 != null) {
            Typeface load = TypefaceUtil.load(textView.getContext(), str2, i10);
            if (load != null) {
                textView.setTypeface(load);
            }
            typeface = load;
        } else {
            typeface = null;
        }
        if (typeface != null) {
            if (i9 == 1) {
                typeface = Typeface.SANS_SERIF;
            } else if (i9 == 2) {
                typeface = Typeface.SERIF;
            } else if (i9 == 3) {
                typeface = Typeface.MONOSPACE;
            }
            textView.setTypeface(typeface, i10);
        }
        if (textView instanceof AutoCompleteTextView) {
            applyStyle((AutoCompleteTextView) textView, attributeSet, i, i2);
        }
    }

    private static void applyStyle(AutoCompleteTextView autoCompleteTextView, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = autoCompleteTextView.getContext().obtainStyledAttributes(attributeSet, R.styleable.AutoCompleteTextView, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == R.styleable.AutoCompleteTextView_android_completionHint) {
                autoCompleteTextView.setCompletionHint(obtainStyledAttributes.getString(index));
            } else if (index == R.styleable.AutoCompleteTextView_android_completionThreshold) {
                autoCompleteTextView.setThreshold(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == R.styleable.AutoCompleteTextView_android_dropDownAnchor) {
                autoCompleteTextView.setDropDownAnchor(obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.AutoCompleteTextView_android_dropDownHeight) {
                autoCompleteTextView.setDropDownHeight(obtainStyledAttributes.getLayoutDimension(index, -2));
            } else if (index == R.styleable.AutoCompleteTextView_android_dropDownWidth) {
                autoCompleteTextView.setDropDownWidth(obtainStyledAttributes.getLayoutDimension(index, -2));
            } else if (index == R.styleable.AutoCompleteTextView_android_dropDownHorizontalOffset) {
                autoCompleteTextView.setDropDownHorizontalOffset(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.AutoCompleteTextView_android_dropDownVerticalOffset) {
                autoCompleteTextView.setDropDownVerticalOffset(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.AutoCompleteTextView_android_popupBackground) {
                autoCompleteTextView.setDropDownBackgroundDrawable(obtainStyledAttributes.getDrawable(index));
            }
        }
        obtainStyledAttributes.recycle();
    }
}
