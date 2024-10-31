package com.rey.material.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import com.rey.material.R;
import com.rey.material.drawable.RippleDrawable;
import com.rey.material.drawable.ToolbarRippleDrawable;
import com.rey.material.util.ViewUtil;

/* loaded from: classes2.dex */
public final class RippleManager implements View.OnClickListener {
    private View.OnClickListener mClickListener;
    private boolean mClickScheduled = false;

    public void onCreate(View view, Context context, AttributeSet attributeSet, int i, int i2) {
        if (view.isInEditMode()) {
            return;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.RippleView, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.RippleView_rd_style, 0);
        RippleDrawable rippleDrawable = null;
        if (resourceId != 0) {
            rippleDrawable = new RippleDrawable.Builder(context, resourceId).backgroundDrawable(getBackground(view)).build();
        } else if (obtainStyledAttributes.getBoolean(R.styleable.RippleView_rd_enable, false)) {
            rippleDrawable = new RippleDrawable.Builder(context, attributeSet, i, i2).backgroundDrawable(getBackground(view)).build();
        }
        obtainStyledAttributes.recycle();
        if (rippleDrawable != null) {
            ViewUtil.setBackground(view, rippleDrawable);
        }
    }

    private Drawable getBackground(View view) {
        Drawable background = view.getBackground();
        if (background == null) {
            return null;
        }
        return background instanceof RippleDrawable ? ((RippleDrawable) background).getBackgroundDrawable() : background;
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.mClickListener = onClickListener;
    }

    public boolean onTouchEvent(View view, MotionEvent motionEvent) {
        Drawable background = view.getBackground();
        return background != null && (background instanceof RippleDrawable) && ((RippleDrawable) background).onTouch(view, motionEvent);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        long j;
        Drawable background = view.getBackground();
        if (background != null) {
            if (background instanceof RippleDrawable) {
                j = ((RippleDrawable) background).getClickDelayTime();
            } else if (background instanceof ToolbarRippleDrawable) {
                j = ((ToolbarRippleDrawable) background).getClickDelayTime();
            }
            if (j <= 0 && view.getHandler() != null && !this.mClickScheduled) {
                this.mClickScheduled = true;
                view.getHandler().postDelayed(new ClickRunnable(view), j);
                return;
            } else {
                dispatchClickEvent(view);
            }
        }
        j = 0;
        if (j <= 0) {
        }
        dispatchClickEvent(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dispatchClickEvent(View view) {
        View.OnClickListener onClickListener = this.mClickListener;
        if (onClickListener != null) {
            onClickListener.onClick(view);
        }
    }

    public static void cancelRipple(View view) {
        Drawable background = view.getBackground();
        if (background instanceof RippleDrawable) {
            ((RippleDrawable) background).cancel();
        } else if (background instanceof ToolbarRippleDrawable) {
            ((ToolbarRippleDrawable) background).cancel();
        }
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            int childCount = viewGroup.getChildCount();
            for (int i = 0; i < childCount; i++) {
                cancelRipple(viewGroup.getChildAt(i));
            }
        }
    }

    /* loaded from: classes2.dex */
    class ClickRunnable implements Runnable {
        View mView;

        public ClickRunnable(View view) {
            this.mView = view;
        }

        @Override // java.lang.Runnable
        public void run() {
            RippleManager.this.mClickScheduled = false;
            RippleManager.this.dispatchClickEvent(this.mView);
        }
    }
}
