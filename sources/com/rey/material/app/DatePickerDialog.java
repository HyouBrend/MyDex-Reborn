package com.rey.material.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import com.rey.material.R;
import com.rey.material.app.Dialog;
import com.rey.material.util.ThemeUtil;
import com.rey.material.widget.DatePicker;
import com.rey.material.widget.YearPicker;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DatePickerDialog extends Dialog {
    private float mCornerRadius;
    private DatePickerLayout mDatePickerLayout;
    private OnDateChangedListener mOnDateChangedListener;

    /* loaded from: classes2.dex */
    public interface OnDateChangedListener {
        void onDateChanged(int i, int i2, int i3, int i4, int i5, int i6);
    }

    public DatePickerDialog(Context context) {
        super(context, R.style.Material_App_Dialog_DatePicker_Light);
    }

    public DatePickerDialog(Context context, int i) {
        super(context, i);
    }

    @Override // com.rey.material.app.Dialog
    protected void onCreate() {
        DatePickerLayout datePickerLayout = new DatePickerLayout(getContext());
        this.mDatePickerLayout = datePickerLayout;
        contentView(datePickerLayout);
    }

    @Override // com.rey.material.app.Dialog
    public Dialog applyStyle(int i) {
        super.applyStyle(i);
        if (i == 0) {
            return this;
        }
        this.mDatePickerLayout.applyStyle(i);
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

    public DatePickerDialog dateRange(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mDatePickerLayout.setDateRange(i, i2, i3, i4, i5, i6);
        return this;
    }

    public DatePickerDialog dateRange(long j, long j2) {
        this.mDatePickerLayout.setDateRange(j, j2);
        return this;
    }

    public DatePickerDialog date(int i, int i2, int i3) {
        this.mDatePickerLayout.setDate(i, i2, i3);
        return this;
    }

    public DatePickerDialog date(long j) {
        this.mDatePickerLayout.setDate(j);
        return this;
    }

    public DatePickerDialog onDateChangedListener(OnDateChangedListener onDateChangedListener) {
        this.mOnDateChangedListener = onDateChangedListener;
        return this;
    }

    public int getDay() {
        return this.mDatePickerLayout.getDay();
    }

    public int getMonth() {
        return this.mDatePickerLayout.getMonth();
    }

    public int getYear() {
        return this.mDatePickerLayout.getYear();
    }

    public long getDate() {
        Calendar calendar = getCalendar();
        calendar.set(14, 0);
        calendar.set(13, 0);
        calendar.set(12, 0);
        calendar.set(10, 0);
        calendar.set(5, getDay());
        calendar.set(2, getMonth());
        calendar.set(1, getYear());
        return calendar.getTimeInMillis();
    }

    public Calendar getCalendar() {
        return this.mDatePickerLayout.getCalendar();
    }

    public String getFormattedDate(DateFormat dateFormat) {
        return this.mDatePickerLayout.getFormattedDate(dateFormat);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DatePickerLayout extends FrameLayout implements DatePicker.OnDateChangedListener, YearPicker.OnYearChangedListener {
        private static final String BASE_TEXT = "0";
        private static final String DAY_FORMAT = "%2d";
        private static final String YEAR_FORMAT = "%4d";
        private float mBaseX;
        private float mCenterY;
        private DatePicker mDatePicker;
        private String mDay;
        private boolean mDaySelectMode;
        private float mDayY;
        private float mFirstWidth;
        private int mHeaderPrimaryColor;
        private int mHeaderPrimaryHeight;
        private int mHeaderPrimaryRealHeight;
        private int mHeaderPrimaryTextSize;
        private int mHeaderRealWidth;
        private Path mHeaderSecondaryBackground;
        private int mHeaderSecondaryColor;
        private int mHeaderSecondaryHeight;
        private int mHeaderSecondaryTextSize;
        private boolean mLocationDirty;
        private String mMonth;
        private boolean mMonthFirst;
        private float mMonthY;
        private int mPadding;
        private Paint mPaint;
        private RectF mRect;
        private float mSecondWidth;
        private int mTextHeaderColor;
        private String mWeekDay;
        private float mWeekDayY;
        private String mYear;
        private YearPicker mYearPicker;
        private float mYearY;

        private boolean isTouched(float f, float f2, float f3, float f4, float f5, float f6) {
            return f5 >= f && f5 <= f3 && f6 >= f2 && f6 <= f4;
        }

        public DatePickerLayout(Context context) {
            super(context);
            this.mTextHeaderColor = -16777216;
            this.mDaySelectMode = true;
            this.mMonthFirst = true;
            this.mLocationDirty = true;
            Paint paint = new Paint(1);
            this.mPaint = paint;
            paint.setStyle(Paint.Style.FILL);
            this.mPaint.setTextAlign(Paint.Align.CENTER);
            this.mRect = new RectF();
            this.mHeaderSecondaryBackground = new Path();
            this.mPadding = ThemeUtil.dpToPx(context, 8);
            this.mYearPicker = new YearPicker(context);
            this.mDatePicker = new DatePicker(context);
            YearPicker yearPicker = this.mYearPicker;
            int i = this.mPadding;
            yearPicker.setPadding(i, i, i, i);
            this.mYearPicker.setOnYearChangedListener(this);
            DatePicker datePicker = this.mDatePicker;
            int i2 = this.mPadding;
            datePicker.setContentPadding(i2, i2, i2, i2);
            this.mDatePicker.setOnDateChangedListener(this);
            addView(this.mDatePicker);
            addView(this.mYearPicker);
            this.mYearPicker.setVisibility(this.mDaySelectMode ? 8 : 0);
            this.mDatePicker.setVisibility(this.mDaySelectMode ? 0 : 8);
            this.mMonthFirst = isMonthFirst();
            setWillNotDraw(false);
            this.mHeaderPrimaryHeight = ThemeUtil.dpToPx(context, 144);
            this.mHeaderSecondaryHeight = ThemeUtil.dpToPx(context, 32);
            this.mHeaderPrimaryTextSize = context.getResources().getDimensionPixelOffset(R.dimen.abc_text_size_display_2_material);
            this.mHeaderSecondaryTextSize = context.getResources().getDimensionPixelOffset(R.dimen.abc_text_size_headline_material);
        }

        private boolean isMonthFirst() {
            String localizedPattern = ((SimpleDateFormat) SimpleDateFormat.getDateInstance(0)).toLocalizedPattern();
            return localizedPattern.indexOf("M") < localizedPattern.indexOf("d");
        }

        public void setDateSelectMode(boolean z) {
            if (this.mDaySelectMode != z) {
                this.mDaySelectMode = z;
                if (z) {
                    DatePicker datePicker = this.mDatePicker;
                    datePicker.goTo(datePicker.getMonth(), this.mDatePicker.getYear());
                    animOut(this.mYearPicker);
                    animIn(this.mDatePicker);
                } else {
                    YearPicker yearPicker = this.mYearPicker;
                    yearPicker.goTo(yearPicker.getYear());
                    animOut(this.mDatePicker);
                    animIn(this.mYearPicker);
                }
                invalidate(0, 0, this.mHeaderRealWidth, this.mHeaderPrimaryRealHeight + this.mHeaderSecondaryHeight);
            }
        }

        private void animOut(final View view) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.0f);
            alphaAnimation.setDuration(getContext().getResources().getInteger(android.R.integer.config_mediumAnimTime));
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.rey.material.app.DatePickerDialog.DatePickerLayout.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    view.setVisibility(8);
                }
            });
            view.startAnimation(alphaAnimation);
        }

        private void animIn(final View view) {
            AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);
            alphaAnimation.setDuration(getContext().getResources().getInteger(android.R.integer.config_mediumAnimTime));
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.rey.material.app.DatePickerDialog.DatePickerLayout.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(0);
                }
            });
            view.startAnimation(alphaAnimation);
        }

        public void applyStyle(int i) {
            this.mYearPicker.applyStyle(i);
            this.mDatePicker.applyStyle(i);
            int selectionColor = this.mDatePicker.getSelectionColor();
            this.mHeaderPrimaryColor = selectionColor;
            this.mHeaderSecondaryColor = selectionColor;
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(i, R.styleable.DatePickerDialog);
            int indexCount = obtainStyledAttributes.getIndexCount();
            for (int i2 = 0; i2 < indexCount; i2++) {
                int index = obtainStyledAttributes.getIndex(i2);
                if (index == R.styleable.DatePickerDialog_dp_headerPrimaryHeight) {
                    this.mHeaderPrimaryHeight = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.DatePickerDialog_dp_headerSecondaryHeight) {
                    this.mHeaderSecondaryHeight = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.DatePickerDialog_dp_headerPrimaryColor) {
                    this.mHeaderPrimaryColor = obtainStyledAttributes.getColor(index, 0);
                } else if (index == R.styleable.DatePickerDialog_dp_headerSecondaryColor) {
                    this.mHeaderSecondaryColor = obtainStyledAttributes.getColor(index, 0);
                } else if (index == R.styleable.DatePickerDialog_dp_headerPrimaryTextSize) {
                    this.mHeaderPrimaryTextSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.DatePickerDialog_dp_headerSecondaryTextSize) {
                    this.mHeaderSecondaryTextSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.DatePickerDialog_dp_textHeaderColor) {
                    this.mTextHeaderColor = obtainStyledAttributes.getColor(index, 0);
                }
            }
            obtainStyledAttributes.recycle();
            this.mPaint.setTypeface(this.mDatePicker.getTypeface());
        }

        public void setDateRange(int i, int i2, int i3, int i4, int i5, int i6) {
            this.mDatePicker.setDateRange(i, i2, i3, i4, i5, i6);
            this.mYearPicker.setYearRange(i3, i6);
        }

        public void setDateRange(long j, long j2) {
            Calendar calendar = this.mDatePicker.getCalendar();
            calendar.setTimeInMillis(j);
            int i = calendar.get(5);
            int i2 = calendar.get(2);
            int i3 = calendar.get(1);
            calendar.setTimeInMillis(j2);
            setDateRange(i, i2, i3, calendar.get(5), calendar.get(2), calendar.get(1));
        }

        public void setDate(int i, int i2, int i3) {
            this.mDatePicker.setDate(i, i2, i3);
        }

        public void setDate(long j) {
            Calendar calendar = this.mDatePicker.getCalendar();
            calendar.setTimeInMillis(j);
            this.mDatePicker.setDate(calendar.get(5), calendar.get(2), calendar.get(1));
        }

        public int getDay() {
            return this.mDatePicker.getDay();
        }

        public int getMonth() {
            return this.mDatePicker.getMonth();
        }

        public int getYear() {
            return this.mDatePicker.getYear();
        }

        public String getFormattedDate(DateFormat dateFormat) {
            return this.mDatePicker.getFormattedDate(dateFormat);
        }

        public Calendar getCalendar() {
            return this.mDatePicker.getCalendar();
        }

        @Override // com.rey.material.widget.YearPicker.OnYearChangedListener
        public void onYearChanged(int i, int i2) {
            if (this.mDaySelectMode) {
                return;
            }
            DatePicker datePicker = this.mDatePicker;
            datePicker.setDate(datePicker.getDay(), this.mDatePicker.getMonth(), i2);
        }

        @Override // com.rey.material.widget.DatePicker.OnDateChangedListener
        public void onDateChanged(int i, int i2, int i3, int i4, int i5, int i6) {
            if (this.mDaySelectMode) {
                this.mYearPicker.setYear(i6);
            }
            if (i4 < 0 || i5 < 0 || i6 < 0) {
                this.mWeekDay = null;
                this.mMonth = null;
                this.mDay = null;
                this.mYear = null;
            } else {
                Calendar calendar = this.mDatePicker.getCalendar();
                calendar.set(1, i6);
                calendar.set(2, i5);
                calendar.set(5, i4);
                this.mWeekDay = calendar.getDisplayName(7, 2, Locale.getDefault());
                this.mMonth = calendar.getDisplayName(2, 1, Locale.getDefault());
                this.mDay = String.format(DAY_FORMAT, Integer.valueOf(i4));
                this.mYear = String.format(YEAR_FORMAT, Integer.valueOf(i6));
                if (i2 != i5 || i3 != i6) {
                    this.mDatePicker.goTo(i5, i6);
                }
            }
            this.mLocationDirty = true;
            invalidate(0, 0, this.mHeaderRealWidth, this.mHeaderPrimaryRealHeight + this.mHeaderSecondaryHeight);
            if (DatePickerDialog.this.mOnDateChangedListener != null) {
                DatePickerDialog.this.mOnDateChangedListener.onDateChanged(i, i2, i3, i4, i5, i6);
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            View.MeasureSpec.getMode(i);
            int size2 = View.MeasureSpec.getSize(i2);
            int mode = View.MeasureSpec.getMode(i2);
            if (getContext().getResources().getConfiguration().orientation == 1) {
                if (mode == Integer.MIN_VALUE) {
                    int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                    this.mDatePicker.measure(makeMeasureSpec, View.MeasureSpec.makeMeasureSpec(0, 0));
                    this.mYearPicker.measure(makeMeasureSpec, makeMeasureSpec);
                } else {
                    int max = Math.max((size2 - this.mHeaderSecondaryHeight) - this.mHeaderPrimaryHeight, this.mDatePicker.getMeasuredHeight());
                    int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(size, 1073741824);
                    this.mDatePicker.measure(makeMeasureSpec2, View.MeasureSpec.makeMeasureSpec(max, 1073741824));
                    this.mYearPicker.measure(makeMeasureSpec2, View.MeasureSpec.makeMeasureSpec(0, 0));
                    if (this.mYearPicker.getMeasuredHeight() != max) {
                        YearPicker yearPicker = this.mYearPicker;
                        yearPicker.measure(makeMeasureSpec2, View.MeasureSpec.makeMeasureSpec(Math.min(yearPicker.getMeasuredHeight(), max), 1073741824));
                    }
                }
                setMeasuredDimension(size, size2);
                return;
            }
            if (mode == Integer.MIN_VALUE) {
                int makeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(size / 2, 1073741824);
                this.mDatePicker.measure(makeMeasureSpec3, View.MeasureSpec.makeMeasureSpec(0, 0));
                this.mYearPicker.measure(makeMeasureSpec3, makeMeasureSpec3);
            } else {
                int max2 = Math.max(size2, this.mDatePicker.getMeasuredHeight());
                int makeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(size / 2, 1073741824);
                this.mDatePicker.measure(makeMeasureSpec4, View.MeasureSpec.makeMeasureSpec(max2, 1073741824));
                this.mYearPicker.measure(makeMeasureSpec4, View.MeasureSpec.makeMeasureSpec(0, 0));
                if (this.mYearPicker.getMeasuredHeight() != max2) {
                    YearPicker yearPicker2 = this.mYearPicker;
                    yearPicker2.measure(makeMeasureSpec4, View.MeasureSpec.makeMeasureSpec(Math.min(yearPicker2.getMeasuredHeight(), max2), 1073741824));
                }
            }
            setMeasuredDimension(size, size2);
        }

        @Override // android.view.View
        protected void onSizeChanged(int i, int i2, int i3, int i4) {
            if (getContext().getResources().getConfiguration().orientation == 1) {
                this.mHeaderRealWidth = i;
                this.mHeaderPrimaryRealHeight = (i2 - this.mHeaderSecondaryHeight) - this.mDatePicker.getMeasuredHeight();
                this.mHeaderSecondaryBackground.reset();
                if (DatePickerDialog.this.mCornerRadius == 0.0f) {
                    this.mHeaderSecondaryBackground.addRect(0.0f, 0.0f, this.mHeaderRealWidth, this.mHeaderSecondaryHeight, Path.Direction.CW);
                    return;
                }
                this.mHeaderSecondaryBackground.moveTo(0.0f, this.mHeaderSecondaryHeight);
                this.mHeaderSecondaryBackground.lineTo(0.0f, DatePickerDialog.this.mCornerRadius);
                this.mRect.set(0.0f, 0.0f, DatePickerDialog.this.mCornerRadius * 2.0f, DatePickerDialog.this.mCornerRadius * 2.0f);
                this.mHeaderSecondaryBackground.arcTo(this.mRect, 180.0f, 90.0f, false);
                this.mHeaderSecondaryBackground.lineTo(this.mHeaderRealWidth - DatePickerDialog.this.mCornerRadius, 0.0f);
                this.mRect.set(this.mHeaderRealWidth - (DatePickerDialog.this.mCornerRadius * 2.0f), 0.0f, this.mHeaderRealWidth, DatePickerDialog.this.mCornerRadius * 2.0f);
                this.mHeaderSecondaryBackground.arcTo(this.mRect, 270.0f, 90.0f, false);
                this.mHeaderSecondaryBackground.lineTo(this.mHeaderRealWidth, this.mHeaderSecondaryHeight);
                this.mHeaderSecondaryBackground.close();
                return;
            }
            this.mHeaderRealWidth = i - this.mDatePicker.getMeasuredWidth();
            this.mHeaderPrimaryRealHeight = i2 - this.mHeaderSecondaryHeight;
            this.mHeaderSecondaryBackground.reset();
            if (DatePickerDialog.this.mCornerRadius == 0.0f) {
                this.mHeaderSecondaryBackground.addRect(0.0f, 0.0f, this.mHeaderRealWidth, this.mHeaderSecondaryHeight, Path.Direction.CW);
                return;
            }
            this.mHeaderSecondaryBackground.moveTo(0.0f, this.mHeaderSecondaryHeight);
            this.mHeaderSecondaryBackground.lineTo(0.0f, DatePickerDialog.this.mCornerRadius);
            this.mRect.set(0.0f, 0.0f, DatePickerDialog.this.mCornerRadius * 2.0f, DatePickerDialog.this.mCornerRadius * 2.0f);
            this.mHeaderSecondaryBackground.arcTo(this.mRect, 180.0f, 90.0f, false);
            this.mHeaderSecondaryBackground.lineTo(this.mHeaderRealWidth, 0.0f);
            this.mHeaderSecondaryBackground.lineTo(this.mHeaderRealWidth, this.mHeaderSecondaryHeight);
            this.mHeaderSecondaryBackground.close();
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int i5;
            int i6 = i3 - i;
            int i7 = i4 - i2;
            int i8 = 0;
            if (getContext().getResources().getConfiguration().orientation == 1) {
                i5 = this.mHeaderPrimaryRealHeight + this.mHeaderSecondaryHeight + 0;
            } else {
                i8 = this.mHeaderRealWidth + 0;
                i5 = 0;
            }
            this.mDatePicker.layout(i8, i5, i6, i7);
            int measuredHeight = ((i7 + i5) - this.mYearPicker.getMeasuredHeight()) / 2;
            YearPicker yearPicker = this.mYearPicker;
            yearPicker.layout(i8, measuredHeight, i6, yearPicker.getMeasuredHeight() + measuredHeight);
        }

        private void measureHeaderText() {
            if (this.mLocationDirty) {
                if (this.mWeekDay == null) {
                    this.mLocationDirty = false;
                    return;
                }
                this.mBaseX = this.mHeaderRealWidth / 2.0f;
                Rect rect = new Rect();
                this.mPaint.setTextSize(this.mDatePicker.getTextSize());
                this.mPaint.getTextBounds(BASE_TEXT, 0, 1, rect);
                this.mWeekDayY = (this.mHeaderSecondaryHeight + rect.height()) / 2.0f;
                this.mPaint.setTextSize(this.mHeaderPrimaryTextSize);
                this.mPaint.getTextBounds(BASE_TEXT, 0, 1, rect);
                int height = rect.height();
                if (this.mMonthFirst) {
                    Paint paint = this.mPaint;
                    String str = this.mDay;
                    this.mFirstWidth = paint.measureText(str, 0, str.length());
                } else {
                    Paint paint2 = this.mPaint;
                    String str2 = this.mMonth;
                    this.mFirstWidth = paint2.measureText(str2, 0, str2.length());
                }
                this.mPaint.setTextSize(this.mHeaderSecondaryTextSize);
                this.mPaint.getTextBounds(BASE_TEXT, 0, 1, rect);
                int height2 = rect.height();
                if (this.mMonthFirst) {
                    float f = this.mFirstWidth;
                    Paint paint3 = this.mPaint;
                    String str3 = this.mMonth;
                    this.mFirstWidth = Math.max(f, paint3.measureText(str3, 0, str3.length()));
                } else {
                    float f2 = this.mFirstWidth;
                    Paint paint4 = this.mPaint;
                    String str4 = this.mDay;
                    this.mFirstWidth = Math.max(f2, paint4.measureText(str4, 0, str4.length()));
                }
                Paint paint5 = this.mPaint;
                String str5 = this.mYear;
                this.mSecondWidth = paint5.measureText(str5, 0, str5.length());
                int i = this.mHeaderSecondaryHeight;
                int i2 = this.mHeaderPrimaryRealHeight;
                float f3 = i + ((i2 + height) / 2.0f);
                this.mCenterY = f3;
                float f4 = (((i2 - height) / 2.0f) + height2) / 2.0f;
                float f5 = i + f4;
                float f6 = f4 + f3;
                if (this.mMonthFirst) {
                    this.mDayY = f3;
                    this.mMonthY = f5;
                } else {
                    this.mMonthY = f3;
                    this.mDayY = f5;
                }
                this.mYearY = f6;
                this.mLocationDirty = false;
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
            this.mPaint.setColor(this.mHeaderSecondaryColor);
            canvas.drawPath(this.mHeaderSecondaryBackground, this.mPaint);
            this.mPaint.setColor(this.mHeaderPrimaryColor);
            canvas.drawRect(0.0f, this.mHeaderSecondaryHeight, this.mHeaderRealWidth, this.mHeaderPrimaryRealHeight + r0, this.mPaint);
            measureHeaderText();
            if (this.mWeekDay == null) {
                return;
            }
            this.mPaint.setTextSize(this.mDatePicker.getTextSize());
            this.mPaint.setColor(this.mDatePicker.getTextHighlightColor());
            String str = this.mWeekDay;
            canvas.drawText(str, 0, str.length(), this.mBaseX, this.mWeekDayY, this.mPaint);
            this.mPaint.setColor(this.mDaySelectMode ? this.mDatePicker.getTextHighlightColor() : this.mTextHeaderColor);
            this.mPaint.setTextSize(this.mHeaderPrimaryTextSize);
            if (this.mMonthFirst) {
                String str2 = this.mDay;
                canvas.drawText(str2, 0, str2.length(), this.mBaseX, this.mDayY, this.mPaint);
            } else {
                String str3 = this.mMonth;
                canvas.drawText(str3, 0, str3.length(), this.mBaseX, this.mMonthY, this.mPaint);
            }
            this.mPaint.setTextSize(this.mHeaderSecondaryTextSize);
            if (this.mMonthFirst) {
                String str4 = this.mMonth;
                canvas.drawText(str4, 0, str4.length(), this.mBaseX, this.mMonthY, this.mPaint);
            } else {
                String str5 = this.mDay;
                canvas.drawText(str5, 0, str5.length(), this.mBaseX, this.mDayY, this.mPaint);
            }
            this.mPaint.setColor(this.mDaySelectMode ? this.mTextHeaderColor : this.mDatePicker.getTextHighlightColor());
            String str6 = this.mYear;
            canvas.drawText(str6, 0, str6.length(), this.mBaseX, this.mYearY, this.mPaint);
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            boolean onTouchEvent = super.onTouchEvent(motionEvent);
            if (onTouchEvent) {
                return onTouchEvent;
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                float f = this.mBaseX;
                float f2 = this.mFirstWidth;
                if (isTouched(f - (f2 / 2.0f), this.mHeaderSecondaryHeight, f + (f2 / 2.0f), this.mCenterY, motionEvent.getX(), motionEvent.getY())) {
                    return !this.mDaySelectMode;
                }
                float f3 = this.mBaseX;
                float f4 = this.mSecondWidth;
                if (isTouched(f3 - (f4 / 2.0f), this.mCenterY, f3 + (f4 / 2.0f), this.mHeaderSecondaryHeight + this.mHeaderPrimaryRealHeight, motionEvent.getX(), motionEvent.getY())) {
                    return this.mDaySelectMode;
                }
            } else if (action == 1) {
                float f5 = this.mBaseX;
                float f6 = this.mFirstWidth;
                if (isTouched(f5 - (f6 / 2.0f), this.mHeaderSecondaryHeight, f5 + (f6 / 2.0f), this.mCenterY, motionEvent.getX(), motionEvent.getY())) {
                    setDateSelectMode(true);
                    return true;
                }
                float f7 = this.mBaseX;
                float f8 = this.mSecondWidth;
                if (isTouched(f7 - (f8 / 2.0f), this.mCenterY, f7 + (f8 / 2.0f), this.mHeaderSecondaryHeight + this.mHeaderPrimaryRealHeight, motionEvent.getX(), motionEvent.getY())) {
                    setDateSelectMode(false);
                    return true;
                }
            }
            return false;
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder extends Dialog.Builder implements OnDateChangedListener {
        public static final Parcelable.Creator<Builder> CREATOR = new Parcelable.Creator<Builder>() { // from class: com.rey.material.app.DatePickerDialog.Builder.1
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
        private Calendar mCalendar;
        protected int mDay;
        protected int mMaxDay;
        protected int mMaxMonth;
        protected int mMaxYear;
        protected int mMinDay;
        protected int mMinMonth;
        protected int mMinYear;
        protected int mMonth;
        protected int mYear;

        @Override // com.rey.material.app.Dialog.Builder
        public Dialog.Builder contentView(int i) {
            return this;
        }

        public Builder() {
            this(R.style.Material_App_Dialog_DatePicker_Light);
        }

        public Builder(int i) {
            super(i);
            Calendar calendar = Calendar.getInstance();
            this.mDay = calendar.get(5);
            this.mMonth = calendar.get(2);
            int i2 = calendar.get(1);
            this.mYear = i2;
            int i3 = this.mDay;
            this.mMinDay = i3;
            int i4 = this.mMonth;
            this.mMinMonth = i4;
            this.mMinYear = i2 - 12;
            this.mMaxDay = i3;
            this.mMaxMonth = i4;
            this.mMaxYear = i2 + 12;
        }

        public Builder(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
            this(R.style.Material_App_Dialog_DatePicker_Light, i, i2, i3, i4, i5, i6, i7, i8, i9);
        }

        public Builder(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
            super(i);
            this.mMinDay = i2;
            this.mMinMonth = i3;
            this.mMinYear = i4;
            this.mMaxDay = i5;
            this.mMaxMonth = i6;
            this.mMaxYear = i7;
            this.mDay = i8;
            this.mMonth = i9;
            this.mYear = i10;
        }

        public Builder dateRange(int i, int i2, int i3, int i4, int i5, int i6) {
            this.mMinDay = i;
            this.mMinMonth = i2;
            this.mMinYear = i3;
            this.mMaxDay = i4;
            this.mMaxMonth = i5;
            this.mMaxYear = i6;
            return this;
        }

        public Builder dateRange(long j, long j2) {
            if (this.mCalendar == null) {
                this.mCalendar = Calendar.getInstance();
            }
            this.mCalendar.setTimeInMillis(j);
            int i = this.mCalendar.get(5);
            int i2 = this.mCalendar.get(2);
            int i3 = this.mCalendar.get(1);
            this.mCalendar.setTimeInMillis(j2);
            return dateRange(i, i2, i3, this.mCalendar.get(5), this.mCalendar.get(2), this.mCalendar.get(1));
        }

        public Builder date(int i, int i2, int i3) {
            this.mDay = i;
            this.mMonth = i2;
            this.mYear = i3;
            return this;
        }

        public Builder date(long j) {
            if (this.mCalendar == null) {
                this.mCalendar = Calendar.getInstance();
            }
            this.mCalendar.setTimeInMillis(j);
            return date(this.mCalendar.get(5), this.mCalendar.get(2), this.mCalendar.get(1));
        }

        public int getDay() {
            return this.mDay;
        }

        public int getMonth() {
            return this.mMonth;
        }

        public int getYear() {
            return this.mYear;
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected Dialog onBuild(Context context, int i) {
            DatePickerDialog datePickerDialog = new DatePickerDialog(context, i);
            datePickerDialog.dateRange(this.mMinDay, this.mMinMonth, this.mMinYear, this.mMaxDay, this.mMaxMonth, this.mMaxYear).date(this.mDay, this.mMonth, this.mYear).onDateChangedListener(this);
            return datePickerDialog;
        }

        @Override // com.rey.material.app.DatePickerDialog.OnDateChangedListener
        public void onDateChanged(int i, int i2, int i3, int i4, int i5, int i6) {
            date(i4, i5, i6);
        }

        protected Builder(Parcel parcel) {
            super(parcel);
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected void onReadFromParcel(Parcel parcel) {
            this.mMinDay = parcel.readInt();
            this.mMinMonth = parcel.readInt();
            this.mMinYear = parcel.readInt();
            this.mMaxDay = parcel.readInt();
            this.mMaxMonth = parcel.readInt();
            this.mMaxYear = parcel.readInt();
            this.mDay = parcel.readInt();
            this.mMonth = parcel.readInt();
            this.mYear = parcel.readInt();
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected void onWriteToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mMinDay);
            parcel.writeInt(this.mMinMonth);
            parcel.writeInt(this.mMinYear);
            parcel.writeInt(this.mMaxDay);
            parcel.writeInt(this.mMaxMonth);
            parcel.writeInt(this.mMaxYear);
            parcel.writeInt(this.mDay);
            parcel.writeInt(this.mMonth);
            parcel.writeInt(this.mYear);
        }
    }
}
