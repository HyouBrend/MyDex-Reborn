package com.rey.material.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.SparseArray;
import com.rey.material.R;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ThemeManager {
    private static final String KEY_THEME = "theme";
    private static final String PREF = "theme.pref";
    public static final int THEME_UNDEFINED = Integer.MIN_VALUE;
    private static volatile ThemeManager mInstance;
    private Context mContext;
    private int mCurrentTheme;
    private EventDispatcher mDispatcher;
    private SparseArray<int[]> mStyles = new SparseArray<>();
    private int mThemeCount;

    /* loaded from: classes2.dex */
    public interface EventDispatcher {
        void dispatchThemeChanged(int i);

        void registerListener(OnThemeChangedListener onThemeChangedListener);

        void unregisterListener(OnThemeChangedListener onThemeChangedListener);
    }

    /* loaded from: classes2.dex */
    public interface OnThemeChangedListener {
        void onThemeChanged(OnThemeChangedEvent onThemeChangedEvent);
    }

    public static int getStyleId(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ThemableView, i, i2);
        int resourceId = obtainStyledAttributes.getResourceId(R.styleable.ThemableView_v_styleId, 0);
        obtainStyledAttributes.recycle();
        return resourceId;
    }

    public static void init(Context context, int i, int i2, EventDispatcher eventDispatcher) {
        getInstance().setup(context, i, i2, eventDispatcher);
    }

    public static ThemeManager getInstance() {
        if (mInstance == null) {
            synchronized (ThemeManager.class) {
                if (mInstance == null) {
                    mInstance = new ThemeManager();
                }
            }
        }
        return mInstance;
    }

    protected void setup(Context context, int i, int i2, EventDispatcher eventDispatcher) {
        this.mContext = context;
        if (eventDispatcher == null) {
            eventDispatcher = new SimpleDispatcher();
        }
        this.mDispatcher = eventDispatcher;
        this.mThemeCount = i;
        SharedPreferences sharedPreferences = getSharedPreferences(this.mContext);
        if (sharedPreferences != null) {
            this.mCurrentTheme = sharedPreferences.getInt(KEY_THEME, i2);
        } else {
            this.mCurrentTheme = i2;
        }
        if (this.mCurrentTheme >= this.mThemeCount) {
            setCurrentTheme(i2);
        }
    }

    private int[] loadStyleList(Context context, int i) {
        if (context == null) {
            return null;
        }
        TypedArray obtainTypedArray = context.getResources().obtainTypedArray(i);
        int length = obtainTypedArray.length();
        int[] iArr = new int[length];
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = obtainTypedArray.getResourceId(i2, 0);
        }
        obtainTypedArray.recycle();
        return iArr;
    }

    private int[] getStyleList(int i) {
        SparseArray<int[]> sparseArray = this.mStyles;
        if (sparseArray == null) {
            return null;
        }
        int[] iArr = sparseArray.get(i);
        if (iArr != null) {
            return iArr;
        }
        int[] loadStyleList = loadStyleList(this.mContext, i);
        this.mStyles.put(i, loadStyleList);
        return loadStyleList;
    }

    private SharedPreferences getSharedPreferences(Context context) {
        if (context == null) {
            return null;
        }
        return context.getSharedPreferences(PREF, 0);
    }

    private void dispatchThemeChanged(int i) {
        EventDispatcher eventDispatcher = this.mDispatcher;
        if (eventDispatcher != null) {
            eventDispatcher.dispatchThemeChanged(i);
        }
    }

    public Context getContext() {
        return this.mContext;
    }

    public int getCurrentTheme() {
        return this.mCurrentTheme;
    }

    public boolean setCurrentTheme(int i) {
        if (Looper.getMainLooper().getThread() != Thread.currentThread() || this.mCurrentTheme == i) {
            return false;
        }
        this.mCurrentTheme = i;
        SharedPreferences sharedPreferences = getSharedPreferences(this.mContext);
        if (sharedPreferences != null) {
            sharedPreferences.edit().putInt(KEY_THEME, this.mCurrentTheme).apply();
        }
        dispatchThemeChanged(this.mCurrentTheme);
        return true;
    }

    public int getThemeCount() {
        return this.mThemeCount;
    }

    public int getCurrentStyle(int i) {
        return getStyle(i, this.mCurrentTheme);
    }

    public int getStyle(int i, int i2) {
        int[] styleList = getStyleList(i);
        if (styleList == null) {
            return 0;
        }
        return styleList[i2];
    }

    public void registerOnThemeChangedListener(OnThemeChangedListener onThemeChangedListener) {
        EventDispatcher eventDispatcher = this.mDispatcher;
        if (eventDispatcher != null) {
            eventDispatcher.registerListener(onThemeChangedListener);
        }
    }

    public void unregisterOnThemeChangedListener(OnThemeChangedListener onThemeChangedListener) {
        EventDispatcher eventDispatcher = this.mDispatcher;
        if (eventDispatcher != null) {
            eventDispatcher.unregisterListener(onThemeChangedListener);
        }
    }

    /* loaded from: classes2.dex */
    public static class SimpleDispatcher implements EventDispatcher {
        ArrayList<WeakReference<OnThemeChangedListener>> mListeners = new ArrayList<>();

        @Override // com.rey.material.app.ThemeManager.EventDispatcher
        public void registerListener(OnThemeChangedListener onThemeChangedListener) {
            boolean z = false;
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                WeakReference<OnThemeChangedListener> weakReference = this.mListeners.get(size);
                if (weakReference.get() == null) {
                    this.mListeners.remove(size);
                } else if (weakReference.get() == onThemeChangedListener) {
                    z = true;
                }
            }
            if (z) {
                return;
            }
            this.mListeners.add(new WeakReference<>(onThemeChangedListener));
        }

        @Override // com.rey.material.app.ThemeManager.EventDispatcher
        public void unregisterListener(OnThemeChangedListener onThemeChangedListener) {
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                WeakReference<OnThemeChangedListener> weakReference = this.mListeners.get(size);
                if (weakReference.get() == null || weakReference.get() == onThemeChangedListener) {
                    this.mListeners.remove(size);
                }
            }
        }

        @Override // com.rey.material.app.ThemeManager.EventDispatcher
        public void dispatchThemeChanged(int i) {
            OnThemeChangedEvent onThemeChangedEvent = new OnThemeChangedEvent(i);
            for (int size = this.mListeners.size() - 1; size >= 0; size--) {
                WeakReference<OnThemeChangedListener> weakReference = this.mListeners.get(size);
                if (weakReference.get() == null) {
                    this.mListeners.remove(size);
                } else {
                    weakReference.get().onThemeChanged(onThemeChangedEvent);
                }
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class OnThemeChangedEvent {
        public final int theme;

        public OnThemeChangedEvent(int i) {
            this.theme = i;
        }
    }
}
