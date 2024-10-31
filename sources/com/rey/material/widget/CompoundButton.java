package com.rey.material.widget;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.PaddingDrawable;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class CompoundButton extends android.widget.CompoundButton implements ThemeManager.OnThemeChangedListener {
    protected int mCurrentStyle;
    private boolean mIsRtl;
    private volatile PaddingDrawable mPaddingDrawable;
    private RippleManager mRippleManager;
    protected int mStyleId;

    public CompoundButton(Context context) {
        super(context);
        this.mIsRtl = false;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, null, 0, 0);
    }

    public CompoundButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mIsRtl = false;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, 0, 0);
    }

    public CompoundButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mIsRtl = false;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, i, 0);
    }

    public CompoundButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mIsRtl = false;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        if (Build.VERSION.SDK_INT >= 17) {
            applyPadding(context, attributeSet, i, i2);
        }
        setClickable(true);
        ViewUtil.applyFont(this, attributeSet, i, i2);
        applyStyle(context, attributeSet, i, i2);
        if (isInEditMode()) {
            return;
        }
        this.mStyleId = ThemeManager.getStyleId(context, attributeSet, i, i2);
    }

    public void applyStyle(int i) {
        ViewUtil.applyStyle(this, i);
        applyStyle(getContext(), null, 0, i);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        getRippleManager().onCreate(this, context, attributeSet, i, i2);
    }

    private void applyPadding(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, new int[]{R.attr.padding, R.attr.paddingLeft, R.attr.paddingTop, R.attr.paddingRight, R.attr.paddingBottom, R.attr.paddingStart, R.attr.paddingEnd}, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        int i4 = 0;
        int i5 = -1;
        boolean z = false;
        boolean z2 = false;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        int i9 = -1;
        int i10 = Integer.MIN_VALUE;
        int i11 = Integer.MIN_VALUE;
        boolean z3 = false;
        boolean z4 = false;
        while (i4 < indexCount) {
            int index = obtainStyledAttributes.getIndex(i4);
            if (index == 0) {
                i5 = obtainStyledAttributes.getDimensionPixelSize(index, i3);
                z = true;
            } else {
                if (index == 1) {
                    i6 = obtainStyledAttributes.getDimensionPixelSize(index, i3);
                    z = true;
                } else if (index == 2) {
                    i7 = obtainStyledAttributes.getDimensionPixelSize(index, i3);
                } else if (index == 3) {
                    i8 = obtainStyledAttributes.getDimensionPixelSize(index, i3);
                } else if (index == 4) {
                    i9 = obtainStyledAttributes.getDimensionPixelSize(index, i3);
                } else if (index == 5) {
                    if (Build.VERSION.SDK_INT >= 17) {
                        i10 = obtainStyledAttributes.getDimensionPixelSize(index, Integer.MIN_VALUE);
                        z3 = i10 != Integer.MIN_VALUE;
                    }
                } else if (index == 6 && Build.VERSION.SDK_INT >= 17) {
                    i11 = obtainStyledAttributes.getDimensionPixelSize(index, Integer.MIN_VALUE);
                    z4 = i11 != Integer.MIN_VALUE;
                }
                i4++;
                i3 = -1;
            }
            z2 = true;
            i4++;
            i3 = -1;
        }
        obtainStyledAttributes.recycle();
        if (i5 >= 0) {
            setPadding(i5, i5, i5, i5);
            return;
        }
        if (z || z2) {
            if (!z) {
                i6 = getPaddingLeft();
            }
            int paddingTop = i7 >= 0 ? i7 : getPaddingTop();
            if (!z2) {
                i8 = getPaddingRight();
            }
            setPadding(i6, paddingTop, i8, i9 >= 0 ? i9 : getPaddingBottom());
        }
        if (z3 || z4) {
            if (!z3) {
                i10 = getPaddingStart();
            }
            if (i7 < 0) {
                i7 = getPaddingTop();
            }
            if (!z4) {
                i11 = getPaddingEnd();
            }
            if (i9 < 0) {
                i9 = getPaddingBottom();
            }
            setPaddingRelative(i10, i7, i11, i9);
        }
    }

    private PaddingDrawable getPaddingDrawable() {
        if (this.mPaddingDrawable == null) {
            synchronized (this) {
                if (this.mPaddingDrawable == null) {
                    this.mPaddingDrawable = new PaddingDrawable(null);
                }
            }
        }
        return this.mPaddingDrawable;
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        ViewUtil.applyTextAppearance(this, i);
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        ViewUtil.applyTextAppearance(this, i);
    }

    @Override // com.rey.material.app.ThemeManager.OnThemeChangedListener
    public void onThemeChanged(ThemeManager.OnThemeChangedEvent onThemeChangedEvent) {
        int currentStyle = ThemeManager.getInstance().getCurrentStyle(this.mStyleId);
        if (this.mCurrentStyle != currentStyle) {
            this.mCurrentStyle = currentStyle;
            applyStyle(currentStyle);
        }
    }

    @Override // android.widget.TextView, android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().registerOnThemeChangedListener(this);
            onThemeChanged(null);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        RippleManager.cancelRipple(this);
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().unregisterOnThemeChangedListener(this);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public void onRtlPropertiesChanged(int i) {
        boolean z = i == 1;
        if (this.mIsRtl != z) {
            this.mIsRtl = z;
            if (Build.VERSION.SDK_INT >= 17) {
                setPaddingRelative(getPaddingStart(), getPaddingTop(), getPaddingEnd(), getPaddingBottom());
            } else {
                setPadding(getPaddingLeft(), getPaddingTop(), getPaddingRight(), getPaddingBottom());
            }
            setCompoundDrawablePadding(getCompoundDrawablePadding());
            invalidate();
        }
    }

    @Override // android.view.View
    public void setBackgroundDrawable(Drawable drawable) {
        Drawable background = getBackground();
        if ((background instanceof RippleDrawable) && !(drawable instanceof RippleDrawable)) {
            ((RippleDrawable) background).setBackgroundDrawable(drawable);
        } else {
            super.setBackgroundDrawable(drawable);
        }
    }

    protected RippleManager getRippleManager() {
        if (this.mRippleManager == null) {
            synchronized (RippleManager.class) {
                if (this.mRippleManager == null) {
                    this.mRippleManager = new RippleManager();
                }
            }
        }
        return this.mRippleManager;
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener onClickListener) {
        RippleManager rippleManager = getRippleManager();
        if (onClickListener == rippleManager) {
            super.setOnClickListener(onClickListener);
        } else {
            rippleManager.setOnClickListener(onClickListener);
            setOnClickListener(rippleManager);
        }
    }

    @Override // android.widget.TextView, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        return getRippleManager().onTouchEvent(this, motionEvent) || super.onTouchEvent(motionEvent);
    }

    @Override // android.widget.CompoundButton
    public void setButtonDrawable(Drawable drawable) {
        super.setButtonDrawable((Drawable) null);
        getPaddingDrawable().setWrappedDrawable(drawable);
        super.setButtonDrawable(getPaddingDrawable());
    }

    @Override // android.widget.CompoundButton
    public Drawable getButtonDrawable() {
        return getPaddingDrawable().getWrappedDrawable();
    }

    @Override // android.widget.TextView, android.view.View
    public void setPadding(int i, int i2, int i3, int i4) {
        PaddingDrawable paddingDrawable = getPaddingDrawable();
        if (this.mIsRtl) {
            paddingDrawable.setPadding(paddingDrawable.getPaddingLeft(), i2, i3, i4);
        } else {
            paddingDrawable.setPadding(i, i2, paddingDrawable.getPaddingRight(), i4);
        }
        super.setPadding(i, i2, i3, i4);
    }

    @Override // android.widget.TextView, android.view.View
    public void setPaddingRelative(int i, int i2, int i3, int i4) {
        PaddingDrawable paddingDrawable = getPaddingDrawable();
        if (this.mIsRtl) {
            paddingDrawable.setPadding(paddingDrawable.getPaddingLeft(), i2, i, i4);
        } else {
            paddingDrawable.setPadding(i, i2, paddingDrawable.getPaddingRight(), i4);
        }
        super.setPaddingRelative(i, i2, i3, i4);
    }

    @Override // android.widget.TextView
    public void setCompoundDrawablePadding(int i) {
        PaddingDrawable paddingDrawable = getPaddingDrawable();
        if (this.mIsRtl) {
            paddingDrawable.setPadding(i, paddingDrawable.getPaddingTop(), paddingDrawable.getPaddingRight(), paddingDrawable.getPaddingBottom());
        } else {
            paddingDrawable.setPadding(paddingDrawable.getPaddingLeft(), paddingDrawable.getPaddingTop(), i, paddingDrawable.getPaddingBottom());
        }
        super.setCompoundDrawablePadding(i);
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingLeft() {
        if (this.mIsRtl) {
            return getPaddingLeft();
        }
        return getPaddingDrawable().getIntrinsicWidth();
    }

    @Override // android.widget.CompoundButton, android.widget.TextView
    public int getCompoundPaddingRight() {
        if (!this.mIsRtl) {
            return getPaddingRight();
        }
        return getPaddingDrawable().getIntrinsicWidth();
    }
}
