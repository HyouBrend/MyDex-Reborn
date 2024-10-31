package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.airbnb.lottie.LottieAnimationView;
import com.ebanx.swipebtn.SwipeButton;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class ActivityInputSpeBinding implements ViewBinding {
    public final LottieAnimationView addAnimation;
    public final CardView cardInputData;
    public final EditText edtNamaDriver;
    public final EditText edtNoSpe;
    public final EditText edtNomorMobil;
    public final EditText edtNomorTlp;
    public final TextView headerInputData;
    private final ConstraintLayout rootView;
    public final SwipeButton swipeSave;
    public final TableLayout tableLayout1;
    public final TableRow tbDriver;
    public final TableRow tbNomorMobil;
    public final TableRow tbNomorTlp;
    public final TableRow tbSpe;

    private ActivityInputSpeBinding(ConstraintLayout constraintLayout, LottieAnimationView lottieAnimationView, CardView cardView, EditText editText, EditText editText2, EditText editText3, EditText editText4, TextView textView, SwipeButton swipeButton, TableLayout tableLayout, TableRow tableRow, TableRow tableRow2, TableRow tableRow3, TableRow tableRow4) {
        this.rootView = constraintLayout;
        this.addAnimation = lottieAnimationView;
        this.cardInputData = cardView;
        this.edtNamaDriver = editText;
        this.edtNoSpe = editText2;
        this.edtNomorMobil = editText3;
        this.edtNomorTlp = editText4;
        this.headerInputData = textView;
        this.swipeSave = swipeButton;
        this.tableLayout1 = tableLayout;
        this.tbDriver = tableRow;
        this.tbNomorMobil = tableRow2;
        this.tbNomorTlp = tableRow3;
        this.tbSpe = tableRow4;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityInputSpeBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static ActivityInputSpeBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.activity_input_spe, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static ActivityInputSpeBinding bind(View view) {
        int i = R.id.addAnimation;
        LottieAnimationView lottieAnimationView = (LottieAnimationView) ViewBindings.findChildViewById(view, R.id.addAnimation);
        if (lottieAnimationView != null) {
            i = R.id.cardInputData;
            CardView cardView = (CardView) ViewBindings.findChildViewById(view, R.id.cardInputData);
            if (cardView != null) {
                i = R.id.edtNamaDriver;
                EditText editText = (EditText) ViewBindings.findChildViewById(view, R.id.edtNamaDriver);
                if (editText != null) {
                    i = R.id.edtNoSpe;
                    EditText editText2 = (EditText) ViewBindings.findChildViewById(view, R.id.edtNoSpe);
                    if (editText2 != null) {
                        i = R.id.edtNomorMobil;
                        EditText editText3 = (EditText) ViewBindings.findChildViewById(view, R.id.edtNomorMobil);
                        if (editText3 != null) {
                            i = R.id.edtNomorTlp;
                            EditText editText4 = (EditText) ViewBindings.findChildViewById(view, R.id.edtNomorTlp);
                            if (editText4 != null) {
                                i = R.id.headerInputData;
                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.headerInputData);
                                if (textView != null) {
                                    i = R.id.swipeSave;
                                    SwipeButton swipeButton = (SwipeButton) ViewBindings.findChildViewById(view, R.id.swipeSave);
                                    if (swipeButton != null) {
                                        i = R.id.tableLayout1;
                                        TableLayout tableLayout = (TableLayout) ViewBindings.findChildViewById(view, R.id.tableLayout1);
                                        if (tableLayout != null) {
                                            i = R.id.tbDriver;
                                            TableRow tableRow = (TableRow) ViewBindings.findChildViewById(view, R.id.tbDriver);
                                            if (tableRow != null) {
                                                i = R.id.tbNomorMobil;
                                                TableRow tableRow2 = (TableRow) ViewBindings.findChildViewById(view, R.id.tbNomorMobil);
                                                if (tableRow2 != null) {
                                                    i = R.id.tbNomorTlp;
                                                    TableRow tableRow3 = (TableRow) ViewBindings.findChildViewById(view, R.id.tbNomorTlp);
                                                    if (tableRow3 != null) {
                                                        i = R.id.tbSpe;
                                                        TableRow tableRow4 = (TableRow) ViewBindings.findChildViewById(view, R.id.tbSpe);
                                                        if (tableRow4 != null) {
                                                            return new ActivityInputSpeBinding((ConstraintLayout) view, lottieAnimationView, cardView, editText, editText2, editText3, editText4, textView, swipeButton, tableLayout, tableRow, tableRow2, tableRow3, tableRow4);
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
            }
        }
        throw new NullPointerException("Missing required view with ID: ".concat(view.getResources().getResourceName(i)));
    }
}
