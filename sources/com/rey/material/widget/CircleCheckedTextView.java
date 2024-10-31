package com.rey.material.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.animation.Interpolator;
import com.rey.material.drawable.CircleDrawable;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class CircleCheckedTextView extends android.widget.CheckedTextView {
    private CircleDrawable mBackground;
    private OnCheckedChangeListener mCheckedChangeListener;

    /* loaded from: classes2.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(CircleCheckedTextView circleCheckedTextView, boolean z);
    }

    public CircleCheckedTextView(Context context) {
        super(context);
        init(context, null, 0, 0);
    }

    public CircleCheckedTextView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public CircleCheckedTextView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    public CircleCheckedTextView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        setGravity(17);
        setPadding(0, 0, 0, 0);
        CircleDrawable circleDrawable = new CircleDrawable();
        this.mBackground = circleDrawable;
        circleDrawable.setInEditMode(isInEditMode());
        this.mBackground.setAnimEnable(false);
        ViewUtil.setBackground(this, this.mBackground);
        this.mBackground.setAnimEnable(true);
    }

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // android.widget.TextView
    public void setTextAppearance(int i) {
        ViewUtil.applyTextAppearance(this, i);
    }

    @Override // android.widget.TextView
    public void setTextAppearance(Context context, int i) {
        ViewUtil.applyTextAppearance(this, i);
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mBackground.setColor(i);
    }

    public void setAnimDuration(int i) {
        this.mBackground.setAnimDuration(i);
    }

    public void setInterpolator(Interpolator interpolator, Interpolator interpolator2) {
        this.mBackground.setInterpolator(interpolator, interpolator2);
    }

    @Override // android.widget.CheckedTextView, android.widget.Checkable
    public void setChecked(boolean z) {
        if (isChecked() != z) {
            super.setChecked(z);
            OnCheckedChangeListener onCheckedChangeListener = this.mCheckedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
    }

    public void setCheckedImmediately(boolean z) {
        this.mBackground.setAnimEnable(false);
        setChecked(z);
        this.mBackground.setAnimEnable(true);
    }
}
