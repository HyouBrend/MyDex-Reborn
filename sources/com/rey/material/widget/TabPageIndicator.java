package com.rey.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class TabPageIndicator extends HorizontalScrollView implements ViewPager.OnPageChangeListener, View.OnClickListener, ThemeManager.OnThemeChangedListener {
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLL = 0;
    protected int mCurrentStyle;
    private boolean mIndicatorAtTop;
    private int mIndicatorHeight;
    private int mIndicatorOffset;
    private int mIndicatorWidth;
    private boolean mIsRtl;
    private ViewPager.OnPageChangeListener mListener;
    private int mMode;
    private DataSetObserver mObserver;
    private Paint mPaint;
    private boolean mScrolling;
    private int mSelectedPosition;
    protected int mStyleId;
    private Runnable mTabAnimSelector;
    private TabContainerLayout mTabContainer;
    private int mTabPadding;
    private int mTabRippleStyle;
    private boolean mTabSingleLine;
    private int mTextAppearance;
    private ViewPager mViewPager;

    public TabPageIndicator(Context context) {
        super(context);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mObserver = new DataSetObserver() { // from class: com.rey.material.widget.TabPageIndicator.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                TabPageIndicator.this.notifyDataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                TabPageIndicator.this.notifyDataSetInvalidated();
            }
        };
        init(context, null, 0, 0);
    }

    public TabPageIndicator(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mObserver = new DataSetObserver() { // from class: com.rey.material.widget.TabPageIndicator.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                TabPageIndicator.this.notifyDataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                TabPageIndicator.this.notifyDataSetInvalidated();
            }
        };
        init(context, attributeSet, 0, 0);
    }

    public TabPageIndicator(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mObserver = new DataSetObserver() { // from class: com.rey.material.widget.TabPageIndicator.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                TabPageIndicator.this.notifyDataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                TabPageIndicator.this.notifyDataSetInvalidated();
            }
        };
        init(context, attributeSet, i, 0);
    }

    public TabPageIndicator(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mObserver = new DataSetObserver() { // from class: com.rey.material.widget.TabPageIndicator.1
            @Override // android.database.DataSetObserver
            public void onChanged() {
                TabPageIndicator.this.notifyDataSetChanged();
            }

            @Override // android.database.DataSetObserver
            public void onInvalidated() {
                TabPageIndicator.this.notifyDataSetInvalidated();
            }
        };
        init(context, attributeSet, i, i2);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        setHorizontalScrollBarEnabled(false);
        this.mTabPadding = -1;
        this.mTabSingleLine = true;
        this.mIndicatorHeight = -1;
        this.mIndicatorAtTop = false;
        this.mScrolling = false;
        this.mIsRtl = false;
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(ThemeUtil.colorAccent(context, -1));
        this.mTabContainer = new TabContainerLayout(context);
        applyStyle(context, attributeSet, i, i2);
        if (isInEditMode()) {
            addTemporaryTab();
        }
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
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabPageIndicator, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        int i4 = 0;
        int i5 = 0;
        for (int i6 = 0; i6 < indexCount; i6++) {
            int index = obtainStyledAttributes.getIndex(i6);
            if (index == R.styleable.TabPageIndicator_tpi_tabPadding) {
                this.mTabPadding = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.TabPageIndicator_tpi_tabRipple) {
                i5 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.TabPageIndicator_tpi_indicatorColor) {
                this.mPaint.setColor(obtainStyledAttributes.getColor(index, 0));
            } else if (index == R.styleable.TabPageIndicator_tpi_indicatorHeight) {
                this.mIndicatorHeight = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.TabPageIndicator_tpi_indicatorAtTop) {
                this.mIndicatorAtTop = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == R.styleable.TabPageIndicator_tpi_tabSingleLine) {
                this.mTabSingleLine = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == R.styleable.TabPageIndicator_android_textAppearance) {
                i4 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.TabPageIndicator_tpi_mode) {
                i3 = obtainStyledAttributes.getInteger(index, 0);
            }
        }
        obtainStyledAttributes.recycle();
        if (this.mTabPadding < 0) {
            this.mTabPadding = ThemeUtil.dpToPx(context, 12);
        }
        if (this.mIndicatorHeight < 0) {
            this.mIndicatorHeight = ThemeUtil.dpToPx(context, 2);
        }
        if (i3 >= 0 && (this.mMode != i3 || getChildCount() == 0)) {
            this.mMode = i3;
            removeAllViews();
            int i7 = this.mMode;
            if (i7 == 0) {
                addView(this.mTabContainer, new ViewGroup.LayoutParams(-2, -1));
                setFillViewport(false);
            } else if (i7 == 1) {
                addView(this.mTabContainer, new ViewGroup.LayoutParams(-1, -1));
                setFillViewport(true);
            }
        }
        if (i4 != 0 && this.mTextAppearance != i4) {
            this.mTextAppearance = i4;
            int childCount = this.mTabContainer.getChildCount();
            for (int i8 = 0; i8 < childCount; i8++) {
                ((CheckedTextView) this.mTabContainer.getChildAt(i8)).setTextAppearance(context, this.mTextAppearance);
            }
        }
        if (i5 != 0 && i5 != this.mTabRippleStyle) {
            this.mTabRippleStyle = i5;
            int childCount2 = this.mTabContainer.getChildCount();
            for (int i9 = 0; i9 < childCount2; i9++) {
                ViewUtil.setBackground(this.mTabContainer.getChildAt(i9), new RippleDrawable.Builder(getContext(), this.mTabRippleStyle).build());
            }
        }
        if (this.mViewPager != null) {
            notifyDataSetChanged();
        }
        requestLayout();
    }

    @Override // com.rey.material.app.ThemeManager.OnThemeChangedListener
    public void onThemeChanged(ThemeManager.OnThemeChangedEvent onThemeChangedEvent) {
        int currentStyle = ThemeManager.getInstance().getCurrentStyle(this.mStyleId);
        if (this.mCurrentStyle != currentStyle) {
            this.mCurrentStyle = currentStyle;
            applyStyle(currentStyle);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Runnable runnable = this.mTabAnimSelector;
        if (runnable != null) {
            post(runnable);
        }
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().registerOnThemeChangedListener(this);
            onThemeChanged(null);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Runnable runnable = this.mTabAnimSelector;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        if (this.mStyleId != 0) {
            ThemeManager.getInstance().unregisterOnThemeChangedListener(this);
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

    @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        if (i != 0) {
            i = View.MeasureSpec.makeMeasureSpec((size - getPaddingLeft()) - getPaddingRight(), mode);
        }
        if (mode2 != 0) {
            i2 = View.MeasureSpec.makeMeasureSpec((size2 - getPaddingTop()) - getPaddingBottom(), mode2);
        }
        this.mTabContainer.measure(i, i2);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(this.mTabContainer.getMeasuredWidth() + getPaddingLeft() + getPaddingRight(), size);
        } else if (mode == 0) {
            size = getPaddingRight() + this.mTabContainer.getMeasuredWidth() + getPaddingLeft();
        } else if (mode != 1073741824) {
            size = 0;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(this.mTabContainer.getMeasuredHeight() + getPaddingTop() + getPaddingBottom(), size2);
        } else if (mode2 == 0) {
            size2 = this.mTabContainer.getMeasuredHeight() + getPaddingTop() + getPaddingBottom();
        } else if (mode2 != 1073741824) {
            size2 = 0;
        }
        if (this.mTabContainer.getMeasuredWidth() != (size - getPaddingLeft()) - getPaddingRight() || this.mTabContainer.getMeasuredHeight() != (size2 - getPaddingTop()) - getPaddingBottom()) {
            this.mTabContainer.measure(View.MeasureSpec.makeMeasureSpec((size - getPaddingLeft()) - getPaddingRight(), 1073741824), View.MeasureSpec.makeMeasureSpec((size2 - getPaddingTop()) - getPaddingBottom(), 1073741824));
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    protected void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        CheckedTextView tabView = getTabView(this.mSelectedPosition);
        if (tabView != null) {
            updateIndicator(tabView.getLeft(), tabView.getMeasuredWidth());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public CheckedTextView getTabView(int i) {
        return (CheckedTextView) this.mTabContainer.getChildAt(i);
    }

    private void animateToTab(final int i) {
        if (getTabView(i) == null) {
            return;
        }
        Runnable runnable = this.mTabAnimSelector;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: com.rey.material.widget.TabPageIndicator.2
            @Override // java.lang.Runnable
            public void run() {
                CheckedTextView tabView = TabPageIndicator.this.getTabView(i);
                if (tabView != null) {
                    if (!TabPageIndicator.this.mScrolling) {
                        TabPageIndicator.this.updateIndicator(tabView.getLeft(), tabView.getMeasuredWidth());
                    }
                    TabPageIndicator.this.smoothScrollTo((tabView.getLeft() - ((TabPageIndicator.this.getWidth() - tabView.getWidth()) / 2)) + TabPageIndicator.this.getPaddingLeft(), 0);
                }
                TabPageIndicator.this.mTabAnimSelector = null;
            }
        };
        this.mTabAnimSelector = runnable2;
        post(runnable2);
    }

    public void setOnPageChangeListener(ViewPager.OnPageChangeListener onPageChangeListener) {
        this.mListener = onPageChangeListener;
    }

    public void setViewPager(ViewPager viewPager) {
        ViewPager viewPager2 = this.mViewPager;
        if (viewPager2 == viewPager) {
            return;
        }
        if (viewPager2 != null) {
            viewPager2.removeOnPageChangeListener(this);
            PagerAdapter adapter = this.mViewPager.getAdapter();
            if (adapter != null) {
                adapter.unregisterDataSetObserver(this.mObserver);
            }
        }
        this.mViewPager = viewPager;
        if (viewPager != null) {
            PagerAdapter adapter2 = viewPager.getAdapter();
            if (adapter2 == null) {
                throw new IllegalStateException("ViewPager does not have adapter instance.");
            }
            adapter2.registerDataSetObserver(this.mObserver);
            this.mViewPager.addOnPageChangeListener(this);
            notifyDataSetChanged();
            onPageSelected(this.mViewPager.getCurrentItem());
            return;
        }
        this.mTabContainer.removeAllViews();
    }

    public void setViewPager(ViewPager viewPager, int i) {
        setViewPager(viewPager);
        setCurrentItem(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateIndicator(int i, int i2) {
        this.mIndicatorOffset = i;
        this.mIndicatorWidth = i2;
        invalidate();
    }

    @Override // android.widget.HorizontalScrollView, android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        int paddingLeft = this.mIndicatorOffset + getPaddingLeft();
        float height = this.mIndicatorAtTop ? 0 : getHeight() - this.mIndicatorHeight;
        canvas.drawRect(paddingLeft, height, paddingLeft + this.mIndicatorWidth, this.mIndicatorHeight + r1, this.mPaint);
        if (isInEditMode()) {
            canvas.drawRect(getPaddingLeft(), height, getPaddingLeft() + this.mTabContainer.getChildAt(0).getWidth(), r1 + this.mIndicatorHeight, this.mPaint);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
        if (i == 0) {
            this.mScrolling = false;
            CheckedTextView tabView = getTabView(this.mSelectedPosition);
            if (tabView != null) {
                updateIndicator(tabView.getLeft(), tabView.getMeasuredWidth());
            }
        } else {
            this.mScrolling = true;
        }
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrollStateChanged(i);
        }
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageScrolled(i, f, i2);
        }
        CheckedTextView tabView = getTabView(i);
        CheckedTextView tabView2 = getTabView(i + 1);
        if (tabView == null || tabView2 == null) {
            return;
        }
        int measuredWidth = tabView.getMeasuredWidth();
        int measuredWidth2 = tabView2.getMeasuredWidth();
        float f2 = (measuredWidth + measuredWidth2) / 2.0f;
        float f3 = measuredWidth;
        int i3 = (int) (((measuredWidth2 - measuredWidth) * f) + f3 + 0.5f);
        updateIndicator((int) ((((tabView.getLeft() + (f3 / 2.0f)) + (f2 * f)) - (i3 / 2.0f)) + 0.5f), i3);
    }

    @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        setCurrentItem(i);
        ViewPager.OnPageChangeListener onPageChangeListener = this.mListener;
        if (onPageChangeListener != null) {
            onPageChangeListener.onPageSelected(i);
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        ViewPager.OnPageChangeListener onPageChangeListener;
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue == this.mSelectedPosition && (onPageChangeListener = this.mListener) != null) {
            onPageChangeListener.onPageSelected(intValue);
        }
        this.mViewPager.setCurrentItem(intValue, true);
    }

    public void setCurrentItem(int i) {
        CheckedTextView tabView;
        int i2 = this.mSelectedPosition;
        if (i2 != i && (tabView = getTabView(i2)) != null) {
            tabView.setChecked(false);
        }
        this.mSelectedPosition = i;
        CheckedTextView tabView2 = getTabView(i);
        if (tabView2 != null) {
            tabView2.setChecked(true);
        }
        animateToTab(i);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDataSetChanged() {
        this.mTabContainer.removeAllViews();
        PagerAdapter adapter = this.mViewPager.getAdapter();
        int count = adapter.getCount();
        if (this.mSelectedPosition > count) {
            this.mSelectedPosition = count - 1;
        }
        for (int i = 0; i < count; i++) {
            CharSequence pageTitle = adapter.getPageTitle(i);
            if (pageTitle == null) {
                pageTitle = "NULL";
            }
            CheckedTextView checkedTextView = new CheckedTextView(getContext());
            checkedTextView.setCheckMarkDrawable((Drawable) null);
            checkedTextView.setText(pageTitle);
            checkedTextView.setGravity(17);
            checkedTextView.setTextAppearance(getContext(), this.mTextAppearance);
            if (this.mTabSingleLine) {
                checkedTextView.setSingleLine(true);
            } else {
                checkedTextView.setSingleLine(false);
                checkedTextView.setMaxLines(2);
            }
            checkedTextView.setEllipsize(TextUtils.TruncateAt.END);
            checkedTextView.setOnClickListener(this);
            checkedTextView.setTag(Integer.valueOf(i));
            if (this.mTabRippleStyle > 0) {
                ViewUtil.setBackground(checkedTextView, new RippleDrawable.Builder(getContext(), this.mTabRippleStyle).build());
            }
            int i2 = this.mTabPadding;
            checkedTextView.setPadding(i2, 0, i2, 0);
            this.mTabContainer.addView(checkedTextView, new ViewGroup.LayoutParams(-2, -1));
        }
        setCurrentItem(this.mSelectedPosition);
        requestLayout();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyDataSetInvalidated() {
        PagerAdapter adapter = this.mViewPager.getAdapter();
        int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CheckedTextView tabView = getTabView(i);
            if (tabView != null) {
                CharSequence pageTitle = adapter.getPageTitle(i);
                if (pageTitle == null) {
                    pageTitle = "NULL";
                }
                tabView.setText(pageTitle);
            }
        }
        requestLayout();
    }

    private void addTemporaryTab() {
        int i = 0;
        while (i < 3) {
            String str = i == 0 ? "TAB ONE" : i == 1 ? "TAB TWO" : i == 2 ? "TAB THREE" : null;
            CheckedTextView checkedTextView = new CheckedTextView(getContext());
            checkedTextView.setCheckMarkDrawable((Drawable) null);
            checkedTextView.setText(str);
            checkedTextView.setGravity(17);
            checkedTextView.setTextAppearance(getContext(), this.mTextAppearance);
            checkedTextView.setSingleLine(true);
            checkedTextView.setEllipsize(TextUtils.TruncateAt.END);
            checkedTextView.setTag(Integer.valueOf(i));
            checkedTextView.setChecked(i == 0);
            int i2 = this.mMode;
            if (i2 == 0) {
                int i3 = this.mTabPadding;
                checkedTextView.setPadding(i3, 0, i3, 0);
                this.mTabContainer.addView(checkedTextView, new ViewGroup.LayoutParams(-2, -1));
            } else if (i2 == 1) {
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, -1);
                layoutParams.weight = 1.0f;
                this.mTabContainer.addView(checkedTextView, layoutParams);
            }
            i++;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class TabContainerLayout extends android.widget.FrameLayout {
        public TabContainerLayout(Context context) {
            super(context);
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            int i3;
            int mode = View.MeasureSpec.getMode(i);
            int size = View.MeasureSpec.getSize(i);
            if (TabPageIndicator.this.mMode == 0) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                int i4 = 0;
                i3 = 0;
                for (int i5 = 0; i5 < getChildCount(); i5++) {
                    View childAt = getChildAt(i5);
                    childAt.measure(makeMeasureSpec, i2);
                    i4 += childAt.getMeasuredWidth();
                    i3 = Math.max(i3, childAt.getMeasuredHeight());
                }
                setMeasuredDimension(i4, i3);
            } else if (mode != 1073741824) {
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
                i3 = 0;
                int i6 = 0;
                for (int i7 = 0; i7 < getChildCount(); i7++) {
                    View childAt2 = getChildAt(i7);
                    childAt2.measure(makeMeasureSpec2, i2);
                    i6 += childAt2.getMeasuredWidth();
                    i3 = Math.max(i3, childAt2.getMeasuredHeight());
                }
                if (mode == 0 || i6 < size) {
                    setMeasuredDimension(size, i3);
                } else {
                    int childCount = getChildCount();
                    int i8 = childCount == 0 ? 0 : size / childCount;
                    for (int i9 = 0; i9 < childCount; i9++) {
                        View childAt3 = getChildAt(i9);
                        int i10 = childCount - 1;
                        if (i9 != i10) {
                            childAt3.measure(View.MeasureSpec.makeMeasureSpec(i8, 1073741824), i2);
                        } else {
                            childAt3.measure(View.MeasureSpec.makeMeasureSpec(size - (i10 * i8), 1073741824), i2);
                        }
                    }
                    setMeasuredDimension(size, i3);
                }
            } else {
                int childCount2 = getChildCount();
                int i11 = childCount2 == 0 ? 0 : size / childCount2;
                i3 = 0;
                for (int i12 = 0; i12 < childCount2; i12++) {
                    View childAt4 = getChildAt(i12);
                    int i13 = childCount2 - 1;
                    if (i12 != i13) {
                        childAt4.measure(View.MeasureSpec.makeMeasureSpec(i11, 1073741824), i2);
                    } else {
                        childAt4.measure(View.MeasureSpec.makeMeasureSpec(size - (i13 * i11), 1073741824), i2);
                    }
                    i3 = Math.max(i3, childAt4.getMeasuredHeight());
                }
                setMeasuredDimension(size, i3);
            }
            int makeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(i3, 1073741824);
            for (int i14 = 0; i14 < getChildCount(); i14++) {
                View childAt5 = getChildAt(i14);
                if (childAt5.getMeasuredHeight() != i3) {
                    childAt5.measure(View.MeasureSpec.makeMeasureSpec(childAt5.getMeasuredWidth(), 1073741824), makeMeasureSpec3);
                }
            }
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            if (TabPageIndicator.this.mIsRtl) {
                int childCount = getChildCount();
                for (int i7 = 0; i7 < childCount; i7++) {
                    View childAt = getChildAt(i7);
                    childAt.layout(i5 - childAt.getMeasuredWidth(), 0, i5, i6);
                    i5 -= childAt.getMeasuredWidth();
                }
                return;
            }
            int childCount2 = getChildCount();
            int i8 = 0;
            for (int i9 = 0; i9 < childCount2; i9++) {
                View childAt2 = getChildAt(i9);
                childAt2.layout(i8, 0, childAt2.getMeasuredWidth() + i8, i6);
                i8 += childAt2.getMeasuredWidth();
            }
        }
    }
}
