package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class NavHeaderMainBinding implements ViewBinding {
    private final LinearLayout rootView;
    public final TextView txtEmail;
    public final TextView txtName;

    private NavHeaderMainBinding(LinearLayout linearLayout, TextView textView, TextView textView2) {
        this.rootView = linearLayout;
        this.txtEmail = textView;
        this.txtName = textView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public LinearLayout getRoot() {
        return this.rootView;
    }

    public static NavHeaderMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static NavHeaderMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.nav_header_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static NavHeaderMainBinding bind(View view) {
        int i = R.id.txtEmail;
        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.txtEmail);
        if (textView != null) {
            i = R.id.txtName;
            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.txtName);
            if (textView2 != null) {
                return new NavHeaderMainBinding((LinearLayout) view, textView, textView2);
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
