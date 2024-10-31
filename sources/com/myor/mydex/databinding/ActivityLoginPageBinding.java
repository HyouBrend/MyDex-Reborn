package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.button.MaterialButton;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class ActivityLoginPageBinding implements ViewBinding {
    public final MaterialButton btnLogin;
    public final EditText edtPassword;
    public final ImageView imageView2;
    private final ConstraintLayout rootView;
    public final TextView textView12;
    public final TextView txtSerial;
    public final TextView txtSerialId;

    private ActivityLoginPageBinding(ConstraintLayout constraintLayout, MaterialButton materialButton, EditText editText, ImageView imageView, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.btnLogin = materialButton;
        this.edtPassword = editText;
        this.imageView2 = imageView;
        this.textView12 = textView;
        this.txtSerial = textView2;
        this.txtSerialId = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityLoginPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityLoginPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_login_page, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityLoginPageBinding bind(View view) {
        int i = R.id.btnLogin;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnLogin);
        if (materialButton != null) {
            i = R.id.edtPassword;
            EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.edtPassword);
            if (editText != null) {
                i = R.id.imageView2;
                ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imageView2);
                if (imageView != null) {
                    i = R.id.textView12;
                    TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textView12);
                    if (textView != null) {
                        i = R.id.txtSerial;
                        TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.txtSerial);
                        if (textView2 != null) {
                            i = R.id.txtSerialId;
                            TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.txtSerialId);
                            if (textView3 != null) {
                                return new ActivityLoginPageBinding((ConstraintLayout) view, materialButton, editText, imageView, textView, textView2, textView3);
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
