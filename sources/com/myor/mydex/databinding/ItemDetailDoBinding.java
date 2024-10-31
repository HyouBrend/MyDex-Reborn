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
public final class ItemDetailDoBinding implements ViewBinding {
    private final ConstraintLayout rootView;
    public final TextView textViewNo;
    public final TextView textViewPcode;
    public final TextView textViewPcodename;
    public final TextView textViewQty;

    private ItemDetailDoBinding(ConstraintLayout constraintLayout, TextView textView, TextView textView2, TextView textView3, TextView textView4) {
        this.rootView = constraintLayout;
        this.textViewNo = textView;
        this.textViewPcode = textView2;
        this.textViewPcodename = textView3;
        this.textViewQty = textView4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ItemDetailDoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ItemDetailDoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.item_detail_do, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ItemDetailDoBinding bind(View view) {
        int i = R.id.textViewNo;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textViewNo);
        if (textView != null) {
            i = R.id.textViewPcode;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.textViewPcode);
            if (textView2 != null) {
                i = R.id.textViewPcodename;
                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.textViewPcodename);
                if (textView3 != null) {
                    i = R.id.textViewQty;
                    TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.textViewQty);
                    if (textView4 != null) {
                        return new ItemDetailDoBinding((ConstraintLayout) view, textView, textView2, textView3, textView4);
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
