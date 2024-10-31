package com.rey.material.app;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

/* loaded from: classes2.dex */
public class DialogFragment extends androidx.fragment.app.DialogFragment {
    protected static final String ARG_BUILDER = "arg_builder";
    private View.OnClickListener mActionListener = new View.OnClickListener() { // from class: com.rey.material.app.DialogFragment.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (DialogFragment.this.mBuilder == null) {
                return;
            }
            if (view.getId() == Dialog.ACTION_POSITIVE) {
                DialogFragment.this.mBuilder.onPositiveActionClicked(DialogFragment.this);
            } else if (view.getId() == Dialog.ACTION_NEGATIVE) {
                DialogFragment.this.mBuilder.onNegativeActionClicked(DialogFragment.this);
            } else if (view.getId() == Dialog.ACTION_NEUTRAL) {
                DialogFragment.this.mBuilder.onNeutralActionClicked(DialogFragment.this);
            }
        }
    };
    protected Builder mBuilder;

    /* loaded from: classes2.dex */
    public interface Builder {
        Dialog build(Context context);

        void onCancel(DialogInterface dialogInterface);

        void onDismiss(DialogInterface dialogInterface);

        void onNegativeActionClicked(DialogFragment dialogFragment);

        void onNeutralActionClicked(DialogFragment dialogFragment);

        void onPositiveActionClicked(DialogFragment dialogFragment);
    }

    public static DialogFragment newInstance(Builder builder) {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.mBuilder = builder;
        return dialogFragment;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        Builder builder = this.mBuilder;
        Dialog dialog = builder == null ? new Dialog(getActivity()) : builder.build(getActivity());
        dialog.positiveActionClickListener(this.mActionListener).negativeActionClickListener(this.mActionListener).neutralActionClickListener(this.mActionListener);
        return dialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (bundle == null || this.mBuilder != null) {
            return;
        }
        this.mBuilder = (Builder) bundle.getParcelable(ARG_BUILDER);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Builder builder = this.mBuilder;
        if (builder == null || !(builder instanceof Parcelable)) {
            return;
        }
        bundle.putParcelable(ARG_BUILDER, (Parcelable) builder);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        android.app.Dialog dialog = getDialog();
        if (dialog != null && (dialog instanceof Dialog)) {
            ((Dialog) dialog).dismissImmediately();
        }
        super.onDestroyView();
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialogInterface) {
        super.onCancel(dialogInterface);
        this.mBuilder.onCancel(dialogInterface);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        super.onDismiss(dialogInterface);
        this.mBuilder.onDismiss(dialogInterface);
    }
}
