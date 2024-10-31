package jcifs.https;

/* loaded from: classes2.dex */
public class Handler extends jcifs.http.Handler {
    public static final int DEFAULT_HTTPS_PORT = 443;

    @Override // jcifs.http.Handler, java.net.URLStreamHandler
    protected int getDefaultPort() {
        return DEFAULT_HTTPS_PORT;
    }
}
