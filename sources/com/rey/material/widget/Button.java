package com.rey.material.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class Button extends android.widget.Button implements ThemeManager.OnThemeChangedListener {
    protected int mCurrentStyle;
    private RippleManager mRippleManager;
    protected int mStyleId;

    public Button(Context context) {
        super(context);
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, null, 0, 0);
    }

    public Button(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, 0, 0);
    }

    public Button(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, i, 0);
    }

    public Button(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
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

    protected void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        getRippleManager().onCreate(this, context, attributeSet, i, i2);
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
}
