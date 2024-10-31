package org.apache.commons.net.util;

import java.io.Serializable;
import java.util.EventListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/* loaded from: classes2.dex */
public class ListenerList implements Serializable, Iterable<EventListener> {
    private final CopyOnWriteArrayList<EventListener> __listeners = new CopyOnWriteArrayList<>();

    public void addListener(EventListener eventListener) {
        this.__listeners.add(eventListener);
    }

    public void removeListener(EventListener eventListener) {
        this.__listeners.remove(eventListener);
    }

    public int getListenerCount() {
        return this.__listeners.size();
    }

    @Override // java.lang.Iterable
    public Iterator<EventListener> iterator() {
        return this.__listeners.iterator();
    }
}
