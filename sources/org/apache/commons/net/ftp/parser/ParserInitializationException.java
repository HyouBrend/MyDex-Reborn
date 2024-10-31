package org.apache.commons.net.ftp.parser;

/* loaded from: classes2.dex */
public class ParserInitializationException extends RuntimeException {
    private final Throwable rootCause;

    public ParserInitializationException(String str) {
        super(str);
        this.rootCause = null;
    }

    public ParserInitializationException(String str, Throwable th) {
        super(str);
        this.rootCause = th;
    }

    public Throwable getRootCause() {
        return this.rootCause;
    }
}
