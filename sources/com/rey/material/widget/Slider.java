package com.rey.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
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
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ColorUtil;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.TypefaceUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class Slider extends View implements ThemeManager.OnThemeChangedListener {
    private boolean mAlwaysFillThumb;
    private int mBaselineOffset;
    protected int mCurrentStyle;
    private boolean mDiscreteMode;
    private RectF mDrawRect;
    private int mGravity;
    private Interpolator mInterpolator;
    private boolean mIsDragging;
    private boolean mIsRtl;
    private Path mLeftTrackPath;
    private Path mMarkPath;
    private int mMaxValue;
    private PointF mMemoPoint;
    private int mMemoValue;
    private int mMinValue;
    private OnPositionChangeListener mOnPositionChangeListener;
    private Paint mPaint;
    private int mPrimaryColor;
    private Path mRightTrackPath;
    private RippleManager mRippleManager;
    private int mSecondaryColor;
    private int mStepValue;
    protected int mStyleId;
    private RectF mTempRect;
    private int mTextColor;
    private int mTextHeight;
    private int mTextSize;
    private int mThumbBorderSize;
    private float mThumbCurrentRadius;
    private float mThumbFillPercent;
    private int mThumbFocusRadius;
    private ThumbMoveAnimator mThumbMoveAnimator;
    private float mThumbPosition;
    private int mThumbRadius;
    private ThumbRadiusAnimator mThumbRadiusAnimator;
    private ThumbStrokeAnimator mThumbStrokeAnimator;
    private int mTouchSlop;
    private Paint.Cap mTrackCap;
    private int mTrackSize;
    private int mTransformAnimationDuration;
    private int mTravelAnimationDuration;
    private Typeface mTypeface;
    private ValueDescriptionProvider mValueDescriptionProvider;
    private String mValueText;

    /* loaded from: classes2.dex */
    public interface OnPositionChangeListener {
        void onPositionChanged(Slider slider, boolean z, float f, float f2, int i, int i2);
    }

    /* loaded from: classes2.dex */
    public interface ValueDescriptionProvider {
        String getDescription(int i);
    }

    public Slider(Context context) {
        super(context);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mMinValue = 0;
        this.mMaxValue = 100;
        this.mStepValue = 1;
        this.mDiscreteMode = false;
        this.mTrackSize = -1;
        this.mTrackCap = Paint.Cap.BUTT;
        this.mThumbBorderSize = -1;
        this.mThumbRadius = -1;
        this.mThumbFocusRadius = -1;
        this.mThumbPosition = -1.0f;
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -1;
        this.mGravity = 17;
        this.mTravelAnimationDuration = -1;
        this.mTransformAnimationDuration = -1;
        this.mAlwaysFillThumb = false;
        this.mIsRtl = false;
        init(context, null, 0, 0);
    }

    public Slider(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mMinValue = 0;
        this.mMaxValue = 100;
        this.mStepValue = 1;
        this.mDiscreteMode = false;
        this.mTrackSize = -1;
        this.mTrackCap = Paint.Cap.BUTT;
        this.mThumbBorderSize = -1;
        this.mThumbRadius = -1;
        this.mThumbFocusRadius = -1;
        this.mThumbPosition = -1.0f;
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -1;
        this.mGravity = 17;
        this.mTravelAnimationDuration = -1;
        this.mTransformAnimationDuration = -1;
        this.mAlwaysFillThumb = false;
        this.mIsRtl = false;
        init(context, attributeSet, 0, 0);
    }

    public Slider(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mMinValue = 0;
        this.mMaxValue = 100;
        this.mStepValue = 1;
        this.mDiscreteMode = false;
        this.mTrackSize = -1;
        this.mTrackCap = Paint.Cap.BUTT;
        this.mThumbBorderSize = -1;
        this.mThumbRadius = -1;
        this.mThumbFocusRadius = -1;
        this.mThumbPosition = -1.0f;
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -1;
        this.mGravity = 17;
        this.mTravelAnimationDuration = -1;
        this.mTransformAnimationDuration = -1;
        this.mAlwaysFillThumb = false;
        this.mIsRtl = false;
        init(context, attributeSet, i, 0);
    }

    public Slider(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mMinValue = 0;
        this.mMaxValue = 100;
        this.mStepValue = 1;
        this.mDiscreteMode = false;
        this.mTrackSize = -1;
        this.mTrackCap = Paint.Cap.BUTT;
        this.mThumbBorderSize = -1;
        this.mThumbRadius = -1;
        this.mThumbFocusRadius = -1;
        this.mThumbPosition = -1.0f;
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -1;
        this.mGravity = 17;
        this.mTravelAnimationDuration = -1;
        this.mTransformAnimationDuration = -1;
        this.mAlwaysFillThumb = false;
        this.mIsRtl = false;
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mPaint = new Paint(1);
        this.mPrimaryColor = ThemeUtil.colorControlActivated(context, -16777216);
        this.mSecondaryColor = ThemeUtil.colorControlNormal(context, -16777216);
        this.mDrawRect = new RectF();
        this.mTempRect = new RectF();
        this.mLeftTrackPath = new Path();
        this.mRightTrackPath = new Path();
        this.mThumbRadiusAnimator = new ThumbRadiusAnimator();
        this.mThumbStrokeAnimator = new ThumbStrokeAnimator();
        this.mThumbMoveAnimator = new ThumbMoveAnimator();
        this.mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.mMemoPoint = new PointF();
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
        getRippleManager().onCreate(this, context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Slider, i, i2);
        int minValue = getMinValue();
        int maxValue = getMaxValue();
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        String str = null;
        int i4 = 0;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i5 = 0;
        while (i4 < indexCount) {
            int index = obtainStyledAttributes.getIndex(i4);
            int i6 = indexCount;
            if (index == R.styleable.Slider_sl_discreteMode) {
                this.mDiscreteMode = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == R.styleable.Slider_sl_primaryColor) {
                this.mPrimaryColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.Slider_sl_secondaryColor) {
                this.mSecondaryColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.Slider_sl_trackSize) {
                this.mTrackSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.Slider_sl_trackCap) {
                int integer = obtainStyledAttributes.getInteger(index, 0);
                if (integer == 0) {
                    this.mTrackCap = Paint.Cap.BUTT;
                } else if (integer == 1) {
                    this.mTrackCap = Paint.Cap.ROUND;
                } else {
                    this.mTrackCap = Paint.Cap.SQUARE;
                }
            } else if (index == R.styleable.Slider_sl_thumbBorderSize) {
                this.mThumbBorderSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.Slider_sl_thumbRadius) {
                this.mThumbRadius = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.Slider_sl_thumbFocusRadius) {
                this.mThumbFocusRadius = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.Slider_sl_travelAnimDuration) {
                int integer2 = obtainStyledAttributes.getInteger(index, 0);
                this.mTravelAnimationDuration = integer2;
                this.mTransformAnimationDuration = integer2;
            } else if (index == R.styleable.Slider_sl_alwaysFillThumb) {
                this.mAlwaysFillThumb = obtainStyledAttributes.getBoolean(R.styleable.Slider_sl_alwaysFillThumb, false);
            } else if (index == R.styleable.Slider_sl_interpolator) {
                this.mInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(R.styleable.Slider_sl_interpolator, 0));
            } else if (index == R.styleable.Slider_android_gravity) {
                this.mGravity = obtainStyledAttributes.getInteger(index, 0);
            } else {
                if (index == R.styleable.Slider_sl_minValue) {
                    minValue = obtainStyledAttributes.getInteger(index, 0);
                } else if (index == R.styleable.Slider_sl_maxValue) {
                    maxValue = obtainStyledAttributes.getInteger(index, 0);
                } else if (index == R.styleable.Slider_sl_stepValue) {
                    this.mStepValue = obtainStyledAttributes.getInteger(index, 0);
                } else if (index == R.styleable.Slider_sl_value) {
                    i3 = obtainStyledAttributes.getInteger(index, 0);
                    z2 = true;
                } else {
                    if (index == R.styleable.Slider_sl_fontFamily) {
                        str = obtainStyledAttributes.getString(index);
                    } else if (index == R.styleable.Slider_sl_textStyle) {
                        i5 = obtainStyledAttributes.getInteger(index, 0);
                    } else if (index == R.styleable.Slider_sl_textColor) {
                        this.mTextColor = obtainStyledAttributes.getColor(index, 0);
                    } else if (index == R.styleable.Slider_sl_textSize) {
                        this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                    } else if (index == R.styleable.Slider_android_enabled) {
                        setEnabled(obtainStyledAttributes.getBoolean(index, true));
                    } else if (index == R.styleable.Slider_sl_baselineOffset) {
                        this.mBaselineOffset = obtainStyledAttributes.getDimensionPixelOffset(index, 0);
                    }
                    z3 = true;
                }
                z = true;
            }
            i4++;
            indexCount = i6;
        }
        obtainStyledAttributes.recycle();
        if (this.mTrackSize < 0) {
            this.mTrackSize = ThemeUtil.dpToPx(context, 2);
        }
        if (this.mThumbBorderSize < 0) {
            this.mThumbBorderSize = ThemeUtil.dpToPx(context, 2);
        }
        if (this.mThumbRadius < 0) {
            this.mThumbRadius = ThemeUtil.dpToPx(context, 10);
        }
        if (this.mThumbFocusRadius < 0) {
            this.mThumbFocusRadius = ThemeUtil.dpToPx(context, 14);
        }
        if (this.mTravelAnimationDuration < 0) {
            int integer3 = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
            this.mTravelAnimationDuration = integer3;
            this.mTransformAnimationDuration = integer3;
        }
        if (this.mInterpolator == null) {
            this.mInterpolator = new DecelerateInterpolator();
        }
        if (z) {
            setValueRange(minValue, maxValue, false);
        }
        if (z2) {
            setValue(i3, false);
        } else if (this.mThumbPosition < 0.0f) {
            setValue(this.mMinValue, false);
        }
        if (z3) {
            this.mTypeface = TypefaceUtil.load(context, str, i5);
        }
        if (this.mTextSize < 0) {
            this.mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.abc_text_size_small_material);
        }
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mPaint.setTypeface(this.mTypeface);
        measureText();
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

    private void measureText() {
        if (this.mValueText == null) {
            return;
        }
        Rect rect = new Rect();
        this.mPaint.setTextSize(this.mTextSize);
        float measureText = this.mPaint.measureText(this.mValueText);
        float sqrt = (float) (((this.mThumbRadius * Math.sqrt(2.0d)) * 2.0d) - ThemeUtil.dpToPx(getContext(), 8));
        if (measureText > sqrt) {
            this.mPaint.setTextSize((this.mTextSize * sqrt) / measureText);
        }
        Paint paint = this.mPaint;
        String str = this.mValueText;
        paint.getTextBounds(str, 0, str.length(), rect);
        this.mTextHeight = rect.height();
    }

    private String getValueText() {
        int value = getValue();
        if (this.mValueText == null || this.mMemoValue != value) {
            this.mMemoValue = value;
            ValueDescriptionProvider valueDescriptionProvider = this.mValueDescriptionProvider;
            this.mValueText = valueDescriptionProvider == null ? String.valueOf(value) : valueDescriptionProvider.getDescription(value);
            measureText();
        }
        return this.mValueText;
    }

    public int getMinValue() {
        return this.mMinValue;
    }

    public int getMaxValue() {
        return this.mMaxValue;
    }

    public int getStepValue() {
        return this.mStepValue;
    }

    public void setValueRange(int i, int i2, boolean z) {
        if (i2 >= i) {
            if (i == this.mMinValue && i2 == this.mMaxValue) {
                return;
            }
            float exactValue = getExactValue();
            float position = getPosition();
            this.mMinValue = i;
            this.mMaxValue = i2;
            setValue(exactValue, z);
            if (this.mOnPositionChangeListener == null || position != getPosition() || exactValue == getExactValue()) {
                return;
            }
            this.mOnPositionChangeListener.onPositionChanged(this, false, position, position, Math.round(exactValue), getValue());
        }
    }

    public int getValue() {
        return Math.round(getExactValue());
    }

    public float getExactValue() {
        return ((this.mMaxValue - this.mMinValue) * getPosition()) + this.mMinValue;
    }

    public float getPosition() {
        return this.mThumbMoveAnimator.isRunning() ? this.mThumbMoveAnimator.getPosition() : this.mThumbPosition;
    }

    public void setPosition(float f, boolean z) {
        setPosition(f, z, z, false);
    }

    private void setPosition(float f, boolean z, boolean z2, boolean z3) {
        OnPositionChangeListener onPositionChangeListener;
        boolean z4 = getPosition() != f;
        int value = getValue();
        float position = getPosition();
        if (!z || !this.mThumbMoveAnimator.startAnimation(f)) {
            this.mThumbPosition = f;
            if (z2) {
                if (!this.mIsDragging) {
                    this.mThumbRadiusAnimator.startAnimation(this.mThumbRadius);
                }
                this.mThumbStrokeAnimator.startAnimation(f == 0.0f ? 0 : 1);
            } else {
                this.mThumbCurrentRadius = this.mThumbRadius;
                this.mThumbFillPercent = (this.mAlwaysFillThumb || f != 0.0f) ? 1.0f : 0.0f;
                invalidate();
            }
        }
        int value2 = getValue();
        float position2 = getPosition();
        if (!z4 || (onPositionChangeListener = this.mOnPositionChangeListener) == null) {
            return;
        }
        onPositionChangeListener.onPositionChanged(this, z3, position, position2, value, value2);
    }

    public void setPrimaryColor(int i) {
        this.mPrimaryColor = i;
        invalidate();
    }

    public void setSecondaryColor(int i) {
        this.mSecondaryColor = i;
        invalidate();
    }

    public void setAlwaysFillThumb(boolean z) {
        this.mAlwaysFillThumb = z;
    }

    public void setValue(float f, boolean z) {
        setPosition((Math.min(this.mMaxValue, Math.max(f, this.mMinValue)) - this.mMinValue) / (this.mMaxValue - r0), z);
    }

    public void setOnPositionChangeListener(OnPositionChangeListener onPositionChangeListener) {
        this.mOnPositionChangeListener = onPositionChangeListener;
    }

    public void setValueDescriptionProvider(ValueDescriptionProvider valueDescriptionProvider) {
        this.mValueDescriptionProvider = valueDescriptionProvider;
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
        return ((this.mDiscreteMode ? (int) (this.mThumbRadius * Math.sqrt(2.0d)) : this.mThumbFocusRadius) * 4) + getPaddingLeft() + getPaddingRight();
    }

    @Override // android.view.View
    public int getSuggestedMinimumHeight() {
        return (this.mDiscreteMode ? (int) (this.mThumbRadius * (Math.sqrt(2.0d) + 4.0d)) : this.mThumbFocusRadius * 2) + getPaddingTop() + getPaddingBottom();
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
    public int getBaseline() {
        int paddingTop;
        int i;
        int measuredHeight;
        int paddingBottom;
        int round;
        int i2 = this.mGravity & 112;
        if (this.mDiscreteMode) {
            int sqrt = (int) (this.mThumbRadius * (Math.sqrt(2.0d) + 4.0d));
            int i3 = this.mThumbRadius * 2;
            if (i2 == 48) {
                paddingTop = Math.max(getPaddingTop(), sqrt - i3);
                i = this.mThumbRadius;
                round = paddingTop + i;
            } else if (i2 == 80) {
                measuredHeight = getMeasuredHeight();
                paddingBottom = getPaddingBottom();
                round = measuredHeight - paddingBottom;
            } else {
                round = Math.round(Math.max((getMeasuredHeight() - i3) / 2.0f, sqrt - i3) + this.mThumbRadius);
            }
        } else {
            int i4 = this.mThumbFocusRadius * 2;
            if (i2 == 48) {
                paddingTop = getPaddingTop();
                i = this.mThumbFocusRadius;
                round = paddingTop + i;
            } else if (i2 == 80) {
                measuredHeight = getMeasuredHeight();
                paddingBottom = getPaddingBottom();
                round = measuredHeight - paddingBottom;
            } else {
                round = Math.round(((getMeasuredHeight() - i4) / 2.0f) + this.mThumbFocusRadius);
            }
        }
        return round + this.mBaselineOffset;
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        this.mDrawRect.left = getPaddingLeft() + this.mThumbRadius;
        this.mDrawRect.right = (i - getPaddingRight()) - this.mThumbRadius;
        int i5 = this.mGravity & 112;
        if (this.mDiscreteMode) {
            int sqrt = (int) (this.mThumbRadius * (Math.sqrt(2.0d) + 4.0d));
            int i6 = this.mThumbRadius * 2;
            if (i5 == 48) {
                this.mDrawRect.top = Math.max(getPaddingTop(), sqrt - i6);
                RectF rectF = this.mDrawRect;
                rectF.bottom = rectF.top + i6;
                return;
            }
            if (i5 == 80) {
                this.mDrawRect.bottom = i2 - getPaddingBottom();
                RectF rectF2 = this.mDrawRect;
                rectF2.top = rectF2.bottom - i6;
                return;
            }
            this.mDrawRect.top = Math.max((i2 - i6) / 2.0f, sqrt - i6);
            RectF rectF3 = this.mDrawRect;
            rectF3.bottom = rectF3.top + i6;
            return;
        }
        int i7 = this.mThumbFocusRadius * 2;
        if (i5 == 48) {
            this.mDrawRect.top = getPaddingTop();
            RectF rectF4 = this.mDrawRect;
            rectF4.bottom = rectF4.top + i7;
            return;
        }
        if (i5 == 80) {
            this.mDrawRect.bottom = i2 - getPaddingBottom();
            RectF rectF5 = this.mDrawRect;
            rectF5.top = rectF5.bottom - i7;
            return;
        }
        this.mDrawRect.top = (i2 - i7) / 2.0f;
        RectF rectF6 = this.mDrawRect;
        rectF6.bottom = rectF6.top + i7;
    }

    private boolean isThumbHit(float f, float f2, float f3) {
        float width = (this.mDrawRect.width() * this.mThumbPosition) + this.mDrawRect.left;
        float centerY = this.mDrawRect.centerY();
        return f >= width - f3 && f <= width + f3 && f2 >= centerY - f3 && f2 < centerY + f3;
    }

    private double distance(float f, float f2, float f3, float f4) {
        return Math.sqrt(Math.pow(f - f3, 2.0d) + Math.pow(f2 - f4, 2.0d));
    }

    private float correctPosition(float f) {
        if (!this.mDiscreteMode) {
            return f;
        }
        int i = this.mMaxValue - this.mMinValue;
        float f2 = i;
        int round = Math.round(f * f2);
        int i2 = this.mStepValue;
        int i3 = round / i2;
        int i4 = i3 * i2;
        int min = Math.min(i, (i3 + 1) * i2);
        return (round - i4 < min - round ? i4 : min) / f2;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        super.onTouchEvent(motionEvent);
        getRippleManager().onTouchEvent(this, motionEvent);
        if (!isEnabled()) {
            return false;
        }
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (this.mIsRtl) {
            x = (this.mDrawRect.centerX() * 2.0f) - x;
        }
        int action = motionEvent.getAction();
        if (action == 0) {
            this.mIsDragging = isThumbHit(x, y, (float) this.mThumbRadius) && !this.mThumbMoveAnimator.isRunning();
            this.mMemoPoint.set(x, y);
            if (this.mIsDragging) {
                this.mThumbRadiusAnimator.startAnimation(this.mDiscreteMode ? 0 : this.mThumbFocusRadius);
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }
            }
        } else if (action != 1) {
            if (action == 2) {
                if (this.mIsDragging) {
                    if (this.mDiscreteMode) {
                        setPosition(correctPosition(Math.min(1.0f, Math.max(0.0f, (x - this.mDrawRect.left) / this.mDrawRect.width()))), true, true, true);
                    } else {
                        setPosition(Math.min(1.0f, Math.max(0.0f, this.mThumbPosition + ((x - this.mMemoPoint.x) / this.mDrawRect.width()))), false, true, true);
                        this.mMemoPoint.x = x;
                        invalidate();
                    }
                }
            } else if (action == 3 && this.mIsDragging) {
                this.mIsDragging = false;
                setPosition(getPosition(), true, true, true);
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(false);
                }
            }
        } else if (this.mIsDragging) {
            this.mIsDragging = false;
            setPosition(getPosition(), true, true, true);
            if (getParent() != null) {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        } else if (distance(this.mMemoPoint.x, this.mMemoPoint.y, x, y) <= this.mTouchSlop) {
            setPosition(correctPosition(Math.min(1.0f, Math.max(0.0f, (x - this.mDrawRect.left) / this.mDrawRect.width()))), true, true, true);
        }
        return true;
    }

    private void getTrackPath(float f, float f2, float f3) {
        float f4 = this.mTrackSize / 2.0f;
        this.mLeftTrackPath.reset();
        this.mRightTrackPath.reset();
        if (f3 - 1.0f < f4) {
            if (this.mTrackCap != Paint.Cap.ROUND) {
                if (f > this.mDrawRect.left) {
                    float f5 = f2 - f4;
                    this.mLeftTrackPath.moveTo(this.mDrawRect.left, f5);
                    this.mLeftTrackPath.lineTo(f, f5);
                    float f6 = f2 + f4;
                    this.mLeftTrackPath.lineTo(f, f6);
                    this.mLeftTrackPath.lineTo(this.mDrawRect.left, f6);
                    this.mLeftTrackPath.close();
                }
                if (f < this.mDrawRect.right) {
                    float f7 = f2 + f4;
                    this.mRightTrackPath.moveTo(this.mDrawRect.right, f7);
                    this.mRightTrackPath.lineTo(f, f7);
                    float f8 = f2 - f4;
                    this.mRightTrackPath.lineTo(f, f8);
                    this.mRightTrackPath.lineTo(this.mDrawRect.right, f8);
                    this.mRightTrackPath.close();
                    return;
                }
                return;
            }
            if (f > this.mDrawRect.left) {
                float f9 = f2 - f4;
                float f10 = f2 + f4;
                this.mTempRect.set(this.mDrawRect.left, f9, this.mDrawRect.left + this.mTrackSize, f10);
                this.mLeftTrackPath.arcTo(this.mTempRect, 90.0f, 180.0f);
                this.mLeftTrackPath.lineTo(f, f9);
                this.mLeftTrackPath.lineTo(f, f10);
                this.mLeftTrackPath.close();
            }
            if (f < this.mDrawRect.right) {
                float f11 = f2 - f4;
                float f12 = f2 + f4;
                this.mTempRect.set(this.mDrawRect.right - this.mTrackSize, f11, this.mDrawRect.right, f12);
                this.mRightTrackPath.arcTo(this.mTempRect, 270.0f, 180.0f);
                this.mRightTrackPath.lineTo(f, f12);
                this.mRightTrackPath.lineTo(f, f11);
                this.mRightTrackPath.close();
                return;
            }
            return;
        }
        if (this.mTrackCap != Paint.Cap.ROUND) {
            float f13 = f - f3;
            float f14 = f + f3;
            this.mTempRect.set(f13 + 1.0f, (f2 - f3) + 1.0f, f14 - 1.0f, (f2 + f3) - 1.0f);
            float asin = (float) ((Math.asin(f4 / r6) / 3.141592653589793d) * 180.0d);
            if (f13 > this.mDrawRect.left) {
                this.mLeftTrackPath.moveTo(this.mDrawRect.left, f2 - f4);
                this.mLeftTrackPath.arcTo(this.mTempRect, 180.0f + asin, (-asin) * 2.0f);
                this.mLeftTrackPath.lineTo(this.mDrawRect.left, f2 + f4);
                this.mLeftTrackPath.close();
            }
            if (f14 < this.mDrawRect.right) {
                this.mRightTrackPath.moveTo(this.mDrawRect.right, f2 - f4);
                this.mRightTrackPath.arcTo(this.mTempRect, -asin, asin * 2.0f);
                this.mRightTrackPath.lineTo(this.mDrawRect.right, f2 + f4);
                this.mRightTrackPath.close();
                return;
            }
            return;
        }
        float asin2 = (float) ((Math.asin(f4 / r6) / 3.141592653589793d) * 180.0d);
        float f15 = f - f3;
        if (f15 > this.mDrawRect.left) {
            float acos = (float) ((Math.acos(Math.max(0.0f, (((this.mDrawRect.left + f4) - f) + f3) / f4)) / 3.141592653589793d) * 180.0d);
            this.mTempRect.set(this.mDrawRect.left, f2 - f4, this.mDrawRect.left + this.mTrackSize, f2 + f4);
            this.mLeftTrackPath.arcTo(this.mTempRect, 180.0f - acos, acos * 2.0f);
            this.mTempRect.set(f15 + 1.0f, (f2 - f3) + 1.0f, (f + f3) - 1.0f, (f2 + f3) - 1.0f);
            this.mLeftTrackPath.arcTo(this.mTempRect, 180.0f + asin2, (-asin2) * 2.0f);
            this.mLeftTrackPath.close();
        }
        float f16 = f + f3;
        if (f16 < this.mDrawRect.right) {
            double acos2 = (float) Math.acos(Math.max(0.0f, ((f16 - this.mDrawRect.right) + f4) / f4));
            double d = f4;
            this.mRightTrackPath.moveTo((float) ((this.mDrawRect.right - f4) + (Math.cos(acos2) * d)), (float) (f2 + (Math.sin(acos2) * d)));
            float f17 = (float) ((acos2 / 3.141592653589793d) * 180.0d);
            this.mTempRect.set(this.mDrawRect.right - this.mTrackSize, f2 - f4, this.mDrawRect.right, f4 + f2);
            this.mRightTrackPath.arcTo(this.mTempRect, f17, (-f17) * 2.0f);
            this.mTempRect.set(f15 + 1.0f, (f2 - f3) + 1.0f, f16 - 1.0f, (f2 + f3) - 1.0f);
            this.mRightTrackPath.arcTo(this.mTempRect, -asin2, asin2 * 2.0f);
            this.mRightTrackPath.close();
        }
    }

    private Path getMarkPath(Path path, float f, float f2, float f3, float f4) {
        Path path2;
        if (path == null) {
            path2 = new Path();
        } else {
            path.reset();
            path2 = path;
        }
        float f5 = f - f3;
        float f6 = f + f3;
        float f7 = f2 + f3;
        float f8 = f2 - (f3 * f4);
        float atan2 = (float) ((Math.atan2(f2 - f8, f6 - f) * 180.0d) / 3.141592653589793d);
        float distance = (float) distance(f, f8, f5, f2);
        this.mTempRect.set(f - distance, f8 - distance, f + distance, f8 + distance);
        path2.moveTo(f5, f2);
        path2.arcTo(this.mTempRect, 180.0f - atan2, (atan2 * 2.0f) + 180.0f);
        if (f4 > 0.9f) {
            path2.lineTo(f, f7);
        } else {
            float f9 = (f6 + f) / 2.0f;
            float f10 = (f2 + f7) / 2.0f;
            double distance2 = distance(f6, f2, f9, f10) / Math.tan(((1.0f - f4) * 3.141592653589793d) / 4.0d);
            float cos = (float) (f9 - (Math.cos(0.7853981633974483d) * distance2));
            float sin = (float) (f10 - (Math.sin(0.7853981633974483d) * distance2));
            double d = f2 - sin;
            float atan22 = (float) ((Math.atan2(d, f6 - cos) * 180.0d) / 3.141592653589793d);
            double d2 = f7 - sin;
            float atan23 = (float) ((Math.atan2(d2, f - cos) * 180.0d) / 3.141592653589793d);
            float distance3 = (float) distance(cos, sin, f6, f2);
            float f11 = sin - distance3;
            float f12 = sin + distance3;
            this.mTempRect.set(cos - distance3, f11, cos + distance3, f12);
            path2.arcTo(this.mTempRect, atan22, atan23 - atan22);
            float f13 = (2.0f * f) - cos;
            float atan24 = (float) ((Math.atan2(d2, f - f13) * 180.0d) / 3.141592653589793d);
            float atan25 = (float) ((Math.atan2(d, f5 - f13) * 180.0d) / 3.141592653589793d);
            this.mTempRect.set(f13 - distance3, f11, f13 + distance3, f12);
            path2.arcTo(this.mTempRect, 0.7853982f + atan24, atan25 - atan24);
        }
        path2.close();
        return path2;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float width = (this.mDrawRect.width() * this.mThumbPosition) + this.mDrawRect.left;
        if (this.mIsRtl) {
            width = (this.mDrawRect.centerX() * 2.0f) - width;
        }
        float centerY = this.mDrawRect.centerY();
        int middleColor = ColorUtil.getMiddleColor(this.mSecondaryColor, isEnabled() ? this.mPrimaryColor : this.mSecondaryColor, this.mThumbFillPercent);
        getTrackPath(width, centerY, this.mThumbCurrentRadius);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mIsRtl ? middleColor : this.mSecondaryColor);
        canvas.drawPath(this.mRightTrackPath, this.mPaint);
        this.mPaint.setColor(this.mIsRtl ? this.mSecondaryColor : middleColor);
        canvas.drawPath(this.mLeftTrackPath, this.mPaint);
        this.mPaint.setColor(middleColor);
        if (this.mDiscreteMode) {
            float f = this.mThumbCurrentRadius;
            int i = this.mThumbRadius;
            float f2 = 1.0f - (f / i);
            if (f2 > 0.0f) {
                this.mMarkPath = getMarkPath(this.mMarkPath, width, centerY, i, f2);
                this.mPaint.setStyle(Paint.Style.FILL);
                int save = canvas.save();
                canvas.translate(0.0f, (-this.mThumbRadius) * 2 * f2);
                canvas.drawPath(this.mMarkPath, this.mPaint);
                this.mPaint.setColor(ColorUtil.getColor(this.mTextColor, f2));
                canvas.drawText(getValueText(), width, ((this.mTextHeight / 2.0f) + centerY) - (this.mThumbRadius * f2), this.mPaint);
                canvas.restoreToCount(save);
            }
            float f3 = isEnabled() ? this.mThumbCurrentRadius : this.mThumbCurrentRadius - this.mThumbBorderSize;
            if (f3 > 0.0f) {
                this.mPaint.setColor(middleColor);
                canvas.drawCircle(width, centerY, f3, this.mPaint);
                return;
            }
            return;
        }
        float f4 = isEnabled() ? this.mThumbCurrentRadius : this.mThumbCurrentRadius - this.mThumbBorderSize;
        float f5 = this.mThumbFillPercent;
        if (f5 == 1.0f) {
            this.mPaint.setStyle(Paint.Style.FILL);
        } else {
            int i2 = this.mThumbBorderSize;
            float f6 = ((f4 - i2) * f5) + i2;
            f4 -= f6 / 2.0f;
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(f6);
        }
        canvas.drawCircle(width, centerY, f4, this.mPaint);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ThumbRadiusAnimator implements Runnable {
        int mRadius;
        boolean mRunning = false;
        float mStartRadius;
        long mStartTime;

        ThumbRadiusAnimator() {
        }

        public void resetAnimation() {
            this.mStartTime = SystemClock.uptimeMillis();
            this.mStartRadius = Slider.this.mThumbCurrentRadius;
        }

        public boolean startAnimation(int i) {
            if (Slider.this.mThumbCurrentRadius == i) {
                return false;
            }
            this.mRadius = i;
            if (Slider.this.getHandler() != null) {
                resetAnimation();
                this.mRunning = true;
                Slider.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
                Slider.this.invalidate();
                return true;
            }
            Slider.this.mThumbCurrentRadius = this.mRadius;
            Slider.this.invalidate();
            return false;
        }

        public void stopAnimation() {
            this.mRunning = false;
            Slider.this.mThumbCurrentRadius = this.mRadius;
            if (Slider.this.getHandler() != null) {
                Slider.this.getHandler().removeCallbacks(this);
            }
            Slider.this.invalidate();
        }

        @Override // java.lang.Runnable
        public void run() {
            float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / Slider.this.mTransformAnimationDuration);
            float interpolation = Slider.this.mInterpolator.getInterpolation(min);
            Slider slider = Slider.this;
            float f = this.mRadius;
            float f2 = this.mStartRadius;
            slider.mThumbCurrentRadius = ((f - f2) * interpolation) + f2;
            if (min == 1.0f) {
                stopAnimation();
            }
            if (this.mRunning) {
                if (Slider.this.getHandler() != null) {
                    Slider.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
                } else {
                    stopAnimation();
                }
            }
            Slider.this.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ThumbStrokeAnimator implements Runnable {
        int mFillPercent;
        boolean mRunning = false;
        float mStartFillPercent;
        long mStartTime;

        ThumbStrokeAnimator() {
        }

        public void resetAnimation() {
            this.mStartTime = SystemClock.uptimeMillis();
            this.mStartFillPercent = Slider.this.mThumbFillPercent;
        }

        public boolean startAnimation(int i) {
            if (Slider.this.mThumbFillPercent == i) {
                return false;
            }
            this.mFillPercent = i;
            if (Slider.this.getHandler() != null) {
                resetAnimation();
                this.mRunning = true;
                Slider.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
                Slider.this.invalidate();
                return true;
            }
            Slider slider = Slider.this;
            slider.mThumbFillPercent = slider.mAlwaysFillThumb ? 1.0f : this.mFillPercent;
            Slider.this.invalidate();
            return false;
        }

        public void stopAnimation() {
            this.mRunning = false;
            Slider slider = Slider.this;
            slider.mThumbFillPercent = slider.mAlwaysFillThumb ? 1.0f : this.mFillPercent;
            if (Slider.this.getHandler() != null) {
                Slider.this.getHandler().removeCallbacks(this);
            }
            Slider.this.invalidate();
        }

        @Override // java.lang.Runnable
        public void run() {
            float f;
            float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / Slider.this.mTransformAnimationDuration);
            float interpolation = Slider.this.mInterpolator.getInterpolation(min);
            Slider slider = Slider.this;
            if (slider.mAlwaysFillThumb) {
                f = 1.0f;
            } else {
                float f2 = this.mFillPercent;
                float f3 = this.mStartFillPercent;
                f = ((f2 - f3) * interpolation) + f3;
            }
            slider.mThumbFillPercent = f;
            if (min == 1.0f) {
                stopAnimation();
            }
            if (this.mRunning) {
                if (Slider.this.getHandler() != null) {
                    Slider.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
                } else {
                    stopAnimation();
                }
            }
            Slider.this.invalidate();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ThumbMoveAnimator implements Runnable {
        int mDuration;
        float mFillPercent;
        float mPosition;
        boolean mRunning = false;
        float mStartFillPercent;
        float mStartPosition;
        float mStartRadius;
        long mStartTime;

        ThumbMoveAnimator() {
        }

        public boolean isRunning() {
            return this.mRunning;
        }

        public float getPosition() {
            return this.mPosition;
        }

        public void resetAnimation() {
            this.mStartTime = SystemClock.uptimeMillis();
            this.mStartPosition = Slider.this.mThumbPosition;
            this.mStartFillPercent = Slider.this.mThumbFillPercent;
            this.mStartRadius = Slider.this.mThumbCurrentRadius;
            this.mFillPercent = this.mPosition != 0.0f ? 1.0f : 0.0f;
            this.mDuration = (!Slider.this.mDiscreteMode || Slider.this.mIsDragging) ? Slider.this.mTravelAnimationDuration : (Slider.this.mTransformAnimationDuration * 2) + Slider.this.mTravelAnimationDuration;
        }

        public boolean startAnimation(float f) {
            if (Slider.this.mThumbPosition == f) {
                return false;
            }
            this.mPosition = f;
            if (Slider.this.getHandler() == null) {
                Slider.this.mThumbPosition = f;
                Slider.this.invalidate();
                return false;
            }
            resetAnimation();
            this.mRunning = true;
            Slider.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
            Slider.this.invalidate();
            return true;
        }

        public void stopAnimation() {
            this.mRunning = false;
            Slider slider = Slider.this;
            slider.mThumbCurrentRadius = (slider.mDiscreteMode && Slider.this.mIsDragging) ? 0.0f : Slider.this.mThumbRadius;
            Slider slider2 = Slider.this;
            slider2.mThumbFillPercent = slider2.mAlwaysFillThumb ? 1.0f : this.mFillPercent;
            Slider.this.mThumbPosition = this.mPosition;
            if (Slider.this.getHandler() != null) {
                Slider.this.getHandler().removeCallbacks(this);
            }
            Slider.this.invalidate();
        }

        @Override // java.lang.Runnable
        public void run() {
            float f;
            float f2;
            float f3;
            float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mDuration);
            float interpolation = Slider.this.mInterpolator.getInterpolation(min);
            if (Slider.this.mDiscreteMode) {
                if (!Slider.this.mIsDragging) {
                    float f4 = Slider.this.mTravelAnimationDuration / this.mDuration;
                    float f5 = (Slider.this.mTravelAnimationDuration + Slider.this.mTransformAnimationDuration) / this.mDuration;
                    if (min < f4) {
                        float interpolation2 = Slider.this.mInterpolator.getInterpolation(min / f4);
                        Slider.this.mThumbCurrentRadius = this.mStartRadius * (1.0f - interpolation2);
                        Slider slider = Slider.this;
                        float f6 = this.mPosition;
                        float f7 = this.mStartPosition;
                        slider.mThumbPosition = ((f6 - f7) * interpolation2) + f7;
                        Slider slider2 = Slider.this;
                        if (slider2.mAlwaysFillThumb) {
                            f2 = 1.0f;
                        } else {
                            float f8 = this.mFillPercent;
                            float f9 = this.mStartFillPercent;
                            f2 = ((f8 - f9) * interpolation2) + f9;
                        }
                        slider2.mThumbFillPercent = f2;
                    } else if (min > f5) {
                        Slider.this.mThumbCurrentRadius = (r2.mThumbRadius * (min - f5)) / (1.0f - f5);
                    }
                } else {
                    Slider slider3 = Slider.this;
                    float f10 = this.mPosition;
                    float f11 = this.mStartPosition;
                    slider3.mThumbPosition = ((f10 - f11) * interpolation) + f11;
                    Slider slider4 = Slider.this;
                    if (slider4.mAlwaysFillThumb) {
                        f3 = 1.0f;
                    } else {
                        float f12 = this.mFillPercent;
                        float f13 = this.mStartFillPercent;
                        f3 = ((f12 - f13) * interpolation) + f13;
                    }
                    slider4.mThumbFillPercent = f3;
                }
            } else {
                Slider slider5 = Slider.this;
                float f14 = this.mPosition;
                float f15 = this.mStartPosition;
                slider5.mThumbPosition = ((f14 - f15) * interpolation) + f15;
                Slider slider6 = Slider.this;
                if (slider6.mAlwaysFillThumb) {
                    f = 1.0f;
                } else {
                    float f16 = this.mFillPercent;
                    float f17 = this.mStartFillPercent;
                    f = ((f16 - f17) * interpolation) + f17;
                }
                slider6.mThumbFillPercent = f;
                double d = min;
                if (d < 0.2d) {
                    Slider.this.mThumbCurrentRadius = Math.max(r2.mThumbRadius + (Slider.this.mThumbBorderSize * min * 5.0f), Slider.this.mThumbCurrentRadius);
                } else if (d >= 0.8d) {
                    Slider.this.mThumbCurrentRadius = r2.mThumbRadius + (Slider.this.mThumbBorderSize * (5.0f - (min * 5.0f)));
                }
            }
            if (min == 1.0f) {
                stopAnimation();
            }
            if (this.mRunning) {
                if (Slider.this.getHandler() != null) {
                    Slider.this.getHandler().postAtTime(this, SystemClock.uptimeMillis() + 16);
                } else {
                    stopAnimation();
                }
            }
            Slider.this.invalidate();
        }
    }

    @Override // android.view.View
    protected Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.position = getPosition();
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setPosition(savedState.position, false);
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.rey.material.widget.Slider.SavedState.1
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
        float position;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.position = parcel.readFloat();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeFloat(this.position);
        }

        public String toString() {
            return "Slider.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " pos=" + this.position + "}";
        }
    }
}
