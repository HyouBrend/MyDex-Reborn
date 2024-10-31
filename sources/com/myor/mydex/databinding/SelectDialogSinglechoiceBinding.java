package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import androidx.viewbinding.ViewBinding;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class SelectDialogSinglechoiceBinding implements ViewBinding {
    private final CheckedTextView rootView;
    public final CheckedTextView text1;

    private SelectDialogSinglechoiceBinding(CheckedTextView checkedTextView, CheckedTextView checkedTextView2) {
        this.rootView = checkedTextView;
        this.text1 = checkedTextView2;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CheckedTextView getRoot() {
        return this.rootView;
    }

    public static SelectDialogSinglechoiceBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static SelectDialogSinglechoiceBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.select_dialog_singlechoice, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static SelectDialogSinglechoiceBinding bind(View view) {
        if (view == null) {
            throw new NullPointerException("rootView");
        }
        CheckedTextView checkedTextView = (CheckedTextView) view;
        return new SelectDialogSinglechoiceBinding(checkedTextView, checkedTextView);
    }
}
