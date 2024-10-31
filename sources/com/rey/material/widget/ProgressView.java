package com.rey.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.CircularProgressDrawable;
import com.rey.material.drawable.LinearProgressDrawable;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class ProgressView extends View implements ThemeManager.OnThemeChangedListener {
    public static final int MODE_BUFFER = 2;
    public static final int MODE_DETERMINATE = 0;
    public static final int MODE_INDETERMINATE = 1;
    public static final int MODE_QUERY = 3;
    private boolean mAutostart;
    private boolean mCircular;
    protected int mCurrentStyle;
    private Drawable mProgressDrawable;
    private int mProgressId;
    protected int mStyleId;

    public ProgressView(Context context) {
        super(context);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mAutostart = false;
        this.mCircular = true;
        init(context, null, 0, 0);
    }

    public ProgressView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mAutostart = false;
        this.mCircular = true;
        init(context, attributeSet, 0, 0);
    }

    public ProgressView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mAutostart = false;
        this.mCircular = true;
        init(context, attributeSet, i, 0);
    }

    public ProgressView(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mAutostart = false;
        this.mCircular = true;
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
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

    private boolean needCreateProgress(boolean z) {
        if (this.mProgressDrawable == null) {
            return true;
        }
        if (z) {
            return !(r0 instanceof CircularProgressDrawable);
        }
        return !(r0 instanceof LinearProgressDrawable);
    }

    protected void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ProgressView, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        float f = -1.0f;
        boolean z = false;
        float f2 = -1.0f;
        int i3 = -1;
        int i4 = 0;
        for (int i5 = 0; i5 < indexCount; i5++) {
            int index = obtainStyledAttributes.getIndex(i5);
            if (index == R.styleable.ProgressView_pv_autostart) {
                this.mAutostart = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == R.styleable.ProgressView_pv_circular) {
                this.mCircular = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == R.styleable.ProgressView_pv_progressStyle) {
                i4 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.ProgressView_pv_progressMode) {
                i3 = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.ProgressView_pv_progress) {
                f = obtainStyledAttributes.getFloat(index, 0.0f);
            } else if (index == R.styleable.ProgressView_pv_secondaryProgress) {
                f2 = obtainStyledAttributes.getFloat(index, 0.0f);
            }
        }
        obtainStyledAttributes.recycle();
        if (needCreateProgress(this.mCircular)) {
            this.mProgressId = i4;
            if (i4 == 0) {
                this.mProgressId = this.mCircular ? R.style.Material_Drawable_CircularProgress : R.style.Material_Drawable_LinearProgress;
            }
            Object obj = this.mProgressDrawable;
            if (obj != null && ((Animatable) obj).isRunning()) {
                z = true;
            }
            Drawable build = this.mCircular ? new CircularProgressDrawable.Builder(context, this.mProgressId).build() : new LinearProgressDrawable.Builder(context, this.mProgressId).build();
            this.mProgressDrawable = build;
            ViewUtil.setBackground(this, build);
        } else if (this.mProgressId != i4) {
            this.mProgressId = i4;
            Drawable drawable = this.mProgressDrawable;
            if (drawable instanceof CircularProgressDrawable) {
                ((CircularProgressDrawable) drawable).applyStyle(context, i4);
            } else {
                ((LinearProgressDrawable) drawable).applyStyle(context, i4);
            }
        }
        if (i3 >= 0) {
            Drawable drawable2 = this.mProgressDrawable;
            if (drawable2 instanceof CircularProgressDrawable) {
                ((CircularProgressDrawable) drawable2).setProgressMode(i3);
            } else {
                ((LinearProgressDrawable) drawable2).setProgressMode(i3);
            }
        }
        if (f >= 0.0f) {
            setProgress(f);
        }
        if (f2 >= 0.0f) {
            setSecondaryProgress(f2);
        }
        if (z) {
            start();
        }
    }

    @Override // com.rey.material.app.ThemeManager.OnThemeChangedListener
    public void onThemeChanged(ThemeManager.OnThemeChangedEvent onThemeChangedEvent) {
        int currentStyle = ThemeManager.getInstance().getCurrentStyle(this.mStyleId);
        if (this.mCurrentStyle != currentStyle) {
            this.mCurrentStyle = currentStyle;
            applyStyle(currentStyle);
        }
    }

    @Override // android.view.View
    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (view == this && this.mAutostart) {
            if (i == 8 || i == 4) {
                stop();
            } else {
                start();
            }
        }
    }

    @Override // android.view.View
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (getVisibility() == 0 && this.mAutostart) {
            start();
        }
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().registerOnThemeChangedListener(this);
            onThemeChanged(null);
        }
    }

    @Override // android.view.View
    protected void onDetachedFromWindow() {
        if (this.mAutostart) {
            stop();
        }
        super.onDetachedFromWindow();
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().unregisterOnThemeChangedListener(this);
        }
    }

    public int getProgressMode() {
        if (this.mCircular) {
            return ((CircularProgressDrawable) this.mProgressDrawable).getProgressMode();
        }
        return ((LinearProgressDrawable) this.mProgressDrawable).getProgressMode();
    }

    public float getProgress() {
        if (this.mCircular) {
            return ((CircularProgressDrawable) this.mProgressDrawable).getProgress();
        }
        return ((LinearProgressDrawable) this.mProgressDrawable).getProgress();
    }

    public float getSecondaryProgress() {
        if (this.mCircular) {
            return ((CircularProgressDrawable) this.mProgressDrawable).getSecondaryProgress();
        }
        return ((LinearProgressDrawable) this.mProgressDrawable).getSecondaryProgress();
    }

    public void setProgress(float f) {
        if (this.mCircular) {
            ((CircularProgressDrawable) this.mProgressDrawable).setProgress(f);
        } else {
            ((LinearProgressDrawable) this.mProgressDrawable).setProgress(f);
        }
    }

    public void setSecondaryProgress(float f) {
        if (this.mCircular) {
            ((CircularProgressDrawable) this.mProgressDrawable).setSecondaryProgress(f);
        } else {
            ((LinearProgressDrawable) this.mProgressDrawable).setSecondaryProgress(f);
        }
    }

    public void start() {
        Object obj = this.mProgressDrawable;
        if (obj != null) {
            ((Animatable) obj).start();
        }
    }

    public void stop() {
        Object obj = this.mProgressDrawable;
        if (obj != null) {
            ((Animatable) obj).stop();
        }
    }
}
