package com.rey.material.widget;

import android.content.Context;
import android.util.AttributeSet;
import com.rey.material.drawable.RadioButtonDrawable;

/* loaded from: classes2.dex */
public class RadioButton extends CompoundButton {
    public RadioButton(Context context) {
        super(context);
    }

    public RadioButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public RadioButton(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public RadioButton(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.rey.material.widget.CompoundButton
    public void applyStyle(Context context, AttributeSet attributeSet, int i, int i2) {
        super.applyStyle(context, attributeSet, i, i2);
        RadioButtonDrawable build = new RadioButtonDrawable.Builder(context, attributeSet, i, i2).build();
        build.setInEditMode(isInEditMode());
        build.setAnimEnable(false);
        setButtonDrawable(build);
        build.setAnimEnable(true);
    }

    @Override // android.widget.CompoundButton, android.widget.Checkable
    public void toggle() {
        if (isChecked()) {
            return;
        }
        super.toggle();
    }

    public void setCheckedImmediately(boolean z) {
        if (getButtonDrawable() instanceof RadioButtonDrawable) {
            RadioButtonDrawable radioButtonDrawable = (RadioButtonDrawable) getButtonDrawable();
            radioButtonDrawable.setAnimEnable(false);
            setChecked(z);
            radioButtonDrawable.setAnimEnable(true);
            return;
        }
        setChecked(z);
    }
}
