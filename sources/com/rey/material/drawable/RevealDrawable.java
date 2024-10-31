package com.rey.material.drawable;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import com.rey.material.util.ColorUtil;

/* loaded from: classes2.dex */
public class RevealDrawable extends Drawable implements Animatable {
    private static final float GRADIENT_RADIUS = 16.0f;
    private static final float[] GRADIENT_STOPS = {0.0f, 0.99f, 1.0f};
    private float mAnimProgress;
    private int mCurColor;
    private boolean mCurColorTransparent;
    private int mCurTask;
    private Paint mFillPaint;
    private Matrix mMatrix;
    private float mMaxRadius;
    private boolean mNextColorTransparent;
    private RectF mRect;
    private RadialGradient mShader;
    private Paint mShaderPaint;
    private long mStartTime;
    private ColorChangeTask[] mTasks;
    private boolean mRunning = false;
    private final Runnable mUpdater = new Runnable() { // from class: com.rey.material.drawable.RevealDrawable.1
        @Override // java.lang.Runnable
        public void run() {
            RevealDrawable.this.update();
        }
    };

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    public RevealDrawable(int i) {
        Paint paint = new Paint(1);
        this.mShaderPaint = paint;
        paint.setStyle(Paint.Style.FILL);
        Paint paint2 = new Paint(1);
        this.mFillPaint = paint2;
        paint2.setStyle(Paint.Style.FILL);
        this.mCurColor = i;
        this.mRect = new RectF();
        this.mMatrix = new Matrix();
    }

    public int getCurColor() {
        return this.mCurColor;
    }

    public void setCurColor(int i) {
        if (this.mCurColor != i) {
            this.mCurColor = i;
            this.mCurColorTransparent = Color.alpha(i) == 0;
            invalidateSelf();
        }
    }

    private float getMaxRadius(float f, float f2, Rect rect) {
        return (float) Math.sqrt(Math.pow((f < ((float) rect.centerX()) ? rect.right : rect.left) - f, 2.0d) + Math.pow((f2 < ((float) rect.centerY()) ? rect.bottom : rect.top) - f2, 2.0d));
    }

    private RadialGradient getShader(ColorChangeTask colorChangeTask) {
        if (this.mShader == null) {
            if (colorChangeTask.isOut) {
                this.mShader = new RadialGradient(colorChangeTask.x, colorChangeTask.y, GRADIENT_RADIUS, new int[]{0, ColorUtil.getColor(this.mCurColor, 0.0f), this.mCurColor}, GRADIENT_STOPS, Shader.TileMode.CLAMP);
            } else {
                this.mShader = new RadialGradient(colorChangeTask.x, colorChangeTask.y, GRADIENT_RADIUS, new int[]{0, ColorUtil.getColor(colorChangeTask.color, 0.0f), colorChangeTask.color}, GRADIENT_STOPS, Shader.TileMode.CLAMP);
            }
        }
        return this.mShader;
    }

    private void fillCanvas(Canvas canvas, int i, boolean z) {
        if (z) {
            return;
        }
        this.mFillPaint.setColor(i);
        canvas.drawRect(getBounds(), this.mFillPaint);
    }

    private void fillCanvasWithHole(Canvas canvas, ColorChangeTask colorChangeTask, float f, boolean z) {
        if (z) {
            return;
        }
        float f2 = f / GRADIENT_RADIUS;
        this.mMatrix.reset();
        this.mMatrix.postScale(f2, f2, colorChangeTask.x, colorChangeTask.y);
        RadialGradient shader = getShader(colorChangeTask);
        shader.setLocalMatrix(this.mMatrix);
        this.mShaderPaint.setShader(shader);
        canvas.drawRect(getBounds(), this.mShaderPaint);
    }

    private void fillCircle(Canvas canvas, float f, float f2, float f3, int i, boolean z) {
        if (z) {
            return;
        }
        this.mFillPaint.setColor(i);
        this.mRect.set(f - f3, f2 - f3, f + f3, f2 + f3);
        canvas.drawOval(this.mRect, this.mFillPaint);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        if (!isRunning()) {
            fillCanvas(canvas, this.mCurColor, this.mCurColorTransparent);
            return;
        }
        ColorChangeTask colorChangeTask = this.mTasks[this.mCurTask];
        float f = this.mAnimProgress;
        if (f == 0.0f) {
            fillCanvas(canvas, this.mCurColor, this.mCurColorTransparent);
            return;
        }
        if (f == 1.0f) {
            fillCanvas(canvas, colorChangeTask.color, this.mNextColorTransparent);
            return;
        }
        if (colorChangeTask.isOut) {
            float interpolation = this.mMaxRadius * colorChangeTask.interpolator.getInterpolation(this.mAnimProgress);
            if (Color.alpha(colorChangeTask.color) == 255) {
                fillCanvas(canvas, this.mCurColor, this.mCurColorTransparent);
            } else {
                fillCanvasWithHole(canvas, colorChangeTask, interpolation, this.mCurColorTransparent);
            }
            fillCircle(canvas, colorChangeTask.x, colorChangeTask.y, interpolation, colorChangeTask.color, this.mNextColorTransparent);
            return;
        }
        float interpolation2 = this.mMaxRadius * colorChangeTask.interpolator.getInterpolation(this.mAnimProgress);
        if (Color.alpha(this.mCurColor) == 255) {
            fillCanvas(canvas, colorChangeTask.color, this.mNextColorTransparent);
        } else {
            fillCanvasWithHole(canvas, colorChangeTask, interpolation2, this.mNextColorTransparent);
        }
        fillCircle(canvas, colorChangeTask.x, colorChangeTask.y, interpolation2, this.mCurColor, this.mCurColorTransparent);
    }

    public void changeColor(int i, int i2, Interpolator interpolator, float f, float f2, boolean z) {
        changeColor(new ColorChangeTask(i, i2, interpolator, f, f2, z));
    }

    public void changeColor(ColorChangeTask... colorChangeTaskArr) {
        synchronized (RevealDrawable.class) {
            int i = 0;
            if (isRunning()) {
                ColorChangeTask[] colorChangeTaskArr2 = this.mTasks;
                int length = colorChangeTaskArr2.length;
                int i2 = this.mCurTask;
                int i3 = length - i2;
                ColorChangeTask[] colorChangeTaskArr3 = new ColorChangeTask[colorChangeTaskArr.length + i3];
                System.arraycopy(colorChangeTaskArr2, i2, colorChangeTaskArr3, 0, i3);
                System.arraycopy(colorChangeTaskArr, 0, colorChangeTaskArr3, i3, colorChangeTaskArr.length);
                this.mTasks = colorChangeTaskArr3;
                this.mCurTask = 0;
            } else {
                while (true) {
                    if (i >= colorChangeTaskArr.length) {
                        break;
                    }
                    if (colorChangeTaskArr[i].color != this.mCurColor) {
                        this.mCurTask = i;
                        this.mTasks = colorChangeTaskArr;
                        start();
                        break;
                    }
                    i++;
                }
            }
        }
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mShaderPaint.setAlpha(i);
        this.mFillPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mShaderPaint.setColorFilter(colorFilter);
        this.mFillPaint.setColorFilter(colorFilter);
    }

    private void resetAnimation() {
        this.mStartTime = SystemClock.uptimeMillis();
        this.mAnimProgress = 0.0f;
        this.mCurColorTransparent = Color.alpha(this.mCurColor) == 0;
        this.mNextColorTransparent = Color.alpha(this.mTasks[this.mCurTask].color) == 0;
        this.mMaxRadius = getMaxRadius(this.mTasks[this.mCurTask].x, this.mTasks[this.mCurTask].y, getBounds());
        this.mShader = null;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        if (isRunning()) {
            return;
        }
        resetAnimation();
        scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (isRunning()) {
            this.mTasks = null;
            this.mRunning = false;
            unscheduleSelf(this.mUpdater);
            invalidateSelf();
        }
    }

    @Override // android.graphics.drawable.Animatable
    public boolean isRunning() {
        return this.mRunning;
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable runnable, long j) {
        this.mRunning = true;
        super.scheduleSelf(runnable, j);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void update() {
        long uptimeMillis = SystemClock.uptimeMillis();
        synchronized (RevealDrawable.class) {
            float min = Math.min(1.0f, ((float) (uptimeMillis - this.mStartTime)) / this.mTasks[this.mCurTask].duration);
            this.mAnimProgress = min;
            if (min == 1.0f) {
                setCurColor(this.mTasks[this.mCurTask].color);
                this.mCurTask++;
                while (true) {
                    int i = this.mCurTask;
                    ColorChangeTask[] colorChangeTaskArr = this.mTasks;
                    if (i >= colorChangeTaskArr.length) {
                        break;
                    }
                    if (colorChangeTaskArr[i].color == this.mCurColor) {
                        this.mCurTask++;
                    } else {
                        resetAnimation();
                        break;
                    }
                }
                if (this.mCurTask == this.mTasks.length) {
                    stop();
                }
            }
        }
        invalidateSelf();
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
    }

    /* loaded from: classes2.dex */
    public static class ColorChangeTask {
        public final int color;
        public final int duration;
        public final Interpolator interpolator;
        public final boolean isOut;
        public final float x;
        public final float y;

        public ColorChangeTask(int i, int i2, Interpolator interpolator, float f, float f2, boolean z) {
            this.color = i;
            this.duration = i2;
            this.interpolator = interpolator == null ? new DecelerateInterpolator() : interpolator;
            this.x = f;
            this.y = f2;
            this.isOut = z;
        }
    }
}
