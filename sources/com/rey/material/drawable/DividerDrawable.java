package com.rey.material.drawable;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class DividerDrawable extends Drawable implements Animatable {
    private int mAnimDuration;
    private boolean mAnimEnable;
    private float mAnimProgress;
    private ColorStateList mColorStateList;
    private int mCurColor;
    private boolean mEnable;
    private int mHeight;
    private boolean mInEditMode;
    private int mPaddingLeft;
    private int mPaddingRight;
    private Paint mPaint;
    private Path mPath;
    private PathEffect mPathEffect;
    private int mPrevColor;
    private boolean mRunning;
    private long mStartTime;
    private final Runnable mUpdater;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    public DividerDrawable(int i, ColorStateList colorStateList, int i2) {
        this(i, 0, 0, colorStateList, i2);
    }

    public DividerDrawable(int i, int i2, int i3, ColorStateList colorStateList, int i4) {
        this.mRunning = false;
        this.mEnable = true;
        this.mInEditMode = false;
        this.mAnimEnable = true;
        this.mUpdater = new Runnable() { // from class: com.rey.material.drawable.DividerDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                DividerDrawable.this.update();
            }
        };
        this.mHeight = i;
        this.mPaddingLeft = i2;
        this.mPaddingRight = i3;
        this.mAnimDuration = i4;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mHeight);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mPath = new Path();
        this.mAnimEnable = false;
        setColor(colorStateList);
        this.mAnimEnable = true;
    }

    public void setDividerHeight(int i) {
        if (this.mHeight != i) {
            this.mHeight = i;
            this.mPaint.setStrokeWidth(i);
            invalidateSelf();
        }
    }

    public int getDividerHeight() {
        return this.mHeight;
    }

    public void setPadding(int i, int i2) {
        if (this.mPaddingLeft == i && this.mPaddingRight == i2) {
            return;
        }
        this.mPaddingLeft = i;
        this.mPaddingRight = i2;
        invalidateSelf();
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public void setInEditMode(boolean z) {
        this.mInEditMode = z;
    }

    public void setAnimEnable(boolean z) {
        this.mAnimEnable = z;
    }

    public void setColor(ColorStateList colorStateList) {
        this.mColorStateList = colorStateList;
        onStateChange(getState());
    }

    public void setAnimationDuration(int i) {
        this.mAnimDuration = i;
    }

    private PathEffect getPathEffect() {
        if (this.mPathEffect == null) {
            this.mPathEffect = new DashPathEffect(new float[]{0.2f, this.mHeight * 2}, 0.0f);
        }
        return this.mPathEffect;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mHeight == 0) {
            return;
        }
        Rect bounds = getBounds();
        float f = bounds.bottom - (this.mHeight / 2);
        if (!isRunning()) {
            this.mPath.reset();
            this.mPath.moveTo(bounds.left + this.mPaddingLeft, f);
            this.mPath.lineTo(bounds.right - this.mPaddingRight, f);
            this.mPaint.setPathEffect(this.mEnable ? null : getPathEffect());
            this.mPaint.setColor(this.mCurColor);
            canvas.drawPath(this.mPath, this.mPaint);
            return;
        }
        float f2 = (((bounds.right + bounds.left) - this.mPaddingRight) + this.mPaddingLeft) / 2.0f;
        float f3 = (1.0f - this.mAnimProgress) * f2;
        float f4 = bounds.left + this.mPaddingLeft;
        float f5 = this.mAnimProgress;
        float f6 = f3 + (f4 * f5);
        float f7 = (f2 * (1.0f - f5)) + ((bounds.right + this.mPaddingRight) * this.mAnimProgress);
        this.mPaint.setPathEffect(null);
        if (this.mAnimProgress < 1.0f) {
            this.mPaint.setColor(this.mPrevColor);
            this.mPath.reset();
            this.mPath.moveTo(bounds.left + this.mPaddingLeft, f);
            this.mPath.lineTo(f6, f);
            this.mPath.moveTo(bounds.right - this.mPaddingRight, f);
            this.mPath.lineTo(f7, f);
            canvas.drawPath(this.mPath, this.mPaint);
        }
        this.mPaint.setColor(this.mCurColor);
        this.mPath.reset();
        this.mPath.moveTo(f6, f);
        this.mPath.lineTo(f7, f);
        canvas.drawPath(this.mPath, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        this.mEnable = ViewUtil.hasState(iArr, R.attr.state_enabled);
        int colorForState = this.mColorStateList.getColorForState(iArr, this.mCurColor);
        if (this.mCurColor != colorForState) {
            if (!this.mInEditMode && this.mAnimEnable && this.mEnable && this.mAnimDuration > 0) {
                this.mPrevColor = isRunning() ? this.mPrevColor : this.mCurColor;
                this.mCurColor = colorForState;
                start();
                return true;
            }
            this.mPrevColor = colorForState;
            this.mCurColor = colorForState;
            return true;
        }
        if (isRunning()) {
            return false;
        }
        this.mPrevColor = colorForState;
        return false;
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
}
