package com.rey.material.widget;

import android.R;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.BaseAdapter;
import com.rey.material.drawable.BlankDrawable;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.TypefaceUtil;
import java.util.Calendar;

/* loaded from: classes2.dex */
public class YearPicker extends ListView {
    private static final int[][] STATES = {new int[]{-16842912}, new int[]{R.attr.state_checked}};
    private static final String YEAR_FORMAT = "%4d";
    private YearAdapter mAdapter;
    private int mAnimDuration;
    private int mDistanceShift;
    private Interpolator mInInterpolator;
    private int mItemHeight;
    private int mItemRealHeight;
    private OnYearChangedListener mOnYearChangedListener;
    private Interpolator mOutInterpolator;
    private int mPadding;
    private Paint mPaint;
    private int mPositionShift;
    private int mSelectionColor;
    private int[] mTextColors;
    private int mTextSize;
    private Typeface mTypeface;

    /* loaded from: classes2.dex */
    public interface OnYearChangedListener {
        void onYearChanged(int i, int i2);
    }

    public YearPicker(Context context) {
        super(context);
        this.mTextColors = new int[]{-16777216, -1};
    }

    public YearPicker(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mTextColors = new int[]{-16777216, -1};
    }

    public YearPicker(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mTextColors = new int[]{-16777216, -1};
    }

    public YearPicker(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mTextColors = new int[]{-16777216, -1};
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.ListView
    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mTextSize = -1;
        this.mItemHeight = -1;
        this.mAnimDuration = -1;
        this.mTypeface = Typeface.DEFAULT;
        this.mItemRealHeight = -1;
        setWillNotDraw(false);
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        YearAdapter yearAdapter = new YearAdapter();
        this.mAdapter = yearAdapter;
        setAdapter(yearAdapter);
        setScrollBarStyle(33554432);
        setSelector(BlankDrawable.getInstance());
        setDividerHeight(0);
        setCacheColorHint(0);
        setClipToPadding(false);
        this.mPadding = ThemeUtil.dpToPx(context, 4);
        this.mSelectionColor = ThemeUtil.colorPrimary(context, -16777216);
        super.init(context, attributeSet, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.ListView
    public void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        super.applyStyle(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, com.rey.material.R.styleable.YearPicker, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        String str = null;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        for (int i7 = 0; i7 < indexCount; i7++) {
            int index = obtainStyledAttributes.getIndex(i7);
            if (index == com.rey.material.R.styleable.YearPicker_dp_yearTextSize) {
                this.mTextSize = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_year) {
                i6 = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_yearMin) {
                i4 = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_yearMax) {
                i5 = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_yearItemHeight) {
                this.mItemHeight = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_textColor) {
                this.mTextColors[0] = obtainStyledAttributes.getColor(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_textHighlightColor) {
                this.mTextColors[1] = obtainStyledAttributes.getColor(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_selectionColor) {
                this.mSelectionColor = obtainStyledAttributes.getColor(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_animDuration) {
                this.mAnimDuration = obtainStyledAttributes.getInteger(index, 0);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_inInterpolator) {
                this.mInInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_outInterpolator) {
                this.mOutInterpolator = AnimationUtils.loadInterpolator(context, obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_fontFamily) {
                str = obtainStyledAttributes.getString(index);
            } else if (index == com.rey.material.R.styleable.YearPicker_dp_textStyle) {
                i3 = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
        if (this.mTextSize < 0) {
            this.mTextSize = context.getResources().getDimensionPixelOffset(com.rey.material.R.dimen.abc_text_size_title_material);
        }
        if (this.mItemHeight < 0) {
            this.mItemHeight = ThemeUtil.dpToPx(context, 48);
        }
        if (this.mAnimDuration < 0) {
            this.mAnimDuration = context.getResources().getInteger(R.integer.config_mediumAnimTime);
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
        if (i4 >= 0 || i5 >= 0) {
            if (i4 < 0) {
                i4 = this.mAdapter.getMinYear();
            }
            if (i5 < 0) {
                i5 = this.mAdapter.getMaxYear();
            }
            if (i5 < i4) {
                i5 = Integer.MAX_VALUE;
            }
            setYearRange(i4, i5);
        }
        if (this.mAdapter.getYear() < 0 && i6 < 0) {
            i6 = Calendar.getInstance().get(1);
        }
        if (i6 >= 0) {
            setYear(Math.max(i4, Math.min(i5, i6)));
        }
        this.mAdapter.notifyDataSetChanged();
        requestLayout();
    }

    public void setYearRange(int i, int i2) {
        this.mAdapter.setYearRange(i, i2);
    }

    public void goTo(int i) {
        int positionOfYear = this.mAdapter.positionOfYear(i) - this.mPositionShift;
        int i2 = this.mDistanceShift;
        if (positionOfYear < 0) {
            positionOfYear = 0;
            i2 = 0;
        }
        postSetSelectionFromTop(positionOfYear, i2);
    }

    public void postSetSelectionFromTop(final int i, final int i2) {
        post(new Runnable() { // from class: com.rey.material.widget.YearPicker.1
            @Override // java.lang.Runnable
            public void run() {
                YearPicker.this.setSelectionFromTop(i, i2);
                YearPicker.this.requestLayout();
            }
        });
    }

    public void setYear(int i) {
        if (this.mAdapter.getYear() == i) {
            return;
        }
        this.mAdapter.setYear(i);
        goTo(i);
    }

    public int getYear() {
        return this.mAdapter.getYear();
    }

    public void setOnYearChangedListener(OnYearChangedListener onYearChangedListener) {
        this.mOnYearChangedListener = onYearChangedListener;
    }

    private void measureItemHeight() {
        if (this.mItemRealHeight > 0) {
            return;
        }
        this.mPaint.setTextSize(this.mTextSize);
        this.mItemRealHeight = Math.max(Math.round(this.mPaint.measureText("9999", 0, 4)) + (this.mPadding * 2), this.mItemHeight);
    }

    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i2);
        int size = View.MeasureSpec.getSize(i2);
        measureItemHeight();
        if (mode != 1073741824) {
            if (mode == Integer.MIN_VALUE) {
                int min = Math.min(this.mAdapter.getCount(), size / this.mItemRealHeight);
                if (min >= 3) {
                    int i3 = this.mItemRealHeight;
                    if (min % 2 == 0) {
                        min--;
                    }
                    size = i3 * min;
                }
            } else {
                size = this.mItemRealHeight * this.mAdapter.getCount();
            }
            i2 = View.MeasureSpec.makeMeasureSpec(size + getPaddingTop() + getPaddingBottom(), 1073741824);
        }
        super.onMeasure(i, i2);
    }

    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        float f = ((i2 / this.mItemRealHeight) - 1.0f) / 2.0f;
        int floor = (int) Math.floor(f);
        this.mPositionShift = floor;
        if (f > floor) {
            floor++;
        }
        this.mPositionShift = floor;
        this.mDistanceShift = ((int) ((f - floor) * this.mItemRealHeight)) - getPaddingTop();
        goTo(this.mAdapter.getYear());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class YearAdapter extends BaseAdapter implements View.OnClickListener {
        private int mMinYear = 1990;
        private int mMaxYear = 2147483646;
        private int mCurYear = -1;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        public YearAdapter() {
        }

        public int getMinYear() {
            return this.mMinYear;
        }

        public int getMaxYear() {
            return this.mMaxYear;
        }

        public void setYearRange(int i, int i2) {
            if (this.mMinYear == i && this.mMaxYear == i2) {
                return;
            }
            this.mMinYear = i;
            this.mMaxYear = i2;
            notifyDataSetChanged();
        }

        public int positionOfYear(int i) {
            return i - this.mMinYear;
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return (this.mMaxYear - this.mMinYear) + 1;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(this.mMinYear + i);
        }

        public void setYear(int i) {
            int i2 = this.mCurYear;
            if (i2 != i) {
                this.mCurYear = i;
                CircleCheckedTextView circleCheckedTextView = (CircleCheckedTextView) YearPicker.this.getChildAt(positionOfYear(i2) - YearPicker.this.getFirstVisiblePosition());
                if (circleCheckedTextView != null) {
                    circleCheckedTextView.setChecked(false);
                }
                CircleCheckedTextView circleCheckedTextView2 = (CircleCheckedTextView) YearPicker.this.getChildAt(positionOfYear(this.mCurYear) - YearPicker.this.getFirstVisiblePosition());
                if (circleCheckedTextView2 != null) {
                    circleCheckedTextView2.setChecked(true);
                }
                if (YearPicker.this.mOnYearChangedListener != null) {
                    YearPicker.this.mOnYearChangedListener.onYearChanged(i2, this.mCurYear);
                }
            }
        }

        public int getYear() {
            return this.mCurYear;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            setYear(((Integer) view.getTag()).intValue());
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            CircleCheckedTextView circleCheckedTextView = (CircleCheckedTextView) view;
            if (circleCheckedTextView == null) {
                circleCheckedTextView = new CircleCheckedTextView(YearPicker.this.getContext());
                circleCheckedTextView.setGravity(17);
                if (Build.VERSION.SDK_INT >= 17) {
                    circleCheckedTextView.setTextAlignment(4);
                }
                circleCheckedTextView.setMinHeight(YearPicker.this.mItemRealHeight);
                circleCheckedTextView.setMaxHeight(YearPicker.this.mItemRealHeight);
                circleCheckedTextView.setAnimDuration(YearPicker.this.mAnimDuration);
                circleCheckedTextView.setInterpolator(YearPicker.this.mInInterpolator, YearPicker.this.mOutInterpolator);
                circleCheckedTextView.setBackgroundColor(YearPicker.this.mSelectionColor);
                circleCheckedTextView.setTypeface(YearPicker.this.mTypeface);
                circleCheckedTextView.setTextSize(0, YearPicker.this.mTextSize);
                circleCheckedTextView.setTextColor(new ColorStateList(YearPicker.STATES, YearPicker.this.mTextColors));
                circleCheckedTextView.setOnClickListener(this);
            }
            int intValue = ((Integer) getItem(i)).intValue();
            circleCheckedTextView.setTag(Integer.valueOf(intValue));
            circleCheckedTextView.setText(String.format(YearPicker.YEAR_FORMAT, Integer.valueOf(intValue)));
            circleCheckedTextView.setCheckedImmediately(intValue == this.mCurYear);
            return circleCheckedTextView;
        }
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.yearMin = this.mAdapter.getMinYear();
        savedState.yearMax = this.mAdapter.getMaxYear();
        savedState.year = this.mAdapter.getYear();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setYearRange(savedState.yearMin, savedState.yearMax);
        setYear(savedState.year);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.rey.material.widget.YearPicker.SavedState.1
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
        int year;
        int yearMax;
        int yearMin;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.yearMin = parcel.readInt();
            this.yearMax = parcel.readInt();
            this.year = parcel.readInt();
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeValue(Integer.valueOf(this.yearMin));
            parcel.writeValue(Integer.valueOf(this.yearMax));
            parcel.writeValue(Integer.valueOf(this.year));
        }

        public String toString() {
            return "YearPicker.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " yearMin=" + this.yearMin + " yearMax=" + this.yearMax + " year=" + this.year + "}";
        }
    }
}
