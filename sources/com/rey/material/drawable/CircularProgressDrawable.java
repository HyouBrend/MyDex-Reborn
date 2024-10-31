package com.rey.material.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.rey.material.R;
import com.rey.material.util.ColorUtil;
import com.rey.material.util.ThemeUtil;

/* loaded from: classes2.dex */
public class CircularProgressDrawable extends Drawable implements Animatable {
    private static final int PROGRESS_STATE_HIDE = -1;
    private static final int PROGRESS_STATE_KEEP_SHRINK = 3;
    private static final int PROGRESS_STATE_KEEP_STRETCH = 1;
    private static final int PROGRESS_STATE_SHRINK = 2;
    private static final int PROGRESS_STATE_STRETCH = 0;
    private static final int RUN_STATE_RUNNING = 3;
    private static final int RUN_STATE_STARTED = 2;
    private static final int RUN_STATE_STARTING = 1;
    private static final int RUN_STATE_STOPPED = 0;
    private static final int RUN_STATE_STOPPING = 4;
    private int mInAnimationDuration;
    private int[] mInColors;
    private float mInStepPercent;
    private float mInitialAngle;
    private int mKeepDuration;
    private long mLastProgressStateTime;
    private long mLastRunStateTime;
    private long mLastUpdateTime;
    private float mMaxSweepAngle;
    private float mMinSweepAngle;
    private int mOutAnimationDuration;
    private int mPadding;
    private Paint mPaint;
    private int mProgressMode;
    private float mProgressPercent;
    private int mProgressState;
    private RectF mRect;
    private boolean mReverse;
    private int mRotateDuration;
    private int mRunState;
    private float mSecondaryProgressPercent;
    private float mStartAngle;
    private int mStrokeColorIndex;
    private int[] mStrokeColors;
    private int mStrokeSecondaryColor;
    private int mStrokeSize;
    private float mSweepAngle;
    private int mTransformDuration;
    private Interpolator mTransformInterpolator;
    private final Runnable mUpdater;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    private CircularProgressDrawable(int i, float f, float f2, float f3, float f4, float f5, int i2, int[] iArr, int i3, boolean z, int i4, int i5, int i6, Interpolator interpolator, int i7, int i8, float f6, int[] iArr2, int i9) {
        this.mRunState = 0;
        this.mUpdater = new Runnable() { // from class: com.rey.material.drawable.CircularProgressDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                CircularProgressDrawable.this.update();
            }
        };
        this.mPadding = i;
        this.mInitialAngle = f;
        setProgress(f2);
        setSecondaryProgress(f3);
        this.mMaxSweepAngle = f4;
        this.mMinSweepAngle = f5;
        this.mStrokeSize = i2;
        this.mStrokeColors = iArr;
        this.mStrokeSecondaryColor = i3;
        this.mReverse = z;
        this.mRotateDuration = i4;
        this.mTransformDuration = i5;
        this.mKeepDuration = i6;
        this.mTransformInterpolator = interpolator;
        this.mProgressMode = i7;
        this.mInAnimationDuration = i8;
        this.mInStepPercent = f6;
        this.mInColors = iArr2;
        this.mOutAnimationDuration = i9;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStrokeCap(Paint.Cap.ROUND);
        this.mPaint.setStrokeJoin(Paint.Join.ROUND);
        this.mRect = new RectF();
    }

    public void applyStyle(Context context, int i) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.CircularProgressDrawable);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int[] iArr = null;
        boolean z = false;
        int i2 = 0;
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == R.styleable.CircularProgressDrawable_cpd_padding) {
                this.mPadding = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_initialAngle) {
                this.mInitialAngle = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_pv_progress) {
                setProgress(obtainStyledAttributes.getFloat(index, 0.0f));
            } else if (index == R.styleable.CircularProgressDrawable_pv_secondaryProgress) {
                setSecondaryProgress(obtainStyledAttributes.getFloat(index, 0.0f));
            } else if (index == R.styleable.CircularProgressDrawable_cpd_maxSweepAngle) {
                this.mMaxSweepAngle = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_minSweepAngle) {
                this.mMinSweepAngle = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_strokeSize) {
                this.mStrokeSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_strokeColor) {
                i2 = obtainStyledAttributes.getColor(index, 0);
                z = true;
            } else if (index == R.styleable.CircularProgressDrawable_cpd_strokeColors) {
                TypedArray obtainTypedArray = context.getResources().obtainTypedArray(obtainStyledAttributes.getResourceId(index, 0));
                int[] iArr2 = new int[obtainTypedArray.length()];
                for (int i4 = 0; i4 < obtainTypedArray.length(); i4++) {
                    iArr2[i4] = obtainTypedArray.getColor(i4, 0);
                }
                obtainTypedArray.recycle();
                iArr = iArr2;
            } else if (index == R.styleable.CircularProgressDrawable_cpd_strokeSecondaryColor) {
                this.mStrokeSecondaryColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_reverse) {
                this.mReverse = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_rotateDuration) {
                this.mRotateDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_transformDuration) {
                this.mTransformDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_keepDuration) {
                this.mKeepDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_transformInterpolator) {
                this.mTransformInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.CircularProgressDrawable_pv_progressMode) {
                this.mProgressMode = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_inAnimDuration) {
                this.mInAnimationDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_inStepColors) {
                TypedArray obtainTypedArray2 = context.getResources().obtainTypedArray(obtainStyledAttributes.getResourceId(index, 0));
                this.mInColors = new int[obtainTypedArray2.length()];
                for (int i5 = 0; i5 < obtainTypedArray2.length(); i5++) {
                    this.mInColors[i5] = obtainTypedArray2.getColor(i5, 0);
                }
                obtainTypedArray2.recycle();
            } else if (index == R.styleable.CircularProgressDrawable_cpd_inStepPercent) {
                this.mInStepPercent = obtainStyledAttributes.getFloat(index, 0.0f);
            } else if (index == R.styleable.CircularProgressDrawable_cpd_outAnimDuration) {
                this.mOutAnimationDuration = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
        if (iArr != null) {
            this.mStrokeColors = iArr;
        } else if (z) {
            this.mStrokeColors = new int[]{i2};
        }
        if (this.mStrokeColorIndex >= this.mStrokeColors.length) {
            this.mStrokeColorIndex = 0;
        }
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i = this.mProgressMode;
        if (i == 0) {
            drawDeterminate(canvas);
        } else {
            if (i != 1) {
                return;
            }
            drawIndeterminate(canvas);
        }
    }

    private void drawDeterminate(Canvas canvas) {
        float f;
        float min;
        int min2;
        int i;
        float f2;
        Rect bounds = getBounds();
        int i2 = this.mRunState;
        if (i2 == 1) {
            f = (this.mStrokeSize * ((float) Math.min(this.mInAnimationDuration, SystemClock.uptimeMillis() - this.mLastRunStateTime))) / this.mInAnimationDuration;
            if (f > 0.0f) {
                min2 = Math.min(bounds.width(), bounds.height()) - (this.mPadding * 2);
                i = this.mStrokeSize;
                min = (min2 - (i * 2)) + f;
                f2 = min / 2.0f;
            }
            f2 = 0.0f;
        } else {
            if (i2 == 4) {
                f = (this.mStrokeSize * ((float) Math.max(0L, (this.mOutAnimationDuration - SystemClock.uptimeMillis()) + this.mLastRunStateTime))) / this.mOutAnimationDuration;
                if (f > 0.0f) {
                    min2 = Math.min(bounds.width(), bounds.height()) - (this.mPadding * 2);
                    i = this.mStrokeSize;
                    min = (min2 - (i * 2)) + f;
                    f2 = min / 2.0f;
                }
            } else if (i2 != 0) {
                f = this.mStrokeSize;
                min = (Math.min(bounds.width(), bounds.height()) - (this.mPadding * 2)) - this.mStrokeSize;
                f2 = min / 2.0f;
            } else {
                f = 0.0f;
            }
            f2 = 0.0f;
        }
        if (f2 > 0.0f) {
            float f3 = (bounds.left + bounds.right) / 2.0f;
            float f4 = (bounds.top + bounds.bottom) / 2.0f;
            this.mPaint.setStrokeWidth(f);
            this.mPaint.setStyle(Paint.Style.STROKE);
            float f5 = this.mProgressPercent;
            if (f5 == 1.0f) {
                this.mPaint.setColor(this.mStrokeColors[0]);
                canvas.drawCircle(f3, f4, f2, this.mPaint);
            } else {
                if (f5 == 0.0f) {
                    this.mPaint.setColor(this.mStrokeSecondaryColor);
                    canvas.drawCircle(f3, f4, f2, this.mPaint);
                    return;
                }
                float f6 = (this.mReverse ? -360 : 360) * f5;
                this.mRect.set(f3 - f2, f4 - f2, f3 + f2, f4 + f2);
                this.mPaint.setColor(this.mStrokeSecondaryColor);
                canvas.drawArc(this.mRect, this.mStartAngle + f6, (this.mReverse ? -360 : 360) - f6, false, this.mPaint);
                this.mPaint.setColor(this.mStrokeColors[0]);
                canvas.drawArc(this.mRect, this.mStartAngle, f6, false, this.mPaint);
            }
        }
    }

    private int getIndeterminateStrokeColor() {
        if (this.mProgressState != 3 || this.mStrokeColors.length == 1) {
            return this.mStrokeColors[this.mStrokeColorIndex];
        }
        float max = Math.max(0.0f, Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mLastProgressStateTime)) / this.mKeepDuration));
        int i = this.mStrokeColorIndex;
        int length = i == 0 ? this.mStrokeColors.length - 1 : i - 1;
        int[] iArr = this.mStrokeColors;
        return ColorUtil.getMiddleColor(iArr[length], iArr[i], max);
    }

    private void drawIndeterminate(Canvas canvas) {
        int i = this.mRunState;
        float f = 0.0f;
        float f2 = 2.0f;
        if (i != 1) {
            if (i != 4) {
                if (i != 0) {
                    Rect bounds = getBounds();
                    float min = ((Math.min(bounds.width(), bounds.height()) - (this.mPadding * 2)) - this.mStrokeSize) / 2.0f;
                    float f3 = (bounds.left + bounds.right) / 2.0f;
                    float f4 = (bounds.top + bounds.bottom) / 2.0f;
                    this.mRect.set(f3 - min, f4 - min, f3 + min, f4 + min);
                    this.mPaint.setStrokeWidth(this.mStrokeSize);
                    this.mPaint.setStyle(Paint.Style.STROKE);
                    this.mPaint.setColor(getIndeterminateStrokeColor());
                    canvas.drawArc(this.mRect, this.mStartAngle, this.mSweepAngle, false, this.mPaint);
                    return;
                }
                return;
            }
            float max = (this.mStrokeSize * ((float) Math.max(0L, (this.mOutAnimationDuration - SystemClock.uptimeMillis()) + this.mLastRunStateTime))) / this.mOutAnimationDuration;
            if (max > 0.0f) {
                Rect bounds2 = getBounds();
                float min2 = (((Math.min(bounds2.width(), bounds2.height()) - (this.mPadding * 2)) - (this.mStrokeSize * 2)) + max) / 2.0f;
                float f5 = (bounds2.left + bounds2.right) / 2.0f;
                float f6 = (bounds2.top + bounds2.bottom) / 2.0f;
                this.mRect.set(f5 - min2, f6 - min2, f5 + min2, f6 + min2);
                this.mPaint.setStrokeWidth(max);
                this.mPaint.setStyle(Paint.Style.STROKE);
                this.mPaint.setColor(getIndeterminateStrokeColor());
                canvas.drawArc(this.mRect, this.mStartAngle, this.mSweepAngle, false, this.mPaint);
                return;
            }
            return;
        }
        Rect bounds3 = getBounds();
        float f7 = (bounds3.left + bounds3.right) / 2.0f;
        float f8 = (bounds3.top + bounds3.bottom) / 2.0f;
        float min3 = (Math.min(bounds3.width(), bounds3.height()) - (this.mPadding * 2)) / 2.0f;
        float length = this.mInStepPercent * (this.mInColors.length + 2);
        float f9 = 1.0f;
        float uptimeMillis = ((float) (SystemClock.uptimeMillis() - this.mLastRunStateTime)) / this.mInAnimationDuration;
        float f10 = uptimeMillis / (1.0f / (length + 1.0f));
        int floor = (int) Math.floor(f10);
        float f11 = 0.0f;
        while (floor >= 0) {
            float min4 = Math.min(f9, (f10 - floor) * this.mInStepPercent) * min3;
            int[] iArr = this.mInColors;
            if (floor < iArr.length) {
                if (f11 != f) {
                    if (min4 <= f11) {
                        break;
                    }
                    float f12 = (f11 + min4) / f2;
                    this.mRect.set(f7 - f12, f8 - f12, f7 + f12, f8 + f12);
                    this.mPaint.setStrokeWidth(min4 - f11);
                    this.mPaint.setStyle(Paint.Style.STROKE);
                    this.mPaint.setColor(this.mInColors[floor]);
                    canvas.drawCircle(f7, f8, f12, this.mPaint);
                } else {
                    this.mPaint.setColor(iArr[floor]);
                    this.mPaint.setStyle(Paint.Style.FILL);
                    canvas.drawCircle(f7, f8, min4, this.mPaint);
                }
            }
            floor--;
            f11 = min4;
            f = 0.0f;
            f2 = 2.0f;
            f9 = 1.0f;
        }
        if (this.mProgressState == -1) {
            if (f10 >= 1.0f / this.mInStepPercent || uptimeMillis >= 1.0f) {
                resetAnimation();
                this.mProgressState = 0;
                return;
            }
            return;
        }
        float f13 = min3 - (this.mStrokeSize / 2.0f);
        this.mRect.set(f7 - f13, f8 - f13, f7 + f13, f8 + f13);
        this.mPaint.setStrokeWidth(this.mStrokeSize);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setColor(getIndeterminateStrokeColor());
        canvas.drawArc(this.mRect, this.mStartAngle, this.mSweepAngle, false, this.mPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    public int getProgressMode() {
        return this.mProgressMode;
    }

    public void setProgressMode(int i) {
        if (this.mProgressMode != i) {
            this.mProgressMode = i;
            invalidateSelf();
        }
    }

    public float getProgress() {
        return this.mProgressPercent;
    }

    public float getSecondaryProgress() {
        return this.mSecondaryProgressPercent;
    }

    public void setProgress(float f) {
        float min = Math.min(1.0f, Math.max(0.0f, f));
        if (this.mProgressPercent != min) {
            this.mProgressPercent = min;
            if (isRunning()) {
                invalidateSelf();
            } else if (this.mProgressPercent != 0.0f) {
                start();
            }
        }
    }

    public void setSecondaryProgress(float f) {
        float min = Math.min(1.0f, Math.max(0.0f, f));
        if (this.mSecondaryProgressPercent != min) {
            this.mSecondaryProgressPercent = min;
            if (isRunning()) {
                invalidateSelf();
            } else if (this.mSecondaryProgressPercent != 0.0f) {
                start();
            }
        }
    }

    private void resetAnimation() {
        long uptimeMillis = SystemClock.uptimeMillis();
        this.mLastUpdateTime = uptimeMillis;
        this.mLastProgressStateTime = uptimeMillis;
        this.mStartAngle = this.mInitialAngle;
        this.mStrokeColorIndex = 0;
        this.mSweepAngle = this.mReverse ? -this.mMinSweepAngle : this.mMinSweepAngle;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        start(this.mInAnimationDuration > 0);
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        stop(this.mOutAnimationDuration > 0);
    }

    private void start(boolean z) {
        if (isRunning()) {
            return;
        }
        resetAnimation();
        if (z) {
            this.mRunState = 1;
            this.mLastRunStateTime = SystemClock.uptimeMillis();
            this.mProgressState = -1;
        }
        scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        invalidateSelf();
    }

    private void stop(boolean z) {
        if (isRunning()) {
            if (z) {
                this.mLastRunStateTime = SystemClock.uptimeMillis();
                if (this.mRunState == 2) {
                    scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
                    invalidateSelf();
                }
                this.mRunState = 4;
                return;
            }
            this.mRunState = 0;
            unscheduleSelf(this.mUpdater);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mRunState != 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        if (this.mRunState == 0) {
            this.mRunState = this.mInAnimationDuration > 0 ? 1 : 3;
        }
        super.scheduleSelf(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        int i = this.mProgressMode;
        if (i == 0) {
            updateDeterminate();
        } else {
            if (i != 1) {
                return;
            }
            updateIndeterminate();
        }
    }

    private void updateDeterminate() {
        long uptimeMillis = SystemClock.uptimeMillis();
        float f = (((float) (uptimeMillis - this.mLastUpdateTime)) * 360.0f) / this.mRotateDuration;
        if (this.mReverse) {
            f = -f;
        }
        this.mLastUpdateTime = uptimeMillis;
        this.mStartAngle += f;
        int i = this.mRunState;
        if (i == 1) {
            if (uptimeMillis - this.mLastRunStateTime > this.mInAnimationDuration) {
                this.mRunState = 3;
            }
        } else if (i == 4 && uptimeMillis - this.mLastRunStateTime > this.mOutAnimationDuration) {
            stop(false);
            return;
        }
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
        invalidateSelf();
    }

    private void updateIndeterminate() {
        long uptimeMillis = SystemClock.uptimeMillis();
        float f = (((float) (uptimeMillis - this.mLastUpdateTime)) * 360.0f) / this.mRotateDuration;
        boolean z = this.mReverse;
        if (z) {
            f = -f;
        }
        this.mLastUpdateTime = uptimeMillis;
        int i = this.mProgressState;
        if (i == 0) {
            int i2 = this.mTransformDuration;
            if (i2 <= 0) {
                this.mSweepAngle = z ? -this.mMinSweepAngle : this.mMinSweepAngle;
                this.mProgressState = 1;
                this.mStartAngle += f;
                this.mLastProgressStateTime = uptimeMillis;
            } else {
                float f2 = ((float) (uptimeMillis - this.mLastProgressStateTime)) / i2;
                float f3 = this.mMaxSweepAngle;
                if (z) {
                    f3 = -f3;
                }
                float f4 = z ? -this.mMinSweepAngle : this.mMinSweepAngle;
                this.mStartAngle += f;
                this.mSweepAngle = (this.mTransformInterpolator.getInterpolation(f2) * (f3 - f4)) + f4;
                if (f2 > 1.0f) {
                    this.mSweepAngle = f3;
                    this.mProgressState = 1;
                    this.mLastProgressStateTime = uptimeMillis;
                }
            }
        } else if (i == 1) {
            this.mStartAngle += f;
            if (uptimeMillis - this.mLastProgressStateTime > this.mKeepDuration) {
                this.mProgressState = 2;
                this.mLastProgressStateTime = uptimeMillis;
            }
        } else if (i == 2) {
            int i3 = this.mTransformDuration;
            if (i3 <= 0) {
                this.mSweepAngle = z ? -this.mMinSweepAngle : this.mMinSweepAngle;
                this.mProgressState = 3;
                this.mStartAngle += f;
                this.mLastProgressStateTime = uptimeMillis;
                this.mStrokeColorIndex = (this.mStrokeColorIndex + 1) % this.mStrokeColors.length;
            } else {
                float f5 = ((float) (uptimeMillis - this.mLastProgressStateTime)) / i3;
                float f6 = this.mMaxSweepAngle;
                if (z) {
                    f6 = -f6;
                }
                float f7 = z ? -this.mMinSweepAngle : this.mMinSweepAngle;
                float interpolation = ((1.0f - this.mTransformInterpolator.getInterpolation(f5)) * (f6 - f7)) + f7;
                this.mStartAngle += (f + this.mSweepAngle) - interpolation;
                this.mSweepAngle = interpolation;
                if (f5 > 1.0f) {
                    this.mSweepAngle = f7;
                    this.mProgressState = 3;
                    this.mLastProgressStateTime = uptimeMillis;
                    this.mStrokeColorIndex = (this.mStrokeColorIndex + 1) % this.mStrokeColors.length;
                }
            }
        } else if (i == 3) {
            this.mStartAngle += f;
            if (uptimeMillis - this.mLastProgressStateTime > this.mKeepDuration) {
                this.mProgressState = 0;
                this.mLastProgressStateTime = uptimeMillis;
            }
        }
        int i4 = this.mRunState;
        if (i4 == 1) {
            if (uptimeMillis - this.mLastRunStateTime > this.mInAnimationDuration) {
                this.mRunState = 3;
                if (this.mProgressState == -1) {
                    resetAnimation();
                    this.mProgressState = 0;
                }
            }
        } else if (i4 == 4 && uptimeMillis - this.mLastRunStateTime > this.mOutAnimationDuration) {
            stop(false);
            return;
        }
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
        invalidateSelf();
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mInAnimationDuration;
        private int[] mInColors;
        private float mInStepPercent;
        private float mInitialAngle;
        private int mKeepDuration;
        private float mMaxSweepAngle;
        private float mMinSweepAngle;
        private int mOutAnimationDuration;
        private int mPadding;
        private int mProgressMode;
        private float mProgressPercent;
        private boolean mReverse;
        private int mRotateDuration;
        private float mSecondaryProgressPercent;
        private int[] mStrokeColors;
        private int mStrokeSecondaryColor;
        private int mStrokeSize;
        private int mTransformDuration;
        private Interpolator mTransformInterpolator;

        public Builder() {
        }

        public Builder(Context context, int i) {
            this(context, null, 0, i);
        }

        public Builder(Context context, AttributeSet attributeSet, int i, int i2) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.CircularProgressDrawable, i, i2);
            padding(obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircularProgressDrawable_cpd_padding, 0));
            initialAngle(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_cpd_initialAngle, 0));
            progressPercent(obtainStyledAttributes.getFloat(R.styleable.CircularProgressDrawable_pv_progress, 0.0f));
            secondaryProgressPercent(obtainStyledAttributes.getFloat(R.styleable.CircularProgressDrawable_pv_secondaryProgress, 0.0f));
            maxSweepAngle(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_cpd_maxSweepAngle, 270));
            minSweepAngle(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_cpd_minSweepAngle, 1));
            strokeSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.CircularProgressDrawable_cpd_strokeSize, ThemeUtil.dpToPx(context, 4)));
            strokeColors(obtainStyledAttributes.getColor(R.styleable.CircularProgressDrawable_cpd_strokeColor, ThemeUtil.colorPrimary(context, -16777216)));
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.CircularProgressDrawable_cpd_strokeColors, 0);
            if (resourceId != 0) {
                TypedArray obtainTypedArray = context.getResources().obtainTypedArray(resourceId);
                int[] iArr = new int[obtainTypedArray.length()];
                for (int i3 = 0; i3 < obtainTypedArray.length(); i3++) {
                    iArr[i3] = obtainTypedArray.getColor(i3, 0);
                }
                obtainTypedArray.recycle();
                strokeColors(iArr);
            }
            strokeSecondaryColor(obtainStyledAttributes.getColor(R.styleable.CircularProgressDrawable_cpd_strokeSecondaryColor, 0));
            reverse(obtainStyledAttributes.getBoolean(R.styleable.CircularProgressDrawable_cpd_reverse, false));
            rotateDuration(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_cpd_rotateDuration, context.getResources().getInteger(android.R.integer.config_longAnimTime)));
            transformDuration(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_cpd_transformDuration, context.getResources().getInteger(android.R.integer.config_mediumAnimTime)));
            keepDuration(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_cpd_keepDuration, context.getResources().getInteger(android.R.integer.config_shortAnimTime)));
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.CircularProgressDrawable_cpd_transformInterpolator, 0);
            if (resourceId2 != 0) {
                transformInterpolator(AnimationUtils.loadInterpolator(context, resourceId2));
            }
            progressMode(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_pv_progressMode, 1));
            inAnimDuration(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_cpd_inAnimDuration, context.getResources().getInteger(android.R.integer.config_mediumAnimTime)));
            int resourceId3 = obtainStyledAttributes.getResourceId(R.styleable.CircularProgressDrawable_cpd_inStepColors, 0);
            if (resourceId3 != 0) {
                TypedArray obtainTypedArray2 = context.getResources().obtainTypedArray(resourceId3);
                int[] iArr2 = new int[obtainTypedArray2.length()];
                for (int i4 = 0; i4 < obtainTypedArray2.length(); i4++) {
                    iArr2[i4] = obtainTypedArray2.getColor(i4, 0);
                }
                obtainTypedArray2.recycle();
                inStepColors(iArr2);
            }
            inStepPercent(obtainStyledAttributes.getFloat(R.styleable.CircularProgressDrawable_cpd_inStepPercent, 0.5f));
            outAnimDuration(obtainStyledAttributes.getInteger(R.styleable.CircularProgressDrawable_cpd_outAnimDuration, context.getResources().getInteger(android.R.integer.config_mediumAnimTime)));
            obtainStyledAttributes.recycle();
        }

        public CircularProgressDrawable build() {
            if (this.mStrokeColors == null) {
                this.mStrokeColors = new int[]{-16737793};
            }
            if (this.mInColors == null && this.mInAnimationDuration > 0) {
                this.mInColors = new int[]{-4860673, -2168068, -327682};
            }
            if (this.mTransformInterpolator == null) {
                this.mTransformInterpolator = new DecelerateInterpolator();
            }
            return new CircularProgressDrawable(this.mPadding, this.mInitialAngle, this.mProgressPercent, this.mSecondaryProgressPercent, this.mMaxSweepAngle, this.mMinSweepAngle, this.mStrokeSize, this.mStrokeColors, this.mStrokeSecondaryColor, this.mReverse, this.mRotateDuration, this.mTransformDuration, this.mKeepDuration, this.mTransformInterpolator, this.mProgressMode, this.mInAnimationDuration, this.mInStepPercent, this.mInColors, this.mOutAnimationDuration);
        }

        public Builder padding(int i) {
            this.mPadding = i;
            return this;
        }

        public Builder initialAngle(float f) {
            this.mInitialAngle = f;
            return this;
        }

        public Builder progressPercent(float f) {
            this.mProgressPercent = f;
            return this;
        }

        public Builder secondaryProgressPercent(float f) {
            this.mSecondaryProgressPercent = f;
            return this;
        }

        public Builder maxSweepAngle(float f) {
            this.mMaxSweepAngle = f;
            return this;
        }

        public Builder minSweepAngle(float f) {
            this.mMinSweepAngle = f;
            return this;
        }

        public Builder strokeSize(int i) {
            this.mStrokeSize = i;
            return this;
        }

        public Builder strokeColors(int... iArr) {
            this.mStrokeColors = iArr;
            return this;
        }

        public Builder strokeSecondaryColor(int i) {
            this.mStrokeSecondaryColor = i;
            return this;
        }

        public Builder reverse(boolean z) {
            this.mReverse = z;
            return this;
        }

        public Builder reverse() {
            return reverse(true);
        }

        public Builder rotateDuration(int i) {
            this.mRotateDuration = i;
            return this;
        }

        public Builder transformDuration(int i) {
            this.mTransformDuration = i;
            return this;
        }

        public Builder keepDuration(int i) {
            this.mKeepDuration = i;
            return this;
        }

        public Builder transformInterpolator(Interpolator interpolator) {
            this.mTransformInterpolator = interpolator;
            return this;
        }

        public Builder progressMode(int i) {
            this.mProgressMode = i;
            return this;
        }

        public Builder inAnimDuration(int i) {
            this.mInAnimationDuration = i;
            return this;
        }

        public Builder inStepPercent(float f) {
            this.mInStepPercent = f;
            return this;
        }

        public Builder inStepColors(int... iArr) {
            this.mInColors = iArr;
            return this;
        }

        public Builder outAnimDuration(int i) {
            this.mOutAnimationDuration = i;
            return this;
        }
    }
}
