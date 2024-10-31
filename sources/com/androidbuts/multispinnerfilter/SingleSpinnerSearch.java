package com.androidbuts.multispinnerfilter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.core.content.ContextCompat;
import com.androidbuts.multispinnerfilter.SingleSpinnerSearch;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes.dex */
public class SingleSpinnerSearch extends AppCompatSpinner implements DialogInterface.OnCancelListener {
    private static final String TAG = "SingleSpinnerSearch";
    public static AlertDialog ad;
    public static AlertDialog.Builder builder;
    MyAdapter adapter;
    private boolean colorseparation;
    private String defaultText;
    private String emptyTitle;
    private boolean isSearchEnabled;
    private List<KeyPairBoolData> items;
    private SingleSpinnerListener listener;
    private String searchHint;
    private String spinnerTitle;

    public SingleSpinnerSearch(Context context) {
        super(context);
        this.defaultText = "";
        this.spinnerTitle = "";
        this.emptyTitle = "Not Found!";
        this.searchHint = "Type to search";
        this.colorseparation = false;
        this.isSearchEnabled = true;
    }

    public SingleSpinnerSearch(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.defaultText = "";
        this.spinnerTitle = "";
        this.emptyTitle = "Not Found!";
        this.searchHint = "Type to search";
        int i = 0;
        this.colorseparation = false;
        this.isSearchEnabled = true;
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SingleSpinnerSearch);
        int indexCount = obtainStyledAttributes.getIndexCount();
        while (true) {
            if (i >= indexCount) {
                break;
            }
            int index = obtainStyledAttributes.getIndex(i);
            if (index == R.styleable.MultiSpinnerSearch_hintText) {
                String string = obtainStyledAttributes.getString(index);
                this.spinnerTitle = string;
                this.defaultText = string;
                break;
            }
            i++;
        }
        Log.i(TAG, "spinnerTitle: " + this.spinnerTitle);
        obtainStyledAttributes.recycle();
    }

    public SingleSpinnerSearch(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.defaultText = "";
        this.spinnerTitle = "";
        this.emptyTitle = "Not Found!";
        this.searchHint = "Type to search";
        this.colorseparation = false;
        this.isSearchEnabled = true;
    }

    public boolean isSearchEnabled() {
        return this.isSearchEnabled;
    }

    public void setSearchEnabled(boolean z) {
        this.isSearchEnabled = z;
    }

    public boolean isColorseparation() {
        return this.colorseparation;
    }

    public void setColorseparation(boolean z) {
        this.colorseparation = z;
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
        String str;
        KeyPairBoolData keyPairBoolData;
        int i = 0;
        while (true) {
            str = null;
            if (i >= this.items.size()) {
                keyPairBoolData = null;
                break;
            } else {
                if (this.items.get(i).isSelected()) {
                    KeyPairBoolData keyPairBoolData2 = this.items.get(i);
                    str = keyPairBoolData2.getName();
                    keyPairBoolData = keyPairBoolData2;
                    break;
                }
                i++;
            }
        }
        if (str == null) {
            str = this.defaultText;
        }
        setAdapter((SpinnerAdapter) new ArrayAdapter(getContext(), R.layout.textview_for_spinner, new String[]{str}));
        MyAdapter myAdapter = this.adapter;
        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }
        this.listener.onItemsSelected(keyPairBoolData);
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
        listView.setChoiceMode(1);
        listView.setFastScrollEnabled(false);
        MyAdapter myAdapter = new MyAdapter(getContext(), this.items);
        this.adapter = myAdapter;
        listView.setAdapter((ListAdapter) myAdapter);
        int i = 0;
        while (true) {
            if (i >= this.items.size()) {
                break;
            }
            if (this.items.get(i).isSelected()) {
                listView.setSelection(i);
                break;
            }
            i++;
        }
        TextView textView = (TextView) inflate.findViewById(R.id.empty);
        textView.setText(this.emptyTitle);
        listView.setEmptyView(textView);
        EditText editText = (EditText) inflate.findViewById(R.id.alertSearchEditText);
        if (this.isSearchEnabled) {
            editText.setVisibility(0);
            editText.setHint(this.searchHint);
            editText.addTextChangedListener(new TextWatcher() { // from class: com.androidbuts.multispinnerfilter.SingleSpinnerSearch.1
                @Override // android.text.TextWatcher
                public void afterTextChanged(Editable editable) {
                }

                @Override // android.text.TextWatcher
                public void beforeTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                }

                @Override // android.text.TextWatcher
                public void onTextChanged(CharSequence charSequence, int i2, int i3, int i4) {
                    SingleSpinnerSearch.this.adapter.getFilter().filter(charSequence.toString());
                }
            });
        } else {
            editText.setVisibility(8);
        }
        builder.setPositiveButton("Clear", new DialogInterface.OnClickListener() { // from class: com.androidbuts.multispinnerfilter.SingleSpinnerSearch$$ExternalSyntheticLambda0
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i2) {
                SingleSpinnerSearch.this.m130xf11b7fd0(dialogInterface, i2);
            }
        });
        ad = builder.show();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: lambda$performClick$0$com-androidbuts-multispinnerfilter-SingleSpinnerSearch, reason: not valid java name */
    public /* synthetic */ void m130xf11b7fd0(DialogInterface dialogInterface, int i) {
        for (int i2 = 0; i2 < this.items.size(); i2++) {
            this.items.get(i2).setSelected(false);
        }
        setAdapter((SpinnerAdapter) new ArrayAdapter(getContext(), R.layout.textview_for_spinner, new String[]{this.defaultText}));
        MyAdapter myAdapter = this.adapter;
        if (myAdapter != null) {
            myAdapter.notifyDataSetChanged();
        }
        this.listener.onClear();
        dialogInterface.dismiss();
    }

    public void setItems(List<KeyPairBoolData> list, SingleSpinnerListener singleSpinnerListener) {
        this.items = list;
        this.listener = singleSpinnerListener;
        Iterator<KeyPairBoolData> it = list.iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            KeyPairBoolData next = it.next();
            if (next.isSelected()) {
                this.defaultText = next.getName();
                break;
            }
        }
        setAdapter((SpinnerAdapter) new ArrayAdapter(getContext(), R.layout.textview_for_spinner, new String[]{this.defaultText}));
    }

    public void setEmptyTitle(String str) {
        this.emptyTitle = str;
    }

    public void setSearchHint(String str) {
        this.searchHint = str;
    }

    /* loaded from: classes.dex */
    public class MyAdapter extends BaseAdapter implements Filterable {
        List<KeyPairBoolData> arrayList;
        final LayoutInflater inflater;
        List<KeyPairBoolData> mOriginalValues;

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return i;
        }

        public MyAdapter(Context context, List<KeyPairBoolData> list) {
            this.arrayList = list;
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
        public View getView(final int i, View view, ViewGroup viewGroup) {
            View view2;
            ViewHolder viewHolder;
            Log.i(SingleSpinnerSearch.TAG, "getView() enter");
            KeyPairBoolData keyPairBoolData = this.arrayList.get(i);
            if (view == null) {
                viewHolder = new ViewHolder();
                view2 = this.inflater.inflate(R.layout.item_listview_single, viewGroup, false);
                viewHolder.textView = (TextView) view2.findViewById(R.id.alertTextView);
                view2.setTag(viewHolder);
            } else {
                view2 = view;
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.textView.setText(keyPairBoolData.getName());
            int i2 = R.color.white;
            if (SingleSpinnerSearch.this.colorseparation) {
                i2 = i % 2 == 0 ? R.color.list_even : R.color.list_odd;
                view2.setBackgroundColor(ContextCompat.getColor(SingleSpinnerSearch.this.getContext(), i2));
            }
            if (keyPairBoolData.isSelected()) {
                viewHolder.textView.setTypeface(null, 1);
                viewHolder.textView.setTextColor(-1);
                view2.setBackgroundColor(ContextCompat.getColor(SingleSpinnerSearch.this.getContext(), R.color.list_selected));
            } else {
                viewHolder.textView.setTextColor(-12303292);
                viewHolder.textView.setTypeface(null, 0);
                view2.setBackgroundColor(ContextCompat.getColor(SingleSpinnerSearch.this.getContext(), i2));
            }
            view2.setOnClickListener(new View.OnClickListener() { // from class: com.androidbuts.multispinnerfilter.SingleSpinnerSearch$MyAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    SingleSpinnerSearch.MyAdapter.this.m131x6d566f5(i, view3);
                }
            });
            return view2;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: lambda$getView$0$com-androidbuts-multispinnerfilter-SingleSpinnerSearch$MyAdapter, reason: not valid java name */
        public /* synthetic */ void m131x6d566f5(int i, View view) {
            String name = this.arrayList.get(i).getName();
            for (int i2 = 0; i2 < SingleSpinnerSearch.this.items.size(); i2++) {
                ((KeyPairBoolData) SingleSpinnerSearch.this.items.get(i2)).setSelected(false);
                if (((KeyPairBoolData) SingleSpinnerSearch.this.items.get(i2)).getName().equalsIgnoreCase(name)) {
                    ((KeyPairBoolData) SingleSpinnerSearch.this.items.get(i2)).setSelected(true);
                }
            }
            SingleSpinnerSearch.ad.dismiss();
            SingleSpinnerSearch.this.onCancel(SingleSpinnerSearch.ad);
        }

        @Override // android.widget.Filterable
        public Filter getFilter() {
            return new Filter() { // from class: com.androidbuts.multispinnerfilter.SingleSpinnerSearch.MyAdapter.1
                @Override // android.widget.Filter
                protected void publishResults(CharSequence charSequence, Filter.FilterResults filterResults) {
                    MyAdapter.this.arrayList = (List) filterResults.values;
                    MyAdapter.this.notifyDataSetChanged();
                }

                @Override // android.widget.Filter
                protected Filter.FilterResults performFiltering(CharSequence charSequence) {
                    Filter.FilterResults filterResults = new Filter.FilterResults();
                    ArrayList arrayList = new ArrayList();
                    if (MyAdapter.this.mOriginalValues == null) {
                        MyAdapter.this.mOriginalValues = new ArrayList(MyAdapter.this.arrayList);
                    }
                    if (charSequence == null || charSequence.length() == 0) {
                        filterResults.count = MyAdapter.this.mOriginalValues.size();
                        filterResults.values = MyAdapter.this.mOriginalValues;
                    } else {
                        String lowerCase = charSequence.toString().toLowerCase();
                        for (int i = 0; i < MyAdapter.this.mOriginalValues.size(); i++) {
                            Log.i(SingleSpinnerSearch.TAG, "Filter : " + MyAdapter.this.mOriginalValues.get(i).getName() + " -> " + MyAdapter.this.mOriginalValues.get(i).isSelected());
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
            TextView textView;

            private ViewHolder() {
            }
        }
    }
}
