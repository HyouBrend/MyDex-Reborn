package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.material.button.MaterialButton;
import com.makeramen.roundedimageview.RoundedImageView;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class ActivityTakePhotoBinding implements ViewBinding {
    public final MaterialButton btnTakePhoto;
    public final CardView cardViewPhoto;
    public final RoundedImageView imgPhoto;
    public final LinearLayout linearLayout;
    public final LinearLayout linearLayout1;
    public final LinearLayout linearLayout2;
    private final ConstraintLayout rootView;
    public final SwipeButton swipeButtonSave;
    public final ConstraintLayout takePhotoActivity;
    public final CardView textCard;
    public final TextView txtAddress;
    public final TextView txtLatitude;
    public final TextView txtLongitude;

    private ActivityTakePhotoBinding(ConstraintLayout constraintLayout, MaterialButton materialButton, CardView cardView, RoundedImageView roundedImageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, SwipeButton swipeButton, ConstraintLayout constraintLayout2, CardView cardView2, TextView textView, TextView textView2, TextView textView3) {
        this.rootView = constraintLayout;
        this.btnTakePhoto = materialButton;
        this.cardViewPhoto = cardView;
        this.imgPhoto = roundedImageView;
        this.linearLayout = linearLayout;
        this.linearLayout1 = linearLayout2;
        this.linearLayout2 = linearLayout3;
        this.swipeButtonSave = swipeButton;
        this.takePhotoActivity = constraintLayout2;
        this.textCard = cardView2;
        this.txtAddress = textView;
        this.txtLatitude = textView2;
        this.txtLongitude = textView3;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityTakePhotoBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityTakePhotoBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_take_photo, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityTakePhotoBinding bind(View view) {
        int i = R.id.btnTakePhoto;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnTakePhoto);
        if (materialButton != null) {
            i = R.id.cardViewPhoto;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.cardViewPhoto);
            if (cardView != null) {
                i = R.id.imgPhoto;
                RoundedImageView roundedImageView = (RoundedImageView) ViewBindings.findChildViewById(view, R.id.imgPhoto);
                if (roundedImageView != null) {
                    i = R.id.linearLayout;
                    LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout);
                    if (linearLayout != null) {
                        i = R.id.linearLayout1;
                        LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout1);
                        if (linearLayout2 != null) {
                            i = R.id.linearLayout2;
                            LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout2);
                            if (linearLayout3 != null) {
                                i = R.id.swipeButtonSave;
                                SwipeButton swipeButton = (SwipeButton) ViewBindings.findChildViewById(view, R.id.swipeButtonSave);
                                if (swipeButton != null) {
                                    ConstraintLayout constraintLayout = (ConstraintLayout) view;
                                    i = R.id.textCard;
                                    CardView cardView2 = (CardView) ViewBindings.findChildViewById(view, R.id.textCard);
                                    if (cardView2 != null) {
                                        i = R.id.txtAddress;
                                        TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.txtAddress);
                                        if (textView != null) {
                                            i = R.id.txtLatitude;
                                            TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.txtLatitude);
                                            if (textView2 != null) {
                                                i = R.id.txtLongitude;
                                                TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.txtLongitude);
                                                if (textView3 != null) {
                                                    return new ActivityTakePhotoBinding(constraintLayout, materialButton, cardView, roundedImageView, linearLayout, linearLayout2, linearLayout3, swipeButton, constraintLayout, cardView2, textView, textView2, textView3);
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
