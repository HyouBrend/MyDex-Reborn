package com.myor.mydex.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.material.button.MaterialButton;
import com.myor.mydex.R;

/* loaded from: classes2.dex */
public final class FragmentDashboardPageBinding implements ViewBinding {
    public final MaterialButton btnRefresh;
    public final MaterialButton btnViewDo;
    public final FrameLayout dashboardPage;
    public final View divider;
    public final View divider2;
    public final ImageView imageView;
    public final LinearLayout linearLayout;
    public final LinearLayout linearLayout1;
    public final LinearLayout linearLayout10;
    public final LinearLayout linearLayout11;
    public final LinearLayout linearLayout2;
    public final LinearLayout linearLayout3;
    public final LinearLayout linearLayout4;
    public final LinearLayout linearLayout5;
    public final LinearLayout linearLayout6;
    public final LinearLayout linearLayout7;
    public final LinearLayout linearLayout8;
    public final LinearLayout linearLayout9;
    private final FrameLayout rootView;
    public final SwipeButton swipeButton;
    public final TextView textView;
    public final TextView txtDriver;
    public final TextView txtInfoFinishBongkar;
    public final TextView txtInfoMulaiBongkar;
    public final TextView txtInfoStart;
    public final TextView txtJalur;
    public final TextView txtMobil;
    public final TextView txtSpe;
    public final TextView txtStatus;
    public final TextView txtTanggal;
    public final TextView txtTotalDo;
    public final TextView txtTujuan;
    public final TextView txtView1;
    public final TextView txtView2;
    public final TextView txtView3;
    public final TextView txtView4;
    public final TextView txtView5;
    public final TextView txtView6;
    public final TextView txtView7;
    public final TextView txtViewDriver;

    private FragmentDashboardPageBinding(FrameLayout frameLayout, MaterialButton materialButton, MaterialButton materialButton2, FrameLayout frameLayout2, View view, View view2, ImageView imageView, LinearLayout linearLayout, LinearLayout linearLayout2, LinearLayout linearLayout3, LinearLayout linearLayout4, LinearLayout linearLayout5, LinearLayout linearLayout6, LinearLayout linearLayout7, LinearLayout linearLayout8, LinearLayout linearLayout9, LinearLayout linearLayout10, LinearLayout linearLayout11, LinearLayout linearLayout12, SwipeButton swipeButton, TextView textView, TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8, TextView textView9, TextView textView10, TextView textView11, TextView textView12, TextView textView13, TextView textView14, TextView textView15, TextView textView16, TextView textView17, TextView textView18, TextView textView19, TextView textView20) {
        this.rootView = frameLayout;
        this.btnRefresh = materialButton;
        this.btnViewDo = materialButton2;
        this.dashboardPage = frameLayout2;
        this.divider = view;
        this.divider2 = view2;
        this.imageView = imageView;
        this.linearLayout = linearLayout;
        this.linearLayout1 = linearLayout2;
        this.linearLayout10 = linearLayout3;
        this.linearLayout11 = linearLayout4;
        this.linearLayout2 = linearLayout5;
        this.linearLayout3 = linearLayout6;
        this.linearLayout4 = linearLayout7;
        this.linearLayout5 = linearLayout8;
        this.linearLayout6 = linearLayout9;
        this.linearLayout7 = linearLayout10;
        this.linearLayout8 = linearLayout11;
        this.linearLayout9 = linearLayout12;
        this.swipeButton = swipeButton;
        this.textView = textView;
        this.txtDriver = textView2;
        this.txtInfoFinishBongkar = textView3;
        this.txtInfoMulaiBongkar = textView4;
        this.txtInfoStart = textView5;
        this.txtJalur = textView6;
        this.txtMobil = textView7;
        this.txtSpe = textView8;
        this.txtStatus = textView9;
        this.txtTanggal = textView10;
        this.txtTotalDo = textView11;
        this.txtTujuan = textView12;
        this.txtView1 = textView13;
        this.txtView2 = textView14;
        this.txtView3 = textView15;
        this.txtView4 = textView16;
        this.txtView5 = textView17;
        this.txtView6 = textView18;
        this.txtView7 = textView19;
        this.txtViewDriver = textView20;
    }

    @Override // androidx.viewbinding.ViewBinding
    public FrameLayout getRoot() {
        return this.rootView;
    }

    public static FragmentDashboardPageBinding inflate(LayoutInflater layoutInflater) {
        return inflate(layoutInflater, null, false);
    }

    public static FragmentDashboardPageBinding inflate(LayoutInflater layoutInflater, ViewGroup viewGroup, boolean z) {
        View inflate = layoutInflater.inflate(R.layout.fragment_dashboard_page, viewGroup, false);
        if (z) {
            viewGroup.addView(inflate);
        }
        return bind(inflate);
    }

    public static FragmentDashboardPageBinding bind(View view) {
        int i = R.id.btnRefresh;
        MaterialButton materialButton = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnRefresh);
        if (materialButton != null) {
            i = R.id.btnViewDo;
            MaterialButton materialButton2 = (MaterialButton) ViewBindings.findChildViewById(view, R.id.btnViewDo);
            if (materialButton2 != null) {
                FrameLayout frameLayout = (FrameLayout) view;
                i = R.id.divider;
                View findChildViewById = ViewBindings.findChildViewById(view, R.id.divider);
                if (findChildViewById != null) {
                    i = R.id.divider2;
                    View findChildViewById2 = ViewBindings.findChildViewById(view, R.id.divider2);
                    if (findChildViewById2 != null) {
                        i = R.id.imageView;
                        ImageView imageView = (ImageView) ViewBindings.findChildViewById(view, R.id.imageView);
                        if (imageView != null) {
                            i = R.id.linearLayout;
                            LinearLayout linearLayout = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout);
                            if (linearLayout != null) {
                                i = R.id.linearLayout1;
                                LinearLayout linearLayout2 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout1);
                                if (linearLayout2 != null) {
                                    i = R.id.linearLayout10;
                                    LinearLayout linearLayout3 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout10);
                                    if (linearLayout3 != null) {
                                        i = R.id.linearLayout11;
                                        LinearLayout linearLayout4 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout11);
                                        if (linearLayout4 != null) {
                                            i = R.id.linearLayout2;
                                            LinearLayout linearLayout5 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout2);
                                            if (linearLayout5 != null) {
                                                i = R.id.linearLayout3;
                                                LinearLayout linearLayout6 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout3);
                                                if (linearLayout6 != null) {
                                                    i = R.id.linearLayout4;
                                                    LinearLayout linearLayout7 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout4);
                                                    if (linearLayout7 != null) {
                                                        i = R.id.linearLayout5;
                                                        LinearLayout linearLayout8 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout5);
                                                        if (linearLayout8 != null) {
                                                            i = R.id.linearLayout6;
                                                            LinearLayout linearLayout9 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout6);
                                                            if (linearLayout9 != null) {
                                                                i = R.id.linearLayout7;
                                                                LinearLayout linearLayout10 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout7);
                                                                if (linearLayout10 != null) {
                                                                    i = R.id.linearLayout8;
                                                                    LinearLayout linearLayout11 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout8);
                                                                    if (linearLayout11 != null) {
                                                                        i = R.id.linearLayout9;
                                                                        LinearLayout linearLayout12 = (LinearLayout) ViewBindings.findChildViewById(view, R.id.linearLayout9);
                                                                        if (linearLayout12 != null) {
                                                                            i = R.id.swipeButton;
                                                                            SwipeButton swipeButton = (SwipeButton) ViewBindings.findChildViewById(view, R.id.swipeButton);
                                                                            if (swipeButton != null) {
                                                                                i = R.id.textView;
                                                                                TextView textView = (TextView) ViewBindings.findChildViewById(view, R.id.textView);
                                                                                if (textView != null) {
                                                                                    i = R.id.txtDriver;
                                                                                    TextView textView2 = (TextView) ViewBindings.findChildViewById(view, R.id.txtDriver);
                                                                                    if (textView2 != null) {
                                                                                        i = R.id.txtInfoFinishBongkar;
                                                                                        TextView textView3 = (TextView) ViewBindings.findChildViewById(view, R.id.txtInfoFinishBongkar);
                                                                                        if (textView3 != null) {
                                                                                            i = R.id.txtInfoMulaiBongkar;
                                                                                            TextView textView4 = (TextView) ViewBindings.findChildViewById(view, R.id.txtInfoMulaiBongkar);
                                                                                            if (textView4 != null) {
                                                                                                i = R.id.txtInfoStart;
                                                                                                TextView textView5 = (TextView) ViewBindings.findChildViewById(view, R.id.txtInfoStart);
                                                                                                if (textView5 != null) {
                                                                                                    i = R.id.txtJalur;
                                                                                                    TextView textView6 = (TextView) ViewBindings.findChildViewById(view, R.id.txtJalur);
                                                                                                    if (textView6 != null) {
                                                                                                        i = R.id.txtMobil;
                                                                                                        TextView textView7 = (TextView) ViewBindings.findChildViewById(view, R.id.txtMobil);
                                                                                                        if (textView7 != null) {
                                                                                                            i = R.id.txtSpe;
                                                                                                            TextView textView8 = (TextView) ViewBindings.findChildViewById(view, R.id.txtSpe);
                                                                                                            if (textView8 != null) {
                                                                                                                i = R.id.txtStatus;
                                                                                                                TextView textView9 = (TextView) ViewBindings.findChildViewById(view, R.id.txtStatus);
                                                                                                                if (textView9 != null) {
                                                                                                                    i = R.id.txtTanggal;
                                                                                                                    TextView textView10 = (TextView) ViewBindings.findChildViewById(view, R.id.txtTanggal);
                                                                                                                    if (textView10 != null) {
                                                                                                                        i = R.id.txtTotalDo;
                                                                                                                        TextView textView11 = (TextView) ViewBindings.findChildViewById(view, R.id.txtTotalDo);
                                                                                                                        if (textView11 != null) {
                                                                                                                            i = R.id.txtTujuan;
                                                                                                                            TextView textView12 = (TextView) ViewBindings.findChildViewById(view, R.id.txtTujuan);
                                                                                                                            if (textView12 != null) {
                                                                                                                                i = R.id.txtView1;
                                                                                                                                TextView textView13 = (TextView) ViewBindings.findChildViewById(view, R.id.txtView1);
                                                                                                                                if (textView13 != null) {
                                                                                                                                    i = R.id.txtView2;
                                                                                                                                    TextView textView14 = (TextView) ViewBindings.findChildViewById(view, R.id.txtView2);
                                                                                                                                    if (textView14 != null) {
                                                                                                                                        i = R.id.txtView3;
                                                                                                                                        TextView textView15 = (TextView) ViewBindings.findChildViewById(view, R.id.txtView3);
                                                                                                                                        if (textView15 != null) {
                                                                                                                                            i = R.id.txtView4;
                                                                                                                                            TextView textView16 = (TextView) ViewBindings.findChildViewById(view, R.id.txtView4);
                                                                                                                                            if (textView16 != null) {
                                                                                                                                                i = R.id.txtView5;
                                                                                                                                                TextView textView17 = (TextView) ViewBindings.findChildViewById(view, R.id.txtView5);
                                                                                                                                                if (textView17 != null) {
                                                                                                                                                    i = R.id.txtView6;
                                                                                                                                                    TextView textView18 = (TextView) ViewBindings.findChildViewById(view, R.id.txtView6);
                                                                                                                                                    if (textView18 != null) {
                                                                                                                                                        i = R.id.txtView7;
                                                                                                                                                        TextView textView19 = (TextView) ViewBindings.findChildViewById(view, R.id.txtView7);
                                                                                                                                                        if (textView19 != null) {
                                                                                                                                                            i = R.id.txtViewDriver;
                                                                                                                                                            TextView textView20 = (TextView) ViewBindings.findChildViewById(view, R.id.txtViewDriver);
                                                                                                                                                            if (textView20 != null) {
                                                                                                                                                                return new FragmentDashboardPageBinding(frameLayout, materialButton, materialButton2, frameLayout, findChildViewById, findChildViewById2, imageView, linearLayout, linearLayout2, linearLayout3, linearLayout4, linearLayout5, linearLayout6, linearLayout7, linearLayout8, linearLayout9, linearLayout10, linearLayout11, linearLayout12, swipeButton, textView, textView2, textView3, textView4, textView5, textView6, textView7, textView8, textView9, textView10, textView11, textView12, textView13, textView14, textView15, textView16, textView17, textView18, textView19, textView20);
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
