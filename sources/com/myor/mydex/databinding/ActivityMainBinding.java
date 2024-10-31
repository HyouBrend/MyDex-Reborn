package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.navigation.NavigationView;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final DrawerLayout drawerLayout;
    public final NavigationView navView;
    private final DrawerLayout rootView;

    private ActivityMainBinding(DrawerLayout drawerLayout, DrawerLayout drawerLayout2, NavigationView navigationView) {
        this.rootView = drawerLayout;
        this.drawerLayout = drawerLayout2;
        this.navView = navigationView;
    }

    @Override // androidx.viewbinding.ViewBinding
    public DrawerLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_main, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityMainBinding bind(View view) {
        DrawerLayout drawerLayout = (DrawerLayout) view;
        NavigationView navigationView = (NavigationView) ViewBindings.findChildViewById(view, R.id.nav_view);
        if (navigationView != null) {
            return new ActivityMainBinding(drawerLayout, drawerLayout, navigationView);
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(R.id.nav_view)));
    }
}
