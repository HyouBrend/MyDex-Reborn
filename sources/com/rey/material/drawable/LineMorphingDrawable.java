package com.rey.material.drawable;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import androidx.core.text.TextUtilsCompat;
import com.rey.material.R;
import com.rey.material.util.ThemeUtil;
import java.util.Locale;

/* loaded from: classes2.dex */
public class LineMorphingDrawable extends Drawable implements Animatable {
    private int mAnimDuration;
    private float mAnimProgress;
    private boolean mClockwise;
    private int mCurState;
    private RectF mDrawBound;
    private Interpolator mInterpolator;
    private boolean mIsRtl;
    private int mPaddingBottom;
    private int mPaddingLeft;
    private int mPaddingRight;
    private int mPaddingTop;
    private Paint mPaint;
    private Path mPath;
    private int mPrevState;
    private boolean mRunning;
    private long mStartTime;
    private State[] mStates;
    private Paint.Cap mStrokeCap;
    private int mStrokeColor;
    private Paint.Join mStrokeJoin;
    private int mStrokeSize;
    private final Runnable mUpdater;

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -3;
    }

    private LineMorphingDrawable(State[] stateArr, int i, int i2, int i3, int i4, int i5, int i6, Interpolator interpolator, int i7, int i8, Paint.Cap cap, Paint.Join join, boolean z, boolean z2) {
        this.mRunning = false;
        this.mPaddingLeft = 12;
        this.mPaddingTop = 12;
        this.mPaddingRight = 12;
        this.mPaddingBottom = 12;
        this.mUpdater = new Runnable() { // from class: com.rey.material.drawable.LineMorphingDrawable.1
            @Override // java.lang.Runnable
            public void run() {
                LineMorphingDrawable.this.update();
            }
        };
        this.mStates = stateArr;
        this.mPaddingLeft = i2;
        this.mPaddingTop = i3;
        this.mPaddingRight = i4;
        this.mPaddingBottom = i5;
        this.mAnimDuration = i6;
        this.mInterpolator = interpolator;
        this.mStrokeSize = i7;
        this.mStrokeColor = i8;
        this.mStrokeCap = cap;
        this.mStrokeJoin = join;
        this.mClockwise = z;
        this.mIsRtl = z2;
        Paint paint = new Paint();
        this.mPaint = paint;
        paint.setAntiAlias(true);
        this.mPaint.setStyle(Paint.Style.STROKE);
        this.mPaint.setStrokeCap(this.mStrokeCap);
        this.mPaint.setStrokeJoin(this.mStrokeJoin);
        this.mPaint.setColor(this.mStrokeColor);
        this.mPaint.setStrokeWidth(this.mStrokeSize);
        this.mDrawBound = new RectF();
        this.mPath = new Path();
        switchLineState(i, false);
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas canvas) {
        int save = canvas.save();
        float f = (this.mClockwise ? 180 : -180) * ((this.mPrevState < this.mCurState ? 0.0f : 1.0f) + this.mAnimProgress);
        if (this.mIsRtl) {
            canvas.scale(-1.0f, 1.0f, this.mDrawBound.centerX(), this.mDrawBound.centerY());
        }
        canvas.rotate(f, this.mDrawBound.centerX(), this.mDrawBound.centerY());
        canvas.drawPath(this.mPath, this.mPaint);
        canvas.restoreToCount(save);
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int i) {
        this.mPaint.setAlpha(i);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter colorFilter) {
        this.mPaint.setColorFilter(colorFilter);
    }

    @Override // android.graphics.drawable.Drawable
    protected void onBoundsChange(Rect rect) {
        super.onBoundsChange(rect);
        this.mDrawBound.left = rect.left + this.mPaddingLeft;
        this.mDrawBound.top = rect.top + this.mPaddingTop;
        this.mDrawBound.right = rect.right - this.mPaddingRight;
        this.mDrawBound.bottom = rect.bottom - this.mPaddingBottom;
        updatePath();
    }

    public void switchLineState(int i, boolean z) {
        int i2 = this.mCurState;
        if (i2 == i) {
            if (z) {
                return;
            }
            this.mAnimProgress = 1.0f;
            updatePath();
            return;
        }
        this.mPrevState = i2;
        this.mCurState = i;
        if (z) {
            start();
        } else {
            this.mAnimProgress = 1.0f;
            updatePath();
        }
    }

    public boolean setLineState(int i, float f) {
        int i2 = this.mCurState;
        if (i2 != i) {
            this.mPrevState = i2;
            this.mCurState = i;
            this.mAnimProgress = f;
            updatePath();
            return true;
        }
        if (this.mAnimProgress == f) {
            return false;
        }
        this.mAnimProgress = f;
        updatePath();
        return true;
    }

    public int getLineState() {
        return this.mCurState;
    }

    public int getLineStateCount() {
        State[] stateArr = this.mStates;
        if (stateArr == null) {
            return 0;
        }
        return stateArr.length;
    }

    public float getAnimProgress() {
        return this.mAnimProgress;
    }

    private void updatePath() {
        this.mPath.reset();
        State[] stateArr = this.mStates;
        if (stateArr == null) {
            return;
        }
        if (this.mAnimProgress == 0.0f || (stateArr[this.mPrevState].links != null && this.mAnimProgress < 0.05f)) {
            updatePathWithState(this.mPath, this.mStates[this.mPrevState]);
        } else if (this.mAnimProgress == 1.0f || (this.mStates[this.mCurState].links != null && this.mAnimProgress > 0.95f)) {
            updatePathWithState(this.mPath, this.mStates[this.mCurState]);
        } else {
            Path path = this.mPath;
            State[] stateArr2 = this.mStates;
            updatePathBetweenStates(path, stateArr2[this.mPrevState], stateArr2[this.mCurState], this.mInterpolator.getInterpolation(this.mAnimProgress));
        }
        invalidateSelf();
    }

    private void updatePathWithState(Path path, State state) {
        boolean z;
        if (state.links != null) {
            for (int i = 0; i < state.links.length; i += 2) {
                int i2 = state.links[i] * 4;
                int i3 = state.links[i + 1] * 4;
                float x = getX(state.points[i2]);
                float y = getY(state.points[i2 + 1]);
                float x2 = getX(state.points[i2 + 2]);
                float y2 = getY(state.points[i2 + 3]);
                float x3 = getX(state.points[i3]);
                float y3 = getY(state.points[i3 + 1]);
                float x4 = getX(state.points[i3 + 2]);
                float y4 = getY(state.points[i3 + 3]);
                if (x == x3 && y == y3) {
                    path.moveTo(x2, y2);
                    path.lineTo(x, y);
                    path.lineTo(x4, y4);
                } else if (x == x4 && y == y4) {
                    path.moveTo(x2, y2);
                    path.lineTo(x, y);
                    path.lineTo(x3, y3);
                } else if (x2 == x3 && y2 == y3) {
                    path.moveTo(x, y);
                    path.lineTo(x2, y2);
                    path.lineTo(x4, y4);
                } else {
                    path.moveTo(x, y);
                    path.lineTo(x2, y2);
                    path.lineTo(x3, y3);
                }
            }
            int length = state.points.length / 4;
            for (int i4 = 0; i4 < length; i4++) {
                int i5 = 0;
                while (true) {
                    if (i5 >= state.links.length) {
                        z = false;
                        break;
                    } else {
                        if (state.links[i5] == i4) {
                            z = true;
                            break;
                        }
                        i5++;
                    }
                }
                if (!z) {
                    int i6 = i4 * 4;
                    path.moveTo(getX(state.points[i6]), getY(state.points[i6 + 1]));
                    path.lineTo(getX(state.points[i6 + 2]), getY(state.points[i6 + 3]));
                }
            }
            return;
        }
        int length2 = state.points.length / 4;
        for (int i7 = 0; i7 < length2; i7++) {
            int i8 = i7 * 4;
            path.moveTo(getX(state.points[i8]), getY(state.points[i8 + 1]));
            path.lineTo(getX(state.points[i8 + 2]), getY(state.points[i8 + 3]));
        }
    }

    private void updatePathBetweenStates(Path path, State state, State state2, float f) {
        float f2;
        float f3;
        float f4;
        float f5;
        float f6;
        float f7;
        float f8;
        int max = Math.max(state.points.length, state2.points.length) / 4;
        for (int i = 0; i < max; i++) {
            int i2 = i * 4;
            float f9 = 0.5f;
            if (i2 >= state.points.length) {
                f2 = 0.5f;
                f3 = 0.5f;
                f4 = 0.5f;
                f5 = 0.5f;
            } else {
                f2 = state.points[i2];
                f3 = state.points[i2 + 1];
                f4 = state.points[i2 + 2];
                f5 = state.points[i2 + 3];
            }
            if (i2 >= state2.points.length) {
                f8 = 0.5f;
                f6 = 0.5f;
                f7 = 0.5f;
            } else {
                f9 = state2.points[i2];
                f6 = state2.points[i2 + 1];
                f7 = state2.points[i2 + 2];
                f8 = state2.points[i2 + 3];
            }
            this.mPath.moveTo(getX(f2 + ((f9 - f2) * f)), getY(f3 + ((f6 - f3) * f)));
            this.mPath.lineTo(getX(f4 + ((f7 - f4) * f)), getY(f5 + ((f8 - f5) * f)));
        }
    }

    private float getX(float f) {
        return this.mDrawBound.left + (this.mDrawBound.width() * f);
    }

    private float getY(float f) {
        return this.mDrawBound.top + (this.mDrawBound.height() * f);
    }

    private void resetAnimation() {
        this.mStartTime = SystemClock.uptimeMillis();
        this.mAnimProgress = 0.0f;
    }

    @Override // android.graphics.drawable.Animatable
    public void start() {
        resetAnimation();
        scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        invalidateSelf();
    }

    @Override // android.graphics.drawable.Animatable
    public void stop() {
        if (isRunning()) {
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
        float min = Math.min(1.0f, ((float) (SystemClock.uptimeMillis() - this.mStartTime)) / this.mAnimDuration);
        if (min == 1.0f) {
            setLineState(this.mCurState, 1.0f);
            this.mRunning = false;
        } else {
            setLineState(this.mCurState, this.mInterpolator.getInterpolation(min));
        }
        if (isRunning()) {
            scheduleSelf(this.mUpdater, SystemClock.uptimeMillis() + 16);
        }
    }

    /* loaded from: classes2.dex */
    public static class State {
        int[] links;
        float[] points;

        public State() {
        }

        public State(float[] fArr, int[] iArr) {
            this.points = fArr;
            this.links = iArr;
        }
    }

    /* loaded from: classes2.dex */
    public static class Builder {
        private static final String TAG_ITEM = "item";
        private static final String TAG_LINKS = "links";
        private static final String TAG_POINTS = "points";
        private static final String TAG_STATE = "state";
        private static final String TAG_STATE_LIST = "state-list";
        private int mAnimDuration;
        private boolean mClockwise;
        private int mCurState;
        private Interpolator mInterpolator;
        private boolean mIsRtl;
        private int mPaddingBottom;
        private int mPaddingLeft;
        private int mPaddingRight;
        private int mPaddingTop;
        private State[] mStates;
        private Paint.Cap mStrokeCap;
        private int mStrokeColor;
        private Paint.Join mStrokeJoin;
        private int mStrokeSize;

        public Builder() {
        }

        public Builder(Context context, int i) {
            this(context, null, 0, i);
        }

        public Builder(Context context, AttributeSet attributeSet, int i, int i2) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.LineMorphingDrawable, i, i2);
            int resourceId = obtainStyledAttributes.getResourceId(R.styleable.LineMorphingDrawable_lmd_state, 0);
            if (resourceId != 0) {
                states(readStates(context, resourceId));
            }
            curState(obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_curState, 0));
            padding(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_padding, 0));
            paddingLeft(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_paddingLeft, this.mPaddingLeft));
            paddingTop(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_paddingTop, this.mPaddingTop));
            paddingRight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_paddingRight, this.mPaddingRight));
            paddingBottom(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_paddingBottom, this.mPaddingBottom));
            animDuration(obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_animDuration, context.getResources().getInteger(android.R.integer.config_mediumAnimTime)));
            int resourceId2 = obtainStyledAttributes.getResourceId(R.styleable.LineMorphingDrawable_lmd_interpolator, 0);
            if (resourceId2 != 0) {
                interpolator(AnimationUtils.loadInterpolator(context, resourceId2));
            }
            strokeSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.LineMorphingDrawable_lmd_strokeSize, ThemeUtil.dpToPx(context, 3)));
            strokeColor(obtainStyledAttributes.getColor(R.styleable.LineMorphingDrawable_lmd_strokeColor, -1));
            int integer = obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_strokeCap, 0);
            if (integer == 0) {
                strokeCap(Paint.Cap.BUTT);
            } else if (integer == 1) {
                strokeCap(Paint.Cap.ROUND);
            } else {
                strokeCap(Paint.Cap.SQUARE);
            }
            int integer2 = obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_strokeJoin, 0);
            if (integer2 == 0) {
                strokeJoin(Paint.Join.MITER);
            } else if (integer2 == 1) {
                strokeJoin(Paint.Join.ROUND);
            } else {
                strokeJoin(Paint.Join.BEVEL);
            }
            clockwise(obtainStyledAttributes.getBoolean(R.styleable.LineMorphingDrawable_lmd_clockwise, true));
            int integer3 = obtainStyledAttributes.getInteger(R.styleable.LineMorphingDrawable_lmd_layoutDirection, 0);
            if (integer3 == 3) {
                rtl(TextUtilsCompat.getLayoutDirectionFromLocale(Locale.getDefault()) == 1);
            } else {
                rtl(integer3 == 1);
            }
            obtainStyledAttributes.recycle();
        }

        /* JADX WARN: Code restructure failed: missing block: B:100:0x0174, code lost:
        
            if (r2 == null) goto L114;
         */
        /* JADX WARN: Code restructure failed: missing block: B:102:0x018f, code lost:
        
            if (r0.isEmpty() == false) goto L118;
         */
        /* JADX WARN: Code restructure failed: missing block: B:103:0x0191, code lost:
        
            return null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:106:0x019f, code lost:
        
            return (com.rey.material.drawable.LineMorphingDrawable.State[]) r0.toArray(new com.rey.material.drawable.LineMorphingDrawable.State[r0.size()]);
         */
        /* JADX WARN: Code restructure failed: missing block: B:107:0x0188, code lost:
        
            r2.close();
         */
        /* JADX WARN: Code restructure failed: missing block: B:116:0x0186, code lost:
        
            if (r2 == null) goto L114;
         */
        /* JADX WARN: Code restructure failed: missing block: B:11:0x0045, code lost:
        
            r7 = new java.util.ArrayList();
            r8 = new java.lang.StringBuilder();
         */
        /* JADX WARN: Code restructure failed: missing block: B:12:0x004f, code lost:
        
            r10 = false;
            r11 = false;
            r12 = null;
            r13 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:13:0x0053, code lost:
        
            if (r10 != false) goto L126;
         */
        /* JADX WARN: Code restructure failed: missing block: B:14:0x0055, code lost:
        
            if (r3 == r6) goto L98;
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x0062, code lost:
        
            if (r3 == r5) goto L68;
         */
        /* JADX WARN: Code restructure failed: missing block: B:18:0x0065, code lost:
        
            if (r3 == 3) goto L25;
         */
        /* JADX WARN: Code restructure failed: missing block: B:19:0x0067, code lost:
        
            if (r3 == 4) goto L24;
         */
        /* JADX WARN: Code restructure failed: missing block: B:20:0x006b, code lost:
        
            r8.append(r2.getText());
         */
        /* JADX WARN: Code restructure failed: missing block: B:23:0x016f, code lost:
        
            r3 = r2.next();
            r5 = 2;
            r6 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:24:0x0074, code lost:
        
            r3 = r2.getName();
         */
        /* JADX WARN: Code restructure failed: missing block: B:25:0x0078, code lost:
        
            if (r11 == false) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:27:0x007e, code lost:
        
            if (r3.equals(r12) == false) goto L30;
         */
        /* JADX WARN: Code restructure failed: missing block: B:28:0x0080, code lost:
        
            r11 = false;
            r12 = null;
         */
        /* JADX WARN: Code restructure failed: missing block: B:30:0x0086, code lost:
        
            switch(r3.hashCode()) {
                case -982754077: goto L45;
                case -273989542: goto L42;
                case 3242771: goto L39;
                case 102977465: goto L36;
                case 109757585: goto L33;
                default: goto L32;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:32:0x00b2, code lost:
        
            r1 = 65535;
         */
        /* JADX WARN: Code restructure failed: missing block: B:33:0x00b3, code lost:
        
            if (r1 == 0) goto L67;
         */
        /* JADX WARN: Code restructure failed: missing block: B:35:0x00b6, code lost:
        
            if (r1 == 1) goto L66;
         */
        /* JADX WARN: Code restructure failed: missing block: B:37:0x00b9, code lost:
        
            if (r1 == 2) goto L62;
         */
        /* JADX WARN: Code restructure failed: missing block: B:38:0x00bb, code lost:
        
            if (r1 == 3) goto L58;
         */
        /* JADX WARN: Code restructure failed: missing block: B:39:0x00bd, code lost:
        
            if (r1 == 4) goto L57;
         */
        /* JADX WARN: Code restructure failed: missing block: B:41:0x00c0, code lost:
        
            r7.add(r8.toString());
         */
        /* JADX WARN: Code restructure failed: missing block: B:42:0x00c8, code lost:
        
            r13.links = new int[r7.size()];
            r1 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:44:0x00d4, code lost:
        
            if (r1 >= r13.links.length) goto L127;
         */
        /* JADX WARN: Code restructure failed: missing block: B:45:0x00d6, code lost:
        
            r13.links[r1] = java.lang.Integer.parseInt((java.lang.String) r7.get(r1));
            r1 = r1 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:47:0x00e7, code lost:
        
            r13.points = new float[r7.size()];
            r1 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:49:0x00f3, code lost:
        
            if (r1 >= r13.points.length) goto L128;
         */
        /* JADX WARN: Code restructure failed: missing block: B:50:0x00f5, code lost:
        
            r13.points[r1] = java.lang.Float.parseFloat((java.lang.String) r7.get(r1));
            r1 = r1 + 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:52:0x0106, code lost:
        
            r0.add(r13);
         */
        /* JADX WARN: Code restructure failed: missing block: B:53:0x010a, code lost:
        
            r10 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:55:0x008e, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_STATE) == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:56:0x0090, code lost:
        
            r1 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:58:0x0096, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_LINKS) == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:59:0x0098, code lost:
        
            r1 = 3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:61:0x009e, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_ITEM) == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:62:0x00a0, code lost:
        
            r1 = 4;
         */
        /* JADX WARN: Code restructure failed: missing block: B:64:0x00a6, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_STATE_LIST) == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:65:0x00a8, code lost:
        
            r1 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:67:0x00ae, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_POINTS) == false) goto L48;
         */
        /* JADX WARN: Code restructure failed: missing block: B:68:0x00b0, code lost:
        
            r1 = 2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:69:0x010c, code lost:
        
            if (r11 == false) goto L70;
         */
        /* JADX WARN: Code restructure failed: missing block: B:70:0x0112, code lost:
        
            r3 = r2.getName();
         */
        /* JADX WARN: Code restructure failed: missing block: B:71:0x011a, code lost:
        
            switch(r3.hashCode()) {
                case -982754077: goto L82;
                case 3242771: goto L79;
                case 102977465: goto L76;
                case 109757585: goto L73;
                default: goto L72;
            };
         */
        /* JADX WARN: Code restructure failed: missing block: B:73:0x013e, code lost:
        
            r1 = 65535;
         */
        /* JADX WARN: Code restructure failed: missing block: B:74:0x013f, code lost:
        
            if (r1 == 0) goto L97;
         */
        /* JADX WARN: Code restructure failed: missing block: B:76:0x0142, code lost:
        
            if (r1 == 1) goto L95;
         */
        /* JADX WARN: Code restructure failed: missing block: B:78:0x0145, code lost:
        
            if (r1 == 2) goto L94;
         */
        /* JADX WARN: Code restructure failed: missing block: B:79:0x0147, code lost:
        
            if (r1 == 3) goto L93;
         */
        /* JADX WARN: Code restructure failed: missing block: B:80:0x0149, code lost:
        
            r12 = r3;
            r11 = true;
         */
        /* JADX WARN: Code restructure failed: missing block: B:81:0x014d, code lost:
        
            r8.delete(0, r8.length());
         */
        /* JADX WARN: Code restructure failed: missing block: B:83:0x015a, code lost:
        
            r7.clear();
         */
        /* JADX WARN: Code restructure failed: missing block: B:85:0x015e, code lost:
        
            r13 = new com.rey.material.drawable.LineMorphingDrawable.State();
         */
        /* JADX WARN: Code restructure failed: missing block: B:87:0x0122, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_STATE) == false) goto L85;
         */
        /* JADX WARN: Code restructure failed: missing block: B:88:0x0124, code lost:
        
            r1 = 0;
         */
        /* JADX WARN: Code restructure failed: missing block: B:90:0x012a, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_LINKS) == false) goto L85;
         */
        /* JADX WARN: Code restructure failed: missing block: B:91:0x012c, code lost:
        
            r1 = 2;
         */
        /* JADX WARN: Code restructure failed: missing block: B:93:0x0132, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_ITEM) == false) goto L85;
         */
        /* JADX WARN: Code restructure failed: missing block: B:94:0x0134, code lost:
        
            r1 = 3;
         */
        /* JADX WARN: Code restructure failed: missing block: B:96:0x013a, code lost:
        
            if (r3.equals(com.rey.material.drawable.LineMorphingDrawable.Builder.TAG_POINTS) == false) goto L85;
         */
        /* JADX WARN: Code restructure failed: missing block: B:97:0x013c, code lost:
        
            r1 = 1;
         */
        /* JADX WARN: Code restructure failed: missing block: B:98:0x0167, code lost:
        
            r10 = true;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        private com.rey.material.drawable.LineMorphingDrawable.State[] readStates(android.content.Context r19, int r20) {
            /*
                Method dump skipped, instructions count: 456
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.rey.material.drawable.LineMorphingDrawable.Builder.readStates(android.content.Context, int):com.rey.material.drawable.LineMorphingDrawable$State[]");
        }

        public LineMorphingDrawable build() {
            if (this.mStrokeCap == null) {
                this.mStrokeCap = Paint.Cap.BUTT;
            }
            if (this.mStrokeJoin == null) {
                this.mStrokeJoin = Paint.Join.MITER;
            }
            if (this.mInterpolator == null) {
                this.mInterpolator = new AccelerateInterpolator();
            }
            return new LineMorphingDrawable(this.mStates, this.mCurState, this.mPaddingLeft, this.mPaddingTop, this.mPaddingRight, this.mPaddingBottom, this.mAnimDuration, this.mInterpolator, this.mStrokeSize, this.mStrokeColor, this.mStrokeCap, this.mStrokeJoin, this.mClockwise, this.mIsRtl);
        }

        public Builder states(State... stateArr) {
            this.mStates = stateArr;
            return this;
        }

        public Builder curState(int i) {
            this.mCurState = i;
            return this;
        }

        public Builder padding(int i) {
            this.mPaddingLeft = i;
            this.mPaddingTop = i;
            this.mPaddingRight = i;
            this.mPaddingBottom = i;
            return this;
        }

        public Builder paddingLeft(int i) {
            this.mPaddingLeft = i;
            return this;
        }

        public Builder paddingTop(int i) {
            this.mPaddingTop = i;
            return this;
        }

        public Builder paddingRight(int i) {
            this.mPaddingRight = i;
            return this;
        }

        public Builder paddingBottom(int i) {
            this.mPaddingBottom = i;
            return this;
        }

        public Builder animDuration(int i) {
            this.mAnimDuration = i;
            return this;
        }

        public Builder interpolator(Interpolator interpolator) {
            this.mInterpolator = interpolator;
            return this;
        }

        public Builder strokeSize(int i) {
            this.mStrokeSize = i;
            return this;
        }

        public Builder strokeColor(int i) {
            this.mStrokeColor = i;
            return this;
        }

        public Builder strokeCap(Paint.Cap cap) {
            this.mStrokeCap = cap;
            return this;
        }

        public Builder strokeJoin(Paint.Join join) {
            this.mStrokeJoin = join;
            return this;
        }

        public Builder clockwise(boolean z) {
            this.mClockwise = z;
            return this;
        }

        public Builder rtl(boolean z) {
            this.mIsRtl = z;
            return this;
        }
    }
}
