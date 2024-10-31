package com.google.android.gms.internal.places;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [FieldDescriptorType] */
/* loaded from: classes.dex */
public final class zzde<FieldDescriptorType> extends zzdb<FieldDescriptorType, Object> {
    /* JADX INFO: Access modifiers changed from: package-private */
    public zzde(int i) {
        super(i, null);
    }

    @Override // com.google.android.gms.internal.places.zzdb
    public final void zzab() {
        if (!isImmutable()) {
            for (int i = 0; i < zzcu(); i++) {
                Map.Entry<FieldDescriptorType, Object> zzam = zzam(i);
                if (((zzax) zzam.getKey()).zzaz()) {
                    zzam.setValue(Collections.unmodifiableList((List) zzam.getValue()));
                }
            }
            for (Map.Entry<FieldDescriptorType, Object> entry : zzcv()) {
                if (((zzax) entry.getKey()).zzaz()) {
                    entry.setValue(Collections.unmodifiableList((List) entry.getValue()));
                }
            }
        }
        super.zzab();
    }
}
