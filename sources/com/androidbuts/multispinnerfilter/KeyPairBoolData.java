package com.androidbuts.multispinnerfilter;

/* loaded from: classes.dex */
public class KeyPairBoolData {
    private long id;
    private boolean isSelected;
    private String name;
    private Object object;

    public KeyPairBoolData() {
    }

    public KeyPairBoolData(String str, boolean z) {
        this.name = str;
        this.isSelected = z;
    }

    public Object getObject() {
        return this.object;
    }

    public void setObject(Object obj) {
        this.object = obj;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long j) {
        this.id = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean z) {
        this.isSelected = z;
    }
}
