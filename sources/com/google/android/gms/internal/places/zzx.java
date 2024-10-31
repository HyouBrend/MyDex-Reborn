package com.google.android.gms.internal.places;

/* loaded from: classes.dex */
abstract class zzx implements zzab {
    @Override // java.util.Iterator
    public final void remove() {
        throw new UnsupportedOperationException();
    }

    @Override // java.util.Iterator
    public /* synthetic */ Byte next() {
        return Byte.valueOf(nextByte());
    }
}
