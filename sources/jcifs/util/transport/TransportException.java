package jcifs.util.transport;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/* loaded from: classes2.dex */
public class TransportException extends IOException {
    private Throwable rootCause;

    public TransportException() {
    }

    public TransportException(String str) {
        super(str);
    }

    public TransportException(Throwable th) {
        this.rootCause = th;
    }

    public TransportException(String str, Throwable th) {
        super(str);
        this.rootCause = th;
    }

    public Throwable getRootCause() {
        return this.rootCause;
    }

    @Override // java.lang.Throwable
    public String toString() {
        if (this.rootCause != null) {
            StringWriter stringWriter = new StringWriter();
            this.rootCause.printStackTrace(new PrintWriter(stringWriter));
            return super.toString() + "\n" + stringWriter;
        }
        return super.toString();
    }
}
