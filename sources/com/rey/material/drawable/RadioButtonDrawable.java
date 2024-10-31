package com.rey.material.drawable;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import com.rey.material.util.ColorUtil;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class RadioButtonDrawable extends Drawable implements Animatable {
    private int mAnimDuration;
    private boolean mAnimEnable;
    private float mAnimProgress;
    private boolean mChecked;
    private int mCurColor;
    private int mHeight;
    private boolean mInEditMode;
    private int mInnerRadius;
    private Paint mPaint;
    private int mPrevColor;
    private int mRadius;
    private boolean mRunning;
    private long mStartTime;
    private ColorStateList mStrokeColor;
    private int mStrokeSize;
    private final Runnable mUpdater;
    private int mWidth;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    private RadioButtonDrawable(int i, int i2, int i3, ColorStateList colorStateList, int i4, int i5, int i6) {
        this.mRunning = false;
        this.mChecked = false;
        this.mInEditMode = false;
        this.mAnimEnable = true;
        this.mUpdater = new Runnable() { // from class: com.rey.material.drawable.RadioButtonDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                RadioButtonDrawable.this.update();
            }
        };
        this.mAnimDuration = i6;
        this.mStrokeSize = i3;
        this.mWidth = i;
        this.mHeight = i2;
        this.mRadius = i4;
        this.mInnerRadius = i5;
        this.mStrokeColor = colorStateList;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
    }

    public void setInEditMode(boolean z) {
        this.mInEditMode = z;
    }

    public void setAnimEnable(boolean z) {
        this.mAnimEnable = z;
    }

    public boolean isAnimEnable() {
        return this.mAnimEnable;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return this.mWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return this.mHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        return this.mWidth;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        return this.mHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mChecked) {
            drawChecked(canvas);
        } else {
            drawUnchecked(canvas);
        }
    }

    private void drawChecked(Canvas canvas) {
        float exactCenterX = getBounds().exactCenterX();
        float exactCenterY = getBounds().exactCenterY();
        if (isRunning()) {
            int i = this.mStrokeSize;
            float f = i / 2.0f;
            int i2 = this.mRadius;
            int i3 = this.mInnerRadius;
            float f2 = (i2 - f) / ((((i2 - f) + i2) - i) - i3);
            float f3 = this.mAnimProgress;
            if (f3 < f2) {
                float f4 = f3 / f2;
                float f5 = 1.0f - f4;
                float f6 = i2 + (f * f5);
                float f7 = (i2 - f) * f5;
                this.mPaint.setColor(ColorUtil.getMiddleColor(this.mPrevColor, this.mCurColor, f4));
                this.mPaint.setStrokeWidth(f6 - f7);
                this.mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(exactCenterX, exactCenterY, (f6 + f7) / 2.0f, this.mPaint);
                return;
            }
            float f8 = (f3 - f2) / (1.0f - f2);
            this.mPaint.setColor(this.mCurColor);
            this.mPaint.setStyle(Paint.Style.FILL);
            canvas.drawCircle(exactCenterX, exactCenterY, ((i2 - i) * (1.0f - f8)) + (i3 * f8), this.mPaint);
            this.mPaint.setStrokeWidth(this.mStrokeSize);
            this.mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(exactCenterX, exactCenterY, (this.mRadius + (f8 * f)) - f, this.mPaint);
            return;
        }
        this.mPaint.setColor(this.mCurColor);
        this.mPaint.setStrokeWidth(this.mStrokeSize);
        this.mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(exactCenterX, exactCenterY, this.mRadius, this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(exactCenterX, exactCenterY, this.mInnerRadius, this.mPaint);
    }

    private void drawUnchecked(Canvas canvas) {
        float exactCenterX = getBounds().exactCenterX();
        float exactCenterY = getBounds().exactCenterY();
        if (isRunning()) {
            int i = this.mStrokeSize;
            float f = i / 2.0f;
            int i2 = this.mRadius;
            int i3 = this.mInnerRadius;
            float f2 = ((i2 - i) - i3) / ((((i2 - f) + i2) - i) - i3);
            float f3 = this.mAnimProgress;
            if (f3 < f2) {
                float f4 = f3 / f2;
                float f5 = 1.0f - f4;
                this.mPaint.setColor(ColorUtil.getMiddleColor(this.mPrevColor, this.mCurColor, f4));
                this.mPaint.setStyle(Paint.Style.FILL);
                canvas.drawCircle(exactCenterX, exactCenterY, ((i2 - i) * f4) + (i3 * f5), this.mPaint);
                this.mPaint.setStrokeWidth(this.mStrokeSize);
                this.mPaint.setStyle(Paint.Style.STROKE);
                canvas.drawCircle(exactCenterX, exactCenterY, (this.mRadius + (f5 * f)) - f, this.mPaint);
                return;
            }
            float f6 = (f3 - f2) / (1.0f - f2);
            float f7 = i2 + (f * f6);
            float f8 = (i2 - f) * f6;
            this.mPaint.setColor(this.mCurColor);
            this.mPaint.setStrokeWidth(f7 - f8);
            this.mPaint.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(exactCenterX, exactCenterY, (f7 + f8) / 2.0f, this.mPaint);
            return;
        }
        this.mPaint.setColor(this.mCurColor);
        this.mPaint.setStrokeWidth(this.mStrokeSize);
        this.mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(exactCenterX, exactCenterY, this.mRadius, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        boolean z;
        boolean hasState = ViewUtil.hasState(iArr, R.attr.state_checked);
        int colorForState = this.mStrokeColor.getColorForState(iArr, this.mCurColor);
        if (this.mChecked != hasState) {
            this.mChecked = hasState;
            if (!this.mInEditMode && this.mAnimEnable) {
                start();
            }
            z = true;
        } else {
            z = false;
        }
        if (this.mCurColor != colorForState) {
            this.mPrevColor = isRunning() ? this.mCurColor : colorForState;
            this.mCurColor = colorForState;
            return true;
        }
        if (!isRunning()) {
            this.mPrevColor = colorForState;
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    private void resetAnimation() {
        this.mStartTime = SystemClock.uptimeMillis();
        this.mAnimProgress = 0.0f;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        resetAnimation();
        scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        this.mRunning = false;
        unscheduleSelf(this.mUpdater);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mRunning;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        this.mRunning = true;
        super.scheduleSelf(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mAnimDuration);
        this.mAnimProgress = min;
        if (min == 1.0f) {
            this.mRunning = false;
        }
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
        invalidateSelf();
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mAnimDuration;
        private int mHeight;
        private int mInnerRadius;
        private int mRadius;
        private ColorStateList mStrokeColor;
        private int mStrokeSize;
        private int mWidth;

        public Builder() {
            this.mAnimDuration = 400;
            this.mStrokeSize = 4;
            this.mWidth = 64;
            this.mHeight = 64;
            this.mRadius = 18;
            this.mInnerRadius = 10;
        }

        public Builder(Context context, int i) {
            this(context, null, 0, i);
        }

        public Builder(Context context, AttributeSet attributeSet, int i, int i2) {
            this.mAnimDuration = 400;
            this.mStrokeSize = 4;
            this.mWidth = 64;
            this.mHeight = 64;
            this.mRadius = 18;
            this.mInnerRadius = 10;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.rey.material.R.styleable.RadioButtonDrawable, i, i2);
            width(obtainStyledAttributes.getDimensionPixelSize(com.rey.material.R.styleable.RadioButtonDrawable_rbd_width, ThemeUtil.dpToPx(context, 32)));
            height(obtainStyledAttributes.getDimensionPixelSize(com.rey.material.R.styleable.RadioButtonDrawable_rbd_height, ThemeUtil.dpToPx(context, 32)));
            strokeSize(obtainStyledAttributes.getDimensionPixelSize(com.rey.material.R.styleable.RadioButtonDrawable_rbd_strokeSize, ThemeUtil.dpToPx(context, 2)));
            radius(obtainStyledAttributes.getDimensionPixelSize(com.rey.material.R.styleable.RadioButtonDrawable_rbd_radius, ThemeUtil.dpToPx(context, 10)));
            innerRadius(obtainStyledAttributes.getDimensionPixelSize(com.rey.material.R.styleable.RadioButtonDrawable_rbd_innerRadius, ThemeUtil.dpToPx(context, 5)));
            strokeColor(obtainStyledAttributes.getColorStateList(com.rey.material.R.styleable.RadioButtonDrawable_rbd_strokeColor));
            animDuration(obtainStyledAttributes.getInt(com.rey.material.R.styleable.RadioButtonDrawable_rbd_animDuration, context.getResources().getInteger(R.integer.config_mediumAnimTime)));
            obtainStyledAttributes.recycle();
            if (this.mStrokeColor == null) {
                strokeColor(new ColorStateList(new int[][]{new int[]{-16842912}, new int[]{R.attr.state_checked}}, new int[]{ThemeUtil.colorControlNormal(context, -16777216), ThemeUtil.colorControlActivated(context, -16777216)}));
            }
        }

        public RadioButtonDrawable build() {
            if (this.mStrokeColor == null) {
                this.mStrokeColor = ColorStateList.valueOf(-16777216);
            }
            return new RadioButtonDrawable(this.mWidth, this.mHeight, this.mStrokeSize, this.mStrokeColor, this.mRadius, this.mInnerRadius, this.mAnimDuration);
        }

        public Builder width(int i) {
            this.mWidth = i;
            return this;
        }

        public Builder height(int i) {
            this.mHeight = i;
            return this;
        }

        public Builder strokeSize(int i) {
            this.mStrokeSize = i;
            return this;
        }

        public Builder strokeColor(int i) {
            this.mStrokeColor = ColorStateList.valueOf(i);
            return this;
        }

        public Builder strokeColor(ColorStateList colorStateList) {
            this.mStrokeColor = colorStateList;
            return this;
        }

        public Builder radius(int i) {
            this.mRadius = i;
            return this;
        }

        public Builder innerRadius(int i) {
            this.mInnerRadius = i;
            return this;
        }

        public Builder animDuration(int i) {
            this.mAnimDuration = i;
            return this;
        }
    }
}
