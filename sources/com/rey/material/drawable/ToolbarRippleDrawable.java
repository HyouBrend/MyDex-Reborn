package com.rey.material.drawable;

import android.R;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.rey.material.util.ColorUtil;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class ToolbarRippleDrawable extends Drawable implements Animatable {
    private static final float GRADIENT_RADIUS = 16.0f;
    private static final float[] GRADIENT_STOPS = {0.0f, 0.99f, 1.0f};
    private static final int STATE_HOVER = 2;
    private static final int STATE_OUT = 0;
    private static final int STATE_PRESS = 1;
    private static final int STATE_RELEASE = 4;
    private static final int STATE_RELEASE_ON_HOLD = 3;
    private static final int TYPE_TOUCH = 0;
    private static final int TYPE_TOUCH_MATCH_VIEW = -1;
    private static final int TYPE_WAVE = 1;
    private int mAlpha;
    private Path mBackground;
    private float mBackgroundAlphaPercent;
    private int mBackgroundAnimDuration;
    private RectF mBackgroundBounds;
    private int mBackgroundColor;
    private int mDelayClickType;
    private Paint mFillPaint;
    private Interpolator mInInterpolator;
    private RadialGradient mInShader;
    private Matrix mMatrix;
    private int mMaxRippleRadius;
    private Interpolator mOutInterpolator;
    private RadialGradient mOutShader;
    private boolean mPressed;
    private float mRippleAlphaPercent;
    private int mRippleAnimDuration;
    private int mRippleColor;
    private PointF mRipplePoint;
    private float mRippleRadius;
    private int mRippleType;
    private boolean mRunning;
    private Paint mShaderPaint;
    private long mStartTime;
    private int mState;
    private final Runnable mUpdater;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        return true;
    }

    private ToolbarRippleDrawable(int i, int i2, int i3, int i4, int i5, int i6, int i7, Interpolator interpolator, Interpolator interpolator2) {
        this.mRunning = false;
        this.mAlpha = 255;
        this.mPressed = false;
        this.mState = 0;
        this.mUpdater = new Runnable() { // from class: com.rey.material.drawable.ToolbarRippleDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                int i8 = ToolbarRippleDrawable.this.mRippleType;
                if (i8 == -1 || i8 == 0) {
                    ToolbarRippleDrawable.this.updateTouch();
                } else {
                    if (i8 != 1) {
                        return;
                    }
                    ToolbarRippleDrawable.this.updateWave();
                }
            }
        };
        this.mBackgroundAnimDuration = i;
        this.mBackgroundColor = i2;
        this.mRippleType = i3;
        this.mMaxRippleRadius = i5;
        this.mRippleAnimDuration = i6;
        this.mRippleColor = i7;
        this.mDelayClickType = i4;
        if (i3 == 0 && i5 <= 0) {
            this.mRippleType = -1;
        }
        this.mInInterpolator = interpolator;
        this.mOutInterpolator = interpolator2;
        Paint paint = new Paint(1);
        this.mFillPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.mShaderPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.mBackground = new Path();
        this.mBackgroundBounds = new RectF();
        this.mRipplePoint = new PointF();
        this.mMatrix = new Matrix();
        int i8 = this.mRippleColor;
        float[] fArr = GRADIENT_STOPS;
        this.mInShader = new RadialGradient(0.0f, 0.0f, GRADIENT_RADIUS, new int[]{i8, i8, 0}, fArr, Shader.TileMode.CLAMP);
        if (this.mRippleType == 1) {
            this.mOutShader = new RadialGradient(0.0f, 0.0f, GRADIENT_RADIUS, new int[]{0, ColorUtil.getColor(this.mRippleColor, 0.0f), this.mRippleColor}, fArr, Shader.TileMode.CLAMP);
        }
    }

    public int getDelayClickType() {
        return this.mDelayClickType;
    }

    public void setDelayClickType(int i) {
        this.mDelayClickType = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mAlpha = i;
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mFillPaint.setColorFilter(colorFilter);
        this.mShaderPaint.setColorFilter(colorFilter);
    }

    public long getClickDelayTime() {
        long max;
        long uptimeMillis;
        long j;
        int i = this.mDelayClickType;
        if (i != 1) {
            if (i != 2) {
                return -1L;
            }
            int i2 = this.mState;
            if (i2 == 3) {
                max = Math.max(this.mBackgroundAnimDuration, this.mRippleAnimDuration) * 2;
                uptimeMillis = SystemClock.uptimeMillis();
                j = this.mStartTime;
            } else {
                if (i2 != 4) {
                    return -1L;
                }
                max = Math.max(this.mBackgroundAnimDuration, this.mRippleAnimDuration);
                uptimeMillis = SystemClock.uptimeMillis();
                j = this.mStartTime;
            }
        } else {
            if (this.mState != 3) {
                return -1L;
            }
            max = Math.max(this.mBackgroundAnimDuration, this.mRippleAnimDuration);
            uptimeMillis = SystemClock.uptimeMillis();
            j = this.mStartTime;
        }
        return max - (uptimeMillis - j);
    }

    private void setRippleState(int i) {
        if (this.mState != i) {
            this.mState = i;
            if (i == 0) {
                stop();
            } else if (i != 2) {
                start();
            } else {
                stop();
            }
        }
    }

    private boolean setRippleEffect(float f, float f2, float f3) {
        if (this.mRipplePoint.x == f && this.mRipplePoint.y == f2 && this.mRippleRadius == f3) {
            return false;
        }
        this.mRipplePoint.set(f, f2);
        this.mRippleRadius = f3;
        float f4 = f3 / GRADIENT_RADIUS;
        this.mMatrix.reset();
        this.mMatrix.postTranslate(f, f2);
        this.mMatrix.postScale(f4, f4, f, f2);
        this.mInShader.setLocalMatrix(this.mMatrix);
        RadialGradient radialGradient = this.mOutShader;
        if (radialGradient == null) {
            return true;
        }
        radialGradient.setLocalMatrix(this.mMatrix);
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        this.mBackgroundBounds.set(rect.left, rect.top, rect.right, rect.bottom);
        this.mBackground.reset();
        this.mBackground.addRect(this.mBackgroundBounds, Path.Direction.CW);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] iArr) {
        boolean hasState = ViewUtil.hasState(iArr, R.attr.state_pressed);
        if (this.mPressed == hasState) {
            return false;
        }
        this.mPressed = hasState;
        if (hasState) {
            Rect bounds = getBounds();
            int i = this.mState;
            if (i == 0 || i == 4) {
                int i2 = this.mRippleType;
                if (i2 == 1 || i2 == -1) {
                    this.mMaxRippleRadius = getMaxRippleRadius(bounds.exactCenterX(), bounds.exactCenterY());
                }
                setRippleEffect(bounds.exactCenterX(), bounds.exactCenterY(), 0.0f);
                setRippleState(1);
            } else if (this.mRippleType == 0) {
                setRippleEffect(bounds.exactCenterX(), bounds.exactCenterY(), this.mRippleRadius);
            }
        } else {
            int i3 = this.mState;
            if (i3 != 0) {
                if (i3 == 2) {
                    int i4 = this.mRippleType;
                    if (i4 == 1 || i4 == -1) {
                        setRippleEffect(this.mRipplePoint.x, this.mRipplePoint.y, 0.0f);
                    }
                    setRippleState(4);
                } else {
                    setRippleState(3);
                }
            }
        }
        return true;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int i = this.mRippleType;
        if (i == -1 || i == 0) {
            drawTouch(canvas);
        } else {
            if (i != 1) {
                return;
            }
            drawWave(canvas);
        }
    }

    private void drawTouch(Canvas canvas) {
        if (this.mState != 0) {
            if (this.mBackgroundAlphaPercent > 0.0f) {
                this.mFillPaint.setColor(this.mBackgroundColor);
                this.mFillPaint.setAlpha(Math.round(this.mAlpha * this.mBackgroundAlphaPercent));
                canvas.drawPath(this.mBackground, this.mFillPaint);
            }
            if (this.mRippleRadius > 0.0f) {
                float f = this.mRippleAlphaPercent;
                if (f > 0.0f) {
                    this.mShaderPaint.setAlpha(Math.round(this.mAlpha * f));
                    this.mShaderPaint.setShader(this.mInShader);
                    canvas.drawPath(this.mBackground, this.mShaderPaint);
                }
            }
        }
    }

    private void drawWave(Canvas canvas) {
        int i = this.mState;
        if (i != 0) {
            if (i != 4) {
                if (this.mRippleRadius > 0.0f) {
                    this.mShaderPaint.setShader(this.mInShader);
                    canvas.drawPath(this.mBackground, this.mShaderPaint);
                    return;
                }
                return;
            }
            if (this.mRippleRadius == 0.0f) {
                this.mFillPaint.setColor(this.mRippleColor);
                canvas.drawPath(this.mBackground, this.mFillPaint);
            } else {
                this.mShaderPaint.setShader(this.mOutShader);
                canvas.drawPath(this.mBackground, this.mShaderPaint);
            }
        }
    }

    private int getMaxRippleRadius(float f, float f2) {
        return (int) Math.round(Math.sqrt(Math.pow((f < this.mBackgroundBounds.centerX() ? this.mBackgroundBounds.right : this.mBackgroundBounds.left) - f, 2.0d) + Math.pow((f2 < this.mBackgroundBounds.centerY() ? this.mBackgroundBounds.bottom : this.mBackgroundBounds.top) - f2, 2.0d)));
    }

    public void cancel() {
        setRippleState(0);
    }

    private void resetAnimation() {
        this.mStartTime = SystemClock.uptimeMillis();
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (isRunning()) {
            return;
        }
        resetAnimation();
        scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (isRunning()) {
            this.mRunning = false;
            unscheduleSelf(this.mUpdater);
            invalidateSelf();
        }
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
    public void updateTouch() {
        if (this.mState != 4) {
            float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mBackgroundAnimDuration);
            this.mBackgroundAlphaPercent = (this.mInInterpolator.getInterpolation(min) * Color.alpha(this.mBackgroundColor)) / 255.0f;
            float min2 = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mRippleAnimDuration);
            this.mRippleAlphaPercent = this.mInInterpolator.getInterpolation(min2);
            setRippleEffect(this.mRipplePoint.x, this.mRipplePoint.y, this.mMaxRippleRadius * this.mInInterpolator.getInterpolation(min2));
            if (min == 1.0f && min2 == 1.0f) {
                this.mStartTime = SystemClock.uptimeMillis();
                setRippleState(this.mState == 1 ? 2 : 4);
            }
        } else {
            float min3 = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mBackgroundAnimDuration);
            this.mBackgroundAlphaPercent = ((1.0f - this.mOutInterpolator.getInterpolation(min3)) * Color.alpha(this.mBackgroundColor)) / 255.0f;
            float min4 = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mRippleAnimDuration);
            this.mRippleAlphaPercent = 1.0f - this.mOutInterpolator.getInterpolation(min4);
            setRippleEffect(this.mRipplePoint.x, this.mRipplePoint.y, this.mMaxRippleRadius * ((this.mOutInterpolator.getInterpolation(min4) * 0.5f) + 1.0f));
            if (min3 == 1.0f && min4 == 1.0f) {
                setRippleState(0);
            }
        }
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
        invalidateSelf();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWave() {
        float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mRippleAnimDuration);
        if (this.mState != 4) {
            setRippleEffect(this.mRipplePoint.x, this.mRipplePoint.y, this.mMaxRippleRadius * this.mInInterpolator.getInterpolation(min));
            if (min == 1.0f) {
                this.mStartTime = SystemClock.uptimeMillis();
                if (this.mState == 1) {
                    setRippleState(2);
                } else {
                    setRippleEffect(this.mRipplePoint.x, this.mRipplePoint.y, 0.0f);
                    setRippleState(4);
                }
            }
        } else {
            setRippleEffect(this.mRipplePoint.x, this.mRipplePoint.y, this.mMaxRippleRadius * this.mOutInterpolator.getInterpolation(min));
            if (min == 1.0f) {
                setRippleState(0);
            }
        }
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
        invalidateSelf();
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mBackgroundAnimDuration;
        private int mBackgroundColor;
        private int mDelayClickType;
        private Interpolator mInInterpolator;
        private int mMaxRippleRadius;
        private Interpolator mOutInterpolator;
        private int mRippleAnimDuration;
        private int mRippleColor;
        private int mRippleType;

        public Builder() {
            this.mBackgroundAnimDuration = 200;
            this.mRippleAnimDuration = 400;
        }

        public Builder(Context context, int i) {
            this(context, null, 0, i);
        }

        public Builder(Context context, AttributeSet attributeSet, int i, int i2) {
            this.mBackgroundAnimDuration = 200;
            this.mRippleAnimDuration = 400;
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.rey.material.R.styleable.RippleDrawable, i, i2);
            backgroundColor(obtainStyledAttributes.getColor(com.rey.material.R.styleable.RippleDrawable_rd_backgroundColor, 0));
            backgroundAnimDuration(obtainStyledAttributes.getInteger(com.rey.material.R.styleable.RippleDrawable_rd_backgroundAnimDuration, context.getResources().getInteger(R.integer.config_mediumAnimTime)));
            rippleType(obtainStyledAttributes.getInteger(com.rey.material.R.styleable.RippleDrawable_rd_rippleType, 0));
            delayClickType(obtainStyledAttributes.getInteger(com.rey.material.R.styleable.RippleDrawable_rd_delayClick, 0));
            int type = ThemeUtil.getType(obtainStyledAttributes, com.rey.material.R.styleable.RippleDrawable_rd_maxRippleRadius);
            if (type >= 16 && type <= 31) {
                maxRippleRadius(obtainStyledAttributes.getInteger(com.rey.material.R.styleable.RippleDrawable_rd_maxRippleRadius, -1));
            } else {
                maxRippleRadius(obtainStyledAttributes.getDimensionPixelSize(com.rey.material.R.styleable.RippleDrawable_rd_maxRippleRadius, ThemeUtil.dpToPx(context, 48)));
            }
            rippleColor(obtainStyledAttributes.getColor(com.rey.material.R.styleable.RippleDrawable_rd_rippleColor, ThemeUtil.colorControlHighlight(context, 0)));
            rippleAnimDuration(obtainStyledAttributes.getInteger(com.rey.material.R.styleable.RippleDrawable_rd_rippleAnimDuration, context.getResources().getInteger(R.integer.config_mediumAnimTime)));
            int resourceId = obtainStyledAttributes.getResourceId(com.rey.material.R.styleable.RippleDrawable_rd_inInterpolator, 0);
            if (resourceId != 0) {
                inInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
            }
            int resourceId2 = obtainStyledAttributes.getResourceId(com.rey.material.R.styleable.RippleDrawable_rd_outInterpolator, 0);
            if (resourceId2 != 0) {
                outInterpolator(AnimationUtils.loadInterpolator(context, resourceId2));
            }
            obtainStyledAttributes.recycle();
        }

        public ToolbarRippleDrawable build() {
            if (this.mInInterpolator == null) {
                this.mInInterpolator = new AccelerateInterpolator();
            }
            if (this.mOutInterpolator == null) {
                this.mOutInterpolator = new DecelerateInterpolator();
            }
            return new ToolbarRippleDrawable(this.mBackgroundAnimDuration, this.mBackgroundColor, this.mRippleType, this.mDelayClickType, this.mMaxRippleRadius, this.mRippleAnimDuration, this.mRippleColor, this.mInInterpolator, this.mOutInterpolator);
        }

        public Builder backgroundAnimDuration(int i) {
            this.mBackgroundAnimDuration = i;
            return this;
        }

        public Builder backgroundColor(int i) {
            this.mBackgroundColor = i;
            return this;
        }

        public Builder rippleType(int i) {
            this.mRippleType = i;
            return this;
        }

        public Builder delayClickType(int i) {
            this.mDelayClickType = i;
            return this;
        }

        public Builder maxRippleRadius(int i) {
            this.mMaxRippleRadius = i;
            return this;
        }

        public Builder rippleAnimDuration(int i) {
            this.mRippleAnimDuration = i;
            return this;
        }

        public Builder rippleColor(int i) {
            this.mRippleColor = i;
            return this;
        }

        public Builder inInterpolator(Interpolator interpolator) {
            this.mInInterpolator = interpolator;
            return this;
        }

        public Builder outInterpolator(Interpolator interpolator) {
            this.mOutInterpolator = interpolator;
            return this;
        }
    }
}
