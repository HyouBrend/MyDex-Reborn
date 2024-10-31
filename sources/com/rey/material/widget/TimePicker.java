package com.rey.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.format.DateFormat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.util.ColorUtil;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.TypefaceUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class TimePicker extends View implements ThemeManager.OnThemeChangedListener {
    public static final int MODE_HOUR = 0;
    public static final int MODE_MINUTE = 1;
    private boolean m24Hour;
    private int mAnimDuration;
    private float mAnimProgress;
    private int mBackgroundColor;
    private PointF mCenterPoint;
    protected int mCurrentStyle;
    private boolean mEdited;
    private int mHour;
    private Interpolator mInInterpolator;
    private float mInnerRadius;
    private float[] mLocations;
    private int mMinute;
    private int mMode;
    private OnTimeChangedListener mOnTimeChangedListener;
    private Interpolator mOutInterpolator;
    private float mOuterRadius;
    private Paint mPaint;
    private Rect mRect;
    private boolean mRunning;
    private float mSecondInnerRadius;
    private int mSelectionColor;
    private int mSelectionRadius;
    private long mStartTime;
    protected int mStyleId;
    private int mTextColor;
    private int mTextHighlightColor;
    private int mTextSize;
    private int mTickSize;
    private String[] mTicks;
    private Typeface mTypeface;
    private final Runnable mUpdater;

    /* loaded from: classes2.dex */
    public interface OnTimeChangedListener {
        void onHourChanged(int i, int i2);

        void onMinuteChanged(int i, int i2);

        void onModeChanged(int i);
    }

    private float getAngle(int i, int i2) {
        double d;
        if (i2 == 0) {
            d = 0.5235987755982988d;
        } else {
            if (i2 != 1) {
                return 0.0f;
            }
            d = 0.10471975511965977d;
        }
        return (float) ((i * d) - 1.5707963267948966d);
    }

    public TimePicker(Context context) {
        super(context);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mSelectionRadius = -1;
        this.mTickSize = -1;
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -16777216;
        this.mTextHighlightColor = -1;
        this.m24Hour = true;
        this.mAnimDuration = -1;
        this.mLocations = new float[72];
        this.mMode = 0;
        this.mHour = 0;
        this.mMinute = 0;
        this.mEdited = false;
        this.mUpdater = new Runnable() { // from class: com.rey.material.widget.TimePicker.1
            @Override // java.lang.Runnable
            public void run() {
                TimePicker.this.update();
            }
        };
        init(context, null, 0, 0);
    }

    public TimePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mSelectionRadius = -1;
        this.mTickSize = -1;
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -16777216;
        this.mTextHighlightColor = -1;
        this.m24Hour = true;
        this.mAnimDuration = -1;
        this.mLocations = new float[72];
        this.mMode = 0;
        this.mHour = 0;
        this.mMinute = 0;
        this.mEdited = false;
        this.mUpdater = new Runnable() { // from class: com.rey.material.widget.TimePicker.1
            @Override // java.lang.Runnable
            public void run() {
                TimePicker.this.update();
            }
        };
        init(context, attributeSet, 0, 0);
    }

    public TimePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mSelectionRadius = -1;
        this.mTickSize = -1;
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -16777216;
        this.mTextHighlightColor = -1;
        this.m24Hour = true;
        this.mAnimDuration = -1;
        this.mLocations = new float[72];
        this.mMode = 0;
        this.mHour = 0;
        this.mMinute = 0;
        this.mEdited = false;
        this.mUpdater = new Runnable() { // from class: com.rey.material.widget.TimePicker.1
            @Override // java.lang.Runnable
            public void run() {
                TimePicker.this.update();
            }
        };
        init(context, attributeSet, i, 0);
    }

    public TimePicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mSelectionRadius = -1;
        this.mTickSize = -1;
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -16777216;
        this.mTextHighlightColor = -1;
        this.m24Hour = true;
        this.mAnimDuration = -1;
        this.mLocations = new float[72];
        this.mMode = 0;
        this.mHour = 0;
        this.mMinute = 0;
        this.mEdited = false;
        this.mUpdater = new Runnable() { // from class: com.rey.material.widget.TimePicker.1
            @Override // java.lang.Runnable
            public void run() {
                TimePicker.this.update();
            }
        };
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mPaint = new Paint(1);
        this.mRect = new Rect();
        this.mBackgroundColor = ColorUtil.getColor(ThemeUtil.colorPrimary(context, -16777216), 0.25f);
        this.mSelectionColor = ThemeUtil.colorPrimary(context, -16777216);
        initTickLabels();
        setWillNotDraw(false);
        applyStyle(context, attributeSet, i, i2);
        if (isInEditMode()) {
            return;
        }
        this.mStyleId = ThemeManager.getStyleId(context, attributeSet, i, i2);
    }

    private void initTickLabels() {
        this.mTicks = new String[36];
        int i = 0;
        while (i < 23) {
            int i2 = i + 1;
            this.mTicks[i] = String.format("%2d", Integer.valueOf(i2));
            i = i2;
        }
        this.mTicks[23] = String.format("%2d", 0);
        String[] strArr = this.mTicks;
        strArr[35] = strArr[23];
        for (int i3 = 24; i3 < 35; i3++) {
            this.mTicks[i3] = String.format("%2d", Integer.valueOf((i3 - 23) * 5));
        }
    }

    public void applyStyle(int i) {
        ViewUtil.applyStyle(this, i);
        applyStyle(getContext(), null, 0, i);
    }

    protected void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TimePicker, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        String str = null;
        int i3 = -1;
        boolean z = false;
        for (int i4 = 0; i4 < indexCount; i4++) {
            int index = obtainStyledAttributes.getIndex(i4);
            if (index == R.styleable.TimePicker_tp_backgroundColor) {
                this.mBackgroundColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.TimePicker_tp_selectionColor) {
                this.mSelectionColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.TimePicker_tp_selectionRadius) {
                this.mSelectionRadius = obtainStyledAttributes.getDimensionPixelOffset(index, 0);
            } else if (index == R.styleable.TimePicker_tp_tickSize) {
                this.mTickSize = obtainStyledAttributes.getDimensionPixelOffset(index, 0);
            } else if (index == R.styleable.TimePicker_tp_textSize) {
                this.mTextSize = obtainStyledAttributes.getDimensionPixelOffset(index, 0);
            } else if (index == R.styleable.TimePicker_tp_textColor) {
                this.mTextColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.TimePicker_tp_textHighlightColor) {
                this.mTextHighlightColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.TimePicker_tp_animDuration) {
                this.mAnimDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.TimePicker_tp_inInterpolator) {
                this.mInInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.TimePicker_tp_outInterpolator) {
                this.mOutInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.TimePicker_tp_mode) {
                setMode(obtainStyledAttributes.getInteger(index, 0), false);
            } else if (index == R.styleable.TimePicker_tp_24Hour) {
                set24Hour(obtainStyledAttributes.getBoolean(index, false));
                z = true;
            } else if (index == R.styleable.TimePicker_tp_hour) {
                setHour(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == R.styleable.TimePicker_tp_minute) {
                setMinute(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == R.styleable.TimePicker_tp_fontFamily) {
                str = obtainStyledAttributes.getString(index);
            } else if (index == R.styleable.TimePicker_tp_textStyle) {
                i3 = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
        if (this.mSelectionRadius < 0) {
            this.mSecondInnerRadius = ThemeUtil.dpToPx(context, 8);
        }
        if (this.mTickSize < 0) {
            this.mTickSize = ThemeUtil.dpToPx(context, 1);
        }
        if (this.mTextSize < 0) {
            this.mTextSize = context.getResources().getDimensionPixelOffset(R.dimen.abc_text_size_caption_material);
        }
        if (this.mAnimDuration < 0) {
            this.mAnimDuration = context.getResources().getInteger(android.R.integer.config_mediumAnimTime);
        }
        if (this.mInInterpolator == null) {
            this.mInInterpolator = new DecelerateInterpolator();
        }
        if (this.mOutInterpolator == null) {
            this.mOutInterpolator = new DecelerateInterpolator();
        }
        if (!z) {
            set24Hour(DateFormat.is24HourFormat(context));
        }
        if (str != null || i3 >= 0) {
            this.mTypeface = TypefaceUtil.load(context, str, i3);
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
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().unregisterOnThemeChangedListener(this);
        }
    }

    public int getBackgroundColor() {
        return this.mBackgroundColor;
    }

    public int getSelectionColor() {
        return this.mSelectionColor;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public int getTextHighlightColor() {
        return this.mTextHighlightColor;
    }

    public int getAnimDuration() {
        return this.mAnimDuration;
    }

    public Interpolator getInInterpolator() {
        return this.mInInterpolator;
    }

    public Interpolator getOutInterpolator() {
        return this.mOutInterpolator;
    }

    public int getMode() {
        return this.mMode;
    }

    public int getHour() {
        return this.mHour;
    }

    public int getMinute() {
        return this.mMinute;
    }

    public boolean is24Hour() {
        return this.m24Hour;
    }

    public void setMode(int i, boolean z) {
        if (this.mMode != i) {
            this.mMode = i;
            OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
            if (onTimeChangedListener != null) {
                onTimeChangedListener.onModeChanged(i);
            }
            if (z) {
                startAnimation();
            } else {
                invalidate();
            }
        }
    }

    public void setHour(int i) {
        int max;
        if (this.m24Hour) {
            max = Math.max(i, 0) % 24;
        } else {
            max = Math.max(i, 0) % 12;
        }
        int i2 = this.mHour;
        if (i2 != max) {
            this.mHour = max;
            OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
            if (onTimeChangedListener != null) {
                onTimeChangedListener.onHourChanged(i2, max);
            }
            if (this.mMode == 0) {
                invalidate();
            }
        }
    }

    public void setMinute(int i) {
        int min = Math.min(Math.max(i, 0), 59);
        int i2 = this.mMinute;
        if (i2 != min) {
            this.mMinute = min;
            OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
            if (onTimeChangedListener != null) {
                onTimeChangedListener.onMinuteChanged(i2, min);
            }
            if (this.mMode == 1) {
                invalidate();
            }
        }
    }

    public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        this.mOnTimeChangedListener = onTimeChangedListener;
    }

    public void set24Hour(boolean z) {
        int i;
        if (this.m24Hour != z) {
            this.m24Hour = z;
            if (!z && (i = this.mHour) > 11) {
                setHour(i - 12);
            }
            calculateTextLocation();
        }
    }

    private int getSelectedTick(int i, int i2) {
        if (i2 == 0) {
            return i == 0 ? this.m24Hour ? 23 : 11 : i - 1;
        }
        if (i2 != 1 || i % 5 != 0) {
            return -1;
        }
        if (i == 0) {
            return 35;
        }
        return (i / 5) + 23;
    }

    @Override // android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = mode == 0 ? this.mSelectionRadius * 12 : (View.MeasureSpec.getSize(i) - getPaddingLeft()) - getPaddingRight();
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = mode2 == 0 ? this.mSelectionRadius * 12 : (View.MeasureSpec.getSize(i2) - getPaddingTop()) - getPaddingBottom();
        int min = Math.min(size, size2);
        if (mode != 1073741824) {
            size = min;
        }
        if (mode2 != 1073741824) {
            size2 = min;
        }
        setMeasuredDimension(size + getPaddingLeft() + getPaddingRight(), size2 + getPaddingTop() + getPaddingBottom());
    }

    private void calculateTextLocation() {
        if (this.mCenterPoint == null) {
            return;
        }
        double d = -1.0471975511965976d;
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setTypeface(this.mTypeface);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        if (this.m24Hour) {
            for (int i = 0; i < 12; i++) {
                Paint paint = this.mPaint;
                String str = this.mTicks[i];
                paint.getTextBounds(str, 0, str.length(), this.mRect);
                if (i == 0) {
                    this.mSecondInnerRadius = (this.mInnerRadius - this.mSelectionRadius) - this.mRect.height();
                }
                float cos = this.mCenterPoint.x + (((float) Math.cos(d)) * this.mSecondInnerRadius);
                float sin = this.mCenterPoint.y + (((float) Math.sin(d)) * this.mSecondInnerRadius);
                float[] fArr = this.mLocations;
                int i2 = i * 2;
                fArr[i2] = cos;
                fArr[i2 + 1] = sin + (this.mRect.height() / 2.0f);
                d += 0.5235987755982988d;
            }
            for (int i3 = 12; i3 < this.mTicks.length; i3++) {
                float cos2 = this.mCenterPoint.x + (((float) Math.cos(d)) * this.mInnerRadius);
                float sin2 = this.mCenterPoint.y + (((float) Math.sin(d)) * this.mInnerRadius);
                Paint paint2 = this.mPaint;
                String str2 = this.mTicks[i3];
                paint2.getTextBounds(str2, 0, str2.length(), this.mRect);
                float[] fArr2 = this.mLocations;
                int i4 = i3 * 2;
                fArr2[i4] = cos2;
                fArr2[i4 + 1] = sin2 + (this.mRect.height() / 2.0f);
                d += 0.5235987755982988d;
            }
            return;
        }
        for (int i5 = 0; i5 < 12; i5++) {
            float cos3 = this.mCenterPoint.x + (((float) Math.cos(d)) * this.mInnerRadius);
            float sin3 = this.mCenterPoint.y + (((float) Math.sin(d)) * this.mInnerRadius);
            Paint paint3 = this.mPaint;
            String str3 = this.mTicks[i5];
            paint3.getTextBounds(str3, 0, str3.length(), this.mRect);
            float[] fArr3 = this.mLocations;
            int i6 = i5 * 2;
            fArr3[i6] = cos3;
            fArr3[i6 + 1] = sin3 + (this.mRect.height() / 2.0f);
            d += 0.5235987755982988d;
        }
        for (int i7 = 24; i7 < this.mTicks.length; i7++) {
            float cos4 = this.mCenterPoint.x + (((float) Math.cos(d)) * this.mInnerRadius);
            float sin4 = this.mCenterPoint.y + (((float) Math.sin(d)) * this.mInnerRadius);
            Paint paint4 = this.mPaint;
            String str4 = this.mTicks[i7];
            paint4.getTextBounds(str4, 0, str4.length(), this.mRect);
            float[] fArr4 = this.mLocations;
            int i8 = i7 * 2;
            fArr4[i8] = cos4;
            fArr4[i8 + 1] = sin4 + (this.mRect.height() / 2.0f);
            d += 0.5235987755982988d;
        }
    }

    @Override // android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int min = Math.min((i - getPaddingLeft()) - getPaddingRight(), (i2 - getPaddingTop()) - getPaddingBottom());
        if (this.mCenterPoint == null) {
            this.mCenterPoint = new PointF();
        }
        float f = min / 2.0f;
        this.mOuterRadius = f;
        this.mCenterPoint.set(paddingLeft + f, paddingTop + f);
        this.mInnerRadius = (this.mOuterRadius - this.mSelectionRadius) - ThemeUtil.dpToPx(getContext(), 4);
        calculateTextLocation();
    }

    private int getPointedValue(float f, float f2, boolean z) {
        float sqrt = (float) Math.sqrt(Math.pow(f - this.mCenterPoint.x, 2.0d) + Math.pow(f2 - this.mCenterPoint.y, 2.0d));
        if (z) {
            if (this.mMode == 0 && this.m24Hour) {
                float f3 = this.mInnerRadius;
                int i = this.mSelectionRadius;
                if (sqrt > f3 + i || sqrt < this.mSecondInnerRadius - i) {
                    return -1;
                }
            } else {
                float f4 = this.mInnerRadius;
                int i2 = this.mSelectionRadius;
                if (sqrt > i2 + f4 || sqrt < f4 - i2) {
                    return -1;
                }
            }
        }
        float atan2 = (float) Math.atan2(f2 - this.mCenterPoint.y, f - this.mCenterPoint.x);
        if (atan2 < 0.0f) {
            atan2 = (float) (atan2 + 6.283185307179586d);
        }
        int i3 = this.mMode;
        if (i3 != 0) {
            if (i3 != 1) {
                return -1;
            }
            int round = ((int) Math.round((atan2 * 30.0f) / 3.141592653589793d)) + 15;
            return round > 59 ? round - 60 : round;
        }
        if (!this.m24Hour) {
            int round2 = ((int) Math.round((atan2 * 6.0f) / 3.141592653589793d)) + 3;
            return round2 > 11 ? round2 - 12 : round2;
        }
        if (sqrt > this.mSecondInnerRadius + (this.mSelectionRadius / 2)) {
            int round3 = ((int) Math.round((atan2 * 6.0f) / 3.141592653589793d)) + 15;
            if (round3 == 24) {
                return 0;
            }
            return round3 > 24 ? round3 - 12 : round3;
        }
        int round4 = ((int) Math.round((atan2 * 6.0f) / 3.141592653589793d)) + 3;
        return round4 > 12 ? round4 - 12 : round4;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int action = motionEvent.getAction();
        if (action == 0) {
            int pointedValue = getPointedValue(motionEvent.getX(), motionEvent.getY(), true);
            if (pointedValue < 0) {
                return false;
            }
            int i = this.mMode;
            if (i == 0) {
                setHour(pointedValue);
            } else if (i == 1) {
                setMinute(pointedValue);
            }
            this.mEdited = true;
            return true;
        }
        if (action != 1) {
            if (action == 2) {
                int pointedValue2 = getPointedValue(motionEvent.getX(), motionEvent.getY(), false);
                if (pointedValue2 < 0) {
                    return true;
                }
                int i2 = this.mMode;
                if (i2 == 0) {
                    setHour(pointedValue2);
                } else if (i2 == 1) {
                    setMinute(pointedValue2);
                }
                this.mEdited = true;
                return true;
            }
            if (action == 3) {
                this.mEdited = false;
            }
        } else if (this.mEdited && this.mMode == 0) {
            setMode(1, true);
            this.mEdited = false;
            return true;
        }
        return false;
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        float angle;
        float interpolation;
        int selectedTick;
        float f;
        float f2;
        float f3;
        int i;
        int i2;
        int i3;
        int i4;
        float f4;
        int i5;
        float angle2;
        int selectedTick2;
        float f5;
        int i6;
        int i7;
        super.draw(canvas);
        this.mPaint.setColor(this.mBackgroundColor);
        this.mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(this.mCenterPoint.x, this.mCenterPoint.y, this.mOuterRadius, this.mPaint);
        if (!this.mRunning) {
            if (this.mMode == 0) {
                angle2 = getAngle(this.mHour, 0);
                int selectedTick3 = getSelectedTick(this.mHour, 0);
                boolean z = this.m24Hour;
                int i8 = z ? 24 : 12;
                f5 = (!z || selectedTick3 >= 12) ? this.mInnerRadius : this.mSecondInnerRadius;
                i7 = i8;
                selectedTick2 = selectedTick3;
                i6 = 0;
            } else {
                angle2 = getAngle(this.mMinute, 1);
                selectedTick2 = getSelectedTick(this.mMinute, 1);
                f5 = this.mInnerRadius;
                i6 = 24;
                i7 = 12;
            }
            this.mPaint.setColor(this.mSelectionColor);
            double d = angle2;
            float cos = this.mCenterPoint.x + (((float) Math.cos(d)) * f5);
            float sin = this.mCenterPoint.y + (((float) Math.sin(d)) * f5);
            canvas.drawCircle(cos, sin, this.mSelectionRadius, this.mPaint);
            this.mPaint.setStyle(Paint.Style.STROKE);
            this.mPaint.setStrokeWidth(this.mTickSize);
            canvas.drawLine(this.mCenterPoint.x, this.mCenterPoint.y, cos - (((float) Math.cos(d)) * this.mSelectionRadius), sin - (((float) Math.sin(d)) * this.mSelectionRadius), this.mPaint);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mTextColor);
            canvas.drawCircle(this.mCenterPoint.x, this.mCenterPoint.y, this.mTickSize * 2, this.mPaint);
            this.mPaint.setTextSize(this.mTextSize);
            this.mPaint.setTypeface(this.mTypeface);
            this.mPaint.setTextAlign(Paint.Align.CENTER);
            for (int i9 = 0; i9 < i7; i9++) {
                int i10 = i6 + i9;
                this.mPaint.setColor(i10 == selectedTick2 ? this.mTextHighlightColor : this.mTextColor);
                String str = this.mTicks[i10];
                float[] fArr = this.mLocations;
                int i11 = i10 * 2;
                canvas.drawText(str, fArr[i11], fArr[i11 + 1], this.mPaint);
            }
            return;
        }
        float f6 = (this.mOuterRadius - this.mInnerRadius) + (this.mTextSize / 2);
        int color = ColorUtil.getColor(this.mTextColor, 1.0f - this.mAnimProgress);
        int color2 = ColorUtil.getColor(this.mTextHighlightColor, 1.0f - this.mAnimProgress);
        int color3 = ColorUtil.getColor(this.mTextColor, this.mAnimProgress);
        int color4 = ColorUtil.getColor(this.mTextHighlightColor, this.mAnimProgress);
        if (this.mMode == 1) {
            angle = getAngle(this.mHour, 0);
            float angle3 = getAngle(this.mMinute, 1);
            float interpolation2 = this.mOutInterpolator.getInterpolation(this.mAnimProgress) * f6;
            interpolation = (1.0f - this.mInInterpolator.getInterpolation(this.mAnimProgress)) * (-f6);
            int selectedTick4 = getSelectedTick(this.mHour, 0);
            selectedTick = getSelectedTick(this.mMinute, 1);
            boolean z2 = this.m24Hour;
            int i12 = z2 ? 24 : 12;
            float f7 = (!z2 || selectedTick4 >= 12) ? this.mInnerRadius : this.mSecondInnerRadius;
            f = this.mInnerRadius;
            f2 = angle3;
            f3 = interpolation2;
            i = 12;
            i2 = 0;
            i4 = selectedTick4;
            f4 = f7;
            i5 = i12;
            i3 = 24;
        } else {
            angle = getAngle(this.mMinute, 1);
            float angle4 = getAngle(this.mHour, 0);
            float interpolation3 = this.mOutInterpolator.getInterpolation(this.mAnimProgress) * (-f6);
            interpolation = (1.0f - this.mInInterpolator.getInterpolation(this.mAnimProgress)) * f6;
            int selectedTick5 = getSelectedTick(this.mMinute, 1);
            selectedTick = getSelectedTick(this.mHour, 0);
            float f8 = this.mInnerRadius;
            boolean z3 = this.m24Hour;
            int i13 = z3 ? 24 : 12;
            f = (!z3 || selectedTick >= 12) ? f8 : this.mSecondInnerRadius;
            f2 = angle4;
            f3 = interpolation3;
            i = i13;
            i2 = 24;
            i3 = 0;
            i4 = selectedTick5;
            f4 = f8;
            i5 = 12;
        }
        int i14 = selectedTick;
        float f9 = interpolation;
        this.mPaint.setColor(ColorUtil.getColor(this.mSelectionColor, 1.0f - this.mAnimProgress));
        double d2 = angle;
        float f10 = f2;
        float f11 = f4 + f3;
        float cos2 = this.mCenterPoint.x + (((float) Math.cos(d2)) * f11);
        int i15 = i;
        float sin2 = this.mCenterPoint.y + (((float) Math.sin(d2)) * f11);
        canvas.drawCircle(cos2, sin2, this.mSelectionRadius, this.mPaint);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mTickSize);
        int i16 = i5;
        canvas.drawLine(this.mCenterPoint.x, this.mCenterPoint.y, cos2 - (((float) Math.cos(d2)) * this.mSelectionRadius), sin2 - (((float) Math.sin(d2)) * this.mSelectionRadius), this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(ColorUtil.getColor(this.mSelectionColor, this.mAnimProgress));
        double d3 = f10;
        float f12 = f + f9;
        float cos3 = this.mCenterPoint.x + (((float) Math.cos(d3)) * f12);
        float sin3 = this.mCenterPoint.y + (((float) Math.sin(d3)) * f12);
        canvas.drawCircle(cos3, sin3, this.mSelectionRadius, this.mPaint);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeWidth(this.mTickSize);
        canvas.drawLine(this.mCenterPoint.x, this.mCenterPoint.y, cos3 - (((float) Math.cos(d3)) * this.mSelectionRadius), sin3 - (((float) Math.sin(d3)) * this.mSelectionRadius), this.mPaint);
        this.mPaint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(this.mTextColor);
        canvas.drawCircle(this.mCenterPoint.x, this.mCenterPoint.y, this.mTickSize * 2, this.mPaint);
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setTypeface(this.mTypeface);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        double d4 = -1.0471975511965976d;
        int i17 = 0;
        while (i17 < i16) {
            int i18 = i17 + i2;
            int i19 = i18 * 2;
            float cos4 = this.mLocations[i19] + (((float) Math.cos(d4)) * f3);
            float f13 = this.mLocations[i19 + 1];
            int i20 = i16;
            int i21 = color4;
            float sin4 = f13 + (((float) Math.sin(d4)) * f3);
            this.mPaint.setColor(i18 == i4 ? color2 : color);
            canvas.drawText(this.mTicks[i18], cos4, sin4, this.mPaint);
            d4 += 0.5235987755982988d;
            i17++;
            color4 = i21;
            i16 = i20;
        }
        int i22 = color4;
        for (int i23 = 0; i23 < i15; i23++) {
            int i24 = i23 + i3;
            int i25 = i24 * 2;
            float cos5 = this.mLocations[i25] + (((float) Math.cos(d4)) * f9);
            float sin5 = this.mLocations[i25 + 1] + (((float) Math.sin(d4)) * f9);
            this.mPaint.setColor(i24 == i14 ? i22 : color3);
            canvas.drawText(this.mTicks[i24], cos5, sin5, this.mPaint);
            d4 += 0.5235987755982988d;
        }
    }

    private void resetAnimation() {
        this.mStartTime = SystemClock.uptimeMillis();
        this.mAnimProgress = 0.0f;
    }

    private void startAnimation() {
        if (getHandler() != null) {
            resetAnimation();
            this.mRunning = true;
            getHandler().postAtTime(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
        invalidate();
    }

    private void stopAnimation() {
        this.mRunning = false;
        this.mAnimProgress = 1.0f;
        if (getHandler() != null) {
            getHandler().removeCallbacks(this.mUpdater);
        }
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mAnimDuration);
        this.mAnimProgress = min;
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
        savedState.mode = this.mMode;
        savedState.hour = this.mHour;
        savedState.minute = this.mMinute;
        savedState.is24Hour = this.m24Hour;
        return savedState;
    }

    @Override // android.view.View
    protected void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        set24Hour(savedState.is24Hour);
        setMode(savedState.mode, false);
        setHour(savedState.hour);
        setMinute(savedState.minute);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.rey.material.widget.TimePicker.SavedState.1
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
        int hour;
        boolean is24Hour;
        int minute;
        int mode;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.mode = parcel.readInt();
            this.hour = parcel.readInt();
            this.minute = parcel.readInt();
            this.is24Hour = parcel.readInt() == 1;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Integer.valueOf(this.mode));
            parcel.writeValue(Integer.valueOf(this.hour));
            parcel.writeValue(Integer.valueOf(this.minute));
            parcel.writeValue(Integer.valueOf(this.is24Hour ? 1 : 0));
        }

        public String toString() {
            return "TimePicker.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " mode=" + this.mode + " hour=" + this.hour + " minute=" + this.minute + "24hour=" + this.is24Hour + "}";
        }
    }
}
