package com.rey.material.drawable;

import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.Drawable;
import androidx.core.graphics.drawable.DrawableCompat;

/* loaded from: classes2.dex */
public class PaddingDrawable extends Drawable implements Drawable.Callback {
    private Drawable mDrawable;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;

    public PaddingDrawable(Drawable drawable) {
        setWrappedDrawable(drawable);
    }

    public void setPadding(int i, int i2, int i3, int i4) {
        this.mPaddingLeft = i;
        this.mPaddingTop = i2;
        this.mPaddingRight = i3;
        this.mPaddingBottom = i4;
    }

    public int getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public int getPaddingTop() {
        return this.mPaddingTop;
    }

    public int getPaddingRight() {
        return this.mPaddingRight;
    }

    public int getPaddingBottom() {
        return this.mPaddingBottom;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.draw(canvas);
        }
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setBounds(rect.left + this.mPaddingLeft, rect.top + this.mPaddingTop, rect.right - this.mPaddingRight, rect.bottom - this.mPaddingBottom);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setChangingConfigurations(int i) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setChangingConfigurations(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            return drawable.getChangingConfigurations();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void setDither(boolean z) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setDither(z);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setFilterBitmap(boolean z) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setFilterBitmap(z);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setAlpha(i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            drawable.setColorFilter(colorFilter);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable drawable = this.mDrawable;
        return drawable != null && drawable.isStateful();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setState(int[] iArr) {
        Drawable drawable = this.mDrawable;
        return drawable != null && drawable.setState(iArr);
    }

    @Override // android.graphics.drawable.Drawable
    public int[] getState() {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            return drawable.getState();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public void jumpToCurrentState() {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            DrawableCompat.jumpToCurrentState(drawable);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable getCurrent() {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            return drawable.getCurrent();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean z, boolean z2) {
        Drawable drawable;
        return super.setVisible(z, z2) || ((drawable = this.mDrawable) != null && drawable.setVisible(z, z2));
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            return drawable.getOpacity();
        }
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public Region getTransparentRegion() {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            return drawable.getTransparentRegion();
        }
        return null;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable drawable = this.mDrawable;
        return (drawable != null ? drawable.getIntrinsicWidth() : 0) + this.mPaddingLeft + this.mPaddingRight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable drawable = this.mDrawable;
        return (drawable != null ? drawable.getIntrinsicHeight() : 0) + this.mPaddingTop + this.mPaddingBottom;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumWidth() {
        Drawable drawable = this.mDrawable;
        return (drawable != null ? drawable.getMinimumWidth() : 0) + this.mPaddingLeft + this.mPaddingRight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getMinimumHeight() {
        Drawable drawable = this.mDrawable;
        return (drawable != null ? drawable.getMinimumHeight() : 0) + this.mPaddingTop + this.mPaddingBottom;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean getPadding(Rect rect) {
        Drawable drawable = this.mDrawable;
        boolean z = true;
        boolean z2 = drawable != null && drawable.getPadding(rect);
        if (z2) {
            rect.left += this.mPaddingLeft;
            rect.top += this.mPaddingTop;
            rect.right += this.mPaddingRight;
            rect.bottom += this.mPaddingBottom;
            return z2;
        }
        rect.set(this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom);
        if (this.mPaddingLeft == 0 && this.mPaddingTop == 0 && this.mPaddingRight == 0 && this.mPaddingBottom == 0) {
            z = false;
        }
        return z;
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void invalidateDrawable(Drawable drawable) {
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void scheduleDrawable(Drawable drawable, Runnable runnable, long j) {
        scheduleSelf(runnable, j);
    }

    @Override // android.graphics.drawable.Drawable.Callback
    public void unscheduleDrawable(Drawable drawable, Runnable runnable) {
        unscheduleSelf(runnable);
    }

    @Override // android.graphics.drawable.Drawable
    protected boolean onLevelChange(int i) {
        Drawable drawable = this.mDrawable;
        return drawable != null && drawable.setLevel(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean z) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            DrawableCompat.setAutoMirrored(drawable, z);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable drawable = this.mDrawable;
        return drawable != null && DrawableCompat.isAutoMirrored(drawable);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int i) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            DrawableCompat.setTint(drawable, i);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList colorStateList) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            DrawableCompat.setTintList(drawable, colorStateList);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode mode) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            DrawableCompat.setTintMode(drawable, mode);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspot(float f, float f2) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspot(drawable, f, f2);
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setHotspotBounds(int i, int i2, int i3, int i4) {
        Drawable drawable = this.mDrawable;
        if (drawable != null) {
            DrawableCompat.setHotspotBounds(drawable, i, i2, i3, i4);
        }
    }

    public Drawable getWrappedDrawable() {
        return this.mDrawable;
    }

    public void setWrappedDrawable(Drawable drawable) {
        Drawable drawable2 = this.mDrawable;
        if (drawable2 != null) {
            drawable2.setCallback(null);
        }
        this.mDrawable = drawable;
        if (drawable != null) {
            drawable.setCallback(this);
        }
        onBoundsChange(getBounds());
        invalidateSelf();
    }
}
