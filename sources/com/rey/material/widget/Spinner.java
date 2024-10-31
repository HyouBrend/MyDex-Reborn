package com.rey.material.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.SpinnerAdapter;
import androidx.core.view.GravityCompat;
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.ArrowDrawable;
import com.rey.material.drawable.DividerDrawable;

/* loaded from: classes2.dex */
public class Spinner extends FrameLayout implements ThemeManager.OnThemeChangedListener {
    private static final int INVALID_POSITION = -1;
    private static final int MAX_ITEMS_MEASURED = 15;
    private SpinnerAdapter mAdapter;
    private boolean mArrowAnimSwitchMode;
    private ArrowDrawable mArrowDrawable;
    private int mArrowPadding;
    private int mArrowSize;
    private SpinnerDataSetObserver mDataSetObserver;
    private boolean mDisableChildrenWhenDisabled;
    private DividerDrawable mDividerDrawable;
    private int mDividerHeight;
    private int mDividerPadding;
    private int mDropDownWidth;
    private int mGravity;
    private boolean mIsRtl;
    private boolean mLabelEnable;
    private TextView mLabelView;
    private int mMinHeight;
    private int mMinWidth;
    private OnItemClickListener mOnItemClickListener;
    private OnItemSelectedListener mOnItemSelectedListener;
    private DropdownPopup mPopup;
    private RecycleBin mRecycler;
    private int mSelectedPosition;
    private DropDownAdapter mTempAdapter;
    private Rect mTempRect;

    /* loaded from: classes2.dex */
    public interface OnItemClickListener {
        boolean onItemClick(Spinner spinner, View view, int i, long j);
    }

    /* loaded from: classes2.dex */
    public interface OnItemSelectedListener {
        void onItemSelected(Spinner spinner, View view, int i, long j);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        return true;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Spinner(Context context) {
        super(context, null, R.attr.listPopupWindowStyle);
        this.mRecycler = new RecycleBin();
        this.mTempRect = new Rect();
        this.mDataSetObserver = new SpinnerDataSetObserver();
    }

    public Spinner(Context context, AttributeSet attributeSet) {
        super(context, attributeSet, R.attr.listPopupWindowStyle);
        this.mRecycler = new RecycleBin();
        this.mTempRect = new Rect();
        this.mDataSetObserver = new SpinnerDataSetObserver();
    }

    public Spinner(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mRecycler = new RecycleBin();
        this.mTempRect = new Rect();
        this.mDataSetObserver = new SpinnerDataSetObserver();
    }

    public Spinner(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mRecycler = new RecycleBin();
        this.mTempRect = new Rect();
        this.mDataSetObserver = new SpinnerDataSetObserver();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.FrameLayout
    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mLabelEnable = false;
        this.mDropDownWidth = -2;
        this.mArrowAnimSwitchMode = false;
        this.mGravity = 17;
        this.mDisableChildrenWhenDisabled = false;
        this.mSelectedPosition = -1;
        this.mIsRtl = false;
        setWillNotDraw(false);
        DropdownPopup dropdownPopup = new DropdownPopup(context, attributeSet, i, i2);
        this.mPopup = dropdownPopup;
        dropdownPopup.setModal(true);
        if (isInEditMode()) {
            applyStyle(R.style.Material_Widget_Spinner);
        }
        setOnClickListener(new View.OnClickListener() { // from class: com.rey.material.widget.Spinner.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                Spinner.this.showPopup();
            }
        });
        super.init(context, attributeSet, i, i2);
    }

    private android.widget.TextView getLabelView() {
        if (this.mLabelView == null) {
            this.mLabelView = new TextView(getContext());
            if (Build.VERSION.SDK_INT >= 17) {
                this.mLabelView.setTextDirection(this.mIsRtl ? 4 : 3);
            }
            this.mLabelView.setSingleLine(true);
            this.mLabelView.setDuplicateParentStateEnabled(true);
        }
        return this.mLabelView;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:160:0x02b8  */
    /* JADX WARN: Removed duplicated region for block: B:163:0x02c3  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x02cc  */
    @Override // com.rey.material.widget.FrameLayout
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void applyStyle(android.content.Context r23, android.util.AttributeSet r24, int r25, int r26) {
        /*
            Method dump skipped, instructions count: 742
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rey.material.widget.Spinner.applyStyle(android.content.Context, android.util.AttributeSet, int, int):void");
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        boolean z = i == 1;
        if (this.mIsRtl != z) {
            this.mIsRtl = z;
            if (this.mLabelView != null && Build.VERSION.SDK_INT >= 17) {
                this.mLabelView.setTextDirection(this.mIsRtl ? 4 : 3);
            }
            requestLayout();
        }
    }

    public View getSelectedView() {
        View childAt = getChildAt(getChildCount() - 1);
        if (childAt == this.mLabelView) {
            return null;
        }
        return childAt;
    }

    public void setSelection(int i) {
        if (this.mAdapter != null) {
            i = Math.max(0, Math.min(i, r0.getCount() - 1));
        }
        int i2 = i;
        if (this.mSelectedPosition != i2) {
            this.mSelectedPosition = i2;
            OnItemSelectedListener onItemSelectedListener = this.mOnItemSelectedListener;
            if (onItemSelectedListener != null) {
                View selectedView = getSelectedView();
                SpinnerAdapter spinnerAdapter = this.mAdapter;
                onItemSelectedListener.onItemSelected(this, selectedView, i2, spinnerAdapter == null ? -1L : spinnerAdapter.getItemId(i2));
            }
            onDataInvalidated();
        }
    }

    public int getSelectedItemPosition() {
        return this.mSelectedPosition;
    }

    public Object getSelectedItem() {
        SpinnerAdapter spinnerAdapter = this.mAdapter;
        if (spinnerAdapter == null) {
            return null;
        }
        return spinnerAdapter.getItem(this.mSelectedPosition);
    }

    public SpinnerAdapter getAdapter() {
        return this.mAdapter;
    }

    public void setAdapter(SpinnerAdapter spinnerAdapter) {
        SpinnerAdapter spinnerAdapter2 = this.mAdapter;
        if (spinnerAdapter2 != null) {
            spinnerAdapter2.unregisterDataSetObserver(this.mDataSetObserver);
        }
        this.mRecycler.clear();
        this.mAdapter = spinnerAdapter;
        spinnerAdapter.registerDataSetObserver(this.mDataSetObserver);
        onDataChanged();
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup != null) {
            dropdownPopup.setAdapter(new DropDownAdapter(spinnerAdapter));
        } else {
            this.mTempAdapter = new DropDownAdapter(spinnerAdapter);
        }
    }

    public void setPopupBackgroundDrawable(Drawable drawable) {
        this.mPopup.setBackgroundDrawable(drawable);
    }

    public void setPopupBackgroundResource(int i) {
        setPopupBackgroundDrawable(getContext().getDrawable(i));
    }

    public Drawable getPopupBackground() {
        return this.mPopup.getBackground();
    }

    public void setDropDownVerticalOffset(int i) {
        this.mPopup.setVerticalOffset(i);
    }

    public int getDropDownVerticalOffset() {
        return this.mPopup.getVerticalOffset();
    }

    public void setDropDownHorizontalOffset(int i) {
        this.mPopup.setHorizontalOffset(i);
    }

    public int getDropDownHorizontalOffset() {
        return this.mPopup.getHorizontalOffset();
    }

    public void setDropDownWidth(int i) {
        this.mDropDownWidth = i;
    }

    public int getDropDownWidth() {
        return this.mDropDownWidth;
    }

    @Override // android.view.View
    public void setEnabled(boolean z) {
        super.setEnabled(z);
        if (this.mDisableChildrenWhenDisabled) {
            int childCount = getChildCount();
            for (int i = 0; i < childCount; i++) {
                getChildAt(i).setEnabled(z);
            }
        }
    }

    @Override // android.view.View
    public void setMinimumHeight(int i) {
        this.mMinHeight = i;
        super.setMinimumHeight(i);
    }

    @Override // android.view.View
    public void setMinimumWidth(int i) {
        this.mMinWidth = i;
        super.setMinimumWidth(i);
    }

    public void setGravity(int i) {
        if (this.mGravity != i) {
            if ((i & 7) == 0) {
                i |= GravityCompat.START;
            }
            this.mGravity = i;
            requestLayout();
        }
    }

    @Override // android.view.View
    public int getBaseline() {
        int baseline;
        View selectedView = getSelectedView();
        if (selectedView == null || (baseline = selectedView.getBaseline()) < 0) {
            return -1;
        }
        return selectedView.getTop() + baseline;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.FrameLayout, android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        DropdownPopup dropdownPopup = this.mPopup;
        if (dropdownPopup == null || !dropdownPopup.isShowing()) {
            return;
        }
        this.mPopup.dismiss();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener) {
        this.mOnItemSelectedListener = onItemSelectedListener;
    }

    @Override // android.view.View
    protected boolean verifyDrawable(Drawable drawable) {
        return super.verifyDrawable(drawable) || this.mArrowDrawable == drawable || this.mDividerDrawable == drawable;
    }

    private int getArrowDrawableWidth() {
        if (this.mArrowDrawable != null) {
            return this.mArrowSize + (this.mArrowPadding * 2);
        }
        return 0;
    }

    private int getDividerDrawableHeight() {
        int i = this.mDividerHeight;
        if (i > 0) {
            return i + this.mDividerPadding;
        }
        return 0;
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int i3;
        int i4;
        int i5;
        int measuredWidth;
        int measuredHeight;
        int makeMeasureSpec;
        int makeMeasureSpec2;
        int mode = View.MeasureSpec.getMode(i);
        int size = View.MeasureSpec.getSize(i);
        int mode2 = View.MeasureSpec.getMode(i2);
        int size2 = View.MeasureSpec.getSize(i2);
        int paddingLeft = getPaddingLeft() + getPaddingRight() + getArrowDrawableWidth();
        int paddingTop = getPaddingTop() + getPaddingBottom() + getDividerDrawableHeight();
        TextView textView = this.mLabelView;
        int i6 = 0;
        if (textView == null || textView.getLayoutParams() == null) {
            i3 = 0;
            i4 = 0;
        } else {
            this.mLabelView.measure(View.MeasureSpec.makeMeasureSpec(size - paddingLeft, mode), View.MeasureSpec.makeMeasureSpec(0, 0));
            i3 = this.mLabelView.getMeasuredWidth();
            i4 = this.mLabelView.getMeasuredHeight();
        }
        View selectedView = getSelectedView();
        if (selectedView != null) {
            ViewGroup.LayoutParams layoutParams = selectedView.getLayoutParams();
            int i7 = layoutParams.width;
            if (i7 == -2) {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
            } else if (i7 == -1) {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(size - paddingLeft, mode);
            } else {
                makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
            }
            int i8 = layoutParams.height;
            if (i8 == -2) {
                makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
            } else if (i8 == -1) {
                makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec((size2 - paddingTop) - i4, mode2);
            } else {
                makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
            }
            selectedView.measure(makeMeasureSpec, makeMeasureSpec2);
            i6 = selectedView.getMeasuredWidth();
            i5 = selectedView.getMeasuredHeight();
        } else {
            i5 = 0;
        }
        int max = Math.max(this.mMinWidth, Math.max(i3, i6) + paddingLeft);
        int max2 = Math.max(this.mMinHeight, i5 + i4 + paddingTop);
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, max);
        } else if (mode != 1073741824) {
            size = max;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, max2);
        } else if (mode2 != 1073741824) {
            size2 = max2;
        }
        setMeasuredDimension(size, size2);
        if (selectedView != null) {
            ViewGroup.LayoutParams layoutParams2 = selectedView.getLayoutParams();
            int i9 = layoutParams2.width;
            if (i9 == -2) {
                measuredWidth = selectedView.getMeasuredWidth();
            } else {
                measuredWidth = i9 != -1 ? layoutParams2.width : size - paddingLeft;
            }
            int i10 = layoutParams2.height;
            if (i10 == -2) {
                measuredHeight = selectedView.getMeasuredHeight();
            } else {
                measuredHeight = i10 != -1 ? layoutParams2.height : (size2 - i4) - paddingTop;
            }
            if (selectedView.getMeasuredWidth() == measuredWidth && selectedView.getMeasuredHeight() == measuredHeight) {
                return;
            }
            selectedView.measure(View.MeasureSpec.makeMeasureSpec(measuredWidth, 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d7, code lost:
    
        if (r4.mIsRtl != false) goto L36;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x00d9, code lost:
    
        r0 = 5;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00db, code lost:
    
        r0 = 3;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00e4, code lost:
    
        if (r4.mIsRtl != false) goto L37;
     */
    /* JADX WARN: Removed duplicated region for block: B:43:0x010f  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0127  */
    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
        /*
            Method dump skipped, instructions count: 318
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.rey.material.widget.Spinner.onLayout(boolean, int, int, int, int):void");
    }

    @Override // android.view.View
    public void draw(Canvas canvas) {
        super.draw(canvas);
        DividerDrawable dividerDrawable = this.mDividerDrawable;
        if (dividerDrawable != null) {
            dividerDrawable.draw(canvas);
        }
        ArrowDrawable arrowDrawable = this.mArrowDrawable;
        if (arrowDrawable != null) {
            arrowDrawable.draw(canvas);
        }
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        ArrowDrawable arrowDrawable = this.mArrowDrawable;
        if (arrowDrawable != null) {
            arrowDrawable.setState(getDrawableState());
        }
        DividerDrawable dividerDrawable = this.mDividerDrawable;
        if (dividerDrawable != null) {
            dividerDrawable.setState(getDrawableState());
        }
    }

    public boolean performItemClick(View view, int i, long j) {
        OnItemClickListener onItemClickListener = this.mOnItemClickListener;
        if (onItemClickListener != null) {
            if (!onItemClickListener.onItemClick(this, view, i, j)) {
                return true;
            }
            setSelection(i);
            return true;
        }
        setSelection(i);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataChanged() {
        int i = this.mSelectedPosition;
        if (i == -1) {
            setSelection(0);
        } else if (i < this.mAdapter.getCount()) {
            onDataInvalidated();
        } else {
            setSelection(this.mAdapter.getCount() - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onDataInvalidated() {
        if (this.mAdapter == null) {
            return;
        }
        if (this.mLabelView == null) {
            removeAllViews();
        } else {
            for (int childCount = getChildCount() - 1; childCount > 0; childCount--) {
                removeViewAt(childCount);
            }
        }
        int itemViewType = this.mAdapter.getItemViewType(this.mSelectedPosition);
        View view = this.mAdapter.getView(this.mSelectedPosition, this.mRecycler.get(itemViewType), this);
        view.setFocusable(false);
        view.setClickable(false);
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        super.addView(view);
        this.mRecycler.put(itemViewType, view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPopup() {
        if (this.mPopup.isShowing()) {
            return;
        }
        this.mPopup.show();
        final ListView listView = this.mPopup.getListView();
        if (listView != null) {
            if (Build.VERSION.SDK_INT >= 11) {
                listView.setChoiceMode(1);
            }
            listView.setSelection(getSelectedItemPosition());
            if (this.mArrowDrawable == null || !this.mArrowAnimSwitchMode) {
                return;
            }
            listView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.rey.material.widget.Spinner.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    listView.getViewTreeObserver().removeOnPreDrawListener(this);
                    Spinner.this.mArrowDrawable.setMode(ArrowDrawable.MODE_UP, true);
                    return true;
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPopupDismissed() {
        ArrowDrawable arrowDrawable = this.mArrowDrawable;
        if (arrowDrawable != null) {
            arrowDrawable.setMode(ArrowDrawable.MODE_DOWN, true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int measureContentWidth(SpinnerAdapter spinnerAdapter, Drawable drawable) {
        int i = 0;
        if (spinnerAdapter == null) {
            return 0;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        int max = Math.max(0, getSelectedItemPosition());
        int min = Math.min(spinnerAdapter.getCount(), max + 15);
        View view = null;
        int i2 = 0;
        for (int max2 = Math.max(0, max - (15 - (min - max))); max2 < min; max2++) {
            int itemViewType = spinnerAdapter.getItemViewType(max2);
            if (itemViewType != i) {
                view = null;
                i = itemViewType;
            }
            view = spinnerAdapter.getView(max2, view, null);
            if (view.getLayoutParams() == null) {
                view.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
            }
            view.measure(makeMeasureSpec, makeMeasureSpec2);
            i2 = Math.max(i2, view.getMeasuredWidth());
        }
        if (drawable == null) {
            return i2;
        }
        drawable.getPadding(this.mTempRect);
        return i2 + this.mTempRect.left + this.mTempRect.right;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: classes2.dex */
    public static class SavedState extends View.BaseSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.Creator<SavedState>() { // from class: com.rey.material.widget.Spinner.SavedState.1
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
        int position;
        boolean showDropdown;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        SavedState(Parcel parcel) {
            super(parcel);
            this.position = parcel.readInt();
            this.showDropdown = parcel.readByte() != 0;
        }

        @Override // android.view.View.BaseSavedState, android.view.AbsSavedState, android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.position);
            parcel.writeByte(this.showDropdown ? (byte) 1 : (byte) 0);
        }

        public String toString() {
            return "AbsSpinner.SavedState{" + Integer.toHexString(System.identityHashCode(this)) + " position=" + this.position + " showDropdown=" + this.showDropdown + "}";
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        savedState.position = getSelectedItemPosition();
        DropdownPopup dropdownPopup = this.mPopup;
        savedState.showDropdown = dropdownPopup != null && dropdownPopup.isShowing();
        return savedState;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable parcelable) {
        ViewTreeObserver viewTreeObserver;
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        setSelection(savedState.position);
        if (!savedState.showDropdown || (viewTreeObserver = getViewTreeObserver()) == null) {
            return;
        }
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.rey.material.widget.Spinner.3
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                Spinner.this.showPopup();
                ViewTreeObserver viewTreeObserver2 = Spinner.this.getViewTreeObserver();
                if (viewTreeObserver2 != null) {
                    viewTreeObserver2.removeGlobalOnLayoutListener(this);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SpinnerDataSetObserver extends DataSetObserver {
        private SpinnerDataSetObserver() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            Spinner.this.onDataChanged();
        }

        @Override // android.database.DataSetObserver
        public void onInvalidated() {
            Spinner.this.onDataInvalidated();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class RecycleBin {
        private final SparseArray<View> mScrapHeap;

        private RecycleBin() {
            this.mScrapHeap = new SparseArray<>();
        }

        public void put(int i, View view) {
            this.mScrapHeap.put(i, view);
        }

        View get(int i) {
            View view = this.mScrapHeap.get(i);
            if (view != null) {
                this.mScrapHeap.delete(i);
            }
            return view;
        }

        void clear() {
            this.mScrapHeap.clear();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public static class DropDownAdapter implements ListAdapter, SpinnerAdapter, View.OnClickListener {
        private SpinnerAdapter mAdapter;
        private ListAdapter mListAdapter;
        private AdapterView.OnItemClickListener mOnItemClickListener;

        public DropDownAdapter(SpinnerAdapter spinnerAdapter) {
            this.mAdapter = spinnerAdapter;
            if (spinnerAdapter instanceof ListAdapter) {
                this.mListAdapter = (ListAdapter) spinnerAdapter;
            }
        }

        public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
            this.mOnItemClickListener = onItemClickListener;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int intValue = ((Integer) view.getTag()).intValue();
            AdapterView.OnItemClickListener onItemClickListener = this.mOnItemClickListener;
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(null, view, intValue, 0L);
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return 0;
            }
            return spinnerAdapter.getCount();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getItem(i);
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return -1L;
            }
            return spinnerAdapter.getItemId(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            View dropDownView = getDropDownView(i, view, viewGroup);
            dropDownView.setOnClickListener(this);
            dropDownView.setTag(Integer.valueOf(i));
            return dropDownView;
        }

        @Override // android.widget.SpinnerAdapter
        public View getDropDownView(int i, View view, ViewGroup viewGroup) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter == null) {
                return null;
            }
            return spinnerAdapter.getDropDownView(i, view, viewGroup);
        }

        @Override // android.widget.Adapter
        public boolean hasStableIds() {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            return spinnerAdapter != null && spinnerAdapter.hasStableIds();
        }

        @Override // android.widget.ListAdapter
        public boolean areAllItemsEnabled() {
            ListAdapter listAdapter = this.mListAdapter;
            return listAdapter == null || listAdapter.areAllItemsEnabled();
        }

        @Override // android.widget.ListAdapter
        public boolean isEnabled(int i) {
            ListAdapter listAdapter = this.mListAdapter;
            return listAdapter == null || listAdapter.isEnabled(i);
        }

        @Override // android.widget.Adapter
        public int getItemViewType(int i) {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.getItemViewType(i);
            }
            return 0;
        }

        @Override // android.widget.Adapter
        public int getViewTypeCount() {
            ListAdapter listAdapter = this.mListAdapter;
            if (listAdapter != null) {
                return listAdapter.getViewTypeCount();
            }
            return 1;
        }

        @Override // android.widget.Adapter
        public boolean isEmpty() {
            return getCount() == 0;
        }

        @Override // android.widget.Adapter
        public void registerDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.registerDataSetObserver(dataSetObserver);
            }
        }

        @Override // android.widget.Adapter
        public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {
            SpinnerAdapter spinnerAdapter = this.mAdapter;
            if (spinnerAdapter != null) {
                spinnerAdapter.unregisterDataSetObserver(dataSetObserver);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DropdownPopup extends ListPopupWindow {
        private ViewTreeObserver.OnGlobalLayoutListener layoutListener;
        private DropDownAdapter mAdapter;
        private CharSequence mHintText;

        public DropdownPopup(Context context, AttributeSet attributeSet, int i, int i2) {
            super(context, attributeSet, i, i2);
            this.layoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.rey.material.widget.Spinner.DropdownPopup.1
                @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
                public void onGlobalLayout() {
                    DropdownPopup.this.computeContentWidth();
                    DropdownPopup.super.show();
                }
            };
            setAnchorView(Spinner.this);
            setModal(true);
            setPromptPosition(0);
            setOnDismissListener(new PopupWindow.OnDismissListener() { // from class: com.rey.material.widget.Spinner.DropdownPopup.2
                @Override // android.widget.PopupWindow.OnDismissListener
                public void onDismiss() {
                    ViewTreeObserver viewTreeObserver = Spinner.this.getViewTreeObserver();
                    if (viewTreeObserver != null) {
                        if (Build.VERSION.SDK_INT >= 16) {
                            viewTreeObserver.removeOnGlobalLayoutListener(DropdownPopup.this.layoutListener);
                        } else {
                            viewTreeObserver.removeGlobalOnLayoutListener(DropdownPopup.this.layoutListener);
                        }
                    }
                    Spinner.this.onPopupDismissed();
                }
            });
        }

        @Override // com.rey.material.widget.ListPopupWindow
        public void setAdapter(ListAdapter listAdapter) {
            super.setAdapter(listAdapter);
            DropDownAdapter dropDownAdapter = (DropDownAdapter) listAdapter;
            this.mAdapter = dropDownAdapter;
            dropDownAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() { // from class: com.rey.material.widget.Spinner.DropdownPopup.3
                @Override // android.widget.AdapterView.OnItemClickListener
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
                    Spinner.this.performItemClick(view, i, DropdownPopup.this.mAdapter.getItemId(i));
                    DropdownPopup.this.dismiss();
                }
            });
        }

        public CharSequence getHintText() {
            return this.mHintText;
        }

        public void setPromptText(CharSequence charSequence) {
            this.mHintText = charSequence;
        }

        void computeContentWidth() {
            Drawable background = getBackground();
            int i = 0;
            if (background != null) {
                background.getPadding(Spinner.this.mTempRect);
                i = Spinner.this.mIsRtl ? Spinner.this.mTempRect.right : -Spinner.this.mTempRect.left;
            } else {
                Rect rect = Spinner.this.mTempRect;
                Spinner.this.mTempRect.right = 0;
                rect.left = 0;
            }
            int paddingLeft = Spinner.this.getPaddingLeft();
            int paddingRight = Spinner.this.getPaddingRight();
            int width = Spinner.this.getWidth();
            if (Spinner.this.mDropDownWidth == -2) {
                int measureContentWidth = Spinner.this.measureContentWidth(this.mAdapter, getBackground());
                int i2 = (Spinner.this.getContext().getResources().getDisplayMetrics().widthPixels - Spinner.this.mTempRect.left) - Spinner.this.mTempRect.right;
                if (measureContentWidth > i2) {
                    measureContentWidth = i2;
                }
                setContentWidth(Math.max(measureContentWidth, (width - paddingLeft) - paddingRight));
            } else if (Spinner.this.mDropDownWidth != -1) {
                setContentWidth(Spinner.this.mDropDownWidth);
            } else {
                setContentWidth((width - paddingLeft) - paddingRight);
            }
            setHorizontalOffset(Spinner.this.mIsRtl ? i + ((width - paddingRight) - getWidth()) : i + paddingLeft);
        }

        @Override // com.rey.material.widget.ListPopupWindow
        public void show() {
            ViewTreeObserver viewTreeObserver;
            boolean isShowing = isShowing();
            computeContentWidth();
            setInputMethodMode(2);
            super.show();
            if (isShowing || (viewTreeObserver = Spinner.this.getViewTreeObserver()) == null) {
                return;
            }
            viewTreeObserver.addOnGlobalLayoutListener(this.layoutListener);
        }
    }
}
