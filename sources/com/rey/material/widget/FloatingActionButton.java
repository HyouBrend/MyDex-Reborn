package com.rey.material.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.LineMorphingDrawable;
import com.rey.material.drawable.OvalShadowDrawable;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class FloatingActionButton extends View implements ThemeManager.OnThemeChangedListener {
    private int mAnimDuration;
    private OvalShadowDrawable mBackground;
    protected int mCurrentStyle;
    private Drawable mIcon;
    private int mIconSize;
    private Interpolator mInterpolator;
    private Drawable mPrevIcon;
    private RippleManager mRippleManager;
    protected int mStyleId;
    private SwitchIconAnimator mSwitchIconAnimator;

    public static FloatingActionButton make(Context context, int i) {
        return new FloatingActionButton(context, null, i);
    }

    public FloatingActionButton(Context context) {
        super(context);
        this.mAnimDuration = -1;
        this.mIconSize = -1;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, null, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mAnimDuration = -1;
        this.mIconSize = -1;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, 0, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mAnimDuration = -1;
        this.mIconSize = -1;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, i, 0);
    }

    public FloatingActionButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mAnimDuration = -1;
        this.mIconSize = -1;
        this.mCurrentStyle = Integer.MIN_VALUE;
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        setClickable(true);
        this.mSwitchIconAnimator = new SwitchIconAnimator();
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
        int resourceId;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.FloatingActionButton, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = 0;
        ColorStateList colorStateList = null;
        int i7 = 0;
        int i8 = 0;
        while (i6 < indexCount) {
            int index = obtainStyledAttributes.getIndex(i6);
            int i9 = indexCount;
            if (index == R.styleable.FloatingActionButton_fab_radius) {
                i3 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_elevation) {
                i4 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_backgroundColor) {
                colorStateList = obtainStyledAttributes.getColorStateList(index);
            } else if (index == R.styleable.FloatingActionButton_fab_backgroundAnimDuration) {
                i5 = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_iconSrc) {
                i8 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_iconLineMorphing) {
                i7 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_iconSize) {
                this.mIconSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_animDuration) {
                this.mAnimDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.FloatingActionButton_fab_interpolator && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.FloatingActionButton_fab_interpolator, 0)) != 0) {
                this.mInterpolator = AnimationUtils.loadInterpolator(context, resourceId);
            }
            i6++;
            indexCount = i9;
        }
        obtainStyledAttributes.recycle();
        if (this.mIconSize < 0) {
            this.mIconSize = ThemeUtil.dpToPx(context, 24);
        }
        if (this.mAnimDuration < 0) {
            this.mAnimDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
        }
        if (this.mInterpolator == null) {
            this.mInterpolator = new DecelerateInterpolator();
        }
        OvalShadowDrawable ovalShadowDrawable = this.mBackground;
        if (ovalShadowDrawable == null) {
            if (i3 < 0) {
                i3 = ThemeUtil.dpToPx(context, 28);
            }
            int i10 = i3;
            if (i4 < 0) {
                i4 = ThemeUtil.dpToPx(context, 4);
            }
            if (colorStateList == null) {
                colorStateList = ColorStateList.valueOf(ThemeUtil.colorAccent(context, 0));
            }
            float f = i4;
            OvalShadowDrawable ovalShadowDrawable2 = new OvalShadowDrawable(i10, colorStateList, f, f, i5 < 0 ? 0 : i5);
            this.mBackground = ovalShadowDrawable2;
            ovalShadowDrawable2.setInEditMode(isInEditMode());
            this.mBackground.setBounds(0, 0, getWidth(), getHeight());
            this.mBackground.setCallback(this);
        } else {
            if (i3 >= 0) {
                ovalShadowDrawable.setRadius(i3);
            }
            if (colorStateList != null) {
                this.mBackground.setColor(colorStateList);
            }
            if (i4 >= 0) {
                float f2 = i4;
                this.mBackground.setShadow(f2, f2);
            }
            if (i5 >= 0) {
                this.mBackground.setAnimationDuration(i5);
            }
        }
        if (i7 != 0) {
            setIcon(new LineMorphingDrawable.Builder(context, i7).build(), false);
        } else if (i8 != 0) {
            setIcon(context.getResources().getDrawable(i8), false);
        }
        getRippleManager().onCreate(this, context, attributeSet, i, i2);
        Drawable background = getBackground();
        if (background == null || !(background instanceof RippleDrawable)) {
            return;
        }
        RippleDrawable rippleDrawable = (RippleDrawable) background;
        rippleDrawable.setBackgroundDrawable(null);
        rippleDrawable.setMask(1, 0, 0, 0, 0, (int) this.mBackground.getPaddingLeft(), (int) this.mBackground.getPaddingTop(), (int) this.mBackground.getPaddingRight(), (int) this.mBackground.getPaddingBottom());
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

    public int getRadius() {
        return this.mBackground.getRadius();
    }

    public void setRadius(int i) {
        if (this.mBackground.setRadius(i)) {
            requestLayout();
        }
    }

    @Override // android.view.View
    public float getElevation() {
        if (Build.VERSION.SDK_INT >= 21) {
            return super.getElevation();
        }
        return this.mBackground.getShadowSize();
    }

    @Override // android.view.View
    public void setElevation(float f) {
        if (Build.VERSION.SDK_INT >= 21) {
            super.setElevation(f);
        } else if (this.mBackground.setShadow(f, f)) {
            requestLayout();
        }
    }

    public int getLineMorphingState() {
        Drawable drawable = this.mIcon;
        if (drawable == null || !(drawable instanceof LineMorphingDrawable)) {
            return -1;
        }
        return ((LineMorphingDrawable) drawable).getLineState();
    }

    public void setLineMorphingState(int i, boolean z) {
        Drawable drawable = this.mIcon;
        if (drawable == null || !(drawable instanceof LineMorphingDrawable)) {
            return;
        }
        ((LineMorphingDrawable) drawable).switchLineState(i, z);
    }

    public ColorStateList getBackgroundColor() {
        return this.mBackground.getColor();
    }

    public Drawable getIcon() {
        return this.mIcon;
    }

    public void setIcon(Drawable drawable, boolean z) {
        if (drawable == null) {
            return;
        }
        if (z) {
            this.mSwitchIconAnimator.startAnimation(drawable);
            invalidate();
            return;
        }
        Drawable drawable2 = this.mIcon;
        if (drawable2 != null) {
            drawable2.setCallback(null);
            unscheduleDrawable(this.mIcon);
        }
        this.mIcon = drawable;
        float f = this.mIconSize / 2.0f;
        drawable.setBounds((int) (this.mBackground.getCenterX() - f), (int) (this.mBackground.getCenterY() - f), (int) (this.mBackground.getCenterX() + f), (int) (this.mBackground.getCenterY() + f));
        this.mIcon.setCallback(this);
        invalidate();
    }

    public void setBackgroundColor(ColorStateList colorStateList) {
        this.mBackground.setColor(colorStateList);
        invalidate();
    }

    @Override // android.view.View
    public void setBackgroundColor(int i) {
        this.mBackground.setColor(i);
        invalidate();
    }

    public void show(Activity activity, int i, int i2, int i3) {
        if (getParent() == null) {
            FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(this.mBackground.getIntrinsicWidth(), this.mBackground.getIntrinsicHeight());
            updateParams(i, i2, i3, layoutParams);
            activity.getWindow().addContentView(this, layoutParams);
            return;
        }
        updateLocation(i, i2, i3);
    }

    public void show(ViewGroup viewGroup, int i, int i2, int i3) {
        if (getParent() == null) {
            ViewGroup.LayoutParams generateLayoutParams = viewGroup.generateLayoutParams((AttributeSet) null);
            generateLayoutParams.width = this.mBackground.getIntrinsicWidth();
            generateLayoutParams.height = this.mBackground.getIntrinsicHeight();
            updateParams(i, i2, i3, generateLayoutParams);
            viewGroup.addView(this, generateLayoutParams);
            return;
        }
        updateLocation(i, i2, i3);
    }

    public void updateLocation(int i, int i2, int i3) {
        if (getParent() != null) {
            updateParams(i, i2, i3, getLayoutParams());
        } else {
            Log.v("FloatingActionButton", "updateLocation() is called without parent");
        }
    }

    private void updateParams(int i, int i2, int i3, ViewGroup.LayoutParams layoutParams) {
        int i4 = i3 & 7;
        if (i4 == 1) {
            setLeftMargin(layoutParams, (int) (i - this.mBackground.getCenterX()));
        } else if (i4 == 3) {
            setLeftMargin(layoutParams, (int) (i - this.mBackground.getPaddingLeft()));
        } else if (i4 == 5) {
            setLeftMargin(layoutParams, (int) ((i - this.mBackground.getPaddingLeft()) - (this.mBackground.getRadius() * 2)));
        } else {
            setLeftMargin(layoutParams, (int) (i - this.mBackground.getPaddingLeft()));
        }
        int i5 = i3 & 112;
        if (i5 == 16) {
            setTopMargin(layoutParams, (int) (i2 - this.mBackground.getCenterY()));
        } else if (i5 == 48) {
            setTopMargin(layoutParams, (int) (i2 - this.mBackground.getPaddingTop()));
        } else if (i5 == 80) {
            setTopMargin(layoutParams, (int) ((i2 - this.mBackground.getPaddingTop()) - (this.mBackground.getRadius() * 2)));
        } else {
            setTopMargin(layoutParams, (int) (i2 - this.mBackground.getPaddingTop()));
        }
        setLayoutParams(layoutParams);
    }

    private void setLeftMargin(ViewGroup.LayoutParams layoutParams, int i) {
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).leftMargin = i;
            return;
        }
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).leftMargin = i;
            return;
        }
        Log.v("FloatingActionButton", "cannot recognize LayoutParams: " + layoutParams);
    }

    private void setTopMargin(ViewGroup.LayoutParams layoutParams, int i) {
        if (layoutParams instanceof FrameLayout.LayoutParams) {
            ((FrameLayout.LayoutParams) layoutParams).topMargin = i;
            return;
        }
        if (layoutParams instanceof RelativeLayout.LayoutParams) {
            ((RelativeLayout.LayoutParams) layoutParams).topMargin = i;
            return;
        }
        Log.v("FloatingActionButton", "cannot recognize LayoutParams: " + layoutParams);
    }

    public void dismiss() {
        if (getParent() != null) {
            ((ViewGroup) getParent()).removeView(this);
        }
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || this.mBackground == drawable || this.mIcon == drawable || this.mPrevIcon == drawable;
    }

    @Override // android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        OvalShadowDrawable ovalShadowDrawable = this.mBackground;
        if (ovalShadowDrawable != null) {
            ovalShadowDrawable.setState(getDrawableState());
        }
        Drawable drawable = this.mIcon;
        if (drawable != null) {
            drawable.setState(getDrawableState());
        }
        Drawable drawable2 = this.mPrevIcon;
        if (drawable2 != null) {
            drawable2.setState(getDrawableState());
        }
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.mBackground.getIntrinsicWidth(), this.mBackground.getIntrinsicHeight());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mBackground.setBounds(0, 0, i, i2);
        Drawable drawable = this.mIcon;
        if (drawable != null) {
            float f = this.mIconSize / 2.0f;
            drawable.setBounds((int) (this.mBackground.getCenterX() - f), (int) (this.mBackground.getCenterY() - f), (int) (this.mBackground.getCenterX() + f), (int) (this.mBackground.getCenterY() + f));
        }
        Drawable drawable2 = this.mPrevIcon;
        if (drawable2 != null) {
            float f2 = this.mIconSize / 2.0f;
            drawable2.setBounds((int) (this.mBackground.getCenterX() - f2), (int) (this.mBackground.getCenterY() - f2), (int) (this.mBackground.getCenterX() + f2), (int) (this.mBackground.getCenterY() + f2));
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        this.mBackground.draw(canvas);
        super.draw(canvas);
        Drawable drawable = this.mPrevIcon;
        if (drawable != null) {
            drawable.draw(canvas);
        }
        Drawable drawable2 = this.mIcon;
        if (drawable2 != null) {
            drawable2.draw(canvas);
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

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() != 0 || this.mBackground.isPointerOver(motionEvent.getX(), motionEvent.getY())) {
            return getRippleManager().onTouchEvent(this, motionEvent) || super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.state = getLineMorphingState();
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        if (savedState.state >= 0) {
            setLineMorphingState(savedState.state, false);
        }
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.rey.material.widget.FloatingActionButton.SavedState.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState createFromParcel(Parcel parcel) {
                return new SavedState(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public SavedState[] newArray(int i) {
                return new SavedState[i];
            }
        };
        int state;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.state = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.state);
        }

        public String toString() {
            return "FloatingActionButton.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " state=" + this.state + "}";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class SwitchIconAnimator implements Runnable {
        boolean mRunning = false;
        long mStartTime;

        SwitchIconAnimator() {
        }

        public void resetAnimation() {
            this.mStartTime = SystemClock.uptimeMillis();
            FloatingActionButton.this.mIcon.setAlpha(0);
            FloatingActionButton.this.mPrevIcon.setAlpha(255);
        }

        public boolean startAnimation(Drawable drawable) {
            if (FloatingActionButton.this.mIcon == drawable) {
                return false;
            }
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            floatingActionButton.mPrevIcon = floatingActionButton.mIcon;
            FloatingActionButton.this.mIcon = drawable;
            float f = FloatingActionButton.this.mIconSize / 2.0f;
            FloatingActionButton.this.mIcon.setBounds((int) (FloatingActionButton.this.mBackground.getCenterX() - f), (int) (FloatingActionButton.this.mBackground.getCenterY() - f), (int) (FloatingActionButton.this.mBackground.getCenterX() + f), (int) (FloatingActionButton.this.mBackground.getCenterY() + f));
            FloatingActionButton.this.mIcon.setCallback(FloatingActionButton.this);
            if (FloatingActionButton.this.getHandler() == null) {
                FloatingActionButton.this.mPrevIcon.setCallback(null);
                FloatingActionButton floatingActionButton2 = FloatingActionButton.this;
                floatingActionButton2.unscheduleDrawable(floatingActionButton2.mPrevIcon);
                FloatingActionButton.this.mPrevIcon = null;
            } else {
                resetAnimation();
                this.mRunning = true;
                FloatingActionButton.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
            }
            FloatingActionButton.this.invalidate();
            return true;
        }

        public void stopAnimation() {
            this.mRunning = false;
            FloatingActionButton.this.mPrevIcon.setCallback(null);
            FloatingActionButton floatingActionButton = FloatingActionButton.this;
            floatingActionButton.unscheduleDrawable(floatingActionButton.mPrevIcon);
            FloatingActionButton.this.mPrevIcon = null;
            FloatingActionButton.this.mIcon.setAlpha(255);
            if (FloatingActionButton.this.getHandler() != null) {
                FloatingActionButton.this.getHandler().removeCallbacks(this);
            }
            FloatingActionButton.this.invalidate();
        }

        @Override // java.lang.Runnable
        public void run() {
            float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / FloatingActionButton.this.mAnimDuration);
            float interpolation = FloatingActionButton.this.mInterpolator.getInterpolation(min);
            FloatingActionButton.this.mIcon.setAlpha(Math.round(interpolation * 255.0f));
            FloatingActionButton.this.mPrevIcon.setAlpha(Math.round((1.0f - interpolation) * 255.0f));
            if (min == 1.0f) {
                stopAnimation();
            }
            if (this.mRunning) {
                if (FloatingActionButton.this.getHandler() != null) {
                    FloatingActionButton.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
                } else {
                    stopAnimation();
                }
            }
            FloatingActionButton.this.invalidate();
        }
    }
}
