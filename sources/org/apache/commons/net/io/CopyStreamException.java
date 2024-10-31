package org.apache.commons.net.io;

import java.io.IOException;

/* loaded from: classes2.dex */
public class CopyStreamException extends IOException {
    private final IOException ioException;
    private final long totalBytesTransferred;

    public CopyStreamException(String str, long j, IOException iOException) {
        super(str);
        this.totalBytesTransferred = j;
        this.ioException = iOException;
    }

    public long getTotalBytesTransferred() {
        return this.totalBytesTransferred;
    }

    public IOException getIOException() {
        return this.ioException;
    }
}
