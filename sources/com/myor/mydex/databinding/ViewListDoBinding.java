package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class ViewListDoBinding implements ViewBinding {
    public final Button btnOk;
    public final LinearLayout linear1;
    public final LinearLayout linear2;
    public final RecyclerView listViewDo;
    private final LinearLayout rootView;

    private ViewListDoBinding(LinearLayout linearLayout, Button button, LinearLayout linearLayout2, LinearLayout linearLayout3, RecyclerView recyclerView) {
        this.rootView = linearLayout;
        this.btnOk = button;
        this.linear1 = linearLayout2;
        this.linear2 = linearLayout3;
        this.listViewDo = recyclerView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static ViewListDoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ViewListDoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.view_list_do, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ViewListDoBinding bind(View view) {
        int i = R.id.btnOk;
        Button button = (Button) ViewBindings.findChildViewById(view, R.id.btnOk);
        if (button != null) {
            i = R.id.linear1;
            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linear1);
            if (linearLayout != null) {
                i = R.id.linear2;
                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linear2);
                if (linearLayout2 != null) {
                    i = R.id.listViewDo;
                    RecyclerView recyclerView = (RecyclerView) ViewBindings.findChildViewById(view, R.id.listViewDo);
                    if (recyclerView != null) {
                        return new ViewListDoBinding((LinearLayout) view, button, linearLayout, linearLayout2, recyclerView);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
