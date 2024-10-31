package org.apache.commons.net.io;

import java.util.EventObject;

/* loaded from: classes2.dex */
public class CopyStreamEvent extends EventObject {
    public static final long UNKNOWN_STREAM_SIZE = -1;
    private final int bytesTransferred;
    private final long streamSize;
    private final long totalBytesTransferred;

    public CopyStreamEvent(Object obj, long j, int i, long j2) {
        super(obj);
        this.bytesTransferred = i;
        this.totalBytesTransferred = j;
        this.streamSize = j2;
    }

    public int getBytesTransferred() {
        return this.bytesTransferred;
    }

    public long getTotalBytesTransferred() {
        return this.totalBytesTransferred;
    }

    public long getStreamSize() {
        return this.streamSize;
    }
}
