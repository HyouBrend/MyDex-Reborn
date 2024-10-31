package com.androidbuts.multispinnerfilter;

import android.app.AlertDialog;
import android.app.Instrumentation;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import com.androidbuts.multispinnerfilter.MultiSpinnerSearch;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* loaded from: classes.dex */
public class MultiSpinnerSearch extends AppCompatSpinner implements DialogInterface.OnCancelListener {
    public static AlertDialog ad;
    public static AlertDialog.Builder builder;
    private MyAdapter adapter;
    private String clearText;
    private boolean colorSeparation;
    private String defaultText;
    private String emptyTitle;
    private int highlightColor;
    private boolean highlightSelected;
    private boolean isSearchEnabled;
    private boolean isShowSelectAllButton;
    private List<KeyPairBoolData> items;
    private int limit;
    private LimitExceedListener limitListener;
    private MultiSpinnerListener listener;
    private String searchHint;
    private int selected;
    private String spinnerTitle;
    private int textColor;

    /* loaded from: classes.dex */
    public interface LimitExceedListener {
        void onLimitListener(KeyPairBoolData keyPairBoolData);
    }

    static /* synthetic */ int access$606(MultiSpinnerSearch multiSpinnerSearch) {
        int i = multiSpinnerSearch.selected - 1;
        multiSpinnerSearch.selected = i;
        return i;
    }

    static /* synthetic */ int access$608(MultiSpinnerSearch multiSpinnerSearch) {
        int i = multiSpinnerSearch.selected;
        multiSpinnerSearch.selected = i + 1;
        return i;
    }

    static /* synthetic */ int access$610(MultiSpinnerSearch multiSpinnerSearch) {
        int i = multiSpinnerSearch.selected;
        multiSpinnerSearch.selected = i - 1;
        return i;
    }

    public MultiSpinnerSearch(Context context) {
        super(context);
        this.highlightSelected = false;
        this.highlightColor = ContextCompat.getColor(getContext(), R.color.list_selected);
        this.textColor = -7829368;
        this.limit = -1;
        this.selected = 0;
        this.defaultText = "";
        this.spinnerTitle = "";
        this.emptyTitle = "Not Found!";
        this.searchHint = "Type to search";
        this.clearText = "Clear All";
        this.colorSeparation = false;
        this.isShowSelectAllButton = false;
        this.isSearchEnabled = true;
    }

    public MultiSpinnerSearch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.highlightSelected = false;
        this.highlightColor = ContextCompat.getColor(getContext(), R.color.list_selected);
        this.textColor = -7829368;
        this.limit = -1;
        this.selected = 0;
        this.defaultText = "";
        this.spinnerTitle = "";
        this.emptyTitle = "Not Found!";
        this.searchHint = "Type to search";
        this.clearText = "Clear All";
        this.colorSeparation = false;
        this.isShowSelectAllButton = false;
        this.isSearchEnabled = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MultiSpinnerSearch);
        int i = 0;
        while (true) {
            if (i >= obtainStyledAttributes.getIndexCount()) {
                break;
            }
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.MultiSpinnerSearch_hintText) {
                setHintText(obtainStyledAttributes.getString(index));
                String hintText = getHintText();
                this.spinnerTitle = hintText;
                this.defaultText = hintText;
                break;
            }
            if (index == R.styleable.MultiSpinnerSearch_highlightSelected) {
                this.highlightSelected = obtainStyledAttributes.getBoolean(index, false);
            } else if (index == R.styleable.MultiSpinnerSearch_highlightColor) {
                this.highlightColor = obtainStyledAttributes.getColor(index, ContextCompat.getColor(getContext(), R.color.list_selected));
            } else if (index == R.styleable.MultiSpinnerSearch_textColor) {
                this.textColor = obtainStyledAttributes.getColor(index, -7829368);
            } else if (index == R.styleable.MultiSpinnerSearch_clearText) {
                setClearText(obtainStyledAttributes.getString(index));
            }
            i++;
        }
        obtainStyledAttributes.recycle();
    }

    public MultiSpinnerSearch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.highlightSelected = false;
        this.highlightColor = ContextCompat.getColor(getContext(), R.color.list_selected);
        this.textColor = -7829368;
        this.limit = -1;
        this.selected = 0;
        this.defaultText = "";
        this.spinnerTitle = "";
        this.emptyTitle = "Not Found!";
        this.searchHint = "Type to search";
        this.clearText = "Clear All";
        this.colorSeparation = false;
        this.isShowSelectAllButton = false;
        this.isSearchEnabled = true;
    }

    public boolean isSearchEnabled() {
        return this.isSearchEnabled;
    }

    public void setSearchEnabled(boolean z) {
        this.isSearchEnabled = z;
    }

    public boolean isColorSeparation() {
        return this.colorSeparation;
    }

    public void setColorSeparation(boolean z) {
        this.colorSeparation = z;
    }

    public String getHintText() {
        return this.spinnerTitle;
    }

    public void setHintText(String str) {
        this.spinnerTitle = str;
        this.defaultText = str;
    }

    public void setClearText(String str) {
        this.clearText = str;
    }

    public void setLimit(int i, LimitExceedListener limitExceedListener) {
        this.limit = i;
        this.limitListener = limitExceedListener;
        this.isShowSelectAllButton = false;
    }

    public List<KeyPairBoolData> getSelectedItems() {
        ArrayList arrayList = new ArrayList();
        for (KeyPairBoolData keyPairBoolData : this.items) {
            if (keyPairBoolData.isSelected()) {
                arrayList.add(keyPairBoolData);
            }
        }
        return arrayList;
    }

    public List<Long> getSelectedIds() {
        ArrayList arrayList = new ArrayList();
        for (KeyPairBoolData keyPairBoolData : this.items) {
            if (keyPairBoolData.isSelected()) {
                arrayList.add(Long.valueOf(keyPairBoolData.getId()));
            }
        }
        return arrayList;
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        String hintText;
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.items.size(); i++) {
            KeyPairBoolData keyPairBoolData = this.items.get(i);
            if (keyPairBoolData.isSelected()) {
                arrayList.add(keyPairBoolData);
                sb.append(keyPairBoolData.getName());
                sb.append(", ");
            }
        }
        String sb2 = sb.toString();
        if (sb2.length() > 2) {
            hintText = sb2.substring(0, sb2.length() - 2);
        } else {
            hintText = getHintText();
        }
        setAdapter((SpinnerAdapter) new ArrayAdapter(getContext(), R.layout.textview_for_spinner, new String[]{hintText}));
        MyAdapter myAdapter = this.adapter;
        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }
        this.listener.onItemsSelected(arrayList);
        new Thread(new Runnable() { // from class: com.androidbuts.multispinnerfilter.MultiSpinnerSearch$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                new Instrumentation().sendKeyDownUpSync(4);
            }
        }).start();
    }

    @Override // androidx.appcompat.widget.AppCompatSpinner, android.widget.Spinner, android.view.View
    public boolean performClick() {
        super.performClick();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(getContext());
        builder = builder2;
        builder2.setTitle(this.spinnerTitle);
        View inflate = ((LayoutInflater) getContext().getSystemService("layout_inflater")).inflate(R.layout.alert_dialog_listview_search, (ViewGroup) null);
        builder.setView(inflate);
        ListView listView = (ListView) inflate.findViewById(R.id.alertSearchListView);
        listView.setChoiceMode(2);
        listView.setFastScrollEnabled(false);
        MyAdapter myAdapter = new MyAdapter(getContext(), this.items);
        this.adapter = myAdapter;
        listView.setAdapter((ListAdapter) myAdapter);
        TextView textView = (TextView) inflate.findViewById(R.id.empty);
        textView.setText(this.emptyTitle);
        listView.setEmptyView(textView);
        EditText editText = (EditText) inflate.findViewById(R.id.alertSearchEditText);
        if (this.isSearchEnabled) {
            editText.setVisibility(0);
            editText.setHint(this.searchHint);
            editText.addTextChangedListener(new TextWatcher() { // from class: com.androidbuts.multispinnerfilter.MultiSpinnerSearch.1
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    MultiSpinnerSearch.this.adapter.getFilter().filter(charSequence.toString());
                }
            });
        } else {
            editText.setVisibility(8);
        }
        this.selected = 0;
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).isSelected()) {
                this.selected++;
            }
        }
        if (this.isShowSelectAllButton && this.limit == -1) {
            builder.setNeutralButton(android.R.string.selectAll, new DialogInterface.OnClickListener() { // from class: com.androidbuts.multispinnerfilter.MultiSpinnerSearch$$ExternalSyntheticLambda1
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i2) {
                    MultiSpinnerSearch.this.m127x167d90b2(dialogInterface, i2);
                }
            });
        }
        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() { // from class: com.androidbuts.multispinnerfilter.MultiSpinnerSearch$$ExternalSyntheticLambda2
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                dialogInterface.cancel();
            }
        });
        builder.setNegativeButton(this.clearText, new DialogInterface.OnClickListener() { // from class: com.androidbuts.multispinnerfilter.MultiSpinnerSearch$$ExternalSyntheticLambda3
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                MultiSpinnerSearch.this.m128x69263b34(dialogInterface, i2);
            }
        });
        builder.setOnCancelListener(this);
        AlertDialog show = builder.show();
        ad = show;
        ((Window) Objects.requireNonNull(show.getWindow())).setLayout(-2, -2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$performClick$1$com-androidbuts-multispinnerfilter-MultiSpinnerSearch, reason: not valid java name */
    public /* synthetic */ void m127x167d90b2(DialogInterface dialogInterface, int i) {
        for (int i2 = 0; i2 < this.adapter.arrayList.size(); i2++) {
            this.adapter.arrayList.get(i2).setSelected(true);
        }
        this.adapter.notifyDataSetChanged();
        dialogInterface.cancel();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$performClick$3$com-androidbuts-multispinnerfilter-MultiSpinnerSearch, reason: not valid java name */
    public /* synthetic */ void m128x69263b34(DialogInterface dialogInterface, int i) {
        for (int i2 = 0; i2 < this.adapter.arrayList.size(); i2++) {
            this.adapter.arrayList.get(i2).setSelected(false);
        }
        this.adapter.notifyDataSetChanged();
        dialogInterface.cancel();
    }

    public void setItems(List<KeyPairBoolData> list, MultiSpinnerListener multiSpinnerListener) {
        this.items = list;
        this.listener = multiSpinnerListener;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isSelected()) {
                sb.append(list.get(i).getName());
                sb.append(", ");
            }
        }
        if (sb.length() > 2) {
            this.defaultText = sb.toString().substring(0, sb.toString().length() - 2);
        }
        setAdapter((SpinnerAdapter) new ArrayAdapter(getContext(), R.layout.textview_for_spinner, new String[]{this.defaultText}));
    }

    public void setEmptyTitle(String str) {
        this.emptyTitle = str;
    }

    public void setSearchHint(String str) {
        this.searchHint = str;
    }

    public boolean isShowSelectAllButton() {
        return this.isShowSelectAllButton;
    }

    public void setShowSelectAllButton(boolean z) {
        this.isShowSelectAllButton = z;
    }

    /* loaded from: classes.dex */
    public class MyAdapter extends BaseAdapter implements Filterable {
        List<KeyPairBoolData> arrayList;
        final LayoutInflater inflater;
        final List<KeyPairBoolData> mOriginalValues;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        MyAdapter(Context context, List<KeyPairBoolData> list) {
            this.arrayList = list;
            this.mOriginalValues = list;
            this.inflater = LayoutInflater.from(context);
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.arrayList.size();
        }

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return Integer.valueOf(i);
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            if (view == null) {
                ViewHolder viewHolder2 = new ViewHolder();
                View inflate = this.inflater.inflate(R.layout.item_listview_multiple, viewGroup, false);
                viewHolder2.textView = (TextView) inflate.findViewById(R.id.alertTextView);
                viewHolder2.checkBox = (CheckBox) inflate.findViewById(R.id.alertCheckbox);
                inflate.setTag(viewHolder2);
                viewHolder = viewHolder2;
                view = inflate;
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            int i2 = R.color.white;
            if (MultiSpinnerSearch.this.colorSeparation) {
                i2 = i % 2 == 0 ? R.color.list_even : R.color.list_odd;
                view.setBackgroundColor(ContextCompat.getColor(MultiSpinnerSearch.this.getContext(), i2));
            }
            final KeyPairBoolData keyPairBoolData = this.arrayList.get(i);
            viewHolder.textView.setText(keyPairBoolData.getName());
            viewHolder.checkBox.setChecked(keyPairBoolData.isSelected());
            view.setOnClickListener(new View.OnClickListener() { // from class: com.androidbuts.multispinnerfilter.MultiSpinnerSearch$MyAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    MultiSpinnerSearch.MyAdapter.this.m129x38a7faca(keyPairBoolData, view2);
                }
            });
            if (keyPairBoolData.isSelected()) {
                viewHolder.textView.setTextColor(MultiSpinnerSearch.this.textColor);
                if (MultiSpinnerSearch.this.highlightSelected) {
                    viewHolder.textView.setTypeface(null, 1);
                    view.setBackgroundColor(MultiSpinnerSearch.this.highlightColor);
                } else {
                    view.setBackgroundColor(-1);
                }
            } else {
                viewHolder.textView.setTypeface(null, 0);
                view.setBackgroundColor(ContextCompat.getColor(MultiSpinnerSearch.this.getContext(), i2));
            }
            viewHolder.checkBox.setTag(viewHolder);
            return view;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getView$0$com-androidbuts-multispinnerfilter-MultiSpinnerSearch$MyAdapter, reason: not valid java name */
        public /* synthetic */ void m129x38a7faca(KeyPairBoolData keyPairBoolData, View view) {
            if (keyPairBoolData.isSelected()) {
                MultiSpinnerSearch.access$610(MultiSpinnerSearch.this);
            } else {
                MultiSpinnerSearch.access$608(MultiSpinnerSearch.this);
                if (MultiSpinnerSearch.this.selected > MultiSpinnerSearch.this.limit && MultiSpinnerSearch.this.limit > 0) {
                    MultiSpinnerSearch.access$606(MultiSpinnerSearch.this);
                    if (MultiSpinnerSearch.this.limitListener != null) {
                        MultiSpinnerSearch.this.limitListener.onLimitListener(keyPairBoolData);
                        return;
                    }
                    return;
                }
            }
            ((ViewHolder) view.getTag()).checkBox.setChecked(!r4.checkBox.isChecked());
            keyPairBoolData.setSelected(!keyPairBoolData.isSelected());
            notifyDataSetChanged();
        }

        @Override // android.widget.Filterable
        public Filter getFilter() {
            return new Filter() { // from class: com.androidbuts.multispinnerfilter.MultiSpinnerSearch.MyAdapter.1
                @Override // android.widget.Filter
                protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                    MyAdapter.this.arrayList = (List) filterResults.values;
                    MyAdapter.this.notifyDataSetChanged();
                }

                @Override // android.widget.Filter
                protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                    Filter.FilterResults filterResults = new Filter.FilterResults();
                    ArrayList arrayList = new ArrayList();
                    if (charSequence == null || charSequence.length() == 0) {
                        filterResults.count = MyAdapter.this.mOriginalValues.size();
                        filterResults.values = MyAdapter.this.mOriginalValues;
                    } else {
                        String lowerCase = charSequence.toString().toLowerCase();
                        for (int i = 0; i < MyAdapter.this.mOriginalValues.size(); i++) {
                            if (MyAdapter.this.mOriginalValues.get(i).getName().toLowerCase().contains(lowerCase.toString())) {
                                arrayList.add(MyAdapter.this.mOriginalValues.get(i));
                            }
                        }
                        filterResults.count = arrayList.size();
                        filterResults.values = arrayList;
                    }
                    return filterResults;
                }
            };
        }

        /* loaded from: classes.dex */
        private class ViewHolder {
            CheckBox checkBox;
            TextView textView;

            private ViewHolder() {
            }
        }
    }
}
