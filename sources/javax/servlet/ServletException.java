package javax.servlet;

/* loaded from: classes2.dex */
public class ServletException extends Exception {
    private Throwable rootCause;

    public ServletException() {
    }

    public ServletException(String str) {
        super(str);
    }

    public ServletException(String str, Throwable th) {
        super(str);
        this.rootCause = th;
    }

    public ServletException(Throwable th) {
        super(th.getLocalizedMessage());
        this.rootCause = th;
    }

    public Throwable getRootCause() {
        return this.rootCause;
    }
}
