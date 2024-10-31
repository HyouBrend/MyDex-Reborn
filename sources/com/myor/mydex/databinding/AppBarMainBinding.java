package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class AppBarMainBinding implements ViewBinding {
    private final CoordinatorLayout rootView;
    public final Toolbar toolbar;

    private AppBarMainBinding(CoordinatorLayout coordinatorLayout, Toolbar toolbar) {
        this.rootView = coordinatorLayout;
        this.toolbar = toolbar;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CoordinatorLayout getRoot() {
        return this.rootView;
    }

    public static AppBarMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static AppBarMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.app_bar_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static AppBarMainBinding bind(View view) {
        Toolbar toolbar = (Toolbar) ViewBindings.findChildViewById(view, R.id.toolbar);
        if (toolbar != null) {
            return new AppBarMainBinding((CoordinatorLayout) view, toolbar);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.toolbar)));
    }
}
