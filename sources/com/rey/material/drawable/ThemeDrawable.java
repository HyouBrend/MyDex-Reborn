package com.rey.material.drawable;

import android.graphics.drawable.LevelListDrawable;
import com.rey.material.app.ThemeManager;

/* loaded from: classes2.dex */
public class ThemeDrawable extends LevelListDrawable implements ThemeManager.OnThemeChangedListener {
    private int mStyleId;

    public ThemeDrawable(int i) {
        this.mStyleId = i;
        if (i != 0) {
            ThemeManager.getInstance().registerOnThemeChangedListener(this);
            initDrawables();
        }
    }

    private void initDrawables() {
        ThemeManager themeManager = ThemeManager.getInstance();
        int themeCount = themeManager.getThemeCount();
        for (int i = 0; i < themeCount; i++) {
            addLevel(i, i, themeManager.getContext().getResources().getDrawable(themeManager.getStyle(this.mStyleId, i)));
        }
        setLevel(themeManager.getCurrentTheme());
    }

    @Override // com.rey.material.app.ThemeManager.OnThemeChangedListener
    public void onThemeChanged(ThemeManager.OnThemeChangedEvent onThemeChangedEvent) {
        if (getLevel() != onThemeChangedEvent.theme) {
            setLevel(onThemeChangedEvent.theme);
        }
    }
}
