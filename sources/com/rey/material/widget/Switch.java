package com.rey.material.widget;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.Checkable;
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ColorUtil;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class Switch extends View implements Checkable, ThemeManager.OnThemeChangedListener {
    private static final int COLOR_SHADOW_END = 0;
    private static final int COLOR_SHADOW_START = 1275068416;
    private int mAnimDuration;
    private boolean mChecked;
    protected int mCurrentStyle;
    private RectF mDrawRect;
    private float mFlingVelocity;
    private int mGravity;
    private Interpolator mInterpolator;
    private boolean mIsRtl;
    private int mMaxAnimDuration;
    private float mMemoX;
    private OnCheckedChangeListener mOnCheckedChangeListener;
    private Paint mPaint;
    private RippleManager mRippleManager;
    private boolean mRunning;
    private int mShadowOffset;
    private Paint mShadowPaint;
    private Path mShadowPath;
    private int mShadowSize;
    private float mStartPosition;
    private long mStartTime;
    private float mStartX;
    protected int mStyleId;
    private RectF mTempRect;
    private int[] mTempStates;
    private ColorStateList mThumbColors;
    private float mThumbPosition;
    private int mThumbRadius;
    private Paint.Cap mTrackCap;
    private ColorStateList mTrackColors;
    private Path mTrackPath;
    private int mTrackSize;
    private final Runnable mUpdater;

    /* loaded from: classes2.dex */
    public interface OnCheckedChangeListener {
        void onCheckedChanged(Switch r1, boolean z);
    }

    public Switch(Context context) {
        super(context);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mRunning = false;
        this.mTrackSize = -1;
        this.mTrackCap = Paint.Cap.ROUND;
        this.mThumbRadius = -1;
        this.mMaxAnimDuration = -1;
        this.mGravity = 16;
        this.mChecked = false;
        this.mTempStates = new int[2];
        this.mShadowSize = -1;
        this.mShadowOffset = -1;
        this.mIsRtl = false;
        this.mUpdater = new Runnable() { // from class: com.rey.material.widget.Switch.1
            @Override // java.lang.Runnable
            public void run() {
                Switch.this.update();
            }
        };
        init(context, null, 0, 0);
    }

    public Switch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mRunning = false;
        this.mTrackSize = -1;
        this.mTrackCap = Paint.Cap.ROUND;
        this.mThumbRadius = -1;
        this.mMaxAnimDuration = -1;
        this.mGravity = 16;
        this.mChecked = false;
        this.mTempStates = new int[2];
        this.mShadowSize = -1;
        this.mShadowOffset = -1;
        this.mIsRtl = false;
        this.mUpdater = new Runnable() { // from class: com.rey.material.widget.Switch.1
            @Override // java.lang.Runnable
            public void run() {
                Switch.this.update();
            }
        };
        init(context, attributeSet, 0, 0);
    }

    public Switch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mRunning = false;
        this.mTrackSize = -1;
        this.mTrackCap = Paint.Cap.ROUND;
        this.mThumbRadius = -1;
        this.mMaxAnimDuration = -1;
        this.mGravity = 16;
        this.mChecked = false;
        this.mTempStates = new int[2];
        this.mShadowSize = -1;
        this.mShadowOffset = -1;
        this.mIsRtl = false;
        this.mUpdater = new Runnable() { // from class: com.rey.material.widget.Switch.1
            @Override // java.lang.Runnable
            public void run() {
                Switch.this.update();
            }
        };
        init(context, attributeSet, i, 0);
    }

    public Switch(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mRunning = false;
        this.mTrackSize = -1;
        this.mTrackCap = Paint.Cap.ROUND;
        this.mThumbRadius = -1;
        this.mMaxAnimDuration = -1;
        this.mGravity = 16;
        this.mChecked = false;
        this.mTempStates = new int[2];
        this.mShadowSize = -1;
        this.mShadowOffset = -1;
        this.mIsRtl = false;
        this.mUpdater = new Runnable() { // from class: com.rey.material.widget.Switch.1
            @Override // java.lang.Runnable
            public void run() {
                Switch.this.update();
            }
        };
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mPaint = new Paint(1);
        this.mDrawRect = new RectF();
        this.mTempRect = new RectF();
        this.mTrackPath = new Path();
        this.mFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity();
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
        getRippleManager().onCreate(this, context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Switch, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i3 = 0; i3 < indexCount; i3++) {
            int index = obtainStyledAttributes.getIndex(i3);
            if (index == R.styleable.Switch_sw_trackSize) {
                this.mTrackSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.Switch_sw_trackColor) {
                this.mTrackColors = obtainStyledAttributes.getColorStateList(index);
            } else if (index == R.styleable.Switch_sw_trackCap) {
                int integer = obtainStyledAttributes.getInteger(index, 0);
                if (integer == 0) {
                    this.mTrackCap = Paint.Cap.BUTT;
                } else if (integer == 1) {
                    this.mTrackCap = Paint.Cap.ROUND;
                } else {
                    this.mTrackCap = Paint.Cap.SQUARE;
                }
            } else if (index == R.styleable.Switch_sw_thumbColor) {
                this.mThumbColors = obtainStyledAttributes.getColorStateList(index);
            } else if (index == R.styleable.Switch_sw_thumbRadius) {
                this.mThumbRadius = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.Switch_sw_thumbElevation) {
                int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                this.mShadowSize = dimensionPixelSize;
                this.mShadowOffset = dimensionPixelSize / 2;
            } else if (index == R.styleable.Switch_sw_animDuration) {
                this.mMaxAnimDuration = obtainStyledAttributes.getInt(index, 0);
            } else if (index == R.styleable.Switch_android_gravity) {
                this.mGravity = obtainStyledAttributes.getInt(index, 0);
            } else if (index == R.styleable.Switch_android_checked) {
                setCheckedImmediately(obtainStyledAttributes.getBoolean(index, this.mChecked));
            } else if (index == R.styleable.Switch_sw_interpolator && (resourceId = obtainStyledAttributes.getResourceId(R.styleable.Switch_sw_interpolator, 0)) != 0) {
                this.mInterpolator = AnimationUtils.loadInterpolator(context, resourceId);
            }
        }
        obtainStyledAttributes.recycle();
        if (this.mTrackSize < 0) {
            this.mTrackSize = ThemeUtil.dpToPx(context, 2);
        }
        if (this.mThumbRadius < 0) {
            this.mThumbRadius = ThemeUtil.dpToPx(context, 8);
        }
        if (this.mShadowSize < 0) {
            int dpToPx = ThemeUtil.dpToPx(context, 2);
            this.mShadowSize = dpToPx;
            this.mShadowOffset = dpToPx / 2;
        }
        if (this.mMaxAnimDuration < 0) {
            this.mMaxAnimDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
        }
        if (this.mInterpolator == null) {
            this.mInterpolator = new DecelerateInterpolator();
        }
        if (this.mTrackColors == null) {
            this.mTrackColors = new ColorStateList(new int[][]{new int[]{-16842912}, new int[]{android.R.attr.state_checked}}, new int[]{ColorUtil.getColor(ThemeUtil.colorControlNormal(context, -16777216), 0.5f), ColorUtil.getColor(ThemeUtil.colorControlActivated(context, -16777216), 0.5f)});
        }
        if (this.mThumbColors == null) {
            this.mThumbColors = new ColorStateList(new int[][]{new int[]{-16842912}, new int[]{android.R.attr.state_checked}}, new int[]{16448250, ThemeUtil.colorControlActivated(context, -16777216)});
        }
        this.mPaint.setStrokeCap(this.mTrackCap);
        buildShadow();
        invalidate();
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

    public void setOnCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.mOnCheckedChangeListener = onCheckedChangeListener;
    }

    @Override // android.widget.Checkable
    public void setChecked(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
        if (this.mThumbPosition != (this.mChecked ? 1.0f : 0.0f)) {
            startAnimation();
        }
    }

    public void setCheckedImmediately(boolean z) {
        if (this.mChecked != z) {
            this.mChecked = z;
            OnCheckedChangeListener onCheckedChangeListener = this.mOnCheckedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, z);
            }
        }
        this.mThumbPosition = this.mChecked ? 1.0f : 0.0f;
        invalidate();
    }

    @Override // android.widget.Checkable
    public boolean isChecked() {
        return this.mChecked;
    }

    @Override // android.widget.Checkable
    public void toggle() {
        if (isEnabled()) {
            setChecked(!this.mChecked);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        boolean z = i == 1;
        if (this.mIsRtl != z) {
            this.mIsRtl = z;
            invalidate();
        }
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        getRippleManager().onTouchEvent(this, motionEvent);
        float x = motionEvent.getX();
        if (this.mIsRtl) {
            x = (this.mDrawRect.centerX() * 2.0f) - x;
        }
        int action = motionEvent.getAction();
        if (action != 0) {
            if (action == 1) {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                float uptimeMillis = ((x - this.mStartX) / ((float) (SystemClock.uptimeMillis() - this.mStartTime))) * 1000.0f;
                if (Math.abs(uptimeMillis) >= this.mFlingVelocity) {
                    setChecked(uptimeMillis > 0.0f);
                } else {
                    boolean z = this.mChecked;
                    if ((!z && this.mThumbPosition < 0.1f) || (z && this.mThumbPosition > 0.9f)) {
                        toggle();
                    } else {
                        setChecked(this.mThumbPosition > 0.5f);
                    }
                }
            } else if (action == 2) {
                this.mThumbPosition = Math.min(1.0f, Math.max(0.0f, this.mThumbPosition + ((x - this.mMemoX) / (this.mDrawRect.width() - (this.mThumbRadius * 2)))));
                this.mMemoX = x;
                invalidate();
            } else if (action == 3) {
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
                setChecked(this.mThumbPosition > 0.5f);
            }
        } else {
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(true);
            }
            this.mMemoX = x;
            this.mStartX = x;
            this.mStartTime = SystemClock.uptimeMillis();
        }
        return true;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, getSuggestedMinimumWidth());
        } else if (mode == 0) {
            size = getSuggestedMinimumWidth();
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, getSuggestedMinimumHeight());
        } else if (mode2 == 0) {
            size2 = getSuggestedMinimumHeight();
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.view.View
    public int getSuggestedMinimumWidth() {
        return (this.mThumbRadius * 4) + Math.max(this.mShadowSize, getPaddingLeft()) + Math.max(this.mShadowSize, getPaddingRight());
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return (this.mThumbRadius * 2) + Math.max(this.mShadowSize - this.mShadowOffset, getPaddingTop()) + Math.max(this.mShadowSize + this.mShadowOffset, getPaddingBottom());
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mDrawRect.left = Math.max(this.mShadowSize, getPaddingLeft());
        this.mDrawRect.right = i - Math.max(this.mShadowSize, getPaddingRight());
        int i5 = this.mThumbRadius * 2;
        int i6 = this.mGravity & 112;
        if (i6 == 48) {
            this.mDrawRect.top = Math.max(this.mShadowSize - this.mShadowOffset, getPaddingTop());
            RectF rectF = this.mDrawRect;
            rectF.bottom = rectF.top + i5;
            return;
        }
        if (i6 == 80) {
            this.mDrawRect.bottom = i2 - Math.max(this.mShadowSize + this.mShadowOffset, getPaddingBottom());
            RectF rectF2 = this.mDrawRect;
            rectF2.top = rectF2.bottom - i5;
            return;
        }
        this.mDrawRect.top = (i2 - i5) / 2.0f;
        RectF rectF3 = this.mDrawRect;
        rectF3.bottom = rectF3.top + i5;
    }

    private int getTrackColor(boolean z) {
        this.mTempStates[0] = isEnabled() ? android.R.attr.state_enabled : -16842910;
        int[] iArr = this.mTempStates;
        iArr[1] = z ? android.R.attr.state_checked : -16842912;
        return this.mTrackColors.getColorForState(iArr, 0);
    }

    private int getThumbColor(boolean z) {
        this.mTempStates[0] = isEnabled() ? android.R.attr.state_enabled : -16842910;
        int[] iArr = this.mTempStates;
        iArr[1] = z ? android.R.attr.state_checked : -16842912;
        return this.mThumbColors.getColorForState(iArr, 0);
    }

    private void buildShadow() {
        if (this.mShadowSize <= 0) {
            return;
        }
        if (this.mShadowPaint == null) {
            Paint paint = new Paint(5);
            this.mShadowPaint = paint;
            paint.setStyle(Paint.Style.FILL);
            this.mShadowPaint.setDither(true);
        }
        this.mShadowPaint.setShader(new RadialGradient(0.0f, 0.0f, this.mThumbRadius + this.mShadowSize, new int[]{COLOR_SHADOW_START, COLOR_SHADOW_START, 0}, new float[]{0.0f, this.mThumbRadius / ((r0 + this.mShadowSize) + this.mShadowOffset), 1.0f}, Shader.TileMode.CLAMP));
        Path path = this.mShadowPath;
        if (path == null) {
            Path path2 = new Path();
            this.mShadowPath = path2;
            path2.setFillType(Path.FillType.EVEN_ODD);
        } else {
            path.reset();
        }
        float f = this.mThumbRadius + this.mShadowSize;
        float f2 = -f;
        this.mTempRect.set(f2, f2, f, f);
        this.mShadowPath.addOval(this.mTempRect, Path.Direction.CW);
        float f3 = this.mThumbRadius - 1;
        RectF rectF = this.mTempRect;
        float f4 = -f3;
        int i = this.mShadowOffset;
        rectF.set(f4, f4 - i, f3, f3 - i);
        this.mShadowPath.addOval(this.mTempRect, Path.Direction.CW);
    }

    private void getTrackPath(float f, float f2, float f3) {
        float f4 = this.mTrackSize / 2.0f;
        this.mTrackPath.reset();
        if (this.mTrackCap != Paint.Cap.ROUND) {
            float f5 = f - f3;
            float f6 = f + f3;
            this.mTempRect.set(f5 + 1.0f, (f2 - f3) + 1.0f, f6 - 1.0f, (f2 + f3) - 1.0f);
            float asin = (float) ((Math.asin(f4 / (f3 - 1.0f)) / 3.141592653589793d) * 180.0d);
            if (f5 > this.mDrawRect.left) {
                this.mTrackPath.moveTo(this.mDrawRect.left, f2 - f4);
                this.mTrackPath.arcTo(this.mTempRect, 180.0f + asin, (-asin) * 2.0f);
                this.mTrackPath.lineTo(this.mDrawRect.left, f2 + f4);
                this.mTrackPath.close();
            }
            if (f6 < this.mDrawRect.right) {
                this.mTrackPath.moveTo(this.mDrawRect.right, f2 - f4);
                this.mTrackPath.arcTo(this.mTempRect, -asin, asin * 2.0f);
                this.mTrackPath.lineTo(this.mDrawRect.right, f2 + f4);
                this.mTrackPath.close();
                return;
            }
            return;
        }
        float asin2 = (float) ((Math.asin(f4 / (f3 - 1.0f)) / 3.141592653589793d) * 180.0d);
        float f7 = f - f3;
        if (f7 > this.mDrawRect.left) {
            float acos = (float) ((Math.acos(Math.max(0.0f, (((this.mDrawRect.left + f4) - f) + f3) / f4)) / 3.141592653589793d) * 180.0d);
            this.mTempRect.set(this.mDrawRect.left, f2 - f4, this.mDrawRect.left + this.mTrackSize, f2 + f4);
            this.mTrackPath.arcTo(this.mTempRect, 180.0f - acos, acos * 2.0f);
            this.mTempRect.set(f7 + 1.0f, (f2 - f3) + 1.0f, (f + f3) - 1.0f, (f2 + f3) - 1.0f);
            this.mTrackPath.arcTo(this.mTempRect, 180.0f + asin2, (-asin2) * 2.0f);
            this.mTrackPath.close();
        }
        float f8 = f + f3;
        if (f8 < this.mDrawRect.right) {
            double acos2 = (float) Math.acos(Math.max(0.0f, ((f8 - this.mDrawRect.right) + f4) / f4));
            double d = f4;
            this.mTrackPath.moveTo((float) ((this.mDrawRect.right - f4) + (Math.cos(acos2) * d)), (float) (f2 + (Math.sin(acos2) * d)));
            float f9 = (float) ((acos2 / 3.141592653589793d) * 180.0d);
            this.mTempRect.set(this.mDrawRect.right - this.mTrackSize, f2 - f4, this.mDrawRect.right, f4 + f2);
            this.mTrackPath.arcTo(this.mTempRect, f9, (-f9) * 2.0f);
            this.mTempRect.set(f7 + 1.0f, (f2 - f3) + 1.0f, f8 - 1.0f, (f2 + f3) - 1.0f);
            this.mTrackPath.arcTo(this.mTempRect, -asin2, asin2 * 2.0f);
            this.mTrackPath.close();
        }
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float width = ((this.mDrawRect.width() - (this.mThumbRadius * 2)) * this.mThumbPosition) + this.mDrawRect.left + this.mThumbRadius;
        if (this.mIsRtl) {
            width = (this.mDrawRect.centerX() * 2.0f) - width;
        }
        float centerY = this.mDrawRect.centerY();
        getTrackPath(width, centerY, this.mThumbRadius);
        this.mPaint.setColor(ColorUtil.getMiddleColor(getTrackColor(false), getTrackColor(true), this.mThumbPosition));
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawPath(this.mTrackPath, this.mPaint);
        if (this.mShadowSize > 0) {
            int save = canvas.save();
            canvas.translate(width, this.mShadowOffset + centerY);
            canvas.drawPath(this.mShadowPath, this.mShadowPaint);
            canvas.restoreToCount(save);
        }
        this.mPaint.setColor(ColorUtil.getMiddleColor(getThumbColor(false), getThumbColor(true), this.mThumbPosition));
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(width, centerY, this.mThumbRadius, this.mPaint);
    }

    private void resetAnimation() {
        this.mStartTime = SystemClock.uptimeMillis();
        float f = this.mThumbPosition;
        this.mStartPosition = f;
        float f2 = this.mMaxAnimDuration;
        if (this.mChecked) {
            f = 1.0f - f;
        }
        this.mAnimDuration = (int) (f2 * f);
    }

    private void startAnimation() {
        if (getHandler() != null) {
            resetAnimation();
            this.mRunning = true;
            getHandler().postAtTime(this.mUpdater, SystemClock.uptimeMillis() + 16);
        } else {
            this.mThumbPosition = this.mChecked ? 1.0f : 0.0f;
        }
        invalidate();
    }

    private void stopAnimation() {
        this.mRunning = false;
        this.mThumbPosition = this.mChecked ? 1.0f : 0.0f;
        if (getHandler() != null) {
            getHandler().removeCallbacks(this.mUpdater);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mAnimDuration);
        float interpolation = this.mInterpolator.getInterpolation(min);
        this.mThumbPosition = this.mChecked ? (this.mStartPosition * (1.0f - interpolation)) + interpolation : this.mStartPosition * (1.0f - interpolation);
        if (min == 1.0f) {
            stopAnimation();
        }
        if (this.mRunning) {
            if (getHandler() != null) {
                getHandler().postAtTime(this.mUpdater, SystemClock.uptimeMillis() + 16);
            } else {
                stopAnimation();
            }
        }
        invalidate();
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.checked = isChecked();
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setChecked(savedState.checked);
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.rey.material.widget.Switch.SavedState.1
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
        boolean checked;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.checked = ((Boolean) parcel.readValue(null)).booleanValue();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Boolean.valueOf(this.checked));
        }

        public String toString() {
            return "Switch.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " checked=" + this.checked + "}";
        }
    }
}
