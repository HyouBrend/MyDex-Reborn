package com.rey.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import androidx.exifinterface.media.ExifInterface;
import com.rey.material.R;
import com.rey.material.drawable.BlankDrawable;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.TypefaceUtil;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/* loaded from: classes2.dex */
public class DatePicker extends ListView implements AbsListView.OnScrollListener {
    private static final String DAY_FORMAT = "%2d";
    protected static final int LIST_TOP_OFFSET = -1;
    protected static final int SCROLL_CHANGE_DELAY = 40;
    protected static final int SCROLL_DURATION = 250;
    private static final String YEAR_FORMAT = "%4d";
    private static String[] mDayTexts;
    private MonthAdapter mAdapter;
    private int mAnimDuration;
    private Calendar mCalendar;
    protected int mCurrentScrollState;
    private float mDayBaseHeight;
    private float mDayBaseWidth;
    private float mDayHeight;
    private int mDayPadding;
    private float mDayWidth;
    private int mFirstDayOfWeek;
    protected float mFriction;
    protected Handler mHandler;
    private Interpolator mInInterpolator;
    private String[] mLabels;
    private int mMonthRealHeight;
    private int mMonthRealWidth;
    private OnDateChangedListener mOnDateChangedListener;
    private Interpolator mOutInterpolator;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private Paint mPaint;
    protected long mPreviousScrollPosition;
    protected int mPreviousScrollState;
    protected ScrollStateRunnable mScrollStateChangedRunnable;
    private int mSelectionColor;
    private float mSelectionRadius;
    private int mTextColor;
    private int mTextDisableColor;
    private int mTextHighlightColor;
    private int mTextLabelColor;
    private int mTextSize;
    private Typeface mTypeface;

    /* loaded from: classes2.dex */
    public interface OnDateChangedListener {
        void onDateChanged(int i, int i2, int i3, int i4, int i5, int i6);
    }

    public DatePicker(Context context) {
        super(context);
        this.mHandler = new Handler();
        this.mCurrentScrollState = 0;
        this.mPreviousScrollState = 0;
        this.mFriction = 1.0f;
        this.mScrollStateChangedRunnable = new ScrollStateRunnable();
        init(context, null, 0, 0);
    }

    public DatePicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mHandler = new Handler();
        this.mCurrentScrollState = 0;
        this.mPreviousScrollState = 0;
        this.mFriction = 1.0f;
        this.mScrollStateChangedRunnable = new ScrollStateRunnable();
        init(context, attributeSet, 0, 0);
    }

    public DatePicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mHandler = new Handler();
        this.mCurrentScrollState = 0;
        this.mPreviousScrollState = 0;
        this.mFriction = 1.0f;
        this.mScrollStateChangedRunnable = new ScrollStateRunnable();
        init(context, attributeSet, i, 0);
    }

    public DatePicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mHandler = new Handler();
        this.mCurrentScrollState = 0;
        this.mPreviousScrollState = 0;
        this.mFriction = 1.0f;
        this.mScrollStateChangedRunnable = new ScrollStateRunnable();
        init(context, attributeSet, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.ListView
    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mTypeface = Typeface.DEFAULT;
        this.mTextSize = -1;
        this.mTextColor = -16777216;
        this.mTextLabelColor = -9013642;
        this.mTextHighlightColor = -1;
        this.mAnimDuration = -1;
        this.mLabels = new String[7];
        this.mFriction = 1.0f;
        setWillNotDraw(false);
        setSelector(BlankDrawable.getInstance());
        setCacheColorHint(0);
        setDivider(null);
        setItemsCanFocus(true);
        setFastScrollEnabled(false);
        setVerticalScrollBarEnabled(false);
        setOnScrollListener(this);
        setFadingEdgeLength(0);
        setFrictionIfSupported(ViewConfiguration.getScrollFriction() * this.mFriction);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setTextAlign(Paint.Align.CENTER);
        this.mDayPadding = ThemeUtil.dpToPx(context, 4);
        this.mSelectionColor = ThemeUtil.colorPrimary(context, -16777216);
        Calendar calendar = Calendar.getInstance();
        this.mCalendar = calendar;
        this.mFirstDayOfWeek = calendar.getFirstDayOfWeek();
        int i3 = this.mCalendar.get(7) - 1;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Build.VERSION.SDK_INT >= 18 ? "EEEEE" : ExifInterface.LONGITUDE_EAST);
        for (int i4 = 0; i4 < 7; i4++) {
            this.mLabels[i3] = simpleDateFormat.format(this.mCalendar.getTime());
            i3 = (i3 + 1) % 7;
            this.mCalendar.add(5, 1);
        }
        MonthAdapter monthAdapter = new MonthAdapter();
        this.mAdapter = monthAdapter;
        setAdapter(monthAdapter);
        super.init(context, attributeSet, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.ListView
    public void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        super.applyStyle(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.DatePicker, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        String str = null;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = -1;
        int i8 = -1;
        boolean z = false;
        for (int i9 = 0; i9 < indexCount; i9++) {
            int index = obtainStyledAttributes.getIndex(i9);
            if (index == R.styleable.DatePicker_dp_dayTextSize) {
                this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.DatePicker_dp_textColor) {
                this.mTextColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.DatePicker_dp_textHighlightColor) {
                this.mTextHighlightColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.DatePicker_dp_textLabelColor) {
                this.mTextLabelColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.DatePicker_dp_textDisableColor) {
                this.mTextDisableColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.DatePicker_dp_selectionColor) {
                this.mSelectionColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == R.styleable.DatePicker_dp_animDuration) {
                this.mAnimDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == R.styleable.DatePicker_dp_inInterpolator) {
                this.mInInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.DatePicker_dp_outInterpolator) {
                this.mOutInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.DatePicker_dp_fontFamily) {
                str = obtainStyledAttributes.getString(index);
            } else if (index == R.styleable.DatePicker_dp_textStyle) {
                i3 = obtainStyledAttributes.getInteger(index, 0);
            } else {
                if (index == R.styleable.DatePicker_android_padding) {
                    i4 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.DatePicker_android_paddingLeft) {
                    i5 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.DatePicker_android_paddingTop) {
                    i6 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.DatePicker_android_paddingRight) {
                    i7 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                } else if (index == R.styleable.DatePicker_android_paddingBottom) {
                    i8 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
                }
                z = true;
            }
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
        if (str != null || i3 >= 0) {
            this.mTypeface = TypefaceUtil.load(context, str, i3);
        }
        obtainStyledAttributes.recycle();
        if (z) {
            if (i4 >= 0) {
                setContentPadding(i4, i4, i4, i4);
            }
            if (i5 >= 0) {
                this.mPaddingLeft = i5;
            }
            if (i6 >= 0) {
                this.mPaddingTop = i6;
            }
            if (i7 >= 0) {
                this.mPaddingRight = i7;
            }
            if (i8 >= 0) {
                this.mPaddingBottom = i8;
            }
        }
        requestLayout();
        this.mAdapter.notifyDataSetInvalidated();
    }

    private void setFrictionIfSupported(float f) {
        if (Build.VERSION.SDK_INT >= 11) {
            setFriction(f);
        }
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (((MonthView) absListView.getChildAt(0)) == null) {
            return;
        }
        this.mPreviousScrollPosition = (getFirstVisiblePosition() * r1.getHeight()) - r1.getBottom();
        this.mPreviousScrollState = this.mCurrentScrollState;
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        this.mScrollStateChangedRunnable.doScrollStateChange(absListView, i);
    }

    private void measureBaseSize() {
        this.mPaint.setTextSize(this.mTextSize);
        this.mPaint.setTypeface(this.mTypeface);
        this.mDayBaseWidth = this.mPaint.measureText("88", 0, 2) + (this.mDayPadding * 2);
        this.mPaint.getTextBounds("88", 0, 2, new Rect());
        this.mDayBaseHeight = r0.height();
    }

    private void measureMonthView(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        measureBaseSize();
        int round = Math.round(Math.max(this.mDayBaseWidth, this.mDayBaseHeight)) * 7;
        int i3 = this.mPaddingLeft + round + this.mPaddingRight;
        int round2 = Math.round(round + this.mDayBaseHeight + (this.mDayPadding * 2) + this.mPaddingTop + this.mPaddingBottom);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(i3, size);
        } else if (mode != 1073741824) {
            size = i3;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(round2, size2);
        } else if (mode2 != 1073741824) {
            size2 = round2;
        }
        this.mMonthRealWidth = size;
        this.mMonthRealHeight = size2;
    }

    protected void onMeasure(int i, int i2) {
        measureMonthView(i, i2);
        super.onMeasure(i, i2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        float f = ((i - this.mPaddingLeft) - this.mPaddingRight) / 7.0f;
        this.mDayWidth = f;
        float f2 = ((((i2 - this.mDayBaseHeight) - (this.mDayPadding * 2)) - this.mPaddingTop) - this.mPaddingBottom) / 7.0f;
        this.mDayHeight = f2;
        this.mSelectionRadius = Math.min(f, f2) / 2.0f;
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        super.setPadding(0, 0, 0, 0);
    }

    public void setContentPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getDayText(int i) {
        if (mDayTexts == null) {
            synchronized (DatePicker.class) {
                if (mDayTexts == null) {
                    mDayTexts = new String[31];
                }
            }
        }
        String[] strArr = mDayTexts;
        int i2 = i - 1;
        if (strArr[i2] == null) {
            strArr[i2] = String.format(DAY_FORMAT, Integer.valueOf(i));
        }
        return mDayTexts[i2];
    }

    public void setDateRange(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mAdapter.setDateRange(i, i2, i3, i4, i5, i6);
    }

    public void goTo(int i, int i2) {
        postSetSelectionFromTop(this.mAdapter.positionOfMonth(i, i2), 0);
    }

    public void postSetSelectionFromTop(final int i, final int i2) {
        post(new Runnable() { // from class: com.rey.material.widget.DatePicker.1
            @Override // java.lang.Runnable
            public void run() {
                DatePicker.this.setSelectionFromTop(i, i2);
                DatePicker.this.requestLayout();
            }
        });
    }

    public void setDate(int i, int i2, int i3) {
        if (this.mAdapter.getYear() == i3 && this.mAdapter.getMonth() == i2 && this.mAdapter.getDay() == i) {
            return;
        }
        this.mAdapter.setDate(i, i2, i3, false);
        goTo(i2, i3);
    }

    public void setOnDateChangedListener(OnDateChangedListener onDateChangedListener) {
        this.mOnDateChangedListener = onDateChangedListener;
    }

    public int getDay() {
        return this.mAdapter.getDay();
    }

    public int getMonth() {
        return this.mAdapter.getMonth();
    }

    public int getYear() {
        return this.mAdapter.getYear();
    }

    public String getFormattedDate(DateFormat dateFormat) {
        this.mCalendar.set(1, this.mAdapter.getYear());
        this.mCalendar.set(2, this.mAdapter.getMonth());
        this.mCalendar.set(5, this.mAdapter.getDay());
        return dateFormat.format(this.mCalendar.getTime());
    }

    public int getSelectionColor() {
        return this.mSelectionColor;
    }

    public int getTextSize() {
        return this.mTextSize;
    }

    public Typeface getTypeface() {
        return this.mTypeface;
    }

    public int getTextColor() {
        return this.mTextColor;
    }

    public int getTextLabelColor() {
        return this.mTextLabelColor;
    }

    public int getTextHighlightColor() {
        return this.mTextHighlightColor;
    }

    public int getTextDisableColor() {
        return this.mTextDisableColor;
    }

    public Calendar getCalendar() {
        return this.mCalendar;
    }

    /* loaded from: classes2.dex */
    private class ScrollStateRunnable implements Runnable {
        private int mNewState;

        private ScrollStateRunnable() {
        }

        public void doScrollStateChange(AbsListView absListView, int i) {
            DatePicker.this.mHandler.removeCallbacks(this);
            this.mNewState = i;
            DatePicker.this.mHandler.postDelayed(this, 40L);
        }

        @Override // java.lang.Runnable
        public void run() {
            DatePicker.this.mCurrentScrollState = this.mNewState;
            if (this.mNewState == 0 && DatePicker.this.mPreviousScrollState != 0) {
                if (DatePicker.this.mPreviousScrollState != 1) {
                    DatePicker.this.mPreviousScrollState = this.mNewState;
                    View childAt = DatePicker.this.getChildAt(0);
                    int i = 0;
                    while (childAt != null && childAt.getBottom() <= 0) {
                        i++;
                        childAt = DatePicker.this.getChildAt(i);
                    }
                    if (childAt == null) {
                        return;
                    }
                    boolean z = (DatePicker.this.getFirstVisiblePosition() == 0 || DatePicker.this.getLastVisiblePosition() == DatePicker.this.getCount() - 1) ? false : true;
                    int top = childAt.getTop();
                    int bottom = childAt.getBottom();
                    int height = DatePicker.this.getHeight() / 2;
                    if (!z || top >= -1) {
                        return;
                    }
                    if (bottom > height) {
                        DatePicker.this.smoothScrollBy(top, 250);
                        return;
                    } else {
                        DatePicker.this.smoothScrollBy(bottom, 250);
                        return;
                    }
                }
            }
            DatePicker.this.mPreviousScrollState = this.mNewState;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class MonthView extends View {
        private float mAnimProgress;
        private int mFirstDayCol;
        private int mMaxAvailDay;
        private int mMaxDay;
        private int mMinAvailDay;
        private int mMonth;
        private String mMonthText;
        private int mPreviousSelectedDay;
        private boolean mRunning;
        private int mSelectedDay;
        private long mStartTime;
        private int mToday;
        private int mTouchedDay;
        private final Runnable mUpdater;
        private int mYear;

        public MonthView(Context context) {
            super(context);
            this.mTouchedDay = -1;
            this.mMinAvailDay = -1;
            this.mMaxAvailDay = -1;
            this.mToday = -1;
            this.mSelectedDay = -1;
            this.mPreviousSelectedDay = -1;
            this.mUpdater = new Runnable() { // from class: com.rey.material.widget.DatePicker.MonthView.1
                @Override // java.lang.Runnable
                public void run() {
                    MonthView.this.update();
                }
            };
            setWillNotDraw(false);
        }

        public void setMonth(int i, int i2) {
            if (this.mMonth == i && this.mYear == i2) {
                return;
            }
            this.mMonth = i;
            this.mYear = i2;
            calculateMonthView();
            invalidate();
        }

        public void setSelectedDay(int i, boolean z) {
            int i2 = this.mSelectedDay;
            if (i2 != i) {
                this.mPreviousSelectedDay = i2;
                this.mSelectedDay = i;
                if (z) {
                    startAnimation();
                } else {
                    invalidate();
                }
            }
        }

        public void setToday(int i) {
            if (this.mToday != i) {
                this.mToday = i;
                invalidate();
            }
        }

        public void setAvailableDay(int i, int i2) {
            if (this.mMinAvailDay == i && this.mMaxAvailDay == i2) {
                return;
            }
            this.mMinAvailDay = i;
            this.mMaxAvailDay = i2;
            invalidate();
        }

        private void calculateMonthView() {
            DatePicker.this.mCalendar.set(5, 1);
            DatePicker.this.mCalendar.set(2, this.mMonth);
            DatePicker.this.mCalendar.set(1, this.mYear);
            this.mMaxDay = DatePicker.this.mCalendar.getActualMaximum(5);
            int i = DatePicker.this.mCalendar.get(7);
            if (i < DatePicker.this.mFirstDayOfWeek) {
                i += 7;
            }
            this.mFirstDayCol = i - DatePicker.this.mFirstDayOfWeek;
            this.mMonthText = DatePicker.this.mCalendar.getDisplayName(2, 2, Locale.getDefault()) + " " + String.format(DatePicker.YEAR_FORMAT, Integer.valueOf(this.mYear));
        }

        @Override // android.view.View
        protected void onMeasure(int i, int i2) {
            setMeasuredDimension(DatePicker.this.mMonthRealWidth, DatePicker.this.mMonthRealHeight);
        }

        @Override // android.view.View
        protected void onDraw(Canvas canvas) {
            int i;
            DatePicker.this.mPaint.setTextSize(DatePicker.this.mTextSize);
            DatePicker.this.mPaint.setTypeface(DatePicker.this.mTypeface);
            float paddingLeft = (DatePicker.this.mDayWidth * 3.5f) + getPaddingLeft();
            float paddingTop = (DatePicker.this.mDayPadding * 2) + DatePicker.this.mDayBaseHeight + getPaddingTop();
            DatePicker.this.mPaint.setFakeBoldText(true);
            DatePicker.this.mPaint.setColor(DatePicker.this.mTextColor);
            canvas.drawText(this.mMonthText, paddingLeft, paddingTop, DatePicker.this.mPaint);
            float paddingLeft2 = getPaddingLeft();
            float paddingTop2 = (DatePicker.this.mDayPadding * 2) + DatePicker.this.mDayBaseHeight + getPaddingTop();
            int i2 = this.mSelectedDay;
            if (i2 > 0) {
                int i3 = this.mFirstDayCol;
                int i4 = ((i3 + i2) - 1) % 7;
                int i5 = (((i3 + i2) - 1) / 7) + 1;
                float f = ((i4 + 0.5f) * DatePicker.this.mDayWidth) + paddingLeft2;
                float f2 = ((i5 + 0.5f) * DatePicker.this.mDayHeight) + paddingTop2;
                float interpolation = this.mRunning ? DatePicker.this.mInInterpolator.getInterpolation(this.mAnimProgress) * DatePicker.this.mSelectionRadius : DatePicker.this.mSelectionRadius;
                DatePicker.this.mPaint.setColor(DatePicker.this.mSelectionColor);
                canvas.drawCircle(f, f2, interpolation, DatePicker.this.mPaint);
            }
            if (this.mRunning && (i = this.mPreviousSelectedDay) > 0) {
                int i6 = this.mFirstDayCol;
                int i7 = ((i6 + i) - 1) % 7;
                int i8 = (((i6 + i) - 1) / 7) + 1;
                float f3 = ((i7 + 0.5f) * DatePicker.this.mDayWidth) + paddingLeft2;
                float f4 = ((i8 + 0.5f) * DatePicker.this.mDayHeight) + paddingTop2;
                float interpolation2 = (1.0f - DatePicker.this.mOutInterpolator.getInterpolation(this.mAnimProgress)) * DatePicker.this.mSelectionRadius;
                DatePicker.this.mPaint.setColor(DatePicker.this.mSelectionColor);
                canvas.drawCircle(f3, f4, interpolation2, DatePicker.this.mPaint);
            }
            DatePicker.this.mPaint.setFakeBoldText(false);
            DatePicker.this.mPaint.setColor(DatePicker.this.mTextLabelColor);
            float f5 = paddingTop2 + ((DatePicker.this.mDayHeight + DatePicker.this.mDayBaseHeight) / 2.0f);
            for (int i9 = 0; i9 < 7; i9++) {
                canvas.drawText(DatePicker.this.mLabels[((DatePicker.this.mFirstDayOfWeek + i9) - 1) % 7], ((i9 + 0.5f) * DatePicker.this.mDayWidth) + paddingLeft2, f5, DatePicker.this.mPaint);
            }
            int i10 = this.mFirstDayCol;
            int i11 = this.mMaxAvailDay;
            int min = i11 > 0 ? Math.min(i11, this.mMaxDay) : this.mMaxDay;
            int i12 = 1;
            for (int i13 = 1; i13 <= this.mMaxDay; i13++) {
                if (i13 == this.mSelectedDay) {
                    DatePicker.this.mPaint.setColor(DatePicker.this.mTextHighlightColor);
                } else if (i13 < this.mMinAvailDay || i13 > min) {
                    DatePicker.this.mPaint.setColor(DatePicker.this.mTextDisableColor);
                } else if (i13 == this.mToday) {
                    DatePicker.this.mPaint.setColor(DatePicker.this.mSelectionColor);
                } else {
                    DatePicker.this.mPaint.setColor(DatePicker.this.mTextColor);
                }
                canvas.drawText(DatePicker.this.getDayText(i13), ((i10 + 0.5f) * DatePicker.this.mDayWidth) + paddingLeft2, (i12 * DatePicker.this.mDayHeight) + f5, DatePicker.this.mPaint);
                i10++;
                if (i10 == 7) {
                    i12++;
                    i10 = 0;
                }
            }
        }

        private int getTouchedDay(float f, float f2) {
            float paddingTop = (DatePicker.this.mDayPadding * 2) + DatePicker.this.mDayBaseHeight + getPaddingTop() + DatePicker.this.mDayHeight;
            if (f >= getPaddingLeft() && f <= getWidth() - getPaddingRight() && f2 >= paddingTop && f2 <= getHeight() - getPaddingBottom()) {
                int floor = (int) Math.floor((f - getPaddingLeft()) / DatePicker.this.mDayWidth);
                int floor2 = (int) Math.floor((f2 - paddingTop) / DatePicker.this.mDayHeight);
                int i = this.mMaxAvailDay;
                int min = i > 0 ? Math.min(i, this.mMaxDay) : this.mMaxDay;
                int i2 = (((floor2 * 7) + floor) - this.mFirstDayCol) + 1;
                if (i2 >= 0 && i2 >= this.mMinAvailDay && i2 <= min) {
                    return i2;
                }
            }
            return -1;
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            int action = motionEvent.getAction();
            if (action == 0) {
                this.mTouchedDay = getTouchedDay(motionEvent.getX(), motionEvent.getY());
                return true;
            }
            if (action != 1) {
                if (action != 3) {
                    return true;
                }
                this.mTouchedDay = -1;
                return true;
            }
            int touchedDay = getTouchedDay(motionEvent.getX(), motionEvent.getY());
            int i = this.mTouchedDay;
            if (touchedDay == i && i > 0) {
                DatePicker.this.mAdapter.setDate(this.mTouchedDay, this.mMonth, this.mYear, true);
                this.mTouchedDay = -1;
            }
            return true;
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
            float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / DatePicker.this.mAnimDuration);
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
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class MonthAdapter extends BaseAdapter {
        private int mDay;
        private int mMaxDay;
        private int mMaxMonth;
        private int mMaxMonthValue;
        private int mMaxYear;
        private int mMinDay;
        private int mMinMonth;
        private int mMinMonthValue;
        private int mMinYear;
        private int mMonth;
        private int mToday;
        private int mTodayMonth;
        private int mTodayYear;
        private int mYear;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        private MonthAdapter() {
            this.mDay = -1;
            this.mMonth = -1;
            this.mYear = -1;
            this.mMinDay = -1;
            this.mMinMonth = -1;
            this.mMinYear = -1;
            this.mMaxDay = -1;
            this.mMaxMonth = -1;
            this.mMaxYear = -1;
        }

        public void setDateRange(int i, int i2, int i3, int i4, int i5, int i6) {
            int i7 = (i < 0 || i2 < 0 || i3 < 0) ? 0 : (i3 * 12) + i2;
            int i8 = (i4 < 0 || i5 < 0 || i6 < 0) ? 2147483646 : (i6 * 12) + i5;
            if (i == this.mMinDay && this.mMinMonthValue == i7 && i4 == this.mMaxDay && this.mMaxMonthValue == i8) {
                return;
            }
            this.mMinDay = i;
            this.mMinMonth = i2;
            this.mMinYear = i3;
            this.mMaxDay = i4;
            this.mMaxMonth = i5;
            this.mMaxYear = i6;
            this.mMinMonthValue = i7;
            this.mMaxMonthValue = i8;
            notifyDataSetChanged();
        }

        public void setDate(int i, int i2, int i3, boolean z) {
            int i4;
            int i5 = this.mMonth;
            if (i5 != i2 || (i4 = this.mYear) != i3) {
                MonthView monthView = (MonthView) DatePicker.this.getChildAt(positionOfMonth(i5, this.mYear) - DatePicker.this.getFirstVisiblePosition());
                if (monthView != null) {
                    monthView.setSelectedDay(-1, false);
                }
                int i6 = this.mDay;
                int i7 = this.mMonth;
                int i8 = this.mYear;
                this.mDay = i;
                this.mMonth = i2;
                this.mYear = i3;
                MonthView monthView2 = (MonthView) DatePicker.this.getChildAt(positionOfMonth(i2, i3) - DatePicker.this.getFirstVisiblePosition());
                if (monthView2 != null) {
                    monthView2.setSelectedDay(this.mDay, z);
                }
                if (DatePicker.this.mOnDateChangedListener != null) {
                    DatePicker.this.mOnDateChangedListener.onDateChanged(i6, i7, i8, this.mDay, this.mMonth, this.mYear);
                    return;
                }
                return;
            }
            int i9 = this.mDay;
            if (i != i9) {
                this.mDay = i;
                MonthView monthView3 = (MonthView) DatePicker.this.getChildAt(positionOfMonth(i5, i4) - DatePicker.this.getFirstVisiblePosition());
                if (monthView3 != null) {
                    monthView3.setSelectedDay(this.mDay, z);
                }
                if (DatePicker.this.mOnDateChangedListener != null) {
                    OnDateChangedListener onDateChangedListener = DatePicker.this.mOnDateChangedListener;
                    int i10 = this.mMonth;
                    int i11 = this.mYear;
                    onDateChangedListener.onDateChanged(i9, i10, i11, this.mDay, i10, i11);
                }
            }
        }

        public int positionOfMonth(int i, int i2) {
            return ((i2 * 12) + i) - this.mMinMonthValue;
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

        private void calToday() {
            DatePicker.this.mCalendar.setTimeInMillis(System.currentTimeMillis());
            this.mToday = DatePicker.this.mCalendar.get(5);
            this.mTodayMonth = DatePicker.this.mCalendar.get(2);
            this.mTodayYear = DatePicker.this.mCalendar.get(1);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return (this.mMaxMonthValue - this.mMinMonthValue) + 1;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(i + this.mMinMonthValue);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            MonthView monthView = (MonthView) view;
            if (monthView == null) {
                monthView = new MonthView(viewGroup.getContext());
                monthView.setPadding(DatePicker.this.mPaddingLeft, DatePicker.this.mPaddingTop, DatePicker.this.mPaddingRight, DatePicker.this.mPaddingBottom);
            }
            calToday();
            int intValue = ((Integer) getItem(i)).intValue();
            int i2 = intValue / 12;
            int i3 = intValue % 12;
            int i4 = -1;
            int i5 = (i3 == this.mMinMonth && i2 == this.mMinYear) ? this.mMinDay : -1;
            int i6 = (i3 == this.mMaxMonth && i2 == this.mMaxYear) ? this.mMaxDay : -1;
            int i7 = (this.mTodayMonth == i3 && this.mTodayYear == i2) ? this.mToday : -1;
            if (i3 == this.mMonth && i2 == this.mYear) {
                i4 = this.mDay;
            }
            monthView.setMonth(i3, i2);
            monthView.setToday(i7);
            monthView.setAvailableDay(i5, i6);
            monthView.setSelectedDay(i4, false);
            return monthView;
        }
    }
}
