package com.rey.material.app;

import android.os.Build;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.rey.material.app.ThemeManager;
import com.rey.material.drawable.NavigationDrawerDrawable;
import com.rey.material.drawable.ToolbarRippleDrawable;
import com.rey.material.util.ViewUtil;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

/* loaded from: classes2.dex */
public class ToolbarManager {
    private ArrayList<Animation> mAnimations;
    private Animator mAnimator;
    private AppCompatDelegate mAppCompatDelegate;
    private ToolbarRippleDrawable.Builder mBuilder;
    private int mCurrentGroup;
    private boolean mGroupChanged;
    private ArrayList<WeakReference<OnToolbarGroupChangedListener>> mListeners;
    private boolean mMenuDataChanged;
    private ActionMenuView mMenuView;
    private NavigationManager mNavigationManager;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    private Animation.AnimationListener mOutAnimationEndListener;
    private int mRippleStyle;
    private Toolbar mToolbar;

    /* loaded from: classes2.dex */
    public interface Animator {
        Animation getInAnimation(View view, int i);

        Animation getOutAnimation(View view, int i);
    }

    /* loaded from: classes2.dex */
    public interface OnToolbarGroupChangedListener {
        void onToolbarGroupChanged(int i, int i2);
    }

    public ToolbarManager(AppCompatDelegate appCompatDelegate, Toolbar toolbar, int i, int i2, int i3, int i4) {
        this(appCompatDelegate, toolbar, i, i2, new SimpleAnimator(i3, i4));
    }

    public ToolbarManager(AppCompatDelegate appCompatDelegate, Toolbar toolbar, int i, int i2, Animator animator) {
        this.mCurrentGroup = 0;
        this.mGroupChanged = false;
        this.mMenuDataChanged = true;
        this.mListeners = new ArrayList<>();
        this.mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.rey.material.app.ToolbarManager.1
            @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
            public void onGlobalLayout() {
                ToolbarManager.this.onGlobalLayout();
            }
        };
        this.mAnimations = new ArrayList<>();
        this.mOutAnimationEndListener = new Animation.AnimationListener() { // from class: com.rey.material.app.ToolbarManager.2
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                if (ToolbarManager.this.mAppCompatDelegate != null) {
                    ToolbarManager.this.mAppCompatDelegate.invalidateOptionsMenu();
                } else {
                    ToolbarManager.this.onPrepareMenu();
                }
            }
        };
        this.mAppCompatDelegate = appCompatDelegate;
        this.mToolbar = toolbar;
        this.mCurrentGroup = i;
        this.mRippleStyle = i2;
        this.mAnimator = animator;
        appCompatDelegate.setSupportActionBar(toolbar);
    }

    public void registerOnToolbarGroupChangedListener(OnToolbarGroupChangedListener onToolbarGroupChangedListener) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            WeakReference<OnToolbarGroupChangedListener> weakReference = this.mListeners.get(size);
            if (weakReference.get() == null) {
                this.mListeners.remove(size);
            } else if (weakReference.get() == onToolbarGroupChangedListener) {
                return;
            }
        }
        this.mListeners.add(new WeakReference<>(onToolbarGroupChangedListener));
    }

    public void unregisterOnToolbarGroupChangedListener(OnToolbarGroupChangedListener onToolbarGroupChangedListener) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            WeakReference<OnToolbarGroupChangedListener> weakReference = this.mListeners.get(size);
            if (weakReference.get() == null || weakReference.get() == onToolbarGroupChangedListener) {
                this.mListeners.remove(size);
            }
        }
    }

    private void dispatchOnToolbarGroupChanged(int i, int i2) {
        for (int size = this.mListeners.size() - 1; size >= 0; size--) {
            WeakReference<OnToolbarGroupChangedListener> weakReference = this.mListeners.get(size);
            if (weakReference.get() == null) {
                this.mListeners.remove(size);
            } else {
                weakReference.get().onToolbarGroupChanged(i, i2);
            }
        }
    }

    public int getCurrentGroup() {
        return this.mCurrentGroup;
    }

    public void setCurrentGroup(int i) {
        int i2 = this.mCurrentGroup;
        if (i2 != i) {
            this.mCurrentGroup = i;
            this.mGroupChanged = true;
            dispatchOnToolbarGroupChanged(i2, i);
            animateOut();
        }
    }

    public void createMenu(int i) {
        this.mToolbar.inflateMenu(i);
        this.mMenuDataChanged = true;
        if (this.mAppCompatDelegate == null) {
            onPrepareMenu();
        }
    }

    public void onPrepareMenu() {
        if (this.mGroupChanged || this.mMenuDataChanged) {
            this.mToolbar.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
            Menu menu = this.mToolbar.getMenu();
            int size = menu.size();
            for (int i = 0; i < size; i++) {
                MenuItem item = menu.getItem(i);
                item.setVisible(item.getGroupId() == this.mCurrentGroup || item.getGroupId() == 0);
            }
            this.mMenuDataChanged = false;
        }
    }

    public void setNavigationManager(NavigationManager navigationManager) {
        this.mNavigationManager = navigationManager;
        notifyNavigationStateInvalidated();
    }

    public void notifyNavigationStateInvalidated() {
        NavigationManager navigationManager = this.mNavigationManager;
        if (navigationManager != null) {
            navigationManager.notifyStateInvalidated();
        }
    }

    public void notifyNavigationStateChanged() {
        NavigationManager navigationManager = this.mNavigationManager;
        if (navigationManager != null) {
            navigationManager.notifyStateChanged();
        }
    }

    public void notifyNavigationStateProgressChanged(boolean z, float f) {
        NavigationManager navigationManager = this.mNavigationManager;
        if (navigationManager != null) {
            navigationManager.notifyStateProgressChanged(z, f);
        }
    }

    public boolean isNavigationBackState() {
        NavigationManager navigationManager = this.mNavigationManager;
        return navigationManager != null && navigationManager.isBackState();
    }

    private ToolbarRippleDrawable getBackground() {
        if (this.mBuilder == null) {
            this.mBuilder = new ToolbarRippleDrawable.Builder(this.mToolbar.getContext(), this.mRippleStyle);
        }
        return this.mBuilder.build();
    }

    private ActionMenuView getMenuView() {
        if (this.mMenuView == null) {
            int i = 0;
            while (true) {
                if (i >= this.mToolbar.getChildCount()) {
                    break;
                }
                View childAt = this.mToolbar.getChildAt(i);
                if (childAt instanceof ActionMenuView) {
                    this.mMenuView = (ActionMenuView) childAt;
                    break;
                }
                i++;
            }
        }
        return this.mMenuView;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onGlobalLayout() {
        if (Build.VERSION.SDK_INT >= 16) {
            this.mToolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        } else {
            this.mToolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this.mOnGlobalLayoutListener);
        }
        ActionMenuView menuView = getMenuView();
        int childCount = menuView == null ? 0 : menuView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = menuView.getChildAt(i);
            if (this.mRippleStyle != 0 && (childAt.getBackground() == null || !(childAt.getBackground() instanceof ToolbarRippleDrawable))) {
                ViewUtil.setBackground(childAt, getBackground());
            }
        }
        if (this.mGroupChanged) {
            animateIn();
            this.mGroupChanged = false;
        }
    }

    private void animateOut() {
        ActionMenuView menuView = getMenuView();
        int childCount = menuView == null ? 0 : menuView.getChildCount();
        this.mAnimations.clear();
        this.mAnimations.ensureCapacity(childCount);
        Animation animation = null;
        for (int i = 0; i < childCount; i++) {
            Animation outAnimation = this.mAnimator.getOutAnimation(menuView.getChildAt(i), i);
            this.mAnimations.add(outAnimation);
            if (outAnimation != null && (animation == null || animation.getStartOffset() + animation.getDuration() < outAnimation.getStartOffset() + outAnimation.getDuration())) {
                animation = outAnimation;
            }
        }
        if (animation == null) {
            this.mOutAnimationEndListener.onAnimationEnd(null);
        } else {
            animation.setAnimationListener(this.mOutAnimationEndListener);
            for (int i2 = 0; i2 < childCount; i2++) {
                Animation animation2 = this.mAnimations.get(i2);
                if (animation2 != null) {
                    menuView.getChildAt(i2).startAnimation(animation2);
                }
            }
        }
        this.mAnimations.clear();
    }

    private void animateIn() {
        ActionMenuView menuView = getMenuView();
        int childCount = menuView == null ? 0 : menuView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = menuView.getChildAt(i);
            Animation inAnimation = this.mAnimator.getInAnimation(childAt, i);
            if (inAnimation != null) {
                childAt.startAnimation(inAnimation);
            }
        }
    }

    /* loaded from: classes2.dex */
    private static class SimpleAnimator implements Animator {
        private int mAnimationIn;
        private int mAnimationOut;

        public SimpleAnimator(int i, int i2) {
            this.mAnimationIn = i;
            this.mAnimationOut = i2;
        }

        @Override // com.rey.material.app.ToolbarManager.Animator
        public Animation getOutAnimation(View view, int i) {
            if (this.mAnimationOut == 0) {
                return null;
            }
            return AnimationUtils.loadAnimation(view.getContext(), this.mAnimationOut);
        }

        @Override // com.rey.material.app.ToolbarManager.Animator
        public Animation getInAnimation(View view, int i) {
            if (this.mAnimationIn == 0) {
                return null;
            }
            return AnimationUtils.loadAnimation(view.getContext(), this.mAnimationIn);
        }
    }

    /* loaded from: classes2.dex */
    public static abstract class NavigationManager {
        protected NavigationDrawerDrawable mNavigationIcon;
        protected Toolbar mToolbar;

        public abstract boolean isBackState();

        public abstract void onNavigationClick();

        public NavigationManager(NavigationDrawerDrawable navigationDrawerDrawable, Toolbar toolbar) {
            this.mToolbar = toolbar;
            this.mNavigationIcon = navigationDrawerDrawable;
            toolbar.setNavigationIcon(navigationDrawerDrawable);
            this.mToolbar.setNavigationOnClickListener(new View.OnClickListener() { // from class: com.rey.material.app.ToolbarManager.NavigationManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NavigationManager.this.onNavigationClick();
                }
            });
        }

        public void notifyStateInvalidated() {
            this.mNavigationIcon.switchIconState(isBackState() ? 1 : 0, false);
        }

        public void notifyStateChanged() {
            this.mNavigationIcon.switchIconState(isBackState() ? 1 : 0, true);
        }

        public void notifyStateProgressChanged(boolean z, float f) {
            this.mNavigationIcon.setIconState(z ? 1 : 0, f);
        }
    }

    /* loaded from: classes2.dex */
    public static class BaseNavigationManager extends NavigationManager {
        protected DrawerLayout mDrawerLayout;
        protected FragmentManager mFragmentManager;

        protected void onDrawerClosed(View view) {
        }

        protected void onDrawerOpened(View view) {
        }

        protected void onDrawerStateChanged(int i) {
        }

        @Override // com.rey.material.app.ToolbarManager.NavigationManager
        public void onNavigationClick() {
        }

        public BaseNavigationManager(int i, FragmentManager fragmentManager, Toolbar toolbar, DrawerLayout drawerLayout) {
            super(new NavigationDrawerDrawable.Builder(toolbar.getContext(), i).build(), toolbar);
            this.mDrawerLayout = drawerLayout;
            this.mFragmentManager = fragmentManager;
            if (drawerLayout != null) {
                drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() { // from class: com.rey.material.app.ToolbarManager.BaseNavigationManager.1
                    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                    public void onDrawerSlide(View view, float f) {
                        BaseNavigationManager.this.onDrawerSlide(view, f);
                    }

                    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                    public void onDrawerOpened(View view) {
                        BaseNavigationManager.this.onDrawerOpened(view);
                    }

                    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                    public void onDrawerClosed(View view) {
                        BaseNavigationManager.this.onDrawerClosed(view);
                    }

                    @Override // androidx.drawerlayout.widget.DrawerLayout.DrawerListener
                    public void onDrawerStateChanged(int i2) {
                        BaseNavigationManager.this.onDrawerStateChanged(i2);
                    }
                });
            }
            this.mFragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() { // from class: com.rey.material.app.ToolbarManager.BaseNavigationManager.2
                @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
                public /* synthetic */ void onBackStackChangeCommitted(Fragment fragment, boolean z) {
                    FragmentManager.OnBackStackChangedListener.CC.$default$onBackStackChangeCommitted(this, fragment, z);
                }

                @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
                public /* synthetic */ void onBackStackChangeStarted(Fragment fragment, boolean z) {
                    FragmentManager.OnBackStackChangedListener.CC.$default$onBackStackChangeStarted(this, fragment, z);
                }

                @Override // androidx.fragment.app.FragmentManager.OnBackStackChangedListener
                public void onBackStackChanged() {
                    BaseNavigationManager.this.onFragmentChanged();
                }
            });
        }

        @Override // com.rey.material.app.ToolbarManager.NavigationManager
        public boolean isBackState() {
            if (this.mFragmentManager.getBackStackEntryCount() > 1) {
                return true;
            }
            DrawerLayout drawerLayout = this.mDrawerLayout;
            return drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START);
        }

        protected boolean shouldSyncDrawerSlidingProgress() {
            return this.mFragmentManager.getBackStackEntryCount() <= 1;
        }

        protected void onFragmentChanged() {
            notifyStateChanged();
        }

        protected void onDrawerSlide(View view, float f) {
            if (!shouldSyncDrawerSlidingProgress()) {
                notifyStateInvalidated();
            } else if (this.mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                notifyStateProgressChanged(false, 1.0f - f);
            } else {
                notifyStateProgressChanged(true, f);
            }
        }
    }

    /* loaded from: classes2.dex */
    public static class ThemableNavigationManager extends BaseNavigationManager implements ThemeManager.OnThemeChangedListener {
        private int mCurrentStyle;
        private int mStyleId;

        public ThemableNavigationManager(int i, FragmentManager fragmentManager, Toolbar toolbar, DrawerLayout drawerLayout) {
            super(ThemeManager.getInstance().getCurrentStyle(i), fragmentManager, toolbar, drawerLayout);
            this.mStyleId = i;
            this.mCurrentStyle = ThemeManager.getInstance().getCurrentStyle(i);
            ThemeManager.getInstance().registerOnThemeChangedListener(this);
        }

        @Override // com.rey.material.app.ThemeManager.OnThemeChangedListener
        public void onThemeChanged(ThemeManager.OnThemeChangedEvent onThemeChangedEvent) {
            int currentStyle = ThemeManager.getInstance().getCurrentStyle(this.mStyleId);
            if (this.mCurrentStyle != currentStyle) {
                this.mCurrentStyle = currentStyle;
                NavigationDrawerDrawable build = new NavigationDrawerDrawable.Builder(this.mToolbar.getContext(), this.mCurrentStyle).build();
                build.switchIconState(this.mNavigationIcon.getIconState(), false);
                this.mNavigationIcon = build;
                this.mToolbar.setNavigationIcon(this.mNavigationIcon);
            }
        }
    }
}
