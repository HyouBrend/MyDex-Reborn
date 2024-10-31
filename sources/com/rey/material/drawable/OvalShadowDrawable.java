package com.rey.material.drawable;

import android.R;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import com.rey.material.util.ColorUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class OvalShadowDrawable extends Drawable implements Animatable {
    private static final int COLOR_SHADOW_END = 0;
    private static final int COLOR_SHADOW_START = 1275068416;
    private int mAnimDuration;
    private float mAnimProgress;
    private ColorStateList mColorStateList;
    private int mCurColor;
    private Paint mGlowPaint;
    private Path mGlowPath;
    private Paint mPaint;
    private int mPrevColor;
    private int mRadius;
    private float mShadowOffset;
    private Paint mShadowPaint;
    private Path mShadowPath;
    private float mShadowSize;
    private long mStartTime;
    private boolean mRunning = false;
    private boolean mEnable = true;
    private boolean mInEditMode = false;
    private boolean mAnimEnable = true;
    private RectF mTempRect = new RectF();
    private boolean mNeedBuildShadow = true;
    private final Runnable mUpdater = new Runnable() { // from class: com.rey.material.drawable.OvalShadowDrawable.1
        @Override // java.lang.Runnable
        public void run() {
            OvalShadowDrawable.this.update();
        }
    };

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    public OvalShadowDrawable(int i, ColorStateList colorStateList, float f, float f2, int i2) {
        this.mAnimDuration = i2;
        Paint paint = new Paint(5);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        setColor(colorStateList);
        setRadius(i);
        setShadow(f, f2);
    }

    public boolean setRadius(int i) {
        if (this.mRadius == i) {
            return false;
        }
        this.mRadius = i;
        this.mNeedBuildShadow = true;
        invalidateSelf();
        return true;
    }

    public boolean setShadow(float f, float f2) {
        if (this.mShadowSize == f && this.mShadowOffset == f2) {
            return false;
        }
        this.mShadowSize = f;
        this.mShadowOffset = f2;
        this.mNeedBuildShadow = true;
        invalidateSelf();
        return true;
    }

    public boolean setAnimationDuration(int i) {
        if (this.mAnimDuration == i) {
            return false;
        }
        this.mAnimDuration = i;
        return true;
    }

    public void setColor(ColorStateList colorStateList) {
        this.mColorStateList = colorStateList;
        onStateChange(getState());
    }

    public void setColor(int i) {
        this.mColorStateList = ColorStateList.valueOf(i);
        onStateChange(getState());
    }

    public ColorStateList getColor() {
        return this.mColorStateList;
    }

    public void setInEditMode(boolean z) {
        this.mInEditMode = z;
    }

    public void setAnimEnable(boolean z) {
        this.mAnimEnable = z;
    }

    public int getRadius() {
        return this.mRadius;
    }

    public float getShadowSize() {
        return this.mShadowSize;
    }

    public float getShadowOffset() {
        return this.mShadowOffset;
    }

    public float getPaddingLeft() {
        return this.mShadowSize;
    }

    public float getPaddingTop() {
        return this.mShadowSize;
    }

    public float getPaddingRight() {
        return this.mShadowSize;
    }

    public float getPaddingBottom() {
        return this.mShadowSize + this.mShadowOffset;
    }

    public float getCenterX() {
        return this.mRadius + this.mShadowSize;
    }

    public float getCenterY() {
        return this.mRadius + this.mShadowSize;
    }

    public boolean isPointerOver(float f, float f2) {
        return ((float) Math.sqrt(Math.pow((double) (f - getCenterX()), 2.0d) + Math.pow((double) (f2 - getCenterY()), 2.0d))) < ((float) this.mRadius);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return (int) (((this.mRadius + this.mShadowSize) * 2.0f) + 0.5f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return (int) (((this.mRadius + this.mShadowSize) * 2.0f) + this.mShadowOffset + 0.5f);
    }

    private void buildShadow() {
        if (this.mShadowSize <= 0.0f) {
            return;
        }
        if (this.mShadowPaint == null) {
            Paint paint = new Paint(5);
            this.mShadowPaint = paint;
            paint.setStyle(Paint.Style.FILL);
            this.mShadowPaint.setDither(true);
        }
        int i = this.mRadius;
        this.mShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, this.mShadowSize + this.mRadius, new int[]{COLOR_SHADOW_START, COLOR_SHADOW_START, 0}, new float[]{0.0f, i / ((i + this.mShadowSize) + this.mShadowOffset), 1.0f}, Shader.TileMode.CLAMP));
        Path path = this.mShadowPath;
        if (path == null) {
            Path path2 = new Path();
            this.mShadowPath = path2;
            path2.setFillType(Path.FillType.EVEN_ODD);
        } else {
            path.reset();
        }
        float f = this.mRadius + this.mShadowSize;
        float f2 = -f;
        this.mTempRect.set(f2, f2, f, f);
        this.mShadowPath.addOval(this.mTempRect, Path.Direction.CW);
        float f3 = this.mRadius - 1;
        RectF rectF = this.mTempRect;
        float f4 = -f3;
        float f5 = this.mShadowOffset;
        rectF.set(f4, f4 - f5, f3, f3 - f5);
        this.mShadowPath.addOval(this.mTempRect, Path.Direction.CW);
        if (this.mGlowPaint == null) {
            Paint paint2 = new Paint(5);
            this.mGlowPaint = paint2;
            paint2.setStyle(Paint.Style.FILL);
            this.mGlowPaint.setDither(true);
        }
        int i2 = this.mRadius;
        float f6 = this.mShadowSize;
        this.mGlowPaint.setShader(new RadialGradient(0.0f, 0.0f, this.mRadius + (this.mShadowSize / 2.0f), new int[]{COLOR_SHADOW_START, COLOR_SHADOW_START, 0}, new float[]{0.0f, (i2 - (f6 / 2.0f)) / (i2 + (f6 / 2.0f)), 1.0f}, Shader.TileMode.CLAMP));
        Path path3 = this.mGlowPath;
        if (path3 == null) {
            Path path4 = new Path();
            this.mGlowPath = path4;
            path4.setFillType(Path.FillType.EVEN_ODD);
        } else {
            path3.reset();
        }
        float f7 = this.mRadius + (this.mShadowSize / 2.0f);
        float f8 = -f7;
        this.mTempRect.set(f8, f8, f7, f7);
        this.mGlowPath.addOval(this.mTempRect, Path.Direction.CW);
        float f9 = this.mRadius - 1;
        float f10 = -f9;
        this.mTempRect.set(f10, f10, f9, f9);
        this.mGlowPath.addOval(this.mTempRect, Path.Direction.CW);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (this.mNeedBuildShadow) {
            buildShadow();
            this.mNeedBuildShadow = false;
        }
        if (this.mShadowSize > 0.0f) {
            int save = canvas.save();
            float f = this.mShadowSize;
            int i = this.mRadius;
            canvas.translate(i + f, f + i + this.mShadowOffset);
            canvas.drawPath(this.mShadowPath, this.mShadowPaint);
            canvas.restoreToCount(save);
        }
        int save2 = canvas.save();
        float f2 = this.mShadowSize;
        int i2 = this.mRadius;
        canvas.translate(i2 + f2, f2 + i2);
        if (this.mShadowSize > 0.0f) {
            canvas.drawPath(this.mGlowPath, this.mGlowPaint);
        }
        RectF rectF = this.mTempRect;
        int i3 = this.mRadius;
        rectF.set(-i3, -i3, i3, i3);
        if (!isRunning()) {
            this.mPaint.setColor(this.mCurColor);
        } else {
            this.mPaint.setColor(ColorUtil.getMiddleColor(this.mPrevColor, this.mCurColor, this.mAnimProgress));
        }
        canvas.drawOval(this.mTempRect, this.mPaint);
        canvas.restoreToCount(save2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mShadowPaint.setAlpha(i);
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mShadowPaint.setColorFilter(colorFilter);
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
            invalidateSelf();
            return true;
        }
        if (isRunning()) {
            return false;
        }
        this.mPrevColor = colorForState;
        return false;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        super.jumpToCurrentState();
        stop();
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
