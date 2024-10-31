package com.rey.material.drawable;

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
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.rey.material.R;
import com.rey.material.util.ColorUtil;
import com.rey.material.util.ThemeUtil;

/* loaded from: classes2.dex */
public class RippleDrawable extends Drawable implements Animatable, View.OnTouchListener {
    public static final int DELAY_CLICK_AFTER_RELEASE = 2;
    public static final int DELAY_CLICK_NONE = 0;
    public static final int DELAY_CLICK_UNTIL_RELEASE = 1;
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
    private Drawable mBackgroundDrawable;
    private int mDelayClickType;
    private Paint mFillPaint;
    private Interpolator mInInterpolator;
    private RadialGradient mInShader;
    private Mask mMask;
    private Matrix mMatrix;
    private int mMaxRippleRadius;
    private Interpolator mOutInterpolator;
    private RadialGradient mOutShader;
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

    private RippleDrawable(Drawable drawable, int i, int i2, int i3, int i4, int i5, int i6, int i7, Interpolator interpolator, Interpolator interpolator2, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16) {
        this.mRunning = false;
        this.mAlpha = 255;
        this.mState = 0;
        this.mUpdater = new Runnable() { // from class: com.rey.material.drawable.RippleDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                int i17 = RippleDrawable.this.mRippleType;
                if (i17 == -1 || i17 == 0) {
                    RippleDrawable.this.updateTouch();
                } else {
                    if (i17 != 1) {
                        return;
                    }
                    RippleDrawable.this.updateWave();
                }
            }
        };
        setBackgroundDrawable(drawable);
        this.mBackgroundAnimDuration = i;
        this.mBackgroundColor = i2;
        this.mRippleType = i3;
        setDelayClickType(i4);
        this.mMaxRippleRadius = i5;
        this.mRippleAnimDuration = i6;
        this.mRippleColor = i7;
        if (this.mRippleType == 0 && i5 <= 0) {
            this.mRippleType = -1;
        }
        this.mInInterpolator = interpolator;
        this.mOutInterpolator = interpolator2;
        setMask(i8, i9, i10, i11, i12, i13, i14, i15, i16);
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
        int i17 = this.mRippleColor;
        float[] fArr = GRADIENT_STOPS;
        this.mInShader = new RadialGradient(0.0f, 0.0f, GRADIENT_RADIUS, new int[]{i17, i17, 0}, fArr, Shader.TileMode.CLAMP);
        if (this.mRippleType == 1) {
            this.mOutShader = new RadialGradient(0.0f, 0.0f, GRADIENT_RADIUS, new int[]{0, ColorUtil.getColor(this.mRippleColor, 0.0f), this.mRippleColor}, fArr, Shader.TileMode.CLAMP);
        }
    }

    public void setBackgroundDrawable(Drawable drawable) {
        this.mBackgroundDrawable = drawable;
        if (drawable != null) {
            drawable.setBounds(getBounds());
        }
    }

    public Drawable getBackgroundDrawable() {
        return this.mBackgroundDrawable;
    }

    public int getDelayClickType() {
        return this.mDelayClickType;
    }

    public void setDelayClickType(int i) {
        this.mDelayClickType = i;
    }

    public void setMask(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        this.mMask = new Mask(i, i2, i3, i4, i5, i6, i7, i8, i9);
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
        int i2 = this.mState;
        if (i2 != i) {
            if (i2 != 0 || i == 1) {
                this.mState = i;
                if (i == 0 || i == 2) {
                    stop();
                } else {
                    start();
                }
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
        Drawable drawable = this.mBackgroundDrawable;
        if (drawable != null) {
            drawable.setBounds(rect);
        }
        this.mBackgroundBounds.set(rect.left + this.mMask.left, rect.top + this.mMask.top, rect.right - this.mMask.right, rect.bottom - this.mMask.bottom);
        this.mBackground.reset();
        int i = this.mMask.type;
        if (i == 0) {
            this.mBackground.addRoundRect(this.mBackgroundBounds, this.mMask.cornerRadius, Path.Direction.CW);
        } else {
            if (i != 1) {
                return;
            }
            this.mBackground.addOval(this.mBackgroundBounds, Path.Direction.CW);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.mBackgroundDrawable;
        return drawable != null && drawable.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onStateChange(int[] iArr) {
        Drawable drawable = this.mBackgroundDrawable;
        return drawable != null && drawable.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.mBackgroundDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
        }
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

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0010, code lost:
    
        if (r7 != 3) goto L32;
     */
    @Override // android.view.View.OnTouchListener
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouch(android.view.View r7, android.view.MotionEvent r8) {
        /*
            r6 = this;
            int r7 = r8.getAction()
            r0 = 0
            r1 = -1
            r2 = 4
            r3 = 1
            if (r7 == 0) goto L32
            r4 = 3
            r5 = 2
            if (r7 == r3) goto L13
            if (r7 == r5) goto L32
            if (r7 == r4) goto L13
            goto L73
        L13:
            int r7 = r6.mState
            if (r7 == 0) goto L73
            if (r7 != r5) goto L2e
            int r7 = r6.mRippleType
            if (r7 == r3) goto L1f
            if (r7 != r1) goto L2a
        L1f:
            android.graphics.PointF r7 = r6.mRipplePoint
            float r7 = r7.x
            android.graphics.PointF r8 = r6.mRipplePoint
            float r8 = r8.y
            r6.setRippleEffect(r7, r8, r0)
        L2a:
            r6.setRippleState(r2)
            goto L73
        L2e:
            r6.setRippleState(r4)
            goto L73
        L32:
            int r7 = r6.mState
            if (r7 == 0) goto L51
            if (r7 != r2) goto L39
            goto L51
        L39:
            int r7 = r6.mRippleType
            if (r7 != 0) goto L73
            float r7 = r8.getX()
            float r8 = r8.getY()
            float r0 = r6.mRippleRadius
            boolean r7 = r6.setRippleEffect(r7, r8, r0)
            if (r7 == 0) goto L73
            r6.invalidateSelf()
            goto L73
        L51:
            int r7 = r6.mRippleType
            if (r7 == r3) goto L57
            if (r7 != r1) goto L65
        L57:
            float r7 = r8.getX()
            float r1 = r8.getY()
            int r7 = r6.getMaxRippleRadius(r7, r1)
            r6.mMaxRippleRadius = r7
        L65:
            float r7 = r8.getX()
            float r8 = r8.getY()
            r6.setRippleEffect(r7, r8, r0)
            r6.setRippleState(r3)
        L73:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rey.material.drawable.RippleDrawable.onTouch(android.view.View, android.view.MotionEvent):boolean");
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
        this.mRunning = false;
        unscheduleSelf(this.mUpdater);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        int i = this.mState;
        return (i == 0 || i == 2 || !this.mRunning) ? false : true;
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
    public static class Mask {
        public static final int TYPE_OVAL = 1;
        public static final int TYPE_RECTANGLE = 0;
        final int bottom;
        final float[] cornerRadius;
        final int left;
        final int right;
        final int top;
        final int type;

        public Mask(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this.cornerRadius = r0;
            this.type = i;
            float f = i2;
            float f2 = i3;
            float f3 = i4;
            float f4 = i5;
            float[] fArr = {f, f, f2, f2, f3, f3, f4, f4};
            this.left = i6;
            this.top = i7;
            this.right = i8;
            this.bottom = i9;
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private int mBackgroundAnimDuration;
        private int mBackgroundColor;
        private Drawable mBackgroundDrawable;
        private int mDelayClickType;
        private Interpolator mInInterpolator;
        private int mMaskBottom;
        private int mMaskBottomLeftCornerRadius;
        private int mMaskBottomRightCornerRadius;
        private int mMaskLeft;
        private int mMaskRight;
        private int mMaskTop;
        private int mMaskTopLeftCornerRadius;
        private int mMaskTopRightCornerRadius;
        private int mMaskType;
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
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleDrawable, i, i2);
            backgroundColor(obtainStyledAttributes.getColor(R.styleable.RippleDrawable_rd_backgroundColor, 0));
            backgroundAnimDuration(obtainStyledAttributes.getInteger(R.styleable.RippleDrawable_rd_backgroundAnimDuration, context.getResources().getInteger(android.R.integer.config_mediumAnimTime)));
            rippleType(obtainStyledAttributes.getInteger(R.styleable.RippleDrawable_rd_rippleType, 0));
            delayClickType(obtainStyledAttributes.getInteger(R.styleable.RippleDrawable_rd_delayClick, 0));
            int type = ThemeUtil.getType(obtainStyledAttributes, R.styleable.RippleDrawable_rd_maxRippleRadius);
            if (type >= 16 && type <= 31) {
                maxRippleRadius(obtainStyledAttributes.getInteger(R.styleable.RippleDrawable_rd_maxRippleRadius, -1));
            } else {
                maxRippleRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_maxRippleRadius, ThemeUtil.dpToPx(context, 48)));
            }
            rippleColor(obtainStyledAttributes.getColor(R.styleable.RippleDrawable_rd_rippleColor, ThemeUtil.colorControlHighlight(context, 0)));
            rippleAnimDuration(obtainStyledAttributes.getInteger(R.styleable.RippleDrawable_rd_rippleAnimDuration, context.getResources().getInteger(android.R.integer.config_mediumAnimTime)));
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.RippleDrawable_rd_inInterpolator, 0);
            if (resourceId != 0) {
                inInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
            }
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.RippleDrawable_rd_outInterpolator, 0);
            if (resourceId2 != 0) {
                outInterpolator(AnimationUtils.loadInterpolator(context, resourceId2));
            }
            maskType(obtainStyledAttributes.getInteger(R.styleable.RippleDrawable_rd_maskType, 0));
            cornerRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_cornerRadius, 0));
            topLeftCornerRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_topLeftCornerRadius, this.mMaskTopLeftCornerRadius));
            topRightCornerRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_topRightCornerRadius, this.mMaskTopRightCornerRadius));
            bottomRightCornerRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_bottomRightCornerRadius, this.mMaskBottomRightCornerRadius));
            bottomLeftCornerRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_bottomLeftCornerRadius, this.mMaskBottomLeftCornerRadius));
            padding(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_padding, 0));
            left(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_leftPadding, this.mMaskLeft));
            right(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_rightPadding, this.mMaskRight));
            top(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_topPadding, this.mMaskTop));
            bottom(obtainStyledAttributes.getDimensionPixelSize(R.styleable.RippleDrawable_rd_bottomPadding, this.mMaskBottom));
            obtainStyledAttributes.recycle();
        }

        public RippleDrawable build() {
            if (this.mInInterpolator == null) {
                this.mInInterpolator = new AccelerateInterpolator();
            }
            if (this.mOutInterpolator == null) {
                this.mOutInterpolator = new DecelerateInterpolator();
            }
            return new RippleDrawable(this.mBackgroundDrawable, this.mBackgroundAnimDuration, this.mBackgroundColor, this.mRippleType, this.mDelayClickType, this.mMaxRippleRadius, this.mRippleAnimDuration, this.mRippleColor, this.mInInterpolator, this.mOutInterpolator, this.mMaskType, this.mMaskTopLeftCornerRadius, this.mMaskTopRightCornerRadius, this.mMaskBottomRightCornerRadius, this.mMaskBottomLeftCornerRadius, this.mMaskLeft, this.mMaskTop, this.mMaskRight, this.mMaskBottom);
        }

        public Builder backgroundDrawable(Drawable drawable) {
            this.mBackgroundDrawable = drawable;
            return this;
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

        public Builder maskType(int i) {
            this.mMaskType = i;
            return this;
        }

        public Builder cornerRadius(int i) {
            this.mMaskTopLeftCornerRadius = i;
            this.mMaskTopRightCornerRadius = i;
            this.mMaskBottomLeftCornerRadius = i;
            this.mMaskBottomRightCornerRadius = i;
            return this;
        }

        public Builder topLeftCornerRadius(int i) {
            this.mMaskTopLeftCornerRadius = i;
            return this;
        }

        public Builder topRightCornerRadius(int i) {
            this.mMaskTopRightCornerRadius = i;
            return this;
        }

        public Builder bottomLeftCornerRadius(int i) {
            this.mMaskBottomLeftCornerRadius = i;
            return this;
        }

        public Builder bottomRightCornerRadius(int i) {
            this.mMaskBottomRightCornerRadius = i;
            return this;
        }

        public Builder padding(int i) {
            this.mMaskLeft = i;
            this.mMaskTop = i;
            this.mMaskRight = i;
            this.mMaskBottom = i;
            return this;
        }

        public Builder left(int i) {
            this.mMaskLeft = i;
            return this;
        }

        public Builder top(int i) {
            this.mMaskTop = i;
            return this;
        }

        public Builder right(int i) {
            this.mMaskRight = i;
            return this;
        }

        public Builder bottom(int i) {
            this.mMaskBottom = i;
            return this;
        }
    }
}
