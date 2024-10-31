package com.rey.material.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import androidx.core.view.ViewCompat;
import com.rey.material.R;
import com.rey.material.app.Dialog;
import com.rey.material.drawable.BlankDrawable;
import com.rey.material.widget.CheckBox;
import com.rey.material.widget.ListView;
import com.rey.material.widget.RadioButton;
import com.rey.material.widget.TextView;

/* loaded from: classes2.dex */
public class SimpleDialog extends Dialog {
    protected static final int MODE_CUSTOM = 4;
    protected static final int MODE_ITEMS = 2;
    protected static final int MODE_MESSAGE = 1;
    protected static final int MODE_MULTI_ITEMS = 3;
    protected static final int MODE_NONE = 0;
    private InternalAdapter mAdapter;
    private int mCheckBoxStyle;
    private int mItemHeight;
    private int mItemTextAppearance;
    private InternalListView mListView;
    private TextView mMessage;
    private int mMessageTextAppearanceId;
    private int mMessageTextColor;
    private int mMode;
    private OnSelectionChangedListener mOnSelectionChangedListener;
    private int mRadioButtonStyle;
    private InternalScrollView mScrollView;

    /* loaded from: classes2.dex */
    public interface OnSelectionChangedListener {
        void onSelectionChanged(int i, boolean z);
    }

    public SimpleDialog(Context context) {
        super(context, R.style.Material_App_Dialog_Simple_Light);
    }

    public SimpleDialog(Context context, int i) {
        super(context, i);
    }

    @Override // com.rey.material.app.Dialog
    protected void onCreate() {
        messageTextAppearance(R.style.TextAppearance_AppCompat_Body1);
        itemHeight(-2);
        itemTextAppearance(R.style.TextAppearance_AppCompat_Body1);
    }

    @Override // com.rey.material.app.Dialog
    public Dialog applyStyle(int i) {
        super.applyStyle(i);
        if (i == 0) {
            return this;
        }
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(i, R.styleable.SimpleDialog);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i2 = 0;
        boolean z = false;
        int i3 = 0;
        for (int i4 = 0; i4 < indexCount; i4++) {
            int index = obtainStyledAttributes.getIndex(i4);
            if (index == R.styleable.SimpleDialog_di_messageTextAppearance) {
                i2 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.SimpleDialog_di_messageTextColor) {
                i3 = obtainStyledAttributes.getColor(index, 0);
                z = true;
            } else if (index == R.styleable.SimpleDialog_di_radioButtonStyle) {
                radioButtonStyle(obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.SimpleDialog_di_checkBoxStyle) {
                checkBoxStyle(obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.SimpleDialog_di_itemHeight) {
                itemHeight(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.SimpleDialog_di_itemTextAppearance) {
                itemTextAppearance(obtainStyledAttributes.getResourceId(index, 0));
            }
        }
        obtainStyledAttributes.recycle();
        if (i2 != 0) {
            messageTextAppearance(i2);
        }
        if (z) {
            messageTextColor(i3);
        }
        return this;
    }

    @Override // com.rey.material.app.Dialog
    public Dialog clearContent() {
        super.clearContent();
        this.mMode = 0;
        return this;
    }

    @Override // com.rey.material.app.Dialog
    public Dialog title(CharSequence charSequence) {
        contentMargin(this.mContentPadding, TextUtils.isEmpty(charSequence) ^ true ? 0 : this.mContentPadding, this.mContentPadding, 0);
        return super.title(charSequence);
    }

    @Override // com.rey.material.app.Dialog
    public Dialog contentView(View view) {
        if (this.mScrollView == null) {
            initScrollView();
        }
        if (this.mScrollView.getChildAt(0) != view && view != null) {
            this.mScrollView.removeAllViews();
            this.mScrollView.addView(view);
            this.mMode = 4;
            super.contentView(this.mScrollView);
        }
        return this;
    }

    private void initScrollView() {
        InternalScrollView internalScrollView = new InternalScrollView(getContext());
        this.mScrollView = internalScrollView;
        internalScrollView.setPadding(0, 0, 0, this.mContentPadding - this.mActionPadding);
        this.mScrollView.setClipToPadding(false);
        this.mScrollView.setFillViewport(true);
        this.mScrollView.setScrollBarStyle(33554432);
        ViewCompat.setLayoutDirection(this.mScrollView, 2);
    }

    private void initMessageView() {
        TextView textView = new TextView(getContext());
        this.mMessage = textView;
        textView.setTextAppearance(getContext(), this.mMessageTextAppearanceId);
        this.mMessage.setTextColor(this.mMessageTextColor);
        this.mMessage.setGravity(8388627);
    }

    public SimpleDialog message(CharSequence charSequence) {
        if (this.mScrollView == null) {
            initScrollView();
        }
        if (this.mMessage == null) {
            initMessageView();
        }
        if (this.mScrollView.getChildAt(0) != this.mMessage) {
            this.mScrollView.removeAllViews();
            this.mScrollView.addView(this.mMessage);
        }
        this.mMessage.setText(charSequence);
        if (!TextUtils.isEmpty(charSequence)) {
            this.mMode = 1;
            super.contentView(this.mScrollView);
        }
        return this;
    }

    public SimpleDialog message(int i) {
        return message(i == 0 ? null : getContext().getResources().getString(i));
    }

    public SimpleDialog messageTextAppearance(int i) {
        if (this.mMessageTextAppearanceId != i) {
            this.mMessageTextAppearanceId = i;
            TextView textView = this.mMessage;
            if (textView != null) {
                textView.setTextAppearance(getContext(), this.mMessageTextAppearanceId);
            }
        }
        return this;
    }

    public SimpleDialog messageTextColor(int i) {
        if (this.mMessageTextColor != i) {
            this.mMessageTextColor = i;
            TextView textView = this.mMessage;
            if (textView != null) {
                textView.setTextColor(i);
            }
        }
        return this;
    }

    public SimpleDialog radioButtonStyle(int i) {
        if (this.mRadioButtonStyle != i) {
            this.mRadioButtonStyle = i;
            InternalAdapter internalAdapter = this.mAdapter;
            if (internalAdapter != null && this.mMode == 2) {
                internalAdapter.notifyDataSetChanged();
            }
        }
        return this;
    }

    public SimpleDialog checkBoxStyle(int i) {
        if (this.mCheckBoxStyle != i) {
            this.mCheckBoxStyle = i;
            InternalAdapter internalAdapter = this.mAdapter;
            if (internalAdapter != null && this.mMode == 3) {
                internalAdapter.notifyDataSetChanged();
            }
        }
        return this;
    }

    public SimpleDialog itemHeight(int i) {
        if (this.mItemHeight != i) {
            this.mItemHeight = i;
            InternalAdapter internalAdapter = this.mAdapter;
            if (internalAdapter != null) {
                internalAdapter.notifyDataSetChanged();
            }
        }
        return this;
    }

    public SimpleDialog itemTextAppearance(int i) {
        if (this.mItemTextAppearance != i) {
            this.mItemTextAppearance = i;
            InternalAdapter internalAdapter = this.mAdapter;
            if (internalAdapter != null) {
                internalAdapter.notifyDataSetChanged();
            }
        }
        return this;
    }

    private void initListView() {
        InternalListView internalListView = new InternalListView(getContext());
        this.mListView = internalListView;
        internalListView.setDividerHeight(0);
        this.mListView.setCacheColorHint(0);
        this.mListView.setScrollBarStyle(33554432);
        this.mListView.setClipToPadding(false);
        this.mListView.setSelector(BlankDrawable.getInstance());
        this.mListView.setPadding(0, 0, 0, this.mContentPadding - this.mActionPadding);
        this.mListView.setVerticalFadingEdgeEnabled(false);
        this.mListView.setOverScrollMode(2);
        ViewCompat.setLayoutDirection(this.mListView, 2);
        InternalAdapter internalAdapter = new InternalAdapter();
        this.mAdapter = internalAdapter;
        this.mListView.setAdapter(internalAdapter);
    }

    public SimpleDialog items(CharSequence[] charSequenceArr, int i) {
        if (this.mListView == null) {
            initListView();
        }
        this.mMode = 2;
        this.mAdapter.setItems(charSequenceArr, i);
        super.contentView((View) this.mListView);
        return this;
    }

    public SimpleDialog multiChoiceItems(CharSequence[] charSequenceArr, int... iArr) {
        if (this.mListView == null) {
            initListView();
        }
        this.mMode = 3;
        this.mAdapter.setItems(charSequenceArr, iArr);
        super.contentView((View) this.mListView);
        return this;
    }

    public SimpleDialog onSelectionChangedListener(OnSelectionChangedListener onSelectionChangedListener) {
        this.mOnSelectionChangedListener = onSelectionChangedListener;
        return this;
    }

    public int[] getSelectedIndexes() {
        InternalAdapter internalAdapter = this.mAdapter;
        if (internalAdapter == null) {
            return null;
        }
        return internalAdapter.getSelectedIndexes();
    }

    public CharSequence[] getSelectedValues() {
        return this.mAdapter.getSelectedValues();
    }

    public int getSelectedIndex() {
        InternalAdapter internalAdapter = this.mAdapter;
        if (internalAdapter == null) {
            return -1;
        }
        return internalAdapter.getLastSelectedIndex();
    }

    public CharSequence getSelectedValue() {
        return this.mAdapter.getLastSelectedValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class InternalScrollView extends ScrollView {
        private boolean mIsRtl;

        public InternalScrollView(Context context) {
            super(context);
            this.mIsRtl = false;
        }

        @Override // android.view.View
        public void onRtlPropertiesChanged(int i) {
            View childAt;
            boolean z = i == 1;
            if (this.mIsRtl != z) {
                this.mIsRtl = z;
                if (Build.VERSION.SDK_INT >= 17 && (childAt = getChildAt(0)) != null && childAt == SimpleDialog.this.mMessage) {
                    SimpleDialog.this.mMessage.setTextDirection(this.mIsRtl ? 4 : 3);
                }
                requestLayout();
            }
        }

        public boolean isLayoutRtl() {
            return this.mIsRtl;
        }

        @Override // android.widget.ScrollView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            boolean z2 = false;
            View childAt = getChildAt(0);
            SimpleDialog simpleDialog = SimpleDialog.this;
            if (childAt != null && childAt.getMeasuredHeight() > (getMeasuredHeight() - getPaddingTop()) - getPaddingBottom()) {
                z2 = true;
            }
            simpleDialog.showDivider(z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class InternalListView extends ListView {
        private boolean mIsRtl;

        public InternalListView(Context context) {
            super(context);
            this.mIsRtl = false;
        }

        public void onRtlPropertiesChanged(int i) {
            boolean z = i == 1;
            if (this.mIsRtl != z) {
                this.mIsRtl = z;
                requestLayout();
            }
        }

        public boolean isLayoutRtl() {
            return this.mIsRtl;
        }

        protected void onMeasure(int i, int i2) {
            if (View.MeasureSpec.getMode(i2) == 0 && SimpleDialog.this.mItemHeight != -2) {
                i2 = View.MeasureSpec.makeMeasureSpec((SimpleDialog.this.mItemHeight * getAdapter().getCount()) + getPaddingTop() + getPaddingBottom(), 1073741824);
            }
            super.onMeasure(i, i2);
        }

        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            super.onLayout(z, i, i2, i3, i4);
            int childCount = getChildCount();
            boolean z2 = false;
            int i5 = 0;
            for (int i6 = 0; i6 < childCount; i6++) {
                i5 += getChildAt(i6).getMeasuredHeight();
            }
            SimpleDialog simpleDialog = SimpleDialog.this;
            if (i5 > getMeasuredHeight() || (i5 == getMeasuredHeight() && getAdapter().getCount() > childCount)) {
                z2 = true;
            }
            simpleDialog.showDivider(z2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class InternalAdapter extends BaseAdapter implements CompoundButton.OnCheckedChangeListener {
        private CharSequence[] mItems;
        private int mLastSelectedIndex;
        private boolean[] mSelected;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        private InternalAdapter() {
        }

        public void setItems(CharSequence[] charSequenceArr, int... iArr) {
            this.mItems = charSequenceArr;
            boolean[] zArr = this.mSelected;
            if (zArr == null || zArr.length != charSequenceArr.length) {
                this.mSelected = new boolean[charSequenceArr.length];
            }
            int i = 0;
            while (true) {
                boolean[] zArr2 = this.mSelected;
                if (i >= zArr2.length) {
                    break;
                }
                zArr2[i] = false;
                i++;
            }
            if (iArr != null) {
                for (int i2 : iArr) {
                    if (i2 >= 0) {
                        boolean[] zArr3 = this.mSelected;
                        if (i2 < zArr3.length) {
                            zArr3[i2] = true;
                            this.mLastSelectedIndex = i2;
                        }
                    }
                }
            }
            notifyDataSetChanged();
        }

        public int getLastSelectedIndex() {
            return this.mLastSelectedIndex;
        }

        public CharSequence getLastSelectedValue() {
            return this.mItems[this.mLastSelectedIndex];
        }

        public int[] getSelectedIndexes() {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                boolean[] zArr = this.mSelected;
                if (i2 >= zArr.length) {
                    break;
                }
                if (zArr[i2]) {
                    i3++;
                }
                i2++;
            }
            if (i3 == 0) {
                return null;
            }
            int[] iArr = new int[i3];
            int i4 = 0;
            while (true) {
                boolean[] zArr2 = this.mSelected;
                if (i >= zArr2.length) {
                    return iArr;
                }
                if (zArr2[i]) {
                    iArr[i4] = i;
                    i4++;
                }
                i++;
            }
        }

        public CharSequence[] getSelectedValues() {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (true) {
                boolean[] zArr = this.mSelected;
                if (i2 >= zArr.length) {
                    break;
                }
                if (zArr[i2]) {
                    i3++;
                }
                i2++;
            }
            if (i3 == 0) {
                return null;
            }
            CharSequence[] charSequenceArr = new CharSequence[i3];
            int i4 = 0;
            while (true) {
                boolean[] zArr2 = this.mSelected;
                if (i >= zArr2.length) {
                    return charSequenceArr;
                }
                if (zArr2[i]) {
                    charSequenceArr[i4] = this.mItems[i];
                    i4++;
                }
                i++;
            }
        }

        @Override // android.widget.Adapter
        public int getCount() {
            CharSequence[] charSequenceArr = this.mItems;
            if (charSequenceArr == null) {
                return 0;
            }
            return charSequenceArr.length;
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            CharSequence[] charSequenceArr = this.mItems;
            if (charSequenceArr == null) {
                return 0;
            }
            return charSequenceArr[i];
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            com.rey.material.widget.CompoundButton compoundButton = (com.rey.material.widget.CompoundButton) view;
            if (compoundButton == null) {
                if (SimpleDialog.this.mMode == 3) {
                    compoundButton = new CheckBox(viewGroup.getContext());
                    compoundButton.applyStyle(SimpleDialog.this.mCheckBoxStyle);
                } else {
                    compoundButton = new RadioButton(viewGroup.getContext());
                    compoundButton.applyStyle(SimpleDialog.this.mRadioButtonStyle);
                }
                if (SimpleDialog.this.mItemHeight != -2) {
                    compoundButton.setMinHeight(SimpleDialog.this.mItemHeight);
                }
                compoundButton.setGravity(8388627);
                if (Build.VERSION.SDK_INT >= 17) {
                    compoundButton.setTextDirection(((InternalListView) viewGroup).isLayoutRtl() ? 4 : 3);
                }
                compoundButton.setTextAppearance(compoundButton.getContext(), SimpleDialog.this.mItemTextAppearance);
                compoundButton.setCompoundDrawablePadding(SimpleDialog.this.mContentPadding);
            }
            compoundButton.setTag(Integer.valueOf(i));
            compoundButton.setText(this.mItems[i]);
            if (compoundButton instanceof CheckBox) {
                ((CheckBox) compoundButton).setCheckedImmediately(this.mSelected[i]);
            } else {
                ((RadioButton) compoundButton).setCheckedImmediately(this.mSelected[i]);
            }
            compoundButton.setOnCheckedChangeListener(this);
            return compoundButton;
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            int i;
            int intValue = ((Integer) compoundButton.getTag()).intValue();
            boolean[] zArr = this.mSelected;
            if (zArr[intValue] != z) {
                zArr[intValue] = z;
                if (SimpleDialog.this.mOnSelectionChangedListener != null) {
                    SimpleDialog.this.mOnSelectionChangedListener.onSelectionChanged(intValue, this.mSelected[intValue]);
                }
            }
            if (SimpleDialog.this.mMode == 2 && z && (i = this.mLastSelectedIndex) != intValue) {
                this.mSelected[i] = false;
                if (SimpleDialog.this.mOnSelectionChangedListener != null) {
                    SimpleDialog.this.mOnSelectionChangedListener.onSelectionChanged(this.mLastSelectedIndex, false);
                }
                com.rey.material.widget.CompoundButton compoundButton2 = (com.rey.material.widget.CompoundButton) SimpleDialog.this.mListView.getChildAt(this.mLastSelectedIndex - SimpleDialog.this.mListView.getFirstVisiblePosition());
                if (compoundButton2 != null) {
                    compoundButton2.setChecked(false);
                }
                this.mLastSelectedIndex = intValue;
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder extends Dialog.Builder implements OnSelectionChangedListener {
        public static final Parcelable.Creator<Builder> CREATOR = new Parcelable.Creator<Builder>() { // from class: com.rey.material.app.SimpleDialog.Builder.1
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
        protected CharSequence[] mItems;
        protected CharSequence mMessage;
        protected int mMode;
        protected int[] mSelectedIndexes;

        public Builder() {
            super(R.style.Material_App_Dialog_Simple_Light);
        }

        public Builder(int i) {
            super(i);
        }

        public Builder message(CharSequence charSequence) {
            this.mMode = 1;
            this.mMessage = charSequence;
            return this;
        }

        public Builder items(CharSequence[] charSequenceArr, int i) {
            this.mMode = 2;
            this.mItems = charSequenceArr;
            this.mSelectedIndexes = new int[]{i};
            return this;
        }

        public Builder multiChoiceItems(CharSequence[] charSequenceArr, int... iArr) {
            this.mMode = 3;
            this.mItems = charSequenceArr;
            this.mSelectedIndexes = iArr;
            return this;
        }

        public int getSelectedIndex() {
            int i = this.mMode;
            if (i == 2 || i == 3) {
                return this.mSelectedIndexes[0];
            }
            return -1;
        }

        public CharSequence getSelectedValue() {
            int selectedIndex = getSelectedIndex();
            if (selectedIndex >= 0) {
                return this.mItems[selectedIndex];
            }
            return null;
        }

        public int[] getSelectedIndexes() {
            int i = this.mMode;
            if (i == 2 || i == 3) {
                return this.mSelectedIndexes;
            }
            return null;
        }

        public CharSequence[] getSelectedValues() {
            int[] selectedIndexes = getSelectedIndexes();
            if (selectedIndexes == null || selectedIndexes.length == 0) {
                return null;
            }
            CharSequence[] charSequenceArr = new CharSequence[selectedIndexes.length];
            for (int i = 0; i < selectedIndexes.length; i++) {
                charSequenceArr[i] = this.mItems[selectedIndexes[i]];
            }
            return charSequenceArr;
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected Dialog onBuild(Context context, int i) {
            SimpleDialog simpleDialog = new SimpleDialog(context, i);
            int i2 = this.mMode;
            if (i2 == 1) {
                simpleDialog.message(this.mMessage);
            } else if (i2 == 2) {
                CharSequence[] charSequenceArr = this.mItems;
                int[] iArr = this.mSelectedIndexes;
                simpleDialog.items(charSequenceArr, iArr != null ? iArr[0] : 0);
                simpleDialog.onSelectionChangedListener(this);
            } else if (i2 == 3) {
                simpleDialog.multiChoiceItems(this.mItems, this.mSelectedIndexes);
                simpleDialog.onSelectionChangedListener(this);
            }
            return simpleDialog;
        }

        @Override // com.rey.material.app.SimpleDialog.OnSelectionChangedListener
        public void onSelectionChanged(int i, boolean z) {
            int i2 = this.mMode;
            if (i2 != 2) {
                if (i2 != 3) {
                    return;
                }
                this.mSelectedIndexes = ((SimpleDialog) this.mDialog).getSelectedIndexes();
            } else if (z) {
                int[] iArr = this.mSelectedIndexes;
                if (iArr == null) {
                    this.mSelectedIndexes = new int[]{i};
                } else {
                    iArr[0] = i;
                }
            }
        }

        protected Builder(Parcel parcel) {
            super(parcel);
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected void onReadFromParcel(Parcel parcel) {
            int readInt = parcel.readInt();
            this.mMode = readInt;
            if (readInt == 1) {
                this.mMessage = (CharSequence) parcel.readParcelable(null);
                return;
            }
            int i = 0;
            if (readInt == 2) {
                Parcelable[] readParcelableArray = parcel.readParcelableArray(null);
                if (readParcelableArray != null && readParcelableArray.length > 0) {
                    this.mItems = new CharSequence[readParcelableArray.length];
                    int i2 = 0;
                    while (true) {
                        CharSequence[] charSequenceArr = this.mItems;
                        if (i2 >= charSequenceArr.length) {
                            break;
                        }
                        charSequenceArr[i2] = (CharSequence) readParcelableArray[i2];
                        i2++;
                    }
                } else {
                    this.mItems = null;
                }
                this.mSelectedIndexes = new int[]{parcel.readInt()};
                return;
            }
            if (readInt != 3) {
                return;
            }
            Parcelable[] readParcelableArray2 = parcel.readParcelableArray(null);
            if (readParcelableArray2 != null && readParcelableArray2.length > 0) {
                this.mItems = new CharSequence[readParcelableArray2.length];
                while (true) {
                    CharSequence[] charSequenceArr2 = this.mItems;
                    if (i >= charSequenceArr2.length) {
                        break;
                    }
                    charSequenceArr2[i] = (CharSequence) readParcelableArray2[i];
                    i++;
                }
            } else {
                this.mItems = null;
            }
            int readInt2 = parcel.readInt();
            if (readInt2 > 0) {
                int[] iArr = new int[readInt2];
                this.mSelectedIndexes = iArr;
                parcel.readIntArray(iArr);
            }
        }

        @Override // com.rey.material.app.Dialog.Builder
        protected void onWriteToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mMode);
            int i2 = this.mMode;
            if (i2 == 1) {
                parcel.writeValue(this.mMessage);
                return;
            }
            if (i2 == 2) {
                parcel.writeArray(this.mItems);
                int[] iArr = this.mSelectedIndexes;
                parcel.writeInt(iArr != null ? iArr[0] : 0);
            } else {
                if (i2 != 3) {
                    return;
                }
                parcel.writeArray(this.mItems);
                int[] iArr2 = this.mSelectedIndexes;
                int length = iArr2 != null ? iArr2.length : 0;
                parcel.writeInt(length);
                if (length > 0) {
                    parcel.writeIntArray(this.mSelectedIndexes);
                }
            }
        }
    }
}
