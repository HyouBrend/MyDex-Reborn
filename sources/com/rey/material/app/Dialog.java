package com.rey.material.app;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Handler;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import com.rey.material.R;
import com.rey.material.app.DialogFragment;
import com.rey.material.drawable.BlankDrawable;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;
import com.rey.material.widget.Button;
import com.rey.material.widget.TextView;

/* loaded from: classes2.dex */
public class Dialog extends android.app.Dialog {
    protected int mActionHeight;
    protected int mActionMinWidth;
    protected int mActionOuterHeight;
    protected int mActionOuterPadding;
    protected int mActionPadding;
    private boolean mCancelable;
    private boolean mCanceledOnTouchOutside;
    private DialogCardView mCardView;
    private ContainerFrameLayout mContainer;
    private View mContent;
    protected int mContentPadding;
    protected int mDialogHorizontalPadding;
    protected int mDialogVerticalPadding;
    private final Runnable mDismissAction;
    private final Handler mHandler;
    protected int mInAnimationId;
    private boolean mLayoutActionVertical;
    private int mLayoutHeight;
    private int mLayoutWidth;
    private int mMaxHeight;
    private int mMaxWidth;
    protected Button mNegativeAction;
    protected Button mNeutralAction;
    protected int mOutAnimationId;
    protected Button mPositiveAction;
    protected TextView mTitle;
    public static final int TITLE = ViewUtil.generateViewId();
    public static final int ACTION_POSITIVE = ViewUtil.generateViewId();
    public static final int ACTION_NEGATIVE = ViewUtil.generateViewId();
    public static final int ACTION_NEUTRAL = ViewUtil.generateViewId();

    protected void onCreate() {
    }

    public Dialog(Context context) {
        this(context, R.style.Material_App_Dialog_Light);
    }

    public Dialog(Context context, int i) {
        super(context, i);
        this.mLayoutWidth = -2;
        this.mLayoutHeight = -2;
        this.mHandler = new Handler();
        this.mDismissAction = new Runnable() { // from class: com.rey.material.app.Dialog.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    Dialog.super.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
        };
        this.mLayoutActionVertical = false;
        this.mCancelable = true;
        this.mCanceledOnTouchOutside = true;
        requestWindowFeature(1);
        getWindow().setBackgroundDrawable(BlankDrawable.getInstance());
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -1;
        attributes.windowAnimations = R.style.DialogNoAnimation;
        getWindow().setAttributes(attributes);
        init(context, i);
    }

    private void init(Context context, int i) {
        this.mContentPadding = ThemeUtil.dpToPx(context, 24);
        this.mActionMinWidth = ThemeUtil.dpToPx(context, 64);
        this.mActionHeight = ThemeUtil.dpToPx(context, 36);
        this.mActionOuterHeight = ThemeUtil.dpToPx(context, 48);
        this.mActionPadding = ThemeUtil.dpToPx(context, 8);
        this.mActionOuterPadding = ThemeUtil.dpToPx(context, 16);
        this.mDialogHorizontalPadding = ThemeUtil.dpToPx(context, 40);
        this.mDialogVerticalPadding = ThemeUtil.dpToPx(context, 24);
        this.mCardView = new DialogCardView(context);
        this.mContainer = new ContainerFrameLayout(context);
        this.mTitle = new TextView(context);
        this.mPositiveAction = new Button(context);
        this.mNegativeAction = new Button(context);
        this.mNeutralAction = new Button(context);
        this.mCardView.setPreventCornerOverlap(false);
        this.mCardView.setUseCompatPadding(true);
        this.mTitle.setId(TITLE);
        this.mTitle.setGravity(GravityCompat.START);
        TextView textView = this.mTitle;
        int i2 = this.mContentPadding;
        textView.setPadding(i2, i2, i2, i2 - this.mActionPadding);
        this.mPositiveAction.setId(ACTION_POSITIVE);
        Button button = this.mPositiveAction;
        int i3 = this.mActionPadding;
        button.setPadding(i3, 0, i3, 0);
        this.mPositiveAction.setBackgroundResource(0);
        this.mNegativeAction.setId(ACTION_NEGATIVE);
        Button button2 = this.mNegativeAction;
        int i4 = this.mActionPadding;
        button2.setPadding(i4, 0, i4, 0);
        this.mNegativeAction.setBackgroundResource(0);
        this.mNeutralAction.setId(ACTION_NEUTRAL);
        Button button3 = this.mNeutralAction;
        int i5 = this.mActionPadding;
        button3.setPadding(i5, 0, i5, 0);
        this.mNeutralAction.setBackgroundResource(0);
        this.mContainer.addView(this.mCardView);
        this.mCardView.addView(this.mTitle);
        this.mCardView.addView(this.mPositiveAction);
        this.mCardView.addView(this.mNegativeAction);
        this.mCardView.addView(this.mNeutralAction);
        backgroundColor(ThemeUtil.windowBackground(context, -1));
        elevation(ThemeUtil.dpToPx(context, 4));
        cornerRadius(ThemeUtil.dpToPx(context, 2));
        dimAmount(0.5f);
        layoutDirection(3);
        titleTextAppearance(R.style.TextAppearance_AppCompat_Title);
        actionTextAppearance(R.style.TextAppearance_AppCompat_Button);
        dividerColor(503316480);
        dividerHeight(ThemeUtil.dpToPx(context, 1));
        cancelable(true);
        canceledOnTouchOutside(true);
        clearContent();
        onCreate();
        applyStyle(i);
        super.setContentView(this.mContainer);
    }

    public Dialog applyStyle(int i) {
        TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(i, R.styleable.Dialog);
        int i2 = this.mLayoutWidth;
        int i3 = this.mLayoutHeight;
        int indexCount = obtainStyledAttributes.getIndexCount();
        ColorStateList colorStateList = null;
        ColorStateList colorStateList2 = null;
        ColorStateList colorStateList3 = null;
        ColorStateList colorStateList4 = null;
        int i4 = 0;
        boolean z = false;
        int i5 = 0;
        boolean z2 = false;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        int i11 = 0;
        int i12 = 0;
        int i13 = 0;
        int i14 = 0;
        int i15 = 0;
        int i16 = 0;
        int i17 = 0;
        int i18 = 0;
        while (i4 < indexCount) {
            int index = obtainStyledAttributes.getIndex(i4);
            int i19 = indexCount;
            ColorStateList colorStateList5 = colorStateList4;
            ColorStateList colorStateList6 = colorStateList3;
            if (index == R.styleable.Dialog_android_layout_width) {
                i2 = obtainStyledAttributes.getLayoutDimension(index, -2);
            } else if (index == R.styleable.Dialog_android_layout_height) {
                i3 = obtainStyledAttributes.getLayoutDimension(index, -2);
            } else {
                if (index == R.styleable.Dialog_di_maxWidth) {
                    maxWidth(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                } else if (index == R.styleable.Dialog_di_maxHeight) {
                    maxHeight(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                } else if (index == R.styleable.Dialog_di_dimAmount) {
                    dimAmount(obtainStyledAttributes.getFloat(index, 0.0f));
                } else if (index == R.styleable.Dialog_di_backgroundColor) {
                    backgroundColor(obtainStyledAttributes.getColor(index, 0));
                } else if (index == R.styleable.Dialog_di_maxElevation) {
                    maxElevation(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                } else if (index == R.styleable.Dialog_di_elevation) {
                    elevation(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                } else if (index == R.styleable.Dialog_di_cornerRadius) {
                    cornerRadius(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                } else if (index == R.styleable.Dialog_di_layoutDirection) {
                    layoutDirection(obtainStyledAttributes.getInteger(index, 0));
                } else if (index == R.styleable.Dialog_di_titleTextAppearance) {
                    i5 = obtainStyledAttributes.getResourceId(index, 0);
                } else {
                    if (index == R.styleable.Dialog_di_titleTextColor) {
                        i6 = obtainStyledAttributes.getColor(index, 0);
                        colorStateList4 = colorStateList5;
                        colorStateList3 = colorStateList6;
                        z2 = true;
                    } else if (index == R.styleable.Dialog_di_actionBackground) {
                        i7 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_actionRipple) {
                        i8 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_actionTextAppearance) {
                        i9 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_actionTextColor) {
                        colorStateList = obtainStyledAttributes.getColorStateList(index);
                    } else if (index == R.styleable.Dialog_di_positiveActionBackground) {
                        i10 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_positiveActionRipple) {
                        i11 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_positiveActionTextAppearance) {
                        i12 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_positiveActionTextColor) {
                        colorStateList2 = obtainStyledAttributes.getColorStateList(index);
                    } else if (index == R.styleable.Dialog_di_negativeActionBackground) {
                        i13 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_negativeActionRipple) {
                        i14 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_negativeActionTextAppearance) {
                        i15 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_negativeActionTextColor) {
                        colorStateList3 = obtainStyledAttributes.getColorStateList(index);
                        colorStateList4 = colorStateList5;
                    } else if (index == R.styleable.Dialog_di_neutralActionBackground) {
                        i16 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_neutralActionRipple) {
                        i17 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_neutralActionTextAppearance) {
                        i18 = obtainStyledAttributes.getResourceId(index, 0);
                    } else if (index == R.styleable.Dialog_di_neutralActionTextColor) {
                        colorStateList4 = obtainStyledAttributes.getColorStateList(index);
                        colorStateList3 = colorStateList6;
                    } else if (index == R.styleable.Dialog_di_inAnimation) {
                        inAnimation(obtainStyledAttributes.getResourceId(index, 0));
                    } else if (index == R.styleable.Dialog_di_outAnimation) {
                        outAnimation(obtainStyledAttributes.getResourceId(index, 0));
                    } else if (index == R.styleable.Dialog_di_dividerColor) {
                        dividerColor(obtainStyledAttributes.getColor(index, 0));
                    } else if (index == R.styleable.Dialog_di_dividerHeight) {
                        dividerHeight(obtainStyledAttributes.getDimensionPixelOffset(index, 0));
                    } else if (index == R.styleable.Dialog_di_cancelable) {
                        cancelable(obtainStyledAttributes.getBoolean(index, true));
                    } else if (index == R.styleable.Dialog_di_canceledOnTouchOutside) {
                        canceledOnTouchOutside(obtainStyledAttributes.getBoolean(index, true));
                    }
                    i4++;
                    indexCount = i19;
                }
                colorStateList4 = colorStateList5;
                colorStateList3 = colorStateList6;
                i4++;
                indexCount = i19;
            }
            colorStateList4 = colorStateList5;
            colorStateList3 = colorStateList6;
            z = true;
            i4++;
            indexCount = i19;
        }
        ColorStateList colorStateList7 = colorStateList3;
        ColorStateList colorStateList8 = colorStateList4;
        obtainStyledAttributes.recycle();
        if (z) {
            layoutParams(i2, i3);
        }
        if (i5 != 0) {
            titleTextAppearance(i5);
        }
        if (z2) {
            titleColor(i6);
        }
        if (i7 != 0) {
            actionBackground(i7);
        }
        int i20 = i8;
        if (i20 != 0) {
            actionRipple(i20);
        }
        int i21 = i9;
        if (i21 != 0) {
            actionTextAppearance(i21);
        }
        if (colorStateList != null) {
            actionTextColor(colorStateList);
        }
        int i22 = i10;
        if (i22 != 0) {
            positiveActionBackground(i22);
        }
        int i23 = i11;
        if (i23 != 0) {
            positiveActionRipple(i23);
        }
        int i24 = i12;
        if (i24 != 0) {
            positiveActionTextAppearance(i24);
        }
        if (colorStateList2 != null) {
            positiveActionTextColor(colorStateList2);
        }
        int i25 = i13;
        if (i25 != 0) {
            negativeActionBackground(i25);
        }
        int i26 = i14;
        if (i26 != 0) {
            negativeActionRipple(i26);
        }
        int i27 = i15;
        if (i27 != 0) {
            negativeActionTextAppearance(i27);
        }
        if (colorStateList7 != null) {
            negativeActionTextColor(colorStateList7);
        }
        int i28 = i16;
        if (i28 != 0) {
            neutralActionBackground(i28);
        }
        int i29 = i17;
        if (i29 != 0) {
            neutralActionRipple(i29);
        }
        int i30 = i18;
        if (i30 != 0) {
            neutralActionTextAppearance(i30);
        }
        if (colorStateList8 != null) {
            neutralActionTextColor(colorStateList8);
        }
        return this;
    }

    public Dialog clearContent() {
        title(0);
        positiveAction(0);
        positiveActionClickListener(null);
        negativeAction(0);
        negativeActionClickListener(null);
        neutralAction(0);
        neutralActionClickListener(null);
        contentView((View) null);
        return this;
    }

    public Dialog layoutParams(int i, int i2) {
        this.mLayoutWidth = i;
        this.mLayoutHeight = i2;
        return this;
    }

    public Dialog maxWidth(int i) {
        this.mMaxWidth = i;
        return this;
    }

    public Dialog maxHeight(int i) {
        this.mMaxHeight = i;
        return this;
    }

    public Dialog dimAmount(float f) {
        Window window = getWindow();
        if (f > 0.0f) {
            window.addFlags(2);
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.dimAmount = f;
            window.setAttributes(attributes);
        } else {
            window.clearFlags(2);
        }
        return this;
    }

    public Dialog backgroundColor(int i) {
        this.mCardView.setCardBackgroundColor(i);
        return this;
    }

    public Dialog elevation(float f) {
        if (this.mCardView.getMaxCardElevation() < f) {
            this.mCardView.setMaxCardElevation(f);
        }
        this.mCardView.setCardElevation(f);
        return this;
    }

    public Dialog maxElevation(float f) {
        this.mCardView.setMaxCardElevation(f);
        return this;
    }

    public Dialog cornerRadius(float f) {
        this.mCardView.setRadius(f);
        return this;
    }

    public Dialog dividerColor(int i) {
        this.mCardView.setDividerColor(i);
        return this;
    }

    public Dialog dividerHeight(int i) {
        this.mCardView.setDividerHeight(i);
        return this;
    }

    public Dialog title(CharSequence charSequence) {
        this.mTitle.setText(charSequence);
        this.mTitle.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        return this;
    }

    public Dialog title(int i) {
        return title(i == 0 ? null : getContext().getResources().getString(i));
    }

    @Override // android.app.Dialog
    public void setTitle(CharSequence charSequence) {
        title(charSequence);
    }

    @Override // android.app.Dialog
    public void setTitle(int i) {
        title(i);
    }

    public Dialog titleColor(int i) {
        this.mTitle.setTextColor(i);
        return this;
    }

    public Dialog titleTextAppearance(int i) {
        this.mTitle.setTextAppearance(getContext(), i);
        return this;
    }

    public Dialog actionBackground(int i) {
        positiveActionBackground(i);
        negativeActionBackground(i);
        neutralActionBackground(i);
        return this;
    }

    public Dialog actionBackground(Drawable drawable) {
        positiveActionBackground(drawable);
        negativeActionBackground(drawable);
        neutralActionBackground(drawable);
        return this;
    }

    public Dialog actionRipple(int i) {
        positiveActionRipple(i);
        negativeActionRipple(i);
        neutralActionRipple(i);
        return this;
    }

    public Dialog actionTextAppearance(int i) {
        positiveActionTextAppearance(i);
        negativeActionTextAppearance(i);
        neutralActionTextAppearance(i);
        return this;
    }

    public Dialog actionTextColor(ColorStateList colorStateList) {
        positiveActionTextColor(colorStateList);
        negativeActionTextColor(colorStateList);
        neutralActionTextColor(colorStateList);
        return this;
    }

    public Dialog actionTextColor(int i) {
        positiveActionTextColor(i);
        negativeActionTextColor(i);
        neutralActionTextColor(i);
        return this;
    }

    public Dialog positiveAction(CharSequence charSequence) {
        this.mPositiveAction.setText(charSequence);
        this.mPositiveAction.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        return this;
    }

    public Dialog positiveAction(int i) {
        return positiveAction(i == 0 ? null : getContext().getResources().getString(i));
    }

    public Dialog positiveActionBackground(Drawable drawable) {
        ViewUtil.setBackground(this.mPositiveAction, drawable);
        return this;
    }

    public Dialog positiveActionBackground(int i) {
        return positiveActionBackground(i == 0 ? null : getContext().getResources().getDrawable(i));
    }

    public Dialog positiveActionRipple(int i) {
        return positiveActionBackground(new RippleDrawable.Builder(getContext(), i).build());
    }

    public Dialog positiveActionTextAppearance(int i) {
        this.mPositiveAction.setTextAppearance(getContext(), i);
        return this;
    }

    public Dialog positiveActionTextColor(ColorStateList colorStateList) {
        this.mPositiveAction.setTextColor(colorStateList);
        return this;
    }

    public Dialog positiveActionTextColor(int i) {
        this.mPositiveAction.setTextColor(i);
        return this;
    }

    public Dialog positiveActionClickListener(View.OnClickListener onClickListener) {
        this.mPositiveAction.setOnClickListener(onClickListener);
        return this;
    }

    public Dialog negativeAction(CharSequence charSequence) {
        this.mNegativeAction.setText(charSequence);
        this.mNegativeAction.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        return this;
    }

    public Dialog negativeAction(int i) {
        return negativeAction(i == 0 ? null : getContext().getResources().getString(i));
    }

    public Dialog negativeActionBackground(Drawable drawable) {
        ViewUtil.setBackground(this.mNegativeAction, drawable);
        return this;
    }

    public Dialog negativeActionBackground(int i) {
        return negativeActionBackground(i == 0 ? null : getContext().getResources().getDrawable(i));
    }

    public Dialog negativeActionRipple(int i) {
        return negativeActionBackground(new RippleDrawable.Builder(getContext(), i).build());
    }

    public Dialog negativeActionTextAppearance(int i) {
        this.mNegativeAction.setTextAppearance(getContext(), i);
        return this;
    }

    public Dialog negativeActionTextColor(ColorStateList colorStateList) {
        this.mNegativeAction.setTextColor(colorStateList);
        return this;
    }

    public Dialog negativeActionTextColor(int i) {
        this.mNegativeAction.setTextColor(i);
        return this;
    }

    public Dialog negativeActionClickListener(View.OnClickListener onClickListener) {
        this.mNegativeAction.setOnClickListener(onClickListener);
        return this;
    }

    public Dialog neutralAction(CharSequence charSequence) {
        this.mNeutralAction.setText(charSequence);
        this.mNeutralAction.setVisibility(TextUtils.isEmpty(charSequence) ? 8 : 0);
        return this;
    }

    public Dialog neutralAction(int i) {
        return neutralAction(i == 0 ? null : getContext().getResources().getString(i));
    }

    public Dialog neutralActionBackground(Drawable drawable) {
        ViewUtil.setBackground(this.mNeutralAction, drawable);
        return this;
    }

    public Dialog neutralActionBackground(int i) {
        return neutralActionBackground(i == 0 ? null : getContext().getResources().getDrawable(i));
    }

    public Dialog neutralActionRipple(int i) {
        return neutralActionBackground(new RippleDrawable.Builder(getContext(), i).build());
    }

    public Dialog neutralActionTextAppearance(int i) {
        this.mNeutralAction.setTextAppearance(getContext(), i);
        return this;
    }

    public Dialog neutralActionTextColor(ColorStateList colorStateList) {
        this.mNeutralAction.setTextColor(colorStateList);
        return this;
    }

    public Dialog neutralActionTextColor(int i) {
        this.mNeutralAction.setTextColor(i);
        return this;
    }

    public Dialog neutralActionClickListener(View.OnClickListener onClickListener) {
        this.mNeutralAction.setOnClickListener(onClickListener);
        return this;
    }

    public Dialog layoutDirection(int i) {
        ViewCompat.setLayoutDirection(this.mCardView, i);
        return this;
    }

    public Dialog inAnimation(int i) {
        this.mInAnimationId = i;
        return this;
    }

    public Dialog outAnimation(int i) {
        this.mOutAnimationId = i;
        return this;
    }

    public Dialog showDivider(boolean z) {
        this.mCardView.setShowDivider(z);
        return this;
    }

    public Dialog contentView(View view) {
        View view2 = this.mContent;
        if (view2 != view) {
            if (view2 != null) {
                this.mCardView.removeView(view2);
            }
            this.mContent = view;
        }
        View view3 = this.mContent;
        if (view3 != null) {
            this.mCardView.addView(view3);
        }
        return this;
    }

    public Dialog contentView(int i) {
        return i == 0 ? this : contentView(LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null));
    }

    public Dialog cancelable(boolean z) {
        super.setCancelable(z);
        this.mCancelable = z;
        return this;
    }

    public Dialog canceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        this.mCanceledOnTouchOutside = z;
        return this;
    }

    public Dialog contentMargin(int i) {
        this.mCardView.setContentMargin(i);
        return this;
    }

    public Dialog contentMargin(int i, int i2, int i3, int i4) {
        this.mCardView.setContentMargin(i, i2, i3, i4);
        return this;
    }

    @Override // android.app.Dialog
    public void setCancelable(boolean z) {
        cancelable(z);
    }

    @Override // android.app.Dialog
    public void setCanceledOnTouchOutside(boolean z) {
        canceledOnTouchOutside(z);
    }

    @Override // android.app.Dialog
    public void setContentView(View view) {
        contentView(view);
    }

    @Override // android.app.Dialog
    public void setContentView(int i) {
        contentView(i);
    }

    @Override // android.app.Dialog
    public void setContentView(View view, ViewGroup.LayoutParams layoutParams) {
        contentView(view);
    }

    @Override // android.app.Dialog
    public void addContentView(View view, ViewGroup.LayoutParams layoutParams) {
        contentView(view);
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        this.mCardView.setVisibility(0);
        if (this.mInAnimationId != 0) {
            this.mCardView.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() { // from class: com.rey.material.app.Dialog.2
                @Override // android.view.ViewTreeObserver.OnPreDrawListener
                public boolean onPreDraw() {
                    Dialog.this.mCardView.getViewTreeObserver().removeOnPreDrawListener(this);
                    Dialog.this.mCardView.startAnimation(AnimationUtils.loadAnimation(Dialog.this.mCardView.getContext(), Dialog.this.mInAnimationId));
                    return false;
                }
            });
        }
    }

    public void dismissImmediately() {
        super.dismiss();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mDismissAction);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (isShowing()) {
            if (this.mOutAnimationId != 0) {
                Animation loadAnimation = AnimationUtils.loadAnimation(this.mContainer.getContext(), this.mOutAnimationId);
                loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.rey.material.app.Dialog.3
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        Dialog.this.mCardView.setVisibility(8);
                        Dialog.this.mHandler.post(Dialog.this.mDismissAction);
                    }
                });
                this.mCardView.startAnimation(loadAnimation);
                return;
            }
            this.mHandler.post(this.mDismissAction);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ContainerFrameLayout extends FrameLayout {
        private boolean mClickOutside;

        public ContainerFrameLayout(Context context) {
            super(context);
            this.mClickOutside = false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            Dialog.this.mCardView.measure(i, i2);
            setMeasuredDimension(size, size2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int measuredWidth = ((i3 - i) - Dialog.this.mCardView.getMeasuredWidth()) / 2;
            int measuredHeight = ((i4 - i2) - Dialog.this.mCardView.getMeasuredHeight()) / 2;
            Dialog.this.mCardView.layout(measuredWidth, measuredHeight, Dialog.this.mCardView.getMeasuredWidth() + measuredWidth, Dialog.this.mCardView.getMeasuredHeight() + measuredHeight);
        }

        private boolean isOutsideDialog(float f, float f2) {
            return f < ((float) (Dialog.this.mCardView.getLeft() + Dialog.this.mCardView.getPaddingLeft())) || f > ((float) (Dialog.this.mCardView.getRight() - Dialog.this.mCardView.getPaddingRight())) || f2 < ((float) (Dialog.this.mCardView.getTop() + Dialog.this.mCardView.getPaddingTop())) || f2 > ((float) (Dialog.this.mCardView.getBottom() - Dialog.this.mCardView.getPaddingBottom()));
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent motionEvent) {
            if (super.onTouchEvent(motionEvent)) {
                return true;
            }
            int action = motionEvent.getAction();
            if (action == 0) {
                if (!isOutsideDialog(motionEvent.getX(), motionEvent.getY())) {
                    return false;
                }
                this.mClickOutside = true;
                return true;
            }
            if (action != 1) {
                if (action == 2) {
                    return this.mClickOutside;
                }
                if (action != 3) {
                    return false;
                }
                this.mClickOutside = false;
                return false;
            }
            if (!this.mClickOutside || !isOutsideDialog(motionEvent.getX(), motionEvent.getY())) {
                return false;
            }
            this.mClickOutside = false;
            if (Dialog.this.mCancelable && Dialog.this.mCanceledOnTouchOutside) {
                Dialog.this.dismiss();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class DialogCardView extends CardView {
        private int mContentMarginBottom;
        private int mContentMarginLeft;
        private int mContentMarginRight;
        private int mContentMarginTop;
        private Paint mDividerPaint;
        private float mDividerPos;
        private boolean mIsRtl;
        private boolean mShowDivider;

        public DialogCardView(Context context) {
            super(context);
            this.mDividerPos = -1.0f;
            this.mShowDivider = false;
            this.mIsRtl = false;
            Paint paint = new Paint(1);
            this.mDividerPaint = paint;
            paint.setStyle(Paint.Style.STROKE);
            setWillNotDraw(false);
        }

        public void setContentMargin(int i) {
            setContentMargin(i, i, i, i);
        }

        public void setContentMargin(int i, int i2, int i3, int i4) {
            this.mContentMarginLeft = i;
            this.mContentMarginTop = i2;
            this.mContentMarginRight = i3;
            this.mContentMarginBottom = i4;
        }

        public void setDividerColor(int i) {
            this.mDividerPaint.setColor(i);
            invalidate();
        }

        public void setDividerHeight(int i) {
            this.mDividerPaint.setStrokeWidth(i);
            invalidate();
        }

        public void setShowDivider(boolean z) {
            if (this.mShowDivider != z) {
                this.mShowDivider = z;
                invalidate();
            }
        }

        @Override // android.view.View
        public void onRtlPropertiesChanged(int i) {
            boolean z = i == 1;
            if (this.mIsRtl != z) {
                this.mIsRtl = z;
                if (Build.VERSION.SDK_INT >= 17) {
                    int i2 = this.mIsRtl ? 4 : 3;
                    Dialog.this.mTitle.setTextDirection(i2);
                    Dialog.this.mPositiveAction.setTextDirection(i2);
                    Dialog.this.mNegativeAction.setTextDirection(i2);
                    Dialog.this.mNeutralAction.setTextDirection(i2);
                }
                requestLayout();
            }
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // androidx.cardview.widget.CardView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int i, int i2) {
            int i3;
            int i4;
            int i5;
            int i6;
            int i7;
            int i8;
            int i9;
            int i10;
            int i11;
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            int max = Math.max(Dialog.this.mDialogHorizontalPadding, Dialog.this.mCardView.getPaddingLeft());
            int max2 = Math.max(Dialog.this.mDialogHorizontalPadding, Dialog.this.mCardView.getPaddingRight());
            int max3 = Math.max(Dialog.this.mDialogVerticalPadding, Dialog.this.mCardView.getPaddingTop());
            int max4 = Math.max(Dialog.this.mDialogVerticalPadding, Dialog.this.mCardView.getPaddingBottom());
            int i12 = (size - max) - max2;
            if (Dialog.this.mMaxWidth > 0) {
                i12 = Math.min(i12, Dialog.this.mMaxWidth);
            }
            int i13 = (size2 - max3) - max4;
            if (Dialog.this.mMaxHeight > 0) {
                i13 = Math.min(i13, Dialog.this.mMaxHeight);
            }
            int i14 = Dialog.this.mLayoutWidth == -1 ? i12 : Dialog.this.mLayoutWidth;
            int i15 = Dialog.this.mLayoutHeight == -1 ? i13 : Dialog.this.mLayoutHeight;
            if (Dialog.this.mTitle.getVisibility() == 0) {
                Dialog.this.mTitle.measure(View.MeasureSpec.makeMeasureSpec(i14 == -2 ? i12 : i14, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(i13, Integer.MIN_VALUE));
                i3 = Dialog.this.mTitle.getMeasuredWidth();
                i4 = Dialog.this.mTitle.getMeasuredHeight();
            } else {
                i3 = 0;
                i4 = 0;
            }
            if (Dialog.this.mContent != null) {
                Dialog.this.mContent.measure(View.MeasureSpec.makeMeasureSpec(((i14 == -2 ? i12 : i14) - this.mContentMarginLeft) - this.mContentMarginRight, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec((i13 - this.mContentMarginTop) - this.mContentMarginBottom, Integer.MIN_VALUE));
                i5 = Dialog.this.mContent.getMeasuredWidth();
                i6 = Dialog.this.mContent.getMeasuredHeight();
            } else {
                i5 = 0;
                i6 = 0;
            }
            if (Dialog.this.mPositiveAction.getVisibility() == 0) {
                int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
                int makeMeasureSpec2 = View.MeasureSpec.makeMeasureSpec(Dialog.this.mActionHeight, 1073741824);
                Dialog.this.mPositiveAction.measure(makeMeasureSpec, makeMeasureSpec2);
                i7 = Dialog.this.mPositiveAction.getMeasuredWidth();
                if (i7 < Dialog.this.mActionMinWidth) {
                    Dialog.this.mPositiveAction.measure(View.MeasureSpec.makeMeasureSpec(Dialog.this.mActionMinWidth, 1073741824), makeMeasureSpec2);
                    i7 = Dialog.this.mActionMinWidth;
                }
                i8 = 1;
            } else {
                i7 = 0;
                i8 = 0;
            }
            if (Dialog.this.mNegativeAction.getVisibility() == 0) {
                int makeMeasureSpec3 = View.MeasureSpec.makeMeasureSpec(0, 0);
                int makeMeasureSpec4 = View.MeasureSpec.makeMeasureSpec(Dialog.this.mActionHeight, 1073741824);
                Dialog.this.mNegativeAction.measure(makeMeasureSpec3, makeMeasureSpec4);
                i9 = Dialog.this.mNegativeAction.getMeasuredWidth();
                if (i9 < Dialog.this.mActionMinWidth) {
                    Dialog.this.mNegativeAction.measure(View.MeasureSpec.makeMeasureSpec(Dialog.this.mActionMinWidth, 1073741824), makeMeasureSpec4);
                    i9 = Dialog.this.mActionMinWidth;
                }
                i8++;
            } else {
                i9 = 0;
            }
            if (Dialog.this.mNeutralAction.getVisibility() == 0) {
                int makeMeasureSpec5 = View.MeasureSpec.makeMeasureSpec(0, 0);
                int makeMeasureSpec6 = View.MeasureSpec.makeMeasureSpec(Dialog.this.mActionHeight, 1073741824);
                Dialog.this.mNeutralAction.measure(makeMeasureSpec5, makeMeasureSpec6);
                i10 = Dialog.this.mNeutralAction.getMeasuredWidth();
                if (i10 < Dialog.this.mActionMinWidth) {
                    Dialog.this.mNeutralAction.measure(View.MeasureSpec.makeMeasureSpec(Dialog.this.mActionMinWidth, 1073741824), makeMeasureSpec6);
                    i10 = Dialog.this.mActionMinWidth;
                }
                i8++;
            } else {
                i10 = 0;
            }
            int max5 = i7 + i9 + i10 + (Dialog.this.mActionOuterPadding * 2) + (Dialog.this.mActionPadding * Math.max(0, i8 - 1));
            if (i14 == -2) {
                i14 = Math.min(i12, Math.max(i3, Math.max(i5 + this.mContentMarginLeft + this.mContentMarginRight, max5)));
            }
            Dialog.this.mLayoutActionVertical = max5 > i14;
            int i16 = i4 + (i8 > 0 ? Dialog.this.mActionPadding : 0) + this.mContentMarginTop + this.mContentMarginBottom;
            if (Dialog.this.mLayoutActionVertical) {
                i11 = i16 + (Dialog.this.mActionOuterHeight * i8);
            } else {
                i11 = i16 + (i8 > 0 ? Dialog.this.mActionOuterHeight : 0);
            }
            if (i15 == -2) {
                i15 = Math.min(i13, i6 + i11);
            }
            if (Dialog.this.mContent != null) {
                Dialog.this.mContent.measure(View.MeasureSpec.makeMeasureSpec((i14 - this.mContentMarginLeft) - this.mContentMarginRight, 1073741824), View.MeasureSpec.makeMeasureSpec(i15 - i11, 1073741824));
            }
            setMeasuredDimension(i14 + getPaddingLeft() + getPaddingRight(), i15 + getPaddingTop() + getPaddingBottom());
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            int i5;
            int i6 = i4 - i2;
            int paddingLeft = getPaddingLeft() + 0;
            int paddingTop = getPaddingTop() + 0;
            int paddingRight = (i3 - i) - getPaddingRight();
            int paddingBottom = i6 - getPaddingBottom();
            if (Dialog.this.mTitle.getVisibility() == 0) {
                if (this.mIsRtl) {
                    Dialog.this.mTitle.layout(paddingRight - Dialog.this.mTitle.getMeasuredWidth(), paddingTop, paddingRight, Dialog.this.mTitle.getMeasuredHeight() + paddingTop);
                } else {
                    Dialog.this.mTitle.layout(paddingLeft, paddingTop, Dialog.this.mTitle.getMeasuredWidth() + paddingLeft, Dialog.this.mTitle.getMeasuredHeight() + paddingTop);
                }
                paddingTop += Dialog.this.mTitle.getMeasuredHeight();
            }
            boolean z2 = Dialog.this.mNeutralAction.getVisibility() == 0 || Dialog.this.mNegativeAction.getVisibility() == 0 || Dialog.this.mPositiveAction.getVisibility() == 0;
            if (z2) {
                paddingBottom -= Dialog.this.mActionPadding;
            }
            int i7 = (Dialog.this.mActionOuterHeight - Dialog.this.mActionHeight) / 2;
            if (z2) {
                if (Dialog.this.mLayoutActionVertical) {
                    if (Dialog.this.mNeutralAction.getVisibility() == 0) {
                        Dialog.this.mNeutralAction.layout((paddingRight - Dialog.this.mActionOuterPadding) - Dialog.this.mNeutralAction.getMeasuredWidth(), (paddingBottom - Dialog.this.mActionOuterHeight) + i7, paddingRight - Dialog.this.mActionOuterPadding, paddingBottom - i7);
                        paddingBottom -= Dialog.this.mActionOuterHeight;
                    }
                    if (Dialog.this.mNegativeAction.getVisibility() == 0) {
                        Dialog.this.mNegativeAction.layout((paddingRight - Dialog.this.mActionOuterPadding) - Dialog.this.mNegativeAction.getMeasuredWidth(), (paddingBottom - Dialog.this.mActionOuterHeight) + i7, paddingRight - Dialog.this.mActionOuterPadding, paddingBottom - i7);
                        paddingBottom -= Dialog.this.mActionOuterHeight;
                    }
                    if (Dialog.this.mPositiveAction.getVisibility() == 0) {
                        Dialog.this.mPositiveAction.layout((paddingRight - Dialog.this.mActionOuterPadding) - Dialog.this.mPositiveAction.getMeasuredWidth(), (paddingBottom - Dialog.this.mActionOuterHeight) + i7, paddingRight - Dialog.this.mActionOuterPadding, paddingBottom - i7);
                        i5 = Dialog.this.mActionOuterHeight;
                    }
                } else {
                    int i8 = Dialog.this.mActionOuterPadding + paddingLeft;
                    int i9 = paddingRight - Dialog.this.mActionOuterPadding;
                    int i10 = (paddingBottom - Dialog.this.mActionOuterHeight) + i7;
                    int i11 = paddingBottom - i7;
                    if (this.mIsRtl) {
                        if (Dialog.this.mPositiveAction.getVisibility() == 0) {
                            Dialog.this.mPositiveAction.layout(i8, i10, Dialog.this.mPositiveAction.getMeasuredWidth() + i8, i11);
                            i8 += Dialog.this.mPositiveAction.getMeasuredWidth() + Dialog.this.mActionPadding;
                        }
                        if (Dialog.this.mNegativeAction.getVisibility() == 0) {
                            Dialog.this.mNegativeAction.layout(i8, i10, Dialog.this.mNegativeAction.getMeasuredWidth() + i8, i11);
                        }
                        if (Dialog.this.mNeutralAction.getVisibility() == 0) {
                            Dialog.this.mNeutralAction.layout(i9 - Dialog.this.mNeutralAction.getMeasuredWidth(), i10, i9, i11);
                        }
                    } else {
                        if (Dialog.this.mPositiveAction.getVisibility() == 0) {
                            Dialog.this.mPositiveAction.layout(i9 - Dialog.this.mPositiveAction.getMeasuredWidth(), i10, i9, i11);
                            i9 -= Dialog.this.mPositiveAction.getMeasuredWidth() + Dialog.this.mActionPadding;
                        }
                        if (Dialog.this.mNegativeAction.getVisibility() == 0) {
                            Dialog.this.mNegativeAction.layout(i9 - Dialog.this.mNegativeAction.getMeasuredWidth(), i10, i9, i11);
                        }
                        if (Dialog.this.mNeutralAction.getVisibility() == 0) {
                            Dialog.this.mNeutralAction.layout(i8, i10, Dialog.this.mNeutralAction.getMeasuredWidth() + i8, i11);
                        }
                    }
                    i5 = Dialog.this.mActionOuterHeight;
                }
                paddingBottom -= i5;
            }
            this.mDividerPos = paddingBottom - (this.mDividerPaint.getStrokeWidth() / 2.0f);
            if (Dialog.this.mContent != null) {
                Dialog.this.mContent.layout(paddingLeft + this.mContentMarginLeft, paddingTop + this.mContentMarginTop, paddingRight - this.mContentMarginRight, paddingBottom - this.mContentMarginBottom);
            }
        }

        @Override // android.view.View
        public void draw(Canvas canvas) {
            super.draw(canvas);
            if (this.mShowDivider) {
                if (Dialog.this.mPositiveAction.getVisibility() == 0 || Dialog.this.mNegativeAction.getVisibility() == 0 || Dialog.this.mNeutralAction.getVisibility() == 0) {
                    canvas.drawLine(getPaddingLeft(), this.mDividerPos, getWidth() - getPaddingRight(), this.mDividerPos, this.mDividerPaint);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder implements DialogFragment.Builder, Parcelable {
        public static final Parcelable.Creator<Builder> CREATOR = new Parcelable.Creator<Builder>() { // from class: com.rey.material.app.Dialog.Builder.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Builder createFromParcel(Parcel parcel) {
                return new Builder(parcel);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public Builder[] newArray(int i) {
                return new Builder[i];
            }
        };
        protected int mContentViewId;
        protected Dialog mDialog;
        protected CharSequence mNegative;
        protected CharSequence mNeutral;
        protected CharSequence mPositive;
        protected int mStyleId;
        protected CharSequence mTitle;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        protected void onBuildDone(Dialog dialog) {
        }

        @Override // com.rey.material.app.DialogFragment.Builder
        public void onCancel(DialogInterface dialogInterface) {
        }

        @Override // com.rey.material.app.DialogFragment.Builder
        public void onDismiss(DialogInterface dialogInterface) {
        }

        protected void onReadFromParcel(Parcel parcel) {
        }

        protected void onWriteToParcel(Parcel parcel, int i) {
        }

        public Builder() {
            this(R.style.Material_App_Dialog_Light);
        }

        public Builder(int i) {
            this.mStyleId = i;
        }

        public Builder style(int i) {
            this.mStyleId = i;
            return this;
        }

        public Builder contentView(int i) {
            this.mContentViewId = i;
            return this;
        }

        public Builder title(CharSequence charSequence) {
            this.mTitle = charSequence;
            return this;
        }

        public Builder positiveAction(CharSequence charSequence) {
            this.mPositive = charSequence;
            return this;
        }

        public Builder negativeAction(CharSequence charSequence) {
            this.mNegative = charSequence;
            return this;
        }

        public Builder neutralAction(CharSequence charSequence) {
            this.mNeutral = charSequence;
            return this;
        }

        public Dialog getDialog() {
            return this.mDialog;
        }

        @Override // com.rey.material.app.DialogFragment.Builder
        public void onPositiveActionClicked(DialogFragment dialogFragment) {
            dialogFragment.dismiss();
        }

        @Override // com.rey.material.app.DialogFragment.Builder
        public void onNegativeActionClicked(DialogFragment dialogFragment) {
            dialogFragment.dismiss();
        }

        @Override // com.rey.material.app.DialogFragment.Builder
        public void onNeutralActionClicked(DialogFragment dialogFragment) {
            dialogFragment.dismiss();
        }

        @Override // com.rey.material.app.DialogFragment.Builder
        public Dialog build(Context context) {
            Dialog onBuild = onBuild(context, this.mStyleId);
            this.mDialog = onBuild;
            onBuild.title(this.mTitle).positiveAction(this.mPositive).negativeAction(this.mNegative).neutralAction(this.mNeutral);
            int i = this.mContentViewId;
            if (i != 0) {
                this.mDialog.contentView(i);
            }
            onBuildDone(this.mDialog);
            return this.mDialog;
        }

        protected Dialog onBuild(Context context, int i) {
            return new Dialog(context, i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        public Builder(Parcel parcel) {
            this.mStyleId = parcel.readInt();
            this.mContentViewId = parcel.readInt();
            this.mTitle = (CharSequence) parcel.readParcelable(null);
            this.mPositive = (CharSequence) parcel.readParcelable(null);
            this.mNegative = (CharSequence) parcel.readParcelable(null);
            this.mNeutral = (CharSequence) parcel.readParcelable(null);
            onReadFromParcel(parcel);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(this.mStyleId);
            parcel.writeInt(this.mContentViewId);
            parcel.writeValue(this.mTitle);
            parcel.writeValue(this.mPositive);
            parcel.writeValue(this.mNegative);
            parcel.writeValue(this.mNeutral);
            onWriteToParcel(parcel, i);
        }
    }
}
