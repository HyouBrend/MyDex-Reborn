package com.rey.material.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import com.rey.material.R;
import com.rey.material.drawable.BlankDrawable;

/* loaded from: classes2.dex */
public class BottomSheetDialog extends android.app.Dialog {
    private Animation mAnimation;
    private boolean mCancelable;
    private boolean mCanceledOnTouchOutside;
    private ContainerFrameLayout mContainer;
    private View mContentView;
    private final Runnable mDismissAction;
    private GestureDetector mGestureDetector;
    private final Handler mHandler;
    private int mInDuration;
    private Interpolator mInInterpolator;
    private int mLayoutHeight;
    private int mMinFlingVelocity;
    private int mOutDuration;
    private Interpolator mOutInterpolator;
    private boolean mRunShowAnimation;

    protected void onCreate() {
    }

    public BottomSheetDialog(Context context) {
        this(context, R.style.Material_App_BottomSheetDialog);
    }

    public BottomSheetDialog(Context context, int i) {
        super(context, i);
        this.mCancelable = true;
        this.mCanceledOnTouchOutside = true;
        this.mLayoutHeight = -2;
        this.mHandler = new Handler();
        this.mDismissAction = new Runnable() { // from class: com.rey.material.app.BottomSheetDialog.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    BottomSheetDialog.super.dismiss();
                } catch (IllegalArgumentException unused) {
                }
            }
        };
        this.mRunShowAnimation = false;
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
        this.mContainer = new ContainerFrameLayout(context);
        cancelable(true);
        canceledOnTouchOutside(true);
        onCreate();
        applyStyle(i);
        this.mMinFlingVelocity = ViewConfiguration.get(context).getScaledMinimumFlingVelocity() * 2;
        this.mGestureDetector = new GestureDetector(context, new GestureDetector.OnGestureListener() { // from class: com.rey.material.app.BottomSheetDialog.2
            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onDown(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onLongPress(MotionEvent motionEvent) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public void onShowPress(MotionEvent motionEvent) {
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onSingleTapUp(MotionEvent motionEvent) {
                return false;
            }

            @Override // android.view.GestureDetector.OnGestureListener
            public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
                if (f2 <= BottomSheetDialog.this.mMinFlingVelocity) {
                    return false;
                }
                BottomSheetDialog.this.dismiss();
                return true;
            }
        });
        super.setContentView(this.mContainer);
    }

    public BottomSheetDialog applyStyle(int i) {
        int resourceId;
        Context context = getContext();
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(i, R.styleable.BottomSheetDialog);
        int indexCount = obtainStyledAttributes.getIndexCount();
        for (int i2 = 0; i2 < indexCount; i2++) {
            int index = obtainStyledAttributes.getIndex(i2);
            if (index == R.styleable.BottomSheetDialog_android_layout_height) {
                heightParam(obtainStyledAttributes.getLayoutDimension(index, -2));
            } else if (index == R.styleable.BottomSheetDialog_bsd_cancelable) {
                cancelable(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.BottomSheetDialog_bsd_canceledOnTouchOutside) {
                canceledOnTouchOutside(obtainStyledAttributes.getBoolean(index, true));
            } else if (index == R.styleable.BottomSheetDialog_bsd_dimAmount) {
                dimAmount(obtainStyledAttributes.getFloat(index, 0.0f));
            } else if (index == R.styleable.BottomSheetDialog_bsd_inDuration) {
                inDuration(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == R.styleable.BottomSheetDialog_bsd_inInterpolator) {
                int resourceId2 = obtainStyledAttributes.getResourceId(index, 0);
                if (resourceId2 != 0) {
                    inInterpolator(AnimationUtils.loadInterpolator(context, resourceId2));
                }
            } else if (index == R.styleable.BottomSheetDialog_bsd_outDuration) {
                outDuration(obtainStyledAttributes.getInteger(index, 0));
            } else if (index == R.styleable.BottomSheetDialog_bsd_outInterpolator && (resourceId = obtainStyledAttributes.getResourceId(index, 0)) != 0) {
                outInterpolator(AnimationUtils.loadInterpolator(context, resourceId));
            }
        }
        obtainStyledAttributes.recycle();
        if (this.mInInterpolator == null) {
            this.mInInterpolator = new DecelerateInterpolator();
        }
        if (this.mOutInterpolator == null) {
            this.mOutInterpolator = new AccelerateInterpolator();
        }
        return this;
    }

    public BottomSheetDialog cancelable(boolean z) {
        super.setCancelable(z);
        this.mCancelable = z;
        return this;
    }

    public BottomSheetDialog canceledOnTouchOutside(boolean z) {
        super.setCanceledOnTouchOutside(z);
        this.mCanceledOnTouchOutside = z;
        return this;
    }

    public BottomSheetDialog dimAmount(float f) {
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

    public BottomSheetDialog contentView(View view) {
        this.mContentView = view;
        this.mContainer.removeAllViews();
        this.mContainer.addView(view);
        return this;
    }

    public BottomSheetDialog contentView(int i) {
        return i == 0 ? this : contentView(LayoutInflater.from(getContext()).inflate(i, (ViewGroup) null));
    }

    public BottomSheetDialog heightParam(int i) {
        if (this.mLayoutHeight != i) {
            this.mLayoutHeight = i;
            if (isShowing() && this.mContentView != null) {
                this.mRunShowAnimation = true;
                this.mContainer.forceLayout();
                this.mContainer.requestLayout();
            }
        }
        return this;
    }

    public BottomSheetDialog inDuration(int i) {
        this.mInDuration = i;
        return this;
    }

    public BottomSheetDialog inInterpolator(Interpolator interpolator) {
        this.mInInterpolator = interpolator;
        return this;
    }

    public BottomSheetDialog outDuration(int i) {
        this.mOutDuration = i;
        return this;
    }

    public BottomSheetDialog outInterpolator(Interpolator interpolator) {
        this.mOutInterpolator = interpolator;
        return this;
    }

    @Override // android.app.Dialog
    protected void onStart() {
        super.onStart();
        if (this.mContentView != null) {
            this.mRunShowAnimation = true;
            this.mContainer.forceLayout();
        }
    }

    @Override // android.app.Dialog
    protected void onStop() {
        super.onStop();
        this.mContainer = null;
        this.mContentView = null;
        this.mGestureDetector = null;
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

    public void dismissImmediately() {
        super.dismiss();
        Animation animation = this.mAnimation;
        if (animation != null) {
            animation.cancel();
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacks(this.mDismissAction);
        }
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (isShowing()) {
            if (this.mContentView != null) {
                SlideAnimation slideAnimation = new SlideAnimation(this.mContentView.getTop(), this.mContainer.getMeasuredHeight());
                this.mAnimation = slideAnimation;
                slideAnimation.setDuration(this.mOutDuration);
                this.mAnimation.setInterpolator(this.mOutInterpolator);
                this.mAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.rey.material.app.BottomSheetDialog.3
                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationRepeat(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override // android.view.animation.Animation.AnimationListener
                    public void onAnimationEnd(Animation animation) {
                        BottomSheetDialog.this.mAnimation = null;
                        BottomSheetDialog.this.mHandler.post(BottomSheetDialog.this.mDismissAction);
                    }
                });
                this.mContentView.startAnimation(this.mAnimation);
                return;
            }
            this.mHandler.post(this.mDismissAction);
        }
    }

    protected int getContainerHeight() {
        ContainerFrameLayout containerFrameLayout = this.mContainer;
        if (containerFrameLayout == null) {
            return 0;
        }
        return containerFrameLayout.getHeight();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class ContainerFrameLayout extends FrameLayout {
        private int mChildTop;
        private boolean mClickOutside;

        public ContainerFrameLayout(Context context) {
            super(context);
            this.mClickOutside = false;
            this.mChildTop = -1;
        }

        public void setChildTop(int i) {
            this.mChildTop = i;
            View childAt = getChildAt(0);
            if (childAt != null) {
                childAt.offsetTopAndBottom(this.mChildTop - childAt.getTop());
            }
        }

        @Override // android.widget.FrameLayout, android.view.View
        protected void onMeasure(int i, int i2) {
            int size = View.MeasureSpec.getSize(i);
            int size2 = View.MeasureSpec.getSize(i2);
            View childAt = getChildAt(0);
            if (childAt != null) {
                int i3 = BottomSheetDialog.this.mLayoutHeight;
                if (i3 == -2) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE));
                } else if (i3 != -1) {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(Math.min(BottomSheetDialog.this.mLayoutHeight, size2), 1073741824));
                } else {
                    childAt.measure(View.MeasureSpec.makeMeasureSpec(size, 1073741824), View.MeasureSpec.makeMeasureSpec(size2, 1073741824));
                }
            }
            setMeasuredDimension(size, size2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
            View childAt = getChildAt(0);
            if (childAt != null) {
                if (this.mChildTop < 0) {
                    this.mChildTop = i4 - i2;
                }
                childAt.layout(0, this.mChildTop, childAt.getMeasuredWidth(), Math.max(i4 - i2, this.mChildTop + childAt.getMeasuredHeight()));
                if (BottomSheetDialog.this.mRunShowAnimation) {
                    BottomSheetDialog.this.mRunShowAnimation = false;
                    if (BottomSheetDialog.this.mAnimation != null) {
                        BottomSheetDialog.this.mAnimation.cancel();
                        BottomSheetDialog.this.mAnimation = null;
                    }
                    if (BottomSheetDialog.this.mContentView != null) {
                        int height = this.mChildTop < 0 ? getHeight() : childAt.getTop();
                        int measuredHeight = getMeasuredHeight() - BottomSheetDialog.this.mContentView.getMeasuredHeight();
                        if (height != measuredHeight) {
                            BottomSheetDialog.this.mAnimation = new SlideAnimation(height, measuredHeight);
                            BottomSheetDialog.this.mAnimation.setDuration(BottomSheetDialog.this.mInDuration);
                            BottomSheetDialog.this.mAnimation.setInterpolator(BottomSheetDialog.this.mInInterpolator);
                            BottomSheetDialog.this.mAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.rey.material.app.BottomSheetDialog.ContainerFrameLayout.1
                                @Override // android.view.animation.Animation.AnimationListener
                                public void onAnimationRepeat(Animation animation) {
                                }

                                @Override // android.view.animation.Animation.AnimationListener
                                public void onAnimationStart(Animation animation) {
                                }

                                @Override // android.view.animation.Animation.AnimationListener
                                public void onAnimationEnd(Animation animation) {
                                    BottomSheetDialog.this.mAnimation = null;
                                }
                            });
                            BottomSheetDialog.this.mContentView.startAnimation(BottomSheetDialog.this.mAnimation);
                        }
                    }
                }
            }
        }

        private boolean isOutsideDialog(float f, float f2) {
            if (f2 < this.mChildTop) {
                return true;
            }
            View childAt = getChildAt(0);
            return childAt != null && f2 > ((float) (this.mChildTop + childAt.getMeasuredHeight()));
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            if (super.dispatchTouchEvent(motionEvent) || BottomSheetDialog.this.mGestureDetector == null) {
                return true;
            }
            BottomSheetDialog.this.mGestureDetector.onTouchEvent(motionEvent);
            return true;
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
            if (BottomSheetDialog.this.mCancelable && BottomSheetDialog.this.mCanceledOnTouchOutside) {
                BottomSheetDialog.this.dismiss();
            }
            return true;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes2.dex */
    public class SlideAnimation extends Animation {
        int mEnd;
        int mStart;

        public SlideAnimation(int i, int i2) {
            this.mStart = i;
            this.mEnd = i2;
        }

        @Override // android.view.animation.Animation
        protected void applyTransformation(float f, Transformation transformation) {
            int i = this.mEnd;
            int round = Math.round(((i - r0) * f) + this.mStart);
            if (BottomSheetDialog.this.mContainer != null) {
                BottomSheetDialog.this.mContainer.setChildTop(round);
            } else {
                cancel();
            }
        }
    }
}
