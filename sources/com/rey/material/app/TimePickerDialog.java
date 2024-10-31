package com.rey.material.app;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import com.rey.material.R;
import com.rey.material.app.Dialog;
import com.rey.material.util.ThemeUtil;
import com.rey.material.widget.CircleCheckedTextView;
import com.rey.material.widget.TimePicker;
import java.text.DateFormat;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class TimePickerDialog extends Dialog {
    private float mCornerRadius;
    private TimePickerLayout mTimePickerLayout;

    /* loaded from: classes2.dex */
    public interface OnTimeChangedListener {
        void onTimeChanged(int i, int i2, int i3, int i4);
    }

    public TimePickerDialog(Context context) {
        super(context, R.style.Material_App_Dialog_TimePicker_Light);
    }

    public TimePickerDialog(Context context, int i) {
        super(context, i);
    }

    @Override // com.rey.material.app.Dialog
    protected void onCreate() {
        TimePickerLayout timePickerLayout = new TimePickerLayout(getContext());
        this.mTimePickerLayout = timePickerLayout;
        contentView(timePickerLayout);
    }

    @Override // com.rey.material.app.Dialog
    public Dialog applyStyle(int i) {
        super.applyStyle(i);
        if (i == 0) {
            return this;
        }
        this.mTimePickerLayout.applyStyle(i);
        layoutParams(-1, -1);
        return this;
    }

    @Override // com.rey.material.app.Dialog
    public Dialog layoutParams(int i, int i2) {
        return super.layoutParams(-1, -1);
    }

    @Override // com.rey.material.app.Dialog
    public Dialog cornerRadius(float f) {
        this.mCornerRadius = f;
        return super.cornerRadius(f);
    }

    public TimePickerDialog hour(int i) {
        this.mTimePickerLayout.setHour(i);
        return this;
    }

    public TimePickerDialog minute(int i) {
        this.mTimePickerLayout.setMinute(i);
        return this;
    }

    public TimePickerDialog onTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
        this.mTimePickerLayout.setOnTimeChangedListener(onTimeChangedListener);
        return this;
    }

    public int getHour() {
        return this.mTimePickerLayout.getHour();
    }

    public int getMinute() {
        return this.mTimePickerLayout.getMinute();
    }

    public String getFormattedTime(DateFormat dateFormat) {
        return this.mTimePickerLayout.getFormattedTime(dateFormat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TimePickerLayout extends FrameLayout implements View.OnClickListener, TimePicker.OnTimeChangedListener {
        private static final String BASE_TEXT = "0";
        private static final String FORMAT = "%02d";
        private static final String FORMAT_NO_LEADING_ZERO = "%d";
        private static final String TIME_DIVIDER = ":";
        private CircleCheckedTextView mAmView;
        private float mBaseHeight;
        private float mBaseY;
        private int mCheckBoxSize;
        private float mDividerX;
        private Path mHeaderBackground;
        private int mHeaderHeight;
        private int mHeaderRealHeight;
        private int mHeaderRealWidth;
        private String mHour;
        private float mHourWidth;
        private float mHourX;
        private boolean mIsAm;
        private boolean mIsLeadingZero;
        private boolean mLocationDirty;
        private String mMidday;
        private float mMiddayX;
        private String mMinute;
        private float mMinuteWidth;
        private float mMinuteX;
        private OnTimeChangedListener mOnTimeChangedListener;
        private Paint mPaint;
        private CircleCheckedTextView mPmView;
        private RectF mRect;
        private int mTextTimeColor;
        private int mTextTimeSize;
        private TimePicker mTimePicker;

        private boolean isTouched(float f, float f2, float f3, float f4, float f5, float f6) {
            return f5 >= f && f5 <= f3 && f6 >= f2 && f6 <= f4;
        }

        public TimePickerLayout(Context context) {
            super(context);
            this.mTextTimeColor = -16777216;
            this.mIsLeadingZero = false;
            this.mIsAm = true;
            this.mLocationDirty = true;
            Paint paint = new Paint(1);
            this.mPaint = paint;
            paint.setTextAlign(Paint.Align.LEFT);
            this.mHeaderBackground = new Path();
            this.mRect = new RectF();
            this.mAmView = new CircleCheckedTextView(context);
            this.mPmView = new CircleCheckedTextView(context);
            TimePicker timePicker = new TimePicker(context);
            this.mTimePicker = timePicker;
            timePicker.setPadding(TimePickerDialog.this.mContentPadding, TimePickerDialog.this.mContentPadding, TimePickerDialog.this.mContentPadding, TimePickerDialog.this.mContentPadding);
            this.mTimePicker.setOnTimeChangedListener(this);
            this.mAmView.setGravity(17);
            this.mPmView.setGravity(17);
            if (Build.VERSION.SDK_INT >= 17) {
                this.mAmView.setTextAlignment(4);
                this.mPmView.setTextAlignment(4);
            }
            this.mAmView.setCheckedImmediately(this.mIsAm);
            this.mPmView.setCheckedImmediately(true ^ this.mIsAm);
            this.mAmView.setOnClickListener(this);
            this.mPmView.setOnClickListener(this);
            addView(this.mTimePicker);
            addView(this.mAmView);
            addView(this.mPmView);
            setWillNotDraw(false);
            this.mCheckBoxSize = ThemeUtil.dpToPx(context, 48);
            this.mHeaderHeight = ThemeUtil.dpToPx(context, 120);
            this.mTextTimeSize = context.getResources().getDimensionPixelOffset(R.dimen.abc_text_size_headline_material);
        }

        public void applyStyle(int i) {
            this.mTimePicker.applyStyle(i);
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(i, R.styleable.TimePickerDialog);
            int indexCount = obtainStyledAttributes.getIndexCount();
            String str = null;
            String str2 = null;
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.TimePickerDialog_tp_headerHeight) {
                    this.mHeaderHeight = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.TimePickerDialog_tp_textTimeColor) {
                    this.mTextTimeColor = obtainStyledAttributes.getColor(index, 0);
                } else if (index == R.styleable.TimePickerDialog_tp_textTimeSize) {
                    this.mTextTimeSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.TimePickerDialog_tp_leadingZero) {
                    this.mIsLeadingZero = obtainStyledAttributes.getBoolean(index, false);
                } else if (index == R.styleable.TimePickerDialog_tp_am) {
                    str = obtainStyledAttributes.getString(index);
                } else if (index == R.styleable.TimePickerDialog_tp_pm) {
                    str2 = obtainStyledAttributes.getString(index);
                }
            }
            obtainStyledAttributes.recycle();
            if (str == null) {
                str = DateUtils.getAMPMString(0).toUpperCase();
            }
            if (str2 == null) {
                str2 = DateUtils.getAMPMString(1).toUpperCase();
            }
            int[][] iArr = {new int[]{-16842912}, new int[]{android.R.attr.state_checked}};
            int[] iArr2 = {this.mTimePicker.getTextColor(), this.mTimePicker.getTextHighlightColor()};
            this.mAmView.setBackgroundColor(this.mTimePicker.getSelectionColor());
            this.mAmView.setAnimDuration(this.mTimePicker.getAnimDuration());
            this.mAmView.setInterpolator(this.mTimePicker.getInInterpolator(), this.mTimePicker.getOutInterpolator());
            this.mAmView.setTypeface(this.mTimePicker.getTypeface());
            this.mAmView.setTextSize(0, this.mTimePicker.getTextSize());
            this.mAmView.setTextColor(new ColorStateList(iArr, iArr2));
            this.mAmView.setText(str);
            this.mPmView.setBackgroundColor(this.mTimePicker.getSelectionColor());
            this.mPmView.setAnimDuration(this.mTimePicker.getAnimDuration());
            this.mPmView.setInterpolator(this.mTimePicker.getInInterpolator(), this.mTimePicker.getOutInterpolator());
            this.mPmView.setTypeface(this.mTimePicker.getTypeface());
            this.mPmView.setTextSize(0, this.mTimePicker.getTextSize());
            this.mPmView.setTextColor(new ColorStateList(iArr, iArr2));
            this.mPmView.setText(str2);
            this.mPaint.setTypeface(this.mTimePicker.getTypeface());
            String str3 = this.mIsLeadingZero ? "%02d" : "%d";
            Object[] objArr = new Object[1];
            objArr[0] = Integer.valueOf((this.mTimePicker.is24Hour() || this.mTimePicker.getHour() != 0) ? this.mTimePicker.getHour() : 12);
            this.mHour = String.format(str3, objArr);
            this.mMinute = String.format("%02d", Integer.valueOf(this.mTimePicker.getMinute()));
            if (!this.mTimePicker.is24Hour()) {
                this.mMidday = (this.mIsAm ? this.mAmView : this.mPmView).getText().toString();
            }
            this.mLocationDirty = true;
            invalidate(0, 0, this.mHeaderRealWidth, this.mHeaderRealHeight);
        }

        public void setHour(int i) {
            if (!this.mTimePicker.is24Hour()) {
                if (i > 11 && i < 24) {
                    setAm(false, false);
                } else {
                    setAm(true, false);
                }
            }
            this.mTimePicker.setHour(i);
        }

        public int getHour() {
            return (this.mTimePicker.is24Hour() || this.mIsAm) ? this.mTimePicker.getHour() : this.mTimePicker.getHour() + 12;
        }

        public void setMinute(int i) {
            this.mTimePicker.setMinute(i);
        }

        public int getMinute() {
            return this.mTimePicker.getMinute();
        }

        private void setAm(boolean z, boolean z2) {
            if (this.mTimePicker.is24Hour() || this.mIsAm == z) {
                return;
            }
            int hour = getHour();
            this.mIsAm = z;
            if (z2) {
                this.mAmView.setChecked(z);
                this.mPmView.setChecked(!this.mIsAm);
            } else {
                this.mAmView.setCheckedImmediately(z);
                this.mPmView.setCheckedImmediately(!this.mIsAm);
            }
            this.mMidday = (this.mIsAm ? this.mAmView : this.mPmView).getText().toString();
            invalidate(0, 0, this.mHeaderRealWidth, this.mHeaderRealHeight);
            OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
            if (onTimeChangedListener != null) {
                onTimeChangedListener.onTimeChanged(hour, getMinute(), getHour(), getMinute());
            }
        }

        public String getFormattedTime(DateFormat dateFormat) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(11, getHour());
            calendar.set(12, getMinute());
            calendar.set(13, 0);
            calendar.set(14, 0);
            return dateFormat.format(calendar.getTime());
        }

        public void setOnTimeChangedListener(OnTimeChangedListener onTimeChangedListener) {
            this.mOnTimeChangedListener = onTimeChangedListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            setAm(view == this.mAmView, true);
        }

        @Override // com.rey.material.widget.TimePicker.OnTimeChangedListener
        public void onModeChanged(int i) {
            invalidate(0, 0, this.mHeaderRealWidth, this.mHeaderRealHeight);
        }

        @Override // com.rey.material.widget.TimePicker.OnTimeChangedListener
        public void onHourChanged(int i, int i2) {
            if (!this.mTimePicker.is24Hour() && !this.mIsAm) {
                i += 12;
            }
            String str = this.mIsLeadingZero ? "%02d" : "%d";
            Object[] objArr = new Object[1];
            if (!this.mTimePicker.is24Hour() && i2 == 0) {
                i2 = 12;
            }
            objArr[0] = Integer.valueOf(i2);
            this.mHour = String.format(str, objArr);
            this.mLocationDirty = true;
            invalidate(0, 0, this.mHeaderRealWidth, this.mHeaderRealHeight);
            OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
            if (onTimeChangedListener != null) {
                onTimeChangedListener.onTimeChanged(i, getMinute(), getHour(), getMinute());
            }
        }

        @Override // com.rey.material.widget.TimePicker.OnTimeChangedListener
        public void onMinuteChanged(int i, int i2) {
            this.mMinute = String.format("%02d", Integer.valueOf(i2));
            this.mLocationDirty = true;
            invalidate(0, 0, this.mHeaderRealWidth, this.mHeaderRealHeight);
            OnTimeChangedListener onTimeChangedListener = this.mOnTimeChangedListener;
            if (onTimeChangedListener != null) {
                onTimeChangedListener.onTimeChanged(getHour(), i, getHour(), i2);
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            View.MeasureSpec.getMode(i);
            int size2 = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            boolean z = getContext().getResources().getConfiguration().orientation == 1;
            int i3 = this.mTimePicker.is24Hour() ? 0 : this.mCheckBoxSize;
            if (z) {
                if (mode == Integer.MIN_VALUE) {
                    size2 = Math.min(size2, i3 + size + this.mHeaderHeight);
                }
                if (i3 > 0) {
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(this.mCheckBoxSize, 1073741824);
                    this.mAmView.setVisibility(0);
                    this.mPmView.setVisibility(0);
                    this.mAmView.measure(makeMeasureSpec, makeMeasureSpec);
                    this.mPmView.measure(makeMeasureSpec, makeMeasureSpec);
                } else {
                    this.mAmView.setVisibility(8);
                    this.mPmView.setVisibility(8);
                }
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                this.mTimePicker.measure(makeMeasureSpec2, makeMeasureSpec2);
                setMeasuredDimension(size, size2);
                return;
            }
            int i4 = size / 2;
            if (mode == Integer.MIN_VALUE) {
                int i5 = this.mHeaderHeight;
                if (i3 > 0) {
                    i5 = i5 + i3 + TimePickerDialog.this.mContentPadding;
                }
                size2 = Math.min(size2, Math.max(i5, i4));
            }
            if (i3 > 0) {
                int makeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
                this.mAmView.setVisibility(0);
                this.mPmView.setVisibility(0);
                this.mAmView.measure(makeMeasureSpec3, makeMeasureSpec3);
                this.mPmView.measure(makeMeasureSpec3, makeMeasureSpec3);
            } else {
                this.mAmView.setVisibility(8);
                this.mPmView.setVisibility(8);
            }
            int makeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(Math.min(i4, size2), 1073741824);
            this.mTimePicker.measure(makeMeasureSpec4, makeMeasureSpec4);
            setMeasuredDimension(size, size2);
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            boolean z = getContext().getResources().getConfiguration().orientation == 1;
            this.mLocationDirty = true;
            int i5 = this.mTimePicker.is24Hour() ? 0 : this.mCheckBoxSize;
            if (z) {
                this.mHeaderRealWidth = i;
                this.mHeaderRealHeight = (i2 - i5) - i;
                this.mHeaderBackground.reset();
                if (TimePickerDialog.this.mCornerRadius == 0.0f) {
                    this.mHeaderBackground.addRect(0.0f, 0.0f, this.mHeaderRealWidth, this.mHeaderRealHeight, Path.Direction.CW);
                    return;
                }
                this.mHeaderBackground.moveTo(0.0f, this.mHeaderRealHeight);
                this.mHeaderBackground.lineTo(0.0f, TimePickerDialog.this.mCornerRadius);
                this.mRect.set(0.0f, 0.0f, TimePickerDialog.this.mCornerRadius * 2.0f, TimePickerDialog.this.mCornerRadius * 2.0f);
                this.mHeaderBackground.arcTo(this.mRect, 180.0f, 90.0f, false);
                this.mHeaderBackground.lineTo(this.mHeaderRealWidth - TimePickerDialog.this.mCornerRadius, 0.0f);
                this.mRect.set(this.mHeaderRealWidth - (TimePickerDialog.this.mCornerRadius * 2.0f), 0.0f, this.mHeaderRealWidth, TimePickerDialog.this.mCornerRadius * 2.0f);
                this.mHeaderBackground.arcTo(this.mRect, 270.0f, 90.0f, false);
                this.mHeaderBackground.lineTo(this.mHeaderRealWidth, this.mHeaderRealHeight);
                this.mHeaderBackground.close();
                return;
            }
            this.mHeaderRealWidth = i / 2;
            if (i5 > 0) {
                i2 = (i2 - i5) - TimePickerDialog.this.mContentPadding;
            }
            this.mHeaderRealHeight = i2;
            this.mHeaderBackground.reset();
            if (TimePickerDialog.this.mCornerRadius == 0.0f) {
                this.mHeaderBackground.addRect(0.0f, 0.0f, this.mHeaderRealWidth, this.mHeaderRealHeight, Path.Direction.CW);
                return;
            }
            this.mHeaderBackground.moveTo(0.0f, this.mHeaderRealHeight);
            this.mHeaderBackground.lineTo(0.0f, TimePickerDialog.this.mCornerRadius);
            this.mRect.set(0.0f, 0.0f, TimePickerDialog.this.mCornerRadius * 2.0f, TimePickerDialog.this.mCornerRadius * 2.0f);
            this.mHeaderBackground.arcTo(this.mRect, 180.0f, 90.0f, false);
            this.mHeaderBackground.lineTo(this.mHeaderRealWidth, 0.0f);
            this.mHeaderBackground.lineTo(this.mHeaderRealWidth, this.mHeaderRealHeight);
            this.mHeaderBackground.close();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            boolean z2 = getContext().getResources().getConfiguration().orientation == 1;
            int i7 = this.mTimePicker.is24Hour() ? 0 : this.mCheckBoxSize;
            if (z2) {
                int i8 = TimePickerDialog.this.mContentPadding + TimePickerDialog.this.mActionPadding;
                int i9 = TimePickerDialog.this.mContentPadding - TimePickerDialog.this.mActionPadding;
                if (i7 > 0) {
                    int i10 = i8 + 0;
                    int i11 = i6 - i9;
                    int i12 = i11 - i7;
                    this.mAmView.layout(i10, i12, i10 + i7, i11);
                    int i13 = i5 - i8;
                    this.mPmView.layout(i13 - i7, i12, i13, i11);
                }
                this.mTimePicker.layout(0, this.mHeaderRealHeight + 0, i5, i6 - i7);
                return;
            }
            int i14 = i5 / 2;
            int measuredWidth = (i14 - this.mTimePicker.getMeasuredWidth()) / 2;
            int measuredHeight = (i6 - this.mTimePicker.getMeasuredHeight()) / 2;
            TimePicker timePicker = this.mTimePicker;
            int i15 = i5 - measuredWidth;
            int i16 = measuredHeight + 0;
            timePicker.layout(i15 - timePicker.getMeasuredWidth(), i16, i15, this.mTimePicker.getMeasuredHeight() + i16);
            if (i7 > 0) {
                int i17 = TimePickerDialog.this.mContentPadding + TimePickerDialog.this.mActionPadding;
                int i18 = i17 + 0;
                int i19 = i6 - (TimePickerDialog.this.mContentPadding - TimePickerDialog.this.mActionPadding);
                int i20 = i19 - i7;
                this.mAmView.layout(i18, i20, i18 + i7, i19);
                int i21 = i14 - i17;
                this.mPmView.layout(i21 - i7, i20, i21, i19);
            }
        }

        private void measureTimeText() {
            if (this.mLocationDirty) {
                this.mPaint.setTextSize(this.mTextTimeSize);
                Rect rect = new Rect();
                this.mPaint.getTextBounds(BASE_TEXT, 0, 1, rect);
                float height = rect.height();
                this.mBaseHeight = height;
                this.mBaseY = (this.mHeaderRealHeight + height) / 2.0f;
                float measureText = this.mPaint.measureText(TIME_DIVIDER, 0, 1);
                Paint paint = this.mPaint;
                String str = this.mHour;
                this.mHourWidth = paint.measureText(str, 0, str.length());
                Paint paint2 = this.mPaint;
                String str2 = this.mMinute;
                float measureText2 = paint2.measureText(str2, 0, str2.length());
                this.mMinuteWidth = measureText2;
                float f = (this.mHeaderRealWidth - measureText) / 2.0f;
                this.mDividerX = f;
                this.mHourX = f - this.mHourWidth;
                float f2 = f + measureText;
                this.mMinuteX = f2;
                this.mMiddayX = f2 + measureText2;
                this.mLocationDirty = false;
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mPaint.setColor(this.mTimePicker.getSelectionColor());
            canvas.drawPath(this.mHeaderBackground, this.mPaint);
            measureTimeText();
            this.mPaint.setTextSize(this.mTextTimeSize);
            this.mPaint.setColor(this.mTimePicker.getMode() == 0 ? this.mTimePicker.getTextHighlightColor() : this.mTextTimeColor);
            canvas.drawText(this.mHour, this.mHourX, this.mBaseY, this.mPaint);
            this.mPaint.setColor(this.mTextTimeColor);
            canvas.drawText(TIME_DIVIDER, this.mDividerX, this.mBaseY, this.mPaint);
            this.mPaint.setColor(this.mTimePicker.getMode() == 1 ? this.mTimePicker.getTextHighlightColor() : this.mTextTimeColor);
            canvas.drawText(this.mMinute, this.mMinuteX, this.mBaseY, this.mPaint);
            if (this.mTimePicker.is24Hour()) {
                return;
            }
            this.mPaint.setTextSize(this.mTimePicker.getTextSize());
            this.mPaint.setColor(this.mTextTimeColor);
            canvas.drawText(this.mMidday, this.mMiddayX, this.mBaseY, this.mPaint);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (onTouchEvent) {
                return onTouchEvent;
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                float f = this.mHourX;
                float f2 = this.mBaseY;
                if (isTouched(f, f2 - this.mBaseHeight, f + this.mHourWidth, f2, motionEvent.getX(), motionEvent.getY())) {
                    return this.mTimePicker.getMode() == 1;
                }
                float f3 = this.mMinuteX;
                float f4 = this.mBaseY;
                return isTouched(f3, f4 - this.mBaseHeight, f3 + this.mMinuteWidth, f4, motionEvent.getX(), motionEvent.getY()) && this.mTimePicker.getMode() == 0;
            }
            if (action != 1) {
                return false;
            }
            float f5 = this.mHourX;
            float f6 = this.mBaseY;
            if (isTouched(f5, f6 - this.mBaseHeight, f5 + this.mHourWidth, f6, motionEvent.getX(), motionEvent.getY())) {
                this.mTimePicker.setMode(0, true);
            }
            float f7 = this.mMinuteX;
            float f8 = this.mBaseY;
            if (!isTouched(f7, f8 - this.mBaseHeight, f7 + this.mMinuteWidth, f8, motionEvent.getX(), motionEvent.getY())) {
                return false;
            }
            this.mTimePicker.setMode(1, true);
            return false;
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder extends Dialog.Builder implements OnTimeChangedListener {
        public static final Parcelable.Creator<Builder> CREATOR = new Parcelable.Creator<Builder>() { // from class: com.rey.material.app.TimePickerDialog.Builder.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Builder createFromParcel(Parcel parcel) {
                return new Builder(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Builder[] newArray(int i) {
                return new Builder[i];
            }
        };
        protected int mHour;
        protected int mMinute;

        @Override // com.rey.material.app.Dialog.Builder
        public Dialog.Builder contentView(int i) {
            return this;
        }

        public Builder() {
            super(R.style.Material_App_Dialog_TimePicker_Light);
            Calendar calendar = Calendar.getInstance();
            this.mHour = calendar.get(11);
            this.mMinute = calendar.get(12);
        }

        public Builder(int i, int i2) {
            this(R.style.Material_App_Dialog_TimePicker_Light, i, i2);
        }

        public Builder(int i, int i2, int i3) {
            super(i);
            hour(i2);
            minute(i3);
        }

        public Builder hour(int i) {
            this.mHour = Math.min(Math.max(i, 0), 24);
            return this;
        }

        public Builder minute(int i) {
            this.mMinute = i;
            return this;
        }

        public int getHour() {
            return this.mHour;
        }

        public int getMinute() {
            return this.mMinute;
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected Dialog onBuild(Context context, int i) {
            TimePickerDialog timePickerDialog = new TimePickerDialog(context, i);
            timePickerDialog.hour(this.mHour).minute(this.mMinute).onTimeChangedListener(this);
            return timePickerDialog;
        }

        @Override // com.rey.material.app.TimePickerDialog.OnTimeChangedListener
        public void onTimeChanged(int i, int i2, int i3, int i4) {
            hour(i3).minute(i4);
        }

        protected Builder(Parcel parcel) {
            super(parcel);
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected void onWriteToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mHour);
            parcel.writeInt(this.mMinute);
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected void onReadFromParcel(Parcel parcel) {
            this.mHour = parcel.readInt();
            this.mMinute = parcel.readInt();
        }
    }
}
