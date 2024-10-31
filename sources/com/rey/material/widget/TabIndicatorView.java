package com.rey.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Checkable;
import android.widget.ImageView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class TabIndicatorView extends RecyclerView implements ThemeManager.OnThemeChangedListener {
    public static final int MODE_FIXED = 1;
    public static final int MODE_SCROLL = 0;
    public static final int SCROLL_STATE_DRAGGING = 1;
    public static final int SCROLL_STATE_IDLE = 0;
    public static final int SCROLL_STATE_SETTLING = 2;
    private Adapter mAdapter;
    private boolean mCenterCurrentTab;
    protected int mCurrentStyle;
    private TabIndicatorFactory mFactory;
    private boolean mIndicatorAtTop;
    private int mIndicatorHeight;
    private int mIndicatorOffset;
    private int mIndicatorWidth;
    private boolean mIsRtl;
    private RecyclerView.LayoutManager mLayoutManager;
    private int mMode;
    private Paint mPaint;
    private boolean mScrolling;
    private boolean mScrollingToCenter;
    private int mSelectedPosition;
    protected int mStyleId;
    private Runnable mTabAnimSelector;
    private int mTabPadding;
    private int mTabRippleStyle;
    private boolean mTabSingleLine;
    private int mTextAppearance;

    public TabIndicatorView(Context context) {
        super(context);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mScrollingToCenter = false;
        init(context, null, 0, 0);
    }

    public TabIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mScrollingToCenter = false;
        init(context, attributeSet, 0, 0);
    }

    public TabIndicatorView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mCurrentStyle = Integer.MIN_VALUE;
        this.mScrollingToCenter = false;
        init(context, attributeSet, i, 0);
    }

    protected void init(Context context, AttributeSet attributeSet, int i, int i2) {
        setHorizontalScrollBarEnabled(false);
        this.mTabPadding = -1;
        this.mTabSingleLine = true;
        this.mCenterCurrentTab = false;
        this.mIndicatorHeight = -1;
        this.mIndicatorAtTop = false;
        this.mScrolling = false;
        this.mIsRtl = false;
        Paint paint = new Paint(1);
        this.mPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        this.mPaint.setColor(ThemeUtil.colorAccent(context, -1));
        Adapter adapter = new Adapter();
        this.mAdapter = adapter;
        setAdapter(adapter);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(context, 0, this.mIsRtl);
        this.mLayoutManager = linearLayoutManager;
        setLayoutManager(linearLayoutManager);
        setItemAnimator(new DefaultItemAnimator());
        addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.rey.material.widget.TabIndicatorView.1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView, int i3) {
                if (i3 == 0) {
                    TabIndicatorView tabIndicatorView = TabIndicatorView.this;
                    tabIndicatorView.updateIndicator(tabIndicatorView.mLayoutManager.findViewByPosition(TabIndicatorView.this.mSelectedPosition));
                }
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView, int i3, int i4) {
                TabIndicatorView tabIndicatorView = TabIndicatorView.this;
                tabIndicatorView.updateIndicator(tabIndicatorView.mLayoutManager.findViewByPosition(TabIndicatorView.this.mSelectedPosition));
            }
        });
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
        boolean z;
        boolean z2;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.TabPageIndicator, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        int i4 = -1;
        int i5 = 0;
        boolean z3 = false;
        boolean z4 = false;
        int i6 = 0;
        int i7 = 0;
        while (true) {
            z = true;
            if (i5 >= indexCount) {
                break;
            }
            int index = obtainStyledAttributes.getIndex(i5);
            if (index == R.styleable.TabPageIndicator_tpi_tabPadding) {
                i3 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.TabPageIndicator_tpi_tabRipple) {
                i7 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.TabPageIndicator_tpi_indicatorColor) {
                this.mPaint.setColor(obtainStyledAttributes.getColor(index, 0));
            } else if (index == R.styleable.TabPageIndicator_tpi_indicatorHeight) {
                this.mIndicatorHeight = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.TabPageIndicator_tpi_indicatorAtTop) {
                this.mIndicatorAtTop = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == R.styleable.TabPageIndicator_tpi_tabSingleLine) {
                z3 = obtainStyledAttributes.getBoolean(index, true);
                z4 = true;
            } else if (index == R.styleable.TabPageIndicator_tpi_centerCurrentTab) {
                this.mCenterCurrentTab = obtainStyledAttributes.getBoolean(index, true);
            } else if (index == R.styleable.TabPageIndicator_android_textAppearance) {
                i6 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.TabPageIndicator_tpi_mode) {
                i4 = obtainStyledAttributes.getInteger(index, 0);
            }
            i5++;
        }
        obtainStyledAttributes.recycle();
        if (this.mIndicatorHeight < 0) {
            this.mIndicatorHeight = ThemeUtil.dpToPx(context, 2);
        }
        if (i3 < 0 || this.mTabPadding == i3) {
            z2 = false;
        } else {
            this.mTabPadding = i3;
            z2 = true;
        }
        if (z4 && this.mTabSingleLine != z3) {
            this.mTabSingleLine = z3;
            z2 = true;
        }
        if (i4 >= 0 && this.mMode != i4) {
            this.mMode = i4;
            this.mAdapter.setFixedWidth(0, 0);
            z2 = true;
        }
        if (i6 != 0 && this.mTextAppearance != i6) {
            this.mTextAppearance = i6;
            z2 = true;
        }
        if (i7 == 0 || i7 == this.mTabRippleStyle) {
            z = z2;
        } else {
            this.mTabRippleStyle = i7;
        }
        if (z) {
            Adapter adapter = this.mAdapter;
            adapter.notifyItemRangeChanged(0, adapter.getItemCount());
        }
        invalidate();
    }

    public void setTabIndicatorFactory(TabIndicatorFactory tabIndicatorFactory) {
        this.mFactory = tabIndicatorFactory;
        this.mAdapter.setFactory(tabIndicatorFactory);
    }

    private void animateToTab(final int i) {
        if (i < 0 || i >= this.mAdapter.getItemCount()) {
            return;
        }
        Runnable runnable = this.mTabAnimSelector;
        if (runnable != null) {
            removeCallbacks(runnable);
        }
        Runnable runnable2 = new Runnable() { // from class: com.rey.material.widget.TabIndicatorView.2
            @Override // java.lang.Runnable
            public void run() {
                View findViewByPosition = TabIndicatorView.this.mLayoutManager.findViewByPosition(i);
                if (!TabIndicatorView.this.mScrolling) {
                    TabIndicatorView.this.updateIndicator(findViewByPosition);
                }
                TabIndicatorView tabIndicatorView = TabIndicatorView.this;
                tabIndicatorView.smoothScrollToPosition(tabIndicatorView.mSelectedPosition);
                TabIndicatorView.this.mTabAnimSelector = null;
            }
        };
        this.mTabAnimSelector = runnable2;
        post(runnable2);
    }

    private void updateIndicator(int i, int i2) {
        this.mIndicatorOffset = i;
        this.mIndicatorWidth = i2;
        invalidate();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public void updateIndicator(View view) {
        if (view != 0) {
            updateIndicator(view.getLeft(), view.getMeasuredWidth());
            ((Checkable) view).setChecked(true);
        } else {
            updateIndicator(getWidth(), 0);
        }
    }

    public void setCurrentTab(int i) {
        KeyEvent.Callback findViewByPosition;
        int i2 = this.mSelectedPosition;
        if (i2 != i && (findViewByPosition = this.mLayoutManager.findViewByPosition(i2)) != null) {
            ((Checkable) findViewByPosition).setChecked(false);
        }
        this.mSelectedPosition = i;
        KeyEvent.Callback findViewByPosition2 = this.mLayoutManager.findViewByPosition(i);
        if (findViewByPosition2 != null) {
            ((Checkable) findViewByPosition2).setChecked(true);
        }
        animateToTab(i);
    }

    @Override // com.rey.material.app.ThemeManager.OnThemeChangedListener
    public void onThemeChanged(ThemeManager.OnThemeChangedEvent onThemeChangedEvent) {
        int currentStyle = ThemeManager.getInstance().getCurrentStyle(this.mStyleId);
        if (this.mCurrentStyle != currentStyle) {
            this.mCurrentStyle = currentStyle;
            applyStyle(currentStyle);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
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

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
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
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, this.mIsRtl);
            this.mLayoutManager = linearLayoutManager;
            setLayoutManager(linearLayoutManager);
            requestLayout();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.mMode == 1) {
            int measuredWidth = (getMeasuredWidth() - getPaddingLeft()) - getPaddingRight();
            int itemCount = this.mAdapter.getItemCount();
            if (itemCount > 0) {
                int i3 = measuredWidth / itemCount;
                this.mAdapter.setFixedWidth(i3, measuredWidth - ((itemCount - 1) * i3));
                return;
            }
            this.mAdapter.setFixedWidth(measuredWidth, measuredWidth);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onSizeChanged(int i, int i2, int i3, int i4) {
        super.onSizeChanged(i, i2, i3, i4);
        updateIndicator(this.mLayoutManager.findViewByPosition(this.mSelectedPosition));
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        canvas.drawRect(this.mIndicatorOffset, this.mIndicatorAtTop ? 0 : getHeight() - this.mIndicatorHeight, r0 + this.mIndicatorWidth, r1 + this.mIndicatorHeight, this.mPaint);
    }

    protected void onTabScrollStateChanged(int i) {
        View findViewByPosition;
        int left;
        if (this.mCenterCurrentTab) {
            if (i == 0 && !this.mScrollingToCenter && (findViewByPosition = this.mLayoutManager.findViewByPosition(this.mSelectedPosition)) != null && (left = ((findViewByPosition.getLeft() + findViewByPosition.getRight()) / 2) - ((((getLeft() + getPaddingLeft()) + getRight()) - getPaddingRight()) / 2)) != 0) {
                smoothScrollBy(left, 0);
                this.mScrollingToCenter = true;
            }
            if (i == 1 || i == 2) {
                this.mScrollingToCenter = false;
            }
        }
        if (i == 0) {
            this.mScrolling = false;
            updateIndicator(this.mLayoutManager.findViewByPosition(this.mSelectedPosition));
        } else {
            this.mScrolling = true;
        }
    }

    protected void onTabScrolled(int i, float f) {
        View findViewByPosition = this.mLayoutManager.findViewByPosition(i);
        View findViewByPosition2 = this.mLayoutManager.findViewByPosition(i + 1);
        if (findViewByPosition == null || findViewByPosition2 == null) {
            return;
        }
        int measuredWidth = findViewByPosition.getMeasuredWidth();
        int measuredWidth2 = findViewByPosition2.getMeasuredWidth();
        float f2 = (measuredWidth + measuredWidth2) / 2.0f;
        float f3 = measuredWidth;
        int i2 = (int) (((measuredWidth2 - measuredWidth) * f) + f3 + 0.5f);
        updateIndicator((int) ((((findViewByPosition.getLeft() + (f3 / 2.0f)) + (f2 * f)) - (i2 / 2.0f)) + 0.5f), i2);
    }

    protected void onTabSelected(int i) {
        setCurrentTab(i);
    }

    /* loaded from: classes2.dex */
    public static abstract class TabIndicatorFactory {
        private TabIndicatorView mView;

        public abstract int getCurrentTabIndicator();

        public abstract Drawable getIcon(int i);

        public abstract int getTabIndicatorCount();

        public abstract CharSequence getText(int i);

        public abstract boolean isIconTabIndicator(int i);

        public abstract void onTabIndicatorSelected(int i);

        protected void setTabIndicatorView(TabIndicatorView tabIndicatorView) {
            this.mView = tabIndicatorView;
        }

        public final void notifyTabScrollStateChanged(int i) {
            this.mView.onTabScrollStateChanged(i);
        }

        public final void notifyTabScrolled(int i, float f) {
            this.mView.onTabScrolled(i, f);
        }

        public final void notifyTabSelected(int i) {
            this.mView.onTabSelected(i);
        }

        public final void notifyDataSetChanged() {
            this.mView.getAdapter().notifyDataSetChanged();
        }

        public final void notifyTabChanged(int i) {
            this.mView.getAdapter().notifyItemRangeChanged(i, 1);
        }

        public final void notifyTabRangeChanged(int i, int i2) {
            this.mView.getAdapter().notifyItemRangeChanged(i, i2);
        }

        public final void notifyTabInserted(int i) {
            this.mView.getAdapter().notifyItemRangeInserted(i, 1);
        }

        public final void notifyTabMoved(int i, int i2) {
            this.mView.getAdapter().notifyItemMoved(i, i2);
        }

        public final void notifyTabRangeInserted(int i, int i2) {
            this.mView.getAdapter().notifyItemRangeInserted(i, i2);
        }

        public final void notifyTabRemoved(int i) {
            this.mView.getAdapter().notifyItemRangeRemoved(i, 1);
        }

        public final void notifyTabRangeRemoved(int i, int i2) {
            this.mView.getAdapter().notifyItemRangeRemoved(i, i2);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class Adapter extends RecyclerView.Adapter<ViewHolder> implements View.OnClickListener {
        static final int TYPE_ICON = 1;
        static final int TYPE_TEXT = 0;
        TabIndicatorFactory mFactory;
        int mFixedWidth;
        int mLastFixedWidth;

        Adapter() {
        }

        public void setFactory(TabIndicatorFactory tabIndicatorFactory) {
            TabIndicatorFactory tabIndicatorFactory2 = this.mFactory;
            if (tabIndicatorFactory2 != null) {
                tabIndicatorFactory2.setTabIndicatorView(null);
            }
            int itemCount = getItemCount();
            if (itemCount > 0) {
                notifyItemRangeRemoved(0, itemCount);
            }
            this.mFactory = tabIndicatorFactory;
            if (tabIndicatorFactory != null) {
                tabIndicatorFactory.setTabIndicatorView(TabIndicatorView.this);
            }
            int itemCount2 = getItemCount();
            if (itemCount2 > 0) {
                notifyItemRangeInserted(0, itemCount2);
            }
            TabIndicatorFactory tabIndicatorFactory3 = this.mFactory;
            if (tabIndicatorFactory3 != null) {
                TabIndicatorView.this.onTabSelected(tabIndicatorFactory3.getCurrentTabIndicator());
            }
        }

        public void setFixedWidth(int i, int i2) {
            if (this.mFixedWidth == i && this.mLastFixedWidth == i2) {
                return;
            }
            this.mFixedWidth = i;
            this.mLastFixedWidth = i2;
            int itemCount = getItemCount();
            if (itemCount > 0) {
                notifyItemRangeChanged(0, itemCount);
            }
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View checkedTextView;
            if (i == 0) {
                checkedTextView = new CheckedTextView(viewGroup.getContext());
            } else {
                checkedTextView = i != 1 ? null : new ImageButton(viewGroup.getContext());
            }
            ViewHolder viewHolder = new ViewHolder(checkedTextView);
            checkedTextView.setTag(viewHolder);
            checkedTextView.setLayoutParams(new ViewGroup.LayoutParams(-2, -1));
            checkedTextView.setOnClickListener(this);
            if (i == 0) {
                viewHolder.textView.setCheckMarkDrawable((Drawable) null);
                if (Build.VERSION.SDK_INT >= 17) {
                    viewHolder.textView.setTextAlignment(1);
                }
                viewHolder.textView.setGravity(17);
                viewHolder.textView.setEllipsize(TextUtils.TruncateAt.END);
                viewHolder.textView.setSingleLine(true);
            } else if (i == 1) {
                viewHolder.iconView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
            return viewHolder;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(ViewHolder viewHolder, int i) {
            int itemViewType = getItemViewType(i);
            ViewGroup.LayoutParams layoutParams = viewHolder.itemView.getLayoutParams();
            if (this.mFixedWidth > 0) {
                layoutParams.width = i == getItemCount() - 1 ? this.mLastFixedWidth : this.mFixedWidth;
            } else {
                layoutParams.width = -2;
            }
            viewHolder.itemView.setLayoutParams(layoutParams);
            if (viewHolder.padding != TabIndicatorView.this.mTabPadding) {
                viewHolder.padding = TabIndicatorView.this.mTabPadding;
                viewHolder.itemView.setPadding(TabIndicatorView.this.mTabPadding, 0, TabIndicatorView.this.mTabPadding, 0);
            }
            if (viewHolder.rippleStyle != TabIndicatorView.this.mTabRippleStyle) {
                viewHolder.rippleStyle = TabIndicatorView.this.mTabRippleStyle;
                if (TabIndicatorView.this.mTabRippleStyle > 0) {
                    ViewUtil.setBackground(viewHolder.itemView, new RippleDrawable.Builder(TabIndicatorView.this.getContext(), TabIndicatorView.this.mTabRippleStyle).build());
                }
            }
            if (itemViewType != 0) {
                if (itemViewType != 1) {
                    return;
                }
                viewHolder.iconView.setImageDrawable(this.mFactory.getIcon(i));
                viewHolder.iconView.setChecked(i == TabIndicatorView.this.mSelectedPosition);
                return;
            }
            if (viewHolder.textAppearance != TabIndicatorView.this.mTextAppearance) {
                viewHolder.textAppearance = TabIndicatorView.this.mTextAppearance;
                viewHolder.textView.setTextAppearance(TabIndicatorView.this.getContext(), TabIndicatorView.this.mTextAppearance);
            }
            if (viewHolder.singleLine != TabIndicatorView.this.mTabSingleLine) {
                viewHolder.singleLine = TabIndicatorView.this.mTabSingleLine;
                if (TabIndicatorView.this.mTabSingleLine) {
                    viewHolder.textView.setSingleLine(true);
                } else {
                    viewHolder.textView.setSingleLine(false);
                    viewHolder.textView.setMaxLines(2);
                }
            }
            viewHolder.textView.setText(this.mFactory.getText(i));
            viewHolder.textView.setChecked(i == TabIndicatorView.this.mSelectedPosition);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int i) {
            return this.mFactory.isIconTabIndicator(i) ? 1 : 0;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            TabIndicatorFactory tabIndicatorFactory = this.mFactory;
            if (tabIndicatorFactory == null) {
                return 0;
            }
            return tabIndicatorFactory.getTabIndicatorCount();
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            this.mFactory.onTabIndicatorSelected(((ViewHolder) view.getTag()).getAdapterPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckedImageView iconView;
        int padding;
        int rippleStyle;
        boolean singleLine;
        int textAppearance;
        CheckedTextView textView;

        public ViewHolder(View view) {
            super(view);
            this.rippleStyle = 0;
            this.singleLine = true;
            this.textAppearance = 0;
            this.padding = 0;
            if (view instanceof CheckedImageView) {
                this.iconView = (CheckedImageView) view;
            } else if (view instanceof CheckedTextView) {
                this.textView = (CheckedTextView) view;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ViewPagerIndicatorFactory extends TabIndicatorFactory implements ViewPager.OnPageChangeListener {
        ViewPager mViewPager;

        @Override // com.rey.material.widget.TabIndicatorView.TabIndicatorFactory
        public Drawable getIcon(int i) {
            return null;
        }

        @Override // com.rey.material.widget.TabIndicatorView.TabIndicatorFactory
        public boolean isIconTabIndicator(int i) {
            return false;
        }

        public ViewPagerIndicatorFactory(ViewPager viewPager) {
            this.mViewPager = viewPager;
            viewPager.addOnPageChangeListener(this);
        }

        @Override // com.rey.material.widget.TabIndicatorView.TabIndicatorFactory
        public int getTabIndicatorCount() {
            return this.mViewPager.getAdapter().getCount();
        }

        @Override // com.rey.material.widget.TabIndicatorView.TabIndicatorFactory
        public CharSequence getText(int i) {
            return this.mViewPager.getAdapter().getPageTitle(i);
        }

        @Override // com.rey.material.widget.TabIndicatorView.TabIndicatorFactory
        public void onTabIndicatorSelected(int i) {
            this.mViewPager.setCurrentItem(i, true);
        }

        @Override // com.rey.material.widget.TabIndicatorView.TabIndicatorFactory
        public int getCurrentTabIndicator() {
            return this.mViewPager.getCurrentItem();
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int i, float f, int i2) {
            notifyTabScrolled(i, f);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int i) {
            notifyTabSelected(i);
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int i) {
            if (i == 0) {
                notifyTabScrollStateChanged(0);
            } else if (i == 1) {
                notifyTabScrollStateChanged(1);
            } else {
                if (i != 2) {
                    return;
                }
                notifyTabScrollStateChanged(2);
            }
        }
    }
}
