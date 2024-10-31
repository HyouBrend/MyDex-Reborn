package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class ItemListDoBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView textViewDetail;
    public final TextView textViewDo;
    public final TextView textViewNo;

    private ItemListDoBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.textViewDetail = textView;
        this.textViewDo = textView2;
        this.textViewNo = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemListDoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemListDoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_list_do, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemListDoBinding bind(View view) {
        int i = R.id.textViewDetail;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textViewDetail);
        if (textView != null) {
            i = R.id.textViewDo;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.textViewDo);
            if (textView2 != null) {
                i = R.id.textViewNo;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.textViewNo);
                if (textView3 != null) {
                    return new ItemListDoBinding((ConstraintLayout) view, textView, textView2, textView3);
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
