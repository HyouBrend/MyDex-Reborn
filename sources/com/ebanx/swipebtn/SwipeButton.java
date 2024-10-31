package com.ebanx.swipebtn;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;

/* loaded from: classes.dex */
public class SwipeButton extends RelativeLayout {
    private static final int DISABLED = 1;
    private static final int ENABLED = 0;
    private boolean active;
    private ViewGroup background;
    private TextView centerText;
    private int collapsedHeight;
    private int collapsedWidth;
    private Drawable disabledDrawable;
    private Drawable enabledDrawable;
    private boolean hasActivationState;
    private float initialX;
    private LinearLayout layer;
    private OnActiveListener onActiveListener;
    private OnStateChangeListener onStateChangeListener;
    private ImageView swipeButtonInner;
    private boolean trailEnabled;

    public SwipeButton(Context context) {
        super(context);
        this.trailEnabled = false;
        init(context, null, -1, -1);
    }

    public SwipeButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.trailEnabled = false;
        init(context, attributeSet, -1, -1);
    }

    public SwipeButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.trailEnabled = false;
        init(context, attributeSet, i, -1);
    }

    public SwipeButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        this.trailEnabled = false;
        init(context, attributeSet, i, i2);
    }

    public boolean isActive() {
        return this.active;
    }

    public void setText(String str) {
        this.centerText.setText(str);
    }

    @Override // android.view.View
    public void setBackground(Drawable drawable) {
        this.background.setBackground(drawable);
    }

    public void setSlidingButtonBackground(Drawable drawable) {
        this.background.setBackground(drawable);
    }

    public void setDisabledDrawable(Drawable drawable) {
        this.disabledDrawable = drawable;
        if (this.active) {
            return;
        }
        this.swipeButtonInner.setImageDrawable(drawable);
    }

    public void setButtonBackground(Drawable drawable) {
        if (drawable != null) {
            this.swipeButtonInner.setBackground(drawable);
        }
    }

    public void setEnabledDrawable(Drawable drawable) {
        this.enabledDrawable = drawable;
        if (this.active) {
            this.swipeButtonInner.setImageDrawable(drawable);
        }
    }

    public void setOnStateChangeListener(OnStateChangeListener onStateChangeListener) {
        this.onStateChangeListener = onStateChangeListener;
    }

    public void setOnActiveListener(OnActiveListener onActiveListener) {
        this.onActiveListener = onActiveListener;
    }

    public void setInnerTextPadding(int i, int i2, int i3, int i4) {
        this.centerText.setPadding(i, i2, i3, i4);
    }

    public void setSwipeButtonPadding(int i, int i2, int i3, int i4) {
        this.swipeButtonInner.setPadding(i, i2, i3, i4);
    }

    public void setHasActivationState(boolean z) {
        this.hasActivationState = z;
    }

    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        this.hasActivationState = true;
        this.background = new RelativeLayout(context);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(13, -1);
        addView(this.background, layoutParams);
        TextView textView = new TextView(context);
        this.centerText = textView;
        textView.setGravity(17);
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13, -1);
        this.background.addView(textView, layoutParams2);
        ImageView imageView = new ImageView(context);
        this.swipeButtonInner = imageView;
        if (attributeSet != null && i == -1 && i2 == -1) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SwipeButton, i, i2);
            this.collapsedWidth = (int) obtainStyledAttributes.getDimension(R.styleable.SwipeButton_button_image_width, -2.0f);
            this.collapsedHeight = (int) obtainStyledAttributes.getDimension(R.styleable.SwipeButton_button_image_height, -2.0f);
            this.trailEnabled = obtainStyledAttributes.getBoolean(R.styleable.SwipeButton_button_trail_enabled, false);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.SwipeButton_button_trail_drawable);
            Drawable drawable2 = obtainStyledAttributes.getDrawable(R.styleable.SwipeButton_inner_text_background);
            if (drawable2 != null) {
                this.background.setBackground(drawable2);
            } else {
                this.background.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_rounded));
            }
            if (this.trailEnabled) {
                LinearLayout linearLayout = new LinearLayout(context);
                this.layer = linearLayout;
                if (drawable != null) {
                    linearLayout.setBackground(drawable);
                } else {
                    linearLayout.setBackground(obtainStyledAttributes.getDrawable(R.styleable.SwipeButton_button_background));
                }
                this.layer.setGravity(GravityCompat.START);
                this.layer.setVisibility(8);
                this.background.addView(this.layer, layoutParams);
            }
            textView.setText(obtainStyledAttributes.getText(R.styleable.SwipeButton_inner_text));
            textView.setTextColor(obtainStyledAttributes.getColor(R.styleable.SwipeButton_inner_text_color, -1));
            float converPixelsToSp = DimentionUtils.converPixelsToSp(obtainStyledAttributes.getDimension(R.styleable.SwipeButton_inner_text_size, 0.0f), context);
            if (converPixelsToSp != 0.0f) {
                textView.setTextSize(converPixelsToSp);
            } else {
                textView.setTextSize(12.0f);
            }
            this.disabledDrawable = obtainStyledAttributes.getDrawable(R.styleable.SwipeButton_button_image_disabled);
            this.enabledDrawable = obtainStyledAttributes.getDrawable(R.styleable.SwipeButton_button_image_enabled);
            float dimension = obtainStyledAttributes.getDimension(R.styleable.SwipeButton_inner_text_left_padding, 0.0f);
            float dimension2 = obtainStyledAttributes.getDimension(R.styleable.SwipeButton_inner_text_top_padding, 0.0f);
            float dimension3 = obtainStyledAttributes.getDimension(R.styleable.SwipeButton_inner_text_right_padding, 0.0f);
            float dimension4 = obtainStyledAttributes.getDimension(R.styleable.SwipeButton_inner_text_bottom_padding, 0.0f);
            if (obtainStyledAttributes.getInt(R.styleable.SwipeButton_initial_state, 1) == 0) {
                RelativeLayout.LayoutParams layoutParams3 = new RelativeLayout.LayoutParams(-1, -2);
                layoutParams3.addRule(9, -1);
                layoutParams3.addRule(15, -1);
                imageView.setImageDrawable(this.enabledDrawable);
                addView(imageView, layoutParams3);
                this.active = true;
            } else {
                RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(this.collapsedWidth, this.collapsedHeight);
                layoutParams4.addRule(9, -1);
                layoutParams4.addRule(15, -1);
                imageView.setImageDrawable(this.disabledDrawable);
                addView(imageView, layoutParams4);
                this.active = false;
            }
            textView.setPadding((int) dimension, (int) dimension2, (int) dimension3, (int) dimension4);
            Drawable drawable3 = obtainStyledAttributes.getDrawable(R.styleable.SwipeButton_button_background);
            if (drawable3 != null) {
                imageView.setBackground(drawable3);
            } else {
                imageView.setBackground(ContextCompat.getDrawable(context, R.drawable.shape_button));
            }
            imageView.setPadding((int) obtainStyledAttributes.getDimension(R.styleable.SwipeButton_button_left_padding, 0.0f), (int) obtainStyledAttributes.getDimension(R.styleable.SwipeButton_button_top_padding, 0.0f), (int) obtainStyledAttributes.getDimension(R.styleable.SwipeButton_button_right_padding, 0.0f), (int) obtainStyledAttributes.getDimension(R.styleable.SwipeButton_button_bottom_padding, 0.0f));
            this.hasActivationState = obtainStyledAttributes.getBoolean(R.styleable.SwipeButton_has_activate_state, true);
            obtainStyledAttributes.recycle();
        }
        setOnTouchListener(getButtonTouchListener());
    }

    private View.OnTouchListener getButtonTouchListener() {
        return new View.OnTouchListener() { // from class: com.ebanx.swipebtn.SwipeButton.1
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int action = motionEvent.getAction();
                if (action == 0) {
                    return !TouchUtils.isTouchOutsideInitialPosition(motionEvent, SwipeButton.this.swipeButtonInner);
                }
                if (action == 1) {
                    if (SwipeButton.this.active) {
                        SwipeButton.this.collapseButton();
                    } else if (SwipeButton.this.swipeButtonInner.getX() + SwipeButton.this.swipeButtonInner.getWidth() > SwipeButton.this.getWidth() * 0.9d) {
                        if (SwipeButton.this.hasActivationState) {
                            SwipeButton.this.expandButton();
                        } else if (SwipeButton.this.onActiveListener != null) {
                            SwipeButton.this.onActiveListener.onActive();
                            SwipeButton.this.moveButtonBack();
                        }
                    } else {
                        SwipeButton.this.moveButtonBack();
                    }
                    return true;
                }
                if (action != 2) {
                    return false;
                }
                if (SwipeButton.this.initialX == 0.0f) {
                    SwipeButton swipeButton = SwipeButton.this;
                    swipeButton.initialX = swipeButton.swipeButtonInner.getX();
                }
                if (motionEvent.getX() > SwipeButton.this.swipeButtonInner.getWidth() / 2 && motionEvent.getX() + (SwipeButton.this.swipeButtonInner.getWidth() / 2) < SwipeButton.this.getWidth()) {
                    SwipeButton.this.swipeButtonInner.setX(motionEvent.getX() - (SwipeButton.this.swipeButtonInner.getWidth() / 2));
                    SwipeButton.this.centerText.setAlpha(1.0f - (((SwipeButton.this.swipeButtonInner.getX() + SwipeButton.this.swipeButtonInner.getWidth()) * 1.3f) / SwipeButton.this.getWidth()));
                    SwipeButton.this.setTrailingEffect();
                }
                if (motionEvent.getX() + (SwipeButton.this.swipeButtonInner.getWidth() / 2) > SwipeButton.this.getWidth() && SwipeButton.this.swipeButtonInner.getX() + (SwipeButton.this.swipeButtonInner.getWidth() / 2) < SwipeButton.this.getWidth()) {
                    SwipeButton.this.swipeButtonInner.setX(SwipeButton.this.getWidth() - SwipeButton.this.swipeButtonInner.getWidth());
                }
                if (motionEvent.getX() < SwipeButton.this.swipeButtonInner.getWidth() / 2) {
                    SwipeButton.this.swipeButtonInner.setX(0.0f);
                }
                return true;
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void expandButton() {
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(this.swipeButtonInner.getX(), 0.0f);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ebanx.swipebtn.SwipeButton.2
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SwipeButton.this.swipeButtonInner.setX(((Float) ofFloat.getAnimatedValue()).floatValue());
            }
        });
        final ValueAnimator ofInt = ValueAnimator.ofInt(this.swipeButtonInner.getWidth(), getWidth());
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ebanx.swipebtn.SwipeButton.3
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams layoutParams = SwipeButton.this.swipeButtonInner.getLayoutParams();
                layoutParams.width = ((Integer) ofInt.getAnimatedValue()).intValue();
                SwipeButton.this.swipeButtonInner.setLayoutParams(layoutParams);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.ebanx.swipebtn.SwipeButton.4
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                SwipeButton.this.active = true;
                SwipeButton.this.swipeButtonInner.setImageDrawable(SwipeButton.this.enabledDrawable);
                if (SwipeButton.this.onStateChangeListener != null) {
                    SwipeButton.this.onStateChangeListener.onStateChange(SwipeButton.this.active);
                }
                if (SwipeButton.this.onActiveListener != null) {
                    SwipeButton.this.onActiveListener.onActive();
                }
            }
        });
        animatorSet.playTogether(ofFloat, ofInt);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void moveButtonBack() {
        final ValueAnimator ofFloat = ValueAnimator.ofFloat(this.swipeButtonInner.getX(), 0.0f);
        ofFloat.setInterpolator(new AccelerateDecelerateInterpolator());
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ebanx.swipebtn.SwipeButton.5
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                SwipeButton.this.swipeButtonInner.setX(((Float) ofFloat.getAnimatedValue()).floatValue());
                SwipeButton.this.setTrailingEffect();
            }
        });
        ofFloat.addListener(new AnimatorListenerAdapter() { // from class: com.ebanx.swipebtn.SwipeButton.6
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                if (SwipeButton.this.layer != null) {
                    SwipeButton.this.layer.setVisibility(8);
                }
            }
        });
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(this.centerText, "alpha", 1.0f);
        ofFloat.setDuration(200L);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat2, ofFloat);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void collapseButton() {
        int i = this.collapsedWidth;
        if (i == -2) {
            i = this.swipeButtonInner.getHeight();
        }
        final ValueAnimator ofInt = ValueAnimator.ofInt(this.swipeButtonInner.getWidth(), i);
        ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.ebanx.swipebtn.SwipeButton.7
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ViewGroup.LayoutParams layoutParams = SwipeButton.this.swipeButtonInner.getLayoutParams();
                layoutParams.width = ((Integer) ofInt.getAnimatedValue()).intValue();
                SwipeButton.this.swipeButtonInner.setLayoutParams(layoutParams);
                SwipeButton.this.setTrailingEffect();
            }
        });
        ofInt.addListener(new AnimatorListenerAdapter() { // from class: com.ebanx.swipebtn.SwipeButton.8
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animator) {
                super.onAnimationEnd(animator);
                SwipeButton.this.active = false;
                SwipeButton.this.swipeButtonInner.setImageDrawable(SwipeButton.this.disabledDrawable);
                if (SwipeButton.this.onStateChangeListener != null) {
                    SwipeButton.this.onStateChangeListener.onStateChange(SwipeButton.this.active);
                }
                if (SwipeButton.this.layer != null) {
                    SwipeButton.this.layer.setVisibility(8);
                }
            }
        });
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(this.centerText, "alpha", 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(ofFloat, ofInt);
        animatorSet.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setTrailingEffect() {
        if (this.trailEnabled) {
            this.layer.setVisibility(0);
            this.layer.setLayoutParams(new RelativeLayout.LayoutParams((int) (this.swipeButtonInner.getX() + (this.swipeButtonInner.getWidth() / 3)), this.centerText.getHeight()));
        }
    }

    public void toggleState() {
        if (isActive()) {
            collapseButton();
        } else {
            expandButton();
        }
    }
}
