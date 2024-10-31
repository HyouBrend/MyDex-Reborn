package com.rey.material.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.rey.material.R;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.util.ThemeUtil;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public class SnackBar extends FrameLayout implements ThemeManager.OnThemeChangedListener {
    public static final int MATCH_PARENT = -1;
    public static final int STATE_DISMISSED = 0;
    public static final int STATE_DISMISSING = 3;
    public static final int STATE_SHOWING = 2;
    public static final int STATE_SHOWN = 1;
    public static final int WRAP_CONTENT = -2;
    private Button mAction;
    private OnActionClickListener mActionClickListener;
    private int mActionId;
    private BackgroundDrawable mBackground;
    private Runnable mDismissRunnable;
    private long mDuration;
    private int mHeight;
    private Animation mInAnimation;
    private boolean mIsRtl;
    private int mMarginBottom;
    private int mMarginStart;
    private int mMaxHeight;
    private int mMinHeight;
    private Animation mOutAnimation;
    private boolean mRemoveOnDismiss;
    private int mState;
    private OnStateChangeListener mStateChangeListener;
    private TextView mText;
    private int mWidth;

    /* loaded from: classes2.dex */
    public interface OnActionClickListener {
        void onActionClick(SnackBar snackBar, int i);
    }

    /* loaded from: classes2.dex */
    public interface OnStateChangeListener {
        void onStateChange(SnackBar snackBar, int i, int i2);
    }

    public static SnackBar make(Context context) {
        return new SnackBar(context);
    }

    public SnackBar(Context context) {
        super(context);
        this.mDismissRunnable = new Runnable() { // from class: com.rey.material.widget.SnackBar.1
            @Override // java.lang.Runnable
            public void run() {
                SnackBar.this.dismiss();
            }
        };
        this.mState = 0;
    }

    public SnackBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mDismissRunnable = new Runnable() { // from class: com.rey.material.widget.SnackBar.1
            @Override // java.lang.Runnable
            public void run() {
                SnackBar.this.dismiss();
            }
        };
        this.mState = 0;
    }

    public SnackBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.mDismissRunnable = new Runnable() { // from class: com.rey.material.widget.SnackBar.1
            @Override // java.lang.Runnable
            public void run() {
                SnackBar.this.dismiss();
            }
        };
        this.mState = 0;
    }

    public SnackBar(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.mDismissRunnable = new Runnable() { // from class: com.rey.material.widget.SnackBar.1
            @Override // java.lang.Runnable
            public void run() {
                SnackBar.this.dismiss();
            }
        };
        this.mState = 0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.FrameLayout
    public void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.mWidth = -1;
        this.mHeight = -2;
        this.mDuration = -1L;
        this.mIsRtl = false;
        TextView textView = new TextView(context);
        this.mText = textView;
        textView.setSingleLine(true);
        this.mText.setGravity(8388627);
        addView(this.mText, new FrameLayout.LayoutParams(-2, -2));
        Button button = new Button(context);
        this.mAction = button;
        button.setBackgroundResource(0);
        this.mAction.setGravity(17);
        this.mAction.setOnClickListener(new View.OnClickListener() { // from class: com.rey.material.widget.SnackBar.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                if (SnackBar.this.mActionClickListener != null) {
                    OnActionClickListener onActionClickListener = SnackBar.this.mActionClickListener;
                    SnackBar snackBar = SnackBar.this;
                    onActionClickListener.onActionClick(snackBar, snackBar.mActionId);
                }
                SnackBar.this.dismiss();
            }
        });
        addView(this.mAction, new FrameLayout.LayoutParams(-2, -2));
        BackgroundDrawable backgroundDrawable = new BackgroundDrawable();
        this.mBackground = backgroundDrawable;
        backgroundDrawable.setColor(-13487566);
        ViewUtil.setBackground(this, this.mBackground);
        setClickable(true);
        super.init(context, attributeSet, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.FrameLayout
    public void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        super.applyStyle(context, attributeSet, i, i2);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SnackBar, i, i2);
        int indexCount = obtainStyledAttributes.getIndexCount();
        int i3 = -1;
        ColorStateList colorStateList = null;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        int i7 = 0;
        boolean z = false;
        int i8 = 0;
        int i9 = 0;
        int i10 = 0;
        while (i7 < indexCount) {
            int index = obtainStyledAttributes.getIndex(i7);
            int i11 = indexCount;
            if (index == R.styleable.SnackBar_sb_backgroundColor) {
                backgroundColor(obtainStyledAttributes.getColor(index, 0));
            } else if (index == R.styleable.SnackBar_sb_backgroundCornerRadius) {
                backgroundRadius(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.SnackBar_sb_horizontalPadding) {
                i3 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.SnackBar_sb_verticalPadding) {
                i4 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.SnackBar_sb_width) {
                if (ThemeUtil.getType(obtainStyledAttributes, index) == 16) {
                    width(obtainStyledAttributes.getInteger(index, 0));
                } else {
                    width(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            } else if (index == R.styleable.SnackBar_sb_height) {
                if (ThemeUtil.getType(obtainStyledAttributes, index) == 16) {
                    height(obtainStyledAttributes.getInteger(index, 0));
                } else {
                    height(obtainStyledAttributes.getDimensionPixelSize(index, 0));
                }
            } else if (index == R.styleable.SnackBar_sb_minWidth) {
                minWidth(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.SnackBar_sb_maxWidth) {
                maxWidth(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.SnackBar_sb_minHeight) {
                minHeight(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.SnackBar_sb_maxHeight) {
                maxHeight(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.SnackBar_sb_marginStart) {
                marginStart(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.SnackBar_sb_marginBottom) {
                marginBottom(obtainStyledAttributes.getDimensionPixelSize(index, 0));
            } else if (index == R.styleable.SnackBar_sb_textSize) {
                i5 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.SnackBar_sb_textColor) {
                i9 = obtainStyledAttributes.getColor(index, 0);
                z = true;
            } else if (index == R.styleable.SnackBar_sb_textAppearance) {
                i8 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.SnackBar_sb_text) {
                text(obtainStyledAttributes.getString(index));
            } else if (index == R.styleable.SnackBar_sb_singleLine) {
                singleLine(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.SnackBar_sb_maxLines) {
                maxLines(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == R.styleable.SnackBar_sb_lines) {
                lines(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == R.styleable.SnackBar_sb_ellipsize) {
                int integer = obtainStyledAttributes.getInteger(index, 0);
                if (integer == 1) {
                    ellipsize(TextUtils.TruncateAt.START);
                } else if (integer == 2) {
                    ellipsize(TextUtils.TruncateAt.MIDDLE);
                } else if (integer == 3) {
                    ellipsize(TextUtils.TruncateAt.END);
                } else if (integer == 4) {
                    ellipsize(TextUtils.TruncateAt.MARQUEE);
                } else {
                    ellipsize(TextUtils.TruncateAt.END);
                }
            } else if (index == R.styleable.SnackBar_sb_actionTextSize) {
                i6 = obtainStyledAttributes.getDimensionPixelSize(index, 0);
            } else if (index == R.styleable.SnackBar_sb_actionTextColor) {
                colorStateList = obtainStyledAttributes.getColorStateList(index);
            } else if (index == R.styleable.SnackBar_sb_actionTextAppearance) {
                i10 = obtainStyledAttributes.getResourceId(index, 0);
            } else if (index == R.styleable.SnackBar_sb_actionText) {
                actionText(obtainStyledAttributes.getString(index));
            } else if (index == R.styleable.SnackBar_sb_actionRipple) {
                actionRipple(obtainStyledAttributes.getResourceId(index, 0));
            } else if (index == R.styleable.SnackBar_sb_duration) {
                duration(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == R.styleable.SnackBar_sb_removeOnDismiss) {
                removeOnDismiss(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.SnackBar_sb_inAnimation) {
                animationIn(AnimationUtils.loadAnimation(getContext(), obtainStyledAttributes.getResourceId(index, 0)));
            } else if (index == R.styleable.SnackBar_sb_outAnimation) {
                animationOut(AnimationUtils.loadAnimation(getContext(), obtainStyledAttributes.getResourceId(index, 0)));
            }
            i7++;
            indexCount = i11;
        }
        obtainStyledAttributes.recycle();
        if (i3 >= 0 || i4 >= 0) {
            if (i3 < 0) {
                i3 = this.mText.getPaddingLeft();
            }
            if (i4 < 0) {
                i4 = this.mText.getPaddingTop();
            }
            padding(i3, i4);
        }
        if (i8 != 0) {
            textAppearance(i8);
        }
        if (i5 >= 0) {
            textSize(i5);
        }
        if (z) {
            textColor(i9);
        }
        if (i8 != 0) {
            actionTextAppearance(i10);
        }
        if (i6 >= 0) {
            actionTextSize(i6);
        }
        if (colorStateList != null) {
            actionTextColor(colorStateList);
        }
    }

    @Override // android.view.View
    public void onRtlPropertiesChanged(int i) {
        boolean z = i == 1;
        if (this.mIsRtl != z) {
            this.mIsRtl = z;
            if (Build.VERSION.SDK_INT >= 17) {
                this.mText.setTextDirection(this.mIsRtl ? 4 : 3);
                this.mAction.setTextDirection(this.mIsRtl ? 4 : 3);
            }
            requestLayout();
        }
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int i, int i2) {
        int measuredWidth;
        int size = View.MeasureSpec.getSize(i);
        int mode = View.MeasureSpec.getMode(i);
        int size2 = View.MeasureSpec.getSize(i2);
        int mode2 = View.MeasureSpec.getMode(i2);
        if (this.mAction.getVisibility() == 0) {
            this.mAction.measure(View.MeasureSpec.makeMeasureSpec(0, 0), i2);
            int paddingLeft = this.mIsRtl ? this.mText.getPaddingLeft() : this.mText.getPaddingRight();
            this.mText.measure(View.MeasureSpec.makeMeasureSpec(size - (this.mAction.getMeasuredWidth() - paddingLeft), mode), i2);
            measuredWidth = (this.mText.getMeasuredWidth() + this.mAction.getMeasuredWidth()) - paddingLeft;
        } else {
            this.mText.measure(View.MeasureSpec.makeMeasureSpec(size, mode), i2);
            measuredWidth = this.mText.getMeasuredWidth();
        }
        int max = Math.max(this.mText.getMeasuredHeight(), this.mAction.getMeasuredHeight());
        if (mode == Integer.MIN_VALUE) {
            size = Math.min(size, measuredWidth);
        } else if (mode != 1073741824) {
            size = measuredWidth;
        }
        if (mode2 == Integer.MIN_VALUE) {
            size2 = Math.min(size2, max);
        } else if (mode2 != 1073741824) {
            size2 = max;
        }
        int i3 = this.mMaxHeight;
        if (i3 > 0) {
            size2 = Math.min(i3, size2);
        }
        int i4 = this.mMinHeight;
        if (i4 > 0) {
            size2 = Math.max(i4, size2);
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingRight = (i3 - i) - getPaddingRight();
        int paddingTop = getPaddingTop();
        int paddingBottom = (i4 - i2) - getPaddingBottom();
        if (this.mAction.getVisibility() == 0) {
            if (this.mIsRtl) {
                Button button = this.mAction;
                button.layout(paddingLeft, paddingTop, button.getMeasuredWidth() + paddingLeft, paddingBottom);
                paddingLeft += this.mAction.getMeasuredWidth() - this.mText.getPaddingLeft();
            } else {
                Button button2 = this.mAction;
                button2.layout(paddingRight - button2.getMeasuredWidth(), paddingTop, paddingRight, paddingBottom);
                paddingRight -= this.mAction.getMeasuredWidth() - this.mText.getPaddingRight();
            }
        }
        this.mText.layout(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    public SnackBar text(CharSequence charSequence) {
        this.mText.setText(charSequence);
        return this;
    }

    public SnackBar text(int i) {
        return text(getContext().getResources().getString(i));
    }

    public SnackBar textColor(int i) {
        this.mText.setTextColor(i);
        return this;
    }

    public SnackBar textSize(float f) {
        this.mText.setTextSize(2, f);
        return this;
    }

    public SnackBar textAppearance(int i) {
        if (i != 0) {
            this.mText.setTextAppearance(getContext(), i);
        }
        return this;
    }

    public SnackBar ellipsize(TextUtils.TruncateAt truncateAt) {
        this.mText.setEllipsize(truncateAt);
        return this;
    }

    public SnackBar singleLine(boolean z) {
        this.mText.setSingleLine(z);
        return this;
    }

    public SnackBar maxLines(int i) {
        this.mText.setMaxLines(i);
        return this;
    }

    public SnackBar lines(int i) {
        this.mText.setLines(i);
        return this;
    }

    public SnackBar actionId(int i) {
        this.mActionId = i;
        return this;
    }

    public SnackBar actionText(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)) {
            this.mAction.setVisibility(4);
        } else {
            this.mAction.setVisibility(0);
            this.mAction.setText(charSequence);
        }
        return this;
    }

    public SnackBar actionText(int i) {
        if (i == 0) {
            return actionText((CharSequence) null);
        }
        return actionText(getContext().getResources().getString(i));
    }

    public SnackBar actionTextColor(int i) {
        this.mAction.setTextColor(i);
        return this;
    }

    public SnackBar actionTextColor(ColorStateList colorStateList) {
        this.mAction.setTextColor(colorStateList);
        return this;
    }

    public SnackBar actionTextAppearance(int i) {
        if (i != 0) {
            this.mAction.setTextAppearance(getContext(), i);
        }
        return this;
    }

    public SnackBar actionTextSize(float f) {
        this.mAction.setTextSize(2, f);
        return this;
    }

    public SnackBar actionRipple(int i) {
        if (i != 0) {
            ViewUtil.setBackground(this.mAction, new RippleDrawable.Builder(getContext(), i).build());
        }
        return this;
    }

    public SnackBar duration(long j) {
        this.mDuration = j;
        return this;
    }

    public SnackBar backgroundColor(int i) {
        this.mBackground.setColor(i);
        return this;
    }

    public SnackBar backgroundRadius(int i) {
        this.mBackground.setRadius(i);
        return this;
    }

    public SnackBar horizontalPadding(int i) {
        TextView textView = this.mText;
        textView.setPadding(i, textView.getPaddingTop(), i, this.mText.getPaddingBottom());
        Button button = this.mAction;
        button.setPadding(i, button.getPaddingTop(), i, this.mAction.getPaddingBottom());
        return this;
    }

    public SnackBar verticalPadding(int i) {
        TextView textView = this.mText;
        textView.setPadding(textView.getPaddingLeft(), i, this.mText.getPaddingRight(), i);
        Button button = this.mAction;
        button.setPadding(button.getPaddingLeft(), i, this.mAction.getPaddingRight(), i);
        return this;
    }

    public SnackBar padding(int i, int i2) {
        this.mText.setPadding(i, i2, i, i2);
        this.mAction.setPadding(i, i2, i, i2);
        return this;
    }

    public SnackBar width(int i) {
        this.mWidth = i;
        return this;
    }

    public SnackBar minWidth(int i) {
        this.mText.setMinWidth(i);
        return this;
    }

    public SnackBar maxWidth(int i) {
        this.mText.setMaxWidth(i);
        return this;
    }

    public SnackBar height(int i) {
        this.mHeight = i;
        return this;
    }

    public SnackBar maxHeight(int i) {
        this.mMaxHeight = i;
        return this;
    }

    public SnackBar minHeight(int i) {
        this.mMinHeight = i;
        return this;
    }

    public SnackBar marginStart(int i) {
        this.mMarginStart = i;
        return this;
    }

    public SnackBar marginBottom(int i) {
        this.mMarginBottom = i;
        return this;
    }

    public SnackBar actionClickListener(OnActionClickListener onActionClickListener) {
        this.mActionClickListener = onActionClickListener;
        return this;
    }

    public SnackBar stateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.mStateChangeListener = onStateChangeListener;
        return this;
    }

    public SnackBar animationIn(Animation animation) {
        this.mInAnimation = animation;
        return this;
    }

    public SnackBar animationOut(Animation animation) {
        this.mOutAnimation = animation;
        return this;
    }

    public SnackBar removeOnDismiss(boolean z) {
        this.mRemoveOnDismiss = z;
        return this;
    }

    public void show(Activity activity) {
        show((ViewGroup) activity.getWindow().findViewById(android.R.id.content));
    }

    public void show(ViewGroup viewGroup) {
        int i = this.mState;
        if (i == 2 || i == 3) {
            return;
        }
        if (getParent() != viewGroup) {
            if (getParent() != null) {
                ((ViewGroup) getParent()).removeView(this);
            }
            viewGroup.addView(this);
        }
        show();
    }

    public void show() {
        int i;
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup == null || (i = this.mState) == 2 || i == 3) {
            return;
        }
        if (viewGroup instanceof android.widget.FrameLayout) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) getLayoutParams();
            layoutParams.width = this.mWidth;
            layoutParams.height = this.mHeight;
            layoutParams.gravity = 8388691;
            if (this.mIsRtl) {
                layoutParams.rightMargin = this.mMarginStart;
            } else {
                layoutParams.leftMargin = this.mMarginStart;
            }
            layoutParams.bottomMargin = this.mMarginBottom;
            setLayoutParams(layoutParams);
        } else if (viewGroup instanceof android.widget.RelativeLayout) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) getLayoutParams();
            layoutParams2.width = this.mWidth;
            layoutParams2.height = this.mHeight;
            layoutParams2.addRule(12);
            layoutParams2.addRule(Build.VERSION.SDK_INT >= 17 ? 20 : 9);
            if (this.mIsRtl) {
                layoutParams2.rightMargin = this.mMarginStart;
            } else {
                layoutParams2.leftMargin = this.mMarginStart;
            }
            layoutParams2.bottomMargin = this.mMarginBottom;
            setLayoutParams(layoutParams2);
        }
        Animation animation = this.mInAnimation;
        if (animation != null && this.mState != 1) {
            animation.cancel();
            this.mInAnimation.reset();
            this.mInAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.rey.material.widget.SnackBar.3
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                    SnackBar.this.setState(2);
                    SnackBar.this.setVisibility(0);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    SnackBar.this.setState(1);
                    SnackBar.this.startTimer();
                }
            });
            clearAnimation();
            startAnimation(this.mInAnimation);
            return;
        }
        setVisibility(0);
        setState(1);
        startTimer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTimer() {
        removeCallbacks(this.mDismissRunnable);
        long j = this.mDuration;
        if (j > 0) {
            postDelayed(this.mDismissRunnable, j);
        }
    }

    public void dismiss() {
        if (this.mState != 1) {
            return;
        }
        removeCallbacks(this.mDismissRunnable);
        Animation animation = this.mOutAnimation;
        if (animation != null) {
            animation.cancel();
            this.mOutAnimation.reset();
            this.mOutAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.rey.material.widget.SnackBar.4
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation2) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation2) {
                    SnackBar.this.setState(3);
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation2) {
                    if (SnackBar.this.mRemoveOnDismiss && SnackBar.this.getParent() != null && (SnackBar.this.getParent() instanceof ViewGroup)) {
                        ((ViewGroup) SnackBar.this.getParent()).removeView(SnackBar.this);
                    }
                    SnackBar.this.setState(0);
                    SnackBar.this.setVisibility(8);
                }
            });
            clearAnimation();
            startAnimation(this.mOutAnimation);
            return;
        }
        if (this.mRemoveOnDismiss && getParent() != null && (getParent() instanceof ViewGroup)) {
            ((ViewGroup) getParent()).removeView(this);
        }
        setState(0);
        setVisibility(8);
    }

    public int getState() {
        return this.mState;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setState(int i) {
        int i2 = this.mState;
        if (i2 != i) {
            this.mState = i;
            OnStateChangeListener onStateChangeListener = this.mStateChangeListener;
            if (onStateChangeListener != null) {
                onStateChangeListener.onStateChange(this, i2, i);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class BackgroundDrawable extends Drawable {
        private int mBackgroundColor;
        private int mBackgroundRadius;
        private Paint mPaint;
        private RectF mRect;

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        public BackgroundDrawable() {
            Paint paint = new Paint();
            this.mPaint = paint;
            paint.setAntiAlias(true);
            this.mPaint.setStyle(Paint.Style.FILL);
            this.mRect = new RectF();
        }

        public void setColor(int i) {
            if (this.mBackgroundColor != i) {
                this.mBackgroundColor = i;
                this.mPaint.setColor(i);
                invalidateSelf();
            }
        }

        public void setRadius(int i) {
            if (this.mBackgroundRadius != i) {
                this.mBackgroundRadius = i;
                invalidateSelf();
            }
        }

        @Override // android.graphics.drawable.Drawable
        protected void onBoundsChange(Rect rect) {
            this.mRect.set(rect);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas canvas) {
            RectF rectF = this.mRect;
            int i = this.mBackgroundRadius;
            canvas.drawRoundRect(rectF, i, i, this.mPaint);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int i) {
            this.mPaint.setAlpha(i);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter colorFilter) {
            this.mPaint.setColorFilter(colorFilter);
        }
    }
}
